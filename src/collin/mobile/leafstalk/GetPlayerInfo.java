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

/*
 * @name: GetPlayerInfo
 * @author: Collin Stubbs - 100454604
 * @desc: This class sends a get request for a json string,
 *  parses the json string, and returns the top players
 * 
 * */
public class GetPlayerInfo extends AsyncTask<Void, Void, Void>{

	private ArrayList<String> arr = new ArrayList<String>();
	private int checker = 0;
	
	
	
	@Override
	protected Void doInBackground(Void... params) {
		
		//Http Stuff
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
		    
		    	//take sthe jason info and puts the player info into the Arraylist
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
		        checker = 1;
		} catch (Exception e) { 
		    // Oops
		}
		finally {
		    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
		}
		
		return null;
	}

	//Used to take the players names, add the numbers, and add to arraylist
	private void fillArray(String one, String two, String three){
		String oneParts[] = one.split(", ");
		String twoParts[] = two.split(", ");
		String threeParts[] = three.split(", ");
		arr.add("1.  "+oneParts[2]+": "+oneParts[4]+"-"+oneParts[5]+"-"+oneParts[6]);
		arr.add("2.  "+twoParts[2]+": "+twoParts[4]+"-"+twoParts[5]+"-"+twoParts[6]);
		arr.add("3.  "+threeParts[2]+": "+threeParts[4]+"-"+threeParts[5]+"-"+threeParts[6]);
		
	}
	//gets the first player
	public String getOne(){
		return arr.get(0);
	}
	//gets the second player
	public String getTwo(){
		return arr.get(1);
	}
	//gets the third player
	public String getThree(){
		return arr.get(2);
	}
	//gets the check value -- used to tell if task is complete
	public int getCheck(){
		return checker;
	}
}
