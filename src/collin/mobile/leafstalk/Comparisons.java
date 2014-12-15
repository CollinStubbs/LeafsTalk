package collin.mobile.leafstalk;

import java.util.ArrayList;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Comparisons extends ActionBarActivity {

	TextView leafTitle, oppTitle; 
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
		
		 leafTitle.setTypeface(kabeBold);
		 leafTitle.setTextSize(24f);
		 leafTitle.setText(tor.get(0));
		 
		 oppTitle.setTypeface(kabeBold);
		 oppTitle.setTextSize(24f);
		 oppTitle.setText(opp.get(0));
		 
		
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
	}
}
