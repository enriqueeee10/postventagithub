package Controllers;


import Models.Medidas;
import Models.MedidasDao;
import Models.Tables;
import Paginador.PaginadoTable;
import Paginador.Paginar;
import Paginador.TotalRows;
import Views.FrmLogin;
import Views.PanelAdmin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class MedidasControllers implements ActionListener, MouseListener, KeyListener, TableModelListener{
    private final Medidas med;
    private final MedidasDao medDao;
    private final PanelAdmin views;
    DefaultTableModel modeloMed = new DefaultTableModel();
    //paginador
    private PaginadoTable<Medidas> paginado;
    private JComboBox<Integer> cbxFilasPermitidas;
    public MedidasControllers(Medidas med, MedidasDao medDao, PanelAdmin views) {
        this.med = med;
        this.medDao = medDao;
        this.views = views;
        this.views.btnGuardarMed.addActionListener(this);
        this.views.btnNuevoMed.addActionListener(this);
        this.views.btnModificarMed.addActionListener(this);
        this.views.TableMedidas.addMouseListener(this);
        this.views.JLabelMedidas.addMouseListener(this);
        this.views.JMenuEliminarMed.addActionListener(this);
        this.views.JMenuReingresarMed.addActionListener(this);
        this.views.txtBuscarMed.addKeyListener(this);
        this.views.txtNombreMed.addKeyListener(this);
        this.views.txtNombreCortoMed.addKeyListener(this);
        this.views.txtIdMed.setVisible(false);
        this.views.TableMedidas.getModel().addTableModelListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnGuardarMed) {
            if (views.txtNombreMed.getText().equals("") || views.txtNombreCortoMed.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Todo los campos son obligatorios"); 
                FrmLogin l = new FrmLogin();
                l.advertencia("Todos los campos son obligatorios");
            }else{
                med.setNombre(views.txtNombreMed.getText());
                med.setNombre_corto(views.txtNombreCortoMed.getText());
                if (medDao.registrar(med)) {
                    Nuevo();
                    Listar();
                    //JOptionPane.showMessageDialog(null, "Medida Registrado"); 
                    FrmLogin l = new FrmLogin();
                    l.exito("Medida registrada");
                }else{
                    //JOptionPane.showMessageDialog(null, "La medida ya existe");
                    FrmLogin l = new FrmLogin();
                    l.error("Seleccione una fila");
                }
            }
        }else if (e.getSource() == views.btnModificarMed){
            if (views.txtNombreMed.getText().equals("") || views.txtNombreCortoMed.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Todo los campos son obligatorios"); 
                FrmLogin l = new FrmLogin();
              l.advertencia("Todos los campos son obligatorios");
            }else{
                med.setNombre(views.txtNombreMed.getText());
                med.setNombre_corto(views.txtNombreCortoMed.getText());
                med.setId(Integer.parseInt(views.txtIdMed.getText()));
                if (medDao.modificar(med)) {
                    Nuevo();
                    Listar();
                    //JOptionPane.showMessageDialog(null, "Medida Modificado"); 
                    FrmLogin l = new FrmLogin();
                    l.exito("Medida modificada");
                }
            }
        }else if (e.getSource() == views.JMenuEliminarMed){
            if (views.txtIdMed.getText().equals("") || views.txtIdMed.getText() == null) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar"); 
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila para eliminar");
            }else{
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdMed.getText());
                    if (medDao.accion("Inactivo", id)) {
                        Nuevo();
                        Listar();
                        //JOptionPane.showMessageDialog(null, "Medida Eliminado"); 
                        FrmLogin l = new FrmLogin();
                        l.exito("Medida eliminada");
                    }
                }
            }
        }else if (e.getSource() == views.JMenuReingresarMed){
            if (views.txtIdMed.getText().equals("") || views.txtIdMed.getText() == null) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar"); 
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila para reingresar");
            }else{
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de reingresar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdMed.getText());
                    if (medDao.accion("Activo", id)) {
                        Nuevo();
                        Listar();
                        //JOptionPane.showMessageDialog(null, "Medida Reingresado"); 
                        FrmLogin l = new FrmLogin();
                        l.exito("Medida reingresada");
                    }
                }
            }
        }else if(e.getSource() == views.btnNuevoMed){
            Nuevo();
        }else if(e.getSource() == cbxFilasPermitidas){
            paginado.eventosPag(cbxFilasPermitidas);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableMedidas) {
            int fila = views.TableMedidas.rowAtPoint(e.getPoint());
            String estado = views.TableMedidas.getValueAt(fila, 2).toString();
            if (estado.equals("Inactivo")){
                views.JMenuEliminarMed.setVisible(false);
                views.JMenuReingresarMed.setVisible(true);
            }else{
                views.JMenuEliminarMed.setVisible(true);
                views.JMenuReingresarMed.setVisible(false);
            }
            views.txtIdMed.setText(views.TableMedidas.getValueAt(fila, 0).toString());
            views.txtNombreMed.setText(views.TableMedidas.getValueAt(fila, 1).toString());
            views.txtNombreCortoMed.setText(views.TableMedidas.getValueAt(fila, 2).toString());
        }else if(e.getSource() == views.JLabelMedidas){
            views.jTabbedPane1.setSelectedIndex(8);
            //menu();
            Listar();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    private void Nuevo() {
        views.txtIdMed.setText("");
        views.txtNombreMed.setText("");
        views.txtNombreCortoMed.setText("");
    }
    @Override
    public void keyTyped(KeyEvent e) {
        char caracter = e.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            e.setKeyChar(Character.toUpperCase(caracter));
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarMed) {
            Listar();
        }
    }

    //paginador
    private void Listar() {
        Tables color = new Tables();
        views.TableMedidas.setDefaultRenderer(views.TableMedidas.getColumnClass(0), color);
        views.JPaginarMedida.removeAll();
        views.TableMedidas.setModel(ModeloTableMedidas());
        TotalRows<Medidas> UsL = ListarMedidas();
        paginado = new PaginadoTable(views.TableMedidas, UsL, new int[]{20, 50, 100, 200}, 20);
        paginado.crearPermitidas(views.JPaginarMedida);
        cbxFilasPermitidas = paginado.getCbxFilasPermitidas();
        cbxFilasPermitidas.addActionListener(this);
        views.TableMedidas.getModel().addTableModelListener(this);
        cbxFilasPermitidas.setSelectedItem(Integer.parseInt("20"));
        JTableHeader header = views.TableMedidas.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.BLUE);
        header.setForeground(Color.white);
    }

    public TotalRows<Medidas> ListarMedidas() {
        List<Medidas> listaMed = medDao.Listar(views.txtBuscarMed.getText());
        return new TotalRows<Medidas>() {
            @Override
            public int getTotalRowCount() {
                return listaMed.size();
            }

            @Override
            public List<Medidas> getRows(int inicio, int fin) {
                return listaMed.subList(inicio, fin);
            }

        };
    }
    
    private TableModel ModeloTableMedidas() {
        return new Paginar<Medidas>() {
            @Override
            public Object getValueAt(Medidas t, int columna) {
                switch (columna) {
                    case 0:
                        return t.getId();
                    case 1:
                        return t.getNombre();
                    case 2:
                        return t.getNombre_corto();
                    case 3:
                        return t.getEstado();
                }
                return null;
            }

            @Override
            public String getColumnName(int columna) {
                switch (columna) {
                    case 0:
                        return "Id";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "Nombre corto";
                    case 3:
                        return "Estado";
                }
                return null;

            }

            @Override
            public int getColumnCount() {
                return 4;
            }

        };
    }
    @Override
    public void tableChanged(TableModelEvent tme) {
        paginado.paginate(); 
    }


}
