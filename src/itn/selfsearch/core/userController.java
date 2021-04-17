package itn.selfsearch.core;

import itn.selfsearch.database.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class responsible for controlling the functionality of the userView panel.
 */
public class userController {

    Scanner passwordScanner = new Scanner(System.in);
    String inputPassword;
    ArrayList<String> correctPassword;
    boolean adminMode;
    String defaultPassword;

    private userView uView;
    private Database d;

    private final ArrayList<ChangeListener> adminLoginListener = new ArrayList<ChangeListener>();//ArrayList of listeners
    private final ArrayList<ChangeListener> backListener = new ArrayList<ChangeListener>();//ArrayList of listeners

    /**
     * Method to verify if the password entered by the user is correct.
     * @param inputPassword The password entered by the user
     * @param correctPassword ArrayList of correct passwords
     * @return True if the inputted password matches one of the correct passwords, false otherwise.
     */
    public boolean verifyPassword(String inputPassword , ArrayList<String> correctPassword)
    {

        adminMode = false;
        for(int i = 0; i < correctPassword.size(); i++)
        {
            if(inputPassword.equals(correctPassword.get(i)))
            {
                adminMode = true;

                ChangeEvent adminLoginSelected = new ChangeEvent(this);
                for(ChangeListener listener: adminLoginListener)
                {
                    listener.stateChanged(adminLoginSelected);
                }
            }

        }

        if(adminMode == false){
            JOptionPane.showMessageDialog(null, "Incorrect password!", "Login Failure", JOptionPane.ERROR_MESSAGE);
        }

        return adminMode;
    }

    /**
     * Constructor for userController
     * @param v userView panel for userController to use
     * @param data database for userController to use.
     */
    public userController(userView v, Database data)
    {
        uView = v;
        d = data;
    }

    /**
     * Method to initialize the userController.
     */
    public void initController()
    {
        uView.getEnterButton().addActionListener(e -> verifyPassword(uView.getUserPassword().getText(), d.getPasswords()));
        uView.getBackButton().addActionListener(e -> updateBackListener());
    }

    /**
     * Function used to determine if user clicks on the admin login button
     * @param newListener an input listener
     */
    public void addAdminLoginListener(ChangeListener newListener)
    {
        adminLoginListener.add(newListener);
    }

    /**
     * Function used to determine if user clicks on back
     * @param newListener an input listener
     */
    public void addBackListener(ChangeListener newListener)
    {
        backListener.add(newListener);
    }

    private void updateBackListener()
    {
        ChangeEvent backButtonSelected = new ChangeEvent(this);
        for(ChangeListener listener: backListener)
        {
            listener.stateChanged(backButtonSelected);
        }
    }


}
