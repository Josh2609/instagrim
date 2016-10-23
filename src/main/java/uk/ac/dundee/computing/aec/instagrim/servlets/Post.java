package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.models.PostModel;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
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
    
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String args[] = Convertors.SplitRequestPath(request);
        String profileToGet = args[2];
        request.setAttribute("profileToGet", profileToGet); 
        PostModel pm = new PostModel();
        pm.setCluster(cluster);
        java.util.LinkedList<PostBean> lsPosts = pm.getPosts(profileToGet);
        RequestDispatcher rd = request.getRequestDispatcher("/UsersPosts.jsp");
        request.setAttribute("Posts", lsPosts);
        request.setAttribute("Username", profileToGet);
        rd.forward(request, response);  
        
    }
    
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
