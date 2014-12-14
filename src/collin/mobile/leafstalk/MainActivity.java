package collin.mobile.leafstalk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//IMPLEMENT KABEL FONT
		TextView pI =(TextView)findViewById(R.id.playoffIntro);
		TextView pA =(TextView)findViewById(R.id.playoffAns);
		TextView oA =(TextView)findViewById(R.id.overall);
		TextView oAAns =(TextView)findViewById(R.id.overallAns);
		TextView lT =(TextView)findViewById(R.id.lastten);
		TextView lTAns =(TextView)findViewById(R.id.lasttenans);
		
		TextView topPlay =(TextView)findViewById(R.id.topPlayers);
		TextView topOne =(TextView)findViewById(R.id.topOne);
		TextView topTwo =(TextView)findViewById(R.id.topTwo);
		TextView topThree =(TextView)findViewById(R.id.topThree);
		
	    Typeface kabe=Typeface.createFromAsset(getAssets(), "fonts/Kabel_Regular.ttf");
	    Typeface kabeBold=Typeface.createFromAsset(getAssets(), "fonts/Kabel_Bold.ttf");
	    
	    GetInfo gI = new GetInfo();
	    gI.execute();
	    
	    GetPlayerInfo gPI = new GetPlayerInfo();
	    gPI.execute();
	    
	    //Playoff Spot Answer
	    pA.setTypeface(kabeBold);
	    pA.setTextSize(40f);
	    
	    try {
	    	while(gI.getRank() == 0 || gPI.getCheck() == 0){
	    		Thread.sleep(100);         
	    	}
	    } catch (InterruptedException e) {
	       e.printStackTrace();
	    }
	    
	    if(gI.getRank() <= 8){
	    	pA.setText("Yes");
	    }else{
	    	pA.setText("No");
	    }
	    
	    //Playoff Spot Question
	    pI.setTypeface(kabe);
	    pI.setTextSize(24f);
	    
	    //Overall stats
	    oA.setTypeface(kabe);
	    oA.setTextSize(24f);
	    oA.setText("Wins/Losses/OT-Losses: ");
	    
	    //Overall stats enter
	    oAAns.setTypeface(kabeBold);
	    oAAns.setTextSize(24f);
	    oAAns.setText(gI.getOverallStats());
	    

	    //Overall stats
	    lT.setTypeface(kabe);
	    lT.setTextSize(24f);
	    lT.setText("Last Ten:");
	    
	    //Overall stats enter
	    lTAns.setTypeface(kabeBold);
	    lTAns.setTextSize(24f);
	    lTAns.setText(gI.getLastTen());
	    
	    //Top players text
	    topPlay.setTypeface(kabe);
	    topPlay.setTextSize(24f);
	    topPlay.setText("Top Three (G-A-Total):");
	    
	    //Top Player One
	    topOne.setTypeface(kabeBold);
	    topOne.setTextSize(14f);
	    topOne.setText(gPI.getOne());
	    
	    //Top Player Two
	    topTwo.setTypeface(kabeBold);
	    topTwo.setTextSize(14f);
	    topTwo.setText(gPI.getTwo());
	    
	    //Top Player Three
	    topThree.setTypeface(kabeBold);
	    topThree.setTextSize(14f);
	    topThree.setText(gPI.getThree());
	    
	    //conference rank
	    //division rank
	    //how good is kessel
	    //http://mapleleafs.nhl.com/rss/news.xml for news maybe
	    //top 3 players
	    
	    
	    
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}