/**
 * This class is to send end receive data to and from Dreambox. 
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class 
 * 
 */

package ch.kusar.dwr.commands;


public class MessageCommands extends Commands {

	/**
	 * This Method sends a message to the Dreambox.
	 * 
	 * @param message
	 */
	public void sendMessage(String message) {
		httpGetDreamBox(getMessagePath(), message);
		httpGetExecute();
	}
}
