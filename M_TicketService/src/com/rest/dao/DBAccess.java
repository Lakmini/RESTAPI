package com.rest.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.rest.data.Ticket;
import com.rest.data.Train;
import com.rest.data.User;

public class DBAccess {
	
	
	
public boolean getuser(Connection connection,String phone_num,String pw) throws Exception
{
	User user=new User();

	if(connection!=null)
	{
		// Statement statement=(Statement) connection.createStatement();
		 String sql="select* from user where phone_num=? and password= md5(?)";
		 PreparedStatement statement=(PreparedStatement) connection.prepareStatement(sql);
		 statement.setString(1, phone_num);
		 statement.setString(2, pw);
		 ResultSet resultSet = statement.executeQuery();
		 if(resultSet.next())
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	}
	
	else
	{
		System.out.println("Check your db connection!!!!");
		return false;
	}
}
	




public  boolean insertUser(Connection con,String phone_num, String email, String pwd, String regId) throws SQLException, Exception {
	
	
	
	boolean insertStatus = false;
	if(con!=null)
	{
	try {
		
		
		String insertTableSQL = "INSERT INTO user"
				+ "(reg_Id,email,phone_num,password) VALUES"
				+ "(?,?,?,md5(?))";	
		PreparedStatement statement=(PreparedStatement) con.prepareStatement(insertTableSQL);
		statement.setString(1, regId);
		statement.setString(2, email);
		statement.setString(3, phone_num);
		statement.setString(4, pwd);
		System.out.println(statement.toString());
		int records=statement.executeUpdate();
		System.out.println("--------------records--------------------"+records);
		//When record is successfully inserted
		if (records > 0) {
			insertStatus = true;
		}
	} catch (SQLException sqle) {
		sqle.printStackTrace();
		throw sqle;
	} catch (Exception e) {
		e.printStackTrace();
		
		
		throw e;
	}
	}
	else
	{
		System.out.println("Check your db connection!!!!");
	}
	return insertStatus;
}

public ArrayList<Train> searchTrain(Connection con,String  date_string,String source,String destination) throws SQLException, ParseException
{
	String journey_id = null;
	Time arrival_time;
	Time depature_time;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	java.util.Date date=df.parse(date_string) ;
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());  
	
	int i=0;
	if(con!=null)
	{
		//String sql="select type from train where train.train_id=? and train.train_name=?";
		 ArrayList<Train> trainList = new ArrayList<Train>();
		 
		 String sql="select distinct journy_id  from journy,station_plan where journy.date=? and journy.plan_id=station_plan.plan_id and  ((select  arrival_time from station_plan where station_plan.station=? and station_plan.plan_id=journy.plan_id)<(select arrival_time from station_plan where station_plan.station=? and station_plan.plan_id=journy.plan_id ))";
		 PreparedStatement statement=(PreparedStatement) con.prepareStatement(sql);
		 statement.setDate(1, sqlDate);
		 statement.setString(2, source);
		 statement.setString(3, destination);
		 ResultSet resultSet = statement.executeQuery();
		 
		 
		 
		 while (resultSet.next()) {
			 
				 journey_id = resultSet.getString("journy_id");
				 
				 Train train=new Train();

				 String traindetails="select train_name,type,Available_Seat_FClass,Available_Seats_SClass,arrival_time,depature_time,cost_fclass,cost_sclass from train,journy,station_plan where journy_id=?and journy.train_id=train.train_id and journy.plan_id=station_plan.plan_id and station=?";
				 PreparedStatement stmt=(PreparedStatement) con.prepareStatement(traindetails);
				 stmt.setString(1, journey_id);
				 stmt.setString(2, source);
				 ResultSet rs=stmt.executeQuery();
				 
				 while(rs.next())
				 {
					 
					 train.setName(rs.getString("train_name"));
					 train.setType(rs.getString("type"));
					 train.setAvailable_seat_first_class(rs.getInt("Available_seat_FClass"));
					 train.setAvailabale_seat_second_class(rs.getInt("Available_seats_SClass"));
					 train.setArrival_time(rs.getTime("arrival_time").toString());
					 train.setDepature_time(rs.getTime("depature_time").toString());
					 train.setFclass_cost_source(rs.getInt("cost_fclass"));
					 train.setSclass_cost_source(rs.getInt("cost_sclass"));
					 				
				 

					
				 }
				 
				 
				 trainList.add(train);
				
				
				
			}
		 return trainList; 
	}
	else
	{
		return null;
	}
	
}


