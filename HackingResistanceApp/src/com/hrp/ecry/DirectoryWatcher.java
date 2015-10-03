/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.ecry;

import com.hrp.bean.FileInfoBean;
import com.hrp.util.AppVariables;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Staff
 */
public class DirectoryWatcher implements Runnable {

    AESEncryption encryption = null;
    public static boolean encryptionworking = false;
    public DirectoryWatcher() {
        encryption = new AESEncryption();
    }

    public void run() {
        while (true) {
            if (AppVariables.settingsInfo.getEncrHomeDir() != null
                    && !AppVariables.settingsInfo.getIMEI().equals("")) {
                iterateFolder(AppVariables.settingsInfo.getEncrHomeDir());
            }
        }
    }

    private void iterateFolder(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file != null) {
                    if (!AppVariables.settingsInfo.getFileInfos().containsKey(
                            file.getAbsolutePath()) && file.isFile()) {
                        try {
                            encryptionworking = true;
                            String fileName = file.getAbsolutePath();
                            File ecrFile = encryption.encryptFile(file, file.getParentFile(),
                                    AppVariables.settingsInfo.getIMEI(), true);
                            FileInfoBean fileInfoBean = new FileInfoBean();
                            fileInfoBean.setFileName(fileName);
                            AppVariables.settingsInfo.getFileInfos().put(fileName, fileInfoBean);
                            ecrFile.renameTo(new File(fileName));
                            encryptionworking  = false;
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(DirectoryWatcher.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(DirectoryWatcher.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(DirectoryWatcher.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoSuchPaddingException ex) {
                            Logger.getLogger(DirectoryWatcher.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvalidKeyException ex) {
                            Logger.getLogger(DirectoryWatcher.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalBlockSizeException ex) {
                            Logger.getLogger(DirectoryWatcher.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (BadPaddingException ex) {
                            Logger.getLogger(DirectoryWatcher.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (file.isDirectory()) {
                        iterateFolder(file);
                    }
                }
            }
        }
    }
}
