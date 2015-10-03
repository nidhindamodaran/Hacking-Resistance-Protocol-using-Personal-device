/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.beans;

/**
 *
 * @author Harris
 */
public class LoginBean {

    private String username = "";
    private String password = "";
    private String userType = "";
    private String oneTimekey = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getOneTimekey() {
        return oneTimekey;
    }

    public void setOneTimekey(String oneTimekey) {
        this.oneTimekey = oneTimekey;
    }
}
