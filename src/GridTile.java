import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class used to create the buttons used as tiles on the map grid.
 */
public class GridTile extends JButton
{
    /**
     * Constructor for GridTile.
     * Sets the tile's state to 0 (Blank Tile), Initialize the tile's action listener, and initialize the array of colors used to draw the tile.
     */
    GridTile()
    {
        gridState = 0;

        // Initialize the grid button's action listener
        addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (owner != null)
                {
                    // Tell the panel that the button belongs to that the tile has been clicked.
                    ChangeEvent event = new ChangeEvent(this);
                    owner.stateChanged(event);
                }
            }
        });

        // Initialize the tileColors array
        tileColors = new Color[7];
        // Colors used for tile states that can be assigned using the map editor.
        tileColors[0] = Color.BLACK; // Blank Tile
        tileColors[1] = Color.CYAN; // Shelf
        tileColors[2] = Color.LIGHT_GRAY; // Path
        tileColors[3] = Color.DARK_GRAY; // Wall
        // Colors used for tile states that are automatically assigned by the product locator.
        tileColors[4] = Color.RED; // Path to the product
        tileColors[5] = Color.GREEN; // Kiosk location
        tileColors[6] = Color.ORANGE; // Product location

        repaint();
        updateTooltip();
    }


    /**
     * Method to register the panel that this button belongs to.
     * @param tileOwner The panel that this button belongs to.
     */
    public void registerOwner(ChangeListener tileOwner)
    {
        owner = tileOwner;
    }


    /**
     * Method to get the state of the grid tile.
     * @return The current state of the grid tile.
     */
    public int getGridState()
    {
        return gridState;
    }


    /**
     * Method to set the state of the grid tile.
     * @param newState The new state of the grid tile.
     */
    public void setGridState(int newState)
    {
        gridState = newState;
        repaint();
        updateTooltip();
    }


    /**
     * Method to draw the color of the grid tile using the grid tile's current grid state.
     * Method is called by the repaint() method.
     * @param graphics copy of the Graphics object.
     */
    @Override
    public void paintComponent(Graphics graphics)
    {
        Graphics2D tileGraphics = (Graphics2D)graphics;
        tileGraphics.setColor(tileColors[getGridState()]);
        tileGraphics.fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Method to set the tooltip text of the grid tile using the state of the grid tile.
     */
    private void updateTooltip()
    {
        switch (gridState)
        {
            case 0 -> setToolTipText("Blank Tile");
            case 1 -> setToolTipText("Shelf");
            case 2 -> setToolTipText("Path");
            case 3 -> setToolTipText("Wall");
            case 4 -> setToolTipText("Path to Product");
            case 5 -> setToolTipText("Kiosk Location");
            case 6 -> setToolTipText("Product Location");
            default -> setToolTipText("???");
        }
    }


    /**
     * Variable used to store the state of the grid tile.
     */
    private int gridState;


    /**
     * Array of colors used when drawing the grid tile.
     */
    private final Color[] tileColors;


    /**
     * ChangeListener object used to store the map grid that the grid tile is a part of.
     */
    private ChangeListener owner;
}