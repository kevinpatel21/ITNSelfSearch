import java.util.ArrayList;

public class Product implements Cloneable {
    private String productName;
    private Double productPrice;
    private ArrayList<String> productTags;
    Coordinate productLocation;


    public Product(String inputName, double inputPrice) {
        productName = inputName;
        productPrice = inputPrice;
        productTags = new ArrayList<String>();
        productLocation = new Coordinate(0, 0);
    }

    public Product() {
        productName = "Unknown";
        productPrice = 0.00;
        productTags = new ArrayList<String>();
        productLocation = new Coordinate(0, 0);
    }

    public Product clone() {
        try {
            Product ProductClone = (Product) super.clone();
            ProductClone.productTags = (ArrayList<String>) productTags.clone();

            return ProductClone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getProductName() {
        return productName;
    }

    public ArrayList<String> getProductTags() {
        return (ArrayList<String>) productTags.clone();
    }


    public void setProductTags(ArrayList<String> inputTags) {
        productTags = (ArrayList<String>) inputTags.clone();
    }

    public void setProductTag(String inputTag) {
        productTags.add(inputTag);
    }





    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductName(String inputName) {
        productName = inputName;
    }

    public void setProductPrice(Double inputPrice) {
        productPrice = inputPrice;
    }

    public void setProductLocation(Coordinate inputLocation)
    {
        productLocation = inputLocation;
    }

    public Coordinate getProductLocation()
    {
        return productLocation;
    }

    public void printProductInfo()
    {
        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: "+ productPrice);
        System.out.println("Product Tags: " + productTags);
        System.out.println("Product Location: " + productLocation);
    }



}
