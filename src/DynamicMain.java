import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DynamicMain extends JFrame{
    //Creating a database for the software to use
    private ActiveDatabase testDatabase;
    //Creating a class for importing a new database
    //private ImportController testControl = new ImportController(false);
    final ArrayList<ChangeListener> importListeners = new ArrayList<ChangeListener>();//ArrayList of listeners

    public DynamicMain(ActiveDatabase inputActiveDatabase){
        testDatabase = inputActiveDatabase;

        // Set up the frame with a few settings.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("ITNSelfSearch");

        //Updates database if admin finds an import file and confirms import
        /**
         * Use this section below as main
         */

        tagMenu tM = new tagMenu(getTestDatabase().getDatabase());
        NameFilter nf = new NameFilter();
        TagFilter tf = new TagFilter();

        tagMenuController tC = new tagMenuController(tM, getTestDatabase().getDatabase(), tf);
        tC.initController();

        homeView v = new homeView();
        homeController c = new homeController(v, nf, tf, getTestDatabase().getDatabase());
        c.initController();

        JPanel viewSet = new JPanel(new CardLayout());

        //Adding panels to the frame cardset
        viewSet.add(v, "v");
        this.add(viewSet);
        CardLayout cardlayout = (CardLayout) (viewSet.getLayout());


        //Listens for when user searches for product name
        c.addNameSearchListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
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

        //Listens for when user searches by product tags
        c.addTagSearchListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JPanel testView2 = new JPanel();
                testView2.setLayout(new FlowLayout());

                String prompt = "Tag search has been selected.\nThis is a temporary panel, this is where tag search GUI will be displayed.";


                JTextArea testText = new JTextArea();
                testText = new JTextArea(prompt, 10, 20);
                testText.setEditable(false);
                testView2.add(testText);

                JButton mainMenu = new JButton("Main Menu");
                testView2.add(mainMenu);

                //Changing card set to tag display
                viewSet.add(testView2, "testView2");
                cardlayout.show(viewSet, "testView2");

                System.out.println(viewSet.getComponentCount());

                //Listens for when user clicks the main menu button (to exit product view)
                mainMenu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardlayout.show(viewSet, "v");
                        viewSet.remove(testView2);
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
        /**
         * End of main
         */
    }

    public int getProductSize(){
        return testDatabase.getProducts().size();
    }

    public void addImportedListener(ChangeListener newListener){
        importListeners.add(newListener);
    }

    public ActiveDatabase getTestDatabase() {
        return testDatabase;
    }
}
