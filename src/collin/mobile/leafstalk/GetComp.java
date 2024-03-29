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
 * @name: GetComp
 * @author: Collin Stubbs - 100454604
 * @desc: This class sends a get request for an xml page,
 * parses the xml, and stores the data
 * 
 * */
public class GetComp extends AsyncTask<Void, Void, Void> {
	private Document doc;
	private int checker = 0;
	private ArrayList<String> tor, bos, buf, det, flo, mon, ott, tam; 


	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		//declaring arraylists for each team
		tor = new ArrayList<String>();
		bos= new ArrayList<String>();
		buf= new ArrayList<String>();
		det= new ArrayList<String>();
		flo= new ArrayList<String>();
		mon= new ArrayList<String>();
		ott= new ArrayList<String>();
		tam= new ArrayList<String>();
		
		try{
			//http request code
	    	HttpGet uri = new HttpGet("http://app.cgy.nhl.yinzcam.com/V2/Stats/Standings");    

	    	DefaultHttpClient client = new DefaultHttpClient();
	    	HttpResponse resp = client.execute(uri);

	    	StatusLine status = resp.getStatusLine();
	    	
	    	//check if request was successful
	    	if (status.getStatusCode() != 200) {
	    	    Log.d("Hello", "HTTP error, invalid server status code: " + resp.getStatusLine());  
	    	}

	    	//for parsing the xml
	    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	doc = builder.parse(resp.getEntity().getContent());
	    	doc.getDocumentElement().normalize();
	    	resp.getEntity().consumeContent();
	       
	    	//nodelist of all the teams
	    	NodeList nList = doc.getElementsByTagName("Standing");
	    	 
	    	//for all the teams, check if the current is the one we want and add data to arraylist if it is
	    	for (int temp = 0; temp < nList.getLength(); temp++) {
	     
	    		Node nNode = nList.item(temp);
	     
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	     
	    			Element eElement = (Element) nNode;
	    			
	    			if(eElement.getAttribute("TriCode").equals("TOR")){
	    				
	    				Element stats1 = (Element) eElement.getElementsByTagName("StatsGroup").item(0);
	    				Element stats2 = (Element) eElement.getElementsByTagName("StatsGroup").item(1);
	    				
	    				tor.add("Toronto");
	    				tor.add(stats1.getAttribute("Stat0"));
	    				tor.add(stats1.getAttribute("Stat1"));	    				
	    				tor.add(stats1.getAttribute("Stat2"));
	    				tor.add(stats1.getAttribute("Stat3"));
	    				tor.add(stats1.getAttribute("Stat4"));
	    				tor.add(stats2.getAttribute("Stat0"));
	    				tor.add(stats2.getAttribute("Stat1"));	    				
	    				tor.add(stats2.getAttribute("Stat3"));
	    				
	    				
	    			}
	    			if(eElement.getAttribute("TriCode").equals("BOS")){
	    				
	    				Element stats1 = (Element) eElement.getElementsByTagName("StatsGroup").item(0);
	    				Element stats2 = (Element) eElement.getElementsByTagName("StatsGroup").item(1);
	    				
	    				bos.add("Boston");
	    				bos.add(stats1.getAttribute("Stat0"));
	    				bos.add(stats1.getAttribute("Stat1"));	    				
	    				bos.add(stats1.getAttribute("Stat2"));
	    				bos.add(stats1.getAttribute("Stat3"));
	    				bos.add(stats1.getAttribute("Stat4"));
	    				bos.add(stats2.getAttribute("Stat0"));
	    				bos.add(stats2.getAttribute("Stat1"));	    				
	    				bos.add(stats2.getAttribute("Stat3"));
	    				
	    				
	    			}
	    			if(eElement.getAttribute("TriCode").equals("BUF")){
	    				
	    				Element stats1 = (Element) eElement.getElementsByTagName("StatsGroup").item(0);
	    				Element stats2 = (Element) eElement.getElementsByTagName("StatsGroup").item(1);
	    				
	    				buf.add("Buffalo");
	    				buf.add(stats1.getAttribute("Stat0"));
	    				buf.add(stats1.getAttribute("Stat1"));	    				
	    				buf.add(stats1.getAttribute("Stat2"));
	    				buf.add(stats1.getAttribute("Stat3"));
	    				buf.add(stats1.getAttribute("Stat4"));
	    				buf.add(stats2.getAttribute("Stat0"));
	    				buf.add(stats2.getAttribute("Stat1"));	    				
	    				buf.add(stats2.getAttribute("Stat3"));
	    				
	    				
	    			}
	    			if(eElement.getAttribute("TriCode").equals("DET")){
	    				
	    				Element stats1 = (Element) eElement.getElementsByTagName("StatsGroup").item(0);
	    				Element stats2 = (Element) eElement.getElementsByTagName("StatsGroup").item(1);
	    				
	    				det.add("Detroit");
	    				det.add(stats1.getAttribute("Stat0"));
	    				det.add(stats1.getAttribute("Stat1"));	    				
	    				det.add(stats1.getAttribute("Stat2"));
	    				det.add(stats1.getAttribute("Stat3"));
	    				det.add(stats1.getAttribute("Stat4"));
	    				det.add(stats2.getAttribute("Stat0"));
	    				det.add(stats2.getAttribute("Stat1"));	    				
	    				det.add(stats2.getAttribute("Stat3"));
	    				
	    				
	    			}
	    			if(eElement.getAttribute("TriCode").equals("FLA")){
	    				
	    				Element stats1 = (Element) eElement.getElementsByTagName("StatsGroup").item(0);
	    				Element stats2 = (Element) eElement.getElementsByTagName("StatsGroup").item(1);
	    				
	    				flo.add("Florida");
	    				flo.add(stats1.getAttribute("Stat0"));
	    				flo.add(stats1.getAttribute("Stat1"));	    				
	    				flo.add(stats1.getAttribute("Stat2"));
	    				flo.add(stats1.getAttribute("Stat3"));
	    				flo.add(stats1.getAttribute("Stat4"));
	    				flo.add(stats2.getAttribute("Stat0"));
	    				flo.add(stats2.getAttribute("Stat1"));	    				
	    				flo.add(stats2.getAttribute("Stat3"));
	    				
	    				
	    			}
	    			if(eElement.getAttribute("TriCode").equals("MTL")){
	    				
	    				Element stats1 = (Element) eElement.getElementsByTagName("StatsGroup").item(0);
	    				Element stats2 = (Element) eElement.getElementsByTagName("StatsGroup").item(1);
	    				
	    				mon.add("Montreal");
	    				mon.add(stats1.getAttribute("Stat0"));
	    				mon.add(stats1.getAttribute("Stat1"));	    				
	    				mon.add(stats1.getAttribute("Stat2"));
	    				mon.add(stats1.getAttribute("Stat3"));
	    				mon.add(stats1.getAttribute("Stat4"));
	    				mon.add(stats2.getAttribute("Stat0"));
	    				mon.add(stats2.getAttribute("Stat1"));	    				
	    				mon.add(stats2.getAttribute("Stat3"));
	    				
	    				
	    			}
	    			if(eElement.getAttribute("TriCode").equals("OTT")){
	    				
	    				Element stats1 = (Element) eElement.getElementsByTagName("StatsGroup").item(0);
	    				Element stats2 = (Element) eElement.getElementsByTagName("StatsGroup").item(1);
	    				
	    				ott.add("Ottawa");
	    				ott.add(stats1.getAttribute("Stat0"));
	    				ott.add(stats1.getAttribute("Stat1"));	    				
	    				ott.add(stats1.getAttribute("Stat2"));
	    				ott.add(stats1.getAttribute("Stat3"));
	    				ott.add(stats1.getAttribute("Stat4"));
	    				ott.add(stats2.getAttribute("Stat0"));
	    				ott.add(stats2.getAttribute("Stat1"));	    				
	    				ott.add(stats2.getAttribute("Stat3"));
	    				
	    				
	    			}
	    			if(eElement.getAttribute("TriCode").equals("TBL")){
	    				
	    				Element stats1 = (Element) eElement.getElementsByTagName("StatsGroup").item(0);
	    				Element stats2 = (Element) eElement.getElementsByTagName("StatsGroup").item(1);
	    				
	    				tam.add("Tampa");
	    				tam.add(stats1.getAttribute("Stat0"));
	    				tam.add(stats1.getAttribute("Stat1"));	    				
	    				tam.add(stats1.getAttribute("Stat2"));
	    				tam.add(stats1.getAttribute("Stat3"));
	    				tam.add(stats1.getAttribute("Stat4"));
	    				tam.add(stats2.getAttribute("Stat0"));
	    				tam.add(stats2.getAttribute("Stat1"));	    				
	    				tam.add(stats2.getAttribute("Stat3"));
	    				
	    				
	    			}
	    		}
	    			
	    		
	    	}
	    	checker = 1;
	    	
	       }catch(Exception e){
	           Log.e("log_tag", "Error in http connection "+e.toString());
	       }
		return null;
	}
	//for checking if task is complete
	public int getCheck(){
		return checker;
	}
	//returns the arraylist for the team name given in the parameters
	public ArrayList<String> getStats(String name){
		if(name.equals(tor.get(0))){
			return tor;
		}else if(name.equals(bos.get(0))){
			return bos;
		}else if(name.equals(buf.get(0))){
			return buf;
		}else if(name.equals(det.get(0))){
			return det;
		}else if(name.equals(flo.get(0))){
			return flo;
		}else if(name.equals(mon.get(0))){
			return mon;
		}else if(name.equals(ott.get(0))){
			return ott;
		}else if(name.equals(tam.get(0))){
			return tam;
		}else{
			return null;
		}
	}
	
}
