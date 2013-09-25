package com.jobhound.services.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.jobhound.datasource.DiaryEntry;
import com.jobhound.interfaces.DiaryDBInterface;

public class DiaryDBImpl extends BaseService implements DiaryDBInterface {

	public Context context;
	private RuntimeExceptionDao<DiaryEntry, Integer> diaryDAO;

	public DiaryDBImpl(Context theContext) {
		context = theContext;
		diaryDAO = getHelper(context).getRuntimeExceptionDao(DiaryEntry.class);
	}

	@Override
	public ArrayList<DiaryEntry> getList() {
		// TODO Auto-generated method stub
		ArrayList<DiaryEntry> diaryLog = null;

		diaryLog = new ArrayList<DiaryEntry>();
		diaryLog = (ArrayList<DiaryEntry>) diaryDAO.queryForAll();

		return diaryLog;
	}

	@Override
	public void addEntry(DiaryEntry entry) {
		// TODO Auto-generated method stub
		diaryDAO.create(new DiaryEntry(entry.getDate(), entry.getAction(),
				entry.getEmployer(), entry.getFurtherAction(),
				entry.getFDate(), entry.getSuitability(), entry.getReason(),
				entry.getComments()));
	}

	@Override
	public void editEntry(DiaryEntry editEntry) {
		// TODO Auto-generated method stub
		try {
			UpdateBuilder<DiaryEntry, Integer> updateDiaryEntry = diaryDAO.updateBuilder();
			updateDiaryEntry.where().eq(DiaryEntry.DATE, editEntry.getDate());
			updateDiaryEntry.updateColumnValue(DiaryEntry.ACTION, editEntry.getAction());
			updateDiaryEntry.updateColumnValue(DiaryEntry.EMPLOYER, editEntry.getEmployer());
			updateDiaryEntry.updateColumnValue(DiaryEntry.FURTHERACTION, editEntry.getFurtherAction());
			updateDiaryEntry.updateColumnValue(DiaryEntry.FDATE, editEntry.getFDate());
			updateDiaryEntry.updateColumnValue(DiaryEntry.SUITABILITY, editEntry.getSuitability());
			updateDiaryEntry.updateColumnValue(DiaryEntry.REASON, editEntry.getReason());
			updateDiaryEntry.updateColumnValue(DiaryEntry.COMMENTS, editEntry.getComments());
			updateDiaryEntry.update();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public void deleteEntry(String date) {
		// TODO Auto-generated method stub
		try {
        	DeleteBuilder<DiaryEntry,Integer> deleteDiaryEntry = diaryDAO.deleteBuilder();
        	deleteDiaryEntry.where().eq(DiaryEntry.DATE,date);
        	deleteDiaryEntry.delete();
        	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	@Override
	protected Context getContext() {
		// TODO Auto-generated method stub
		return null;
	}

}
