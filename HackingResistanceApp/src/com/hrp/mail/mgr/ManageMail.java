/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.mail.mgr;

import com.hrp.main.gui.MailSettingsJDialog;
import com.hrp.mail.beans.EmailBean;
import com.hrp.util.AppVariables;
import com.hrp.mail.utils.MailConstants;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JOptionPane;

/**
 *
 * @author TF-PRG-03
 */
public class ManageMail {

    String workingFolder = "";

    public ManageMail() {
        workingFolder = System.getProperty("user.dir");
    }

    public boolean startMailDownloading() {
        boolean saveStatus = false;
        String email = AppVariables.mailSettingsInfo.getMailID();
        String passWord = AppVariables.mailSettingsInfo.getPassword();
        if (!email.equals("") && !passWord.equals("")) {
            DownloadMail mailDownload = new DownloadMail();
            mailDownload.setDownloading(true);
            new Thread(mailDownload, "Downloading thread").start();
            System.out.println("Downloading mail");
            saveStatus = true;
        } else {
            int option = JOptionPane.showConfirmDialog(null, "You haven't configured mail client  "
                    + "Do you want to configure it now ?", "Cannot connect to mail ", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                MailSettingsJDialog mailSettingsJDialog = new MailSettingsJDialog(null, true);
                mailSettingsJDialog.setLocationRelativeTo(null);
                mailSettingsJDialog.setVisible(true);
//                DownloadMail mailDownload = new DownloadMail();
//                mailDownload.setDownloading(true);
//                new Thread(mailDownload, "Downloading thread").start();
                saveStatus = true;
            } else {
                saveStatus = false;
            }

        }
        return saveStatus;
    }

    public  ArrayList<EmailBean> getAllMailInfo() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<EmailBean> mails = new ArrayList<EmailBean>();
        String workingFolder = System.getProperty("user.dir");
        File inboxFolder = new File(workingFolder + MailConstants.INBOX_FOLDER);
        if (inboxFolder.exists()) {
            File[] msgs = inboxFolder.listFiles();
            for (File msg : msgs) {
                if (msg.isDirectory()) {
                    File[] contents = msg.listFiles();
                    for (File mail : contents) {
                        if (mail.getName().equals(MailConstants.MAIL_SER_FILE)) {
                            FileInputStream fileInputStream = new FileInputStream(mail);
                            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                            EmailBean emailBean = (EmailBean) objectInputStream.readObject();
                            mails.add(emailBean);
                        } // end of mailsettins
                    }// mail folder for
                }//  check is dir
            } // msgs Folders
        }// end of inbox folder check
        return mails;
    }

//    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
//        getAllMailInfo();
//    }

    public boolean checkMail(String emailId, String password ){
        try {
            String host = "pop.gmail.com";
            Properties properties = System.getProperties();
            Session session = Session.getInstance(properties, null);
            Store store = session.getStore("pop3s");
            store.connect(host, emailId, password);
            store.close();
            return true;
        } catch ( NoSuchProviderException ex) {
           // Logger.getLogger(ManageMail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (MessagingException  ex) {
            //Logger.getLogger(ManageMail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        


    }
}
