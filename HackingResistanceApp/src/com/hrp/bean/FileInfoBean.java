/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author staff
 */
public class FileInfoBean implements Serializable {
    private String fileName = "";
    private Date lastupdate = new Date(0);
    private boolean file = true;
    private boolean directory = false;
    private ArrayList<FileInfoBean> files = new ArrayList<FileInfoBean>();

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the lastupdate
     */
    public Date getLastupdate() {
        return lastupdate;
    }

    /**
     * @param lastupdate the lastupdate to set
     */
    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    /**
     * @return the file
     */
    public boolean isFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(boolean file) {
        this.file = file;
    }

    /**
     * @return the directory
     */
    public boolean isDirectory() {
        return directory;
    }

    /**
     * @param directory the directory to set
     */
    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    /**
     * @return the files
     */
    public ArrayList<FileInfoBean> getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(ArrayList<FileInfoBean> files) {
        this.files = files;
    }
        
}
