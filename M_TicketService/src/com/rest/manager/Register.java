package com.rest.manager;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.rest.dao.DBAccess;
import com.rest.dao.DBConnection;
import com.rest.data.User;

public class Register {


	User user=new User();
	
	public void setObject(User userobj)
	{
		user.setPhoneNumber(userobj.getPhoneNumber());
		user.setPassword(userobj.getPassword());
		user.setEmail(userobj.getEmail());
		user.setRegId(userobj.getRegId());
		
	}
	
	public  String register() throws Exception
	{
		
		String response = "";
		
		int retCode = registerUser(user.getEmail(), user.getPassword(), user.getPhoneNumber(),user.getRegId());
		System.out.println("Inside register():retCode:"+retCode);
		if(retCode == 0){
			response = Utility.constructJSON("register",true);
		}else if(retCode == 1){
			response = Utility.constructJSON("register",false, "You are already registered");
		}else if(retCode == 2){
			response = Utility.constructJSON("register",false, "Special Characters are not allowed in Username and Password");
		}else if(retCode == 3){
			response = Utility.constructJSON("register",false, "Error occured");
		}
		return response;
		
        
		
	}
	private int registerUser(String email, String pwd, String phoneNumber,String regId) throws Exception{
		
		System.out.println(phoneNumber+" inside register user function"+pwd);
		DBConnection dbcon=new DBConnection();
		Connection connection=dbcon.createConnection();
		DBAccess access=new DBAccess();
		int result=-1;
		
		if(Utility.isNotNull(phoneNumber) && Utility.isNotNull(pwd)){
	
			//////////////////////////////////System.out.println(phoneNumber+"abcd "+pwd);
			try {
				boolean check=access.insertUser(connection,phoneNumber, email, pwd,regId);
				System.out.println("check*********"+check);
				if(check){
					System.out.println("RegisterUSer if");
					result = 0;
				}
			    } catch(SQLException sqle){
				System.out.println("RegisterUSer catch sql :"+sqle.getErrorCode());
				//When Primary key violation occurs that means user is already registered
				if(sqle.getErrorCode()==1054){
					System.out.println(result);
					result = 1;
				} 
				//When special characters are used in name,username or password
				else if(sqle.getErrorCode() == 1064){
					System.out.println(sqle.getErrorCode());
					result = 2;
				}
			}
			catch (Exception e) {
				
				System.out.println("Inside checkCredentials catch e ");
				result = 3;
			}
			
		}else{
			System.out.println("Inside checkCredentials else");
			result = 3;
		}
			
		return result;
	}
	
	
	
	
}
