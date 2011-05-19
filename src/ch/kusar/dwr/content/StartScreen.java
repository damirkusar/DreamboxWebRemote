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
import ch.kusar.dwr.layout.ContentEnum;

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
	public View getStartScreenView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.startscreen, container, false);

		final ImageButton buttonRemote = (ImageButton) view
				.findViewById(R.id.buttonRemote);
		buttonRemote.setBackgroundColor(Color.TRANSPARENT);
		buttonRemote.setOnClickListener(this);

		final ImageButton buttonTVBouquet = (ImageButton) view
				.findViewById(R.id.buttonTVBouquets);
		buttonTVBouquet.setBackgroundColor(Color.TRANSPARENT);
		buttonTVBouquet.setOnClickListener(this);

		final ImageButton buttonEPG = (ImageButton) view
				.findViewById(R.id.buttonEPG);
		buttonEPG.setBackgroundColor(Color.TRANSPARENT);
		buttonEPG.setOnClickListener(this);

		final ImageButton buttonChannels = (ImageButton) view
				.findViewById(R.id.buttonChannels);
		buttonChannels.setBackgroundColor(Color.TRANSPARENT);
		buttonChannels.setOnClickListener(this);

		final ImageButton buttonRadioBouquet = (ImageButton) view
				.findViewById(R.id.buttonRadioBouquets);
		buttonRadioBouquet.setBackgroundColor(Color.TRANSPARENT);
		buttonRadioBouquet.setOnClickListener(this);

		final ImageButton buttonRecorded = (ImageButton) view
				.findViewById(R.id.buttonRecorded);
		buttonRecorded.setBackgroundColor(Color.TRANSPARENT);
		buttonRecorded.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		ShowDetailsListener sdl = (ShowDetailsListener) fragmentManager
				.findFragmentById(R.id.mainfragment);
		if (v.getId() == R.id.buttonRemote) {
			sdl.onShowDetails(ContentEnum.REMOTE.ordinal());
			return;
		}
		if (v.getId() == R.id.buttonEPG) {
			sdl.onShowDetails(ContentEnum.EPG.ordinal());
			return;
		}
		if (v.getId() == R.id.buttonRecorded) {
			sdl.onShowDetails(ContentEnum.RECORDED.ordinal());
			return;
		}
		if (v.getId() == R.id.buttonChannels) {
			sdl.onShowDetails(ContentEnum.CHANNELS.ordinal());
			return;
		}
		if (v.getId() == R.id.buttonTVBouquets) {
			sdl.onShowDetails(ContentEnum.TV_BOUQUETS.ordinal());
			return;
		}
		if (v.getId() == R.id.buttonRadioBouquets) {
			sdl.onShowDetails(ContentEnum.RADIO_BOUQUETS.ordinal());
			return;
		}
	}
}
