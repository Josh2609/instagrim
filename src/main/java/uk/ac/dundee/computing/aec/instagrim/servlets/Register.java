/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.ProfileBean;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    Cluster cluster=null;
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }



    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
       RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
       rd.forward(request, response);
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
        String password=request.getParameter("password");
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String email=request.getParameter("email");
        String repeatPassword = request.getParameter("repeatPassword");
        
        ProfileBean profile = new ProfileBean();
        profile.setUsername(username);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setEmail(email);
        
        if(username.length() < 4)
        {
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
            request.setAttribute("Message", "User Name must be at least 4 characters long");
            request.setAttribute("username", username);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            rd.forward(request, response);
            return;
        }
         
        if(firstName.isEmpty())
        {
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
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
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
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
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
            request.setAttribute("Message", "Email can not be empty");
            request.setAttribute("username", username);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            rd.forward(request, response);
            return;
        }
        
        if(password.length() < 6)
        {
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
            request.setAttribute("Message", "Password must be at least 6 characters long");
            request.setAttribute("username", username);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            rd.forward(request, response);
            return;
        }
        
        // **EDIT**
        for(int i=0; i<username.length(); i++) 
        {
            char c = username.charAt(i);
            if(!(c >= '0' && c <= '9') && !(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z'))
            {
                RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
                request.setAttribute("Message", "Username can only contain upper and lower case characters and numbers");
                rd.forward(request, response);
                return;
            }
        }
        
        if (!(password.equals(repeatPassword)))
        {
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
            request.setAttribute("Message", "Passwords do not match");
            request.setAttribute("username", username);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            rd.forward(request, response);
            return;
        }
        
        User us=new User();
        us.setCluster(cluster);
             
        if (us.RegisterUser(username, password, profile))
        {     
            response.sendRedirect("/Instagrim");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
            request.setAttribute("Message", "Username already taken");
            request.setAttribute("username", username);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            rd.forward(request, response);
            return;
        }
       
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
