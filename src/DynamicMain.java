import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DynamicMain extends JFrame{
    //Creating a database for the software to use
    private ActiveDatabase testDatabase;
    //Creating a class for importing a new database
    //private ImportController testControl = new ImportController(false);
    final ArrayList<ChangeListener> importListeners = new ArrayList<ChangeListener>();//ArrayList of listeners

    public DynamicMain(ActiveDatabase inputActiveDatabase){
        testDatabase = inputActiveDatabase;

        // Set up the frame with a few settings.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Homescreen");

        //Updates database if admin finds an import file and confirms import
        /**
         * Use this section below as main
         */


        // Harrisons GUI test code
        NameFilter nf = new NameFilter();
        TagFilter tf = new TagFilter();

        homeView v = new homeView();
        homeController c = new homeController(v, nf, tf, getTestDatabase().getDatabase());
        c.initController();

        JPanel viewSet = new JPanel(new CardLayout());

        //Adding panels to the frame cardset
        viewSet.add(v, "v");
        this.add(viewSet);
        CardLayout cardlayout = (CardLayout) (viewSet.getLayout());

        //Listens for when user searches for product name
        c.addNameSearchListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //This part is temporary, this is where the ProductGUI call comes in (just creating a JPanel with product information to display)
                JPanel testView = new JPanel();
                testView.setLayout(new FlowLayout());

                String productInfo = "";
                productInfo += c.getRetrievedProduct().getProductName() + ": ";
                productInfo += c.getRetrievedProduct().getProductPrice();

                for (String tag: c.getRetrievedProduct().getProductTags()){
                    productInfo += ", " + tag;
                }

                productInfo += "\n\n" + "NOTE: This is only a test display. The ProductGUI class will go here when complete.";

                JTextArea testText = new JTextArea();
                testText = new JTextArea(productInfo, 10, 20);
                testText.setEditable(false);
                testView.add(testText);

                JButton mainMenu = new JButton("Main Menu");
                testView.add(mainMenu);

                //Changing card set to product display
                viewSet.add(testView, "testView");
                cardlayout.show(viewSet, "testView");

                //Listens for when user clicks the main menu button (to exit product view)
                mainMenu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardlayout.show(viewSet, "v");
                    }
                });

            }
        });










































        /**
         * End of main
         */
    }

    public int getProductSize(){
        return testDatabase.getProducts().size();
    }

    public void addImportedListener(ChangeListener newListener){
        importListeners.add(newListener);
    }

    public ActiveDatabase getTestDatabase() {
        return testDatabase;
    }
}
