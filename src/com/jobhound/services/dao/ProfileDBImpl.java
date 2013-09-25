package com.jobhound.services.dao;

import java.sql.SQLException;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.jobhound.datasource.Profile;
import com.jobhound.interfaces.ProfileDBInterface;

public class ProfileDBImpl extends BaseService implements ProfileDBInterface {

	private Context context;
	private RuntimeExceptionDao<Profile,Integer> profileDAO;
	
	//Constructor to set the context and the DAO helper object 
	public ProfileDBImpl(Context theContext) {
		
		context = theContext;
		profileDAO = getHelper(context).getRuntimeExceptionDao(Profile.class);
		
	}
	
	
	
	@Override
	public Profile getProfile() {
		// TODO Auto-generated method stub
		
		Profile profile = null;
		PreparedQuery<Profile> pq;
		try
		{
			QueryBuilder<Profile, Integer> queryBuilder = profileDAO.queryBuilder();
			queryBuilder.where().isNotNull(Profile.ID);
			pq = queryBuilder.prepare();
			profile = profileDAO.queryForFirst(pq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return profile;
	}

	@Override
	public void updateProfile(Profile profile) {
		// TODO Auto-generated method stub
		try {
			UpdateBuilder<Profile, Integer> updateProfile = profileDAO.updateBuilder();
			updateProfile.updateColumnValue(Profile.JOB1, profile.getJob1());
			updateProfile.updateColumnValue(Profile.JOB2, profile.getJob2());
			updateProfile.updateColumnValue(Profile.JOB3, profile.getJob3());
			updateProfile.updateColumnValue(Profile.PARTFULL, profile.getPartFull());
			updateProfile.updateColumnValue(Profile.PROGRESS, profile.getProgress());
			updateProfile.update();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getProgress() {
		// TODO Auto-generated method stub
		
		Profile profile = null;
		PreparedQuery<Profile> pq;
		try
		{
			QueryBuilder<Profile, Integer> queryBuilder = profileDAO.queryBuilder();
			queryBuilder.where().isNotNull(Profile.ID);
			pq = queryBuilder.prepare();
			profile = profileDAO.queryForFirst(pq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profile.getProgress();
	}

	@Override
	protected Context getContext() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void saveProfile(Profile profile) {
		// TODO Auto-generated method stub
		profileDAO.create(new Profile(profile.getJob1(),profile.getJob2(),profile.getJob3(),
				profile.getPartFull(),profile.getProgress()));
	}



	@Override
	public boolean checkIfProfileExists() {
		// TODO Auto-generated method stub
			
			Profile profile = null;
			PreparedQuery<Profile> pq;
			try
			{
				QueryBuilder<Profile, Integer> queryBuilder = profileDAO.queryBuilder();
				queryBuilder.where().isNotNull(Profile.ID);
				pq = queryBuilder.prepare();
				profile = profileDAO.queryForFirst(pq);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if (profile!=null)
			{
				return true;
			}
			else
			{
				return false;
			}
		
		
	}

}
