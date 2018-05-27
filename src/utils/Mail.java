package utils;

import gui.GUI;
import solverandoptimizer.SolverandOptimizer;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class Mail.
 */
public class Mail {

    /** The instance. */
    private static Mail instance;
    
    /**
     * Gets the single instance of Mail.
     *
     * @return single instance of Mail
     */
    public static Mail getInstance() {
        if (instance == null)
            instance = new Mail();
        return instance;
    }

    /** The username. */
    private final String username = "problemsolverandoptimizer@gmail.com";
    
    /** The password. */
    private final String password = "jukojuko69";
    
    /** The session. */
    private Session session;

    /**
     * Instantiates a new mail.
     */
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

    /**
     * Send mail.
     *
     * @param to the to
     * @param subject the subject
     * @param text the text
     * @return true, if successful
     */
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

    /**
     * Send problem to user.
     *
     * @param to the to
     * @param problem the problem
     * @return true, if successful
     */
    public boolean sendProblemToUser(String to, Problem problem) {
        HashMap<String, String> adminInfo = SolverandOptimizer.getInstance().load_config();
        String text = "Muito obrigado por utilizar esta plataforma de optimização. Será informado por email quando o processo estiver terminado.";
        try{
            Message message = new MimeMessage(session);
            MimeBodyPart msg = new MimeBodyPart();

            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            for(int i = 0; i < adminInfo.size(); i++) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(adminInfo.values().toArray()[i].toString()));
            }
            message.setSubject("Optimização em Curso: " + problem.getName() + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            msg.setContent(text, "text/html; charset=utf-8");

            MimeBodyPart attch = new MimeBodyPart();
            XML.saveXMLProblem(System.getProperty("user.dir") + "\\" + problem.getName()+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).replace(":","-")+".xml", problem);
            File att = new File(System.getProperty("user.dir") + "\\" + problem.getName()+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).replace(":","-")+".xml");
            attch.attachFile(att);

            Multipart content = new MimeMultipart();
            content.addBodyPart(msg);
            content.addBodyPart(attch);
            message.setContent(content);

            Transport.send(message);
            att.delete();

        } catch (AddressException e) {
            GUI.getInstance().show_error("Could not send to this address");
            return false;
        } catch (MessagingException e) {
            GUI.getInstance().show_error("Could not send this mail");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Send issueto admin.
     *
     * @param from the from
     * @param subject the subject
     * @param text the text
     * @return true, if successful
     */
    public boolean sendIssuetoAdmin(String from, String subject, String text){
        text = text.replaceAll("\n","<br>");

        String html ="<body>"
                    +"<p><font size=\"6\">Problem Solver and Optimizer</font></p>"
                    +"<p>This user <b>"+from+"</b> said:</p>"
                    +"<p><i>"+text+"</i></p>"
                    +"<p>Please respond to user as soon as possible</p>"
                    +"</body>";

        HashMap<String, String> adminInfo = SolverandOptimizer.getInstance().load_config();
        for(int i = 0; i < adminInfo.size()-1; i++) {
            sendMail(adminInfo.values().toArray()[i].toString(), "User Problem - "+subject, html);
        }

        return sendMail("afssa11111@iscte-iul.pt", "User Problem - "+subject, html);
    }

    /**
     * Send confirmationto user.
     *
     * @param user the user
     * @param user_subject the user subject
     * @param text the text
     * @return true, if successful
     */
    public boolean sendConfirmationtoUser(String user, String user_subject,String text){
        String subject = "Problem Solver and Optimizer - Mail sent confirmation";
        text = text.replaceAll("\n","<br>");

        String html ="<body>"
                    +"<p><font size=\"6\">Problem Solver and Optimizer</font></p>"
                    +"<p>Your message:</p>"
                    +"<p><i><b>"+user_subject+"</b><br>"+text+"</i></p>"
                    +"<p>We have received your mail and are going to respond as fast as possible<br>Thank you for your understanding</p>"
                    +"</body>";

        return sendMail(user, subject, html);
    }
}
