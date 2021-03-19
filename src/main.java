import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args){

        ArrayList<String> parsedTags = new ArrayList<String>();
        ArrayList<Product> retrievedProducts = new ArrayList<Product>();


        //Creating Database
        System.out.print("Enter file path: ");
        Scanner input = new Scanner(System.in);
        String userinput = input.nextLine();
        System.out.println("Your input was: " + userinput + "\n");
        DatabaseImport newDatabase = new DatabaseImport(userinput);
        Database testDatabase = new Database("Password", newDatabase);

        // Added these two objects so that we can access NameFilter, and TagFilter functions
        NameFilter nf = new NameFilter();
        TagFilter tf = new TagFilter();

        //Displaying Database
        testDatabase.printDatabase();


        //Testing Search By Name
        System.out.println("\n\n");

        System.out.print("Search By Name Test: ");
        input = new Scanner(System.in);
        userinput = input.nextLine();
        System.out.println("Your input was: " + userinput + "\n");

        if (testDatabase.validProductName(userinput)){
            Product userProduct = nf.retrieveByName(userinput, testDatabase);

            System.out.print(userProduct.getProductName() + ": " + userProduct.getProductPrice());

            for(String tag: userProduct.getProductTags()){
                System.out.print(", " + tag);
            }
        }

        System.out.println("\n\n");


        //Testing Search by Tag
        System.out.print("Search By Tag Test: ");
        input = new Scanner(System.in);
        userinput = input.nextLine();
        System.out.println("Your input was: " + userinput + "\n");
        Scanner stringParser = new Scanner(userinput);

        // This while loop is parsing our stringParser that has the userInput inside of it and adding it to the arraylist
        while(stringParser.hasNext())
        {
            parsedTags.add(stringParser.next());
        }

        // Making a call to retrieveByTags and sending it the arrayList of parsed tags
        // Then the retrieveByTags function is going to locate a product with that tag and put it into the retrievedProducts array list
        retrievedProducts = tf.retrieveByTags(parsedTags, testDatabase);

        System.out.println("Items with the tag(s) " + userinput + ":");

        for (Product product: retrievedProducts){
            System.out.print(product.getProductName() + ": " + product.getProductPrice());

            for(String tag: product.getProductTags()){
                System.out.print(", " + tag);
            }

            System.out.println();
        }

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
