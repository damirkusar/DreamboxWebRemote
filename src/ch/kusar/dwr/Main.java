/**
 * This is the Mainclass. It starts only the mainfragment.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class
 *  
 */

package ch.kusar.dwr;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * This is the Main class and the main entry point for the app.
 */
public class Main extends FragmentActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_actionbar, menu);
		// super.onCreateOptionsMenu(menu, inflater);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Log.e("OverviewFragment.onOptionItemSelected", "Button home");
			return true;
		case R.id.menu_add:
			Log.e("OverviewFragment.onOptionItemSelected", "Button add");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}