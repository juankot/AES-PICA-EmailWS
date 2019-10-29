package co.edu.javeriana.pica.kallsonys.business;

import co.edu.javeriana.pica.kallsonys.dto.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@Component
public class EmailBusiness {

    Logger logger = LoggerFactory.getLogger(EmailBusiness.class);

    public void sendEmail(Email email) throws Exception {

        Properties emailProps = new Properties();

        emailProps.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "mail.properties"));

        Properties prop = new Properties();
        prop.put("mail.smtp.host", emailProps.getProperty("mail.smtp.host"));
        prop.put("mail.smtp.port", emailProps.getProperty("mail.smtp.port"));
        prop.put("mail.smtp.auth", emailProps.getProperty("mail.smtp.auth"));
        prop.put("mail.smtp.socketFactory.port", emailProps.getProperty("mail.smtp.socketFactory.port"));
        prop.put("mail.smtp.socketFactory.class", emailProps.getProperty("mail.smtp.socketFactory.class"));

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        emailProps.getProperty("mail.username"),
                        emailProps.getProperty("mail.password"));
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailProps.getProperty("mail.username")));

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
        if (email.getCc() != null && !email.getCc().isEmpty()) {
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(email.getCc()));
        }
        if (email.getCco() != null && !email.getCco().isEmpty()) {
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(email.getCco()));
        }
        message.setSubject(email.getBody());

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(email.getBody(), email.isHTML() ? "text/html" : "text/plain");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
