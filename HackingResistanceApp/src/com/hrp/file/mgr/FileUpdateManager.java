/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.file.mgr;

import com.hrp.ecry.AESEncryption;
import com.hrp.util.AppVariables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Administrator
 */
public class FileUpdateManager implements Runnable {

    private File srcDec = null;
    private File destEnc = null;
    private boolean check = true;
    AESEncryption aESEncryption = null;
    private long createdtime = 0;
    private long lastModified = 0;

    public FileUpdateManager(File srcDec, File destEnc) {
        this.srcDec = srcDec;
        this.destEnc = destEnc;
        aESEncryption = new AESEncryption();
        createdtime = srcDec.lastModified();
        lastModified = createdtime;
    }

    public void run() {
        while (check) {
            try {
                Thread.sleep(2000);
                if (srcDec != null && destEnc != null) {
                    if (srcDec.exists()) {
                        lastModified = srcDec.lastModified();
                        if (createdtime != lastModified) {
                            createdtime = lastModified;
                            System.out.println("****** Modified ******");
                            if (srcDec.exists() && destEnc.exists()) {
                                copyFileToOesis(srcDec, destEnc);
                            }
                        }
                    }

                }
            } catch (InterruptedException ex) {
                Logger.getLogger(FileUpdateManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void copyFileToOesis(File srcDec, File destEnc) {
        {
            FileInputStream fis = null;
            try {
                byte[] key = aESEncryption.generateKey(AppVariables.settingsInfo.getIMEI());
                SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, keySpec);
                fis = new FileInputStream(srcDec);
                FileOutputStream fos = new FileOutputStream(destEnc);
                CipherOutputStream cos = new CipherOutputStream(fos, cipher);
                byte[] data = new byte[1024];
                int readData = 0;
                while ((readData = fis.read(data)) != -1) {
                    cos.write(data, 0, readData);

                }
                cos.flush();
                cos.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileUpdateManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileUpdateManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(FileUpdateManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(FileUpdateManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FileUpdateManager.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FileUpdateManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
