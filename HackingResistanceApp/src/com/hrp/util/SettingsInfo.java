/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.util;

import com.hrp.bean.FileInfoBean;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author Staff
 */
public class SettingsInfo implements Serializable {

    private File encrHomeDir = null;
    private String imei = "";
    private String username = "";
    private LinkedHashMap<String, FileInfoBean> fileInfos =
            new LinkedHashMap<String, FileInfoBean>();

    public String getIMEI() {
        return getString(imei);
    }

    public void setIMEI(String imei) {
        this.imei = getHexString(imei);
    }

    public String getUsername() {
        return getString(username);
    }

    public void setUsername(String username) {
        this.username = getHexString(username);
    }

    /**
     * @return the encrHomeDir
     */
    public File getEncrHomeDir() {
        return encrHomeDir;
    }

    /**
     * @param encrHomeDir the encrHomeDir to set
     */
    public void setEncrHomeDir(File encrHomeDir) {
        this.encrHomeDir = encrHomeDir;
    }

    /**
     * @return the fileInfos
     */
    public LinkedHashMap<String, FileInfoBean> getFileInfos() {
        return fileInfos;
    }

    /**
     * @param fileInfos the fileInfos to set
     */
    public void setFileInfos(LinkedHashMap<String, FileInfoBean> fileInfos) {
        this.fileInfos = fileInfos;
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
