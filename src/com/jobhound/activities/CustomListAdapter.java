package com.jobhound.activities;

import java.util.ArrayList;


import com.jobhound.datasource.DiaryEntry;
import com.jobhound.datasource.Jobs;
import com.jobhound.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {
	
	private ArrayList<Jobs> _data0;
	private ArrayList<DiaryEntry> _data1;
	
	Context _c;
    String senderParent;
    
    CustomListAdapter(ArrayList<Jobs> data0,ArrayList<DiaryEntry> data1, Context c, String parent)
    {
    	senderParent = parent;
    	
    	if (senderParent.equals("SearchResults"))
    	{
    		_data0 = data0;
    	}
    	else if (senderParent.equals("DiaryLog"))
    	{
    		_data1 = data1;
    	}
    	_c = c;
    }
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (senderParent.equals("SearchResults"))
    	{
			return _data0.size();  
    	}
		else if (senderParent.equals("DiaryLog"))
    	{
    		return _data1.size();
    	}
		else
		{
			return 0;
		}
		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (senderParent.equals("SearchResults"))
    	{
			return _data0.get(position);   
    	}
		else if (senderParent.equals("DiaryLog"))
    	{
			return _data1.get(position);   
    	}
		else
		{
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	    View v = convertView;
        if (v == null)
        {
           LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           v = vi.inflate(R.layout.custom_list_item, null);
        }

          TextView name = (TextView)v.findViewById(R.id.txtView_Name);
          TextView extraInfo = (TextView)v.findViewById(R.id.txtView_ExtraInfo);

          if (senderParent.equals("SearchResults"))
      	{
        	  Jobs job = _data0.get(position);
        	  if(job.getTitle().equals("N/A"))
        	  {
        		  name.setText(job.getSource());
        	  }
        	  else 
        	  {
        		  name.setText("Title: "+job.getTitle());
        	  }
        	  extraInfo.setText(job.getDescription());
      	}
          else if (senderParent.equals("DiaryLog"))
      	{
        	  DiaryEntry entry = _data1.get(position);
        	  
        		  name.setText(entry.getDate());
        	 
        		  String [] actions = new String[7];
        		  actions[0] = "Used JobHound App";
        		  actions[1] = "Interview";
        		  actions[2] = "Applied Online";
        		  actions[3] = "Applied in Person";
        		  actions[4] = "Inquired in Store";
        		  actions[5] = "Training";
        		  actions[6] = "Went to the Jobcentre";
        		  
        	  extraInfo.setText(actions[entry.getAction()]);
      	}
		
		
		return v;
	}

}
