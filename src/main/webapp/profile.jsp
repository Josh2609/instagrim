<%-- 
    Document   : profile
    Created on : 10-Oct-2016, 21:07:52
    Author     : Josh
--%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet "href="css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="Styles.css" />
	<%
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            if (lg != null) 
	    {
		String UserName = lg.getUsername();
		if (lg.getlogedin()) %>
		    <title> Instagrim: <%=UserName + "'s Profile" %></title>
	    <% } %>
			
		
		
    </head>
    <body>
        <div class="pagesize">
        <header>
            <h1>InstaGrim ! </h1>
            <h2>Your world in Black and White</h2>
            
            <div class="nav">
            <ul>

               <li><a href="/Instagrim">Home</a></li>
                <li><a href="upload.jsp">Upload</a></li>
		<li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Images</a></li>
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
	
	    
	    
	</div> <!-- class="pageSize"-->
	 
	    
    </body>
</html>
