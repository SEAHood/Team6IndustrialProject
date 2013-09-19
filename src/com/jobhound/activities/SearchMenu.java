package com.jobhound.activities;

import com.jobhound.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class SearchMenu extends RoboActivity {
	
	@InjectView(R.id.profileSearch) Button profileSearch;
	@InjectView(R.id.customSearch) Button customSearch;

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent openSearchMenu = new Intent("android.intent.action.MAIN_MENU_LAYOUT");
    	finish();
		startActivity(openSearchMenu);
		overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_menu);
		
	}

}
