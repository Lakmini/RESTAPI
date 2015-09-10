package com.rest.manager;

import java.sql.Date;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.mysql.jdbc.Connection;
import com.owlike.genson.stream.JsonStreamException;
import com.rest.dao.DBAccess;
import com.rest.dao.DBConnection;
import com.rest.data.Train;
public class SearchTrain {
	ArrayList<Train> trainList=new ArrayList<Train>();

	public ArrayList<Train> searchTrain(String date,String source,String destination) throws Exception
	{
		DBAccess access=new DBAccess();
		DBConnection dbcon=new DBConnection();
		Connection connection=dbcon.createConnection();
		
		trainList=access.searchTrain(connection, date, source, destination);
		if(trainList!=null)
		{
			
			return trainList;
		}
		
		else
		{
			return null;
		}
		
		
		
		
		
		
		
	}
	

	}
	



