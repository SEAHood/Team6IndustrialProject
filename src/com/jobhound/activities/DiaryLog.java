package com.jobhound.activities;

import com.jobhound.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import roboguice.activity.RoboActivity;
import roboguice.activity.RoboListActivity;

public class DiaryLog extends RoboListActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary_layout);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
