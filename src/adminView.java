import javax.swing.*;
import java.awt.*;

/**
 * This class is for the GUI implementation of adminView
 */
public class adminView
{
    private JButton mapEditorButton;
    private JButton databaseImportButton;
    private JButton homeButton;
    private JLabel adminScreen;
    private JPanel adminPanel;

    /**
     * This panel creates the GUI implementation of adminView
     */
    private void createPanel()
    {
        // Create a new JPanel
        adminPanel = new JPanel();

        // Create the layout for the panel
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
        adminPanel.setLayout(fl);

        // Set the vertical gap for the layout
        fl.setVgap(50);

        // Create our variables for our GUI components
        adminScreen = new JLabel("ADMIN SCREEN");
        mapEditorButton = new JButton("Map Editor");
        homeButton = new JButton("Home Button");
        databaseImportButton = new JButton("Database Import");

        // Set the PreferredSizes for the buttons
        homeButton.setPreferredSize(new Dimension(300,50));
        mapEditorButton.setPreferredSize(new Dimension(300,50));
        databaseImportButton.setPreferredSize(new Dimension(300,50));

        // Add the buttons to the panel
        adminPanel.add(mapEditorButton);
        adminPanel.add(databaseImportButton);
        adminPanel.add(homeButton);

        // Set the background color
        adminPanel.setBackground(Color.LIGHT_GRAY);

    }

    /**
     * Constructor for our Admin view it lets us know when we hit this function for testing purposes
     * then goes to createPanel();
     */
    public adminView()
    {
        System.out.println("GUI adminView initalized");
        createPanel();
    }

}
