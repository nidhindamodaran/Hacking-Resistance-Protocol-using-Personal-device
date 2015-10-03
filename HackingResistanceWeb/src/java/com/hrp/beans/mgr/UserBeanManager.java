/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.beans.mgr;

import com.hrp.beans.UserBean;
import com.hrp.db.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Harris
 */
public class UserBeanManager {

    private Connection connection = null;

    public UserBeanManager() throws SQLException, ClassNotFoundException {
        connection = new DBManager().getConnection();
    }

    public boolean addNewUser(UserBean userBean) throws SQLException {
        boolean insert = false;
        if (canAdd(userBean.getImei())) {
            String queryForLogin = "INSERT into login_info  (username, password, imei ) VALUES (?, ?, ?)";
            PreparedStatement psForLogin = connection.prepareStatement(queryForLogin);
            psForLogin.setString(1, userBean.getUsername());
            psForLogin.setString(2, userBean.getPassword());
            psForLogin.setString(3, userBean.getImei());
            if (psForLogin.executeUpdate() > 0) {
                String query = "INSERT  INTO user_info (user_name, name, email, phone ) VALUES (?, ?, ?, ?);";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, userBean.getUsername());
                ps.setString(2, userBean.getName());
                ps.setString(3, userBean.getEmail());
                ps.setString(4, userBean.getPhone());
                if (ps.executeUpdate() > 0) {
                    insert = true;
                }
            }
        }
        return insert;
    }

    public UserBean getUserDetails(String username) throws SQLException {
        UserBean userBean = null;
        String query = "SELECT name, email, phone FROM user_info WHERE user_name = ? ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            userBean = new UserBean();
            userBean.setName(rs.getString("name"));
            userBean.setEmail(rs.getString("email"));
            userBean.setPhone(rs.getString("phone"));
        }
        return userBean;

    }

    public boolean updateProfile(UserBean userBean) throws SQLException {
        String query = "UPDATE user_info  SET   name = ?, email = ?, phone = ?"
                + " WHERE user_name = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, userBean.getName());
        ps.setString(2, userBean.getEmail());
        ps.setString(3, userBean.getPhone());
        ps.setString(4, userBean.getUsername());
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean canAdd(String imei) throws SQLException {
        boolean canAdd = true;
        String query = "SELECT * FROM lost_imei WHERE imei = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, imei);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            canAdd = false;
        }
        return canAdd;
    }
}
