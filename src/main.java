import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
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
        ArrayList<String> parsedTags = new ArrayList<String>();
        ArrayList<Product> retrievedProducts = new ArrayList<Product>();

        while(stringParser.hasNext())
        {
            parsedTags.add(stringParser.next());
        }

        retrievedProducts = tf.retrieveByTags(parsedTags, testDatabase);

        for (Product product: retrievedProducts){
            System.out.print(product.getProductName() + ": " + product.getProductPrice());

            for(String tag: product.getProductTags()){
                System.out.print(", " + tag);
            }

            System.out.println();
        }

        //Testing getProductCatalogue
        System.out.println("Testing getProductCatalogue. The cloned version should NOT include the new product.");
        ArrayList<Product> previousProducts = testDatabase.getProductCatalogue();
        ArrayList<String> sandalTags = new ArrayList<String>();
        sandalTags.add("Clothing");
        sandalTags.add("Shoes");
        sandalTags.add("Leather");
        Product Sandals = new Product("Sandals", 4.99);
        Sandals.setProductTags(sandalTags);




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
