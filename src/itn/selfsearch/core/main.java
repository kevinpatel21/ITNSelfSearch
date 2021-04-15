package itn.selfsearch.core;

import itn.selfsearch.database.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main function used to initialize the frame of the software
 */
public class main {
    /**
     * Main function used to initialize the frame of the software
     * @param args Command Line arguments, not used by the program.
     */
    public static void main(String[] args){

        ImportController testControl = new ImportController(true);

        //Listens if an import file is added to software prior to initialization
        testControl.addFirstImportListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Setting up an ActiveDatabase and transferring it to DynamicMain
                ActiveDatabase tempActiveDatabase = new ActiveDatabase();
                tempActiveDatabase.updateDatabase(testControl.overrideDatabase());
                DynamicMain newDyn = new DynamicMain(tempActiveDatabase);
                testControl.dispose();
            }});
    }

}


