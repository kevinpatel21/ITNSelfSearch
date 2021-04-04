import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that controls the homeview when a user clicks on a button
 */
public class homeController
{
    // This is where we would put our class variables for namefilter and tagfilter didnt include because we are not in that project
    // private nameFilter nf
    // private tagFilter tf
    private homeView view;
    private NameFilter namefilter;
    private TagFilter tagfilter;
    private Database database;
    private Product retrievedProduct;
    private final ArrayList<ChangeListener> nameSearchListener = new ArrayList<ChangeListener>();//ArrayList of listeners

    /**
     * Constructor for homeController when you create the object for it you will initalize variables needed and go to the initView()
     */
    public homeController(homeView v, NameFilter nf, TagFilter tf, Database d)
    {
        namefilter = nf;
        tagfilter = tf;
        view = v;
        database = d;

        initView();
    }


    /**
     * Sets the text in the text field
     */
    public void initView()
    {
        view.getUserText().setText("Search here");
    }

    /**
     * Use the variable in main to call initController after you run the GUI so that you can have button functionality
     * This will check if searchbutton gets pressed from the view if it does then you add an actionlistener and go to search function
     */
    public void initController()
    {
        // These are our actionlisteners when one is clicked it goes to that function and does that action
        view.getSearchButton().addActionListener(e -> search());
        view.getAdminButton().addActionListener(e -> adminView());
    }

    /**
     * The search function adds the button logic if namefilter is toggled then you will execute the nameFilter class
     * Contains all logic for the search button.
     */
    private void search()
    {
        // This will execute if the namefilter is toggled and if the tagfilter is not selected
        if(view.getNameFilterToggle().isSelected() && !view.getTagFilterToggle().isSelected())
        {

            if(database.validProductName(view.getUserText().getText()))
            {
                Product userProduct = namefilter.retrieveByName(view.getUserText().getText(), database);

                System.out.print(userProduct.getProductName() + ": " + userProduct.getProductPrice());

                for(String tag: userProduct.getProductTags())
                {
                    System.out.print(" ," + tag);
                }

                retrievedProduct = userProduct;

                ChangeEvent nameSearchSelected = new ChangeEvent(this);
                for(ChangeListener listener: nameSearchListener){
                    listener.stateChanged(nameSearchSelected);
                }

                System.out.println();
            }
            else if(!database.validProductName(view.getUserText().getText()))
            {
                System.out.println("There was no product in the database with the name " + "'" +view.getUserText().getText() + "'");
            }
        }
        // This will execute if the TagFilterToggle is selected and if the name filter is not selected
        else if(view.getTagFilterToggle().isSelected() && !view.getNameFilterToggle().isSelected())
        {
            //OFF state
            Scanner stringParser = new Scanner(view.getUserText().getText());
            ArrayList<String> parsedTags = new ArrayList<String>();
            ArrayList<Product> retrievedProducts = new ArrayList<Product>();

            while(stringParser.hasNext())
            {
                parsedTags.add(stringParser.next());
            }

            retrievedProducts = tagfilter.retrieveByTags(parsedTags, database);

            for (Product product: retrievedProducts)
            {
                System.out.print(product.getProductName() + ": " + product.getProductPrice());

                for(String tag: product.getProductTags())
                {
                    System.out.print(", " + tag);
                }

                System.out.println();
            }
        }
        else if(view.getTagFilterToggle().isSelected() && view.getNameFilterToggle().isSelected())
        {
            JOptionPane.showMessageDialog(null,"Cant search with both filters toggled on. Please only select 1 filter ", "Error",JOptionPane.ERROR_MESSAGE);
        }

        else if(!view.getTagFilterToggle().isSelected() && !view.getNameFilterToggle().isSelected())
        {
            JOptionPane.showMessageDialog(null, "Cant search with no filters selected. Please select a filter", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * Currently working on adminView
     */
    private void adminView()
    {
        adminView a = new adminView();
        
    }

    /**
     * Returns retrieved product for ProductGUI to display
     */
    public Product getRetrievedProduct(){
        return retrievedProduct;
    }

    //Sets
    /**
     * Function used to determine if user searches by name
     * @param newListener an input listener
     */
    public void addNameSearchListener(ChangeListener newListener){
        nameSearchListener.add(newListener);
    }


}