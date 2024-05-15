/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.NumeroAleatorio;
import Models.UsuariosDao;
import Models.Usuarios;
import Views.RecCorreo;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import java.util.Properties;
/**
 *
 * @author monta
 */
public class Ctrl_User {
    Usuarios users;
    UsuariosDao usDao;
    NumeroAleatorio ale;
    
    //Variables para el envio de correo
    private static String remitente = "hzsxzenterprises@gmail.com";
    private static String clave_remitente = "mpxouishxrjaucdb";
    public static String destinatario;
    private String emailTo;
    private String titulo;
    private String contenido;
    
    private Properties pro;
    private Session session;
    private MimeMessage correo;
     
    public Ctrl_User(Usuarios users, UsuariosDao usDao) {
        this.users = users;
        this.usDao = usDao;
    }
    
    public void Iniciar(){
        pro = new Properties();
    }
    
    
    public boolean Enviar(String user){
        users.setNombre(user);
        String codigoAleatorio = NumeroAleatorio.generarCodigoAleatorio();
        if(usDao.Recuperacion(users)){
            destinatario = users.getCorreo();
            emailTo = destinatario;
            titulo = "Solicitud de Recuperacion de clave";
            //contenido = "Tu contraseña es: " + usuarios.getPassword();
                
            contenido = "<div style=\"font-family: 'Arial', sans-serif; background-color: #f9f9f9; padding: 30px; border-radius: 10px;\">" +
                      "<div style=\"background-color: #fff; padding: 30px; border-radius: 10px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\">" +
                          "<h2 style=\"color: #333; text-align: center; font-size: 28px; margin-bottom: 20px;\">Estimado/a " + user + ",</h2>" +
                          "<p style=\"color: #555; text-align: justify; font-size: 16px; line-height: 1.5;\">Gracias por utilizar nuestro servicio. A continuación, te proporcionamos los detalles de tu cuenta:</p>" +
                          "<ul style=\"list-style-type: none; padding-left: 0; margin-bottom: 20px;\">" +
                              "<li><strong>Usuario:</strong> " + user + "</li>" +
                              "<li><strong>Código de Verificación:</strong> " + codigoAleatorio + "</li>" +
                          "</ul>" +
                          "<p style=\"color: #555; text-align: justify; font-size: 16px; line-height: 1.5;\">Te recomendamos mantener esta información segura y no compartirla con nadie.</p>" +
                          "<p style=\"color: #333; text-align: center; font-size: 18px; margin-top: 30px;\">Atentamente,</p>" +
                          "<p style=\"color: #333; text-align: center; font-size: 18px;\">El equipo de Software Vado</p>" +
                          "<div style=\"text-align: center; margin-top: 20px;\">" +
                              "<img src=\"" + getClass().getResource("/Img/Hz.png") + "\" style=\"max-width: 200px; height: auto;\">" +
                          "</div>" +
                      "</div>" +
                   "</div>";

            // SMTP
            pro.put("mail.smtp.host", "smtp.gmail.com");
            pro.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            pro.setProperty("mail.smtp.starttls.enable", "true");
            pro.setProperty("mail.smtp.port", "587");
            pro.setProperty("mail.smtp.user", remitente);
            pro.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
            pro.setProperty("mail.smtp.auth", "true");
                
            session = Session.getDefaultInstance(pro);
                
            try {
                correo = new MimeMessage(session);
                correo.setFrom(new InternetAddress(remitente));
                correo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
                correo.setSubject(titulo);
                correo.setText(contenido,"ISO-8859-1","html");
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
            Send();
               
            return true;
        }
        return false;
    }
    
    
    
    public void Send(){
        try{
            Transport transport = session.getTransport("smtp");
            transport.connect(remitente,clave_remitente);
            transport.sendMessage(correo, correo.getRecipients(Message.RecipientType.TO));
            transport.close();
            //JOptionPane.showMessageDialog(null, "Correo Enviado");
            RecCorreo r = new RecCorreo();
            r.exito("Correo Enviado");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}