package com.jobhound.activities;

import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;



public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	
	String parent;
	String []monthText= {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	DatePickerDialog dialog;
	



@Override
public Dialog onCreateDialog(Bundle savedInstanceState)
{
	
	parent = getArguments().getString("parent");
	
	// Use the current date as the default date in the picker
	final Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH);
	int day = c.get(Calendar.DAY_OF_MONTH);
	
	// restrict date picker from showing dates in the future.
	dialog = new DatePickerDialog(getActivity(), this, year, month, day);
	
	

	

	//if(parent.equals("RegistrationActivity"))
	//{
	//	dialog.getDatePicker().setMaxDate(new Date().getTime());
	//}
	
	return dialog;

}





@Override
public void onDateSet(DatePicker DatePickerFragment, int year, int month, int day) {
	// TODO Auto-generated method stub
//	String monthFormat = "";
//	if (month<9)
//	{
	//	monthFormat="0";
//	}
	
	String date = Integer.toString(day) + " " +monthText[month] + " " + Integer.toString(year);
	
	if(parent.equals("DiaryView"))
	{
		// USE BELOW CODE TO CALL PARENT ACTIVTY METHODS ECT....
		((DiaryView) getActivity()).setDateA(date);
	}
	if(parent.equals("DiaryViewB"))
	{
		// USE BELOW CODE TO CALL PARENT ACTIVTY METHODS ECT....
		((DiaryView) getActivity()).setDateB(date);
	}
	
}






}
