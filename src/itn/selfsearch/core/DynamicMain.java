package itn.selfsearch.core;

import itn.selfsearch.database.*;
import itn.selfsearch.search.*;
import itn.selfsearch.map.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class is responsible controlling which panel is displayed to the user.
 * This class also serves the main frame for the software.
 * Uses observer pattern
 */
public class DynamicMain extends JFrame{
    //Creating a database for the software to use
    private ActiveDatabase testDatabase;
    final ArrayList<ChangeListener> importListeners = new ArrayList<ChangeListener>();//ArrayList of listeners

    /**
     * Constructor for DynamicMain
     * @param inputActiveDatabase A database for the program to use
     */
    public DynamicMain(ActiveDatabase inputActiveDatabase){
        testDatabase = inputActiveDatabase;

        // Set up the frame with a few settings.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("ITNSelfSearch");

        // Declare variables for NameFilter and TagFilter
        NameFilter nf = new NameFilter();
        TagFilter tf = new TagFilter();

        // Declare variables for homeView and homeController and call initController
        homeView v = new homeView();
        homeController c = new homeController(v, nf, tf, getTestDatabase().getDatabase());
        c.initController();

        // Declare our viewSet JPanel that we will use as the cardLayout
        JPanel viewSet = new JPanel(new CardLayout());

        // The first panel that needs to be added to viewSet is the homeView panel
        viewSet.add(v, "v");
        this.add(viewSet);
        CardLayout cardlayout = (CardLayout) (viewSet.getLayout());


        // The rest of the code is strictly just change listeners that listens for when our user selects buttons.
        // It will then switch panels based on what button was selected

        // Listens for when user searches for product name
        c.addNameSearchListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Call the ProductGUI function because this is the next panel needed
                ProductGUI testView = new ProductGUI(c.getRetrievedProduct(), testDatabase.getKioskLocation(), testDatabase.getStoreMap());

                //Changing card set to product display
                viewSet.add(testView, "testView");
                cardlayout.show(viewSet, "testView");

                //Listens for when user clicks the main menu button (to exit product view)
                testView.addMainListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        cardlayout.show(viewSet, "v");
                        viewSet.remove(testView);
                    }
                });

            }
        });

        //Listens for when user selects tagMenu
        c.addTagSearchListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                // Call the tagMenu because that is the next panel used
                tagMenu tM = new tagMenu(getTestDatabase().getDatabase());

                // Call the tagMenu Controller so we can control the tagMenu
                tagMenuController tC = new tagMenuController(tM, getTestDatabase().getDatabase(), tf);
                tC.initController();

                // Changing card set to tag Menu
                viewSet.add(tM, "tM");
                cardlayout.show(viewSet, "tM");

                // Now inside tagMenu we have other buttons to listen for so this listener is nested inside tagMenu
                // Listens for when user clicks the main menu button (to exit product view)
                tC.addBackListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        cardlayout.show(viewSet, "v");
                        viewSet.remove(tM);
                    }
                });

                // Listen for when user clicks on the search button
                tC.addSearchListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        // Call productSelection because this is the next panel needed
                        productSelection pSelection = new productSelection(tC.getRetrievedProducts(), getTestDatabase().getDatabase(), tC);

                        // Add it to the viewSet panel and show it
                        viewSet.add(pSelection, "pSelection");
                        cardlayout.show(viewSet, "pSelection");

                        // Now inside the pSeelction panel there are buttons we need to listen for so these listeners is nested inside it
                        // Listen for cancel if it is selected then show the previous panel tagMenu
                        pSelection.addCancelListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent e) {
                                cardlayout.show(viewSet, "tM");
                                viewSet.remove(pSelection);
                            }
                        });

                        // Listen for the select button if it is pressed then show the next and final panel ProductGUI
                        pSelection.addSelectListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent e) {
                                // Call pGUI since it is next Panel needed
                                ProductGUI pGUI = new ProductGUI(pSelection.getP(), testDatabase.getKioskLocation(), testDatabase.getStoreMap());

                                // Set it to show pGUI
                                viewSet.add(pGUI, "pGUI");
                                cardlayout.show(viewSet, "pGUI");

                                viewSet.remove(pSelection);
                                viewSet.remove(tM);

                                //Listens for when user clicks the main menu button (to exit product view)
                                pGUI.addMainListener(new ChangeListener() {
                                    @Override
                                    public void stateChanged(ChangeEvent e) {
                                        cardlayout.show(viewSet, "v");
                                        viewSet.remove(pGUI);
                                    }
                                });
                            }
                        });

                    }
                });
            }
        });

        //Listens for when user clicks admin
        c.addAdminListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                userView loginView = new userView();
                userController userLogin = new userController(loginView, testDatabase.getDatabase());
                userLogin.initController();

                viewSet.add(loginView, "loginView");
                cardlayout.show(viewSet, "loginView");

                userLogin.addAdminLoginListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        adminView adminMenu = new adminView();

                        //Changing card set to admin menu
                        viewSet.add(adminMenu, "adminView");
                        cardlayout.show(viewSet, "adminView");

                        adminMenu.addMainListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent e) {
                                cardlayout.show(viewSet, "v");
                                viewSet.remove(adminMenu);
                                viewSet.remove(loginView);
                            }
                        });

                        adminMenu.addMapListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent e) {
                                MapEditor mapEditor = new MapEditor();

                                //Changing card set to admin menu
                                viewSet.add(mapEditor, "mapEditor");
                                cardlayout.show(viewSet, "mapEditor");

                                // Register listener for the map editor to save map data to the database
                                mapEditor.addMapSaveListener(new ChangeListener() {
                                    @Override
                                    public void stateChanged(ChangeEvent e) {
                                        testDatabase.setStoreMap(mapEditor.saveMapData());
                                        JOptionPane.showMessageDialog(null, "Map has been saved!", "Save Confirmation", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                });

                                // Register listener for the map editor to load map data from the database
                                mapEditor.addMapLoadListener(new ChangeListener() {
                                    @Override
                                    public void stateChanged(ChangeEvent e) {
                                        mapEditor.loadMapData(testDatabase.getStoreMap());
                                    }
                                });

                                // Register listener to exit the map editor
                                mapEditor.addBackListener(new ChangeListener() {
                                    @Override
                                    public void stateChanged(ChangeEvent e) {
                                        cardlayout.show(viewSet, "adminView");
                                        viewSet.remove(mapEditor);
                                    }
                                });
                            }
                        });

                        adminMenu.addImportListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent e) {
                                //Setting up frame design/layout

                                ImportView importWindow = new ImportView();
                                viewSet.add(importWindow, "importWindow");
                                cardlayout.show(viewSet, "importWindow");

                                importWindow.addBackListener(new ChangeListener() {
                                    @Override
                                    public void stateChanged(ChangeEvent e) {
                                        cardlayout.show(viewSet, "adminView");
                                        viewSet.remove(importWindow);
                                    }
                                });

                                //If an import was successful, panel switches to database view and allows admin to save or cancel
                                importWindow.addChangeListener(new ChangeListener() {
                                    @Override
                                    public void stateChanged(ChangeEvent e) {
                                        //Switching panel to database view

                                        DatabaseDisplay databasePanel;
                                        databasePanel = new DatabaseDisplay(importWindow.getNewImport());
                                        JButton saveButton = new JButton("Save");
                                        JButton cancelButton = new JButton("Cancel");

                                        viewSet.add(databasePanel, "databasePanel");
                                        CardLayout cardlayout = (CardLayout) (viewSet.getLayout());
                                        cardlayout.show(viewSet, "databasePanel");
                                        viewSet.remove(importWindow);

                                        //If saved, listeners are notified, database import is saved, and user is prompted
                                        saveButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                //ChangeEvent importable = new ChangeEvent(this);
                                                testDatabase.updateDatabase(importWindow.getNewImport().clone());
                                                c.refreshDatabase(importWindow.getNewImport().clone());
                                                //testDatabase = new ActiveDatabase(importWindow.getNewImport().clone());
                                                importWindow.clearNewImport();

                                                JOptionPane.showMessageDialog(null, "Database has been imported!", "Import Confirmation", JOptionPane.INFORMATION_MESSAGE);
                                                cardlayout.show(viewSet, "adminView");
                                                viewSet.remove(databasePanel);
                                            }
                                        });

                                        //If canceled, imported data from input file contents is erased, user is notified and returned to the file search panel
                                        cancelButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                importWindow.clearNewImport();
                                                JOptionPane.showMessageDialog(null, "Import aborted!", "Import Canceled", JOptionPane.INFORMATION_MESSAGE);
                                                cardlayout.show(viewSet, "adminView");
                                                viewSet.remove(databasePanel);
                                            }
                                        });

                                        databasePanel.add(saveButton);
                                        databasePanel.add(cancelButton);
                                    }
                                });
                            }
                        });
                    }
                });

                userLogin.addBackListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        cardlayout.show(viewSet, "v");
                        viewSet.remove(loginView);
                    }
                });

            }
        });
    }

    /**
     * Method to get the ActiveDatabase being used by the program.
     * @return The ActiveDatabase being used by the program.
     */
    public ActiveDatabase getTestDatabase() {
        return testDatabase;
    }
}