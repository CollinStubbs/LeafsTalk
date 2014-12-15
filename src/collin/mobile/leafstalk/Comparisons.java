package collin.mobile.leafstalk;

import java.util.ArrayList;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Comparisons extends ActionBarActivity {

	TextView leafTitle, oppTitle, leafGP, oppGP, leafW, oppW, leafL, oppL,leafOTL, oppOTL, leafGF, oppGF, leafGA, oppGA, leafSTK, oppSTK; 
	GetComp gI;
	ArrayList<String> opp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comparisons);
		
		gI = new GetComp();
		gI.execute();
		
		
		  try {
		    	while(gI.getCheck() == 0){
		    		Thread.sleep(100);         
		    	}
		    } catch (InterruptedException e) {
		       e.printStackTrace();
		    }
		  
		  ArrayList<String> tor = gI.getStats("Toronto");
		  opp = gI.getStats("Ottawa");
		  
		 Typeface kabe=Typeface.createFromAsset(getAssets(), "fonts/Kabel_Regular.ttf");
		 Typeface kabeBold=Typeface.createFromAsset(getAssets(), "fonts/Kabel_Bold.ttf");
		
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
		 
		 leafGF =(TextView)findViewById(R.id.leafsGF);
		 oppGF =(TextView)findViewById(R.id.oppGF);
		 
		 leafGA =(TextView)findViewById(R.id.leafsGA);
		 oppGA =(TextView)findViewById(R.id.oppGA);
		 
		 leafSTK =(TextView)findViewById(R.id.leafsSTK);
		 oppSTK =(TextView)findViewById(R.id.oppSTK);
		 
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
		 
		 leafGF.setTypeface(kabeBold);
		 leafGF.setTextSize(24f);
		 leafGF.setText(tor.get(5));
		 
		 oppGF.setTypeface(kabeBold);
		 oppGF.setTextSize(24f);
		 
		 leafGA.setTypeface(kabeBold);
		 leafGA.setTextSize(24f);
		 leafGA.setText(tor.get(6));
		 
		 oppGA.setTypeface(kabeBold);
		 oppGA.setTextSize(24f);
		 
		 leafSTK.setTypeface(kabeBold);
		 leafSTK.setTextSize(24f);
		 leafSTK.setText(tor.get(7));
		 
		 oppSTK.setTypeface(kabeBold);
		 oppSTK.setTextSize(24f);
		 
		 setOpposition();
		 
		
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
	public void setOpposition(){
		oppTitle.setText(opp.get(0));
		oppGP.setText(opp.get(1));
		oppW.setText(opp.get(2));
		oppL.setText(opp.get(3));
		oppOTL.setText(opp.get(4));
		oppGF.setText(opp.get(5));
		oppGA.setText(opp.get(6));
		oppSTK.setText(opp.get(7));
	}
}
