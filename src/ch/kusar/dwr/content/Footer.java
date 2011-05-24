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
import android.widget.TextView;
import ch.kusar.dwr.R;

public class Footer implements View.OnClickListener {
	private FragmentManager fragmentManager = null;

	public Footer(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
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

		View view = inflater.inflate(R.layout.footer, container, false);

		final ImageButton imageButtonFooterRecord = (ImageButton) view
				.findViewById(R.id.imageButtonFooterRecord);
		imageButtonFooterRecord.setBackgroundColor(Color.TRANSPARENT);
		imageButtonFooterRecord.setOnClickListener(this);

		final TextView textViewFooterChanel = (TextView) view
				.findViewById(R.id.textViewFooterChanel);
		textViewFooterChanel.setOnClickListener(this);

		final TextView textViewFooterShow = (TextView) view
				.findViewById(R.id.textViewFooterShow);
		textViewFooterShow.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {

	}
}
