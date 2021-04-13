import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class productSelection extends JPanel
{
    // Create our JPanels that we need
    private JPanel listPanel;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;

    // Our data variable that we need for products
    private ArrayList<Product> productList;

    // This is what we will store our products into so we can display it on screen
    private JList listOfProducts;

    // Create the buttons for select and cancel
    private JButton select;
    private JButton cancel;

    // Our arraylist that has the product names
    private ArrayList<String> productNames = new ArrayList();

    // Database
    private Database database;

    // Variable for product
    private Product p;

    // Variable for our listeners
    private final ArrayList<ChangeListener> cancelListener = new ArrayList<ChangeListener>();//ArrayList of listeners
    private final ArrayList<ChangeListener> selectListener = new ArrayList<ChangeListener>();//ArrayList of listeners

    /**
     * This is the constructor for our productSelection GUI
     * @param pList it takes in the ArrayList productList
     * @param d it takes in the Database d
     */
    public productSelection(ArrayList<Product> pList, Database d)
    {
        productList = pList;
        database = d;
        getProductNames();
        createPanel();
    }

    /**
     * This function is simply going to get the productNames from the productList and store it into an ArrayList productNames
     *
     */
    private void getProductNames()
    {
        for(Product product: productList)
        {
            productNames.add(product.getProductName());
        }
    }

    private void createPanel()
    {

        // Declare our panels that we need
        buttonPanel = new JPanel();
        listPanel = new JPanel();

        // Set our layouts for our panels
        BoxLayout boxLayout = new BoxLayout(listPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        this.setLayout(new BorderLayout());

        // Lets declare our buttons that we need
        select = new JButton("Select");
        cancel = new JButton("Back");

        // Create our list of products and store our product list inside it. Also set some settings for it
        listOfProducts = new JList(productNames.toArray());
        listOfProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listOfProducts.setFont(new Font("Serif", Font.ITALIC, 14));
        listOfProducts.setLayoutOrientation(JList.VERTICAL);

        // Now we need to create the scroll pane incase the list gets to be too long
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(listOfProducts);
        scrollPane.setPreferredSize(new Dimension(570,300));

        // Now lets create the settings for our buttonPanel for our select and cancel buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(select);
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
        buttonPanel.add(cancel);

        // Lets now add the scrollpane to our listPanel
        listPanel.add(scrollPane);

        // Now lets add both listPanel and buttonPanel to our masterPanel that is being extended
        this.add(listPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);

        // Add action listeners for the select button and cancel button
        select.addActionListener(e -> prepareProduct());
        cancel.addActionListener(e -> updateCancel());

    }

    /**
     * This function is only executed if the user selects the select button
     */
    private void prepareProduct()
    {
        // This is the variable that we are going to put the selected product into
        p = new Product();
        // This array list is going to help us find the correct product in the database
        ArrayList<Product> products = new ArrayList();

        // This is our selection variable where we will get the selected input from user
        // We will later compare this variable with the products ArrayList
        String selection = " ";


        // Checks to see if the listOfProducts is empty if so Display a JOptionPane MessageDialog
        if(listOfProducts.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null, "You have not made any selections");
        }
        // Other wise listOfProducts is not empty
        else
        {
            // Set the selection variable equal to the userSelection. So if the user selects apple
            // Put apple into selection by using listOfProducts
            selection = listOfProducts.getSelectedValue().toString();

            // Then we need to get the productCatalogue from database and put it into products
            // This way we can compare the selection variable with product names in the database
            // And determine if it is equal
            products = database.getProductCatalogue();

            // This for loop will go over all of the products in the database and get the one with the matching name and put the product into
            // our product variable p
            for(int i = 0; i < database.getProductCounter(); i++)
            {
                // This if statement is simply trying to determine where our product is in our database with the string value selection
                if(selection == products.get(i).getProductName())
                {
                    // If this executes then we found our product
                    p = products.get(i);

                    // Once it has found it then tell dynamic main to switch panels to the ProductGUI panel
                    ChangeEvent selectSelected = new ChangeEvent(this);
                    for(ChangeListener listener: selectListener)
                    {
                        listener.stateChanged(selectSelected);
                    }
                }
            }
        }
    }


    /**
     * This function is only when user selects cancel which then takes them back to the tagMenu panel
     */
    private void updateCancel()
    {
        // Updates dynamic main to switch the panel to tagMenu from productSelection
        ChangeEvent cancelSelected = new ChangeEvent(this);
        for(ChangeListener listener: cancelListener)
        {
            listener.stateChanged(cancelSelected);
        }
    }

    /**
     * This function is used to listen for the cancel button and adds newListener to the ArrayList cancelListener
     */
    public void addCancelListener(ChangeListener newListener)
    {
        cancelListener.add(newListener);
    }

    /**
     * This function is used to listen for the select button and adds newListener to the ArrayList selectListener
     */
    public void addSelectListener(ChangeListener newListener)
    {
        selectListener.add(newListener);
    }

    /**
     * This function is used to get our Product which is needed for ProductGUI
     * @return returns our selected product from the productSelection screen
     */
    public Product getP()
    {
        return p;
    }

}