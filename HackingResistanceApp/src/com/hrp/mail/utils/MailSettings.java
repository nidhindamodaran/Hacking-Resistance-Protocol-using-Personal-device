/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.mail.utils;

import java.io.Serializable;

/**
 *
 * @author TF-PRG-03
 */
public class MailSettings implements Serializable {

    private String mailID = "";
    private String password = "";
    private boolean autoDownload = false;
    

    public boolean isAutoDownload() {
        return autoDownload;
    }

    public void setAutoDownload(boolean autoDownload) {
        this.autoDownload = autoDownload;
    }

    public String getMailID() {
        return mailID;
    }

    public void setMailID(String mailID) {
        this.mailID = mailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
