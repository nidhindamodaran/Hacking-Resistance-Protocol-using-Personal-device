/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrp.serv;

import com.hrp.beans.UserBean;
import com.hrp.beans.mgr.UserBeanManager;
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
 * @author Harris
 */
public class UserRegServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            UserBean userBean = new UserBean();
            userBean.setUsername(request.getParameter("username"));
            userBean.setPassword(request.getParameter("password"));
            userBean.setEmail(request.getParameter("email"));
            userBean.setImei(request.getParameter("imei"));
            userBean.setPhone(request.getParameter("phone"));
            userBean.setName(request.getParameter("name"));
            UserBeanManager userBeanManager = new UserBeanManager();
            if (userBeanManager.addNewUser(userBean)) {
                response.sendRedirect("index.jsp?reg=done");
            } else {
                response.sendRedirect("register.jsp?reg=fail");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRegServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("register.jsp?reg=fail");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserRegServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("register.jsp?reg=fail");
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
