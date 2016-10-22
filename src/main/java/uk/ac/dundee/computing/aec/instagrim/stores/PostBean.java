/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author joshcorps
 */
public class PostBean {
    UUID postID = null;
    String user = "";
    String post = "";
    Date postDate = null;
    
    public void setPostID(UUID postID)
    {   this.postID = postID;   }
    
    public UUID getPostID()
    {   return postID;   }
    
    public void setUser(String user)
    {   this.user = user;   }
    
    public String getUser()
    {   return user;   }
    
    public void setPost(String post)
    {   this.post = post;   }
    
    public String getPost()
    {   return post;   }
    
    public void setPostDate(Date postDate)
    {   this.postDate = postDate;   }
    
    public Date getPostDate()
    {   return postDate;   }
    
}
