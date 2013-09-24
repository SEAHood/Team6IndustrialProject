package com.jobhound.interfaces;

import java.util.ArrayList;

import com.jobhound.datasource.DiaryEntries;

public interface DiaryDBInterface {
	
	public ArrayList<DiaryEntries> getList();
	public void addEntry(DiaryEntries entry);
	public void editEntry(DiaryEntries editEntry);
	public void deleteEntry(String date);

}
