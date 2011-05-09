/**
 * @author Damir Kusar (damir@kusar.ch)
 * @date 09.05.2011
 * @version 0.1 - Created the class
 *  
 */

package ch.kusar.dwr;

import ch.kusar.dreamboxwebremote.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;



/**
 * This is the Main class and the main entry point for the app.
 */
public class Main extends FragmentActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    

}