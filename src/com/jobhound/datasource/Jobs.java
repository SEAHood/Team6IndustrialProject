package com.jobhound.datasource;

public class Jobs {

	private String source;
	private String description;
	private String link;
	
	
	public Jobs(String source, String description, String link)
	{
		this.source=source;
		this.description=description;
		this.link=link;
	}
	
	public String getSource()
	{
		return source;
	}
	
	public void setSource(String source)
	{
		this.source=source;
	}
		
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getLink()
	{
		return link;
	}
	
	public void setLink(String link)
	{
		this.link=link;
	}
	
	@Override
	  public String toString() {
	    return description;
	  }
}
