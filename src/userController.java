import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.Scanner;


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
     * Compares the input password to the
     * arraylist of correct passwords and returns
     * true if it matches
     * @param inputPassword
     * @param correctPassword
     * @return
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
    public userController(userView v, Database data)
    {
        uView = v;
        d = data;
    }

    public void initController()
    {
        uView.getEnterButton().addActionListener(e -> verifyPassword(uView.getUserPassword().getText(), d.getPasswords()));
        uView.getBackButton().addActionListener(e -> updateBackListener());
    }

    public void addAdminLoginListener(ChangeListener newListener)
    {
        adminLoginListener.add(newListener);
    }

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
