import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class DynamicMain{
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
                //testDatabase.displayActiveDatabase();
                testDatabase.updateDatabase(testControl.overrideDatabase());
                testDatabase.displayActiveDatabase();

                ChangeEvent importready = new ChangeEvent(this);

                for(ChangeListener listener: importListeners){
                    listener.stateChanged(importready);
                }






                /**
                 * Use this section below as main
                 */

                //Use this to get ActiveDatabase
                getTestDatabase();


                // Harrisons GUI test code
                NameFilter nf = new NameFilter();
                TagFilter tf = new TagFilter();

                homeView v = new homeView();
                homeController c = new homeController(v, nf, tf, getTestDatabase().getDatabase());
                c.initController();












































                /**
                 * End of main
                 */

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
