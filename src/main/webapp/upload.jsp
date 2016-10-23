<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
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
                if (lg != null) 
		{
                    String UserName = lg.getUsername();
                    if (lg.getloggedin()) 
                    { %>
                        <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Images</a></li>
			<li><a href="${pageContext.request.contextPath}/Profile/<%=lg.getUsername()%>"><%=UserName%></a></li>
                        <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
                        <form action="${pageContext.request.contextPath}/Search" method="POST">
                            <input type="text" placeholder="Search" name="searchQuery">
                            <input type="submit" value="Submit">
                        </form>
                    <%}   
                }else{ %>
                    <li><a href="${pageContext.request.contextPath}/Register">Register</a></li>
                    <li><a href="${pageContext.request.contextPath}/Login">Login</a></li>
                <%}%>
            </ul>    
        </div>
        </header>
            
        <body>
            <h1>File Upload</h1>
            <form method="POST" enctype="multipart/form-data" action="Image">
                File to upload: <input type="file" name="upfile"><br/>

                <br/>
                <input type="submit" value="Press"> to upload the file!
            </form>

        </body>
    </div>
</html>
