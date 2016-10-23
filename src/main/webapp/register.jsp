<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
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
                
                <li><a href="/Instagrim">Home</a></li>
                  <li><a href="upload.jsp">Upload</a></li>
                    <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getloggedin()) {
                    %>

                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Images</a></li>
                     <%}
                               }else{
                              %>
                    <li><a class="active" href="${pageContext.request.contextPath}/Register">Register</a></li>
                    <li><a href="${pageContext.request.contextPath}/Login">Login</a></li>
                 <%  }%>
            </ul>
        </div>
       
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
                            if(usrnme != null) { %>
                                value=<%= usrnme%>
                            <%} else {%>
                                value=<%= ""%>
                            <% } %>
                    ></li>
                    <li>First Name <input type="text" name="firstName" required="trues"
                            <% String frstnme = (String)request.getAttribute("firstName");
                            if(frstnme != null) { %>
                                value=<%= frstnme%>
                            <%} else {%>
                                value=<%= ""%>
                            <% } %>
                    ></li>
                    <li>Last Name <input type="text" name="lastName" required="true"
                    <% String lstnme = (String)request.getAttribute("lastName");
                            if(lstnme != null) { %>
                                value=<%= lstnme%>
                            <%} else {%>
                                value=<%= ""%>
                            <% } %>
                    ></li>
                    <li>Email <input type="email" name="email" required="true"
                    <% String eml = (String)request.getAttribute("email");
                            if(eml != null) { %>
                                value=<%= eml%>
                            <%} else {%>
                                value=<%= ""%>
                            <% } %>
                    ></li>
                    <li>Password <input type="password" name="password" required="true"></li>
                    <li>Repeat Password <input type="password" name="repeatPassword" required="true"></li>
                </ul>
                <br/>
                <input type="submit" value="Register"> 
            </form>

        </article>
        <footer>
        </footer>
        </div>
    </body>
</html>
