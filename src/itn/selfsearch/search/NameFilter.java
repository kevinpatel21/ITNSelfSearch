package itn.selfsearch.search;

import itn.selfsearch.database.*;

import java.lang.*;

/**
 * Class that is used to search for a product in the database using the product's name.
 */
public class NameFilter
{
    /**
     * MUST use validProduct() function before calling this function
     * Function used to retrieve a Product in database by name.
     * @param inputName Name of Product that will be retrieved
     * @param d Needs access to the database so that we can use getProductCatalogue()
     * @return Returns Product if found, null otherwise
     */
    public Product retrieveByName(String inputName, Database d)
    {
        Product p = null;

        // Search for the product in the ProductCatalogue
        for(Product product: d.getProductCatalogue())
        {
            // When we find it we put the product into P then return it
            if(product.getProductName().compareTo(inputName) == 0)
            {
                    p = product;
                    break;
            }
        }

        return p.clone();

    }


}
