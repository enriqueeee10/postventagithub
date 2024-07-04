package Controllers;


import Models.Marcas;
import Models.MarcasDao;
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

public class MarcasControllers implements ActionListener, MouseListener, KeyListener, TableModelListener {
    
    private final Marcas marc;
    private final MarcasDao marcDao;
    private final PanelAdmin views;
    DefaultTableModel modeloMarc = new DefaultTableModel();
    //paginador
    private PaginadoTable<Marcas> paginado;
    private JComboBox<Integer> cbxFilasPermitidas;
    
    public MarcasControllers(Marcas marc, MarcasDao marcDao, PanelAdmin views) {
        this.marc = marc;
        this.marcDao = marcDao;
        this.views = views;
        this.views.btnGuardarMarc.addActionListener(this);
        this.views.btnNuevoMarc.addActionListener(this);
        this.views.btnModificarMarc.addActionListener(this);
        this.views.JLabelMarcas.addMouseListener(this);
        this.views.TableMarc.addMouseListener(this);
        this.views.jMenuEliminarMarcas.addActionListener(this);
        this.views.jMenuReingresarMarcas.addActionListener(this);
        this.views.txtBuscarMarc.addKeyListener(this);
        this.views.txtNombreMarc.addKeyListener(this);
        this.views.btnModificarMarc.setEnabled(false);
        this.views.txtIdMarc.setVisible(false);
        this.views.TableCat.getModel().addTableModelListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnGuardarMarc) {
            if (views.txtNombreMarc.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Rellene el campo nombre");
                FrmLogin l = new FrmLogin();
                l.advertencia("Rellene el campo nombre");
            } else {
                marc.setNombre(views.txtNombreMarc.getText());
                if (marcDao.registarMarcas(marc)) {
                    Nuevo();
                    Listar();
                    views.btnGuardarMarc.setEnabled(true);
                    //JOptionPane.showMessageDialog(null, "Categoria Registrado");
                    FrmLogin l = new FrmLogin();
                    l.exito("Marca registrada");
                } else {
                    //JOptionPane.showMessageDialog(null, "La categoria ya existe");
                    FrmLogin l = new FrmLogin();
                    l.advertencia("La marca ya existe");
                }
            }
        } else if (e.getSource() == views.btnModificarMarc) {
            if (views.txtNombreMarc.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Rellene el campo nombre");
                FrmLogin l = new FrmLogin();
                l.advertencia("Rellene el campo nombre");
            } else {
                marc.setNombre(views.txtNombreMarc.getText());
                marc.setId(Integer.parseInt(views.txtIdMarc.getText()));
                if (marcDao.modificarMarcas(marc)) {
                    Nuevo();
                    Listar();
                    views.btnModificarMarc.setEnabled(false);
                    views.btnGuardarMarc.setEnabled(true);
                    //JOptionPane.showMessageDialog(null, "Categoria Modificado");
                    FrmLogin l = new FrmLogin();
                    l.exito("Marca modificada");
                }
            }
        } else if (e.getSource() == views.jMenuEliminarMarcas) {
            if (views.txtIdMarc.getText().equals("") || views.txtIdMarc.getText() == null) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila para eliminar");
            } else {
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdMarc.getText());
                    if (marcDao.accion("Inactivo", id)) {
                        Nuevo();
                        Listar();
                        views.btnModificarMarc.setEnabled(false);
                        views.btnGuardarMarc.setEnabled(true);
                        //JOptionPane.showMessageDialog(null, "Categoria Eliminado");
                        FrmLogin l = new FrmLogin();
                        l.exito("Marca eliminada");
                    }
                }
            }
        } else if (e.getSource() == views.jMenuReingresarMarcas) {
            if (views.txtIdMarc.getText().equals("") || views.txtIdMarc.getText() == null) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar");
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila para reingresar");
            } else {
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de reingresar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdMarc.getText());
                    if (marcDao.accion("Activo", id)) {
                        Nuevo();
                        Listar();
                        views.btnModificarMarc.setEnabled(false);
                        views.btnGuardarMarc.setEnabled(true);
                        //JOptionPane.showMessageDialog(null, "Categoria Reingresado");
                        FrmLogin l = new FrmLogin();
                        l.exito("Marca reingresada");
                    }
                }
            }
        } else if (e.getSource() == views.btnNuevoMarc) {
            Nuevo();
        } else if (e.getSource() == cbxFilasPermitidas) {
            paginado.eventosPag(cbxFilasPermitidas);
        }
    }
    
        @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableMarc) {
            int fila = views.TableMarc.rowAtPoint(e.getPoint());
            String estado = views.TableMarc.getValueAt(fila, 2).toString();
            if (estado.equals("Inactivo")){
                views.jMenuEliminarMarcas.setVisible(false);
                views.jMenuReingresarMarcas.setVisible(true);
            }else{
                views.jMenuEliminarMarcas.setVisible(true);
                views.jMenuReingresarMarcas.setVisible(false);
            }
            views.txtIdMarc.setText(views.TableMarc.getValueAt(fila, 0).toString());
            views.txtNombreMarc.setText(views.TableMarc.getValueAt(fila, 1).toString());
            views.btnModificarMarc.setEnabled(true);
            views.btnGuardarMarc.setEnabled(false);
        } else if (e.getSource() == views.JLabelMarcas) {
            views.jTabbedPane1.setSelectedIndex(21);
            views.btnModificarMarc.setEnabled(false);
            views.btnGuardarMarc.setEnabled(true);
            //menu();
            Listar();
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    private void Nuevo() {
        views.txtIdMarc.setText("");
        views.txtNombreMarc.setText("");
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
        if (e.getSource() == views.txtBuscarMarc) {
            Listar();
        }
    }
    
        //paginador
    private void Listar() {
        Tables color = new Tables();
        views.TableMarc.setDefaultRenderer(views.TableMarc.getColumnClass(0), color);
        views.JPaginarMarc.removeAll();
        views.TableMarc.setModel(ModeloTableMarcas());
        TotalRows<Marcas> UsL = ListarMarcas();
        paginado = new PaginadoTable(views.TableMarc, UsL, new int[]{20, 50, 100, 200}, 20);
        paginado.crearPermitidas(views.JPaginarMarc);
        cbxFilasPermitidas = paginado.getCbxFilasPermitidas();
        cbxFilasPermitidas.addActionListener(this);
        views.TableMarc.getModel().addTableModelListener(this);
        cbxFilasPermitidas.setSelectedItem(Integer.parseInt("20"));
        JTableHeader header = views.TableMarc.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.BLUE);
        header.setForeground(Color.white);
    }
    
        public TotalRows<Marcas> ListarMarcas() {
        List<Marcas> listaMarc = marcDao.Listar(views.txtBuscarMarc.getText());
        return new TotalRows<Marcas>() {
            @Override
            public int getTotalRowCount() {
                return listaMarc.size();
            }

            @Override
            public List<Marcas> getRows(int inicio, int fin) {
                return listaMarc.subList(inicio, fin);
            }

        };
    }
        
        private TableModel ModeloTableMarcas() {
        return new Paginar<Marcas>() {
            @Override
            public Object getValueAt(Marcas t, int columna) {
                switch (columna) {
                    case 0:
                        return t.getId();
                    case 1:
                        return t.getNombre();
                    case 2:
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
                        return "Estado";
                }
                return null;

            }

            @Override
            public int getColumnCount() {
                return 3;
            }

        };
    }
        
        
    @Override
    public void tableChanged(TableModelEvent tme) {
        paginado.paginate();
    }

}
