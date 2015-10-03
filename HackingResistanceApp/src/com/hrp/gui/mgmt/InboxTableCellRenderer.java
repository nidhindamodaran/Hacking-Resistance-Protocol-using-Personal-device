/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.gui.mgmt;

import com.hrp.mail.beans.EmailBean;
import com.hrp.util.AppVariables;
import com.hrp.util.Utilities;
import java.awt.Component;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author TF-PRG-03
 */
public class InboxTableCellRenderer extends DefaultTableCellRenderer {

    private int columnCount = 0;

    public InboxTableCellRenderer(int columnCount) {
        this.columnCount = columnCount;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        EmailBean emailBean = null;
        if (value instanceof EmailBean) {
            emailBean = (EmailBean) value;
        }
        if (emailBean != null) {
            if (columnCount == 1) { // for setting From
                this.setText(emailBean.getFrom());
            } else if (columnCount == 2) {// for Setting Received mail id
                this.setText(emailBean.getReceivedMailID());
            } else if (columnCount == 3) { // for Setting date
               this.setText(Utilities.customizeDate(emailBean.getSendDate(), "dd-MM-yyyy"));
            }
        }
        return this;
    }
}
