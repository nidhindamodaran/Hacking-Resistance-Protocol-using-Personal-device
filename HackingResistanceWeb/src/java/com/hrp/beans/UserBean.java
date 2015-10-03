/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.beans;

/**
 *
 * @author Harris
 */
public class UserBean extends LoginBean {

    private String name = "";
    private String email = "";
    private String phone = "";
    private String imei = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
