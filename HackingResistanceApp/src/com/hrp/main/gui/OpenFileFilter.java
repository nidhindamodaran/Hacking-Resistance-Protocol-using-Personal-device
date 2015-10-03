/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.main.gui;

import java.io.File;

/**
 *
 * @author TF-PRG-03
 */
public class OpenFileFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory() || (f.getName().toLowerCase().endsWith(".key"))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return " Key file";
    }
}
