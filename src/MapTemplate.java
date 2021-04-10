import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Abstract class defining common methods used by GUI panels containing maps.
 */
public abstract class MapTemplate extends JPanel
{
    /**
     * Constructor for MapTemplate.
     * Initializes a panel containing a grid based map.
     */
    MapTemplate()
    {
        // Initialize the map grid panel
        JPanel mapGridPanel = new JPanel();

        // Set the layout manager for the map grid panel
        setLayout(new BorderLayout());
        mapGridPanel.setLayout(new GridLayout(mapSizeX, mapSizeY));

        // Create the map grid
        for(int j = 0; j < mapSizeY; j++)
        {
            for(int i = 0; i < mapSizeX; i++)
            {
                // Create a new grid tile
                gridArray[i][j] = new GridTile();

                // Register the panel as the owner of the grid tile
                registerTile(i, j);

                // Add the grid tile to the panel
                mapGridPanel.add(gridArray[i][j]);
            }
        }

        add(mapGridPanel, "Center"); // Add the map grid panel to the center section of the panel
    }


    /**
     * Method to save the current contents of the map grid to a string.
     * @return The contents of the map grid as a string.
     */
    public String saveMapData()
    {
        StringBuilder mapData = new StringBuilder();

        // Create a map string using data from the map grid
        for(int j = 0; j < mapSizeY; j++)
        {
            for(int i = 0; i < mapSizeX; i++)
            {
                mapData.append(gridArray[i][j].getGridState()); // Add the current tile's data to the map string
                mapData.append("_"); // Character used to separate each tile's data in the map string
            }
        }

        mapData.append("*"); // Character used to mark the end of the map string

        return mapData.toString();
    }


    /**
     * Method used to register a listener for saving a map.
     * Used to notify the database when a map class wants to save a map to the database.
     * @param newListener an input listener
     */
    public void addMapSaveListener(ChangeListener newListener)
    {
        mapSaveListener.add(newListener);
    }


    /**
     * Method to load a saved map in the map editor.
     * @param mapData String containing data used to load an existing map.
     */
    public void loadMapData(String mapData)
    {
        Scanner mapLoader = new Scanner(mapData);
        mapLoader.useDelimiter("_");

        // Set the state of the map grid using data from the map string
        for(int j = 0; j < mapSizeY; j++)
        {
            for(int i = 0; i < mapSizeX; i++)
            {
                if(mapLoader.hasNextInt())
                {
                    // Use the next int in the map string to set the state of the current grid tile
                    gridArray[i][j].setGridState(mapLoader.nextInt());
                }
            }
        }
    }


    /**
     * Method used to register a listener for loading a map.
     * Used to notify the database when a map class wants to load a map from the database.
     * @param newListener an input listener
     */
    public void addMapLoadListener(ChangeListener newListener)
    {
        mapLoadListener.add(newListener);
    }


    /**
     * Method to register the current panel as the owner of a grid tile, allowing the panel to be notified when the grid tile is clicked.
     * The subclasses override this method to define what happens when the panel is notified.
     * @param x The x coordinate of the grid tile.
     * @param y The y coordinate of the grid tile.
     */
    protected abstract void registerTile(int x, int y);


    /**
     * Variable used to define the size of the map grid's X (Horizontal) axis
     */
    final int mapSizeX = 12;


    /**
     * Variable used to define the size of the map grid's Y (Vertical) axis
     */
    final int mapSizeY = 12;


    /**
     * 2D Array used to store the map grid
     */
    final GridTile[][] gridArray = new GridTile[mapSizeX][mapSizeY];


    /**
     * ArrayList used to store listeners that are notified about events related to saving map data
     */
    protected final ArrayList<ChangeListener> mapSaveListener = new ArrayList<>();


    /**
     * ArrayList used to store listeners that are notified about events related to loading map data
     */
    protected final ArrayList<ChangeListener> mapLoadListener = new ArrayList<>();
}