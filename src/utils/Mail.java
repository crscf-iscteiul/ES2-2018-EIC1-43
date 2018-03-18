package utils;

import gui.GUI;
import main.Main;
import solver.SolverandOptimizer;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    private static Mail instance;
    public static Mail getInstance() {
        if (instance == null)
            instance = new Mail();
        return instance;
    }

    private final String username = "problemsolverandoptimizer@gmail.com";
    private final String password = "jukojuko69";
    private Session session;

    private Mail(){
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public boolean sendMail(String to, String subject, String text){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (AddressException e) {
            GUI.getInstance().show_error("Could not send to this address");
            return false;
        } catch (MessagingException e) {
            GUI.getInstance().show_error("Could not send this mail");
            return false;
        }
        return true;
    }
}
