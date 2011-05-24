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
import ch.kusar.dwr.R;
import ch.kusar.dwr.dialog.ShowDetailsListener;
import ch.kusar.dwr.layout.ButtonCommandEnum;

public class StartScreen implements View.OnClickListener {
	private FragmentManager fragmentManager = null;
	private static StartScreen instance = null;

	public StartScreen(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}

	public static StartScreen getInstance(FragmentManager fragmentManager) {
		if (instance == null) {
			instance = new StartScreen(fragmentManager);
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

		View view = inflater.inflate(R.layout.startscreen, container, false);

		final ImageButton buttonRemote = (ImageButton) view
				.findViewById(R.id.buttonRemote);
		buttonRemote.setBackgroundColor(Color.TRANSPARENT);
		buttonRemote.setOnClickListener(this);

		final ImageButton buttonTV = (ImageButton) view
				.findViewById(R.id.buttonTV);
		buttonTV.setBackgroundColor(Color.TRANSPARENT);
		buttonTV.setOnClickListener(this);

		final ImageButton buttonEPG = (ImageButton) view
				.findViewById(R.id.buttonEPG);
		buttonEPG.setBackgroundColor(Color.TRANSPARENT);
		buttonEPG.setOnClickListener(this);

		final ImageButton buttonMEPG = (ImageButton) view
				.findViewById(R.id.buttonMEPG);
		buttonMEPG.setBackgroundColor(Color.TRANSPARENT);
		buttonMEPG.setOnClickListener(this);

		final ImageButton buttonRadio = (ImageButton) view
				.findViewById(R.id.buttonRadio);
		buttonRadio.setBackgroundColor(Color.TRANSPARENT);
		buttonRadio.setOnClickListener(this);

		final ImageButton buttonRecorded = (ImageButton) view
				.findViewById(R.id.buttonRecorded);
		buttonRecorded.setBackgroundColor(Color.TRANSPARENT);
		buttonRecorded.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		ShowDetailsListener sdl = (ShowDetailsListener) fragmentManager
				.findFragmentById(R.id.headerfragment);
		if (v.getId() == R.id.buttonRemote) {
			sdl.onButtonPress(ButtonCommandEnum.REMOTE.ordinal());
			return;
		}
		if (v.getId() == R.id.buttonEPG) {
			sdl.onButtonPress(ButtonCommandEnum.EPG.ordinal());
			return;
		}
		if (v.getId() == R.id.buttonMEPG) {
			sdl.onButtonPress(ButtonCommandEnum.MEPG.ordinal());
			return;
		}
		if (v.getId() == R.id.buttonTV) {
			sdl.onButtonPress(ButtonCommandEnum.TV.ordinal());
			return;
		}
		if (v.getId() == R.id.buttonRadio) {
			sdl.onButtonPress(ButtonCommandEnum.RADIO.ordinal());
			return;
		}
		if (v.getId() == R.id.buttonRecorded) {
			sdl.onButtonPress(ButtonCommandEnum.RECORDED.ordinal());
			return;
		}
	}
}
