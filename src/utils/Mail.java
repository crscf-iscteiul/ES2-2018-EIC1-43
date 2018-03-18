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
            message.setContent(text, "text/html; charset=utf-8");

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

    public boolean sendIssuetoAdmin(String from, String subject, String text){
        text = text.replaceAll("\n","<br>");

        String html ="<body>"
                    +"<title>Problem Solver and Optimizer</title>"
                    +"<p>This user <b>"+from+"</b> said:</p>"
                    +"<p><i>"+text+"</i></p>"
                    +"<p>Please respond to user as soon as possible</p>"
                    +"</body>";

        //TODO Load xml config and send to those admins
        //TODO Add xml mail counter i guess or cagar
        return sendMail("afssa11111@iscte-iul.pt", "User Problem - "+subject, html);
    }

    public boolean sendConfirmationtoUser(String user, String user_subject,String text){
        String subject = "Problem Solver and Optimizer - Mail sent confirmation";
        text = text.replaceAll("\n","<br>");

        String html ="<body>"
                    +"<title>Problem Solver and Optimizer</title>"
                    +"<p>Your message:</p>"
                    +"<p><i><b>"+user_subject+"</b><br>"+text+"</i></p>"
                    +"<p>We have received your mail and are going to respond as fast as possible<br>Thank you for your understanding</p>"
                    +"</body>";

        return sendMail(user, subject, html);
    }
}
