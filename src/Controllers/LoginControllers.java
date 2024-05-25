package Controllers;

import Models.Usuarios;
import Models.UsuariosDao;
import Views.FrmLogin;
import Views.PanelAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class LoginControllers implements ActionListener, KeyListener {

    private Usuarios us;
    private final UsuariosDao usDao;
    private final FrmLogin frm;

    public LoginControllers(Usuarios us, UsuariosDao usDao, FrmLogin frm) {
        this.us = us;
        this.usDao = usDao;
        this.frm = frm;
        this.frm.btnLogin.addActionListener(this);
        this.frm.btnSalir.addActionListener(this);
        this.frm.txtUsuario.addKeyListener(this);
        this.frm.txtClave.addKeyListener(this);
        this.frm.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnLogin) {
            iniciar();
        } else {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea salir", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (pregunta == 0) {
                System.exit(0);
            }
        }
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
        if (e.getSource() == frm.txtUsuario) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                frm.txtClave.requestFocus();
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                iniciar();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void iniciar() {
        if (frm.txtUsuario.getText().equals("") || String.valueOf(frm.txtClave.getPassword()).equals("")) {
            //JOptionPane.showMessageDialog(null, "Rellene los campos");
            FrmLogin l = new FrmLogin();
            l.advertencia("Rellene todos los campos");
        } else {
            String usuario = frm.txtUsuario.getText();
            String clave = String.valueOf(frm.txtClave.getPassword());
            us = usDao.login(usuario, clave);
            if (us.getUsuario() != null) {
                PanelAdmin admin = new PanelAdmin(us);
                admin.setVisible(true);
                admin.exito("Bienvenido");
                this.frm.dispose();
            } else {
                //JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
                FrmLogin lg = new FrmLogin();
                lg.error("Usuario o contraseña incorrecta");
                frm.txtClave.setText("");
                frm.txtClave.requestFocus();
            }
        }
    }

}
