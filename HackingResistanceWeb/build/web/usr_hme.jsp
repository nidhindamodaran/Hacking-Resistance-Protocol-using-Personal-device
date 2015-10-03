<%@page import="com.hrp.beans.LoginBean"%>
<%@page import="com.hrp.utils.AppConstants"%>
<%@page import="java.io.File"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Hacking Resistance  | user home</title>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" media="screen" href="css/reset.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
        <script src="js/jquery-1.7.min.js"></script>
        <script src="js/jquery.easing.1.3.js"></script>
        <script src="js/FF-cash.js"></script>
        <!--[if lt IE 9]>
        <script src="js/html5.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="css/ie.css">
        <![endif]-->
    </head>

    <%
        LoginBean loginBean = (LoginBean) session.getAttribute(AppConstants.LOGIN_SESSION);
        if (loginBean != null) {

            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Expires", "0");
            response.setDateHeader("Expires", -1);
    %> 
   
    <body>
        <!--==============================header=================================-->
        <header>
            <div class="main">
                <div class="wrap">
                    <br/>
                    <h3 style="color:#FF9900;font-size:36px">Hacking Resistance
                        Protocol
                    </h3>
                    <div class="tooltips"></div>
                </div>
                <div class="nav-shadow">
                    <div>
                        <nav>
                            <ul class="menu">
                                <li class="current"><a href="usr_hme.jsp">Home</a></li>
                                <li ><a href="usr_profile.jsp">Profile</a></li>
                                <li ><a href="usr_account.jsp">Account</a></li>
                                <li ><a href="ChangeImeiOtpServlet">ChangeImei</a></li>
                                <li><a href="LogOutServlet">Logout</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="header-content header-subpages"></div>
        </header>
        <!--==============================content================================-->
        <section id="content" class="content-subpages">
            <div>
                <div class="wrap">
                    <div class="col-1 border-2">
                        <h3>Welcome <%=loginBean.getUsername()%>, Download your Application</h3>
                        <table>
                            <thead>
                                <tr>
                                    <th style="width: 25px;">No</th>
                                    <th>File Name</th>
                                    <th>Options</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    String absPath = getServletContext().getRealPath("upload");
                                    File uploadFolder = new File(absPath);
                                    File[] innerFiles = uploadFolder.listFiles();
                                    if (innerFiles != null) {
                                        for (int i = 0; i < innerFiles.length; i++) {
                                            File file = innerFiles[i];
                                %>

                                <tr>
                                    <td><%= (1 + i)%></td>
                                    <td><%= file.getName()%></td>
                                    <td> <a href="./upload/<%= file.getName()%>">Download File </a></td>
                                </tr> 
                                <%}
                                    }%>
                            </tbody>
                        </table>

                    </div>
                    <div class="col-2">
                        <h2 class="p2">Update Security assistance Device</h2>
                        <p> Hacking Resistance, a method used for some encryption.</p>

                        <dl>
                            <dt class="p4">Contact Us for Help<br>
                            </dt>
                            <dd><span>Telephone:</span>+1 959 552 5963</dd>
                            <dd><span>FAX:</span>+1 959 552 5963</dd>
                            <dd><span>E-mail:</span><a href="#" class="link">mail@demolink.org</a></dd>
                        </dl>
                    </div>
                </div>
            </div>
        </section>
        <!--==============================footer=================================-->
        <footer>
            <p>© 2014 <a href="#" class="link">Security Group</a> Website Template by <a target="_blank" href="#" class="link">Your Name</a></p>
            <p>Phone: +1 800 559 6580 &nbsp; Email: <a href="#" class="link">info@security.com</a></p>
        </footer>
    </body>
    <%} else {
            response.sendRedirect("index.jsp");
        }%>
</html>
