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
import com.jobhound.datasource.Jobs;
import com.jobhound.interfaces.JobWebInterface;
import com.jobhound.services.ws.JobSearch;
import com.jobhound.activities.CustomListAdapter;

import roboguice.activity.RoboListActivity;
import roboguice.inject.InjectView;

public class SearchResults extends RoboListActivity implements OnClickListener {

	JobWebInterface job;

	@InjectView(R.id.back) Button back;
	@InjectView(android.R.id.list) ListView results;
	CustomListAdapter myAdapter;
	Jobs selectedJob;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		job = new JobSearch();
		populateList();
		
		back.setOnClickListener(this);
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
		job.findJobs(this);
	}

	public void receiveUserDetails(JSONArray jobResults) {
		// TODO Auto-generated method stub
		try {
			ArrayList<Jobs> JobList = new ArrayList<Jobs>();

			if (jobResults != null) {
				for (int i = 0; i < jobResults.length(); i++) {
					JSONArray jobParse = new JSONArray();
					String testresult = jobResults.get(i).toString();
					jobParse = new JSONArray(testresult);

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
				
				results.setAdapter( myAdapter = new CustomListAdapter(JobList, this, "SearchResults"));
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
		}
	}

}
