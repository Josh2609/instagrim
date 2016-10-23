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
import uk.ac.dundee.computing.aec.instagrim.stores.RelationshipBean;

/**
 *
 * @author joshcorps
 */
public class RelationshipModel {
    
    
    Cluster cluster;
    
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
    public java.util.LinkedList<RelationshipBean> getFollowers(String User) 
    {
        java.util.LinkedList<RelationshipBean> followerList = new java.util.LinkedList<>();
        Session session = cluster.connect("instagrim");
        try 
        {
            PreparedStatement ps = session.prepare("select * from relationshiplist where followed_user=? allow filtering");
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
                    RelationshipBean rb = new RelationshipBean();
                    rb.setRelationshipID(row.getUUID("relationshipid"));
                    rb.setFollowedUser(row.getString("followed_user"));
                    rb.setFollowedByUser(row.getString("followed_by_user"));
                    rb.setFollowDate(row.getDate("followed_date"));
                    followerList.add(rb);
                }
            }
        }
        catch (Exception e) 
        {      
            return null;
        }
        return followerList;
    }

    public java.util.LinkedList<RelationshipBean> getFollowing(String User) 
    {
        java.util.LinkedList<RelationshipBean> followingList = new java.util.LinkedList<>();
        Session session = cluster.connect("instagrim");
        try 
        {
            PreparedStatement ps = session.prepare("select * from relationshiplist where followed_by_user=? allow filtering");
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
                    RelationshipBean rb = new RelationshipBean();
                    rb.setRelationshipID(row.getUUID("relationshipid"));
                    rb.setFollowedUser(row.getString("followed_user"));
                    rb.setFollowedByUser(row.getString("followed_by_user"));
                    rb.setFollowDate(row.getDate("followed_date"));
                    followingList.add(rb);
                }
            }
        }
        catch (Exception e) 
        {      
            return null;
        }
        return followingList;
    }
    
    public boolean alreadyFollowed(String follower, String followed)
    {
        
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select * from relationshiplist where followed_user=? AND followed_by_user=?");
        BoundStatement bs = new BoundStatement(ps);
              ResultSet rs = null;
            rs = session.execute( // this is where the query is executed
                bs.bind( // here you are binding the 'boundStatement'
                        followed,follower));
        return !rs.isExhausted();             
    }
    
    public boolean addFollower(String follower, String followed)
    {
        UUID relationshipid = UUID.randomUUID();
        Date followDate = new Date();
        Session session = cluster.connect("instagrim");
            if(!alreadyFollowed(follower,followed))
            {
                PreparedStatement ps = session.prepare("insert into relationshiplist (relationshipid, followed_user, followed_by_user, followed_date) VALUES (?,?,?,?);");
                BoundStatement bs = new BoundStatement(ps);
                session.execute(bs.bind(relationshipid, followed, follower, followDate));
                return true;
            }else{
                return false;
            }
        
    }

}
