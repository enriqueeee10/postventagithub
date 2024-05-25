package Controllers;


import Models.Proveedor;
import Models.ProveedorDao;
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

public class ProveedorControllers implements ActionListener, MouseListener, KeyListener, TableModelListener{

    private final Proveedor prov;
    private final ProveedorDao provDao;
    private final PanelAdmin views;
    DefaultTableModel modeloProv = new DefaultTableModel();
    private PaginadoTable<Proveedor> paginado;
    private JComboBox<Integer> cbxFilasPermitidas;
    public ProveedorControllers(Proveedor prov, ProveedorDao provDao, PanelAdmin views) {
        this.prov = prov;
        this.provDao = provDao;
        this.views = views;
        this.views.btnGuardarProv.addActionListener(this);
        this.views.btnNuevoProv.addActionListener(this);
        this.views.btnModificarProv.addActionListener(this);
        this.views.TableProveedor.addMouseListener(this);
        this.views.JLabelProveedor.addMouseListener(this);
        this.views.JMenuEliminarProv.addActionListener(this);
        this.views.JMenuReingresarProv.addActionListener(this);
        this.views.txtBuscarProv.addKeyListener(this);
        this.views.txtRucProv.addKeyListener(this);
        this.views.txtNombreProv.addKeyListener(this);
        this.views.txtTelefonoProv.addKeyListener(this);
        this.views.txtDireccionProv.addKeyListener(this);
        this.views.txtIdProv.setVisible(false);
        this.views.TableProveedor.getModel().addTableModelListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnGuardarProv) {
            if (views.txtRucProv.getText().equals("") || views.txtNombreProv.getText().equals("") || views.txtTelefonoProv.getText().equals("") || views.txtDireccionProv.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Todo los campos son obligatorios"); 
                FrmLogin l = new FrmLogin();
                l.advertencia("Todos los campos son obligatorios");
            }else{
                prov.setRuc(views.txtRucProv.getText());
                prov.setNombre(views.txtNombreProv.getText());
                prov.setTelefono(views.txtTelefonoProv.getText());
                prov.setDireccion(views.txtDireccionProv.getText());
                if (provDao.registrar(prov)) {
                    Nuevo();
                    Listar();
                    //JOptionPane.showMessageDialog(null, "Proveedor Registrado"); 
                    FrmLogin l = new FrmLogin();
                    l.exito("Proveedor registrado");
                }else{
                    //JOptionPane.showMessageDialog(null, "El proveedor ya existe");
                    FrmLogin l = new FrmLogin();
                    l.advertencia("El proveedor ya existe");
                }
            }
        }else if (e.getSource() == views.btnModificarProv){
            if (views.txtRucProv.getText().equals("") || views.txtNombreProv.getText().equals("") || views.txtTelefonoProv.getText().equals("") || views.txtDireccionProv.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Todo los campos son obligatorios"); 
                FrmLogin l = new FrmLogin();
                l.advertencia("Todos los campos son obligatorios");
            }else{
                prov.setRuc(views.txtRucProv.getText());
                prov.setNombre(views.txtNombreProv.getText());
                prov.setTelefono(views.txtTelefonoProv.getText());
                prov.setDireccion(views.txtDireccionProv.getText());
                prov.setId(Integer.parseInt(views.txtIdProv.getText()));
                if (provDao.modificar(prov)) {
                    Nuevo();
                    Listar();
                    //JOptionPane.showMessageDialog(null, "Proveedor Modificado"); 
                    FrmLogin l = new FrmLogin();
                    l.exito("Proveedor modificado");
                }
            }
        }else if (e.getSource() == views.JMenuEliminarProv){
            if (views.txtIdProv.getText().equals("") || views.txtIdProv.getText() == null) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar"); 
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila para eliminar");
            }else{
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdProv.getText());
                    if (provDao.accion("Inactivo", id)) {
                        Nuevo();
                        Listar();
                        //JOptionPane.showMessageDialog(null, "Proveedor Eliminado"); 
                        FrmLogin l = new FrmLogin();
                        l.exito("Proveedor eliminado");
                    }
                }
            }
        }else if (e.getSource() == views.JMenuReingresarProv){
            if (views.txtIdProv.getText().equals("") || views.txtIdProv.getText() == null) {
                //JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar"); 
                FrmLogin l = new FrmLogin();
                l.advertencia("Seleccione una fila para reingresar");
            }else{
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de reingresar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdProv.getText());
                    if (provDao.accion("Activo", id)) {
                        Nuevo();
                        Listar();
                        //JOptionPane.showMessageDialog(null, "Proveedor Reingresado"); 
                        FrmLogin l = new FrmLogin();
                        l.exito("Proveedor reingresado");
                    }
                }
            }
        }else if (e.getSource() == views.btnNuevoProv){
            Nuevo();
        }else if(e.getSource() == cbxFilasPermitidas){
            paginado.eventosPag(cbxFilasPermitidas);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableProveedor) {
            int fila = views.TableProveedor.rowAtPoint(e.getPoint());
            String estado = views.TableProveedor.getValueAt(fila, 5).toString();
            if (estado.equals("Inactivo")){
                views.JMenuEliminarProv.setVisible(false);
                views.JMenuReingresarProv.setVisible(true);
            }else{
                views.JMenuEliminarProv.setVisible(true);
                views.JMenuReingresarProv.setVisible(false);
            }
            views.txtIdProv.setText(views.TableProveedor.getValueAt(fila, 0).toString());
            views.txtRucProv.setText(views.TableProveedor.getValueAt(fila, 1).toString());
            views.txtNombreProv.setText(views.TableProveedor.getValueAt(fila, 2).toString());
            views.txtTelefonoProv.setText(views.TableProveedor.getValueAt(fila, 3).toString());
            views.txtDireccionProv.setText(views.TableProveedor.getValueAt(fila, 4).toString());
        }else if (e.getSource() == views.JLabelProveedor) {
            views.jTabbedPane1.setSelectedIndex(4);
            Listar();
            //menu();
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
        views.txtIdProv.setText("");
        views.txtRucProv.setText("");
        views.txtNombreProv.setText("");
        views.txtTelefonoProv.setText("");
        views.txtDireccionProv.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char caracter = e.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            e.setKeyChar(Character.toUpperCase(caracter));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == views.txtRucProv) {
           if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               views.txtNombreProv.requestFocus();
           }
        }else if(e.getSource() == views.txtNombreProv){
           if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               views.txtTelefonoProv.requestFocus();
           } 
        }else{
           if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               views.txtDireccionProv.requestFocus();
           } 
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        Listar();
    }
    //paginador
    private void Listar() {
        Tables color = new Tables();
        views.TableProveedor.setDefaultRenderer(views.TableProveedor.getColumnClass(0), color);
        JTableHeader header = views.TableProveedor.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.BLUE);
        header.setForeground(Color.white);
        views.JPaginarProv.removeAll();
        views.TableProveedor.setModel(ModeloTableProv());
        TotalRows<Proveedor> ProvL = ListarProveedor();
        paginado = new PaginadoTable(views.TableProveedor, ProvL, new int[]{20, 50, 100, 200}, 20);
        paginado.crearPermitidas(views.JPaginarProv);
        cbxFilasPermitidas = paginado.getCbxFilasPermitidas();
        cbxFilasPermitidas.addActionListener(this);
        views.TableProveedor.getModel().addTableModelListener(this);
        cbxFilasPermitidas.setSelectedItem(Integer.parseInt("20"));
    }

    public TotalRows<Proveedor> ListarProveedor() {
        List<Proveedor> lista = provDao.Listar(views.txtBuscarCli.getText());
        return new TotalRows<Proveedor>() {
            @Override
            public int getTotalRowCount() {
                return lista.size();
            }

            @Override
            public List<Proveedor> getRows(int inicio, int fin) {
                return lista.subList(inicio, fin);
            }

        };
    }
    
    private TableModel ModeloTableProv() {
        return new Paginar<Proveedor>() {
            @Override
            public Object getValueAt(Proveedor t, int columna) {
                switch (columna) {
                    case 0:
                        return t.getId();
                    case 1:
                        return t.getRuc();
                    case 2:
                        return t.getNombre();
                    case 3:
                        return t.getTelefono();
                    case 4:
                        return t.getDireccion();
                    case 5:
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
                        return "Ruc";
                    case 2:
                        return "Nombre";
                    case 3:
                        return "Telefono";
                    case 4:
                        return "Direccion";
                    case 5:
                        return "Estado";
                }
                return null;

            }

            @Override
            public int getColumnCount() {
                return 6;
            }

        };
    }
    @Override
    public void tableChanged(TableModelEvent e) {
        paginado.paginate(); 
    }
}
