/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.main.gui;

import java.awt.Component;
import java.io.File;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author staff
 */
public class FileTableCellRenderer extends DefaultTableCellRenderer {

    FileSystemView fileSystemView = null;

    public FileTableCellRenderer() {
        fileSystemView = FileSystemView.getFileSystemView();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof File) {
            if (((File) value).exists()) {
                this.setIcon(fileSystemView.getSystemIcon((File) value));
                this.setText(fileSystemView.getSystemDisplayName((File) value));
                return this;
            }
        }
        return this;
    }
}
