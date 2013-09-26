package com.jobhound.activities;

import roboguice.activity.RoboActivity;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jobhound.R;


public class DiaryView extends RoboActivity implements OnClickListener{
	
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
			
		}
	}
}

