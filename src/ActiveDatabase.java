import java.util.ArrayList;

/**
 * Responsible for managing a core database for the duration of the software runtime
 * Updates database
 * Returns a copy of database to observe products within
 * Display core database contents
 */
public class ActiveDatabase implements Cloneable{
    //Attributes
    /**
     * Stores a database for software to use
     */
    private ArrayList<Database> activeDatabase;

    //Constructors
    /**
     * Constructor for ActiveDatabase class, no default password required.
     * Creates a new database to use as the active database.
     */
    ActiveDatabase(){
        activeDatabase = new ArrayList<Database>();
        activeDatabase.add(0, new Database());
    }

    /**
     * Constructor for ActiveDatabase class, no default password required.
     * Uses an existing database as the active database.
     * @param inputDatabase Database to use as the active database
     */
    ActiveDatabase(Database inputDatabase){
        activeDatabase = new ArrayList<Database>();
        activeDatabase.add(0, inputDatabase.clone());
    }

    /**
     * Constructor for ActiveDatabase class, a default password is required
     * @param defaultPassword default password to be added to database
     */
    ActiveDatabase(String defaultPassword){
        activeDatabase = new ArrayList<Database>();
        activeDatabase.add(0, new Database(defaultPassword));
    }


    //Gets
    /**
     * Get method used to access the database storing products
     * @return Returns a copy of the database for viewing
     */
    public Database getDatabase(){
        return activeDatabase.get(0).clone();
    }

    /**
     * Get method used to access the database stored passwords
     * @return Returns ArrayList of passwords in database, (NOT A COPY/CLONE)
     */
    public ArrayList<String> getPasswords(){
        return activeDatabase.get(0).getPasswords();
    }

    /**
     * Get method used to access the database stored products
     * @return Returns ArrayList of products in database, (NOT A COPY/CLONE)
     */
    public ArrayList<Product> getProducts(){
        return activeDatabase.get(0).getProductCatalogue();
    }

    /**
     * Get method used to access store product tags
     * @return Returns ArrayList of store product tags in database, (NOT A COPY/CLONE)
     */
    public ArrayList<String> getStoreTags(){
        return activeDatabase.get(0).getStoreTags();
    }

    /**
     * Get method used to access the location of current kiosk
     * @return Returns a copy of kiosk location coordinate
     */
    public Coordinate getKioskLocation(){
        return activeDatabase.get(0).getKioskLocation().clone();
    }

    /**
     * Get method used to access the store map data
     * @return Returns currently stored map in the database
     */
    public String getStoreMap(){
        return activeDatabase.get(0).getStoreMap();
    }

    /**
     * Function used to retrieve a arraylist that stores map data, used in databaseimport
     * @return Returns arraylist that stores map data
     */
    public ArrayList<String> getStoreMapArrayList(){
        return activeDatabase.get(0).getStoreMapArrayList();
    }


    //Sets
    /**
     * Get method used to set map store data
     * @param inputMap Input map data to update database with
     */
    public void setStoreMap(String inputMap){
        activeDatabase.get(0).setStoreMap(inputMap);
    }


    //Functionality
    /**
     * Function responsible for updating the database
     * @param newDatabase The updated database
     */
    public void updateDatabase(Database newDatabase){
        activeDatabase.clear();
        activeDatabase.add(0, newDatabase);
    }

    /**
     * Function responsible for displaying the database contents
     */
    public void displayActiveDatabase(){
        activeDatabase.get(0).displayDatabase();
    }

    /**
     * To make a database clone
     * @return a clone of the database
     */
    public ActiveDatabase clone() {
        try {
            ActiveDatabase databaseClone = (ActiveDatabase) super.clone();
            databaseClone.activeDatabase = (ArrayList<Database>) activeDatabase.clone();

            return databaseClone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
