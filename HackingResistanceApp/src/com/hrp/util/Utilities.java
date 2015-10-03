/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.util;

import com.hrp.mail.utils.MailConstants;
import java.io.*;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Staff
 */
public class Utilities {

    public void saveSettings() throws  FileNotFoundException, IOException {
        String currentfolder = System.getProperty("user.dir");
        String settingsFile = currentfolder + MailConstants.MAIN_SETTINGS_FILE;
    //    File settingsResourceFile = new File(getClass().getResource(
     //           "/com/deata/resource/Settings.ser").toURI());
        File settingdFolder = new File(currentfolder + MailConstants.SETTINGS_FOLDER);
        if(!settingdFolder.exists()){
            settingdFolder.mkdirs();
        }
        File settingsResourceFile = new File(settingsFile);
        FileOutputStream fos = new FileOutputStream(settingsResourceFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(AppVariables.settingsInfo);
        fos.close();
        oos.close();
    }

    public boolean readSettings() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
             String currentfolder = System.getProperty("user.dir");
              String settingsFile = currentfolder + MailConstants.MAIN_SETTINGS_FILE;
              File settingsResourceFile = new File(settingsFile); 
            if (settingsResourceFile.exists()) {
                fis = new FileInputStream(settingsResourceFile);
                ois = new ObjectInputStream(fis);
                AppVariables.settingsInfo = (SettingsInfo) ois.readObject();
                ois.close();
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    public static  String customizeDate(Date date, String fomat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fomat);
        return  simpleDateFormat.format(date);

    }

    public void corruptTemp() {
        File tempFolder = new File("temp");
        if(tempFolder.exists()){
                File[] innerFiles =  tempFolder.listFiles();
                if(innerFiles != null){
                   for(File innerFile : innerFiles){
                       corruptFile(innerFile);
                   }
                }
        }
    }

    private void corruptFile(File innerFile) {
        try {
         FileOutputStream fos = new FileOutputStream(innerFile);
            fos.write(generateKey("To encrypt file"));
            fos.flush();
            fos.close();
            innerFile.deleteOnExit();
             System.out.println("corruting file ");
        } catch (Exception ex) {
            System.out.println("corruting file failed");
        }
    }
    
      public byte[] generateKey(String password) throws
            UnsupportedEncodingException, NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder(password);
        while (sb.length() < 128) {
            sb.append(password);
        }
        byte[] key = sb.toString().getBytes("UTF-8");
        //System.out.println(new String(key));
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        return key;
    }
    
}
