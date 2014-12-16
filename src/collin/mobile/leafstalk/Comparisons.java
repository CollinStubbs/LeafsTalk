package collin.mobile.leafstalk;

import java.util.ArrayList;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/*
 * @name: Comparisons
 * @author: Collin Stubbs - 100454604
 * @desc: This activity shows a comparison of statistics between
 * Toronto and whichever team is selected
 * 
 * */
public class Comparisons extends ActionBarActivity {
	//declaration of views for layout
	TextView leafTitle, oppTitle, leafGP, oppGP, leafW, oppW, leafL, oppL,leafOTL, oppOTL,leafPTS,oppPTS, leafGF, oppGF, leafGA, oppGA, leafSTK, oppSTK; 
	
	GetComp gI; //need to use globally
	ArrayList<String> opp;//globally as well
	ArrayList<String> tor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comparisons);
		//instantiate and execute the GetComp class
		gI = new GetComp();
		gI.execute();
		
		//while task is not done, sleep a bit
		try {
	    	while(gI.getCheck() == 0){
	    		Thread.sleep(30);         
	    	}
	    } catch (InterruptedException e) {
	       e.printStackTrace();
	    }
		  
		//set the arraylists for tor and opposition
		 tor = gI.getStats("Toronto");
		 opp = gI.getStats("Ottawa");
		 
		 //set custom typefaces
		 Typeface kabe=Typeface.createFromAsset(getAssets(), "fonts/Kabel_Regular.ttf");
		 Typeface kabeBold=Typeface.createFromAsset(getAssets(), "fonts/Kabel_Bold.ttf");
		
		 //instantiate the views
		 leafTitle =(TextView)findViewById(R.id.leafsTitle);
		 oppTitle =(TextView)findViewById(R.id.oppTitle);
		 
		 leafGP =(TextView)findViewById(R.id.leafsGP);
		 oppGP =(TextView)findViewById(R.id.oppGP);
		 
		 leafW =(TextView)findViewById(R.id.leafsW);
		 oppW =(TextView)findViewById(R.id.oppW);
		
		 leafL =(TextView)findViewById(R.id.leafsL);
		 oppL =(TextView)findViewById(R.id.oppL);
		 
		 leafOTL =(TextView)findViewById(R.id.leafsOTL);
		 oppOTL =(TextView)findViewById(R.id.oppOTL);
		 
		 leafPTS =(TextView)findViewById(R.id.leafsPTS);
		 oppPTS =(TextView)findViewById(R.id.oppPTS);
		 
		 leafGF =(TextView)findViewById(R.id.leafsGF);
		 oppGF =(TextView)findViewById(R.id.oppGF);
		 
		 leafGA =(TextView)findViewById(R.id.leafsGA);
		 oppGA =(TextView)findViewById(R.id.oppGA);
		 
		 leafSTK =(TextView)findViewById(R.id.leafsSTK);
		 oppSTK =(TextView)findViewById(R.id.oppSTK);
		 
		 //customize the views and add data for toronto ones
		 
		 leafTitle.setTypeface(kabeBold);
		 leafTitle.setTextSize(24f);
		 leafTitle.setText(tor.get(0));
		 
		 oppTitle.setTypeface(kabeBold);
		 oppTitle.setTextSize(24f);
		 
		 leafGP.setTypeface(kabeBold);
		 leafGP.setTextSize(24f);
		 leafGP.setText(tor.get(1));
		 
		 oppGP.setTypeface(kabeBold);
		 oppGP.setTextSize(24f);
		 
		 leafW.setTypeface(kabeBold);
		 leafW.setTextSize(24f);
		 leafW.setText(tor.get(2));
		 
		 oppW.setTypeface(kabeBold);
		 oppW.setTextSize(24f);
		 
		 leafL.setTypeface(kabeBold);
		 leafL.setTextSize(24f);
		 leafL.setText(tor.get(3));
		 
		 oppL.setTypeface(kabeBold);
		 oppL.setTextSize(24f);
		 
		 leafOTL.setTypeface(kabeBold);
		 leafOTL.setTextSize(24f);
		 leafOTL.setText(tor.get(4));
		 
		 oppOTL.setTypeface(kabeBold);
		 oppOTL.setTextSize(24f);
		 
		 leafPTS.setTypeface(kabeBold);
		 leafPTS.setTextSize(24f);
		 leafPTS.setText(tor.get(5));
		 
		 oppPTS.setTypeface(kabeBold);
		 oppPTS.setTextSize(24f);
		 
		 leafGF.setTypeface(kabeBold);
		 leafGF.setTextSize(24f);
		 leafGF.setText(tor.get(6));
		 
		 oppGF.setTypeface(kabeBold);
		 oppGF.setTextSize(24f);
		 
		 leafGA.setTypeface(kabeBold);
		 leafGA.setTextSize(24f);
		 leafGA.setText(tor.get(7));
		 
		 oppGA.setTypeface(kabeBold);
		 oppGA.setTextSize(24f);
		 
		 leafSTK.setTypeface(kabeBold);
		 leafSTK.setTextSize(24f);
		 leafSTK.setText(tor.get(8));
		 
		 oppSTK.setTypeface(kabeBold);
		 oppSTK.setTextSize(24f);
		 
		 setOpposition();//sets the data for the opposition		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comparisons, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		setWhite();//to set white before every comparison
		//for whatever team is selected, set the data to correspond
		if (id == R.id.boston) {
			opp = gI.getStats("Boston");
			
			setOpposition();
			return true;
		}
		if (id == R.id.buffalo) {
			opp = gI.getStats("Buffalo");
			setOpposition();
			return true;
		}
		if (id == R.id.detroit) {
			opp = gI.getStats("Detroit");
			setOpposition();
			return true;
		}
		if (id == R.id.florida) {
			opp = gI.getStats("Florida");
			setOpposition();
			return true;
		}
		if (id == R.id.montreal) {
			opp = gI.getStats("Montreal");
			setOpposition();
			return true;
		}
		if (id == R.id.ottawa) {
			opp = gI.getStats("Ottawa");
			setOpposition();
			return true;
		}
		if (id == R.id.tampa) {
			opp = gI.getStats("Tampa");
			setOpposition();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	//sets the data back to white text
	public void setWhite(){
		
		leafGP.setTextColor(getResources().getColor(R.color.leafsWhite));
		leafW.setTextColor(getResources().getColor(R.color.leafsWhite));	
		leafL.setTextColor(getResources().getColor(R.color.leafsWhite));
		leafOTL.setTextColor(getResources().getColor(R.color.leafsWhite));	
		leafPTS.setTextColor(getResources().getColor(R.color.leafsWhite));	
		leafGF.setTextColor(getResources().getColor(R.color.leafsWhite));	
		leafGA.setTextColor(getResources().getColor(R.color.leafsWhite));	
		oppGP.setTextColor(getResources().getColor(R.color.leafsWhite));
		oppW.setTextColor(getResources().getColor(R.color.leafsWhite));	
		oppL.setTextColor(getResources().getColor(R.color.leafsWhite));
		oppOTL.setTextColor(getResources().getColor(R.color.leafsWhite));	
		oppPTS.setTextColor(getResources().getColor(R.color.leafsWhite));	
		oppGF.setTextColor(getResources().getColor(R.color.leafsWhite));	
		oppGA.setTextColor(getResources().getColor(R.color.leafsWhite));	
	}
	//sets the views to show the selected teams data and indicates which is larger
	//**note: if opp and tor data are equal, tor will be higlighted due to home team advantage
	public void setOpposition(){
		
		oppTitle.setText(opp.get(0));
		
		oppGP.setText(opp.get(1));
		if(Integer.parseInt(opp.get(1))>Integer.parseInt(tor.get(1))){
			oppGP.setTextColor(getResources().getColor(R.color.larger));
		}else{
			leafGP.setTextColor(getResources().getColor(R.color.larger));
		}
		
		oppW.setText(opp.get(2));
		if(Integer.parseInt(opp.get(2))>Integer.parseInt(tor.get(2))){
			oppW.setTextColor(getResources().getColor(R.color.larger));
		}else{
			leafW.setTextColor(getResources().getColor(R.color.larger));
		}
		
		oppL.setText(opp.get(3));
		if(Integer.parseInt(opp.get(3))>Integer.parseInt(tor.get(3))){
			oppL.setTextColor(getResources().getColor(R.color.larger));
		}else{
			leafL.setTextColor(getResources().getColor(R.color.larger));
		}
		
		oppOTL.setText(opp.get(4));
		if(Integer.parseInt(opp.get(4))>Integer.parseInt(tor.get(4))){
			oppOTL.setTextColor(getResources().getColor(R.color.larger));
		}else{
			leafOTL.setTextColor(getResources().getColor(R.color.larger));
		}
		
		oppPTS.setText(opp.get(5));
		if(Integer.parseInt(opp.get(5))>Integer.parseInt(tor.get(5))){
			oppPTS.setTextColor(getResources().getColor(R.color.larger));
		}else{
			leafPTS.setTextColor(getResources().getColor(R.color.larger));
		}
		
		oppGF.setText(opp.get(6));
		if(Integer.parseInt(opp.get(6))>Integer.parseInt(tor.get(6))){
			oppGF.setTextColor(getResources().getColor(R.color.larger));
		}else{
			leafGF.setTextColor(getResources().getColor(R.color.larger));
		}
		
		oppGA.setText(opp.get(7));
		if(Integer.parseInt(opp.get(7))>Integer.parseInt(tor.get(7))){
			oppGA.setTextColor(getResources().getColor(R.color.larger));
		}else{
			leafGA.setTextColor(getResources().getColor(R.color.larger));
		}
		
		oppSTK.setText(opp.get(8));
		
		if(tor.get(8).charAt(0) == 'W'){
			//if both on win streak
			if(opp.get(8).charAt(0) == 'W'){
				//if torontos win streak is better
				if((int)tor.get(8).charAt(1) >= (int)opp.get(8).charAt(1)){
					leafSTK.setTextColor(getResources().getColor(R.color.larger));
				}else{
					oppSTK.setTextColor(getResources().getColor(R.color.larger));
				}
			}else{
				leafSTK.setTextColor(getResources().getColor(R.color.larger));
			}
		}else{
			//if both on loss streak
			if(opp.get(8).charAt(0) == 'L'){
				//if toronto's loss streak is less
				if((int)tor.get(8).charAt(1) < (int)opp.get(8).charAt(1)){
					leafSTK.setTextColor(getResources().getColor(R.color.larger));
				}else{
					oppSTK.setTextColor(getResources().getColor(R.color.larger));
				}
			}else{
				oppSTK.setTextColor(getResources().getColor(R.color.larger));
			}
		}
		
	}
}
