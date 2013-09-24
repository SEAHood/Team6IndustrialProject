package com.jobhound.services.dao;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.jobhound.datasource.DatabaseHelper;


public abstract class BaseService {
	
	
	private DatabaseHelper databaseHelper;
	
	
	public DatabaseHelper getHelper(Context context) 
	{
	    if (databaseHelper == null) {
	        databaseHelper =
	            OpenHelperManager.getHelper(context, DatabaseHelper.class);
	    }
	    return databaseHelper;
	}
	
	public void destroy()
	{
	    if (databaseHelper != null) {
	        OpenHelperManager.releaseHelper();
	        databaseHelper = null;
	    }
	}
	
	public void closeHelper()
	{
		databaseHelper.close();
	}
	
	
	
	
	protected abstract Context getContext();
}
