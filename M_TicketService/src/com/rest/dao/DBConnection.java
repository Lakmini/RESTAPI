package com.rest.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.rest.data.User;

public class DBConnection {
/* this class handle DB related operations like Opening DB connection,
	Inserting records and Selecting records from Table.*/
	@SuppressWarnings("finally")
	public  Connection createConnection() throws Exception{
		/*method to create DB connection*/
		Connection connection=null;
		try{
			Class.forName(Constants.dbClass).newInstance();
			connection=(Connection) DriverManager.getConnection(Constants.dbUrl,Constants.dbUser,Constants.dbPwd);
			
		}catch(Exception ex)
		{
			throw ex;
		}

	   return connection;

			
		
	}
	












}

