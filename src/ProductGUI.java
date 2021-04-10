
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ProductGUI extends JPanel {

    String productName;
    Product displayedProduct;
    String databaseContents;
    private JTextArea databasePreview;
    final ArrayList<ChangeListener> mainlistener = new ArrayList<ChangeListener>();//ArrayList of listeners

    public ProductGUI(Product inputProduct, Coordinate kioskCoordinate) {

        //JFrame frame = new JFrame("Product Display");

//        frame.setSize(300, 300);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        databaseContents = "";
        databaseContents += "Product Name: " + inputProduct.getProductName() +"\n";
        databaseContents += "Product Price: $" + inputProduct.getProductPrice() +"\n";
        databaseContents += "Product Tags: " + inputProduct.getProductTags() + "\n";
        databasePreview = new JTextArea(databaseContents);
        databasePreview.setEditable(false);
        this.add(databasePreview, BorderLayout.NORTH);
        this.setVisible(true);


        ProductLocator productMap = new ProductLocator(kioskCoordinate, inputProduct.getProductLocation());
        this.add(productMap, BorderLayout.CENTER);


        JButton menuButton = new JButton("Main Menu");
        this.add(menuButton, BorderLayout.SOUTH);

        menuButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e)
             {
                 ChangeEvent menuSelected = new ChangeEvent(this);
                 for(ChangeListener listener: mainlistener){
                     listener.stateChanged(menuSelected);
                 }
             }
           }

        );



    }

    //Sets
    /**
     * Function used to determine if user searches by name
     * @param newListener an input listener
     */
    public void addMainListener(ChangeListener newListener){
        mainlistener.add(newListener);
    }






}

