import java.lang.*;
import java.util.ArrayList;


public class TagFilter
{

    /**
     * Pre: validProductName MUST be called first to check if Product is in the database BEFORE retrieving
     * Function used to retrieve multiple products by an input of tags
     * @param inputTags ArrayList of tags that the database will use to search for Products
     * @param d We need access to database so that we can use the getProductCatalogue and also access to validProductName
     * @param p We need access to the product that we
     * @return Returns ArrayList Products if found, empty ArrayList otherwise
     */
    public ArrayList<Product> retrieveByTags(ArrayList<String> inputTags, Database d, Product p)
    {
        //ArrayList that will hold related Products containing specified tags
        ArrayList<Product> matchingProducts = new ArrayList<Product>();

        // Search the catalogue for any products that contains all input tags and add it to matchingProducts list
        for (Product product: d.getProductCatalogue())
        {
            if (product.getProductTags().containsAll(inputTags))
            {
                matchingProducts.add(product.clone());
            }
        }
        return matchingProducts;
    }


}
