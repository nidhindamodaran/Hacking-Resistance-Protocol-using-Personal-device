/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.main.gui;

import java.awt.Component;
import java.io.File;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author staff
 */
public class FolderTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (value instanceof DefaultMutableTreeNode) {
            Object obj = ((DefaultMutableTreeNode) value).getUserObject();
            if (obj instanceof File) {
                setText(((File)obj).getName());
            }
        }
        return this;
    }
}
