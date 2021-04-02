import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class DynamicMain extends JFrame {
    //Creating a database for the software to use
    private ActiveDatabase testDatabase = new ActiveDatabase();
    //Creating a class for importing a new database
    private ImportController testControl = new ImportController();
    final ArrayList<ChangeListener> importListeners = new ArrayList<ChangeListener>();//ArrayList of listeners

    public DynamicMain(){


        //Updates database if admin finds an import file and confirms import
        testControl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                testDatabase.displayActiveDatabase();
                testDatabase.updateDatabase(testControl.overrideDatabase());
                testDatabase.displayActiveDatabase();

                ChangeEvent importready = new ChangeEvent(this);

                for(ChangeListener listener: importListeners){
                    listener.stateChanged(importready);
                }

                /**
                 * Add Main Menu Call Here
                 */

                //Add namefilter changelistener to main
                //Add tag filter changelistener to main
                //Add Admin tag filter here

            }
        });
    }

    public int getProductSize(){
        return testDatabase.getProducts().size();
    }

    public void addImportedListener(ChangeListener newListener){
        importListeners.add(newListener);
    }

    public ActiveDatabase getTestDatabase() {
        return testDatabase;
    }
}
