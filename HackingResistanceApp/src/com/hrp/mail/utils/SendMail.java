/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.mail.utils;

import com.hrp.main.gui.WaitingForSendingGUI;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author Staff
 */
public class SendMail implements Runnable {

    private final String SERVER = "smtp.gmail.com";
    private String errorType = "UNKNOWN";
    public MailBean mailBean = null;
    private Session session = null;
    private String messageContent = "";

    public SendMail() {
    }

    public SendMail(MailBean mailBean, String messageContent) {
        this.mailBean = mailBean;
        this.messageContent = messageContent;
    }

    public void run() {
        boolean send_success = sendMessage();
        if (send_success) {
             JOptionPane.showMessageDialog(null, "Email has been successfully send to users","Sending mail",
                        JOptionPane.WARNING_MESSAGE);
        } else {

            if (errorType.equals("AUTHENTICATION_FAILED")) {
                System.out.println("    Your \n    EMail ID [ " + mailBean.getFromAddress()[0] + " ] "
                        + "\n    Password [ ******* ] " + "\nis incorrect. Correct it and try again.");
                JOptionPane.showMessageDialog(null, "E-mail could not be send to "
                        + "recepients.\n\n\t\tYour Email ID or password is invalid.\n"
                        + "\t\tCorrect it and try again.\n\n", "Authentication Failed",
                        JOptionPane.WARNING_MESSAGE);

            } else if (errorType.equals("SEND_FAILED")) {
                JOptionPane.showMessageDialog(null, "E-mail could not be send to "
                        + "recepients.\n\n\t\tYour Mail To:, CC:,"
                        + "BCC: Email addresses are invalid.\n"
                        + "\t\tCorrect it and try again.\n\n", "Send Failed Error:",
                        JOptionPane.WARNING_MESSAGE);

            } else if (errorType.equals("MESSAGING_EXCEPTION")) {
                System.out.println("    Unknown Messaging Exception. Check with DEBUG = true");
                JOptionPane.showMessageDialog(null, "E-mail could not be send to "
                        + "recepients.\nMessaging Error:\n",
                        "Messging Error:", JOptionPane.WARNING_MESSAGE);
            } else {
            }

            System.out.println("E-Mail could not be sent to recepients.");
        }
        System.out.println("-------------------------------------------------\n");
        WaitingForSendingGUI.sendingComplete = true;
    }

    public void connectToMailServer() {
        Properties properties = System.getProperties();
        properties.put("mail.smtps.host", SERVER);
        properties.put("mail.smtps.auth", "true");
        session = Session.getInstance(properties);
        session.setDebug(true);
    }

    

    public boolean sendMessage() {

        connectToMailServer();
        System.out.println("Connected to server...");

        try {
            
            WaitingForSendingGUI.statusStr = "Sending: " + mailBean.getSubject();
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(mailBean.getFromAddress()[0]);
            if (mailBean.getToAddress() != null) {
                msg.addRecipients(Message.RecipientType.TO, mailBean.getToAddress());
            }

            if (mailBean.getCcAddress() != null) {
                msg.setRecipients(Message.RecipientType.CC, mailBean.getCcAddress());
            }

            if (mailBean.getBccAddress() != null) {
                msg.setRecipients(Message.RecipientType.BCC, mailBean.getBccAddress());
            }

            msg.setContent(messageContent, ApplicationVariables.textPlain);

            msg.setSubject(mailBean.getSubject());

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            // Fill the message
            messageBodyPart.setContent(messageContent,
                    ApplicationVariables.textHtml);
            //messageBodyPart.setText();

            // Create a Multipart
            Multipart multipart = new MimeMultipart();
            // Add part one
            multipart.addBodyPart(messageBodyPart);

            for (int i = 0; i < mailBean.getAttachedFiles().size(); i++) {
                String filepath = (String) mailBean.getAttachedFiles().elementAt(i);
                String filename =
                        filepath.substring(filepath.lastIndexOf('\\'));

                // Part two is attachment //
                messageBodyPart = new MimeBodyPart();
                // Get the attachment
                DataSource source = new FileDataSource(filepath);
                // Set the data handler to the attachment
                messageBodyPart.setDataHandler(new DataHandler(source));
                // Set the filename
                messageBodyPart.setFileName(filename);
                // Add part two
                multipart.addBodyPart(messageBodyPart);
            }

            // Put parts in message
            msg.setContent(multipart);

            msg.saveChanges();

            Transport tr = session.getTransport("smtps");
            tr.connect(SERVER, mailBean.getUserEmail(), mailBean.getPassword());
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();

            return true;

        } catch (AuthenticationFailedException e) {
            errorType = "AUTHENTICATION_FAILED";
            return false;
        } catch (SendFailedException e) {
            errorType = "SEND_FAILED";
            return false;
        } catch (Exception e) {
            errorType = "MESSAGING_EXCEPTION";
             return false;
        }
    }

 
}
