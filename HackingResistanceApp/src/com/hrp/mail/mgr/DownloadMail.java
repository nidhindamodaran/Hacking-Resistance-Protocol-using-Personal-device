/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.mail.mgr;

import com.hrp.ecry.AESEncryption;
import com.hrp.mail.beans.EmailBean;
import com.hrp.util.AppVariables;
import com.hrp.mail.utils.MailConstants;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.*;
import javax.swing.JOptionPane;

/**
 *
 * @author TF-PRG-03
 */
public class DownloadMail implements Runnable {

    private String host = "";
    private String username = "";
    private String password = "";
    private boolean downloading = false;
    private long interval = 0;

    public DownloadMail() {
        host = "pop.gmail.com";
//        username = "javavfs1@gmail.com";
//        password = "netbeans";
        downloading = true;
        username = AppVariables.mailSettingsInfo.getMailID();
        password = AppVariables.mailSettingsInfo.getPassword();

    }

    /**********************************************************
     *   The code connects to mail server and grabs all mails *
     **********************************************************/
    public void getMails() {
        try {

            Session session = Session.getInstance(new Properties(), null);
            Store store = session.getStore("pop3s");

            store.connect(host, username, password);
            AppVariables.falseAttemptToDownload = 0;
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            Message message[] = folder.getMessages();
            System.out.println("Message counts : " + message.length);
            // To parse each message

            for (int i = 0; i < message.length; i++) {
                // created the instance of mail bean
                AppVariables.downloading = true;
                EmailBean emailBean = new EmailBean();
                System.out.println(i + ": " + message[i].getFrom()[0]
                        + "\t" + message[i].getSubject());
                // to set From and subject
                emailBean.setFrom(message[i].getFrom()[0].toString());
                emailBean.setSubject(message[i].getSubject());
                emailBean.setMessageDate(new Date());
                emailBean.setSendDate(message[i].getSentDate());
                emailBean.setReceivedMailID(AppVariables.mailSettingsInfo.getMailID());
                emailBean.setMessageNumber("" + new Date().getTime());
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DownloadMail.class.getName()).log(Level.SEVERE, null, ex);
                }
                // To Create a folder for message
                String folderLocation = createFolderForMail(emailBean);
                emailBean.setFolderLocation(folderLocation);
                Object content = message[i].getContent();
                if (content instanceof Multipart) {
                    handleMultipart((Multipart) content, emailBean);
                } else {
                    handlePart(message[i], emailBean, null);
                }
               // System.out.println("From : " + emailBean.getFrom());
               // System.out.println("subject : " + emailBean.getBody());
               // System.out.println("body : " + emailBean.getBody());
                ArrayList<File> files = emailBean.getFileArray();
                if (emailBean.getFileArray() != null) {
                    for (int count = 0; count < files.size(); count++) {
                        System.out.println("File name " + (count + 1) + " : "
                                + files.get(count));
                    }
                }
                saveMailDetails(emailBean);
            }
            AppVariables.downloading = false;
            /***********************************************************
             *  The Following code is so important to close connection *
             ***********************************************************/
            folder.close(false);
            store.close();
        } catch (MessagingException me) {
            AppVariables.falseAttemptToDownload++;
            me.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*********************************************************************************
     *   The code Handles multipart mails     calls handle part to de compose mails  *
     *********************************************************************************/
    public void handleMultipart(Multipart multipart, EmailBean emailBean)
            throws MessagingException, IOException {
        ArrayList<File> atachFiles = new ArrayList<File>();
        for (int i = 0; i < multipart.getCount(); i++) {
            handlePart(multipart.getBodyPart(i), emailBean, atachFiles);
        }
        emailBean.setFileArray(atachFiles);

    }

    /*********************************************************
     *   Split parts are managed  calls saveFile() to save   *
     *********************************************************/
    public void handlePart(Part part, EmailBean emailBean,
            ArrayList<File> atachFiles) throws MessagingException,
            IOException {

        String dposition = part.getDisposition();
        String cType = part.getContentType();
        if (dposition == null) {

            System.out.println("ContentType: " + cType);
            if (cType.toLowerCase().indexOf("text/plain") != -1) {
             //   System.out.println("The valid Body : \n\n\n \n\n\n");

                //  part.writeTo(buBos);

                InputStream inputStream = part.getInputStream();
                int arraySize = inputStream.available();
                byte[] data = new byte[arraySize];
                inputStream.read(data);
                inputStream.close();
                String body = new String(data);
           //     System.out.println(" body From attachment : " + body);
                emailBean.setBody(body);

            } else {
                System.out.println("Other body: " + cType);
                part.writeTo(System.out);
                // part.writeTo();
                InputStream inputStream = part.getInputStream();
                int arraySize = inputStream.available();
                byte[] data = new byte[arraySize];
                inputStream.read(data);
                inputStream.close();
                String body = new String(data);
             //   System.out.println(" body From attachment : " + body);
                emailBean.setBody(body);
            }
        } else if (dposition.equalsIgnoreCase(Part.ATTACHMENT)) {
            //System.out.println("Attachment: " + part.getFileName() + " : " + cType);
            saveFile(part.getFileName(), part.getInputStream(), atachFiles, emailBean);
        } else if (dposition.equalsIgnoreCase(Part.INLINE)) {
            //System.out.println("Inline: " + part.getFileName() + " : " + cType);
            saveFile(part.getFileName(), part.getInputStream(), atachFiles, emailBean);
        } else {
           // System.out.println("Other: " + dposition);
        }
    }

    /************************************************************
     *   mails attachment are downloaded and saved              *
     ************************************************************/
    public void saveFile(String filename, InputStream input,
            ArrayList<File> atachFiles, EmailBean emailBean) throws IOException {

        if (filename == null) {
            filename = File.createTempFile("MailAttacheFile", ".out").getName();
        }
        System.out.println("downloading attachment...");

        //  File file = new File(filename);

        if (atachFiles != null) {
            int count = 0;
            for (int i = 0; i < atachFiles.size(); i++) {

                if (filename.equals(atachFiles.get(i).getName())) {
                    count++;
                    filename = "" + count + filename;
                }
            }
        }

        File toWrite = new File(emailBean.getFolderLocation() + File.separator + filename);
        atachFiles.add(toWrite);
        FileOutputStream fos = new FileOutputStream(toWrite);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        BufferedInputStream bis = new BufferedInputStream(input);
        int fByte;
        while ((fByte = bis.read()) != -1) {
            bos.write(fByte);
        }
        bos.flush();
        bos.close();
        bis.close();
       // System.out.println("done attachment...");
       // System.out.println("To do encryption ");
        AESEncryption aESEncryption = new AESEncryption();
        try {
            File encrFile = aESEncryption.encryptFile(toWrite, toWrite.getParentFile(),
                    AppVariables.settingsInfo.getIMEI(), true);
            encrFile.renameTo(toWrite);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DownloadMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DownloadMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(DownloadMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(DownloadMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(DownloadMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(DownloadMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        try {
            new Thread(new DownloadMail()).start();
        } catch (Exception ex) {
            Logger.getLogger(DownloadMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // This code creates the folder for a message

    private String createFolderForMail(EmailBean emailBean) {
        String userDir = System.getProperty("user.dir");
        String mailId = emailBean.getMessageNumber();
        String location = userDir + MailConstants.INBOX_FOLDER + mailId;
       // System.out.println(" location : " + location);
        File file = new File(location);
        if (!file.exists()) {
            file.mkdirs();
        }
        return location;
    }
    // This method saves the file details as serilised file

    private void saveMailDetails(EmailBean emailBean) {
        String location = emailBean.getFolderLocation();

        if (new File(location).exists() && (new File(location).canWrite())) {
            ObjectOutputStream objectOutputStream = null;
            try {
                File serFile = new File(location + File.separator + MailConstants.MAIL_SER_FILE);
                FileOutputStream fileOutputStream = new FileOutputStream(serFile);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(emailBean);
                objectOutputStream.flush();

            } catch (IOException ex) {
                Logger.getLogger(DownloadMail.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    objectOutputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(DownloadMail.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void run() {
        while (downloading) {

            try {
                if (!AppVariables.mailSettingsInfo.getMailID().equals("")
                        && !AppVariables.mailSettingsInfo.getPassword().equals("")
                        && !(AppVariables.falseAttemptToDownload == AppVariables.attempt_limit)) {
                //    System.out.println("Download started");
                    AppVariables.mailDownloading = true;
                    getMails();
                } else {
                   JOptionPane.showMessageDialog(null, "Fails to download mail ,"
                           + " \nplease verify internet connection", "Error", JOptionPane.ERROR_MESSAGE);
                   AppVariables.falseAttemptToDownload = 0;
                   AppVariables.mailDownloading = false;
                   break;
                }
                Thread.sleep(interval);
            } catch (InterruptedException ex) {
                Logger.getLogger(DownloadMail.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isDownloading() {
        return downloading;
    }

    public void setDownloading(boolean downloading) {
        this.downloading = downloading;
    }
}
