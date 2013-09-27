package com.jobhound.activities;

import com.jobhound.R;




import com.jobhound.datasource.DiaryEntry;
import com.jobhound.interfaces.DiaryDBInterface;
import com.jobhound.services.dao.DiaryDBImpl;

import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;




public class DiaryView extends RoboFragmentActivity implements OnClickListener{
	
	
	@InjectView(R.id.datePicker1) Button dateButton;
	@InjectView(R.id.datePicker2) Button dateSelector;
	@InjectView(R.id.SubmitBtn) Button submitBtn;
	@InjectView(R.id.CommentsText) EditText comments;
	@InjectView(R.id.EmployerInput) EditText employer;
	@InjectView(R.id.ActionSpinner) Spinner actions;
	@InjectView(R.id.furtherAction_spinner) Spinner furtherActions;
	@InjectView(R.id.ReasonText) EditText reason;
	
	String suitability;
	String parent = "DiaryView";
	DialogFragment newFragment;
	DiaryDBInterface diaryDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary_view);
		
		diaryDB = new DiaryDBImpl(this);
		
		Bundle diaryEntry = getIntent().getExtras();
		
		dateButton.setText(diaryEntry.getString("date"));
		employer.setText(diaryEntry.getString("employer"));
		comments.setText(diaryEntry.getString("comments"));
		reason.setText(diaryEntry.getString("reason"));
		
		actions.setSelection(diaryEntry.getInt("action"));
		furtherActions.setSelection(diaryEntry.getInt("further_action"));
		dateButton.setOnClickListener(this);
		dateSelector.setOnClickListener(this);
		submitBtn.setOnClickListener(this);
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent openDiary = new Intent("android.intent.action.DIARY_LAYOUT");
	   	finish();
		startActivity(openDiary);
		overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()){

		case R.id.SubmitBtn:
			
			DiaryEntry editEntry = new DiaryEntry();
			
			editEntry.setDate(dateButton.getText().toString());
			editEntry.setAction(actions.getSelectedItemPosition());
			editEntry.setEmployer(employer.getText().toString());
			editEntry.setFurtherAction(furtherActions.getSelectedItemPosition());
			editEntry.setReason(reason.getText().toString());
			editEntry.setComments(comments.getText().toString());
			editEntry.setFDate(dateSelector.getText().toString());
			editEntry.setSuitability(suitability);
		
			
			diaryDB.editEntry(editEntry);
			
			Intent openDiary = new Intent("android.intent.action.DIARY_LAYOUT");
	    	finish();
			startActivity(openDiary);
			 overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
			
			break;
			
		case R.id.datePicker1:
			Bundle parentBundle = new Bundle();
			parentBundle.putString("parent", parent);
			
			newFragment = new DatePickerFragment();
			newFragment.setArguments(parentBundle);
		    newFragment.show(getSupportFragmentManager(), "datePicker");
			
		    break;
		    
		case R.id.datePicker2:
			Bundle parentBundle2 = new Bundle();
			parentBundle2.putString("parent", "DiaryViewB");
			
			newFragment = new DatePickerFragment();
			newFragment.setArguments(parentBundle2);
		    newFragment.show(getSupportFragmentManager(), "datePicker");
			
		    break;
		    
		case R.id.radioNo:
			
			suitability = "No";
			
			break;
			
		case R.id.radioYes:
			
			suitability = "Yes";
			
			break;
			
		case R.id.radioUnsure:
			
			suitability = "Unsure";
			
			break;
			
		}
		
		
	}
	
	public void setDateA(String Date)
	{
		dateButton.setText(Date);
	}
	public void setDateB(String Date)
	{
		dateSelector.setText(Date);
	}
	
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		View v = getCurrentFocus();
	    if (v instanceof EditText) {
	        View w = getCurrentFocus();
	        int scrcoords[] = new int[2];
	        w.getLocationOnScreen(scrcoords);
	        float x = event.getRawX() + w.getLeft() - scrcoords[0];
	        float y = event.getRawY() + w.getTop() - scrcoords[1];

	        Log.d("Activity", "Touch event "+event.getRawX()+","+event.getRawY()+" "+x+","+y+" rect "+w.getLeft()+","+w.getTop()+","+w.getRight()+","+w.getBottom()+" coords "+scrcoords[0]+","+scrcoords[1]);
	        if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom()) ) { 

	            InputMethodManager imm = (InputMethodManager)getSystemService(DiaryView.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
	        }
	    }
	    	
		
		return super.dispatchTouchEvent(event);
	}
	
}

