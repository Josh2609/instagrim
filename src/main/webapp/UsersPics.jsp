<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet "href="css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Styles.css" />
        <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
     <body>

        <div class="pagesize">
        <header>
            <h1>InstaGrim ! </h1>
            <h2>Your world in Black and White</h2>
            
           <div class="nav">
            <ul>
               <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
               <li><a href="${pageContext.request.contextPath}">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/upload.jsp">Upload</a></li>
		<li><a href="${pageContext.request.contextPath}/Images/<%=lg.getUsername()%>">Images</a></li>
		<%
                    String profileToGet = (String)request.getAttribute("profileToGet");
                    if (lg != null) 
		    {
			if (lg.getUsername().equals(profileToGet)) 
			{ %>
			    <li><a class="active" href="${pageContext.request.contextPath}/Profile/<%=lg.getUsername()%>"><%=lg.getUsername()%></a></li>
                            
                            <form action="${pageContext.request.contextPath}/Search" method="POST">
                                <input type="text" placeholder="Search" name="searchQuery">
                                <input type="submit" value="Submit">
                            </form>
			<%} else { %>
			    <li><a href="${pageContext.request.contextPath}/Profile/<%=lg.getUsername()%>"><%=lg.getUsername()%></a></li>
			<% }
                    }
		%>
            </ul>
	    </div>
        </header>
            <br>
             <div class="nav">
             <ul>
                <li><a href="${pageContext.request.contextPath}/Profile/<%=profileToGet%>">Bio</a></li>
                <li><a href="${pageContext.request.contextPath}/Posts/<%=profileToGet%>">Posts</a></li>
                <li><a class="active" href="${pageContext.request.contextPath}/Images/<%=profileToGet%>">Images</a></li>
                <li><a href="#">Following</a></li>
                <li><a href="#">Followers</a></li>
                <%
                    if (lg != null) 
		    {
			if (lg.getUsername().equals(profileToGet))               // Change address for EditProfile **EDIT**
			{ %>
			    <li><a href="${pageContext.request.contextPath}/EditProfile/<%=profileToGet%>">Edit Profile</a></li>
			<%}
                    } 
		%>  	
            </ul>
             </div>
            
  <div id="profileOutline">
    <div id="content" class="clearfix">
      <div id="userphoto"><img src="../images/avatar.png" alt="default avatar"></div>
      <h1><%=profileToGet%></h1>
      
       <article>
           <% if (lg.getUsername().equals(profileToGet))               // Change address for EditProfile **EDIT**
		{ %>
                    <h1>Your Pics</h1>
		<%} else {%>  	
                    <h1><%=profileToGet%>'s Pics</h1>
                <%}%>  
        <%
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) {
        %>
        <p>No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            while (iterator.hasNext()) {
                Pic p = (Pic) iterator.next();

        %>
        <a href="/Instagrim/Image/<%=p.getSUUID()%>" ><img style="max-width: 50%" src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a><br/><%

            }
            }
        %>
        </article>            
    
    </div><!-- @end #content -->
  </div><!-- @end #w -->

</div> <!-- class="pageSize"-->
</body>
    </body>
</html>
