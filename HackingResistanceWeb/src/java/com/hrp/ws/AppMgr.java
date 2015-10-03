/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Harris
 */
@WebService(serviceName = "AppMgr")
public class AppMgr {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "generateKey")
    public String generateKey(@WebParam(name = "username") String username, @WebParam(name = "imei") String imei) {
        //TODO write your implementation code here:
        WSManager wSManager = new WSManager();
        String oneTimeKey = wSManager.generateKey(username, imei);
        return oneTimeKey;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "invalidateKey")
    public String invalidateKey(@WebParam(name = "username") String username, @WebParam(name = "imei") String imei) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "configureApp")
    public String configureApp(@WebParam(name = "username") String username, @WebParam(name = "imei") String imei) {
        WSManager wSManager = new WSManager();
        return wSManager.isValidUser(username, imei);

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getKeyForUser")
    public String getKeyForUser(@WebParam(name = "username") String username, @WebParam(name = "imei") String imei) {
        //TODO write your implementation code here:
        WSManager wSManager = new WSManager();
        return wSManager.getKeyFor(username, imei);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "oneTimeLogin")
    public String oneTimeLogin(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "imei") String imei) {
        //TODO write your implementation code here:
        WSManager wSManager = new WSManager();
        String status = wSManager.phoneLogin(username, password, imei);
        return status;
    }
}
