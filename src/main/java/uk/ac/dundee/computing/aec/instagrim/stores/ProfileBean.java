/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

/**
 *
 * @author joshcorps
 */
public class ProfileBean {
    
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    
    public ProfileBean()
    {
        String username = null;
        String firstName = null;
        String lastName = null;
        String email = null;
    }

    
    public String getUsername()
    {   return username;   }
    
    public void setUsername(String username)
    {   this.username = username;  }
    
    public String getFirstName()
    {   return firstName;   }
    
    public void setFirstName(String firstName)
    {   this.firstName = firstName;  }

    public String getLastName()
    {   return lastName;   }
    
    public void setLastName(String lastName)
    {   this.lastName = lastName;  }

    public String getEmail()
    {   return email;   }
    
    public void setEmail(String email)
    {   this.email = email;  }
}
