/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.ws;

import com.hrp.beans.mgr.LoginBeanManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harris
 */
public class WSManager  {

    public WSManager() {
    }

    String isValidUser(String username, String imei) {
        try {
            LoginBeanManager beanManager = new LoginBeanManager();
            if (beanManager.validateUser(username, imei)) {
                return "true";
            } else {
                return "false";
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WSManager.class.getName()).log(Level.SEVERE, null, ex);
            return "false";
        } catch (SQLException ex) {
            Logger.getLogger(WSManager.class.getName()).log(Level.SEVERE, null, ex);
            return "false";
        }

    }

    String getKeyFor(String username, String imei) {
        String oneTimeKey = "";
        try {
            LoginBeanManager beanManager = new LoginBeanManager();
            oneTimeKey = beanManager.getOneTimeKeyForDeskTop(username, imei);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WSManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(WSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oneTimeKey;

    }

    String generateKey(String username, String imei) {
        String key = "error";
        try {
            LoginBeanManager beanManager = new LoginBeanManager();
            if (beanManager.validateUser(username, imei)) {
                key = beanManager.generatekey(username, imei);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WSManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(WSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
        
    }

    String phoneLogin(String username, String password, String imei) {
        String status = "error";
        try {
            LoginBeanManager beanManager = new LoginBeanManager();
            status = beanManager.checkPhone(username, password, imei);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WSManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(WSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;


    }
}
