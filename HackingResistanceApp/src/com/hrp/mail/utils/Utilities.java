/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.mail.utils;

import java.io.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Staff
 */
public class Utilities {

    Vector incomingMailMetaVector = new Vector();
    MailBean mailBean = null;
 
 
   

    public Vector getMailMetaData() {
        Vector meta = new Vector();
        FileInputStream fin = null;
        ObjectInputStream objectInputStream = null;
        try {
            File configFile = new File(System.getProperty("user.dir")
                    + File.separator + "Mail" + File.separator + "MailMetaData.ser");
            if (configFile.exists()) {
                fin = new FileInputStream(configFile);
                objectInputStream = new ObjectInputStream(fin);
                meta = (Vector) objectInputStream.readObject();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta;
    }

    public void saveMailMetaData(Vector metaData) {
        try {

            FileOutputStream fout = null;
            ObjectOutputStream objectOutputStream = null;
            File configFile = new File(System.getProperty("user.dir")
                    + File.separator + "Mail");
            if (!configFile.exists()) {
                configFile.mkdirs();
            }
            fout = new FileOutputStream(configFile.getAbsolutePath()
                    + File.separator + "MailMetaData.ser");
            objectOutputStream = new ObjectOutputStream(fout);
            objectOutputStream.writeObject(metaData);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] readMail(File messageFileDir, String fileName) {
        byte[] buffer = null;
        try {
            FileInputStream fin = null;
            if (messageFileDir != null && messageFileDir.exists()) {
                System.out.println("Absolute path : " + messageFileDir.getAbsolutePath());
                fin = new FileInputStream(messageFileDir + File.separator + fileName);
                buffer = new byte[fin.available()];
                fin.read(buffer);
            }
        } catch (FileNotFoundException ex) {
            buffer = "<HTML><BODY><h2>Content of Email cannot be read<h2></BODY></HTML>".getBytes();
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buffer;
    }

    public Vector getMails(String label) {
        incomingMailMetaVector = new Utilities().getMailMetaData();
        Vector data = new Vector();
        for (int i = 0; i < incomingMailMetaVector.size(); i++) {
            mailBean = (MailBean) incomingMailMetaVector.elementAt(i);
            if (mailBean.getLabel().equalsIgnoreCase(label)) {
                Vector rowData = new Vector();
                rowData.addElement(((InternetAddress) mailBean.getFromAddress()[0]));
                rowData.addElement(mailBean.getSubject());
                rowData.addElement(mailBean.getSentOrSavedDate());
                rowData.addElement(mailBean.getReceivedDate());
                data.add(rowData);
            }
        }
        return data;
    }

    public Address[] getStringByAddress(String[] fromAddress) throws AddressException {
        Address[] addresses = new Address[fromAddress.length];
        for (int i = 0; i < fromAddress.length; i++) {
            if (!fromAddress[i].equals("")) {
                addresses[i] = new InternetAddress(fromAddress[i]);
            }
        }
        return addresses;
    }

    public String[] getAddressByStringArray(Address[] toAddress) {
        String[] toAddressStrings = null;
        if (toAddress.length != 0) {
            //System.out.println("toAddress.length : " + toAddress.length);
            for (int i = 0; i < toAddress.length; i++) {
                System.out.println("toAddress[i].getClass().getName()"
                        + toAddress[i].getClass().getName());
                toAddressStrings[i] = ((InternetAddress) (toAddress[i])).getAddress();
            }
        }
        return toAddressStrings;
    }

    public String getAddressArrayByString(Address[] toAddress) {
        String toAddresses = "";
        if (toAddress.length != 0) {
            for (int i = 0; i < toAddress.length; i++) {
                if (i < toAddress.length) {
                    toAddresses += ((InternetAddress) (toAddress[i])).getAddress();
                }
            }
        }
        return toAddresses;
    }

    public StringBuffer getStringArrayByStringBuffer(Object[] toAddress) {
        StringBuffer toAddresses = new StringBuffer("");
        int stringArrLen = toAddress.length;
        if (stringArrLen != 0) {
            for (int i = 0; i < stringArrLen; i++) {
                if (stringArrLen == 1) {
                    toAddresses.append((String) toAddress[i]);
                } else {
                    toAddresses.append((String) toAddress[i] + ",");
                }
            }
            int len = toAddresses.length();
            if (toAddresses.toString().endsWith(",")) {
                toAddresses.deleteCharAt(len - 1);
            }
        }
        return toAddresses;
    }

  
  

   
   
}
