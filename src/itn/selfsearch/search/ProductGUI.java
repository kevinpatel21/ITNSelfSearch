package itn.selfsearch.search;

import itn.selfsearch.database.*;
import itn.selfsearch.map.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class that manages the GUI used to display product information.
 */
public class ProductGUI extends JPanel {

    private String productName;
    private Product displayedProduct;
    private String databaseContents;
    private JTextArea databasePreview;
    final ArrayList<ChangeListener> mainlistener = new ArrayList<ChangeListener>();//ArrayList of listeners

    /**
     * Constructor for ProductGUI.
     * @param inputProduct The product that the GUI is displaying the information of.
     * @param kioskCoordinate Coordinates of the kiosk.
     * @param storeMap A map of the store.
     */
    public ProductGUI(Product inputProduct, Coordinate kioskCoordinate, String storeMap) {


        this.setLayout(new BorderLayout());

        databaseContents = "";
        databaseContents += "Product Name: " + inputProduct.getProductName() +"\n";
        databaseContents += "Product Price: $" + inputProduct.getProductPrice() +"\n";
        databaseContents += "Product Tags: " + inputProduct.getProductTags() + "\n";
        databasePreview = new JTextArea(databaseContents);
        databasePreview.setEditable(false);
        this.add(databasePreview, BorderLayout.NORTH);
        this.setVisible(true);


        ProductLocator productMap = new ProductLocator(kioskCoordinate, inputProduct.getProductLocation());
        this.add(productMap, BorderLayout.CENTER);


        JButton menuButton = new JButton("Main Menu");
        this.add(menuButton, BorderLayout.SOUTH);

        productMap.loadMapData(storeMap);

        // Register listener for the map editor to load map data from the database
        productMap.addMapLoadListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                productMap.loadMapData(storeMap);
            }
        });

        menuButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e)
             {
                 ChangeEvent menuSelected = new ChangeEvent(this);
                 for(ChangeListener listener: mainlistener){
                     listener.stateChanged(menuSelected);
                 }
             }
           }

        );



    }

    //Sets
    /**
     * Function used to determine if user searches by name
     * @param newListener an input listener
     */
    public void addMainListener(ChangeListener newListener){
        mainlistener.add(newListener);
    }






}

