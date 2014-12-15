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

public class GetNews extends AsyncTask<Void, Void, Void> {
	
	private ArrayList<String> news = new ArrayList<String>();
	private int check = 0;

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		try{
	    	HttpGet uri = new HttpGet("http://mapleleafs.nhl.com/rss/news.xml");    

	    	DefaultHttpClient client = new DefaultHttpClient();
	    	HttpResponse resp = client.execute(uri);

	    	StatusLine status = resp.getStatusLine();
	    	if (status.getStatusCode() != 200) {
	    	    Log.d("Hello", "HTTP error, invalid server status code: " + resp.getStatusLine());  
	    	}

	    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	Document doc = builder.parse(resp.getEntity().getContent());
	    	doc.getDocumentElement().normalize();
	    	resp.getEntity().consumeContent();
	       
	    	NodeList tList = doc.getElementsByTagName("item");
	    
	     
	    	for (int temp = 0; temp < 3; temp++) {
	     
	    		Node tNode = tList.item(temp);
	
	    		Element tElement = (Element) tNode;
	    		
	    		
	    		news.add(tElement.getElementsByTagName("title").item(0).getTextContent());
	    		news.add(tElement.getElementsByTagName("link").item(0).getTextContent());	
	    			
	    	}
	    			    	
	    	
	       }catch(Exception e){
	           Log.e("log_tag", "Error in http connection "+e.toString());
	       }
		check = 1;
		return null;
	}
	public int getCheck(){
		
		return check;
	}
	public String getOneTitle(){
		return "1.  "+news.get(0);
	}
	public String getOneLink(){
		return news.get(1);
	}
	public String getTwoTitle(){
		return "2.  "+news.get(2);
	}
	public String getTwoLink(){
		return news.get(3);
	}
	public String getThreeTitle(){
		return "3.  "+news.get(4);
	}
	public String getThreeLink(){
		return news.get(5);
	}

}
