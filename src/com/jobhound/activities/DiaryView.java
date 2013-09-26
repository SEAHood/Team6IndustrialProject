package com.jobhound.activities;

import com.jobhound.R;


import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;




public class DiaryView extends RoboFragmentActivity implements OnClickListener{
	
	
	@InjectView(R.id.datePicker1) Button date1;
	@InjectView(R.id.datePicker2) Button date2;
	
	String parent = "DiaryView";
	DialogFragment newFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary_view);
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent openDiary = new Intent("android.intent.action.DIARY_LAYOUT");
	   	finish();
		startActivity(openDiary);
		overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()){

		case R.id.Submit:
			Intent openDiary = new Intent("android.intent.action.DIARY_LAYOUT");
	    	finish();
			startActivity(openDiary);
			 overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
			
			break;
			
		case R.id.datePicker1:
			Bundle parentBundle = new Bundle();
			parentBundle.putString("parent", parent);
			
			newFragment = new DatePickerFragment();
			newFragment.setArguments(parentBundle);
		    newFragment.show(getSupportFragmentManager(), "datePicker");
			
		    break;
		    
		case R.id.datePicker2:
			Bundle parentBundle2 = new Bundle();
			parentBundle2.putString("parent", "DiaryViewB");
			
			newFragment = new DatePickerFragment();
			newFragment.setArguments(parentBundle2);
		    newFragment.show(getSupportFragmentManager(), "datePicker");
			
		    break;
		}
		
		
	}
	
	public void setDateA(String Date)
	{
		date1.setText(Date);
	}
	public void setDateB(String Date)
	{
		date1.setText(Date);
	}
}

