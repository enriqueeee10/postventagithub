package Controllers;

import Models.Tables;
import Models.Usuarios;
import Models.UsuariosDao;

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
import notification.Notification;

public class UsuariosControllers implements ActionListener, MouseListener, KeyListener, TableModelListener{

    private final Usuarios us;
    private final UsuariosDao usDao;
    private final PanelAdmin views;
    DefaultTableModel modeloUser = new DefaultTableModel();
    //paginador
    private PaginadoTable<Usuarios> paginado;
    private JComboBox<Integer> cbxFilasPermitidas;
    public UsuariosControllers(Usuarios us, UsuariosDao usDao, PanelAdmin views) {
        this.us = us;
        this.usDao = usDao;
        this.views = views;
        this.views.btnGuardarUser.addActionListener(this);
        this.views.btnNuevoUser.addActionListener(this);
        this.views.btnModificarUser.addActionListener(this);
        this.views.JMenuEliminarUser.addActionListener(this);
        this.views.JMenuReingresarUsuarios.addActionListener(this);
        this.views.JMenuEliminarUsuarios.addActionListener(this);
        this.views.JMenuReingresarUser.addActionListener(this);
        this.views.JLabelUsuarios.addMouseListener(this);
        this.views.TableUsers.addMouseListener(this);
        this.views.txtBuscarUser.addKeyListener(this);
        this.views.txtUsuario.addKeyListener(this);
        this.views.txtNombreUser.addKeyListener(this);
        this.views.txtClaveUser.addKeyListener(this);
        this.views.txtActual.addKeyListener(this);
        this.views.txtNueva.addKeyListener(this);
        this.views.txtIdUser.setVisible(false);
        this.views.btnCambiarPass.addActionListener(this);
        this.views.btnCambiar.addActionListener(this);
        this.views.TableUsers.getModel().addTableModelListener(this);
    }
    @Override
    
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == views.btnGuardarUser) {
        if (views.txtUsuario.getText().equals("") || views.txtNombreUser.getText().equals("")
                || views.txtClaveUser.getPassword().equals("")) {
            FrmLogin l = new FrmLogin();
            l.advertencia("Todo los campos son obligatorios.");
        } else {
            us.setUsuario(views.txtUsuario.getText());
            us.setNombre(views.txtNombreUser.getText());
            us.setClave(String.valueOf(views.txtClaveUser.getPassword()));
            us.setCaja(views.cbxCajaUser.getSelectedItem().toString());
            us.setRol(views.cbxRolUser.getSelectedItem().toString());
            if (usDao.registrar(us)) {
                Nuevo();
                Listar();
                this.views.btnModificarUser.setEnabled(false);
                this.views.btnGuardarUser.setEnabled(true);
                FrmLogin l = new FrmLogin();
                l.exito("Usuario Registrado.");
            } else {
                FrmLogin l = new FrmLogin();
                l.advertencia("El usuario ya existe.");
            }
        }
    } else if (e.getSource() == views.btnModificarUser) {
        if (views.txtUsuario.getText().equals("") || views.txtNombreUser.getText().equals("")
                || views.txtClaveUser.getPassword().equals("")) {
            FrmLogin l = new FrmLogin();
            l.advertencia("Todos los campos son obligatorios.");
        } else {
            us.setUsuario(views.txtUsuario.getText());
            us.setNombre(views.txtNombreUser.getText());
            us.setCaja(views.cbxCajaUser.getSelectedItem().toString());
            us.setRol(views.cbxRolUser.getSelectedItem().toString());
            us.setId(Integer.parseInt(views.txtIdUser.getText()));
            if (usDao.modificar(us)) {
                Nuevo();
                Listar();
                this.views.btnModificarUser.setEnabled(false);
                this.views.btnGuardarUser.setEnabled(true);
                FrmLogin l = new FrmLogin();
                l.exito("Usuario Modificado con éxito.");
            } else {
                FrmLogin l = new FrmLogin();
                l.advertencia("El usuario ya existe.");
            }
        }
    } else if (e.getSource() == views.btnNuevoUser) {
        Nuevo();
        this.views.btnModificarUser.setEnabled(false);
        this.views.btnGuardarUser.setEnabled(true);
    } else if (e.getSource() == views.JMenuEliminarUsuarios) {
        if (views.txtIdUser.getText().equals("")) {
            FrmLogin l = new FrmLogin();
            l.advertencia("Selecciona una fila para eliminar.");
        } else {
            int id = Integer.parseInt(views.txtIdUser.getText());
            Usuarios usuario = usDao.obtenerUsuarioPorId(id); // 
            if (usuario.getRol().equals("ADMINISTRADOR")) {
                //JOptionPane.showMessageDialog(null, "No se puede eliminar a un Usuario Administrador");
                FrmLogin l = new FrmLogin();
                l.error("No se puede eliminar a un usuario Administrador.");
            } else {
                int pregunta = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    if (usDao.accion("Inactivo", id)) {
                        Nuevo();
                        Listar();
                        this.views.btnModificarUser.setEnabled(false);
                        this.views.btnGuardarUser.setEnabled(true);
                        FrmLogin l = new FrmLogin();
                        l.exito("Usuario eliminado.");
                    } else {
                        FrmLogin l = new FrmLogin();
                        l.error("Error al eliminar usuario.");
                    }
                }
            }
        }
    } else if (e.getSource() == views.JMenuReingresarUsuarios) {
        if (views.txtIdUser.getText().equals("")) {
            FrmLogin l = new FrmLogin();
            l.advertencia("Selecciona una fila.");
        } else {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Estas seguro de reingresar?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (pregunta == 0) {
                int id = Integer.parseInt(views.txtIdUser.getText());
                if (usDao.accion("Activo", id)) {
                    Nuevo();
                    Listar();
                    this.views.btnModificarUser.setEnabled(false);
                    this.views.btnGuardarUser.setEnabled(true);
                    FrmLogin l = new FrmLogin();
                    l.exito("Usuario reingresado con éxito.");
                } else {
                    FrmLogin l = new FrmLogin();
                    l.error("Error al reingresar.");
                }
            }
        }
    } else if (e.getSource() == views.btnCambiarPass) {
        views.jTabbedPane1.setSelectedIndex(13);
        views.txtActual.requestFocus();
    } else if (e.getSource() == views.btnCambiar) {
        if (views.txtActual.getText().equals("") || String.valueOf(views.txtNueva.getPassword()).equals("")) {
            FrmLogin l = new FrmLogin();
            l.advertencia("Rellene los campos.");
        } else {
            if (usDao.cambiar(views.txtIdLogin.getText(), views.txtActual.getText())) {
                if (usDao.cambiarPass(String.valueOf(views.txtNueva.getPassword()), (views.txtIdLogin.getText()))) {
                    FrmLogin frm = new FrmLogin();
                    frm.setVisible(true);
                    views.dispose();
                }
            } else {
                FrmLogin l = new FrmLogin();
                l.error("Contraseña actual incorrecta.");
            }
        }
    } else if (e.getSource() == cbxFilasPermitidas) {
        paginado.eventosPag(cbxFilasPermitidas);
    }
}

    /* public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnGuardarUser) {
            if (views.txtUsuario.getText().equals("") || views.txtNombreUser.getText().equals("")
                    || views.txtClaveUser.getPassword().equals("")) {
                //JOptionPane.showMessageDialog(null, "Todo los campos son obligatorios");
                    FrmLogin l = new FrmLogin();
                   l.advertencia("Todo los campos son obligatorios.");
                
            }else{
                us.setUsuario(views.txtUsuario.getText());
                us.setNombre(views.txtNombreUser.getText());
                us.setClave(String.valueOf(views.txtClaveUser.getPassword()));
                us.setCaja(views.cbxCajaUser.getSelectedItem().toString());
                us.setRol(views.cbxRolUser.getSelectedItem().toString());
                if (usDao.registrar(us)) {
                    Nuevo();
                    Listar();
                    this.views.btnModificarUser.setEnabled(false);
                    this.views.btnGuardarUser.setEnabled(true);
                   //JOptionPane.showMessageDialog(null, "Usuario Registrado");
                   FrmLogin l = new FrmLogin();
                   l.exito("Usuario Registrado.");
                }else{
                    //JOptionPane.showMessageDialog(null, "El usuario ya existe");
                    FrmLogin l = new FrmLogin();
                   l.advertencia("El usuario ya existe.");
                }
            }
        }else if (e.getSource() == views.btnModificarUser) {
            if (views.txtUsuario.getText().equals("") || views.txtNombreUser.getText().equals("")
                    || views.txtClaveUser.getPassword().equals("")) {
                //JOptionPane.showMessageDialog(null, "Todo los campos son obligatorios");
                 FrmLogin l = new FrmLogin();
                 l.advertencia("Todos los campos son obligatorios.");
            }else{
                us.setUsuario(views.txtUsuario.getText());
                us.setNombre(views.txtNombreUser.getText());
                us.setCaja(views.cbxCajaUser.getSelectedItem().toString());
                us.setRol(views.cbxRolUser.getSelectedItem().toString());
                us.setId(Integer.parseInt(views.txtIdUser.getText()));
                if (usDao.modificar(us)) {
                    Nuevo();
                    Listar();
                    this.views.btnModificarUser.setEnabled(false);
                    this.views.btnGuardarUser.setEnabled(true);
                   //JOptionPane.showMessageDialog(null, "Usuario Modificado con éxito");
                   FrmLogin l = new FrmLogin();
                   l.exito("Usuario Modificado con éxito.");
                }else{
                    //JOptionPane.showMessageDialog(null, "El usuario ya existe");
                    FrmLogin l = new FrmLogin();
                   l.advertencia("El usuario ya existe.");
                }
            }
        }else if (e.getSource() == views.btnNuevoUser) {
            Nuevo();
            this.views.btnModificarUser.setEnabled(false);
            this.views.btnGuardarUser.setEnabled(true);
        }else if (e.getSource() == views.JMenuEliminarUsuarios) {
            if (views.txtIdUser.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar");
                 FrmLogin l = new FrmLogin();
                   l.advertencia("Selecciona una fila para eliminar.");
                
            }else{
                int pregunta = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdUser.getText());
                    if (usDao.accion("Inactivo",id)) {
                        Nuevo();
                        Listar();
                        this.views.btnModificarUser.setEnabled(false);
                        this.views.btnGuardarUser.setEnabled(true);
                       //JOptionPane.showMessageDialog(null, "Usuario eliminado");
                        FrmLogin l = new FrmLogin();
                        l.exito("Usuario eliminado.");
                    }else{
                        //JOptionPane.showMessageDialog(null, "Error al eliminar usuario");
                        FrmLogin l = new FrmLogin();
                        l.error("Error al eliminar usuario.");
                    }
                }
            }
        }else if (e.getSource() == views.JMenuReingresarUsuarios) {
            if (views.txtIdUser.getText().equals("")) {
               //JOptionPane.showMessageDialog(null, "Selecciona una fila");
               FrmLogin l = new FrmLogin();
               l.advertencia("Selecciona una fila.");
            }else{
                int pregunta = JOptionPane.showConfirmDialog(null, "¿Estas seguro de reingresar?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdUser.getText());
                    if (usDao.accion("Activo",id)) {
                        Nuevo();
                        Listar();
                        this.views.btnModificarUser.setEnabled(false);
                        this.views.btnGuardarUser.setEnabled(true);
                       //JOptionPane.showMessageDialog(null, "Usuario reingresado");
                       FrmLogin l = new FrmLogin();
                       l.exito("Usuario reingresado con éxito.");
                    }else{
                        //JOptionPane.showMessageDialog(null, "Error al reingresar");
                        FrmLogin l = new FrmLogin();
                        l.error("Error al reingresar.");
                    }
                }
            }
        }else if(e.getSource() == views.btnCambiarPass){
           views.jTabbedPane1.setSelectedIndex(13);
           views.txtActual.requestFocus();
        }else if(e.getSource() == views.btnCambiar){
            if (views.txtActual.getText().equals("") || String.valueOf(views.txtNueva.getPassword()).equals("")) {
                //JOptionPane.showMessageDialog(null, "Rellene los Campos");
                FrmLogin l = new FrmLogin();
                l.advertencia("Rellene los campos.");
            }else{
                if (usDao.cambiar(views.txtIdLogin.getText(), views.txtActual.getText())) {
                    if (usDao.cambiarPass(String.valueOf(views.txtNueva.getPassword()),(views.txtIdLogin.getText()))) {
                        FrmLogin frm = new FrmLogin();
                        frm.setVisible(true);
                        views.dispose();
                    }
                }else{
                    //JOptionPane.showMessageDialog(null, "Contraseña actual incorrecta");
                    FrmLogin l = new FrmLogin();
                    l.error("Contraseña actual incorrecta.");
                }
            }
        }else if(e.getSource() == cbxFilasPermitidas){
            paginado.eventosPag(cbxFilasPermitidas);
        }
    } */

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.JLabelUsuarios) {
            //menu();
            views.jTabbedPane1.setSelectedIndex(5);
            Listar();
            this.views.btnModificarUser.setEnabled(false);
            this.views.btnGuardarUser.setEnabled(true);
        }else if (e.getSource() == views.TableUsers) {
            int fila = views.TableUsers.rowAtPoint(e.getPoint());
            String estado = views.TableUsers.getValueAt(fila, 5).toString();
            if (estado.equals("Inactivo")){
                views.JMenuEliminarUser.setVisible(false);
                views.JMenuReingresarUser.setVisible(true);
            }else{
                views.JMenuEliminarUser.setVisible(true);
                views.JMenuReingresarUser.setVisible(false);
            }
            views.txtIdUser.setText(views.TableUsers.getValueAt(fila, 0).toString());
            views.txtUsuario.setText(views.TableUsers.getValueAt(fila, 1).toString());
            views.txtNombreUser.setText(views.TableUsers.getValueAt(fila, 2).toString());
            views.cbxCajaUser.setSelectedItem(views.TableUsers.getValueAt(fila, 3).toString());
            views.cbxRolUser.setSelectedItem(views.TableUsers.getValueAt(fila, 4).toString());
            this.views.btnModificarUser.setEnabled(true);
            this.views.btnGuardarUser.setEnabled(false);
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
    private void Nuevo(){
        views.txtUsuario.setText("");
        views.txtNombreUser.setText("");
        views.txtClaveUser.setText("");
        views.txtIdUser.setText("");
    }
    //paginador
    private void Listar() {
        Tables color = new Tables();
        views.TableUsers.setDefaultRenderer(views.TableUsers.getColumnClass(0), color);
        views.JPaginarUser.removeAll();
        views.TableUsers.setModel(ModeloTableUsers());
        TotalRows<Usuarios> UsL = ListarUsuarios();
        paginado = new PaginadoTable(views.TableUsers, UsL, new int[]{20, 50, 100, 200}, 20);
        paginado.crearPermitidas(views.JPaginarUser);
        cbxFilasPermitidas = paginado.getCbxFilasPermitidas();
        cbxFilasPermitidas.addActionListener(this);
        views.TableUsers.getModel().addTableModelListener(this);
        cbxFilasPermitidas.setSelectedItem(Integer.parseInt("20"));
        JTableHeader header = views.TableUsers.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.BLUE);
        header.setForeground(Color.white);
    }

    public TotalRows<Usuarios> ListarUsuarios() {
        List<Usuarios> listaUsers = usDao.Listar(views.txtBuscarUser.getText());
        return new TotalRows<Usuarios>() {
            @Override
            public int getTotalRowCount() {
                return listaUsers.size();
            }

            @Override
            public List<Usuarios> getRows(int inicio, int fin) {
                return listaUsers.subList(inicio, fin);
            }

        };
    }
    
    private TableModel ModeloTableUsers() {
        return new Paginar<Usuarios>() {
            @Override
            public Object getValueAt(Usuarios t, int columna) {
                switch (columna) {
                    case 0:
                        return t.getId();
                    case 1:
                        return t.getUsuario();
                    case 2:
                        return t.getNombre();
                    case 3:
                        return t.getCaja();
                    case 4:
                        return t.getRol();
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
                        return "Usuario";
                    case 2:
                        return "Nombre";
                    case 3:
                        return "Caja";
                    case 4:
                        return "Rol";
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
        if (e.getSource() == views.txtBuscarUser) {
            Listar();
        }
    }

    @Override
    public void tableChanged(TableModelEvent tme) {
        paginado.paginate(); 
    }
}
