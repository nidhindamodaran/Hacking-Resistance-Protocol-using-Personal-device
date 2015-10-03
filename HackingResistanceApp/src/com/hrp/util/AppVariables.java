/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.util;

import com.hrp.mail.utils.MailSettings;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Staff
 */
public class AppVariables implements Serializable {

    public static SettingsInfo settingsInfo = new SettingsInfo();
    // following values are used for mail settings
    public static MailSettings mailSettingsInfo = new MailSettings();
    public static boolean mailDownloadStarted = false;
    public static final String MAIL_SERVER = "pop.gmail.com";
    public static long REFRESH_TIME = 2000;
    public static File keyFile = null;
    public static boolean downloading = false;
    public static int falseAttemptToDownload = 0;
    public static final int attempt_limit = 7;
    public static boolean mailDownloading = false;
}
