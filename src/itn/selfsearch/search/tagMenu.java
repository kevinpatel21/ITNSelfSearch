package itn.selfsearch.search;

import itn.selfsearch.database.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is used for the view of our tagMenu GUI
 */
public class tagMenu extends JPanel
{
    // Declare our ArrayList for to store the uniqueTags
    private ArrayList<String> uniqueTags;

    // Declare our two JButtons for our buttonPanel, our JList for our tags, and our tag for the name
    private JButton search;
    private JButton cancel;
    private JList listOfButtons;
    private JLabel tag;
    private JLabel instructionMultiple;

    // Declare our two JPanels for our JButtons and JList
    private JPanel buttonPanel;
    private JPanel listPanel;

    // Declare our scrollPane for listPanel, and our Database that is needed to get the uniqueTags
    private JScrollPane scrollPane;
    private Database d;

    /**
     * This is the constructor for our tagMenu GUI.
     * Calls createPanel to get the View
     * @param data takes in the databse and puts the values into d
     */
    public tagMenu(Database data)
    {
        d = data;
        createPanel();
    }

    /**
     * This function creates the view that you see when you go into the tag menu
     */
    public void createPanel()
    {

        // Declare our different panels needed. We need a total of 3 buttonPanel, listPanel, and the panel that is being extended
        buttonPanel = new JPanel();
        listPanel = new JPanel();

        // Set the layout for our different panels
        BoxLayout boxlayout = new BoxLayout(listPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        this.setLayout(new BorderLayout());

        // Declare our Buttons, JList and our unique tags array list to store the tags into
        uniqueTags = new ArrayList();
        uniqueTags = d.getStoreTags();
        listOfButtons = new JList(uniqueTags.toArray());
        tag = new JLabel("Tag Menu");
        instructionMultiple = new JLabel("For multiple selection, CTRL left click items");
        instructionMultiple.setFont(new Font("Arial", Font.PLAIN, 12));
        search = new JButton("Search");
        cancel = new JButton("Cancel");

        // Set up the listOfButtons JList to multiple_interval selection and set the font
        listOfButtons.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listOfButtons.setFont(new Font("Serif", Font.ITALIC, 14));
        listOfButtons.setLayoutOrientation(JList.VERTICAL);

        // Declare the scroll Pane and set the viewport to the JList. Also set the preferred size
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(listOfButtons);
        scrollPane.setPreferredSize(new Dimension(570,270));

        // Now that the scrollPane is set up add it to the listPanel
        listPanel.add(instructionMultiple);
        listPanel.add(scrollPane);

        // Set the buttonPanel settings so that the buttons appear in the bottom right
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(search);
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
        buttonPanel.add(cancel);

        // Now that the panels are setup add them to the extended JPanel and determine where on the panel they will go
        this.add(listPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);

        // Set the background color
        this.setBackground(Color.LIGHT_GRAY);

    }

    /**
     * This function gets us our private JList variable
     * @return listOfButtons
     */
    public JList getListOfButtons()
    {
        return listOfButtons;
    }

    /**
     * This function gets us our private button search
     * @return search
     */
    public JButton getSearch()
    {
        return search;
    }

    /**
     * This function gets us our private button cancel
     * @return cancel
     */
    public JButton getCancel()
    {
        return cancel;
    }

    /**
     * This function gets us our private ArrayList uniqueTags
     * @return uniqueTags
     */
    public ArrayList<String> getUniqueTags()
    {
        return uniqueTags;
    }

}