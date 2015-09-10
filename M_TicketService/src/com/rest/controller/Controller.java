package com.rest.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.json.simple.JSONObject;

import com.rest.dao.DBAccess;
import com.rest.dao.DBConnection;
import com.rest.data.Ticket;
import com.rest.data.Train;
import com.rest.data.User;
import com.rest.manager.DoLogin;
import com.rest.manager.GetTicketDetails;
import com.rest.manager.Register;
import com.rest.manager.ReserveTicket;
import com.rest.manager.SearchTrain;
import com.mysql.jdbc.Connection;

@Path("/controller")
public class Controller {
	
    

	@POST
	
	@Path("/login")
	public String loginRequestHandler(@FormParam("phoneNumber") String phoneNumber, @FormParam("Password") String Password) throws Exception
	{
		User user = new User();
		DoLogin a = new DoLogin();
		user.setPhoneNumber(phoneNumber);
		user.setPassword(Password);
		System.out.println(phoneNumber+" "+Password);
		a.setObject(user);
        String b=a.checklogin();
       return b;
       
		
	}
	
	
	
    @POST
    @Path("/register")
	public String registerRequestHandler(@FormParam("phoneNumber") String phoneNumber, @FormParam("Password") String Password,@FormParam("email") String email,@FormParam("regId")String regId) throws Exception
	{
		User user = new User();
		Register registerUser = new Register();
		user.setPhoneNumber(phoneNumber);
		user.setPassword(Password);
		user.setEmail(email);
		user.setRegId(regId);
		registerUser.setObject(user);
        String response=registerUser.register();
      
		return response;
       
		
	}
    @POST
	@Path("/submit")
	public ArrayList<Train> getTrainDetails(@FormParam("journeyDate") String journeyDate, @FormParam("source") String source,@FormParam("destination") String destination) throws Exception
	{
    	
		SearchTrain searchtrain=new SearchTrain();
		
		return searchtrain.searchTrain(journeyDate, source, destination);
	
		
	}
    
    
    
    @POST
    @Path("/ticketdata")
    public ArrayList<Ticket> getTicketDetails(@FormParam("regId") String regId) throws Exception
    {
    	GetTicketDetails ticketDetails=new GetTicketDetails();
    	return ticketDetails.getTicketDetails(regId);
    }
    
    @POST
    @Path("/reserve")	
    public String reserveTickets(@FormParam("date")String travel_date,@FormParam("source")String source,@FormParam("destination")String destination,@FormParam("train_name") String name,@FormParam("class")String class_name,@FormParam("time")String time,@FormParam("count")String count,@FormParam("reg_Id")String reg_Id) throws Exception
    {
    	ArrayList<Ticket> ticketList=new ArrayList<Ticket>();
    	int j=Integer.parseInt(count);
    	ReserveTicket booking=new ReserveTicket();
    	
    	for(int i=0;i<j;i++)
    	{
    		Ticket ticket=new Ticket();
    		ticket.setTrain_name(name);
    		ticket.setDate(travel_date);
    		ticket.setSource(source);
    		ticket.setDestination(destination);
    		ticket.setClass_name(class_name);
    		ticket.setArrival_time(time);
    		
    		ticketList.add(ticket);
    		
    	}
    	return booking.reserveTickets(ticketList, reg_Id);
    }
    
   

}
