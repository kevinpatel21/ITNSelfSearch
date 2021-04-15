package itn.selfsearch.map;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

/**
 * Class that manages the GUI used for providing a path to a product.
 */
public class ProductLocator extends MapTemplate
{
    /**
     * Constructor for the product locator GUI
     * @param kioskCoordinate The coordinates of the kiosk.
     * @param productCoordinate The coordinates of the product.
     */
    public ProductLocator(Coordinate kioskCoordinate, Coordinate productCoordinate)
    {
        // Call the constructor of the MapTemplate class to create the map grid
        super();

        // DEBUG: Button to rerun the pathfinding function for testing purposes.
        JButton redrawButton = new JButton("Draw Path");
        redrawButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pathfinder(kioskCoordinate, productCoordinate);
            }
        });
        add(redrawButton, "South");
        // END OF DEBUG CODE
    }


    /**
     * Method to generate the shortest path from the kiosk to the product.
     * @param kioskLocation The coordinates of the kiosk.
     * @param productLocation The coordinates of the product.
     * @return boolean value indicating if a path was found.
     */
    public boolean pathfinder(Coordinate kioskLocation, Coordinate productLocation)
    {
        // Variable used to return whether or not a path was found
        boolean pathFound = false;

        // Variables used to store the X/Y position of the pathfinder
        int xPos = kioskLocation.getX();
        int yPos = kioskLocation.getY();

        // Variables used for randomly deciding which direction the pathfinder should try to go.
        Random rng = new Random();
        int randomMove;

        // load map from database
        ChangeEvent loadMapAttempt = new ChangeEvent(this);

        for(ChangeListener listener : mapLoadListener)
        {
            listener.stateChanged(loadMapAttempt);
        }

        // Generate a map of the distance from the product tile for every tile on the map grid
        generateDistanceMap(productLocation);

        // Draw the path to the product
        while ((!pathFound) & (distanceMap[xPos][yPos] >= 0))
        {
            // Set the current tile as part of the path
            gridArray[xPos][yPos].setGridState(4);

            // Check if the pathfinder on the product tile
            if (distanceMap[xPos][yPos] == 0)
            {
                // Pathfinder is on the product tile, so the path has been found
                pathFound = true;
            }
            else // The pathfinder is not on the product tile, so look for a adjacent tile that is closer to the product
            {
                // Pick a random direction to try and move.
                randomMove = rng.nextInt(4);

                // Check if the tile that was picked is closer to the product. If it is, move to the tile.
                // Move to the left (-X Direction)
                if (randomMove == 0) { if (xPos > 0) { if (distanceMap[xPos - 1][yPos] == (distanceMap[xPos][yPos] - 1)) { xPos--; } } }

                // Move to the right (+X Direction)
                if (randomMove == 1) { if (xPos < (mapSizeX - 1)) { if (distanceMap[xPos + 1][yPos] == (distanceMap[xPos][yPos] - 1)) { xPos++; } } }

                // Move up (-Y Direction)
                if (randomMove == 2) { if (yPos > 0) { if (distanceMap[xPos][yPos - 1] == (distanceMap[xPos][yPos] - 1)) { yPos--; } } }

                // Move down (+Y Direction)
                if (randomMove == 3) { if (yPos < (mapSizeY - 1)) { if (distanceMap[xPos][yPos + 1] == (distanceMap[xPos][yPos] - 1)) { yPos++; } } }
            }
        }

        // Add the kiosk and product to the map display
        drawKiosk(kioskLocation);
        drawProductLocation(productLocation);

        return pathFound;
    }


    /**
     * Method to generate a map of the distance from the product tile for every tile on the map grid.
     * @param productLocation The coordinates of the product tile.
     */
    private void generateDistanceMap(Coordinate productLocation)
    {
        // Create a copy of the current map to use when making the distance map
        String mapCopy = saveMapData();

        // Initialize the distance map using the saved map data
        Scanner mapLoader = new Scanner(mapCopy);
        mapLoader.useDelimiter("_");

        // Find the paths/walls in the map
        for(int j = 0; j < mapSizeY; j++)
        {
            for(int i = 0; i < mapSizeX; i++)
            {
                if(mapLoader.hasNextInt())
                {
                    // Check if the current tile is a path
                    if(mapLoader.nextInt() == 2)
                    {
                        distanceMap[i][j] = -2; // Value of -2 is used to mark paths that do not have a known distance to the product
                    }
                    else // If the tile is not a path
                    {
                        distanceMap[i][j] = -1; // Value of -1 is used to mark tiles with obstructions the pathfinder can not go through
                    }
                }
            }
        }

        // Set the location of the product
        distanceMap[productLocation.getX()][productLocation.getY()] = 0; // Value of 0 is used to mark tiles containing the product

        // Find the distance to the product for each tile that is capable of reaching the product
        for (int mappingCycle = 1; mappingCycle <= (mapSizeX * mapSizeY); mappingCycle++)
        {
            // Variable used to track how many tiles have been updated this cycle
            int tilesUpdated = 0;

            // Check each tile in the distance map
            for (int j = 0; j < mapSizeY; j++)
            {
                for (int i = 0; i < mapSizeX; i++)
                {
                    // Check if the current tile is a path that does not have a known distance
                    if(distanceMap[i][j] == -2)
                    {
                        // Check if the tile is next to a tile whose distance was found in the previous cycle
                        // If the tile is next to a tile whose distance was found in the previous cycle, set the distance of the tile to the current mapping cycle

                        // Check the tile to the left (-X Direction)
                        if (i > 0) { if (distanceMap[i - 1][j] == (mappingCycle - 1)) { distanceMap[i][j] = mappingCycle; } }

                        // Check the tile to the right (+X Direction)
                        if (i < (mapSizeX - 1)) { if (distanceMap[i + 1][j] == (mappingCycle - 1)) { distanceMap[i][j] = mappingCycle; } }

                        // Check the tile above the current tile (-Y Direction)
                        if (j > 0) { if (distanceMap[i][j - 1] == (mappingCycle - 1)) { distanceMap[i][j] = mappingCycle; } }

                        // Check the tile below the current tile (+Y Direction)
                        if (j < (mapSizeY - 1)) { if (distanceMap[i][j + 1] == (mappingCycle - 1)) { distanceMap[i][j] = mappingCycle; } }

                        // Increment count of updated tiles if the current tile was changed
                        if(distanceMap[i][j] != -2)
                        {
                            tilesUpdated++;
                        }
                    }
                }
            }

            // End the process early if no tiles were updated this cycle
            if(tilesUpdated == 0)
            {
                mappingCycle = (mapSizeX * mapSizeY) + 1;
            }
        }

        // DEBUG: Print the contents of the distance map to the console
        for(int j = 0; j < mapSizeY; j++)
        {
            for(int i = 0; i < mapSizeX; i++)
            {
                //System.out.printf("%4s", distanceMap[i][j] + " ");
            }
            //System.out.print('\n');
        }
        //System.out.print('\n');
        // END OF DEBUG CODE
    }


    /**
     * Method to draw the location of the kiosk on the map.
     * @param kioskLocation The coordinates of the kiosk.
     */
    private void drawKiosk(Coordinate kioskLocation)
    {
        int xPos = kioskLocation.getX();
        int yPos = kioskLocation.getY();

        gridArray[xPos][yPos].setGridState(5);
    }


    /**
     * Method to draw the location of the product on the map.
     * @param productLocation The coordinates of the product.
     */
    private void drawProductLocation(Coordinate productLocation)
    {
        int xPos = productLocation.getX();
        int yPos = productLocation.getY();

        gridArray[xPos][yPos].setGridState(6);
    }


    /**
     * Implementation of registerTile method from the MapTemplate class.
     * Method to register the current panel as the owner of a grid tile, allowing the panel to be notified when the grid tile is clicked.
     * Currently unused by ProductLocator class.
     * @param x The x coordinate of the grid tile.
     * @param y The y coordinate of the grid tile.
     */
    protected void registerTile(int x, int y) {}


    /**
     * Array of integers used to store the distance from the product tile for every tile on the map grid
     */
    private final int[][] distanceMap = new int[mapSizeX][mapSizeY];
}