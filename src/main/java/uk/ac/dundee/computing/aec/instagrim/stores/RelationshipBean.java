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
public class RelationshipBean {
    private UUID relationshipID;
    private String followedUser;
    private String followedByUser;
    private Date followDate;
    
    public RelationshipBean()
    {
        relationshipID = null;
        followedUser = "";
        followedByUser = "";
        followDate = null;
    }
    
    public void setRelationshipID(UUID relationshipID)
    {   this.relationshipID = relationshipID;   }
    
    public UUID getRelationshipID()
    {   return relationshipID;   }
    
    public void setFollowedUser(String followedUser)
    {   this.followedUser = followedUser;   }
    
    public String getFollowedUser()
    {   return followedUser;   }
    
    public void setFollowedByUser(String followedByUser)
    {   this.followedByUser = followedByUser;   }
    
    public String getFollowedByUser()
    {   return followedByUser;   }
    
    public void setFollowDate(Date followDate)
    {   this.followDate = followDate;   }
    
    public Date getFollowDate()
    {   return followDate;   }
    
}

