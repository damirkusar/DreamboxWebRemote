/**
 * This is the OnDialogDoneListener for the DialogPromptFragment.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 16.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.dialog;

public interface ShowDetailsListener {
	/**
	 * The interface that must be implemented to call to something when details
	 * should be shown.
	 * 
	 * @param tag
	 * @param details
	 */
	public void onShowDetails(int details);
}
