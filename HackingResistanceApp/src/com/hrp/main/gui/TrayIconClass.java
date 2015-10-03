/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.main.gui;

import com.hrp.util.AppVariables;
import com.hrp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class TrayIconClass {

    private Main deataMain = null;
    private Utilities utilities = null;

    public TrayIconClass(Main deataMain, Utilities utilities) {
        this.deataMain = deataMain;
        this.utilities = utilities;
        if (!utilities.readSettings()
                || AppVariables.settingsInfo.getEncrHomeDir() == null) {
            SettingsDialog settingsDialog = new SettingsDialog(deataMain, true);
            settingsDialog.setLocationRelativeTo(deataMain);
            settingsDialog.setVisible(true);
        } else {
        }
    }

    public void createTrayIcon() {
        if (SystemTray.isSupported()) {
            try {

                PopupMenu popupMenu = new PopupMenu();
                MenuItem closeMenuItem = new MenuItem("Close");
                closeMenuItem.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        int option = JOptionPane.showConfirmDialog(null,
                                "Do you want to close HRP",
                                "HRP", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {

                            try {
                                utilities.saveSettings();
                                utilities.corruptTemp();
                                System.exit(0);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                });
                popupMenu.add(closeMenuItem);

                Image image = new ImageIcon(getClass().getResource("/com/hrp/icon/display-settings.png")).getImage();
                TrayIcon trayIcon = new TrayIcon(image, "HRP", popupMenu);
                trayIcon.setImageAutoSize(true);
                trayIcon.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        File encrHomeDir = AppVariables.settingsInfo.getEncrHomeDir();
                        if (!encrHomeDir.exists()) {
                            JOptionPane.showMessageDialog(deataMain, "<html>Encrypted "
                                    + "directory is missing <br/>please reconfigure HRP", 
                                    "Missing Directory", JOptionPane.WARNING_MESSAGE);
                            new SettingsDialog(deataMain, true).setVisible(true);
                            return;
                        }
                        AuthenticationDialog authenticationDialog =
                                new AuthenticationDialog(deataMain, true);
                        authenticationDialog.setLocationRelativeTo(deataMain);
                        authenticationDialog.setVisible(true);
                        deataMain.setTitle("HRP: "
                                + AppVariables.settingsInfo.getEncrHomeDir().getName());

                    }
                });
                SystemTray systemTray = SystemTray.getSystemTray();
                systemTray.add(trayIcon);

            } catch (AWTException ex) {
                Logger.getLogger(TrayIconClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
