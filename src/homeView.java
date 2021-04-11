import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that implements our GUI for the homescreen
 */
public class homeView extends JPanel
{
    // Variables needed for the Swing framework to display UI to user
    private JToggleButton nameFilterToggle; //
    private JButton tagMenuButton; //
    private JButton searchButton; //
    private JTextField userText; //
    private JLabel ITN; //
    private JButton adminButton; //
    private boolean nameFilterBoolean; //
    private boolean tagFilterBoolean; //
    private String text; //
    private JFrame frame; //

    /**
     * This function creates the frame and all of the settings associated with the frame and calls addComponents where
     * the components are then added to a panel
     */
    private void createFrame()
    {

        //frame = new JFrame();
//        addComponents(frame.getContentPane());
        addComponents(this);
//        // Set up the frame with a few settings.
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600, 400);
//        frame.setResizable(false);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        frame.setTitle("Homescreen");
    }

    /**
     *
     *  This function uses a GridBagLayout style.
     * @param pane This is our container that holds our panel.
     */
    private void addComponents(Container pane)
    {
        // Set the layout style to GridBagLayout
        // This is the variable we will use to constrain our settings
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create our variables for our GUI components
        ITN = new JLabel("ITN SELF SEARCH");
        userText = new JTextField();
        searchButton = new JButton("Search");
        adminButton = new JButton("Admin");
        nameFilterToggle = new JToggleButton("Name Filter ");
        tagMenuButton = new JButton("Tag Menu ");

        // Set the settings for our adminButton component
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        adminButton.setPreferredSize(new Dimension(100,20));
        pane.add(adminButton, c);

        // Set the settings for the ITN Label component
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(50,0,0,0);
        pane.add(ITN, c);

        // Set the settings for the searchButton component
        c.anchor = GridBagConstraints.PAGE_END;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(0,0,60,0);
        searchButton.setPreferredSize(new Dimension(200,30));
        pane.add(searchButton, c);

        // Set the settings for the userText Textbox component
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(10,0,0,0);
        userText.setPreferredSize(new Dimension(250,25));
        pane.add(userText, c);

        // Set the settings for the nameFilterToggle button component
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(0,167,50,0);
        nameFilterToggle.setPreferredSize(new Dimension(125, 25));
        pane.add(nameFilterToggle, c);

        // Set the settings for the tagFilterToggle button component
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(0,0,50,167);
        tagMenuButton.setPreferredSize(new Dimension(125,25));
        pane.add(tagMenuButton, c);

        // Just messing with the color
        pane.setBackground(Color.LIGHT_GRAY);

    }

    /**
     * Constructor for homeView. When this executes it will notify the console that you hit this function and call createFrame()
     */
    public homeView()
    {
        System.out.println("GUI Successfully loaded in homeView");
        createFrame();
    }

    // The rest of the functions below are just setters and getters for our GUI components

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getITNLabel() {
        return ITN;
    }

    public void setITNLabel(JLabel ITN) {
        this.ITN = ITN;
    }

    public JTextField getUserText() {
        return userText;
    }

    public void setUserText(JTextField userText) {
        this.userText = userText;
    }

    public JToggleButton getNameFilterToggle() {
        return nameFilterToggle;
    }

    public void setNameFilterToggle(JToggleButton nameFilterToggle) {
        this.nameFilterToggle = nameFilterToggle;
    }

    public JButton getTagMenuButton() {
        return tagMenuButton;
    }

    public void setTagMenuButton(JButton tagMenuButton) {
        this.tagMenuButton = tagMenuButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    public JButton getAdminButton() {
        return adminButton;
    }

    public void setAdminButton(JButton adminButton) {
        this.adminButton = adminButton;
    }

    public boolean getNameFilterBoolean() {
        return nameFilterBoolean;
    }

    public void setNameFilterBoolean(boolean nameFilterBoolean) {
        this.nameFilterBoolean = nameFilterBoolean;
    }

    public boolean getTagFilterBoolean() {
        return tagFilterBoolean;
    }

    public void setTagFilterBoolean(boolean tagFilterBoolean) {
        this.tagFilterBoolean = tagFilterBoolean;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}