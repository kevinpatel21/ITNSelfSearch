import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * DatabaseImport is responsible for saving Product data from a JSON file into a Database Object
 */
public class DatabaseImport {
    //Attributes
    private String filePath;


    //Constructors
    /**
     * Constructor for DatabaseImport, requires a file path by default
     * @param inputPath Path of file being imported
     */
    public DatabaseImport(String inputPath){
        filePath = inputPath;
    }


    //Import Function
    /**
     * Function used to read and parse an import JSON file, stores data into a Database Object
     * Stores Products, including name, price, and associated tags
     * Stores admin passwords
     * NOTE: Does not currently store item location...
     * @param databaseProducts productCatalogue ArrayList from Database Object
     * @param databasePasswords adminPasswords ArrayList from Database Object
     */
    public void importDatabase(ArrayList<Product> databaseProducts, ArrayList<String> databasePasswords){
        JSONParser fileParser = new JSONParser();

        try{
            //Setting up file for parsing
            Object fileObject = fileParser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) fileObject;

            //Parsing individual Products in the JSON file
            JSONArray productList = (JSONArray) jsonObject.get("products");
            Iterator<JSONObject> productIterator = productList.iterator();

            while(productIterator.hasNext()){
                //Copying over Product attributes
                Product importProduct = new Product();
                JSONObject currentProduct = productIterator.next();

                importProduct.setProductName((String) currentProduct.get("productName"));
                importProduct.setProductPrice((Double) currentProduct.get("productPrice"));

                //Parsing individual Product tags
                JSONArray importedProductTags = (JSONArray) currentProduct.get("productTags");
                Iterator<String> tagIterator = importedProductTags.iterator();

                while(tagIterator.hasNext()){
                    importProduct.setProductTag(tagIterator.next());
                }

                databaseProducts.add(importProduct);
            }

            //Parsing admin passwords
            JSONArray importPasswords = (JSONArray) jsonObject.get("adminPasswords");
            Iterator<String> passwordIterator = importPasswords.iterator();

            while(passwordIterator.hasNext()){
                databasePasswords.add(passwordIterator.next());
            }

        }
        catch(IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
