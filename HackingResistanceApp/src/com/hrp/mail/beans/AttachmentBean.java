/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hrp.mail.beans;

/**
 *
 * @author TF-PRG-03
 */
public class AttachmentBean {
    private String fileName = "";
    private String fileLocation = "";
    private byte[] fileDataArray = null;

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileData() {
        return fileDataArray;
    }

    public void setFileData(byte[] fileData) {
        this.fileDataArray = fileData;
    }



}
