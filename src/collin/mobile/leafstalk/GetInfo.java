package collin.mobile.leafstalk;

import java.io.BufferedReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.os.AsyncTask;
import android.util.Log;

public class GetInfo extends AsyncTask<Void, Void, Void> {
	private Document doc;
	private int rank;
	private int checker = 0;
	
	private String wins, losses, otl;


	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		
		try{
	    	HttpGet uri = new HttpGet("http://app.cgy.nhl.yinzcam.com/V2/Stats/Standings");    

	    	DefaultHttpClient client = new DefaultHttpClient();
	    	HttpResponse resp = client.execute(uri);

	    	StatusLine status = resp.getStatusLine();
	    	if (status.getStatusCode() != 200) {
	    	    Log.d("Hello", "HTTP error, invalid server status code: " + resp.getStatusLine());  
	    	}

	    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	doc = builder.parse(resp.getEntity().getContent());
	    	doc.getDocumentElement().normalize();
	    	resp.getEntity().consumeContent();
	          
	    	NodeList nList = doc.getElementsByTagName("Standing");
	    	 
	    	
	     
	    	for (int temp = 0; temp < nList.getLength(); temp++) {
	     
	    		Node nNode = nList.item(temp);
	     
	     
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	     
	    			Element eElement = (Element) nNode;
	    			
	    			if(eElement.getAttribute("TriCode").equals("TOR")){
	    				rank = Integer.parseInt(eElement.getAttribute("ConfRank"));
	    				
	    				
	    				Element stats1 = (Element) eElement.getElementsByTagName("StatsGroup").item(0);
	    				
	    				wins = stats1.getAttribute("Stat1");
	    				
	    				losses = stats1.getAttribute("Stat2");
	    				otl = stats1.getAttribute("Stat3");
	    				
	    				
	    				checker = 1;
	    			}
	    		}
	    			
	    		
	    	}
	    	
	    	
	       }catch(Exception e){
	           Log.e("log_tag", "Error in http connection "+e.toString());
	       }
		return null;
	}
	public int getRank(){
		
		
		return rank;
	}
	
	public String getOverallStats(){
		
		return wins+"/"+losses+"/"+otl;
	}
}
