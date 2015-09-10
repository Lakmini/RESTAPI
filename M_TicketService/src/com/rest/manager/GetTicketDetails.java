package com.rest.manager;

import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.rest.dao.DBAccess;
import com.rest.dao.DBConnection;
import com.rest.data.Ticket;

public class GetTicketDetails {
	
	ArrayList<Ticket> tickets=new ArrayList<Ticket>();
	
public ArrayList<Ticket> getTicketDetails(String regId) throws Exception
{
	DBAccess access=new DBAccess();
	DBConnection dbcon=new DBConnection();
	Connection connection=dbcon.createConnection();
	
	tickets=access.getTicketDetails(connection, regId);
	if(!tickets.isEmpty())
	{
		return tickets;
	}
	else
	{
		return null;
	}
	
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
