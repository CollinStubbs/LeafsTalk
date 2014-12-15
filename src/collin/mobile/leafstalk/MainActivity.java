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
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
/*
 * @APPNAME: LeafsTalk
 * @Author: Collin Stubbs - 100454604
 * @Desc: LeafsTalk is a minimalistic app that tells you all you
 * need to know about the leafs to create small talk.
 * */
public class MainActivity extends ActionBarActivity  {
	//declaring the views
	LinearLayout ll, pic;
	View v1,v2,v3,v4,v5,v6, v7, v8, v9;
	TextView pI, pA, oA, oAAns, lT,  lTAns, topPlay, topOne, topTwo, topThree, news;
	Button newsOne,newsTwo,newsThree;
	//use this to toggle the theme
	boolean toggle = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//instantiating the views
		 ll =(LinearLayout)findViewById(R.id.mainLay);
		 pic =(LinearLayout)findViewById(R.id.leafsPic);
		 
		v1=(View)findViewById(R.id.v1);
		v2=(View)findViewById(R.id.v2);
		v3=(View)findViewById(R.id.v3);
		v4=(View)findViewById(R.id.v4);
		v5=(View)findViewById(R.id.v5);
		v6=(View)findViewById(R.id.v6);
		v7=(View)findViewById(R.id.v7);
		v8=(View)findViewById(R.id.v8);
		v9=(View)findViewById(R.id.v9);
		
		 pI =(TextView)findViewById(R.id.playoffIntro);
		 pA =(TextView)findViewById(R.id.playoffAns);
		 oA =(TextView)findViewById(R.id.overall);
		 oAAns =(TextView)findViewById(R.id.overallAns);
		 lT =(TextView)findViewById(R.id.lastten);
		 lTAns =(TextView)findViewById(R.id.lasttenans);
		
		 topPlay =(TextView)findViewById(R.id.topPlayers);
		 topOne =(TextView)findViewById(R.id.topOne);
		 topTwo =(TextView)findViewById(R.id.topTwo);
		 topThree =(TextView)findViewById(R.id.topThree);
		
		 news =(TextView)findViewById(R.id.news);
		 newsOne =(Button)findViewById(R.id.newsOne);
		 newsTwo =(Button)findViewById(R.id.newsTwo);
		 newsThree =(Button)findViewById(R.id.newsThree);
		 
		//IMPLEMENT KABEL FONT
	    Typeface kabe=Typeface.createFromAsset(getAssets(), "fonts/Kabel_Regular.ttf");
	    Typeface kabeBold=Typeface.createFromAsset(getAssets(), "fonts/Kabel_Bold.ttf");
	    
	    //Instantiate and execute the GetInfo class
	    GetInfo gI = new GetInfo();
	    gI.execute();
	    
	    //Instantiate and execute the GetPlayerInfo class
	    GetPlayerInfo gPI = new GetPlayerInfo();
	    gPI.execute();
	    
	    //Instantiate and execute the GetNews class
	    final GetNews gN = new GetNews();
	    gN.execute();
	    
	    //Playoff Spot Answer
	    pA.setTypeface(kabeBold);
	    pA.setTextSize(40f);
	    
	    //Playoff Spot Question
	    pI.setTypeface(kabe);
	    pI.setTextSize(24f);
	    
	    //Overall stats
	    oA.setTypeface(kabe);
	    oA.setTextSize(24f);
	    
	    //Overall stats enter
	    oAAns.setTypeface(kabeBold);
	    oAAns.setTextSize(24f);
	    
	    //last ten
	    lT.setTypeface(kabe);
	    lT.setTextSize(24f);
	    
	    //Overall stats enter
	    lTAns.setTypeface(kabeBold);
	    lTAns.setTextSize(24f);
	    
	    //Top players text
	    topPlay.setTypeface(kabe);
	    topPlay.setTextSize(24f);
	    
	    //Top Player One
	    topOne.setTypeface(kabeBold);
	    topOne.setTextSize(14f);
	    
	    //Top Player Two
	    topTwo.setTypeface(kabeBold);
	    topTwo.setTextSize(14f);
	    
	     //Top Player Three
	    topThree.setTypeface(kabeBold);
	    topThree.setTextSize(14f);
	    
	    //news text
	    news.setTypeface(kabe);
	    news.setTextSize(24f);
	    
	    //NewsButtonOne
	    newsOne.setTypeface(kabeBold);
	    newsOne.setTextSize(14f);
	    
	    //NewsButtonTwo
	    newsTwo.setTypeface(kabeBold);
	    newsTwo.setTextSize(14f);
	    
	    //NewsButtonOne
	    newsThree.setTypeface(kabeBold);
	    newsThree.setTextSize(14f);
	    
	    try {
	    	while(gI.getCheck() == 0 || gPI.getCheck() == 0 || gN.getCheck() == 0){
	    		Thread.sleep(30);         
	    	}
	    } catch (InterruptedException e) {
	       e.printStackTrace();
	    }
	    
	    //if we're in a playoff spot or not
	    if(gI.getRank() <= 8){
	    	pA.setText("Yes");
	    }else{
	    	pA.setText("No");
	    }
	    

	    //setting overall stats text
	    oA.setText("Wins/Losses/OT-Losses: ");
	    
	    //setting the overallstats quantity
	    oAAns.setText(gI.getOverallStats());
	    

	    //setting last ten text
	    lT.setText("Last Ten:");
	    
	    //setting last ten quantity
	    lTAns.setText(gI.getLastTen());
	    
	    //setting top players text
	    topPlay.setText("Top Three (G-A-Total):");
	    
