package com.example.m_ticket.test;

import com.m_ticket.activity.GridActivity;
import com.m_ticket.activity.MainActivity;
import com.robotium.solo.Solo;


import android.test.ActivityInstrumentationTestCase2;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class GridButtonTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;

	
	public GridButtonTest() {
		super("com.example.m_ticket",MainActivity.class);
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo=new Solo(getInstrumentation(),getActivity());
	}

	public void testbtn()
	{
		
		 solo.enterText(0,"0719804454");
	     solo.enterText(1,"12345");
	     solo.clickOnButton("LOGIN");
	     solo.waitForActivity("com.m_ticket.Activity.GridActivity", 3000);
	     solo.assertCurrentActivity("The activity should be Grid", "GridActivity");

	     

	  
	     solo.clickInList(3);
	     solo.waitForActivity("com.m_ticket.Activity.TrainScheduleActivity",3000);
	     solo.assertCurrentActivity("trainscheduleactivity", "TrainScheduleActivity");
	     solo.enterText(0, "2015-09-14");
	     solo.clickOnButton("Cancel");
	     solo.typeText(1, "G");
	     
	     solo.waitForText("Galle");
         solo.clickOnText("Galle");
	     
	     solo.typeText(2, "K");
	     solo.waitForText("Kaluthara");
	     solo.clickOnText("Kaluthara");
	     solo.clickOnButton("Search");
	     solo.waitForActivity("com.m_ticket.Activity.DynamicTrainListActivity",3000);
	     solo.assertCurrentActivity("trainscheduleactivity", "DynamicTrainListActivity");
	     
	     solo.clickInList(0);
	     solo.waitForActivity("com.m_ticket.Activity.OnlineReservationActivity",3000);
	     solo.assertCurrentActivity("trainscheduleactivity", "OnlineReservationActivity");
	     solo.enterText(3, "1");
	     solo.clickOnButton("Pay");
	     solo.waitForActivity("com.m_ticket.Activity.GridActivity",3000);
	     solo.assertCurrentActivity("trainscheduleactivity", "GridActivity");
	     
	     
	     
	     

		
	}
}
