/**
  * The ContentActivity class creates an intent if the layout has no second fragment in the layout, 
 * In an landscape layout with two layouts there will be shown inline.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class
 *  
 */

package ch.kusar.dwr.layout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * The ContentActivity class creates an intent if the layout has no second
 * fragment in the layout. In an landscape layout with two layouts there will be
 * shown inline.
 */
public class ContentActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// If the screen is now in landscape mode, we can show the
		// dialog in-line so we don't need this activity.
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}

		// If the screen is not in landscape mode, it creates a new intent and
		// shows it in the whole screen.
		if (savedInstanceState == null) {
			// During initial setup, plug in the details fragment.
			ContentFragment contentFragment = new ContentFragment();
			contentFragment.setArguments(getIntent().getExtras());
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, contentFragment).commit();
		}
	}
}