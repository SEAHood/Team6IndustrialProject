package com.jobhound.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.jobhound.R;
import com.jobhound.datasource.DiaryEntry;
import com.jobhound.interfaces.DiaryDBInterface;
import com.jobhound.services.dao.DiaryDBImpl;
import com.jobhound.activities.CustomListAdapter;

import roboguice.activity.RoboListActivity;
import roboguice.inject.InjectView;

public class DiaryLog extends RoboListActivity implements OnClickListener {

	DiaryDBInterface diaryDB;


	@InjectView(R.id.back) Button back;
	@InjectView(android.R.id.list) ListView results;
	CustomListAdapter myAdapter;
	DiaryEntry diary_entry;
	
	@InjectView(R.id.btn_Home_Menu) Button homeBtn;
	@InjectView(R.id.btnProfile) Button profileBtn;
	@InjectView(R.id.btnDiary) Button diaryBtn;
	@InjectView(R.id.btnSearch) Button searchBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary_layout);
		diaryDB = new DiaryDBImpl(this);
		populateList();
		
		back.setOnClickListener(this);
		//NAV BAR ---------------------
		homeBtn.setOnClickListener(this);
		profileBtn.setOnClickListener(this);
		diaryBtn.setOnClickListener(this);
		searchBtn.setOnClickListener(this);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent openMainMenu = new Intent("android.intent.action.MAIN_MENU");
    	finish();
		startActivity(openMainMenu);
		overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
	}

	public void populateList() {
		
		ArrayList<DiaryEntry> diaryLog = new ArrayList<DiaryEntry>();
		diaryLog = diaryDB.getList();
		results.setAdapter( myAdapter = new CustomListAdapter(null,diaryLog, this, "DiaryLog"));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		diary_entry = (DiaryEntry) myAdapter.getItem(position);
		
		Bundle diaryBundle = new Bundle();
		diaryBundle.putString("date", diary_entry.getDate());
		diaryBundle.putInt("action", diary_entry.getAction());
		diaryBundle.putString("employer", diary_entry.getEmployer());
		diaryBundle.putInt("further_action", diary_entry.getFurtherAction());
		diaryBundle.putString("further_date", diary_entry.getFDate());
		diaryBundle.putString("suitability", diary_entry.getSuitability());
		diaryBundle.putString("reason", diary_entry.getReason());
		diaryBundle.putString("comments", diary_entry.getComments());
		
		Intent viewEntry = new Intent("android.intent.action.DIARY_VIEW");
		viewEntry.putExtras(diaryBundle);
		startActivity(viewEntry);
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()){

		case R.id.back:
			Intent openMainMenu = new Intent("android.intent.action.MAIN_MENU");
	    	finish();
			startActivity(openMainMenu);
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
					
						break;
			
		}
	}
	
	
	
}