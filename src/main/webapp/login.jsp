<%-- 
    Document   : login.jsp
    Created on : Sep 28, 2014, 12:04:14 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />

    </head>
    <body>
         <div class="pagesize">
        <header>
        <h1>InstaGrim ! </h1>
        <h2>Your world in Black and White</h2>
        </header>
         <div class="nav">
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
                <li><a href="register.jsp">Register</a></li>
                <li><a href="/Instagrim/Images/majed">Samples</a></li>
            </ul>
        </div>
       
        <article>
            <h3>Login</h3>
            <form method="POST"  action="Login">
                <ul>
                    <li>User Name <input type="text" name="username" required="true"></li>
                    <li>Password <input type="password" name="password" required="true"></li>
                </ul>
                <br/>
                <input type="submit" value="Login"> 
            </form>

        </article>
        <footer>
        </footer>
         </div>
    </body>
</html>
