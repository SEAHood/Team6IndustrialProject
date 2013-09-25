package com.jobhound.interfaces;

import com.jobhound.datasource.Profile;

public interface ProfileDBInterface {
	
	public boolean checkIfProfileExists();
	public void saveProfile(Profile profile);
	public Profile getProfile();
	public void updateProfile(Profile profile);
	public int getProgress();

}
