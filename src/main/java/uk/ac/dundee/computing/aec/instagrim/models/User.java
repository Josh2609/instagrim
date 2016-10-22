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
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.ProfileBean;

/**
 *
 * @author Administrator
 */
public class User {
    Cluster cluster;
    public User(){
        
    }
    
    public boolean RegisterUser(String username, String Password, ProfileBean profile){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select * from userprofiles where login =?");
        
        ResultSet rs;
        
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        //We are assuming this always works.  Also a transaction would be good here !
        if (rs.isExhausted())
        {
            ps = session.prepare("insert into userprofiles (login,password,first_name,last_name,email) Values(?,?,?,?,?)");
            boundStatement = new BoundStatement(ps);
            rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username,EncodedPassword,profile.getFirstName(),profile.getLastName(),profile.getEmail()));
            return true;
        } else {
            return false;
        }
    }
    
    public boolean IsValidUser(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
        
    
    return false;  
    }
    
    public ProfileBean getUserProfile(ProfileBean profile, String user) throws Exception
    {
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select * from userprofiles where login=?");
        
        ResultSet rs = null;
        
        BoundStatement bs = new BoundStatement(ps);
        rs = session.execute(bs.bind(user));
        Row row = rs.one(); //**EDIT**//
        //set values from returned data
        profile.setUsername(row.getString("login"));
        profile.setFirstName(row.getString("first_name"));
        profile.setLastName(row.getString("last_name"));
        profile.setEmail(row.getString("email"));
        return profile;
    }
    
    public boolean editProfile(String username, String email, String firstName, String lastName)
    {
        Session session = cluster.connect("instagrim");
        PreparedStatement ps;
        BoundStatement bs;
                
        ps = session.prepare("update userprofiles set email=? WHERE login=?");
        bs = new BoundStatement(ps);
        session.execute(bs.bind(email, username));
        
        ps = session.prepare("update userprofiles set first_name=? WHERE login=?");
        bs = new BoundStatement(ps);
        session.execute(bs.bind(firstName, username));
        
        ps = session.prepare("update userprofiles set last_name=? WHERE login=?");
        bs = new BoundStatement(ps);
        session.execute(bs.bind(lastName, username));
        
        return true;
    }
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
}
