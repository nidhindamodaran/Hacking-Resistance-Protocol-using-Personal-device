/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.mail.utils;

import java.util.Vector;

/**
 *
 * @author Staff
 */
public class ApplicationVariables {

  
    public static Vector<MailBean> metaData = new Vector<MailBean>();
    public static boolean downloadBackgound = false;
    public static String messageFileName = "Message.txt";
    public static String textPlain = "text/plain";
    public static String textHtml = "text/html";
    public static String multipartAlternative = "multipart/alternative";
    public static final String ATTACHMENT = "ATTACHMENT";
    public static final String INLINE = "INLINE";
}
