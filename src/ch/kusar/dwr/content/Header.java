/**
 * Creates the channels content.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 * 
 */

package ch.kusar.dwr.content;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import ch.kusar.dwr.R;
import ch.kusar.dwr.dialog.ShowDetailsListener;
import ch.kusar.dwr.layout.ButtonCommandEnum;

public class Header implements View.OnClickListener {
	private FragmentManager fragmentManager = null;
	private static Header instance = null;

	public Header(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}

	public static Header getInstance(FragmentManager fragmentManager) {
		if (instance == null) {
			instance = new Header(fragmentManager);
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
	public View getView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.header, container, false);
		
		final ImageView imageViewLogo = (ImageView) view.findViewById(R.id.imageViewLogo);
		imageViewLogo.setOnClickListener(this);

		final ImageButton imageButtonHeaderRefresh = (ImageButton) view
				.findViewById(R.id.imageButtonHeaderRefresh);
		imageButtonHeaderRefresh.setOnClickListener(this);

		final ImageButton imageButtonHeaderSettings = (ImageButton) view
				.findViewById(R.id.imageButtonHeaderSettings);
		imageButtonHeaderSettings.setBackgroundColor(Color.TRANSPARENT);
		imageButtonHeaderSettings.setOnClickListener(this);

		final ImageButton imageButtonHeaderMessage = (ImageButton) view
				.findViewById(R.id.imageButtonHeaderMessage);
		imageButtonHeaderMessage.setBackgroundColor(Color.TRANSPARENT);
		imageButtonHeaderMessage.setOnClickListener(this);

		final ImageButton imageButtonHeaderSearch = (ImageButton) view
				.findViewById(R.id.imageButtonHeaderSearch);
		imageButtonHeaderSearch.setBackgroundColor(Color.TRANSPARENT);
		imageButtonHeaderSearch.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		ShowDetailsListener sdl = (ShowDetailsListener) fragmentManager
				.findFragmentById(R.id.headerfragment);

		if (v.getId() == R.id.imageButtonHeaderRefresh) {
			return;
		}
		if (v.getId() == R.id.imageButtonHeaderSettings) {
			sdl.onButtonPress(ButtonCommandEnum.SETUP.ordinal());
			return;
		}
		if (v.getId() == R.id.imageButtonHeaderMessage) {
			sdl.onButtonPress(ButtonCommandEnum.MESSAGE.ordinal());
			return;
		}
		if (v.getId() == R.id.imageButtonHeaderSearch) {
			return;
		}
	}
}
