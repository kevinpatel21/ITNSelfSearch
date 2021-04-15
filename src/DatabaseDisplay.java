import javax.swing.*;
import java.awt.*;

/**
 * Responsible for creating a panel that contains the contents of an inputted database
 */
public class DatabaseDisplay extends JPanel {
    //Attributes
    private JTextArea databasePreview;//JTextArea used to store database contents
    private String databaseContents;//String var that holds data extracted from database

    //Constructors
    /**
     * Constructor for DatabaseDisplay
     * @param inputDatabase The database that DatabaseDisplay is displaying the contents of.
     */
    public DatabaseDisplay(Database inputDatabase){
        //Extracting data from input database and adding it to a JTextArea
        this.setLayout(new FlowLayout());

        databaseContents = "";

        databaseContents += "Kiosk Coordinate: (" + inputDatabase.getKioskLocation().getX() + ", " + inputDatabase.getKioskLocation().getY() + ")\n";//Code breaks here at getKioskLocation

        databaseContents += "\n\nProducts: " + inputDatabase.getProductCounter() +"\n\n";

        for (Product product: inputDatabase.getProductCatalogue()){
            databaseContents += product.getProductName() + ": $" + product.getProductPrice();
            databaseContents += ", (" + product.getProductLocation().getX() + ", " + product.productLocation.getY() + ")";

            for(String tag: product.getProductTags()){
                databaseContents += ", " + tag;
            }

            databaseContents += "\n";
        }

        databaseContents += "\n\nPasswords: " + inputDatabase.getPasswordCounter() + "\n\n";

        for (String password: inputDatabase.getPasswords()){
            databaseContents += password + "\n";
        }

        databaseContents += "\n\nStore Tags: " + inputDatabase.getStoreTagCounter() + "\n\n";

        for (String tag: inputDatabase.getStoreTags()){
            databaseContents += tag + "\n";
        }

        //Setting up the look/design of the panel
        databasePreview = new JTextArea(databaseContents, 14, 20);
        databasePreview.setEditable(false);
        JScrollPane displayScroll = new JScrollPane(databasePreview, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.add(displayScroll);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Database Contents"));
        this.setVisible(true);
    }
}
