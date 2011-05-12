/**
 * Creates the recorded content.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import ch.kusar.dwr.R;

public class ContentRecorded {

	/**
	 * Generates a view from the recorded layout.
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return View. The generated View.
	 */
	public static View getRecordedView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.epg, container, false);

		final Button button2 = (Button) view.findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				button2.setText("damir");
			}
		});
		return view;
	}
}
