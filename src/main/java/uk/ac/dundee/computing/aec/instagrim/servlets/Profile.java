/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import uk.ac.dundee.computing.aec.instagrim.models.RelationshipModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.ProfileBean;

@WebServlet(name = "Profile", urlPatterns = 
        {
            "/Profile",
            "/Profile/*",
        })
@MultipartConfig

/**
 *
 * @author joshcorps
 */
public class Profile extends HttpServlet {
    private Cluster cluster = null;
    
    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException 
    {
        cluster = CassandraHosts.getCluster();
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        User user = new User();
        user.setCluster(cluster);
        String args[] = Convertors.SplitRequestPath(request);
        String profileToGet = args[2];
        request.setAttribute("profileToGet", profileToGet);   
        HttpSession session = request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        ProfileBean profile = new ProfileBean();
        
        RelationshipModel rm = new RelationshipModel();
        rm.setCluster(cluster);
        if (rm.alreadyFollowed(lg.getUsername(),profileToGet))
            request.setAttribute("alreadyFollows", "true");
        else
            request.setAttribute("alreadyFollows", "false");
        
        try {
            profile = user.getUserProfile(profile, profileToGet);
        } catch (Exception ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("ProfileBean", profile);
        RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
        rd.forward(request, response);  
    }   
}
