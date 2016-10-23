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
public class ImageCommentBean {
    private UUID commentID;
    private UUID picID;
    private String user;
    private String comment;
    private Date commentDate;
    
    public ImageCommentBean (){
        commentID = null;
        picID = null;
        user = "";
        comment = "";
        commentDate = null;
    }
    
    public void setCommentID(UUID commentID)
    {   this.commentID = commentID;   }
    
    public UUID getCommentID()
    {   return commentID;   }
    
    public void setPicID(UUID picID)
    {   this.picID = picID;   }
    
    public UUID getPicID()
    {   return picID;   }
    
    public void setUser(String user)
    {   this.user = user;   }
    
    public String getUser()
    {   return user;   }
    
    public void setComment(String comment)
    {   this.comment = comment;   }
    
    public String getComment()
    {   return comment;   }
    
    public void setCommentDate(Date commentDate)
    {   this.commentDate = commentDate;   }
    
    public Date getCommentDate()
    {   return commentDate;   }
    
}

