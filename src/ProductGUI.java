
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

    public ProductGUI(ArrayList<Product> x ) {

        JFrame frame = new JFrame("Product Display");

        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton searchButton = new JButton("Search");
        JTextField productField = new JTextField(20);
        String prompt = "Enter Product here";
        productField.setText(prompt);

        frame.add(productField, BorderLayout.NORTH);
        frame.add(searchButton, BorderLayout.SOUTH);

        frame.setVisible(true);

        searchButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e)
             {

                    //Saving input filepath
                    productName = productField.getText();


                     for(int i = 0; i < x.size(); i++)
                     {
                         System.out.println("No");
                         if(productName.equals(x.get(i).getProductName()))
                         {
                             databaseContents = "";
                             databaseContents += "Product Name: " + x.get(i).getProductName() +"\n";
                             databaseContents += "Product Price: " + x.get(i).getProductPrice() +"\n";
                             databaseContents += "Product Tags: " + x.get(i).getProductTags() + "\n";
                             //databaseContents += "Product Location: " + x.get(i).getProductLocation();
                             //System.out.println(databaseContents);
                             databasePreview = new JTextArea(databaseContents);
                             databasePreview.setEditable(false);
                             frame.add(databasePreview);
                           frame.setVisible(true);
                         }

                     }

                 /*
                  if(productName.equals("hello"))
                    {
                        System.out.println("Test");

                    }
                    else
                    {
                            JOptionPane.showMessageDialog(null, "Product not Found!", "Input Error", JOptionPane.ERROR_MESSAGE);

                    }
                    */


             }




           }

        );



    }






}

