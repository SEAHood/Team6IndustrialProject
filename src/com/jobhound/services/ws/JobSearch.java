package com.jobhound.services.ws;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jobhound.activities.SearchResults;
import com.jobhound.interfaces.JobWebInterface;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class JobSearch implements JobWebInterface {

	public JSONObject jobResults;
	public JSONArray results;
	private HttpClient client;
	String getURL = "http://samhood.net/jh/twitter.php";
	public String statusCode;
	public Context parentContext;
	
	public void findJobs(Context sentContext)
	{
		parentContext = sentContext;
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
					results = new JSONArray(data);			
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
			
			processDownload();

			
		}
	
	
	}


	public void processDownload()
	{if(statusCode.equals("200"))
	{
	/*	if(jobResults.isNull("id"))
		{
			// Display dialog to say that the ID entered by the user doesn't exist
			Dialog d = new Dialog(parentContext);
			d.setTitle("Incorrect User ID");
			TextView tv = new TextView(parentContext);
			tv.setText("No account associated with user ID, please check and try again");
			d.setContentView(tv);
			d.show();
		}
		else
	*/	{
			((SearchResults) parentContext).receiveUserDetails(results);
	/*	}
		
//	}
//	else
	{
		Dialog d = new Dialog(parentContext);
		d.setTitle("Server side error !");
		TextView tv = new TextView(parentContext);
		tv.setText("Terminated with status code : " + statusCode );
		d.setContentView(tv);
		d.show();				
	}
	*/
	
	
	}
	}
	}
}
