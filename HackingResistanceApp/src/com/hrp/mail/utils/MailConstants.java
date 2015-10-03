/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.mail.utils;

import java.io.File;

/**
 *
 * @author TF-PRG-03
 */
public interface MailConstants {

    String INBOX_FOLDER = File.separator + "inbox" + File.separator;
    String SETTINGS_FOLDER = File.separator + "settings" + File.separator;
    String SETTINGS_FILE = File.separator + "settings"
            + File.separator + "settings.ser";
    String MAIL_SER_FILE = "mail.ser";

    String MAIN_SETTINGS_FILE = File.separator + "settings"
            + File.separator + "Main_settings.ser";
    String AUTH_SETTINGS = File.separator + "settings"
            + File.separator + "auth_settings.ser";
}
