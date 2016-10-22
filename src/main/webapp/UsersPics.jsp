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
  
  <div id="profileOutline">
    <div id="content" class="clearfix">
      <div id="userphoto"><img src="../images/avatar.png" alt="default avatar"></div>
      <h1 style="color: black">Josh</h1>

      <nav id="profiletabs">
        <ul class="clearfix">
          <li><a href="#pictures">Pictures</a></li>
          <li><a href="#activity" class="sel">Activity</a></li>
          <li><a href="#friends">Friends</a></li>
          <li><a href="#settings">Settings</a></li>
        </ul>
      </nav>
      
      <section id="pictures" class="hidden">
       <article>
            <h1>Your Pics</h1>
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
      </section>
      
      <section id="activity">
        <p>Most recent actions:</p>
        
        <p class="activity">@10:15PM - Submitted a news article</p>
        
        <p class="activity">@9:50PM - Submitted a news article</p>
        
        <p class="activity">@8:15PM - Posted a comment</p>
        
        <p class="activity">@4:30PM - Added <strong>someusername</strong> as a friend</p>
        
        <p class="activity">@12:30PM - Submitted a news article</p>
      </section>
      
      <section id="friends" class="hidden">
        <p>Friends list:</p>
        
        <ul id="friendslist" class="clearfix">
          <li><a href="#"><img src="../images/avatar.png" width="22" height="22"> Username</a></li>
          <li><a href="#"><img src="../images/avatar.png" width="22" height="22"> SomeGuy123</a></li>
          <li><a href="#"><img src="../images/avatar.png" width="22" height="22"> PurpleGiraffe</a></li>
        </ul>
      </section>
      
      <section id="settings" class="hidden">
        <p>Edit your user settings:</p>
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
                
        <p class="setting"><span>User Name <img src="../images/edit.png" alt="*Edit*"></span> <%=lg.getUsername() %></p>
        
        <p class="setting"><span>Email Address <img src="../images/edit.png" alt="*Edit*"></span><%=email %></p>
        
        <p class="setting"><span>Profile Status <img src="../images/edit.png" alt="*Edit*"></span> Public</p>
        
        <p class="setting"><span>Update Frequency <img src="../images/edit.png" alt="*Edit*"></span> Weekly</p>
        
        <p class="setting"><span>Connected Accounts <img src="../images/edit.png" alt="*Edit*"></span> None</p>
      </section>
    </div><!-- @end #content -->
  </div><!-- @end #w -->
<script type="text/javascript">
$(function(){
  $('#profiletabs ul li a').on('click', function(e){
    e.preventDefault();
    var newcontent = $(this).attr('href');
    
    $('#profiletabs ul li a').removeClass('sel');
    $(this).addClass('sel');
    
    $('#content section').each(function(){
      if(!$(this).hasClass('hidden')) { $(this).addClass('hidden'); }
    });
    
    $(newcontent).removeClass('hidden');
  });
});
</script>
</div> <!-- class="pageSize"-->
</body>
    </body>
</html>
