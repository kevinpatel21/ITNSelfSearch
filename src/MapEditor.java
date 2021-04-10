import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class that manages the GUI used for store map creation.
 */
public class MapEditor extends MapTemplate
{
    private final ArrayList<ChangeListener> backListener = new ArrayList<ChangeListener>();//ArrayList of listeners
    /**
     * Constructor for the map editor GUI
     */
    public MapEditor()
    {
        // Call the constructor of the MapTemplate class to create the map grid
        super();


        // Create a panel to hold the finish editing map/load existing map buttons
        JPanel saveButtons = new JPanel(); // Create the panel
        saveButtons.setBorder(BorderFactory.createEtchedBorder()); // Add a border to the panel
        add(saveButtons, "South"); // Add the panel to the map editor GUI

        // Initialize the finish editing map button
        JButton finishEditingMapButton = new JButton("Finish Editing Map");

        //Button to back out of map editor
        JButton backButton = new JButton("Back");

        // Initialize the action listener for the finish editing map button
        finishEditingMapButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // DEBUG: Print map save data to the console
                System.out.println(saveMapData());

                // save map to database
                ChangeEvent saveMapAttempt = new ChangeEvent(this);

                for(ChangeListener listener : mapSaveListener)
                {
                    listener.stateChanged(saveMapAttempt);
                }
            }
        });

        // Add the button to the saveButtons panel
        saveButtons.add(finishEditingMapButton);

        // Initialize the load existing map button
        JButton loadMapButton = new JButton("Load Existing Map");

        // Initialize the action listener for the load button
        loadMapButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // load map from database
                ChangeEvent loadMapAttempt = new ChangeEvent(this);

                for(ChangeListener listener : mapLoadListener)
                {
                    listener.stateChanged(loadMapAttempt);
                }
            }
        });

        // Add the button to the saveButtons panel
        saveButtons.add(loadMapButton);

        // Back button logic
        saveButtons.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent backSelected = new ChangeEvent(this);

                for(ChangeListener listener: backListener){
                    listener.stateChanged(backSelected);
                }
            }
        });


        // Initialize the Tile selection sidebar
        // Create the button group that the tile selection buttons belongs to
        ButtonGroup tileSelectionButtons = new ButtonGroup();

        // Add the tile selection buttons to the button group
        tileSelectionButtons.add(blankTile);
        tileSelectionButtons.add(shelfTile);
        tileSelectionButtons.add(aisleTile);
        tileSelectionButtons.add(wallTile);

        // Create the panel to store the tile selection buttons
        JPanel tileSelectionPanel = new JPanel();
        tileSelectionPanel.setLayout(new GridLayout(4,1));
        tileSelectionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Map Tile Type"));

        // Add the tile selection buttons to the panel
        tileSelectionPanel.add(blankTile);
        tileSelectionPanel.add(shelfTile);
        tileSelectionPanel.add(aisleTile);
        tileSelectionPanel.add(wallTile);

        // Add the tile selection panel to the map editor panel
        add(tileSelectionPanel, "East");
    }


    /**
     * Method to get the currently selected grid tile type.
     * @return The currently selected grid tile type.
     */
    public int getTileTypeSelection()
    {
        int tileType = 0;

        if(blankTile.isSelected())
        {
            tileType = 0;
        }

        if(shelfTile.isSelected())
        {
            tileType = 1;
        }

        if(aisleTile.isSelected())
        {
            tileType = 2;
        }

        if(wallTile.isSelected())
        {
            tileType = 3;
        }

        return tileType;
    }


    /**
     * Implementation of registerTile method from the MapTemplate class.
     * Method to register the current panel as the owner of a grid tile, allowing the panel to be notified when the grid tile is clicked.
     * When notified, use the currently selected tile type radio button to set the state of the grid tile that was clicked.
     * @param x The x coordinate of the grid tile.
     * @param y The y coordinate of the grid tile.
     */
    protected void registerTile(int x, int y)
    {
        gridArray[x][y].registerOwner(event -> gridArray[x][y].setGridState(getTileTypeSelection()));
    }


    // Radio buttons used to select the grid tile type.
    // Radio buttons are part of the tileSelectionButtons button group
    /**
     * Radio Button used to select the "Blank Tile" grid tile type. The default selection for the radio buttons.
     */
    final JRadioButton blankTile = new JRadioButton("Blank Tile", true);

    /**
     * Radio Button used to select the "Product Shelf" grid tile type.
     */
    final JRadioButton shelfTile = new JRadioButton("Product Shelf");

    /**
     * Radio Button used to select the "Path" grid tile type.
     */
    final JRadioButton aisleTile = new JRadioButton("Path");

    /**
     * Radio Button used to select the "Wall" grid tile type.
     */
    final JRadioButton wallTile = new JRadioButton("Wall");

    /**
     * Function used to determine if user clicks on back
     * @param newListener an input listener
     */
    public void addBackListener(ChangeListener newListener){
        backListener.add(newListener);
    }
}