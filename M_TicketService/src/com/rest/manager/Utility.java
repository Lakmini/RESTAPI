package com.rest.manager;

import org.json.simple.JSONObject;

import com.owlike.genson.stream.JsonStreamException;

public class Utility {
	/*Utility class has utility methods to perform Null check, contruct JSON etc.*/
	
	/*null check method*/
	public static boolean isNotNull(String txt)
	{
		return txt!=null&&txt.trim().length()>=0?true:false;
	}
	/*method to construct JSON*/
	public static String constructJSON(String tag,boolean status){
		
		JSONObject object=new JSONObject();
		try{
			object.put("tag", tag);
			object.put("status",new Boolean(status));
			
			
		}catch(JsonStreamException e)
		{
			
		}
		return object.toString();
	}
    /* method to construct JSON with error message*/
	public static String constructJSON(String tag,boolean status,String error_msg)
	{
		 JSONObject object = new JSONObject();
	        try {
	            object.put("tag", tag);
	            object.put("status", new Boolean(status));
	            object.put("error_msg", error_msg);
	        } catch (JsonStreamException e) {
	            // TODO Auto-generated catch block
	        }
	        return object.toString();
	    }
	}

	
	
	
	
	

