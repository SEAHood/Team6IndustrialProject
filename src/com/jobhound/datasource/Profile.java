package com.jobhound.datasource;

import com.j256.ormlite.field.DatabaseField;

public class Profile {

	public static final String ID = "id";
	public static final String JOB1 ="job1";
	public static final String JOB2 ="job2";
	public static final String JOB3 ="job3";
	public static final String PARTFULL ="partFull";
	public static final String PROGRESS ="progress";
	
	@DatabaseField(generatedId=true, columnName = ID)
	public int id;
	@DatabaseField(columnName = JOB1)
	public String job1;
	@DatabaseField(columnName = JOB2)
	public String job2;
	@DatabaseField(columnName = JOB3)
	public String job3;
	@DatabaseField(columnName = PARTFULL)
	public String partFull;
	@DatabaseField(columnName = PROGRESS)
	public int progress;

	public Profile(String job1, String job2, String job3, String partFull, int progress)
	{
		this.job1=job1;
		this.job2=job2;
		this.job3=job3;
		this.partFull=partFull;
		this.progress=progress;		
	}
	
	public Profile()
	{
		
	}
	
	public String getJob1()
	{
		return job1;
	}
	
	public void setJob1(String job1)
	{
		this.job1=job1;
	}
	
	public String getJob2()
	{
		return job2;
	}
	
	public void setJob2(String job2)
	{
		this.job2=job2;
	}
	
	public String getJob3()
	{
		return job3;
	}
	
	public void setJob3(String job3)
	{
		this.job3=job3;
	}
	
	public String getPartFull()
	{
		return partFull;
	}
	
	public void setPartFull(String partFull)
	{
		this.partFull=partFull;
	}
	
	public int getProgress()
	{
		return progress;
	}

	public int getId() {
		return id;
	}
	
}
