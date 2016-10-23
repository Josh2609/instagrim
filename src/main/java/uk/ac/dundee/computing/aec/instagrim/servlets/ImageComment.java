/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;

@WebServlet(urlPatterns = {
    "/ImageComment",
    "/ImageComment/*",
    "/ImageComments",
    "/ImageComments/*",
})
@MultipartConfig

/**
 *
 * @author joshcorps
 */
public class ImageComment extends HttpServlet {
    private Cluster cluster;
    
    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException 
    {
        // TODO Auto-generated method stub
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        UUID picID = UUID.fromString(request.getParameter("picID"));
        String user = request.getParameter("username");
        String comment = request.getParameter("comment");
        String imageOwner = request.getParameter("picOwner");
        
        PicModel pm = new PicModel();
        pm.setCluster(cluster);
        pm.insertComment(picID, user, comment);
    
        response.sendRedirect("/Instagrim/Images/"+imageOwner);
    }
}
