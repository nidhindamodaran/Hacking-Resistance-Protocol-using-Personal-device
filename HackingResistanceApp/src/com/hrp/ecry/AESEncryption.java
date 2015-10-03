/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.ecry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author staff
 */
public class AESEncryption {

    public File encryptFile(File sourceFile, File destFile, String password, boolean delete)
            throws FileNotFoundException, IOException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        FileInputStream fis = null;
        //generate key
        byte[] encryptedKey = generateKey(password);
        SecretKeySpec keySpec = new SecretKeySpec(encryptedKey, "AES");
        // encrypt file
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        fis = new FileInputStream(sourceFile);
        //create a folder in destination with the src file name
        File ecrFile = null;
        if (delete) {
            ecrFile = new File(destFile + File.separator
                    + sourceFile.getName() + ".encrypted");
        } else {
            ecrFile = new File(destFile + File.separator
                    + sourceFile.getName());
        }
        FileOutputStream fos = new FileOutputStream(ecrFile);
        byte[] data = new byte[1024];
        int count = 0;
        CipherOutputStream cos = new CipherOutputStream(fos, cipher);
        while ((count = fis.read(data)) != -1) {
            cos.write(data, 0, count);
            cos.flush();
        }
        fis.close();
        cos.close();
        fos.close();
        if (delete) {
            System.out.println(sourceFile.delete());
        }
        return ecrFile;
    }

    public File decryptFile(File sourceFile, File destFile, String password)
            throws FileNotFoundException, IOException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        //generate key from password
        byte[] encryptedKey = generateKey(password);
        SecretKeySpec keySpec = new SecretKeySpec(encryptedKey, "AES");
        //decrypt file
        Cipher deCipher = Cipher.getInstance("AES");
        deCipher.init(Cipher.DECRYPT_MODE, keySpec);
        FileInputStream inputStream = new FileInputStream(sourceFile);
        byte[] enData = new byte[1024];
        if (!destFile.exists()) {
            destFile.mkdirs();
        }
        File decrFile = new File(destFile, sourceFile.getName());
        FileOutputStream outputStream = new FileOutputStream(decrFile);
        int count = 0;
        CipherInputStream cis = new CipherInputStream(inputStream, deCipher);
        while ((count = cis.read(enData)) != -1) {
            outputStream.write(enData, 0, count);
            outputStream.flush();
        }
        inputStream.close();
        cis.close();
        outputStream.close();
        return decrFile;
    }

    public byte[] generateKey(String password) throws
            UnsupportedEncodingException, NoSuchAlgorithmException {
        StringBuffer sb = new StringBuffer(password);
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

    File decryptFile(File file, String password) {
        File dec = new File(file.getName());
        FileInputStream inputStream = null;
        try {
            byte[] encryptedKey = generateKey(password);
            SecretKeySpec keySpec = new SecretKeySpec(encryptedKey, "AES");
            Cipher deCipher = Cipher.getInstance("AES");
            deCipher.init(Cipher.DECRYPT_MODE, keySpec);
            inputStream = new FileInputStream(file);
            byte[] enData = new byte[1024];
            FileOutputStream outputStream = new FileOutputStream(dec);
            int count = 0;
            CipherInputStream cis = new CipherInputStream(inputStream, deCipher);
            while ((count = cis.read(enData)) != -1) {
                outputStream.write(enData, 0, count);
                outputStream.flush();
            }
            inputStream.close();
            cis.close();
            outputStream.close();
            file.delete();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dec;
    }
}
