/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.mail.beans;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author TF-PRG-03
 */
public class EmailBean implements Serializable {

    private String from = "";
    private String subject = "";
    private String body = "";
    private ArrayList<File> fileArray = null;
    private Date messageDate = null;
    private String messageNumber = "";
    private String folderLocation = "";
    private Date sendDate = new Date();
    private String ReceivedMailID = "";

    public String getBody() {
        return getString(body);
    }

    public void setBody(String body) {
        this.body = getHexString(body);
    }

    public String getFrom() {
        return getString(from);

    }

    public void setFrom(String fron) {
        this.from = getHexString(fron);
    }

    public String getSubject() {
        return getString(subject);

    }

    public void setSubject(String subject) {
        this.subject = getHexString(subject);
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public ArrayList<File> getFileArray() {
        return fileArray;
    }

    public void setFileArray(ArrayList<File> fileArray) {
        this.fileArray = fileArray;
    }

    public String getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getFolderLocation() {
        return folderLocation;
    }

    public void setFolderLocation(String folderLocation) {
        this.folderLocation = folderLocation;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getReceivedMailID() {
        return getString(ReceivedMailID);

    }

    public void setReceivedMailID(String ReceivedMailID) {
        this.ReceivedMailID = getHexString(ReceivedMailID);
    }

    public String getString(String hexString) {
        String data = hexString;
        int k = 0;
        byte[] results = new byte[data.length() / 2];
        for (int i = 0; i < data.length();) {
            results[k] = (byte) (Character.digit(data.charAt(i++), 16) << 4);
            results[k] += (byte) (Character.digit(data.charAt(i++), 16));
            k++;
        }
        return new String(results);
    }

    public String getHexString(String hexString) {
        byte[] bytes = hexString.getBytes();
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);

    }
}
