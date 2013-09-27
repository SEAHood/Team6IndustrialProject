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
import android.widget.Toast;

import com.jobhound.R;
import com.jobhound.datasource.Jobs;
import com.jobhound.datasource.Profile;
import com.jobhound.interfaces.JobWebInterface;
import com.jobhound.interfaces.ProfileDBInterface;
import com.jobhound.services.dao.ProfileDBImpl;
import com.jobhound.services.ws.JobSearch;
import com.jobhound.activities.CustomListAdapter;

import roboguice.activity.RoboListActivity;
import roboguice.inject.InjectView;

public class SearchResults extends RoboListActivity implements OnClickListener {

	JobWebInterface job;

	@InjectView(R.id.back) Button back;
	@InjectView(android.R.id.list) ListView results;
	
	@InjectView(R.id.btn_Home_Menu) Button homeBtn;
	@InjectView(R.id.btnProfile) Button profileBtn;
	@InjectView(R.id.btnDiary) Button diaryBtn;
	@InjectView(R.id.btnSearch) Button searchBtn;
	
	CustomListAdapter myAdapter;
	Jobs selectedJob;
	ProfileDBInterface profileDB;
	String job1;
	String job2;
	String job3;
	String[] jobSearch = new String[3];
	ArrayList<Jobs> JobList = new ArrayList<Jobs>();
	boolean searched1 = false;
	boolean searched2 = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		profileDB = new ProfileDBImpl(this);
		job = new JobSearch();
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
		Intent openSearchMenu = new Intent("android.intent.action.SEARCH_MENU");
    	finish();
		startActivity(openSearchMenu);
		overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
	}



	public void populateList() {
		
		Profile profile = profileDB.getProfile();

		
		jobSearch[0] = profile.getJob1();
		jobSearch[1] = profile.getJob2();
		jobSearch[2] = profile.getJob3();
		
		if (jobSearch[0]!=null)
		{
			job.findJobs(this, jobSearch[0]);
		}
			results.setAdapter( myAdapter = new CustomListAdapter(JobList,null, this, "SearchResults"));
		
		
	}

	public void receiveUserDetails(JSONArray jobResults) {
		// TODO Auto-generated method stub
		try {

			if (jobResults != null) {
				for (int i = 0; i < jobResults.length(); i++) {
					JSONArray jobParse = new JSONArray();
					String testresult = jobResults.get(i).toString();
					jobParse = new JSONArray(testresult);
					if (jobParse!=null)
					{
						for (int x = 0; x < jobParse.length(); x++) {
							JSONObject jobPost = new JSONObject();
							jobPost = jobParse.getJSONObject(x);
							Jobs jobs = new Jobs(jobPost.getString("source"),
								jobPost.getString("description"),
								jobPost.getString("title"),
								jobPost.getString("url"));
							JobList.add(jobs);
						}
					}
				}	
				
				if(JobList.size()==0)
				{
					Toast.makeText(getApplicationContext(), "No Jobs found for "+ jobSearch[0]+ " Please change your profile/search settings!", Toast.LENGTH_LONG).show();
				}
				results.setAdapter(myAdapter = new CustomListAdapter(JobList,null, this, "SearchResults"));
			
			
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		selectedJob = (Jobs) myAdapter.getItem(position);
		
		Bundle jobBundle = new Bundle();
		jobBundle.putString("source", selectedJob.getSource());
		jobBundle.putString("title", selectedJob.getTitle());
		jobBundle.putString("description", selectedJob.getDescription());
		jobBundle.putString("link", selectedJob.getLink());
		
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
			Intent openSearchMenu = new Intent("android.intent.action.SEARCH_MENU");
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
