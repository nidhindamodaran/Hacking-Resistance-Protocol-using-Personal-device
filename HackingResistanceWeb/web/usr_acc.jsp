<%@page import="com.hrp.beans.LoginBean"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Hacking Resistance  | user profile</title>
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
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Expires", "0");
        response.setDateHeader("Expires", -1);
    %> 

    <%
        LoginBean loginBean = (LoginBean) session.getAttribute(AppConstants.LOGIN_SESSION);
        if (loginBean != null) {

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
                                <li ><a href="index.html">Home</a></li>
                                <li class="current"><a href="usr_profile.html">Profile</a></li>
                                <li><a href="index.html">Logout</a></li>
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
                        <h2>Update Phone</h2>
                        <form id="form" method="post" action="UserProfileUpdate">
                            <fieldset>
                                <label><strong>Name:</strong>
                                    <input type="text" name="name" required="required" value="" />
                                </label>
                                <label><strong>Email:</strong>
                                    <input type="email" name="email"  required="required"   value="" />
                                </label>
                                <label><strong>Phone:</strong>
                                    <input type="tel"  name="phone" required="required" pattern="[0-9]{10,10}" value="" />
                                </label>
                                <label><strong>IMEI :</strong>
                                    <input type="tel" name="imei"  required="required" pattern="[0-9]{16,16}"  value=""/>
                                </label>
                                <label><strong>Username:</strong>
                                    <input type="text" name="username" required="required" value=""/>
                                </label>
                                <label><strong>Password:</strong>
                                    <input type="password" name="password"  id="password" required="required"  />
                                </label>
                                <label><strong>Confirm:</strong>
                                    <input type="password" name="confirm"  id="confirm" required="required"  oninput="check(this)"/>
                                    <script language='javascript' type='text/javascript'>
                                        function check(input) {
                                            if (input.value != document.getElementById('password').value) {
                                                input.setCustomValidity('The two passwords must match.');
                                            } else {
                                                // input is valid -- reset the error message
                                                input.setCustomValidity('');
                                            }
                                        }
                                    </script>

                                </label>


                                <br/>
                                <div class="btns">
                                    <input type="submit" id="button" value="Login" style="height: 30px; width: 100px;" />
                                </div>
                            </fieldset>
                            <%
                                String userType = request.getParameter("reg");
                                if (userType != null && userType.equals("fail")) {
                            %>
                            <b style="color: #FF9900;">Failed to register please try again</b>
                            <%}%>
                        </form>
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
    <% }%>
</html>
