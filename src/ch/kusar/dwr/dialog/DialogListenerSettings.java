/**
 * This is the OnDialogDoneListener for the DialogPromptFragment.
 * @author Damir Kusar (damir@kusar.ch)
 * @date 16.05.2011
 * @version 0.1 - Created the class 
 *  
 */

package ch.kusar.dwr.dialog;

public interface DialogListenerSettings {
	/**
	 * The interface that must be implemented to call to something when Dialog is done.
	 * @param tag
	 * @param cancelled
	 * @param message
	 */
	public void onDialogDone(String tag, boolean cancelled, String message);
}
