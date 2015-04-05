package bf.kulturman.view;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.*;

@SuppressWarnings("serial")
public class MyDialog extends JDialog implements ActionListener
{
	String[] forDay , forMonth = {"janvier" , "février" , "mars" , "avril" , "mai" , "juin" , 
	"juillet", "août" , "septembre" , "octobre" , "novembre" , "décembre"};;
	private JPanel pan;
	private JPanel pan1;
	private JPanel pan2;
	private JComboBox<String> day;
	private JComboBox<String> month;
	private JTextField path;
	private JButton buttonImg;
	private JButton buttonSave;
	private JButton buttonCancel;
	private JLabel labelName;
	private JLabel labelFirstname;
	private JLabel labelMail;
	private JLabel labelBirth;
	private JLabel labelNumber;
	private JTextField name;
	private JTextField firstname;
	private JTextField mail;
	private JTextField number;
	
	public MyDialog(Frame p , String title , boolean modal , JFrame o , MainWindow w)
	{
		super(p , title , modal);
		setLocationRelativeTo(o);
		init();
		buttonImg.addActionListener(this);
		buttonCancel.addActionListener(this);
		buttonSave.addActionListener(this);
	}
	private void init()
	{
		setSize(400 , 250);
		path = new JTextField("images/cont.png");
		path.setEditable(false);
		pan1 = new JPanel(new GridLayout(1 , 2 , 9 , 9));
		buttonImg = new JButton("Choisir une photo de profil");
		pan1.setPreferredSize(new Dimension(10 , 200));
		pan2 = new JPanel(new GridLayout(1 , 2 , 5 , 5));
		pan = new JPanel(new GridLayout(6,6,10,10));
        this.add(pan);
		//this.add(pan1);
		this.add(pan2 , BorderLayout.SOUTH);
		forDay = new String[31];
		for(int i = 0 ; i < forDay.length ; i++)
			forDay[i] = new String(String.valueOf(i + 1));
		
		month = new JComboBox<String>(forMonth);
		day = new JComboBox<String>(forDay);
		name = new JTextField(10);
		firstname = new JTextField(10);
		mail =  new JTextField(10);
		number = new JTextField(10);
		buttonSave = new JButton(" Enregistrer " , new ImageIcon("images/save.png"));
		buttonCancel = new JButton(" Annuler " , new ImageIcon("images/cancel.png"));
		labelName = new JLabel(" Nom :");
		labelFirstname = new JLabel("Prénom :");
		labelMail = new JLabel("E-mail :");
		labelNumber = new JLabel("Numéro :");
		labelBirth = new JLabel("Anniversaire :");
		pan.add(labelName);
		pan.add(name);
		pan.add(labelFirstname);
		pan.add(firstname);
		pan.add(labelNumber);
		pan.add(number);
		pan.add(labelMail);
		pan.add(mail);
		pan.add(labelBirth);
		pan.add(pan1);
		pan.add(path);
		pan.add(buttonImg);
		pan1.add(day);
		pan1.add(month);
		pan2.add(buttonSave);
		pan2.add(buttonCancel);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == buttonImg)
		{
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG , GIF & PNG Images", "jpg", "gif" , "png");
			JFileChooser f = new JFileChooser();
			f.setFileFilter(filter);
			if(f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				path.setText(f.getSelectedFile().getPath());
			}
		
		}
		
		if(e.getSource() == buttonCancel)
		{
			setVisible(false);
		}
		
		if(e.getSource() == buttonSave)
		{
            try 
            {
                PrintWriter file = new PrintWriter(new FileWriter("contacts.txt" , true));  
                file.println(" " + name.getText() + ";" + " " + firstname.getText() + ";" + " " + mail.getText() + ";" + path.getText()
                            + ";" + " " + (String)day.getSelectedItem() + " " 
                            + (String)month.getSelectedItem() + ";" + " " + number.getText());
                file.close();
                this.setVisible(false);
                JOptionPane.showMessageDialog(null , "Contact ajouté avec succès" , "Information" , JOptionPane.INFORMATION_MESSAGE);      
			} 
                     
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(null , "Impossible d'ajouter un contact" , "Erreur" , JOptionPane.ERROR_MESSAGE);
            }
                    
		}
	
	}

}
