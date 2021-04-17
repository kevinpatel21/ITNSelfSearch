package itn.selfsearch.core;

import javax.swing.*;
import java.awt.*;

/**
 * Class that implements our GUI for the homescreen
 */
public class homeView extends JPanel
{
    // Variables needed for the Swing framework to display UI to user
    private JToggleButton nameFilterToggle;
    private JButton tagMenuButton;
    private JButton searchButton;
    private JTextField userText;
    private JLabel ITN;
    private JButton adminButton;

    /**
     * This function creates the frame and all of the settings associated with the frame and calls addComponents where
     * the components are then added to a panel
     */
    private void createFrame()
    {
        addComponents(this);
    }

    /**
     *
     *  This function uses a GridBagLayout style.
     * @param pane This is our container that holds our panel.
     */
    private void addComponents(Container pane)
    {
        // Set the layout style to GridBagLayout
        // This is the variable we will use to constrain our settings
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create our variables for our GUI components
        ITN = new JLabel("ITN SELF SEARCH");
        ITN.setFont(new Font("Verdana", Font.BOLD, 20));
        userText = new JTextField("Search here");
        searchButton = new JButton("Search");
        adminButton = new JButton("Admin");
        nameFilterToggle = new JToggleButton("Name Filter ");
        tagMenuButton = new JButton("Tag Menu ");

        // Set the settings for our adminButton component
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        adminButton.setPreferredSize(new Dimension(100,20));
        pane.add(adminButton, c);

        // Set the settings for the ITN Label component
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(50,0,0,0);
        pane.add(ITN, c);

        // Set the settings for the searchButton component
        c.anchor = GridBagConstraints.PAGE_END;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(0,0,60,0);
        searchButton.setPreferredSize(new Dimension(200,30));
        pane.add(searchButton, c);

        // Set the settings for the userText Textbox component
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(10,0,0,0);
        userText.setPreferredSize(new Dimension(250,25));
        pane.add(userText, c);

        // Set the settings for the nameFilterToggle button component
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(0,167,50,0);
        nameFilterToggle.setPreferredSize(new Dimension(125, 25));
        pane.add(nameFilterToggle, c);

        // Set the settings for the tagFilterToggle button component
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(0,0,50,167);
        tagMenuButton.setPreferredSize(new Dimension(125,25));
        pane.add(tagMenuButton, c);

        // Just messing with the color
        pane.setBackground(Color.LIGHT_GRAY);

    }

    /**
     * Constructor for homeView GUI
     */
    public homeView()
    {
        createFrame();
    }

    // The rest of the functions below are just getters that are used for our GUI components that are needed for homeController

    /**
     * Method to get the text field used as the product search bar
     * @return The text field used as the product search bar
     */
    public JTextField getUserText() {
        return userText;
    }

    /**
     * Method to get the name filter toggle button
     * @return The name filter toggle button
     */
    public JToggleButton getNameFilterToggle() {
        return nameFilterToggle;
    }

    /**
     * Method to get the tag search menu button
     * @return The tag search menu button
     */
    public JButton getTagMenuButton() {
        return tagMenuButton;
    }

    /**
     * Method to get the search by name button
     * @return The search by name button
     */
    public JButton getSearchButton() {
        return searchButton;
    }

    /**
     * Method to get the admin mode button
     * @return The admin mode button
     */
    public JButton getAdminButton() {
        return adminButton;
    }

}