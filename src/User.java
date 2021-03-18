import java.util.ArrayList;
import java.util.Scanner;


public class User {

    Scanner passwordScanner = new Scanner(System.in);
    String inputPassword;
    ArrayList<String> correctPassword;
    boolean adminMode;
    String defaultPassword;

    public boolean verifyPassword(String inputPassword , ArrayList<String> correctPassword)
    {

        adminMode = false;
        for(int i = 0; i < correctPassword.size(); i++)
        {
            if(inputPassword.equals(correctPassword.get(i)))
            {
                adminMode = true;
            }

        }

        return adminMode;
    }





}
