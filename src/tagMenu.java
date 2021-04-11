import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class tagMenu extends JPanel
{
    private ArrayList<String> uniqueTags;

    private ArrayList<JToggleButton> arrayButtons;
    private JToggleButton button;
    private JFrame frame2;

    private JPanel buttonPanel;
    private JPanel listPanel;

    private JScrollPane scrollPane;
    private String text;
    private Database d;
    private JLabel tag;
    private JButton search;
    private JButton cancel;
    private JTextArea textArea;


    private JList listOfButtons;

    public tagMenu(Database data)
    {
        d = data;
        System.out.println("In tagMenu");
        setButtons();
        createPanel();
    }

    public void setButtons()
    {
        // Create the objects
        arrayButtons = new  ArrayList();
        uniqueTags = new ArrayList();

        // We need to get the UNIQUE tags from database
        uniqueTags = d.getStoreTags();

        // This loop goes through all of the tags and creates a JToggleButton for them.
        for(int i = 0; i < d.getStoreTagCounter(); i++)
        {
            text = uniqueTags.get(i);
            button = new JToggleButton(text);
            button.setMaximumSize(new Dimension(150,100));
            arrayButtons.add(button);

        }
    }

    public void createPanel()
    {
        // Set up thr frame with a few settings
        frame2 = new JFrame();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(600, 400);
        frame2.setResizable(true);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
        frame2.setTitle("Tag Menu");

        // Declare our different panels needed. We need a total of 3
        buttonPanel = new JPanel();
        listPanel = new JPanel();

        // Set the layout for our different panels
        BoxLayout boxlayout = new BoxLayout(listPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        this.setLayout(new BorderLayout());

        listOfButtons = new JList(uniqueTags.toArray());
        tag = new JLabel("Tag Menu");
        search = new JButton("Search");
        cancel = new JButton("Cancel");

        listOfButtons.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listOfButtons.setFont(new Font("Serif", Font.ITALIC, 14));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(listOfButtons);
        listOfButtons.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setPreferredSize(new Dimension(570,300));


        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(search);
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
        buttonPanel.add(cancel);

        listPanel.add(scrollPane);

        this.add(listPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);

        frame2.add(this);
        this.setBackground(Color.LIGHT_GRAY);

    }

    public JList getListOfButtons()
    {
        return listOfButtons;
    }

    public JButton getSearch()
    {
        return search;
    }

    public ArrayList<String> getUniqueTags()
    {
        return uniqueTags;
    }


}