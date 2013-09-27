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
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class JobSearch implements JobWebInterface {

	public JSONObject jobResults;
	public JSONArray results;
	private HttpClient client;
	String getURL = "http://samhood.net/jh/search.php?q=";
	public String statusCode;
	public Context parentContext;

	public String searchItem;
	private ProgressDialog mProgressDialog;

	public void findJobs(Context sentContext, String search) {
		parentContext = sentContext;
		searchItem = search;
		showProgressDialog("Searching Jobs...");
		new getJobsThread().execute();
	}

	public void showProgressDialog(String msg) {
		mProgressDialog = new ProgressDialog(parentContext);
		mProgressDialog.setMessage(msg);
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
	}

	// Closes dialog box to avoid leaking windows
	public void closeProgressDialog() {
		mProgressDialog.dismiss();
	}

	public class getJobsThread extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			client = new DefaultHttpClient();

			HttpGet get = new HttpGet(getURL+searchItem);
			HttpResponse response;

			int code = 0;

			try {

				response = client.execute(get);

				code = response.getStatusLine().getStatusCode();

				if (code == 200) {
					HttpEntity entity = response.getEntity();

					String data = EntityUtils.toString(entity);
					results = new JSONArray(data);
				//	jobResults = new JSONObject(data);
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
			closeProgressDialog();

			processDownload();

		}

	}

	public void processDownload() {
		if (statusCode.equals("200")) {
			((SearchResults) parentContext).receiveUserDetails(results);
		} else {
			Dialog d = new Dialog(parentContext);
			d.setTitle("Server side error !");
			TextView tv = new TextView(parentContext);
			tv.setText("Terminated with status code : " + statusCode);
			d.setContentView(tv);
			d.show();
		}

	}

}