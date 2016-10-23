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
            java.util.LinkedList<ImageCommentBean> lsComments = (java.util.LinkedList<ImageCommentBean>) request.getAttribute("Comments");
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
        <a href="/Instagrim/Image/<%=p.getSUUID()%>" ><img style="max-width: 50%" src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a><br/> 
            <form method="POST"  action="${pageContext.request.contextPath}/ImageComment">
                    <input type="text" name="username" value="<%=lg.getUsername()%>" hidden>
                    <input type="text" name="picOwner" value="<%=profileToGet%>" hidden>
                    <input type="text" name="picID" value="<%=p.getSUUID()%>" hidden>
                    <textarea name="comment" rows="2" style="width: 40%" placeholder="New Comment"></textarea>
                    <input type="submit" value="Comment" name="CommentSubmit">
            </form>
        <%if (lsComments != null) 
                    {
                        Iterator<ImageCommentBean> commentIterator;
                        commentIterator = lsComments.iterator();
                        while (commentIterator.hasNext()) {
                            ImageCommentBean icb = (ImageCommentBean) commentIterator.next();
                            if(icb.getPicID().toString().equals(p.getSUUID()))
                            { %>
                                <p style="color: white"><%=icb.getComment()%> - <%=icb.getUser()%>  </p>
                            <%}
                        }
                    }
                }
            }%>
                                   
        </article>            
    
    </div><!-- @end #content -->
  </div><!-- @end #w -->

</div> <!-- class="pageSize"-->
</body>
    </body>
</html>
