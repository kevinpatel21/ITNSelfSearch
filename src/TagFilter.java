import java.lang.*;
import java.util.ArrayList;


public class TagFilter
{





    /**
     * Pre: validProductName MUST be called first to check if Product is in the database BEFORE retrieving
     * Function used to retrieve multiple products by an input of tags
     * @param inputTags ArrayList of tags that the database will use to search for Products
     * @param d We need access to database so that we can use the getProductCatalogue and also access to validProductName
     * @return Returns ArrayList Products if found, empty ArrayList otherwise
     */
    public ArrayList<Product> retrieveByTags(ArrayList<String> inputTags, Database d)
    {
        //ArrayList that will hold related Products containing specified tags
        ArrayList<Product> matchingProducts = new ArrayList<Product>();

        int productCounter = 0;



        // Search the catalogue for any products that contains all input tags and add it to matchingProducts list
        for (Product product: d.getProductCatalogue())
        {
            // We need to set the ProductTagNumber each time we get a new product for our loops
            product.setProductTagNumber();
            productCounter = product.getProductTagNumber();

            /*if(product.getProductTags().containsAll(inputTags))
            {
                matchingProducts.add(product.clone());
            } */

            // You need to iterate through all input tags to see if one matches with the ProductTags List
            for(int i = 0; i < productCounter; i++)
            {
                for(int j = 0; j < inputTags.size(); j++)
                {
                    if(product.getProductTags().get(i).equals(inputTags.get(j)))
                    {
                        matchingProducts.add(product.clone());
                    }
                }
            }

            //System.out.println(product.getProductTags());

        }
        return matchingProducts;
    }


}
