package bf.kulturman.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import bf.kulturman.controler.CMControler;
import bf.kulturman.model.Contact;
import bf.kulturman.observer.Observer;

import java.io.*;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class MainWindow extends JFrame implements Observer
{
	Object[][] data = {new Object[200] , new Object[4]};
	//Les titres des colonnes
	String  title[] = {"Identificateur" , "Nom", "Pr�nom", "Num�ro"};
	DefaultTableModel model = new DefaultTableModel(title , 0);
	JTable tab = new JTable(model);
	private JTextField[] userInfos;
    private MyDialog dial;
    //private String text = "Saisir le nom ou le pr�nom � rechercher";
    private MyPanel userImg;
    private MyPanel appImg;
    private JPanel buttonsPanel;
    private JPanel userInfo;
    private JPanel footer;
    private JPanel contactInfoZone;
    private JTextField SearchZone;
    private JButton view;
    private JButton modifyButton;
    private JButton deleteButton;
    private JPanel Interface;
    private JMenuBar MenuBar;
    private JMenu Contacts;
    private JMenu Actions;
    private JMenu Help;
    private JMenuItem Modify;
    private JMenuItem Delete;
    private JMenuItem SortByBithDate;
    private JMenuItem SortByName;
    private JMenuItem SortByDate;
    private JMenuItem addContact;

    public MainWindow(int h , int w , CMControler ctrl) throws IOException
    {
    	this(ctrl);
    	setSize(h, w);
    }
    public MainWindow(CMControler ctrl) throws IOException
    {
        createMenu();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Gestionnaire de contacts");
		/*SearchZone.setVisible(false);
        SearchZone.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent ev)
                    {
                        SearchZone.setText("");
                    }
                    
                    public void mouseExited(MouseEvent ev)
                    {
                       if(SearchZone.getText().equals(""))
                           SearchZone.setText(text);
                    }
                });*/
    }
    
    private void createMenu()
    {
		/*infos du contacts */
        appImg = new MyPanel(410 , 100 , "images/hh.jpg");
        buttonsPanel = new JPanel();
		userInfo = new JPanel(new GridLayout(5 , 1 , 5 , 5));
		userInfo.setBorder(new TitledBorder("Infos"));
		userInfo.setPreferredSize(new Dimension(100 , 90));
		userInfos = new JTextField[5];
		for(int i = 0 ; i < userInfos.length ; i++)
		{
			userInfos[i] = new JTextField();
			userInfos[i].setEditable(false);
			userInfo.add(userInfos[i]);
		}
		userImg = new MyPanel(100 , 150);
		contactInfoZone = new JPanel(new BorderLayout());
		contactInfoZone.setBorder(new TitledBorder("D�tails"));
		contactInfoZone.add(userImg , BorderLayout.WEST);
		contactInfoZone.add(userInfo);
		userImg.setPreferredSize(new Dimension(100 , 150));
        deleteButton = new JButton("Supprimer" , new ImageIcon("images/delete.png"));
        modifyButton = new JButton("Modifier" ,  new ImageIcon("images/edit.png"));
        view = new JButton("D�tails" ,  new ImageIcon("images/view.png"));
        footer = new JPanel(new BorderLayout());
        //SearchZone = new JTextField(text);
        Interface = new JPanel(new BorderLayout());
        //Interface.add(SearchZone , BorderLayout.SOUTH);
		Interface.add(contactInfoZone);
		//scroll.add(new JTable(data , title));
		//JTable tab = new JTable(data , title);
		add(new JScrollPane(tab));
        add(Interface , BorderLayout.NORTH);
        add(footer , BorderLayout.SOUTH);
        footer.add(buttonsPanel , BorderLayout.NORTH);
        footer.add(appImg , BorderLayout.SOUTH);
        appImg.setBackground(Color.white);
        appImg.setPreferredSize(new Dimension(510 , 100));
        buttonsPanel.add(view);
        buttonsPanel.add(modifyButton);
        buttonsPanel.add(deleteButton);
        /*cr�ation du menu et autres*/
        MenuBar  = new JMenuBar();
        Contacts = new JMenu("Contacts");
        Actions  = new JMenu("Actions");
        Help     =  new JMenu("Aide");
        Modify   = new JMenuItem("Modifier" ,  new ImageIcon("images/edit.png"));
        Delete   = new JMenuItem("Supprimer" , new ImageIcon("images/delete.png")); 
        addContact = new JMenuItem("Nouveau contact" , new ImageIcon("images/add.png"));
        SortByBithDate = new JMenuItem("Trier par date d'anniversaire");
        SortByDate = new JMenuItem("Trier par date d'ajout");
        SortByName = new JMenuItem("Trier par nom et pr�nom");
        Actions.add(addContact);
        Actions.add(Modify);
        Actions.add(Delete);
        Contacts.add(SortByBithDate);
        Contacts.add(SortByDate);
        Contacts.add(SortByName);
        Interface.add(MenuBar , BorderLayout.NORTH);
        MenuBar.add(Contacts);
        MenuBar.add(Actions);
        MenuBar.add(Help);
		Image i = Toolkit.getDefaultToolkit().getImage("images/icon.png");
		setIconImage(i);
		
    }
	@Override
	public void update(ArrayList<Contact> contactsList, Contact focus) 
	{
		if(focus != null)
		{
			userImg.setBackground(focus.getImage());
			userInfos[0].setText("Nom : " + focus.getName());
			userInfos[1].setText("pr�nom : " + focus.getFirstname());
			userInfos[2].setText("Mail : " + focus.getMail());
			userInfos[3].setText("Date d'anniversaire : " + focus.getBirth());
			userInfos[4].setText("Num�ro : " + focus.getNumber());
			
			for(Contact contact : contactsList)
				model.addRow
				(new String[]{String.valueOf(contact.getId()) , contact.getName() , contact.getFirstname()
				, contact.getNumber()});
		}
	}
	
}