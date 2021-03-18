import java.util.ArrayList;
import java.util.Map;

/**
 * Database is responsible for storing and sending essential information in software.
 * Holds all products and associated item details imported from external JSON file.
 * Holds all passwords imported to software.
 * Finds and returns a Product by name.
 * Finds and returns ArrayList of Products by associated tags.
 */
public class Database {
    //Attributes
    private ArrayList<Product> productCatalogue;//ArrayList that stores Products
    private ArrayList<String> adminPasswords;//ArrayList that stores passwords
    String storeMap;//Map of the store


    //Constructors
    /**
     * Constructor for Database class, requires an admin password by default
     * @param defaultPassword Default password used to access database
     */
    public Database(String defaultPassword){
        productCatalogue = new ArrayList<Product>();
        adminPasswords = new ArrayList<String>();
        adminPasswords.add(defaultPassword);
        storeMap = "";
    }
    /**
     * Constructor for Database class, requires an admin password by default
     * requires a DatabaseImport Object IF making new import of database
     * used only when importing products into database
     * @param defaultPassword Default password used to access database
     * @param importCall The import object retrieving data from import JSON file
     */
    public Database(String defaultPassword, DatabaseImport importCall){
        productCatalogue = new ArrayList<Product>();
        adminPasswords = new ArrayList<String>();
        importCall.importDatabase(productCatalogue, adminPasswords);
        adminPasswords.add(defaultPassword);
        storeMap = "";
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
        storeMap = inputMap;
    }


    //Gets
    /**
     * Function used to retrieve a map of the store
     * @return Returns currently stored map in the database
     */
    public String getStoreMap(){
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
        return (ArrayList<Product>) productCatalogue.clone();
    }


    //Display Database
    /**
     * Function used to output data inside of database
     * Displays number of Products in database
     * Displays all Products and associated information
     * Displays all admin passwords
     */
    public void printDatabase(){

        System.out.println("Number of Items in database: " + getProductCounter() + "\n \n");

        System.out.println("Product List" + "\n");

        for (Product product: productCatalogue){
            System.out.print(product.getProductName() + ": " + product.getProductPrice());

            for(String tag: product.getProductTags()){
                System.out.print(", " + tag);
            }

            System.out.println();
        }

        System.out.println("\n\nAdmin passwords\n");

        for (String password: adminPasswords){
            System.out.println(password);
        }
        //Display map??
    }

    //Checkers
    /**
     * Function used to check if Product searched by name is in database
     * Must be ran BEFORE retrieveByTags method is called
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
}
