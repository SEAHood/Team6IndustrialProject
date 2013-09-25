package com.jobhound.activities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jobhound.R;
import com.jobhound.datasource.DiaryEntries;
import com.jobhound.interfaces.DiaryDBInterface;
import com.jobhound.services.dao.DiaryDBImpl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class SingleSearchResult extends RoboActivity implements OnClickListener {

	@InjectView(R.id.sourceContent) TextView SourceText;
	@InjectView(R.id.titleContent) TextView TitleText;
	@InjectView(R.id.descriptionContent) TextView DescriptionText;
	@InjectView(R.id.applyButton) Button Apply;
	@InjectView(R.id.backButtonResult) Button Back;

	String link;
	
	DiaryDBInterface diaryDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_job_result);
		diaryDB = new DiaryDBImpl(this);
		
		Bundle jobResult = getIntent().getExtras();
		
		SourceText.setText(jobResult.getString("source"));
		TitleText.setText(jobResult.getString("title"));
		DescriptionText.setText(jobResult.getString("description"));
		link=(jobResult.getString("link"));
		
		Back.setOnClickListener(this);
		Apply.setOnClickListener(this);
	}
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent openSearchResults = new Intent("android.intent.action.SEARCH_RESULTS");
    	finish();
		startActivity(openSearchResults);
		overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId())
		{

			case R.id.backButtonResult:
				Intent openSearchResults = new Intent("android.intent.action.SEARCH_RESULTS");
		    	finish();
				startActivity(openSearchResults);
				overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
				break;
			
			case R.id.applyButton:
				
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        switch (which){
				        case DialogInterface.BUTTON_POSITIVE:
				            //Yes button clicked
				        	// Code to add to diary entry go here
				        	
				        	Uri uriUrl = Uri.parse(link);
					        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
					        startActivity(launchBrowser);
					        
					        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
							String dateLogged = sdf.format(new Date());
					        
					        DiaryEntries applyJob = new DiaryEntries();
					        applyJob.setDate(dateLogged);
					        applyJob.setAction("Looked into applying for Job via JobHound.");
					        applyJob.setEmployer(SourceText.getText().toString());
					        applyJob.setComments(DescriptionText.getText().toString());
					        
					        diaryDB.addEntry(applyJob);
					        
							break;
							
				        case DialogInterface.BUTTON_NEGATIVE:
				            //No button clicked
				        	//Do nothing...
				            break;
				        }	    
				    }
				};
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("You are now leaving the JobHound app, are you sure you want to continue?").setPositiveButton("Yes", dialogClickListener)
				    .setNegativeButton("No", dialogClickListener).show();		
				break;
			}
				
				
				
				
		
		}


	}

