package collin.mobile.leafstalk;

import java.util.ArrayList;

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
/*
 * @name: GetInfo
 * @author: Collin Stubbs - 100454604
 * @desc: This class sends a get request for an xml page,
 * parses the xml, and stores the data
 * 
 * */
public class GetInfo extends AsyncTask<Void, Void, Void> {
	private Document doc;
	private int rank;
	private int checker = 0;
	
	private String wins, losses, otl, lastTen;


	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		
		try{
			//http get stuff
	    	HttpGet uri = new HttpGet("http://app.cgy.nhl.yinzcam.com/V2/Stats/Standings");    

	    	DefaultHttpClient client = new DefaultHttpClient();
	    	HttpResponse resp = client.execute(uri);

	    	StatusLine status = resp.getStatusLine();
	    	//checks if request went through
	    	if (status.getStatusCode() != 200) {
	    	    Log.d("Hello", "HTTP error, invalid server status code: " + resp.getStatusLine());  
	    	}
	    	
	    	//xml parsing
	    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	doc = builder.parse(resp.getEntity().getContent());
	    	doc.getDocumentElement().normalize();
	    	resp.getEntity().consumeContent();
	       
	    	//all the standings nodes, so every team in the league
	    	NodeList nList = doc.getElementsByTagName("Standing");
	    	 
	    	//for all the teams
	    	for (int temp = 0; temp < nList.getLength(); temp++) {
	    		Node nNode = nList.item(temp);
	     
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	     
	    			Element eElement = (Element) nNode;
	    			//check if the team is toronto
	    			if(eElement.getAttribute("TriCode").equals("TOR")){
	    				//add the data to the variables
	    				rank = Integer.parseInt(eElement.getAttribute("ConfRank"));
	    				
	    				Element stats1 = (Element) eElement.getElementsByTagName("StatsGroup").item(0);
	    				Element stats2 = (Element) eElement.getElementsByTagName("StatsGroup").item(1);
	    				
	    				wins = stats1.getAttribute("Stat1");	    				
	    				losses = stats1.getAttribute("Stat2");
	    				otl = stats1.getAttribute("Stat3");
	    				
	    				lastTen = stats2.getAttribute("Stat2");
	    				
	    				
	    				checker = 1;
	    			}
	    		}
	    			
	    		
	    	}
	    	
	    	
	       }catch(Exception e){
	           Log.e("log_tag", "Error in http connection "+e.toString());
	       }
		return null;
	}
	//used to check if task is done
	public int getCheck(){
		return checker;
	}
	//gets the rank of the team -- for playoff spot
	public int getRank(){

		return rank;
	}
	//gets a string of the overall stats of the team
	public String getOverallStats(){
		
		return wins+"/"+losses+"/"+otl;
	}
	//gets the last ten game's data
	public String getLastTen(){
		return lastTen;
	}
}
