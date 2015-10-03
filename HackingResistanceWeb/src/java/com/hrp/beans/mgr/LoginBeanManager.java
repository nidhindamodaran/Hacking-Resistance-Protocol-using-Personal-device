/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.beans.mgr;

import com.hrp.beans.LoginBean;
import com.hrp.beans.UserBean;
import com.hrp.db.DBManager;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Harris
 */
public class LoginBeanManager {

    private Connection connection = null;

    public LoginBeanManager() throws ClassNotFoundException, SQLException {
        connection = new DBManager().getConnection();
    }

    public boolean checkLogin(LoginBean loginBean) throws SQLException {
        boolean status = false;
        String query = "SELECT * FROM login_info WHERE username = ? AND password = ? ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, loginBean.getUsername());
        ps.setString(2, loginBean.getPassword());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            status = true;
        }
        return status;
    }

    public boolean updateAccount(UserBean userBean) throws SQLException {
        boolean status = false;
        String query = "UPDATE login_info SET password = ?,"
                + " imei = ?  WHERE one_time_key = ? AND username = ? AND password = ? ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, userBean.getPassword());
        ps.setString(2, userBean.getImei());
        ps.setString(3, userBean.getOneTimekey());
        ps.setString(4, userBean.getUsername());
        ps.setString(5, userBean.getPassword());
        if (ps.executeUpdate() > 0) {
            status = true;
        }
        return status;
    }

    public boolean validateUser(String username, String imei) throws SQLException {
        boolean status = false;

        String query = "SELECT * FROM login_info WHERE username = ? AND imei = ? ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, imei);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            status = true;
        }

        return status;
    }

    public String getOneTimeKey(String username, String imei) throws SQLException {
        String oneTimeKey = "";
        String query = "SELECT * FROM login_info WHERE username = ? AND imei = ? ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, imei);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            oneTimeKey = rs.getString("one_time_key");
        }
        return oneTimeKey;
    }

    public String getOneTimeKeyForDeskTop(String username, String imei) throws SQLException {
        String oneTimeKey = "";
        String query = "SELECT * FROM login_info WHERE username = ?  ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            oneTimeKey = rs.getString("one_time_key");
        }
        return oneTimeKey;
    }

    public String generatekey(String username, String imei) throws SQLException {
        String key = "error";
        SecureRandom random = new SecureRandom();
        String genKey = new BigInteger(130, random).toString(32);
        String query = "UPDATE login_info SET one_time_key = ?  WHERE username = ? AND imei = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, genKey);
        ps.setString(2, username);
        ps.setString(3, imei);
        if (ps.executeUpdate() > 0) {
            key = genKey;
        }
        return key;
    }

    public String generateOtpKey(String username) throws SQLException {
        String key = "error";
        SecureRandom random = new SecureRandom();
        String genKey = new BigInteger(130, random).toString(32);
        String query = "UPDATE login_info SET one_time_key = ?  WHERE username = ? ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, genKey);
        ps.setString(2, username);
        if (ps.executeUpdate() > 0) {
            key = genKey;
        }
        return key;
    }

    public synchronized String invalidate(String username) throws SQLException {
        String key = "error";
        SecureRandom random = new SecureRandom();
        String genKey = new BigInteger(130, random).toString(32);
        String query = "UPDATE login_info SET one_time_key = ?  WHERE username = ? ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, genKey);
        ps.setString(2, username);;
        if (ps.executeUpdate() > 0) {
            key = genKey;
        }
        return key;
    }

    public String checkPhone(String username, String password, String imei) throws SQLException {
        String status = "error";
        String query = "SELECT * FROM login_info WHERE username = ? AND password = ?  AND imei = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, imei);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            status = "true";
        }
        return status;
    }

    public boolean updatePassword(LoginBean loginBean, String newPwd) throws SQLException {
        String query = "UPDATE login_info SET password = ? WHERE username = ? AND password = ? ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, newPwd);
        ps.setString(2, loginBean.getUsername());
        ps.setString(3, loginBean.getPassword());
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateImei(UserBean userBean, String blackList) throws SQLException {
        boolean update = false;
        String currentImei = "";
        String selectImeiQuery = "SELECT imei FROM login_info WHERE username = ?";

        PreparedStatement selectImeiPS = connection.prepareStatement(selectImeiQuery);
        selectImeiPS.setString(1, userBean.getUsername());
        ResultSet rsToSelectImei = selectImeiPS.executeQuery();
        if (rsToSelectImei.next()) {
            currentImei = rsToSelectImei.getString("imei");

        }

        String query = "UPDATE login_info SET imei = ? WHERE username = ? AND password = ? AND one_time_key = ?";
        PreparedStatement psForUpdateLogin = connection.prepareStatement(query);
        psForUpdateLogin.setString(1, userBean.getImei());
        psForUpdateLogin.setString(2, userBean.getUsername());
        psForUpdateLogin.setString(3, userBean.getPassword());
        psForUpdateLogin.setString(4, userBean.getOneTimekey());
        if (psForUpdateLogin.executeUpdate() > 0) {
            if (blackList.equals("yes")) {
                String addImeiQuery = "INSERT into lost_imei (imei) VALUES ( ? ) ";
                PreparedStatement ps = connection.prepareStatement(addImeiQuery);
                ps.setString(1, currentImei);
                if (ps.executeUpdate() > 0) {
                    update = true;
                }

            } else {
                update = true;
            }
        }


        return update;
    }
}
