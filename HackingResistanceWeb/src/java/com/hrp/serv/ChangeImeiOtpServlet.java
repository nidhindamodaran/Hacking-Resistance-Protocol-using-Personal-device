/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.serv;

import com.hrp.beans.LoginBean;
import com.hrp.beans.UserBean;
import com.hrp.beans.mgr.LoginBeanManager;
import com.hrp.beans.mgr.UserBeanManager;
import com.hrp.utils.AppConstants;
import com.hrp.utils.SendMail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class ChangeImeiOtpServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute(AppConstants.LOGIN_SESSION);
            if (loginBean != null) {
                String username = loginBean.getUsername();
                LoginBeanManager loginBeanManager = new LoginBeanManager();
                String otpKey = loginBeanManager.generateOtpKey(username);
                UserBeanManager userBeanManager = new UserBeanManager();
                UserBean userDetails = userBeanManager.getUserDetails(username);
                System.out.println("OTP: " + otpKey);
                new Thread(new SendMail(username, userDetails.getEmail(), "Your New OTP is " + otpKey
                        + " and is Valid for only 2 min", "important! OTP FOR HRP")).start();
                response.sendRedirect("usr_account_imei.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChangeImeiOtpServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChangeImeiOtpServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
