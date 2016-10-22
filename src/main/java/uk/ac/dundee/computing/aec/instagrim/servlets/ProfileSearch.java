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
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.ProfileBean;

@WebServlet(name = "ProfileSearch", urlPatterns = 
        {
            "/ProfileSearch",
            "/ProfileSearch/*"
        })
@MultipartConfig

/**
 *
 * @author joshcorps
 */
public class ProfileSearch extends HttpServlet {
    private Cluster cluster = null;
    
    @Override
    public void init(ServletConfig config) throws ServletException 
    {
        cluster = CassandraHosts.getCluster();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        User user = new User();
        user.setCluster(cluster);
        String args[] = Convertors.SplitRequestPath(request); // args[2] will be the searched users profile name
        String profileToGet = args[2];
        HttpSession session = request.getSession();
        ProfileBean profile = new ProfileBean();
        //request.setAttribute("Message", args[2]);
        
            try {
                profile = user.getUserProfile(profile, profileToGet);
            } catch (Exception ex) {
                Logger.getLogger(ProfileSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("ProfileBean", profile);
            RequestDispatcher rd = request.getRequestDispatcher("/profileSearch.jsp");
            rd.forward(request, response);

        
    }
    
}
