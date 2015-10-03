/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.gui.mgmt;

import java.awt.Component;
import java.io.File;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author TF-PRG-03
 */
public class AttachmentTableCellRenderer extends DefaultTableCellRenderer {

    private int columnCount = 0;
    private FileSystemView fileSystemView = null;

    public AttachmentTableCellRenderer(int count) {
        fileSystemView = FileSystemView.getFileSystemView();
        columnCount = count;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        File file = null;
        if (value instanceof File) {
            // file = ;
            if (column == 1) {
                this.setIcon(fileSystemView.getSystemIcon((File) value));
                this.setText(fileSystemView.getSystemDisplayName((File) value));
                
            } 
        }
        return this;

    }
}
