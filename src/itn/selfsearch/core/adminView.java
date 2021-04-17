package itn.selfsearch.core;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class is for the GUI implementation of adminView
 * This uses the Observer design pattern
 */
public class adminView extends JPanel
{
    private JButton mapEditorButton;
    private JButton databaseImportButton;
    private JButton homeButton;
    private JLabel adminScreen;

    /**
     * ArrayList of listeners that are notified if user clicks on the main menu button.
     */
    private final ArrayList<ChangeListener> mainListener = new ArrayList<ChangeListener>();

    /**
     * ArrayList of listeners that are notified if user clicks on the map editor button.
     */
    private final ArrayList<ChangeListener> mapListener = new ArrayList<ChangeListener>();

    /**
     * ArrayList of listeners that are notified if user clicks on the import button.
     */
    private final ArrayList<ChangeListener> importListener = new ArrayList<ChangeListener>();

    /**
     * This panel creates the GUI implementation of adminView
     */
    private void createPanel()
    {
        // Create the layout for the panel
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(fl);

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
        this.add(mapEditorButton);
        this.add(databaseImportButton);
        this.add(homeButton);

        // Set the background color
        this.setBackground(Color.LIGHT_GRAY);

        // Button Listeners
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent mainSelected = new ChangeEvent(this);
                for(ChangeListener listener: mainListener){
                    listener.stateChanged(mainSelected);
                }
            }
        });

        mapEditorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent mapSelected = new ChangeEvent(this);
                for(ChangeListener listener: mapListener){
                    listener.stateChanged(mapSelected);
                }
            }
        });

        databaseImportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent importSelected = new ChangeEvent(this);
                for(ChangeListener listener: importListener){
                    listener.stateChanged(importSelected);
                }
            }
        });

    }

    /**
     * Constructor for our Admin view it lets us know when we hit this function for testing purposes
     * then goes to createPanel();
     */
    public adminView()
    {
        createPanel();
    }

    /**
     * Function used to determine if user clicks on main menu
     * @param newListener an input listener
     */
    public void addMainListener(ChangeListener newListener){
        mainListener.add(newListener);
    }

    /**
     * Function used to determine if user clicks on map editor
     * @param newListener an input listener
     */
    public void addMapListener(ChangeListener newListener){
        mapListener.add(newListener);
    }

    /**
     * Function used to determine if user clicks on import
     * @param newListener an input listener
     */
    public void addImportListener(ChangeListener newListener){
        importListener.add(newListener);
    }
}
