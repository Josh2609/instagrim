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

@WebServlet(name = "EditProfile", urlPatterns = 
        {
            "/EditProfile",
            "/EditProfile/*",
        })
@MultipartConfig
/**
 *
 * @author joshcorps
 */
public class EditProfile extends HttpServlet {
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
        String args[] = Convertors.SplitRequestPath(request);
        String profileToGet = args[2];
        request.setAttribute("profileToGet", profileToGet);   
        HttpSession session = request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        ProfileBean profile = new ProfileBean();
        
        // if user is not logged in as the profile they try to edit then they are redirected to the users profile
        if (!lg.getUsername().equals(profileToGet))
        {
           RequestDispatcher rd = request.getRequestDispatcher("/Profile/" + profileToGet);
           rd.forward(request, response);
           return;
        }
        
        if(!lg.getlogedin())
        {
            RequestDispatcher rd = request.getRequestDispatcher("/Login");
            request.setAttribute("Message", "not Logged In");
            rd.forward(request, response);
            return;
        } else {

            try {
                profile = user.getUserProfile(profile, profileToGet);
            } catch (Exception ex) {
                Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("ProfileBean", profile);
            RequestDispatcher rd = request.getRequestDispatcher("/editProfile.jsp");
            rd.forward(request, response);
        }
    }
    
     /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username=request.getParameter("username");
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String email=request.getParameter("email");
        
        ProfileBean profile = new ProfileBean();
        profile.setUsername(username);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setEmail(email);
        
        User us=new User();
        us.setCluster(cluster);
     
        if(firstName.isEmpty())
        {
            RequestDispatcher rd = request.getRequestDispatcher("/editProfile.jsp");
            request.setAttribute("Message", "First Name can not be empty");
            request.setAttribute("username", username);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            rd.forward(request, response);
            return;
        }
        
        if(lastName.isEmpty())
        {
            RequestDispatcher rd = request.getRequestDispatcher("/editProfile.jsp");
            request.setAttribute("Message", "Last Name can not be empty");
            request.setAttribute("username", username);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            rd.forward(request, response);
            return;
        }
        
        if(email.isEmpty())
        {
            RequestDispatcher rd = request.getRequestDispatcher("/editProfile.jsp");
            request.setAttribute("Message", "Email can not be empty");
            request.setAttribute("username", username);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            rd.forward(request, response);
            return;
        }
        
             
        if (us.editProfile(username, email, firstName, lastName))
        {     
            response.sendRedirect("/Instagrim/Profile/" + username);
        } else {
            return;
        }
    }
 
}
