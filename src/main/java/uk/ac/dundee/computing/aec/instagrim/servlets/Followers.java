/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.PostModel;
import uk.ac.dundee.computing.aec.instagrim.models.RelationshipModel;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.PostBean;
import uk.ac.dundee.computing.aec.instagrim.stores.RelationshipBean;

@WebServlet(urlPatterns = {
    "/Follower",
    "/Follower/*",
    "/Followers",
    "/Followers/*",
    "/Follow",
    "/Follow/*",
})
@MultipartConfig

/**
 *
 * @author joshcorps
 */
public class Followers extends HttpServlet {
    private Cluster cluster;
    private HashMap CommandsMap = new HashMap();
    
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String args[] = Convertors.SplitRequestPath(request);
        String profileToGet = args[2];
        request.setAttribute("profileToGet", profileToGet); 
        
        RelationshipModel rm = new RelationshipModel();
        rm.setCluster(cluster);
        HttpSession session = request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        if (rm.alreadyFollowed(lg.getUsername(),profileToGet))
            request.setAttribute("alreadyFollows", "true");
        else
            request.setAttribute("alreadyFollows", "false");
        
        java.util.LinkedList<RelationshipBean> lsFollower = rm.getFollowers(profileToGet);
        RequestDispatcher rd = request.getRequestDispatcher("/followers.jsp");
        request.setAttribute("Followers", lsFollower);
        request.setAttribute("Username", profileToGet);
        rd.forward(request, response);  
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String follower = request.getParameter("followerUser");
        String following = request.getParameter("followedUser");
        
        RelationshipModel rm = new RelationshipModel();
        rm.setCluster(cluster);
        rm.addFollower(follower, following);
        response.sendRedirect("/Instagrim/Followers/"+following);
    }
    
}
