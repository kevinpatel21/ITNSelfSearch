import java.lang.*;
import java.util.ArrayList;

public class NameFilter
{
    /**
     * Function used to retrieve a Product in database by name.
     * @param inputName Name of Product that will be retrieved
     * @param d Needs access to the database so that we can use getProductCatalogue()
     * @return Returns Product if found, null otherwise
     */
    public Product retrieveByName(String inputName, Database d)
    {
        Product p = null;

        // If the product we are searching for is in the database then return the product
        if(d.validProductName(inputName) == true)
        {
            for(Product product: d.getProductCatalogue())
            {
                if(product.getProductName().compareTo(inputName) == 0)
                {
                    p = product;
                    break;
                }
            }
        }
        // If the product is not in the database then return a empty product
        else
        {
            return p.clone();
        }

        return p.clone();

    }


}
