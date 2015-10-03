/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.utils;

import com.hrp.ws.Invalidator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Administrator
 */
public class SendMail  implements Runnable{

    private String toAddress = "";
    private String content = "";
    private String subject = "";
    private String username = "";
    
    public SendMail(String username, String toAddress, String content, String subject) {
        this.toAddress = toAddress;
        this.content = content;
        this.subject = subject;
        this.username = username;
    }

    public boolean sendMail()throws MessagingException {
        System.out.println("Sending Mail: " + toAddress + " content :" + content);
        Properties properties = System.getProperties();
        properties.put("mail.smtps.host", AppConstants.MAIL_SERVER);
        properties.put("mail.smtps.auth", "true");
        Session session = Session.getInstance(properties);

        session.setDebug(true);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(AppConstants.MAIL_SERVER_USERNAME));
        if (toAddress != null) {
            msg.addRecipients(Message.RecipientType.TO, toAddress);
        }

        msg.setSubject(subject);
        msg.setContent(content, "text/plain");
        Transport tr = session.getTransport("smtps");
        tr.connect(AppConstants.MAIL_SERVER,
                AppConstants.MAIL_SERVER_USERNAME,
                AppConstants.MAIL_SERVER_PASSWORD);
        tr.sendMessage(msg, msg.getAllRecipients());
        tr.close();
        System.out.println("mail Send Succeccfully");
        return true;
    }

    @Override
    public void run() {
        try {
            sendMail();
           new Thread(new Invalidator(username)).start();
        } catch (MessagingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
