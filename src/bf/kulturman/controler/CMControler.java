package bf.kulturman.controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import bf.kulturman.model.CMModel;
import bf.kulturman.model.Contact;
import bf.kulturman.model.ContactManager;

public class CMControler 
{
	CMModel model;
	ContactManager manager;
	Connection db;
	public CMControler(CMModel model)
	{
		this.model = model;
		try 
		{
			db = DriverManager.getConnection("jdbc:sqlite:db/db.sqlite");
			manager = new ContactManager(db);
		} 
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addContact()
	{
		
	}
	
	/* rows: représente les lignes sélectionnées*/
	public void deleteContact(int [] rows)
	{
		ArrayList<Contact> contact = model.getContactsList();
		for(int row : rows)
		{
			manager.delete(contact.get(row).getId());
			contact.remove(contact.get(row));
		}
		if(contact.size() > 0)
			model.notifyObservers(contact , contact.get(0));
		else
			model.notifyObservers(contact , null);
	}	
	
	/* comme on ne peut pas voir les détails de plusieurs contacts en meme temps, on considère une seule
	 * et on s'en fout du reste
	 */
	public void viewContact(int row)
	{
		if(row != - 1)
			model.setFocus(model.getContactsList().get(row));
	}
}
