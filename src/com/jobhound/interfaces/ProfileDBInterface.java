package com.jobhound.interfaces;

import com.jobhound.datasource.Profile;

public interface ProfileDBInterface {
	
	public Profile getProfile();
	public void updateProfile();
	public int getProgress();

}
