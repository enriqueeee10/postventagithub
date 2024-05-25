package Controllers;


import Models.Config;
import Models.ConfigDao;
import Views.FrmLogin;
import Views.PanelAdmin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ConfigControllers implements ActionListener, MouseListener, KeyListener {

    private Config con;
    private ConfigDao conDao;
    private PanelAdmin views;

    public ConfigControllers(Config con, ConfigDao conDao, PanelAdmin views) {
        this.con = con;
        this.conDao = conDao;
        this.views = views;
        this.views.btnModificarEmpresa.addActionListener(this);
        this.views.JLHome.addMouseListener(this);
        this.views.JLabelConfig.addMouseListener(this);
        this.views.txtRucEmpresa.addKeyListener(this);
        this.views.txtNombreEmpresa.addKeyListener(this);
        this.views.txtTelefonoEmpresa.addKeyListener(this);
        this.views.txtDireccionEmpresa.addKeyListener(this);
        this.views.txtMensaje.addKeyListener(this);
        listar();
        this.views.JPBarra.removeAll();
        this.views.JPTorta.removeAll();
        conDao.barra(views.JPBarra);
        conDao.torta(views.JPTorta);
        this.views.TotalPro.setText("" + conDao.admin("productos"));
        this.views.TotalClientes.setText("" + conDao.admin("clientes"));
        this.views.TotalCompras.setText("" + conDao.adminTotal("compras"));
        this.views.JLabelTotalVentas.setText("" + conDao.adminTotal("ventas"));
        ocultar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnModificarEmpresa) {
            if (views.txtRucEmpresa.getText().equals("") || views.txtNombreEmpresa.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Los campos estan vacios");
                FrmLogin l = new FrmLogin();
                l.advertencia("Los campos están vacíos");
            } else {
                con.setRuc(views.txtRucEmpresa.getText());
                con.setNombre(views.txtNombreEmpresa.getText());
                con.setTelefono(views.txtTelefonoEmpresa.getText());
                con.setDireccion(views.txtDireccionEmpresa.getText());
                con.setMensaje(views.txtMensaje.getText());
                con.setId(Integer.parseInt(views.txtIdConfig.getText()));
                if (conDao.empresa(con)) {
                    listar();
                }
            }
        }
    }

    private void listar() {
        con = conDao.ListarConfig();
        views.txtIdConfig.setText(""+con.getId());
        views.txtRucEmpresa.setText(con.getRuc());
        views.txtNombreEmpresa.setText(con.getNombre());
        views.txtTelefonoEmpresa.setText(con.getTelefono());
        views.txtDireccionEmpresa.setText(con.getDireccion());
        views.txtMensaje.setText(con.getMensaje());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.JLHome) {
            views.jTabbedPane1.setSelectedIndex(0);
            views.TotalPro.setText("" + conDao.admin("productos"));
            views.TotalClientes.setText("" + conDao.admin("clientes"));
            views.TotalCompras.setText("" + conDao.adminTotal("compras"));
            views.JLabelTotalVentas.setText("" + conDao.adminTotal("ventas"));
            views.JPBarra.removeAll();
            views.JPTorta.removeAll();
            conDao.barra(views.JPBarra);
            conDao.torta(views.JPTorta);
        } else if (e.getSource() == views.JLabelConfig) {
            views.jTabbedPane1.setSelectedIndex(10);
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
        if (me.getSource() == views.JLabelCat ) {
            views.JMenuEliminarCat.setBackground(new Color(255,51,51));
        } else if (me.getSource()== views.JLabelClientes) {
            views.MenuClientes.setBackground(new Color(255,51,51));
            
        }

        
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }


    private void ocultar() {
        views.txtIdLogin.setVisible(false);
        views.txtIdC.setVisible(false);
        views.txtIdCaja.setVisible(false);
        views.txtIdCat.setVisible(false);
        views.txtIdCli.setVisible(false);
        views.txtIdConfig.setVisible(false);
        views.txtIdMed.setVisible(false);
        views.txtIdNV.setVisible(false);
        views.txtIdPro.setVisible(false);
        views.txtIdProv.setVisible(false);
        views.txtIdUser.setVisible(false);
        views.txtVentasCliente.setVisible(false);
        views.txtVentasCliente1.setVisible(false);
        views.txtVentasId.setVisible(false);
        views.txtVentasId1.setVisible(false);
        views.txtVentasTotal.setVisible(false);
        views.txtVentasTotal1.setVisible(false);
        views.txtVentasUsuario.setVisible(false);
        views.txtUserLogin.setVisible(false);
        views.txtCajaLogin.setVisible(false);
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
        if (e.getSource() == views.txtRucEmpresa) {
           if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               views.txtNombreEmpresa.requestFocus();
           }
        }else if(e.getSource() == views.txtNombreEmpresa){
           if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               views.txtTelefonoEmpresa.requestFocus();
           } 
        }else if(e.getSource() == views.txtTelefonoEmpresa){
           if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               views.txtDireccionEmpresa.requestFocus();
           } 
        }else{
           if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               views.txtMensaje.requestFocus();
           }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
     }
}
