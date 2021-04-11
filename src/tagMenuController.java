import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class tagMenuController
{
    private tagMenu menu;
    private Database database;
    private Product retrievedProduct;
    private ArrayList<String> inputTags;
    private TagFilter tagfilter;
    private ArrayList<Product> retrievedProducts;

    private final ArrayList<ChangeListener> searchListener = new ArrayList<ChangeListener>();//ArrayList of listeners
    private final ArrayList<ChangeListener> backListener = new ArrayList<ChangeListener>();//ArrayList of listeners



    public tagMenuController(tagMenu m, Database d, TagFilter tf)
    {
        menu = m;
        inputTags = new ArrayList();
        tagfilter = tf;
        database = d;
        retrievedProducts = new ArrayList();
    }

    public void initController()
    {
        //ActionListeners for when A button is clicked
        menu.getSearch().addActionListener(e -> search());
        menu.getCancel().addActionListener(e -> cancel());

    }

    private void cancel()
    {
        ChangeEvent backButtonSelected = new ChangeEvent(this);
        for(ChangeListener listener: backListener)
        {
            listener.stateChanged(backButtonSelected);
        }
    }

    private void search()
    {
        int[] indices = menu.getListOfButtons().getSelectedIndices();

        if(menu.getListOfButtons().isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null, "You have not made any selections");
        }
        else
        {
            // This For loop adds the selected indices into the inputTags
            for(int i = 0; i < indices.length; i++)
            {
                inputTags.add(menu.getUniqueTags().get(indices[i]));
            }

            retrievedProducts = tagfilter.retrieveByTags(inputTags, database);

            // Send the retrievedProducts list to the productSelection screen

            for (Product product: retrievedProducts)
            {
                System.out.print(product.getProductName() + ": " + product.getProductPrice());

                for(String tag: product.getProductTags())
                {
                    System.out.print(", " + tag);
                }

                System.out.println();
            }

            ChangeEvent searchSelected = new ChangeEvent(this);
            for(ChangeListener listener: searchListener){
                listener.stateChanged(searchSelected);
            }

            // We need to clear this array after every search
            inputTags.clear();
        }



    }

    public void addBackListener(ChangeListener newListener)
    {
        backListener.add(newListener);
    }

    public void addSearchListener(ChangeListener newListener)
    {
        searchListener.add(newListener);
    }

    public ArrayList<Product> getRetrievedProducts()
    {
        return retrievedProducts;
    }

}
