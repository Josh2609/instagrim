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
	<link rel="stylesheet" type="text/css" href="Styles.css" />
	<%
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) 
			{
                            String UserName = lg.getUsername();
			    if (lg.getlogedin()) 
			    { %>
				<title> Instagrim: <%=UserName + "'s Profile" %></title>
			    <%}
                        }else{
                        %>

			<%}%>
			
		
		
    </head>
    <body>
        
	
	
    </body>
</html>
