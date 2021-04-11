import javax.swing.*;
import java.util.ArrayList;

public class tagMenuController
{
    private tagMenu menu;
    private Database database;
    private Product retrievedProduct;
    private ArrayList<String> inputTags;
    private TagFilter tagfilter;



    public tagMenuController(tagMenu m, Database d, TagFilter tf)
    {
        menu = m;
        inputTags = new ArrayList();
        tagfilter = tf;
        database = d;
    }

   public void initController()
   {
       //ActionListeners for when A button is clicked
       menu.getSearch().addActionListener(e -> search());

   }

   private void search()
   {
      int[] indices = menu.getListOfButtons().getSelectedIndices();
       ArrayList<Product> retrievedProducts = new ArrayList<Product>();

      // This For loop adds the selected indices into the inputTags
      for(int i = 0; i < indices.length; i++)
      {
          inputTags.add(menu.getUniqueTags().get(indices[i]));
      }

      retrievedProducts = tagfilter.retrieveByTags(inputTags, database);


       for (Product product: retrievedProducts)
       {
           System.out.print(product.getProductName() + ": " + product.getProductPrice());

           for(String tag: product.getProductTags())
           {
               System.out.print(", " + tag);
           }

           System.out.println();
       }

      // We need to clear this array after every search
      inputTags.clear();

   }
}
