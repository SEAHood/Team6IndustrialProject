package com.jobhound.interfaces;

import java.util.ArrayList;

import com.jobhound.datasource.DiaryEntry;

public interface DiaryDBInterface {
	
	public ArrayList<DiaryEntry> getList();
	public void addEntry(DiaryEntry entry);
	public void editEntry(DiaryEntry editEntry);
	public void deleteEntry(String date);

}
