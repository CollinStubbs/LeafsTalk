package collin.mobile.leafstalk;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class GetPlayerInfo extends AsyncTask<Void, Void, Void>{

	private ArrayList<String> arr = new ArrayList<String>();
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
		HttpPost httppost = new HttpPost("http://nhlwc.cdnak.neulion.com/fs1/nhl/league/playerstatsline/20142015/2/TOR/iphone/playerstatsline.json");
		// Depends on your web service
		httppost.setHeader("Content-type", "application/json");

		InputStream inputStream = null;
		String result = null;
		try {
		    HttpResponse response = httpclient.execute(httppost);           
		    HttpEntity entity = response.getEntity();

		    inputStream = entity.getContent();
		    // json is UTF-8 by default
		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    while ((line = reader.readLine()) != null)
		    {
		        sb.append(line + "\n");
		    }
		    result = sb.toString();
		    
		    JSONObject jObject = new JSONObject(result);
		    JSONArray jArray = jObject.getJSONArray("skaterData");
		    
		    
		        try {
		            JSONObject oneObject = jArray.getJSONObject(0);
		            JSONObject oneObject1 = jArray.getJSONObject(1);
		            JSONObject oneObject2 = jArray.getJSONObject(2);
		            // Pulling items from the array
		            fillArray(oneObject.getString("data"),
		            		oneObject1.getString("data"),
		            		oneObject2.getString("data"));
		            
		        } catch (JSONException e) {
		            // Oops
		        }
		    
		} catch (Exception e) { 
		    // Oops
		}
		finally {
		    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
		}
		
		return null;
	}

	private void fillArray(String one, String two, String thr){
		// TAKE DATA, SPLIT BY COMMAS, ADD TO ARRAY, MAKE GET ARRAY METHOD
	}
}
