<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="pagesize">
        <header>
            <h1>InstaGrim ! </h1>
            <h2>Your world in Black and White</h2>
            
             <div class="nav">
            <ul>

               <li><a class="active" href="/Instagrim">Home</a></li>
                <li><a href="upload.jsp">Upload</a></li>
                    <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
			if (lg.getlogedin()) {
			%>
			    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Images</a></li>
			    <%
                    if (lg != null) 
		    {
			UserName = lg.getUsername();
			if (lg.getlogedin()) 
			{ %>
			    <li><a href="${pageContext.request.contextPath}/Profile/<%=lg.getUsername()%>"><%=UserName%></a></li>
			<%}
                    } 
		%>
			<%}
                        }else{
                        %>
			    <li><a href="${pageContext.request.contextPath}/Register">Register</a></li>
			    <li><a href="${pageContext.request.contextPath}/Login">Login</a></li>
			<%}%>
            </ul>
       
        </div>
        </header>
       
            <div class="text">
       <img src="${pageContext.request.contextPath}/images/BAndWSkyline.jpg" class="homeImg" alt="Black and White Skyline"/>
                
            </div>
        
        <footer>
            <ul>
                <li>&COPY; Andy Cobley & Josh Corps</li>
            </ul>
        </footer>
            </div>
    </body>
</html>
