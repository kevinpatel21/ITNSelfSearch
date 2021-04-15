import java.util.ArrayList;

/**
 * Class used to store product data.
 */
public class Product implements Cloneable {
    private String productName;
    private Double productPrice;
    private ArrayList<String> productTags;
    private int productTagNumber;
    Coordinate productLocation;

    /**
     * sets product name and price as well as create
     * arrayList for product tags and and new coordinate
     * for product location
     * @param inputName The name of the product.
     * @param inputPrice The price of the product.
     */
    public Product(String inputName, double inputPrice) {
        productName = inputName;
        productPrice = inputPrice;
        productTags = new ArrayList<String>();
        productLocation = new Coordinate(0, 0);
    }

    /**
     * When no parameters are provided everything
     * is initialized to unknown
     */
    public Product() {
        productName = "Unknown";
        productPrice = 0.00;
        productTags = new ArrayList<String>();
        productLocation = new Coordinate(0, 0);
        productTagNumber = 0;
    }

    /**
     * To make a product clone
     * @return a clone of the product
     */
    public Product clone() {
        try {
            Product ProductClone = (Product) super.clone();
            ProductClone.productTags = (ArrayList<String>) productTags.clone();

            return ProductClone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Returns product name
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * returns product tags
     * @return a ArrayList of strings with product tags
     */
    public ArrayList<String> getProductTags() {
        return (ArrayList<String>) productTags.clone();
    }


    /**
     * sets product tags when provided an arraylist
     * @param inputTags An ArrayList of tags to add to the product.
     */
    public void setProductTags(ArrayList<String> inputTags) {
        productTags = (ArrayList<String>) inputTags.clone();
    }

    /**
     * sets a single tag when provided one string input
     * @param inputTag The tag to add to the product
     */
    public void setProductTag(String inputTag) {
        productTags.add(inputTag);
    }


    /**
     * returns product price
     * @return product price
     */
    public Double getProductPrice() {
        return productPrice;
    }

    /**
     * sets the product name
     * @param inputName The name of the product.
     */
    public void setProductName(String inputName) {
        productName = inputName;
    }

    /**
     * sets the product price
     * @param inputPrice The price of the product.
     */
    public void setProductPrice(Double inputPrice) {
        productPrice = inputPrice;
    }

    /**
     * sets the product location
     * @param inputLocation The coordinates of the product.
     */
    public void setProductLocation(Coordinate inputLocation)
    {
        productLocation = inputLocation;
    }

    /**
     * returns product location coordinates
     * @return product location
     */
    public Coordinate getProductLocation()
    {
        return productLocation;
    }

    /**
     * Prints the product name, price, tags, and location
     */
    public void printProductInfo()
    {
        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: "+ productPrice);
        System.out.println("Product Tags: " + productTags);
        System.out.println("Product Location: " + productLocation);
    }

    /**
     * Method to set the number of tags a product has.
     * The number of tags is set using the size of the ArrayList used to store the product's tags.
     */
    public void setProductTagNumber()
    {
        productTagNumber = productTags.size();
    }

    /**
     * Method to get the number of tags a product has.
     * @return The number of tags a product has.
     */
    public int getProductTagNumber()
    {
        return productTagNumber;
    }


}
