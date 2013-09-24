package com.jobhound.datasource;

public class DiaryEntries {

	private String action;
	private String employer;
	private String date;
	private String furtherAction;
	private String fDate;
	private String suitability;
	private String reason;
	private String comments;
	
	public DiaryEntries(String action, String employer,String date, String furtherAction, String fDate, String suitability, String reason, String comments)
	{
		this.action=action;
		this.employer=employer;
		this.date = date;
		this.furtherAction=furtherAction;
		this.fDate=fDate;
		this.suitability=suitability;
		this.reason=reason;
		this.comments=comments;
	}
	
	
	public String getAction()
	{
		return action;
	}
	
	public void setAction(String action)
	{
		this.action=action;
	}
	
	public String getEmployer()
	{
		return employer;
	}
	
	public void setEmployer(String employer)
	{
		this.employer=employer;
	}
	
	public String getdate()
	{
		return date;
	}
	
	public void setDate(String date)
	{
		this.date=date;
	}
	
	public String getFurtherAction()
	{
		return furtherAction;
	}
	
	public void setFurtherAction(String furtherAction)
	{
		this.furtherAction=furtherAction;
	}
	
	public String getFDate()
	{
		return fDate;
	}
	
	public void setFDate(String fDate)
	{
		this.fDate=fDate;
	}
	
	public String getSuitability()
	{
		return suitability;
	}
	
	public void setSuitability(String suitability)
	{
		this.suitability=suitability;
	}
	
	public String getReason()
	{
		return reason;
	}
	
	public void setReason(String reason)
	{
		this.reason=reason;
	}
	
	public String getComments()
	{
		return comments;
	}
	
	public void setComments(String comments)
	{
		this.comments=comments;
	}
	
}
