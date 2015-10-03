/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.util;

/**
 *
 * @author Staff
 */
public class Validation {

    public Validation() {
    }

    public static boolean validateField(String value) {
        boolean valid = false;
        if (value != null && !value.equals("")) {
            if (value.length() >= 3) {
                valid = true;
            }
        }
        return valid;
    }

    

    public static  boolean validateEmail(String email) {
        boolean valid = false;
        if (email != null && !email.equals("")) {
            int atFirstPos = email.indexOf("@");
            int atLastPos = email.lastIndexOf("@");
            int dotLastPos = email.lastIndexOf(".");
            int len = email.length();
            if (atFirstPos > 0 && atFirstPos == atLastPos
                    && atFirstPos < dotLastPos) {
                valid = true;
            }
        }
        return valid;
    }

    public static boolean validatePassword(String password) {
        boolean valid = false;
        if (password != null && !password.equals("")) {
            if (password.length() >= 6) {
                valid = true;
            }
        }
        return valid;
    }

    

    public boolean validateMailServer(String incomingMailServer,
            String outgoingMailServer) {
        boolean valid = false;
        if (incomingMailServer != null && outgoingMailServer != null
                && !incomingMailServer.equals("") && !outgoingMailServer.equals("")) {
            if (incomingMailServer.equals("pop.gmail.com")
                    && outgoingMailServer.equals("smtp.gmail.com")) {
                valid = true;
            }
        }
        return valid;
    }

    public boolean validateDomainName(String domainName) {
        boolean valid = false;
        if (domainName != null && !domainName.equals("")) {
            int firstDotPos = domainName.indexOf(".");
            int lastDotPos = domainName.lastIndexOf(".");
            int afterLastDot = domainName.substring(lastDotPos).length();
            if (firstDotPos > 0) {
                if (firstDotPos == lastDotPos && afterLastDot >= 2) {
                    valid = true;
                } else if ((firstDotPos - lastDotPos) > 2 && afterLastDot >= 2) {
                    valid = true;
                }
            }
        }
        return valid;
    }

//    public boolean validateCustomFolderValue(String criteria, String value) {
//        boolean valid = false;
//        if (criteria != null && value != null && !value.equals("")) {
//            if (criteria.equalsIgnoreCase(AppConstants.SUBJECT)) {
//                valid = validateField(value);
//            } else if (criteria.equalsIgnoreCase(AppConstants.EMAILID)) {
//                valid = validateEmail(value);
//            } else {
//                valid = validateDomainName(value);
//            }
//        }
//        return valid;
//    }
}
