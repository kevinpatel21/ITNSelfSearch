package itn.selfsearch.search;

import itn.selfsearch.database.*;

import java.lang.*;
import java.util.ArrayList;

/**
 * Class that is used to search for a product in the database using the product's tags.
 */
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
            if (product.getProductTags().containsAll(inputTags))
            {
                matchingProducts.add(product.clone());
            }
        }


        // If the matchingProducts arraylist is empty then our first for loop did not do anything and we need to
        // execute this logic
        if(matchingProducts.isEmpty())
        {
            // Search the catalogue for any products that contains all input tags and add it to matchingProducts list
            for (Product product: d.getProductCatalogue())
            {
                // We need to set the ProductTagNumber each time we get a new product for our loops
                product.setProductTagNumber();
                productCounter = product.getProductTagNumber();

                // You need to iterate through all input tags to see if one matches with the ProductTags List
                for(int i = 0; i < productCounter; i++)
                {
                    for(int j = 0; j < inputTags.size(); j++)
                    {
                        if(product.getProductTags().get(i).equals(inputTags.get(j)))
                        {
                            if(checkDuplicate(matchingProducts, product) == false)
                            {
                                matchingProducts.add(product.clone());
                            }

                        }

                    }
                }
            }
        }

        return matchingProducts;
    }

    /**
     * This function will check for duplicates incase the user enters two tags that appears in the product twice
     *
     * Ex. Apple Food, Red
     * If the use enters both food and red before it would put apple in the list twice. This function will make sure its only in there once.
     *
     * @param matchingProducts takes in our matchingProducts list from retrieveByTags so that we can iterate through it and make sure that there are no duplicates
     * @param product Takes in the prduct that will go into matchingProducts ONLY IF it is not in there already it is also taken from retrieveByTags
     * @return duplicate Returns boolean duplicate. IF it is in the list then return true. IF it is NOT in the list the return false.
     */
    private boolean checkDuplicate(ArrayList<Product> matchingProducts, Product product)
    {
        boolean duplicate = false;

        // Iterate through the matching Product Array List
        for(int i = 0; i < matchingProducts.size(); i++)
        {
            if(matchingProducts.get(i).getProductName().equals(product.getProductName()) == true)
            {
                duplicate = true;
            }
            else
            {
                duplicate = false;
            }
        }

        return duplicate;
    }


}
