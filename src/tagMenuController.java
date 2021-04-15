import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class controls our buttons in tagMenu GUI
 */
public class tagMenuController
{
    // Declare the variables needed for the control of tagMenu
    private tagMenu menu;
    private Database database;
    private TagFilter tagfilter;

    // Our ArrayLists for inputTags and retrievedProducts
    private ArrayList<String> inputTags;
    private ArrayList<Product> retrievedProducts;

    // These are our change listeners that are needed to update dynamic main
    private final ArrayList<ChangeListener> searchListener = new ArrayList<ChangeListener>();//ArrayList of listeners
    private final ArrayList<ChangeListener> backListener = new ArrayList<ChangeListener>();//ArrayList of listeners


    /**
     * This is the constructor for tagMenuController takes in 3 parameters that are needed
     * @param m This is the tagMenu it takes in the tagMenu and assigns it to menu
     * @param d This is the Database it takes in the database and assigns it to database
     * @param tf This is the tagFilter is takes in the tagFilter and assigns it to tagFilter
     */
    public tagMenuController(tagMenu m, Database d, TagFilter tf)
    {
        menu = m;
        inputTags = new ArrayList();
        tagfilter = tf;
        database = d;
        retrievedProducts = new ArrayList();
    }

    /**
     * When this function is called it is listening for when our search button and our cancel button from tagMenu is hit
     * When this happens it goes to our functions search() and cancel()
     */
    public void initController()
    {
        //ActionListeners for when A button is clicked
        menu.getSearch().addActionListener(e -> search());
        menu.getCancel().addActionListener(e -> cancel());

    }

    /**
     * This function is self explanatory when the user selects cancel it updates dynamic main to go back to the main menu
     */
    private void cancel()
    {
        ChangeEvent backButtonSelected = new ChangeEvent(this);
        for(ChangeListener listener: backListener)
        {
            listener.stateChanged(backButtonSelected);
        }
    }

    /**
     * This function is when the user selects the search button it will take the input selected by the user
     * It then calls the tagFilter function retrieveByTags that takes in the inputTags, and the database
     * Then it returns a product and puts it into retrievedProducts which is an arrayList of products that have the input tags
     * Then at the end of the function we have a change listener that updates Dynamic main to switch to the productSelection panel
     *
     *  We also have a if statement that tells us if the user selection is empty which then displays a JOptionPane
     */
    private void search()
    {
        // This array indices will store the items selected in the JList
        int indices[] = menu.getListOfButtons().getSelectedIndices();

        // If the user does not select any tags then display and error message
        if(menu.getListOfButtons().isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null, "You have not made any selections");
        }
        // Otherwise add the selected indices into the inputTags arrayList
        else
        {
            // This For loop adds the selected indices into the inputTags
            for(int i = 0; i < indices.length; i++)
            {
                inputTags.add(menu.getUniqueTags().get(indices[i]));
            }

            // Call tagFilter.retrieveByTags and send it our ArrayList inputTags and the database and store the products into
            // the arrayList retrieveProducts
            retrievedProducts = tagfilter.retrieveByTags(inputTags, database);

            // Let Dynamic main know that we have selected search and it needs to switch to the productSelection panel
            ChangeEvent searchSelected = new ChangeEvent(this);
            for(ChangeListener listener: searchListener){
                listener.stateChanged(searchSelected);
            }

        }

    }

    /**
     * This function is used to listen for the back button and adds newListener to the ArrayList backListener
     * @param newListener listener to add to the backListener ArrayList
     */
    public void addBackListener(ChangeListener newListener)
    {
        backListener.add(newListener);
    }

    /**
     * This function is used to listen for the Search button and adds newListener to the ArrayList searchListener
     * @param newListener listener to add to the searchListener ArrayList
     */
    public void addSearchListener(ChangeListener newListener)
    {
        searchListener.add(newListener);
    }

    /**
     * This function is a get for our RetrievedProducts array
     * We need this to populate our productSelection screen with products
     * @return ArrayList retrievedProducts
     */
    public ArrayList<Product> getRetrievedProducts()
    {
        return retrievedProducts;
    }

    /**
     * Function to get the list of selected tags
     * @return A list of selected tags
     */
    public ArrayList<String> getInputTags()
    {
        return inputTags;
    }

}
