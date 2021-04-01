import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args){
//        //Creating Database
//        System.out.print("Enter file path: ");
//        Scanner input = new Scanner(System.in);
//        String userinput = input.nextLine();
//        System.out.println("Your input was: " + userinput + "\n");
  //      DatabaseImport newDatabase = new DatabaseImport(userinput);

//        Database testDatabase = new Database("Password", newDatabase);

        //
//
//        // Added these two objects so that we can access NameFilter, and TagFilter functions
//        NameFilter nf = new NameFilter();
//        TagFilter tf = new TagFilter();
//
//        //Displaying Database
//        testDatabase.printDatabase();
//
//
//        //Testing Search By Name
//        System.out.println("\n\n");
//
//        System.out.print("Search By Name Test: ");
//        input = new Scanner(System.in);
//        userinput = input.nextLine();
//        System.out.println("Your input was: " + userinput + "\n");
//
//        if (testDatabase.validProductName(userinput)){
//            Product userProduct = nf.retrieveByName(userinput, testDatabase);
//
//            System.out.print(userProduct.getProductName() + ": " + userProduct.getProductPrice());
//
//            for(String tag: userProduct.getProductTags()){
//                System.out.print(", " + tag);
//            }
//        }
//
//        System.out.println("\n\n");
//
//
//        //Testing Search by Tag
//        System.out.print("Search By Tag Test: ");
//        input = new Scanner(System.in);
//        userinput = input.nextLine();
//        System.out.println("Your input was: " + userinput + "\n");
//        Scanner stringParser = new Scanner(userinput);
//        ArrayList<String> parsedTags = new ArrayList<String>();
//        ArrayList<Product> retrievedProducts = new ArrayList<Product>();
//
//        while(stringParser.hasNext())
//        {
//            parsedTags.add(stringParser.next());
//        }
//
//        retrievedProducts = tf.retrieveByTags(parsedTags, testDatabase);
//
//        for (Product product: retrievedProducts){
//            System.out.print(product.getProductName() + ": " + product.getProductPrice());
//
//            for(String tag: product.getProductTags()){
//                System.out.print(", " + tag);
//            }
//
//            System.out.println();
//        }

//        //Creating a userinput and a response variable to capture and display input
//        String userinput;
//        String response;
//
//        //Creating the window dialouge box asking for user input
//        userinput = JOptionPane.showInputDialog(null, "Enter a value: ", "User Input Test", JOptionPane.INFORMATION_MESSAGE);
//
//        //Logic to detect what user inputted
//        if(userinput == null)
//            response = "You hit cancel";
//        else if(userinput.equals(""))
//            response = "You didn't type any thing";
//        else
//            response = "You typed " + userinput;
//
//        //Displaying user input via another dialouge box
//        JOptionPane.showMessageDialog(null, response, "User Input Results", JOptionPane.INFORMATION_MESSAGE, new Signature());
//
//
//
//        //Creating the frame
       // JFrame testframe = new JFrame();
//
//        //NOTE: JFrames can house Pannels. Pannels house components such as buttons and text fields. You can add those components directly to the frame without a pannel if desired.
//
//        //Creating components
      // final JButton yesButton = new JButton("Yes");
      // final JButton noButton = new JButton("No");

      //  final int textboxSize = 20;
      //  final JTextField testField = new JTextField(textboxSize);//Parameter specifies text box size
      //  testField.setText("I am a text field!");

//        //Adding things to a frame
     //   testframe.setLayout(new FlowLayout());//Making a layout/canvas to add the components to on the frame

     //    testframe.add(yesButton);
      //   testframe.add(noButton);
      // testframe.add(testField);
      //  testframe.setVisible(true);
//
//        //Packaging the frame, making sure it works and looks proper
      //  testframe.pack();//makes the frame big enough for the components that ill be adding
      //  testframe.setSize(300, 300);//Setting size of frame
//        testframe.setTitle("Testing out JFrames!");//Title seen at top border of JFrame window
//        testframe.setVisible(true);//we want to see the frame right?
//        testframe.setLocationRelativeTo(null);//Centers window!!
//        testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Allows the frame to be closed
//
//        //Adding functionality to the buttons
//        yesButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            //Note: this is where the button logic goes! This example is a basic implementation
//                yesButton.setText(testField.getText());
//            }
//        });
//
//        noButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                noButton.setText(testField.getText());
//            }
//        });
//
//
//
//        //Creating radio/fill-in buttons
//        final JRadioButton optionA = new JRadioButton("OptionA");
//        final JRadioButton optionB = new JRadioButton("OptionB");
//
//        //Adding them to a button collection, if one is selected, the other turns off
//        ButtonGroup options = new ButtonGroup();
//
//        options.add(optionA);
//        options.add(optionB);
//
//        //Giving button logic
//        optionA.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                yesButton.setText("OptionA");
//            }
//        });
//
//        optionB.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                    noButton.setText("OptionB");
//            }
//        });
//
//        //Adding the button group to a panel
//        JPanel buttons = new JPanel();
//        buttons.setLayout(new GridLayout(1, 2));//Setting up the look/format of panel
//        buttons.add(optionA);//Adding the physical button to the layout, different process than the options group
//        buttons.add(optionB);
//        buttons.setBorder(BorderFactory.createEtchedBorder());
//        buttons.setVisible(true);
//
//        //Adding panel to frame
//        testframe.add(buttons);


        DynamicMain newDyn = new DynamicMain();
//        //Creating a database for the software to use
//        ActiveDatabase testDatabase = new ActiveDatabase();
//        //Creating a class for importing a new database
//        ImportController testControl = new ImportController();

        System.out.println(newDyn.getProductSize());

        //Updates database if admin finds an import file and confirms import


        /**
         * This is where the program calls system out on product array size after import is completed
         */
        newDyn.addImportedListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println(newDyn.getTestDatabase().getProducts().size());

                /**
                 * Kevin, put your GUI test code here
                 */

                //Use this to get ActiveDatabase
                newDyn.getTestDatabase();





                //ProductGUI test = new ProductGUI(testDatabase.getProducts());
                /**
                 *
                 */

            }});
//
//        });
//        System.out.println(testDatabase.getProducts().size());
//        ProductGUI test = new ProductGUI(testDatabase.getProducts());
    }



    //Test Tool Functions
    public static ArrayList<String> createTagList (String tag1, String tag2, String tag3){
        ArrayList<String> newTagList = new ArrayList<String>();

        newTagList.add(tag1);
        newTagList.add(tag2);
        newTagList.add(tag3);

        return (ArrayList<String>) newTagList.clone();
    }



    //"C:\\project testing ground\\src\\import.json"


}