public ArrayList<Ticket> getTicketDetails(Connection con,String regId) throws SQLException
{
	ArrayList<Ticket>tickets=new ArrayList<Ticket>();
	if(con!=null)
	{
		
		
		//String sql="select ticket_id,journey_id,source,destination,fare,due_date,class from ticket where regId=? and due_date>NOW()";
		String sql="select  ticket_id,journey_id,source,destination,fare,due_date,class,train_name,arrival_time from ticket ,train,station_plan,journy where regId=? and due_date>NOW() and ticket.journey_id=journy.journy_id and journy.train_id=train.train_id and journy.plan_id=station_plan.plan_id and ticket.source=station_plan.station";
		PreparedStatement stmt=(PreparedStatement) con.prepareStatement(sql);
		stmt.setString(1, regId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next())
		{
			Ticket ticket=new Ticket();
			ticket.setTicket_id(rs.getString("ticket_id"));
			ticket.setDate(rs.getString("due_date").toString());
			ticket.setJourney_id(rs.getString("journey_id"));
			ticket.setSource(rs.getString("source"));
			ticket.setDestination(rs.getString("destination"));
			ticket.setFare(rs.getInt("fare"));
			ticket.setClass_name(rs.getString("class"));
			ticket.setArrival_time(rs.getString("arrival_time").toString());
			ticket.setTrain_name(rs.getString("train_name"));
			tickets.add(ticket);
		}
	}
	return tickets;
	
}


public String ticketBooking(Connection con,ArrayList<Ticket> ticketList,String reg_Id) throws SQLException
{
	int available_seat = 0;
	String journy_id = null;
	int cost = 0;
	PreparedStatement stmt;
	boolean status=false;
	
	if(con!=null)
	{
		System.out.println(ticketList.get(0).getClass_name());
		if(ticketList.get(0).getClass_name().equals("First"))
		{
			
			String sql="select Available_Seat_FClass,journy_id,cost_fclass from journy,train,station_plan where train_name=? and train.train_id=journy.train_id and journy.plan_id=station_plan.plan_id and station=?  and arrival_time=? and date=?";
		    stmt=(PreparedStatement) con.prepareStatement(sql);
		}
		else
		{
			String sql="select Available_Seats_SClass,journy_id,cost_sclass from journy,train,station_plan where train_name=? and train.train_id=journy.train_id and journy.plan_id=station_plan.plan_id and station=?  and arrival_time=? and date=?";
			stmt=(PreparedStatement) con.prepareStatement(sql);
		}
		
		
		stmt.setString(1, ticketList.get(0).getTrain_name());
		stmt.setString(2, ticketList.get(0).getSource());
		stmt.setString(3, ticketList.get(0).getArrival_time());
		stmt.setString(4, ticketList.get(0).getDate());
		ResultSet rs = stmt.executeQuery();
		
		if(rs==null)
		{
			return "invalid data";
		}
		

		
		while(rs.next())
		{
			
			if(ticketList.get(0).getClass_name().equals("First"))
			{
				 available_seat= rs.getInt("Available_Seat_FClass");
				 cost=rs.getInt("cost_fclass");
				 System.out.println(available_seat);
				
				 
			}
			else
			{
				 available_seat= rs.getInt("Available_Seats_SClass");
				 cost=rs.getInt("cost_sclass");
			}
			journy_id=rs.getString("journy_id");
			
           
		}
		

		if(available_seat>ticketList.size())
		{
			
			String sqlupdate = null;
			if(ticketList.get(0).getClass_name().equals("First"))
			{
				sqlupdate="update journy set Available_Seat_FClass=? where journy_id=?";	
			}
			else if(ticketList.get(0).getClass_name().equals("Second"))
			{
				sqlupdate="update journy set Available_Seats_SClass=? where journy_id=?";
			}
			PreparedStatement stmtupdate=(PreparedStatement) con.prepareStatement(sqlupdate);
			stmtupdate.setInt(1, (available_seat-ticketList.size()));
			stmtupdate.setString(2, journy_id);
			stmtupdate.executeUpdate();
			
			
			String sqlInsert="INSERT INTO ticket"
					+ "(regId,journey_id, source,destination,fare,due_date,class) VALUES"
					+ "(?,?,?,?,?,?,?)";	
			PreparedStatement st=(PreparedStatement) con.prepareStatement(sqlInsert);
			st.setString(1, reg_Id);
			st.setString(2, journy_id);
			st.setString(3, ticketList.get(0).getSource());
			st.setString(4, ticketList.get(0).getDestination());
			st.setInt(5, cost);
			st.setString(6, ticketList.get(0).getDate());
			st.setString(7, ticketList.get(0).getClass_name());
			
			for(int i=0;i<ticketList.size();i++)
			{
				int records=st.executeUpdate();
				if(records>0)
				{
					status=true;
				}
			}
			
			
			//return status;
			if(status)
			{
				
				return "success";// successfully enter the purchased ticket data to the db
			}
			else
			{
				return "server error";// not entered data in to the db
			}
			
		}
		else
		{
			
		    return "no sufficient seats";
		}
		
		
		
	}
	else
	{
		return "connection failure";// this return when db connection is going  down
	}
	
}






//end of class
}
