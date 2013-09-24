package com.jobhound.services.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.jobhound.datasource.DiaryEntries;
import com.jobhound.interfaces.DiaryDBInterface;

public class DiaryDBImpl extends BaseService implements DiaryDBInterface {

	public Context context;
	private RuntimeExceptionDao<DiaryEntries, Integer> diaryDAO;

	public DiaryDBImpl(Context theContext) {
		context = theContext;
		diaryDAO = getHelper(context)
				.getRuntimeExceptionDao(DiaryEntries.class);
	}

	@Override
	public ArrayList<DiaryEntries> getList() {
		// TODO Auto-generated method stub
		ArrayList<DiaryEntries> diaryLog = null;

		diaryLog = new ArrayList<DiaryEntries>();
		diaryLog = (ArrayList<DiaryEntries>) diaryDAO.queryForAll();

		return diaryLog;
	}

	@Override
	public void addEntry(DiaryEntries entry) {
		// TODO Auto-generated method stub
		diaryDAO.create(new DiaryEntries(entry.getdate(), entry.getAction(),
				entry.getEmployer(), entry.getFurtherAction(),
				entry.getFDate(), entry.getSuitability(), entry.getReason(),
				entry.getComments()));
	}

	@Override
	public void editEntry(DiaryEntries editEntry) {
		// TODO Auto-generated method stub
		try {
			UpdateBuilder<DiaryEntries, Integer> updateDiaryEntry = diaryDAO.updateBuilder();
			updateDiaryEntry.where().eq(DiaryEntries.DATE, editEntry.getdate());
			updateDiaryEntry.updateColumnValue(DiaryEntries.ACTION, editEntry.getAction());
			updateDiaryEntry.updateColumnValue(DiaryEntries.EMPLOYER, editEntry.getEmployer());
			updateDiaryEntry.updateColumnValue(DiaryEntries.FURTHERACTION, editEntry.getFurtherAction());
			updateDiaryEntry.updateColumnValue(DiaryEntries.FDATE, editEntry.getFDate());
			updateDiaryEntry.updateColumnValue(DiaryEntries.SUITABILITY, editEntry.getSuitability());
			updateDiaryEntry.updateColumnValue(DiaryEntries.REASON, editEntry.getReason());
			updateDiaryEntry.updateColumnValue(DiaryEntries.COMMENTS, editEntry.getComments());
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
        	DeleteBuilder<DiaryEntries,Integer> deleteDiaryEntry = diaryDAO.deleteBuilder();
        	deleteDiaryEntry.where().eq(DiaryEntries.DATE,date);
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
