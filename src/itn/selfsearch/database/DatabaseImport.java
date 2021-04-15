package itn.selfsearch.database;

import itn.selfsearch.map.*;

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
    private boolean imported;


    //Constructors
    /**
     * Constructor for DatabaseImport, requires a file path by default
     * @param inputPath Path of file being imported
     */
    public DatabaseImport(String inputPath){
        filePath = inputPath;
        imported = false;
    }


    //Import Function
    /**
     * Function used to read and parse an import JSON file, stores data into a Database Object
     * Stores Products, including name, price, and associated tags
     * Stores admin passwords
     * NOTE: Does not currently store item location...
     * @param databaseProducts productCatalogue ArrayList from Database Object
     * @param storeTags storeTags ArrayList from Database Object
     * @param databasePasswords adminPasswords ArrayList from Database Object
     * @param kioskCoordinate kioskLocation ArrayList from Database Object
     * @param mapData storeMap ArrayList from Database Object
     */
    public void importDatabase(ArrayList<Product> databaseProducts, ArrayList<String> storeTags, ArrayList<String> databasePasswords, ArrayList<Coordinate> kioskCoordinate, ArrayList<String> mapData){
        databasePasswords.clear();//Ensures that all previous passwords are erased
        databaseProducts.clear();//Ensures that all previous products are erased
        kioskCoordinate.clear();//Ensures that previous kiosk coordinate is erased
        mapData.clear();

        JSONParser fileParser = new JSONParser();//Variable used to parse input file contents

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

                //Below is getting product location. This JSON reader doesn't read ints apparently, only Longs.
                //We need int values for coordinates, so I had to convert string to int (tedious, I know...)
                String tempLocationX = (String) currentProduct.get("productLocationX");
                String tempLocationY = (String) currentProduct.get("productLocationY");
                int productLocationX = Integer.valueOf(tempLocationX);
                int productLocationY = Integer.valueOf(tempLocationY);
                importProduct.setProductLocation(new Coordinate(productLocationX, productLocationY));

                //Parsing individual Product tags
                JSONArray importedProductTags = (JSONArray) currentProduct.get("productTags");
                Iterator<String> tagIterator = importedProductTags.iterator();

                while(tagIterator.hasNext()){
                    importProduct.setProductTag(tagIterator.next());
                }

                databaseProducts.add(importProduct.clone());
            }

            //Parsing store tags
            JSONArray importTags = (JSONArray) jsonObject.get("storeTags");
            Iterator<String> storeTagIterator = importTags.iterator();
            String tempString;

            while(storeTagIterator.hasNext()){
                tempString = storeTagIterator.next();

                //Checks if a tag has already been added to the database
                if(!(storeTags.contains(tempString)))
                    storeTags.add(tempString);
            }

            //Parsing admin passwords
            JSONArray importPasswords = (JSONArray) jsonObject.get("adminPasswords");
            Iterator<String> passwordIterator = importPasswords.iterator();

            while(passwordIterator.hasNext()){
                databasePasswords.add(passwordIterator.next());
            }

            //Below is getting kiosk location. This JSON reader doesn't read ints apparently, only Longs.
            //We need int values for coordinates, so I had to convert string to int (tedious, I know...)
            String tempLocationX = (String) jsonObject.get("kioskLocationX");
            String tempLocationY = (String) jsonObject.get("kioskLocationY");
            int productLocationX = Integer.valueOf(tempLocationX);
            int productLocationY = Integer.valueOf(tempLocationY);
            kioskCoordinate.add(0, new Coordinate(productLocationX, productLocationY));

            //Checking if no passwords were read. If no passwords read, white space is added as a password (prevents admin from locking themselves out of software)
            if(databasePasswords.size() == 0)
                databasePasswords.add("");

            String importedMap = (String) jsonObject.get("mapData");
            mapData.add(importedMap);

            imported = true;
        }
        catch(IOException | ParseException e){
            e.printStackTrace();
        }
    }

    //Functionality
    /**
     * Function used to determine if an input file was parsed successfully
     * @return true if file was parsed, false if not
     */
    public boolean wasImported(){
        return imported;
    }

}
