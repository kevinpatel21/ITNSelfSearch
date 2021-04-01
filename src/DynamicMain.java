import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class DynamicMain extends JFrame {
    //Creating a database for the software to use
    private ActiveDatabase testDatabase = new ActiveDatabase();
    //Creating a class for importing a new database
    private ImportController testControl = new ImportController();
    final ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();//ArrayList of listeners

    public DynamicMain(){


        //Updates database if admin finds an import file and confirms import
        testControl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                testDatabase.displayActiveDatabase();
                testDatabase.updateDatabase(testControl.overrideDatabase());
                testDatabase.displayActiveDatabase();



                /**
                 * Kevin, put your GUI test code here
                 */







                //ProductGUI test = new ProductGUI(testDatabase.getProducts());
                /**
                 *
                 */


            }

        });
    }

    public int getProductSize(){
        return testDatabase.getProducts().size();
    }

    public void addChangeListener(ChangeListener newListener){
        listeners.add(newListener);
    }
}
