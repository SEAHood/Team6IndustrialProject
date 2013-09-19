package com.jobhound.services.ws;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class JobSearch {

	public JSONObject jobResults;
	private HttpClient client;
	String getURL = "http://samhood.net/jh/twitter.php";
	public String statusCode;
	
	
	public void findJobs()
	{
		new getJobsThread().execute();
	}
	
public class getJobsThread extends AsyncTask<String, Integer, String> {
		
		
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
		
			
			client = new DefaultHttpClient();

			HttpGet get = new HttpGet(getURL);
			HttpResponse response;
			
			int code = 0;
			
			try {
							
				response = client.execute(get);
				
				code = response.getStatusLine().getStatusCode();
				
				if(code == 200)
				{
					HttpEntity entity = response.getEntity();
					
					String data = EntityUtils.toString(entity);
								
					jobResults = new JSONObject(data);				
				}
			
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			return Integer.toString(code);
			
			
			
		}

		@Override
		protected void onPostExecute(String code) {
			// TODO Auto-generated method stub
			super.onPostExecute(code);
			
			statusCode = code;
		//	closeProgressDialog();
			
		//	processDownload();

			
		}
	
	
	}
}
