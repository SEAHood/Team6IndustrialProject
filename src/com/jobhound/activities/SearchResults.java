package com.jobhound.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.jobhound.R;
import com.jobhound.datasource.Jobs;
import com.jobhound.interfaces.JobWebInterface;
import com.jobhound.services.ws.JobSearch;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class SearchResults extends RoboActivity {
	
	JobWebInterface job;
	
	@InjectView(R.id.back) Button back;
	@InjectView(R.id.searchResultList) ListView results;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		job = new JobSearch();
		populateList();
	}

	public void populateList()
	{
		job.findJobs(this);
	}

	public void receiveUserDetails(JSONArray jobResults) {
		// TODO Auto-generated method stub
		try{
			ArrayList<Jobs> JobList =  new ArrayList<Jobs>();
			
			if (jobResults != null) 
			{ 
				   for (int i=0;i<jobResults.length();i++)
				   { 
					   JSONArray jobParse = new JSONArray();
					   String testresult = jobResults.get(i).toString();
					   jobParse = new JSONArray(testresult);
					   for(int x=0;x<jobParse.length();x++)
					   {
						   JSONObject jobPost = new JSONObject();
						   jobPost = jobParse.getJSONObject(x);
						   Jobs jobs =new Jobs(jobPost.getString("source"),jobPost.getString("description"),jobPost.getString("url"));
						   JobList.add(jobs);
					   }
					   
					   
				   }
				   ArrayAdapter<Jobs> arrayAdapter =      
					         new ArrayAdapter<Jobs>(this,android.R.layout.simple_list_item_1, JobList);
					         results.setAdapter(arrayAdapter); 
			}
			
	} catch (JSONException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	}

}