	    //setting the first player
	    topOne.setText(gPI.getOne());
	    
	    //setting the second player
	    topTwo.setText(gPI.getTwo());
	    
	    //setting the third player
	    topThree.setText(gPI.getThree());
	    
	    //setting news text
	    news.setText("News:");
	    
	    //setting text and link for first news item
	    newsOne.setText(gN.getOneTitle());
	    newsOne.setOnClickListener(new Button.OnClickListener() {
	        public void onClick(View v) {
	        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(gN.getOneLink()));
	        	startActivity(browserIntent);
		    }
		});
		    
	    //setting text and link for 2nd news item
	    newsTwo.setText(gN.getTwoTitle());
	    newsTwo.setOnClickListener(new Button.OnClickListener() {
	        public void onClick(View v) {
	        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(gN.getTwoLink()));
	        	startActivity(browserIntent);
		    }
		});
	    
	    //setting text and link for 3rd news item
	    newsThree.setText(gN.getThreeTitle());
	    newsThree.setOnClickListener(new Button.OnClickListener() {
	        public void onClick(View v) {
	        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(gN.getThreeLink()));
	        	startActivity(browserIntent);
		    }
		});
	    
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	//Overflow menu has 2 options, to toggle the theme and to open a comparison activity
	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			if(toggle){
			ll.setBackgroundColor(getResources().getColor(R.color.leafsWhite));
			pic.setBackground(getResources().getDrawable(R.drawable.leafblue));
			v1.setBackgroundColor(getResources().getColor(R.color.leafsBlue));
			v2.setBackgroundColor(getResources().getColor(R.color.leafsWhite));
			v3.setBackgroundColor(getResources().getColor(R.color.leafsBlue));
			v4.setBackgroundColor(getResources().getColor(R.color.leafsBlue));
			v5.setBackgroundColor(getResources().getColor(R.color.leafsWhite));
			v6.setBackgroundColor(getResources().getColor(R.color.leafsBlue));
			v7.setBackgroundColor(getResources().getColor(R.color.leafsBlue));
			v8.setBackgroundColor(getResources().getColor(R.color.leafsWhite));
			v9.setBackgroundColor(getResources().getColor(R.color.leafsBlue));
			
			pI.setTextColor(getResources().getColor(R.color.leafsBlue));
			 pA.setTextColor(getResources().getColor(R.color.leafsWhite));
			 oA.setTextColor(getResources().getColor(R.color.leafsBlue));
			 oAAns.setTextColor(getResources().getColor(R.color.leafsBlue));
			 lT.setTextColor(getResources().getColor(R.color.leafsBlue));
			 lTAns.setTextColor(getResources().getColor(R.color.leafsBlue));
			
			 topPlay.setTextColor(getResources().getColor(R.color.leafsBlue));
			 topOne.setTextColor(getResources().getColor(R.color.leafsBlue));
			 topTwo.setTextColor(getResources().getColor(R.color.leafsBlue));
			 topThree.setTextColor(getResources().getColor(R.color.leafsBlue));
			 
			 news.setTextColor(getResources().getColor(R.color.leafsBlue));
			 newsOne.setTextColor(getResources().getColor(R.color.leafsBlue));
			 newsTwo.setTextColor(getResources().getColor(R.color.leafsBlue));
			 newsThree.setTextColor(getResources().getColor(R.color.leafsBlue));
			 
			 toggle = false;
			}else{
				ll.setBackgroundColor(getResources().getColor(R.color.leafsBlue));
				pic.setBackground(getResources().getDrawable(R.drawable.leaf));
				v1.setBackgroundColor(getResources().getColor(R.color.leafsWhite));
				v2.setBackgroundColor(getResources().getColor(R.color.leafsBlue));
				v3.setBackgroundColor(getResources().getColor(R.color.leafsWhite));
				v4.setBackgroundColor(getResources().getColor(R.color.leafsWhite));
				v5.setBackgroundColor(getResources().getColor(R.color.leafsBlue));
				v6.setBackgroundColor(getResources().getColor(R.color.leafsWhite));
				v7.setBackgroundColor(getResources().getColor(R.color.leafsWhite));
				v8.setBackgroundColor(getResources().getColor(R.color.leafsBlue));
				v9.setBackgroundColor(getResources().getColor(R.color.leafsWhite));
				
				pI.setTextColor(getResources().getColor(R.color.leafsWhite));
				 pA.setTextColor(getResources().getColor(R.color.leafsBlue));
				 oA.setTextColor(getResources().getColor(R.color.leafsWhite));
				 oAAns.setTextColor(getResources().getColor(R.color.leafsWhite));
				 lT.setTextColor(getResources().getColor(R.color.leafsWhite));
				 lTAns.setTextColor(getResources().getColor(R.color.leafsWhite));
				
				 topPlay.setTextColor(getResources().getColor(R.color.leafsWhite));
				 topOne.setTextColor(getResources().getColor(R.color.leafsWhite));
				 topTwo.setTextColor(getResources().getColor(R.color.leafsWhite));
				 topThree.setTextColor(getResources().getColor(R.color.leafsWhite));
				 
				 news.setTextColor(getResources().getColor(R.color.leafsWhite));
				 newsOne.setTextColor(getResources().getColor(R.color.leafsWhite));
				 newsTwo.setTextColor(getResources().getColor(R.color.leafsWhite));
				 newsThree.setTextColor(getResources().getColor(R.color.leafsWhite));
				
				 toggle = true;
			}
			return true;
			
			//to open the comparison activity
		}else if(id == R.id.comparisons){
			Intent intent = new Intent(this, Comparisons.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}