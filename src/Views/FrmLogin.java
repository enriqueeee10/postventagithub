package Views;

import Controllers.LoginControllers;
import Models.Usuarios;
import Models.UsuariosDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import notification.Notification;

public class FrmLogin extends javax.swing.JFrame {
    Usuarios us = new Usuarios();
    UsuariosDao usDao = new UsuariosDao();
    int contador;
    int segundos = 30;
    private Timer tiempo;
    
    public FrmLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtUsuario.setText("lramirezcenepo@gmail.com");
        txtClave.setText("12345678");
        barra.setVisible(false);
    }
    
     public class BarraProgreso implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            contador++;
            barra.setValue(contador);
            if (contador == 100) {
                tiempo.stop();
                if (barra.getValue() == 100) {
                    PanelAdmin padmin = new PanelAdmin(us);
                    padmin.setVisible(true);
                    dispose();
                }
            }
        }
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        barra = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        VER = new javax.swing.JLabel();
        OCULTAR = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(14, 76, 117));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        barra.setBackground(new java.awt.Color(255, 255, 255));
        barra.setForeground(new java.awt.Color(0, 110, 255));
        barra.setBorder(null);

        jLabel5.setBackground(new java.awt.Color(255, 153, 0));
        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/contrasena (1).png"))); // NOI18N
        jLabel5.setText("LOGIN");

        txtUsuario.setBackground(new java.awt.Color(14, 76, 117));
        txtUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setBorder(null);
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Correo");

        txtClave.setBackground(new java.awt.Color(14, 76, 117));
        txtClave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtClave.setForeground(new java.awt.Color(255, 255, 255));
        txtClave.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClave.setBorder(null);
        txtClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Contraseña");

        btnSalir.setBackground(new java.awt.Color(204, 0, 0));
        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(0, 153, 0));
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/User.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Lock.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        VER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Eye_1.png"))); // NOI18N
        VER.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VER.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VERMouseClicked(evt);
            }
        });

        OCULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Hide.png"))); // NOI18N
        OCULTAR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        OCULTAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OCULTARMouseClicked(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 255));
        jButton2.setText("¿Olvidaste tu contraseña?");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(VER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(OCULTAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jButton2)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(VER)
                    .addComponent(OCULTAR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jButton2)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 340, 520));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/titulo.png"))); // NOI18N
        jLabel9.setMaximumSize(new java.awt.Dimension(192, 192));
        jLabel9.setMinimumSize(new java.awt.Dimension(192, 192));
        jLabel9.setName(""); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(192, 192));
        jLabel9.setRequestFocusEnabled(false);
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 240, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("RESTAURANTE");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 280, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("SISTEMA ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 190, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("DE");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 50, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 520));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Elegir recuperacion = new Elegir();
        recuperacion.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void OCULTARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OCULTARMouseClicked
        VER.setVisible(true);
        OCULTAR.setVisible(false);
        txtClave.setEchoChar('●');
    }//GEN-LAST:event_OCULTARMouseClicked

    private void VERMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VERMouseClicked
        VER.setVisible(false);
        OCULTAR.setVisible(true);
        txtClave.setEchoChar((char)0);
    }//GEN-LAST:event_VERMouseClicked

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        validar();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginMouseClicked

     public void validar(){
    String correo = txtUsuario.getText();
    String clave = String.valueOf(txtClave.getPassword());
    UsuariosDao usuariodao = new UsuariosDao();

    if (correo.isEmpty()) {
        //JOptionPane.showMessageDialog(null, "Llene el campo correo");
        FrmLogin l = new FrmLogin();
        l.advertencia("Llene el campo correo");
        return;
    }
    if (!usuariodao.correoExiste(correo)) {
            //JOptionPane.showMessageDialog(null, "Correo no registrado o no existe en el sistema.");
            FrmLogin l = new FrmLogin();
            l.error("Correo no registrado o no existe en el sistema.");
            return;
        }
    if (clave.equals("")) {
        //JOptionPane.showMessageDialog(null, "Llene el campo contraseña");
        FrmLogin l = new FrmLogin();
        l.advertencia("Llene el campo contraseña");
        return;
    }
    
    us = usDao.login(correo, clave);
    if (us.getCorreo()!= null && us.getClave()!= null) {
        barra.setVisible(true);
        contador = -1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo = new Timer(segundos, new BarraProgreso());
        tiempo.start();
    } else {
        //JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
        FrmLogin l = new FrmLogin();
        l.error("Contraseña incorrecta");
    }
    }
    
    /* public void validar() {
    String correo = txtUsuario.getText();
    String clave = String.valueOf(txtClave.getPassword());
    UsuariosDao usuariodao = new UsuariosDao();
    String estado = usuariodao.validarestado(correo);

    if (correo.isEmpty()) {
        FrmLogin l = new FrmLogin();
        l.advertencia("Llene el campo correo");
        return;
    }
    if (!usuariodao.correoExiste(correo)) {
        FrmLogin l = new FrmLogin();
        l.error("Correo no registrado o no existe en el sistema.");
        return;
    }
    if (clave.isEmpty()) {
        FrmLogin l = new FrmLogin();
        l.advertencia("Llene el campo contraseña");
        return;
    }
    if (!estado.equalsIgnoreCase("activo")) {
        FrmLogin l = new FrmLogin();
        l.advertencia("Usuario Inhabilitado, Solicite habilitación");
        return;
    }

    Usuarios us = usuariodao.login(correo, clave);
    if (us != null && us.getCorreo() != null && us.getClave() != null) {
        barra.setVisible(true);
        contador = -1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo = new Timer(segundos, new BarraProgreso());
        tiempo.start();
    } else {
        FrmLogin l = new FrmLogin();
        l.error("Contraseña incorrecta");
    }
} */
    
    public void exito(String texto){
        Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, texto);
        panel.showNotification();
    }
    
    public void advertencia(String texto){
        Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, texto);
        panel.showNotification();
    }
    
    public void error(String texto){
        Notification panel = new Notification(this, Notification.Type.ERROR, Notification.Location.TOP_RIGHT, texto);
        panel.showNotification();
    }
    
    
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel OCULTAR;
    private javax.swing.JLabel VER;
    private javax.swing.JProgressBar barra;
    public javax.swing.JButton btnLogin;
    public javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JPasswordField txtClave;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
