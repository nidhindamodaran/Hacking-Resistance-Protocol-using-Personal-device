/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.auth;

import com.hrp.util.AppVariables;
import com.hrp.util.Utilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author TF-PRG-03
 */
public class CheckKeyFile implements Runnable {

    public void run() {
        while (true) {
            try {
                Thread.sleep(20000);
                File file = AppVariables.keyFile;
                if (file != null) {
                    if (!file.exists()) {
                        JOptionPane.showMessageDialog(null, "System could not find key file ",
                                "Error", JOptionPane.OK_OPTION);
                        new Utilities().saveSettings();
                        new Utilities().corruptTemp();
                        System.exit(0);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CheckKeyFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CheckKeyFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(CheckKeyFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
