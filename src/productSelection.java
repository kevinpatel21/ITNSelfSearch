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

    // Test Frame
    private JFrame frame3;

    // Database
    private Database database;

    // Variable for product
    private Product p;

    // Variable for our listeners
    private final ArrayList<ChangeListener> cancelListener = new ArrayList<ChangeListener>();//ArrayList of listeners
    private final ArrayList<ChangeListener> selectListener = new ArrayList<ChangeListener>();//ArrayList of listeners

    public productSelection(ArrayList<Product> pList, Database d)
    {
        productList = pList;
        database = d;
        getProductNames();
        createPanel();
    }

    private void getProductNames()
    {
        for(Product product: productList)
        {
            productNames.add(product.getProductName());
        }
    }

    public void createPanel()
    {
        /*// Set up thr frame with a few settings
        frame3 = new JFrame();
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setSize(600, 400);
        frame3.setResizable(true);
        frame3.setLocationRelativeTo(null);
        frame3.setVisible(true);
        frame3.setTitle("Tag Menu"); */

        // Declare our panels that we need
        buttonPanel = new JPanel();
        listPanel = new JPanel();

        // Set our layouts for our panels
        BoxLayout boxLayout = new BoxLayout(listPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        this.setLayout(new BorderLayout());

        // Lets declare our buttons that we need
        select = new JButton("Select");
        cancel = new JButton("Cancel");

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

        //frame3.add(this);

        // Add action listeners for the select button and cancel button
        select.addActionListener(e -> prepareProduct());
        cancel.addActionListener(e -> updateCancel());

    }

    private void prepareProduct()
    {
        p = new Product();
        ArrayList<Product> products = new ArrayList();
        String selection = " ";


        if(listOfProducts.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null, "You have not made any selections");
        }
        else
        {
            selection = listOfProducts.getSelectedValue().toString();
            products = database.getProductCatalogue();

            // This for loop will go over all of the products in the database and get the one with the matching and put the product into
            // our product variable p
            for(int i = 0; i < database.getProductCounter(); i++)
            {
                if(selection == products.get(i).getProductName())
                {
                    p = products.get(i);
                }
            }

            System.out.println(p.getProductName());

            ChangeEvent selectSelected = new ChangeEvent(this);
            for(ChangeListener listener: selectListener)
            {
                listener.stateChanged(selectSelected);
            }
        }
    }


    private void updateCancel()
    {
        ChangeEvent cancelSelected = new ChangeEvent(this);
        for(ChangeListener listener: cancelListener)
        {
            listener.stateChanged(cancelSelected);
        }
    }

    public void addCancelListener(ChangeListener newListener)
    {
        cancelListener.add(newListener);
    }

    public void addSelectListener(ChangeListener newListener)
    {
        cancelListener.add(newListener);
    }

    public Product getP()
    {
        return p;
    }

}