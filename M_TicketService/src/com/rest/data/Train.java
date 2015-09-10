package com.rest.data;

import java.sql.Time;

public class Train {

	String ID;
	String type;
	String name;
	String arrival_time;
	String depature_time;
	int total_seat_first_class;
	int total_seat_second_class;
	int available_seat_first_class;
	int availabale_seat_second_class;
	int fclass_cost_source;
	int sclass_cost_source;
	int fclass_cost_destination;
	int sclass_cost_destination;
	
	public int getFclass_cost_source() {
		return fclass_cost_source;
	}
	public void setFclass_cost_source(int fclass_cost_source) {
		this.fclass_cost_source = fclass_cost_source;
	}
	public int getSclass_cost_source() {
		return sclass_cost_source;
	}
	public void setSclass_cost_source(int sclass_cost_source) {
		this.sclass_cost_source = sclass_cost_source;
	}
	public int getFclass_cost_destination() {
		return fclass_cost_destination;
	}
	public void setFclass_cost_destination(int fclass_cost_destination) {
		this.fclass_cost_destination = fclass_cost_destination;
	}
	public int getSclass_cost_destination() {
		return sclass_cost_destination;
	}
	public void setSclass_cost_destination(int sclass_cost_destination) {
		this.sclass_cost_destination = sclass_cost_destination;
	}
	public String getDepature_time() {
		return depature_time;
	}
	public void setDepature_time(String string) {
		this.depature_time = string;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String string) {
		this.arrival_time = string;
	}
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal_seat_first_class() {
		return total_seat_first_class;
	}
	public void setTotal_seat_first_class(int total_seat_first_class) {
		this.total_seat_first_class = total_seat_first_class;
	}
	public int getTotal_seat_second_class() {
		return total_seat_second_class;
	}
	public void setTotal_seat_second_class(int total_seat_second_class) {
		this.total_seat_second_class = total_seat_second_class;
	}
	public int getAvailable_seat_first_class() {
		return available_seat_first_class;
	}
	public void setAvailable_seat_first_class(int available_seat_first_class) {
		this.available_seat_first_class = available_seat_first_class;
	}
	public int getAvailabale_seat_second_class() {
		return availabale_seat_second_class;
	}
	public void setAvailabale_seat_second_class(int availabale_seat_second_class) {
		this.availabale_seat_second_class = availabale_seat_second_class;
	}
	
	
	
	
	
}
