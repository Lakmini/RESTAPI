package com.rest.manager;

import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.rest.dao.DBAccess;
import com.rest.dao.DBConnection;
import com.rest.data.Ticket;

public class ReserveTicket {
	
	
	
	public  String reserveTickets(ArrayList<Ticket>tickets,String reg_Id) throws Exception
	{
		DBConnection dbcon=new DBConnection();
		Connection connection=dbcon.createConnection();
		DBAccess access=new DBAccess();
		String status=null;
		
		if(!tickets.isEmpty())
		{
			status=access.ticketBooking(connection, tickets, reg_Id);
		}
		
		return status;
		
		
	}
	
	
	
	
	
	
	
	

}
