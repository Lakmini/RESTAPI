package com.rest.manager;

import com.rest.controller.Controller;
import com.rest.dao.DBAccess;
import com.rest.dao.DBConnection;
import com.rest.data.User;
//import java.sql.Connection;

import com.mysql.jdbc.Connection;

public class DoLogin {
	
	
	
	
	User user=new User();
	
	public void setObject(User userobj)
	{
		user.setPhoneNumber(userobj.getPhoneNumber());
		user.setPassword(userobj.getPassword());
		
	}
	public  String checklogin() throws Exception
	{
		 String response = "";
	        if(checkCredentials()){
	            response = Utility.constructJSON("login",true);
	        }else{
	            response = Utility.constructJSON("login", false, "Incorrect Email or Password");
	        }
	    return response;      
        
		
	}
	
	private boolean checkCredentials() throws Exception{
		DBConnection dbcon=new DBConnection();
		Connection connection=dbcon.createConnection();
		DBAccess access=new DBAccess();
       
        boolean result = false;
        if(Utility.isNotNull(user.getPhoneNumber()) && Utility.isNotNull(user.getPassword())){
            try {
                result =  access.getuser(connection,user.getPhoneNumber(),user.getPassword());
              
            } catch (Exception e) {
               
                result = false;
            }
        }else{
           
            result = false;
        }
 
        return result;
    }
	
	
	
	

}
