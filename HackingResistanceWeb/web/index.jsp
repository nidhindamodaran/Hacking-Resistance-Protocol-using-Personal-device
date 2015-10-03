<%@page import="com.hrp.beans.LoginBean"%>
<%@page import="com.hrp.utils.AppConstants"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Hacking Resistance</title>
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
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>

    <%
        LoginBean loginBean = (LoginBean) session.getAttribute(AppConstants.LOGIN_SESSION);
        if (loginBean != null) {
            response.sendRedirect("usr_hme.jsp");
        }
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
                                <li class="current"><a href="index.html">Home</a></li>
                                <li><a href="register.jsp">Register</a></li>
                                <li><a href="faq.html">FAQ</a></li>

                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="header-content">
                <div class="wrap main">
                    <div class="block-1"> <img src="images/page1-img1.jpg" alt="" class="img-radius">
                        <div class="border-1">
                            <p class="color-1 p2">Security Systems for Files</p>
                            <p>&nbsp;</p>
                        </div>
                    </div>
                    <div class="block-1"> <img src="images/page1-img2.jpg" alt="" class="img-radius">
                        <div class="border-1">
                            <p class="color-1 p2">Security Systems for Data</p>
                            <p>&nbsp;</p>
                        </div>
                    </div>
                    <div class="block-1"> <img src="images/page1-img3.jpg" alt="" class="img-radius">
                        <div>
                            <p class="color-1 p2">Special Security Mails</p>
                            <p>&nbsp;</p>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!--==============================content================================-->
        <section id="content">
            <div>
                <div class="wrap">
                    <div class="col-1 border-2">
                        <h2 class="p3">Login </h2>
                        <div class="wrap"> 

                            <form id="form" method="post" action="LoginServlet">
                                <%
                                    String reg_stat = request.getParameter("reg");
                                    if (reg_stat != null && reg_stat.equals("done")) {
                                %>
                                <b style="color: #FF9900;">Successfully Registerd, Please Login</b>
                                <%}%>
                                <br/>
                                <br/>
                                <br/>
                                <fieldset>
                                    <label><strong style="font-weight: bold;">Username :</strong>
                                        <input type="text" name="username"  required="required" />
                                    </label>
                                    <label><strong style="font-weight: bold;" >Password :</strong>
                                        <input type="password"  name="password"  required="required" />
                                    </label>
                                    <input type="submit"  id="button" value="Login" style="height: 30px; width: 100px; font-weight: bold;" />
                                </fieldset>
                                <%
                                    String userType = request.getParameter("user_type");
                                    if (userType != null && userType.equals("invalid")) {
                                %>
                                <b style="color: #FF9900;">Invalid User</b>
                                <%}%>
                            </form>

                        </div>
                        <div class="wrap top-2">
                            <ul class="list-1 fleft">
                                <li>
                                    The  protocol  utilizes  a  user's  cell phone  as  an  authentication .
                                    Different  from  regular login  processes,  additional  steps  are  required for  the  protocol. 
                                    In  the  registration  phase,  a  user  starts  the  protocol  program 
                                    to  register  her  new  account  on  the  website.  Unlike 
                                    conventional  registration,  the  server  requests  for  the  user's 
                                    account id  and phone number,  


                                </li>
                                <li><a href="#"></a></li>
                            </ul>
                            <ul class="list-1 fleft">
                                <li> 
                                    The  aim  of  this  phase  is  to  allow  a  user  and  a  server  to 
                                    negotiate  a  shared  secret  to  authenticate  succeeding  logins 
                                    for  this  user.  
                                </li>
                                <li> 
                                    The  protocol  starts  when  user  u  wishes  to  log  into  her 
                                    favourite  web  server  (already  registered).
                                </li>




                            </ul>
                        </div>
                    </div>
                    <div class="col-2">
                        <h2 class="p2"></h2>
                        <a href="#" class="link-2">1.Setting up  the System:</a>
                        Different from the  ordinary  user 
                        authentication  system,  users  should  install  cell phone 
                        software  and  a  browser  extension  to  setup  the 
                        protocol  system. 
                        <a href="#" class="link-2">2.  Registering  for  an  Account:</a>
                        <p class="p4">   Users  first  open  the 
                            registration  software on  the  web. Users  then  fill 
                            out  a  form,  which  includes  an  account  id,  a  website's 
                            id,  and  a  long-term  password,  and  submit  it  to  the 
                            website. </p>
                        <a href="#" class="link-2">3.  Logging  into  the  Website: </a>
                        <p class="p4">  Users  first  enter  their 
                            account  id  into  the  browser    and  submit 
                            it  to  the  server.  Users  then  type  their  long-term 
                            password into the. 
                            The  login  succeeds  if  a  success  message  is  shown  on 
                            the  screen  of  cell  phone.  If  login  fails,  participants 
                            should try again until they  are successful.</p>
                        <a href="#" class="link-2">4. Other features</a>
                        <p>Security integrated with android.</p>
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
</html>
