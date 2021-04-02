import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Class that manages the GUI used for store map creation.
 */
public class MapEditor extends MapTemplate
{
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

        // Initialize the action listener for the finish editing map button
        // TO-DO: Make button save map data to database instead of to debug save
        finishEditingMapButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                savedMapTestData = saveMapData();
                System.out.println(savedMapTestData);
            }
        });

        // Add the button to the saveButtons panel
        saveButtons.add(finishEditingMapButton);

        // Initialize the load existing map button
        JButton loadMapButton = new JButton("Load Existing Map");

        // Initialize the action listener for the load button
        // TO-DO: Make button load map data from database instead of from debug save.
        loadMapButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                loadMapData(savedMapTestData);
            }
        });

        // Add the button to the saveButtons panel
        saveButtons.add(loadMapButton);


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


    // DEBUG: map save used for testing
    String savedMapTestData = "";
}