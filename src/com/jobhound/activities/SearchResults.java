package com.jobhound.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		job = new JobSearch();
		populateList();
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
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
