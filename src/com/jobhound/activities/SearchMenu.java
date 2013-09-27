package com.jobhound.activities;

import com.jobhound.R;
import com.jobhound.interfaces.ProfileDBInterface;
import com.jobhound.services.dao.ProfileDBImpl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class SearchMenu extends RoboActivity implements OnClickListener {
	
	ProfileDBInterface profileDB;
	
	@InjectView(R.id.profileSearch) Button profileSearch;
	@InjectView(R.id.customSearch) Button customSearch;
	@InjectView(R.id.back) Button back;
	
	@InjectView(R.id.btn_Home_Menu) Button homeBtn;
	@InjectView(R.id.btnProfile) Button profileBtn;
	@InjectView(R.id.btnDiary) Button diaryBtn;
	@InjectView(R.id.btnSearch) Button searchBtn;

	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent openSearchMenu = new Intent("android.intent.action.MAIN_MENU");
    	finish();
		startActivity(openSearchMenu);
		overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_menu);
		profileDB = new ProfileDBImpl(this);
		
		profileSearch.setOnClickListener(this);
		back.setOnClickListener(this);
		
		//NAV BAR ---------------------
		homeBtn.setOnClickListener(this);
		profileBtn.setOnClickListener(this);
		diaryBtn.setOnClickListener(this);
		searchBtn.setOnClickListener(this);
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		

		switch (v.getId()){

		case R.id.profileSearch:
			
			boolean profileCheck = profileDB.checkIfProfileExists();
			
			if(profileCheck==true)
			{
				Intent openSearchResults = new Intent("android.intent.action.SEARCH_RESULTS");
				finish();
				startActivity(openSearchResults);
				overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Please Update Your Profile Page!", Toast.LENGTH_LONG).show();
			}
			
			break;
			
		case R.id.back:
			Intent openSearchMenu = new Intent("android.intent.action.MAIN_MENU");
	    	finish();
			startActivity(openSearchMenu);
			overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
			
			break;
//NAV BAR ------------------------
		case R.id.btn_Home_Menu:
			Intent openHome = new Intent("android.intent.action.MAIN_MENU");
	    	finish();
			startActivity(openHome);
			overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
			break;
			
		case R.id.btnProfile:
			Intent openProfile = new Intent("android.intent.action.PROFILE_PAGE");
	    	finish();
			startActivity(openProfile);
			overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
			break;
			
		case R.id.btnSearch:
			Intent openSearch = new Intent("android.intent.action.SEARCH_MENU");
	    	finish();
			startActivity(openSearch);
			overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
			break;
			
		case R.id.btnDiary:
			Intent openDiary = new Intent("android.intent.action.DIARY_LAYOUT");
	    	finish();
			startActivity(openDiary);
			overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
			break;
			
	}

}
}
