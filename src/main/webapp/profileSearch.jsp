<%-- 
    Document   : profile
    Created on : 10-Oct-2016, 21:07:52
    Author     : Josh
--%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles.css" />
        <title>Instagrim</title>
    </head>
     <body>

        <div class="pagesize">
        <header>
            <h1>InstaGrim ! </h1>
            <h2>Your world in Black and White</h2>
            
            <div class="nav">
            <ul>
               <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
               <li><a href="/Instagrim">Home</a></li>
                <li><a href="upload.jsp">Upload</a></li>
		<li><a href="/Instagrim/Images/">Images</a></li>
		<%
                    if (lg != null) 
		    {
			String UserName = lg.getUsername();
			if (lg.getlogedin()) 
			{ %>
			    <li><a class="active" href="profile.jsp"><%=UserName%></a></li>
			<%}
                    } 
		%>
            </ul>
	    </div>
        </header>
            <br>
             <div class="nav">
            <ul>
                <li><a class ="active" href="">Bio</a></li>
                <li><a href="/Instagrim">Posts</a></li>
                <li><a href="#">Images</a></li>
                <li><a href="#">Following</a></li>
                <li><a href="#">Followers</a></li>		
            </ul>
                 
            <div id="profileOutline">
                <div id="content" class="clearfix">
                    <div id="userphoto"><img src="../images/avatar.png" alt="default avatar"></div>
                    
                    
                    <%
            String username = "";
            String firstName = "";
            String lastName = "";
            String email = "";
                
            ProfileBean profile = new ProfileBean();
                profile = (ProfileBean) request.getAttribute("ProfileBean");
                if (profile != null) {
                    username = profile.getUsername();
                    firstName = profile.getFirstName();
                    lastName = profile.getLastName();
                    email = profile.getEmail();
                }
                else {
                    username = "Not Logged In";
                }
                %>
               
            
                    <h1 style="color: white"><%=username%></h1>
                    <p class="bio"><span>User Name</span> <%=username %></p>
                    
                    <p class="bio"><span>Email Address</span><%=email %></p>
                    <p class="bio"><span>First Name</span><%=firstName %></p>
                    <p class="bio"><span>Last Name</span><%=lastName %></p>
                    <p class="bio"></p>
                </div>
            </div>
            <% String msg = (String)request.getAttribute("Message");
               if(msg != null) { %>
            <p style="color: white" id="flash_message"><%= msg %></p>
            <% } %>
            
             
            
  
</div> <!-- class="pageSize"-->
</body>
    </body>
</html>
