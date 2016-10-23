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

/**
 * Servlet implementation class Image
 */
@WebServlet(urlPatterns = {
    "/Post",
    "/Post/*",
    "/Posts",
    "/Posts/*",
})
@MultipartConfig

/**
 *
 * @author joshcorps
 */
public class Post extends HttpServlet {
    private Cluster cluster;
    private HashMap CommandsMap = new HashMap();
    
    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String args[] = Convertors.SplitRequestPath(request);
        String profileToGet = args[2];
        request.setAttribute("profileToGet", profileToGet); 
        PostModel pm = new PostModel();
        pm.setCluster(cluster);
        
        RelationshipModel rm = new RelationshipModel();
        rm.setCluster(cluster);
        HttpSession session = request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        if (rm.alreadyFollowed(lg.getUsername(),profileToGet))
            request.setAttribute("alreadyFollows", "true");
        else
            request.setAttribute("alreadyFollows", "false");
        
        java.util.LinkedList<PostBean> lsPosts = pm.getPosts(profileToGet);
        RequestDispatcher rd = request.getRequestDispatcher("/UsersPosts.jsp");
        request.setAttribute("Posts", lsPosts);
        request.setAttribute("Username", profileToGet);
        rd.forward(request, response);  
        
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
        
        String user = request.getParameter("username");
        String post = request.getParameter("post");
        PostModel pm = new PostModel();
        pm.setCluster(cluster);
        pm.insertPost(user, post);
        
        response.sendRedirect("/Instagrim/Posts/"+user);
    }
}
