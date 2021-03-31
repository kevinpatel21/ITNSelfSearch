
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ProductGUI extends JFrame {

    String productName;
    Product displayedProduct;

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


                System.out.println(x.size());


               // System.out.println(x.get(0).getProductName());




                if(productName.equals("hello"))
                {
                    System.out.println("Test");

                }
                else
                    {
                        JOptionPane.showMessageDialog(null, "Product not Found!", "Input Error", JOptionPane.ERROR_MESSAGE);

                     }



            }


/*
                //Detects if user is trying to search filepath that is not empty or just the prompt
                if(!(filepath.equals(prompt)) && !(filepath.equals(""))){

                    //Attempts to extract data from input file
                    importingDatabase = new DatabaseImport(filepath);
                    importingDatabase.importDatabase(newDatabase.getProductCatalogue(), newDatabase.getPasswords());

                    //If valid, listeners are notified
                    if(importingDatabase.wasImported()){
                        ChangeEvent importable = new ChangeEvent(this);

                        for(ChangeListener listener: listeners){
                            listener.stateChanged(importable);
                        }
                    }
                    //If not, user is notified that filepath was not valid
                    else{
                        JOptionPane.showMessageDialog(null, "File was not found!", "Filepath Error", JOptionPane.ERROR_MESSAGE);
                    }*/

                }

        );



    }






}

