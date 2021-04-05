import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args){


        ImportController testControl = new ImportController(true);


        testControl.addFirstImportListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ActiveDatabase tempActiveDatabase = new ActiveDatabase();
                tempActiveDatabase.updateDatabase(testControl.overrideDatabase());
                DynamicMain newDyn = new DynamicMain(tempActiveDatabase);
                testControl.dispose();
            }});
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


