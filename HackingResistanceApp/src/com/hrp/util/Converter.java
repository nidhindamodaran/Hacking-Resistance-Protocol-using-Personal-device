/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.util;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Converter implements Serializable{

    public  static String getHexString(String  hexString) {
        byte[] bytes = hexString.getBytes();
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);

    }

    public static String hexStringToString(String data) {
        int k = 0;
        byte[] results = new byte[data.length() / 2];
        for (int i = 0; i < data.length();) {
            results[k] = (byte) (Character.digit(data.charAt(i++), 16) << 4);
            results[k] += (byte) (Character.digit(data.charAt(i++), 16));
            k++;
        }
        return new String(results);
    }
    
    
//    public static void main(String[] args) {
//        String text = "Hello this isa <b> </b> @# jsher34 3f $%!@#$#%$%^&*()_+ \ndsfjhsdkjh";
//        String hexContent =  getHexString(text);
//        System.out.println(hexContent);
//        String rettext = hexStringToString(hexContent);
//        System.out.println("return text : " + rettext);
//    }
}
