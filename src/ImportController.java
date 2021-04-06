import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class used to mediate the importation process.
 * If the importation process was successful, class can return a copy of the imported database.
 */
public class ImportController extends JFrame{
    //Attributes
    private Database newDatabase;//Stores data extracted from importation process
    private ImportView importWindow;//Panel used to allow admin to search for an input file
    private DatabaseDisplay databasePanel;//Panel used to display imported database contents
    private boolean importable;//Used to determine if an import was successful
    final ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();//ArrayList of listeners
    final ArrayList<ChangeListener> mainlistener = new ArrayList<ChangeListener>();//ArrayList of listeners
    private boolean programStart;

    //Constructors
    /**
     * Constructor for ImportController
     */
    public ImportController(boolean firstImport){
        //Setting up frame design/layout
        importable = false;
        programStart = firstImport;

        JPanel viewSet = new JPanel(new CardLayout());
        importWindow = new ImportView();
        viewSet.add(importWindow, "importWindow");

        this.add(viewSet);
        this.setSize(300, 350);//Setting size of frame
        this.setTitle("Import Viewer");//Title seen at top border of JFrame window
        this.setVisible(true);//we want to see the frame right?
        this.setLocationRelativeTo(null);//Centers window!!
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Allows the frame to be closed


        //If an import was successful, panel switches to database view and allows admin to save or cancel
        importWindow.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Switching panel to database view
                importable = true;
                databasePanel = new DatabaseDisplay(importWindow.getNewImport());
                JButton saveButton = new JButton("Save");
                JButton cancelButton = new JButton("Cancel");

                viewSet.add(databasePanel, "databasePanel");
                CardLayout cardlayout = (CardLayout) (viewSet.getLayout());
                cardlayout.show(viewSet, "databasePanel");

                //If saved, listeners are notified, database import is saved, and user is prompted
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ChangeEvent importable = new ChangeEvent(this);
                        newDatabase = importWindow.getNewImport().clone();
                        importWindow.clearNewImport();

                        JOptionPane.showMessageDialog(null, "Database has been imported!", "Import Confirmation", JOptionPane.INFORMATION_MESSAGE);

                        if(firstImport == true){
                            for(ChangeListener listener: mainlistener){
                                listener.stateChanged(importable);
                            }
                        }
                        else{
                            for(ChangeListener listener: listeners){
                                listener.stateChanged(importable);
                            }
                        }
                        cardlayout.show(viewSet, "importWindow");
                    }
                });

                //If canceled, imported data from input file contents is erased, user is notified and returned to the file search panel
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        importWindow.clearNewImport();
                        JOptionPane.showMessageDialog(null, "Import aborted!", "Import Canceled", JOptionPane.INFORMATION_MESSAGE);
                        cardlayout.show(viewSet, "importWindow");
                    }
                });

                databasePanel.add(saveButton);
                databasePanel.add(cancelButton);
            }
        });
    }

    //Sets
    /**
     * Function used to add as listener to the class
     * @param newListener an input listener
     */
    public void addChangeListener(ChangeListener newListener){
        listeners.add(newListener);
    }
    //Sets
    /**
     * Function used to determine if this was the first import of software's runtime
     * @param newListener an input listener
     */
    public void addFirstImportListener(ChangeListener newListener){
        mainlistener.add(newListener);
    }
    /**
     * Clears data acquired from importation process
     */
    public void clearNewImport(){
        newDatabase = new Database();
    }

    /**
     * Function used to return a database clone containing the imported data
     * @return Returns a copy of the imported contents via a database object
     */
    //Gets
    public Database overrideDatabase() {
        return newDatabase.clone();
    }

    //Functionality
    /**
     * Function used to determine if an input file was imported correctly
     * @return returns true if successful, false if not
     */
    public boolean canImport(){
        return importable;
    }

    /**
     * Function used to determine if program is initializing
     * @return returns true if this is the first import, false if not
     */
    public boolean firstImport(){
        return programStart;
    }
}
