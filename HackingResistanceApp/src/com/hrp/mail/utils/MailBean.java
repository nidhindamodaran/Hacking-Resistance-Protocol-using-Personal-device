/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.mail.utils;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Vector;
import javax.mail.Address;

/**
 *
 * @author Staff
 */
public class MailBean implements Serializable {

    private String folderName = "";
    private Address[] fromAddress = null;
    private Address[] toAddress = null;
    private Address[] bccAddress = null;
    private Address[] ccAddress = null;
    private String subject = "";
    private String content = "";
    private Date sentOrSavedDate = null;
    private Date receivedDate = null;
    private int messageID = 0;
    private String domainName = "";
    Vector attachedFiles = new Vector();
    private String userEmail = "";
    private String password = "";
    private String label = "";
    private File messageFileDir = null;
    private String readStatus = "";

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }
    

    public File getMessageFileDir() {
        return messageFileDir;
    }

    public void setMessageFileDir(File messageFileDir) {
        this.messageFileDir = messageFileDir;
    }


    public Vector getAttachedFiles() {
        return attachedFiles;
    }

    public void setAttachedFiles(Vector attachedFiles) {
        this.attachedFiles = attachedFiles;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String status) {
        this.label = status;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Address[] getBccAddress() {
        return bccAddress;
    }

    public void setBccAddress(Address[] bccAddress) {
        this.bccAddress = bccAddress;
    }

    public Address[] getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(Address[] ccAddress) {
        this.ccAddress = ccAddress;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Address[] getToAddress() {
        return toAddress;
    }

    public void setToAddress(Address[] toAddress) {
        this.toAddress = toAddress;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Address[] getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(Address[] fromAddress) {
        this.fromAddress = fromAddress;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getSentOrSavedDate() {
        return sentOrSavedDate;
    }

    public void setSentOrSavedDate(Date sentOrSavedDate) {
        this.sentOrSavedDate = sentOrSavedDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
