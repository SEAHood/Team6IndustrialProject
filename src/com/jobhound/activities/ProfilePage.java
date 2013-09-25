package com.jobhound.activities;

import com.jobhound.R;
import com.jobhound.datasource.Profile;
import com.jobhound.interfaces.ProfileDBInterface;
import com.jobhound.services.dao.ProfileDBImpl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class ProfilePage extends RoboActivity implements OnClickListener {
	
	@InjectView(R.id.titleProfile) TextView titleProfile;
	@InjectView(R.id.job1) EditText job1;
	@InjectView(R.id.jobText1) TextView jobText1;
	@InjectView(R.id.job2) EditText job2;
	@InjectView(R.id.jobText2) TextView jobText2;
	@InjectView(R.id.job3) EditText job3;
	@InjectView(R.id.jobText3) TextView jobText3;
	@InjectView(R.id.contractType) TextView contractType;
	@InjectView(R.id.spinner1) Spinner spinner1;
	@InjectView(R.id.pb_horizontal) ProgressBar pbHorizontal;
	@InjectView(R.id.jsaProgress) TextView jsaPreogress;
	@InjectView(R.id.tv_progress_horizontal) TextView tv_progress_horizontal;
	@InjectView(R.id.rl_progress_bar_set) RelativeLayout rl_progress_bar_set;
	@InjectView(R.id.btn_save) Button btn_save;

	ProfileDBInterface profileDB;
	
	
	private int progress = 0;  
	boolean existingProfile=false;

	
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent openProfile = new Intent("android.intent.action.MAIN_MENU");
    	finish();
		startActivity(openProfile);
		overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_page);

		profileDB = new ProfileDBImpl(this);
		
		Profile checkProfile = profileDB.getProfile();
		
		if (checkProfile!=null)
		{
			progress = checkProfile.getProgress();
			postProgress(progress);
			job1.setText(checkProfile.getJob1());
			job2.setText(checkProfile.getJob2());
			job3.setText(checkProfile.getJob3());
			existingProfile=true;
		}
		
		Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.contract_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter);	
		
		btn_save.setOnClickListener(this);
	}
	
	

          private void postProgress(int progress) {  
              pbHorizontal.setProgress(progress);  
              
              /* update secondary progress of horizontal progress bar */  
        		                if(progress == 0) {  
                  pbHorizontal.setSecondaryProgress(0);  
              } else {  
                  pbHorizontal.setSecondaryProgress(progress + 5);  
              }  
          }
         

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()){

		case R.id.btn_save:
			
			if(existingProfile==true)
			{
				Profile updateProfile = 
						new Profile(job1.getText().toString(),job2.getText().toString(),job3.getText().toString(),spinner1.toString(),progress);
				
				profileDB.updateProfile(updateProfile);
				Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_LONG).show();
			}
			else
			{
				Profile newProfile = 
						new Profile(job1.getText().toString(),job2.getText().toString(),job3.getText().toString(),spinner1.toString(),progress);
				
				profileDB.saveProfile(newProfile);
				Toast.makeText(getApplicationContext(), "Profile Saved", Toast.LENGTH_LONG).show();
			}
			
			
			Intent openProfile = new Intent("android.intent.action.MAIN_MENU");
	    	finish();
			startActivity(openProfile);
			overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
			
			break;
		
		
		}
	}

}