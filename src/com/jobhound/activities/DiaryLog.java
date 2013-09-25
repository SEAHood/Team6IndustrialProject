package com.jobhound.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.jobhound.R;
import com.jobhound.datasource.DiaryEntry;
import com.jobhound.services.dao.DiaryDBImpl;
import com.jobhound.activities.CustomListAdapter;

import roboguice.activity.RoboListActivity;
import roboguice.inject.InjectView;

public class DiaryLog extends RoboListActivity implements OnClickListener {

	DiaryDBImpl diary_list;

	@InjectView(R.id.back) Button back;
	@InjectView(android.R.id.list) ListView results;
	CustomListAdapter myAdapter;
	DiaryEntry diary_entry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		diary_list = new DiaryDBImpl(this);
		populateList();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent openSearchMenu = new Intent("android.intent.action.SEARCH_MENU");
    	finish();
		startActivity(openSearchMenu);
		overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
	}

	public void populateList() {
		
		ArrayList<DiaryEntry> diaryLog = new ArrayList<DiaryEntry>();
		diaryLog = diary_list.getList();
		results.setAdapter( myAdapter = new CustomListAdapter(null,diaryLog, this, "DiaryLog"));
	}

	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		diary_entry = (DiaryEntry) myAdapter.getItem(position);
		
		Bundle jobBundle = new Bundle();
		jobBundle.putString("date", diary_entry.getDate());
		jobBundle.putString("action", diary_entry.getAction());
		jobBundle.putString("employer", diary_entry.getEmployer());
		jobBundle.putString("further_action", diary_entry.getFurtherAction());
		jobBundle.putString("further_date", diary_entry.getFDate());
		jobBundle.putString("suitability", diary_entry.getSuitability());
		jobBundle.putString("reason", diary_entry.getReason());
		jobBundle.putString("comments", diary_entry.getComments());
		
		Intent viewJob = new Intent("android.intent.action.SINGLE_JOB_RESULT");
		viewJob.putExtras(jobBundle);
		startActivity(viewJob);
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
		}
	}
}