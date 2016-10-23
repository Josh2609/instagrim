<%-- 
    Document   : followers
    Created on : 23-Oct-2016, 14:53:24
    Author     : joshcorps
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
                            <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
                            <form action="${pageContext.request.contextPath}/Search" method="POST">
                                <input type="text" placeholder="Search" name="searchQuery">
                                <input type="submit" value="Submit">
                            </form>                    
                        <%} else { %>
			    <li><a href="${pageContext.request.contextPath}/Profile/<%=lg.getUsername()%>"><%=lg.getUsername()%></a></li>
                            <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
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
                <li><a href="${pageContext.request.contextPath}/Images/<%=profileToGet%>">Images</a></li>
                <li><a href="${pageContext.request.contextPath}/Following/<%=profileToGet%>">Following</a></li>
                <li><a class="active" href="${pageContext.request.contextPath}/Followers/<%=profileToGet%>">Followers</a></li>
              
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
        <% 
                if (!lg.getUsername().equals(profileToGet))
                {
                    if (request.getAttribute("alreadyFollows").equals("false"))
                    { %>
                    
                    <form method="POST"  action="${pageContext.request.contextPath}/Follow">
                        <input type="text" name="followerUser" value="<%=lg.getUsername()%>" hidden>
                        <input type="text" name="followedUser" value="<%=profileToGet%>" hidden>       
                        <input style="width: 20% ;" type="submit" value="Follow" name="followBtn">
                    </form>
                    <%} else {%>
                         <input style="width: 20%; color: white" type="submit" value="Following" disabled>
                    <%}
                } %>
      <h1><%=profileToGet%></h1>
      
       <article>
           <% if (lg.getUsername().equals(profileToGet))               // Change address for EditProfile **EDIT**
		{ %>
                    <h1>Your Followers</h1>
		<%} else {%>  	
                    <h1><%=profileToGet%>'s Followers</h1>
                <%}%>  
        <%
            java.util.LinkedList<RelationshipBean> lsFollowers = (java.util.LinkedList<RelationshipBean>) request.getAttribute("Followers");
            if (lsFollowers == null) {
        %>
        <p>No Followers found</p>
        <%
        } else {
            Iterator<RelationshipBean> iterator;
            iterator = lsFollowers.iterator();
            while (iterator.hasNext()) {
                RelationshipBean rb = (RelationshipBean) iterator.next();

        %>
        <p style="color: white"> <a href="${pageContext.request.contextPath}/Profile/<%=rb.getFollowedByUser()%>"><%=rb.getFollowedByUser()%></a></p>
        <%

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
