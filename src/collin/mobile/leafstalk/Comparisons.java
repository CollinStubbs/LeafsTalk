package collin.mobile.leafstalk;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Comparisons extends ActionBarActivity {

	TextView leafTitle, oppTitle; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comparisons);
		
		GetComp gI = new GetComp();
		gI.execute();
		//gI.getStats("OTT");
		
		  try {
		    	while(gI.getCheck() == 0){
		    		Thread.sleep(100);         
		    	}
		    } catch (InterruptedException e) {
		       e.printStackTrace();
		    }
		
		 Typeface kabe=Typeface.createFromAsset(getAssets(), "fonts/Kabel_Regular.ttf");
		 Typeface kabeBold=Typeface.createFromAsset(getAssets(), "fonts/Kabel_Bold.ttf");
		
		 leafTitle =(TextView)findViewById(R.id.leafsTitle);
		 oppTitle =(TextView)findViewById(R.id.oppTitle);
		
		 leafTitle.setTypeface(kabeBold);
		 leafTitle.setTextSize(24f);
		 
		 oppTitle.setTypeface(kabeBold);
		 oppTitle.setTextSize(24f);
		 oppTitle.setText("OTT");
		
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
