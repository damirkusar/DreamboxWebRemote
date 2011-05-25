/**
 * Creates the channels content.
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
import ch.kusar.dwr.commands.EpgCommands;

public class ContentEpg implements View.OnClickListener{
	private static ContentEpg instance = null;

	public static ContentEpg getInstance() {
		if (instance == null) {
			instance = new ContentEpg();
		}
		return instance;
	}

	/**
	 * Generates a view from the channels layout.
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return View. The generated View.
	 */
	public View getView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.content_remote, container, false);

		final Button button1 = (Button) view.findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new EpgCommands().loadCurrentEPG();
			}
		});
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	
}
