<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
        <header>
        <h1>InstaGrim ! </h1>
        <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>
                
                <li><a href="/Instagrim/Images/majed">Sample Images</a></li>
            </ul>
        </nav>
       
        <article>
            <h3>Register as user</h3>
            
             <% String msg = (String)request.getAttribute("Message");
               if(msg != null) { %>
            <p id="flash_message"><%= msg %></p>
            <% } %>
            
            <form method="POST"  action="Register">
                <ul>
                    <li>User Name <input type="text" name="username" required="true"
                            <% String usrnme = (String)request.getAttribute("username");
                                if(usrnme != null)%>
                                    value=<%= usrnme %>
                        ></li>
                    <li>First Name <input type="text" name="firstName" required="false"></li>
                    <li>Last Name <input type="text" name="lastName" required="false"></li>
                    <li>Email <input type="email" name="email" required="true"></li>
                    <li>Password <input type="password" name="password" required="true"></li>
                    <li>Repeat Password <input type="password" name="repeatPassword" required="true"></li>
                </ul>
                <br/>
                <input type="submit" value="Register"> 
            </form>

        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
