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
public class Invalidator implements Runnable {

    private String username = "";

    public Invalidator(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000 * 60 *2);
            LoginBeanManager loginBeanManager;
            try {
                loginBeanManager = new LoginBeanManager();
                loginBeanManager.invalidate(username);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Invalidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Invalidator.class.getName()).log(Level.SEVERE, null, ex);
            }


        } catch (InterruptedException ex) {
            Logger.getLogger(Invalidator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
