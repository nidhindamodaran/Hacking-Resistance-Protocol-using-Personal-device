/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.mail.utils;

import com.hrp.util.AppVariables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TF-PRG-03
 */
public class MailSettingsManager {

    public boolean saveSettings() {
        boolean saveStatus = false;
        String workingDir = System.getProperty("user.dir");
        File settingsFolder = new File(workingDir + MailConstants.SETTINGS_FOLDER);
        if (!settingsFolder.exists()) {
            settingsFolder.mkdir();
        }
        File settingsFile = new File(workingDir + MailConstants.SETTINGS_FILE);
        if (settingsFile.exists()) {
            settingsFile.delete();
        }
        ObjectOutputStream objectStream = null;
        try {
            settingsFile.createNewFile();
            FileOutputStream fileStream = new FileOutputStream(settingsFile);
            objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(AppVariables.mailSettingsInfo);
            saveStatus = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MailSettingsManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MailSettingsManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                objectStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MailSettingsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return saveStatus;
    }

    public void loadSettings() {
        String workingDir = System.getProperty("user.dir");
        File settingsFile = new File(workingDir + MailConstants.SETTINGS_FILE);
        if (settingsFile.exists()) {
            {
                ObjectInputStream objectInputStream = null;
                try {
                    FileInputStream fis = new FileInputStream(settingsFile);
                    objectInputStream = new ObjectInputStream(fis);
                    AppVariables.mailSettingsInfo = (MailSettings) objectInputStream.readObject();

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MailSettingsManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MailSettingsManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MailSettingsManager.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        objectInputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MailSettingsManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
