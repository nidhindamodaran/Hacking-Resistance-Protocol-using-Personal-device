/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Harris
 */
public class DBManager {

    Connection connection = null;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection != null) {
            return connection;
        } else {
            createNewConnection();
            return connection;
        }
    }

    private void createNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DBConstants.DB_CLASS_NAME);
        connection = DriverManager.getConnection(DBConstants.DB_URL,
                DBConstants.DB_USER, DBConstants.DB_PASSWORD);
    }
}
