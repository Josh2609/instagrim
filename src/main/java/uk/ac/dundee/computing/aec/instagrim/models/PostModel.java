/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.util.Date;
import java.util.UUID;
import uk.ac.dundee.computing.aec.instagrim.stores.PostBean;

/**
 *
 * @author joshcorps
 */
public class PostModel {
    
    Cluster cluster;
    
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
    public void insertPost(String user, String post) {
        Session session = cluster.connect("instagrim");
        try 
        {
            UUID postID = UUID.randomUUID();
            Date dateAdded = new Date();     
            PreparedStatement ps = session.prepare("insert into userpostlist (postid, user, post, post_added) values (?, ?, ?, ?)");
            BoundStatement bs = new BoundStatement(ps);
            session.execute(bs.bind(postID, user, post, dateAdded));
            
            session.close();
     
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }        
    }
    
    public java.util.LinkedList<PostBean> getPosts(String User) 
     {
        java.util.LinkedList<PostBean> postList = new java.util.LinkedList<>();
        Session session = cluster.connect("instagrim");
        try 
        {
            PreparedStatement ps = session.prepare("select * from userpostlist where user=? allow filtering");
            BoundStatement bs = new BoundStatement(ps);
            ResultSet rs = null;
            rs = session.execute( // this is where the query is executed
                bs.bind( // here you are binding the 'boundStatement'
                        User));
            if(rs.isExhausted())
            {
                return null;
            }else{
                for (Row row : rs) 
                {
                    PostBean postBean = new PostBean();
                    postBean.setPostID(row.getUUID("postid"));
                    postBean.setUser(row.getString("user"));
                    postBean.setPost(row.getString("post"));
                    postBean.setPostDate(row.getDate("post_added"));
                    postList.add(postBean);
                }
            }
        }
        catch (Exception e) 
        {      
            return null;
        }
        return postList;
    }
}
