package itn.selfsearch.database;

import itn.selfsearch.map.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Database is responsible for storing and sending essential information in software.
 * Holds all products and associated item details imported from external JSON file.
 * Holds all passwords imported to software.
 * Finds and returns a Product by name.
 * Finds and returns ArrayList of Products by associated tags.
 */
public class Database implements Cloneable{
    //Attributes
    /**
     * ArrayList that stores Products
     */
    private ArrayList<Product> productCatalogue;

    /**
     * ArrayList that stores passwords
     */
    private ArrayList<String> adminPasswords;

    /**
     * ArrayList that stores the map of the store
     */
    private ArrayList<String> storeMap;

    /**
     * ArrayList that stores the coordinates of the kiosk
     */
    private ArrayList<Coordinate> kioskLocation;

    /**
     * ArrayList that imported store product tags used for tag search
     */
    private ArrayList<String> storeTags;


    //Constructors
    /**
     * Constructor for Database class, requires an admin password by default
     * @param defaultPassword Default password used to access database
     */
    public Database(String defaultPassword){
        productCatalogue = new ArrayList<Product>();
        storeTags = new ArrayList<String>();
        adminPasswords = new ArrayList<String>();
        adminPasswords.add(0, defaultPassword);
        storeMap = new ArrayList<String>();
        storeMap.add("");
        kioskLocation = new ArrayList<Coordinate>();
        kioskLocation.add(0, new Coordinate());
    }

    /**
     * Constructor for Database class, ONLY USE THIS WHEN IMPORTING
     */
    public Database(){
        productCatalogue = new ArrayList<Product>();
        storeTags = new ArrayList<String>();
        adminPasswords = new ArrayList<String>();
        adminPasswords.add("");
        storeMap = new ArrayList<String>();
        storeMap.add("");
        kioskLocation = new ArrayList<Coordinate>();
        kioskLocation.add(0, new Coordinate());
    }


    //Sets
    /**
     * Function used to store a product into the database
     * @param inputProduct Product that will be stored in the database
     */
    public void addProduct(Product inputProduct){
        productCatalogue.add(inputProduct.clone());
    }

    /**
     * Function used to store a password into the database
     * @param inputPassword Password that will be stored in the database
     */
    public void addPassword(String inputPassword){
        adminPasswords.add(inputPassword);
    }

    /**
     * Function used to save current store map to database
     * @param inputMap Map that will be stored in the database
     */
    public void setStoreMap(String inputMap){
        storeMap.clear();
        storeMap.add(0, inputMap);
    }

    /**
     * Function used to save kiosk coordinate/locations
     * @param inputCoordinate input coordinate of kiosk
     */
    public void setKioskLocation(Coordinate inputCoordinate){
        kioskLocation.clear();
        kioskLocation.add(0, inputCoordinate);
    }


    //Gets
    /**
     * Function used to retrieve a map of the store
     * @return Returns currently stored map in the database
     */
    public String getStoreMap(){
        return storeMap.get(0);
    }

    /**
     * Function used to retrieve a arraylist that stores map data, used in databaseimport
     * @return Returns arraylist that stores map data
     */
    public ArrayList<String> getStoreMapArrayList(){
        return storeMap;
    }

    /**
     * Function used to retrieve number of Products in database
     * @return Returns number of Products stored in database
     */
    public int getProductCounter(){
        return productCatalogue.size();
    }


    /**
     * Function used to retrieve Products in database
     * @return Returns Product catalogue
     */
    public ArrayList<Product> getProductCatalogue(){
        return (ArrayList<Product>) productCatalogue;//GOT RID OF CLONE!!!
    }

    /**
     * Function used to retrieve admin passwords
     * @return Returns ArrayList of admin passwords
     */
    public ArrayList<String> getPasswords(){
        return (ArrayList<String>) adminPasswords;
    }//GOT RID OF CLONE!!!

    /**
     * Function used to retrieve store product tags
     * @return Returns ArrayList of store product tags
     */
    public ArrayList<String> getStoreTags(){
        return (ArrayList<String>) storeTags;
    }//GOT RID OF CLONE!!!

    /**
     * Function used to retrieve number of passwords in database
     * @return Returns number of passwords stored in database
     */
    public int getPasswordCounter(){
        return adminPasswords.size();
    }

    /**
     * Function used to retrieve kiosk coordinate
     * @return Returns Coordinate of kiosk containing coordinate
     */
    public Coordinate getKioskLocation(){ return kioskLocation.get(0).clone(); }

    /**
     * Function used to retrieve kioskLocation ArrayList, needed for DatabaseImport
     * @return Returns ArrayList containing coordinate, NOT A COPY/CLONE
     */
    public ArrayList<Coordinate> getKioskLocationArrayList(){ return kioskLocation; }

    /**
     * Function used to retrieve number of store product tags in database
     * @return Returns number of store product tags stored in database
     */
    public int getStoreTagCounter(){
        return storeTags.size();
    }



    //Display Database
    /**
     * Function used to output data inside of database
     * Displays number of Products in database
     * Displays all Products and associated information
     * Displays all admin passwords
     */
    public void displayDatabase(){
        ///Setting up the frame for viewing
        JFrame displayWindow = new JFrame();
        DatabaseDisplay contentPanel = new DatabaseDisplay(this);//Panel that will contain all database contents

        displayWindow.setLayout(new FlowLayout());
        displayWindow.setSize(300, 300);//Setting size of frame
        displayWindow.setTitle("Database Viewer");//Title seen at top border of JFrame window
        displayWindow.setVisible(true);//we want to see the frame right?
        displayWindow.setLocationRelativeTo(null);//Centers window!!
        displayWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Allows the frame to be closed
        displayWindow.add(contentPanel);

    }


    //Checkers
    /**
     * Function used to check if Product searched by name is in database
     * Must be ran BEFORE retrieveByTags method is called
     * @param inputName The name of the product that the function is searching for
     * @return Returns true if product was found, false if not
     */
    public boolean validProductName(String inputName){
        boolean  productFound = false;

        for(Product product: productCatalogue){
            if(product.getProductName().compareTo(inputName) == 0){
                productFound = true;
            }
        }
        return productFound;
    }


    //Functionality
    /**
     * To make a database clone
     * @return a clone of the database
     */
    public Database clone() {
        try {
            Database databaseClone = (Database) super.clone();
            databaseClone.adminPasswords = (ArrayList<String>) adminPasswords.clone();
            databaseClone.storeTags = (ArrayList<String>) storeTags.clone();
            databaseClone.productCatalogue = (ArrayList<Product>) productCatalogue.clone();
            databaseClone.kioskLocation = (ArrayList<Coordinate>) kioskLocation.clone();
            databaseClone.storeMap = (ArrayList<String>) storeMap.clone();

            return databaseClone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
