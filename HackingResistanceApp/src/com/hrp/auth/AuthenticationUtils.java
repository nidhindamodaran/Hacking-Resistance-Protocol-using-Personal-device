/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.auth;

import com.hrp.main.gui.SettingsDialog;
import com.hrp.util.AppVariables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author TF-PRG-03
 */
public class AuthenticationUtils {

    public void generateKeyFile(String password, File keyLocation) {
        try {

            StringBuffer stringBuffer = new StringBuffer(password);
            while (stringBuffer.length() < 128) {
                stringBuffer.append(password);
            }
            byte[] data = stringBuffer.toString().getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = sha.digest(data);
            String fileName = keyLocation.getAbsolutePath() + File.separator + "password.key";
            File file = new File(fileName);
            FileOutputStream fis = null;
            if (!file.exists()) {
                try {
                    fis = new FileOutputStream(file);
                    fis.write(key);
                    fis.flush();
                    fis.close();
                    AppVariables.keyFile = file;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SettingsDialog.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error in  creating key file"
                            + " of file name password.key", "error in creating file", JOptionPane.OK_OPTION);
                } catch (IOException ex) {
                    Logger.getLogger(SettingsDialog.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (NoSuchAlgorithmException ex) {
            //  KEY  SHA-1
            Logger.getLogger(SettingsDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            // characterset : UTF-8
            Logger.getLogger(SettingsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }// end of method generateKeyFile

    public boolean authenticate(File passwordFile, String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            FileNotFoundException, IOException {

        FileInputStream fis = null;

        StringBuffer sb = new StringBuffer(password);
        while (sb.length() < 128) {
            sb.append(password);
        }
        byte[] data = sb.toString().getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        byte[] newKey = sha.digest(data);
        fis = new FileInputStream(passwordFile);
        byte[] keyFromFile = new byte[fis.available()];
        fis.read(keyFromFile);
        fis.close();
        boolean flag = false;
        if (newKey.length == keyFromFile.length) {
            for (int i = 0; i < keyFromFile.length; i++) {
                if (keyFromFile[i] == newKey[i]) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        return flag;

    }
}
