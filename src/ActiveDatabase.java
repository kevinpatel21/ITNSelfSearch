import java.util.ArrayList;

/**
 * Responsible for managing a core database for the duration of the software runtime
 * Updates database
 * Returns a copy of database to observe products within
 * Display core database contents
 */
public class ActiveDatabase {
    //Attributes
    private ArrayList<Database> activeDatabase;//Stores a database for software to use

    //Constructors
    /**
     * Constructor for ActiveDatabase class, no default password required
     */
    ActiveDatabase(){
        activeDatabase = new ArrayList<Database>();
        activeDatabase.add(0, new Database());
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


    //Functionality
    /**
     * Function responsible for updating the database
     */
    public void updateDatabase(Database newDatabase){
        activeDatabase.clear();
        activeDatabase.add(newDatabase);
    }

    /**
     * Function responsible for displaying the database contents
     */
    public void displayActiveDatabase(){
        activeDatabase.get(0).displayDatabase();
    }
}
