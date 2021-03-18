/**
 * Class used to store the X and Y coordinates of a point on a 2d grid as a single object for use by other systems.
 */
public class Coordinate implements Cloneable
{
    /**
     * Default constructor for the Coordinate class.
     * Initializes the X and Y coordinates to 0;
     */
    public Coordinate()
    {
        xCoordinate = 0;
        yCoordinate = 0;
    }

    /**
     * Constructor for the Coordinate class.
     * Initializes the X and Y coordinates to the values provided by the inputX and inputY arguments.
     * @param inputX The value of the X coordinate.
     * @param inputY The value of the Y coordinate.
     */
    public Coordinate(int inputX, int inputY)
    {
        xCoordinate = inputX;
        yCoordinate = inputY;
    }

    /**
     * Method to set the value of the X coordinate.
     * @param inputX The value of the X coordinate
     */
    public void setX(int inputX)
    {
        xCoordinate = inputX;
    }

    /**
     * Method to set the value of the Y coordinate.
     * @param inputY The value of the Y coordinate
     */
    public void setY(int inputY)
    {
        yCoordinate = inputY;
    }

    /**
     * Method to get the value of the X coordinate.
     * @return The value of the X coordinate.
     */
    public int getX()
    {
        return xCoordinate;
    }

    /**
     * Method to get the value of the Y coordinate.
     * @return The value of the Y coordinate.
     */
    public int getY()
    {
        return yCoordinate;
    }

    /**
     * Coordinate class implementation of the clone method
     * @return a clone of the Coordinate object that called the clone method
     */
    public Coordinate clone()
    {
        try
        {
            return (Coordinate) super.clone();
        }
        catch (CloneNotSupportedException exception) // If unable to copy the coordinate, return a new coordinate created using the default constructor.
        {
            return new Coordinate();
        }
    }

    /**
     * Variable used to store the x coordinate.
     */
    private int xCoordinate;

    /**
     * Variable used to store the y coordinate.
     */
    private int yCoordinate;

}