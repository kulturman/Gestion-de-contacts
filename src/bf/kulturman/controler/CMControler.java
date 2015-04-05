package bf.kulturman.controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import bf.kulturman.model.CMModel;
import bf.kulturman.model.Contact;
import bf.kulturman.model.ContactManager;
import bf.kulturman.observer.Observer;

public class CMControler 
{
	CMModel model;
	ContactManager manager;
	Connection db;
	public CMControler(CMModel model)
	{
		try 
		{
			db = DriverManager.getConnection("jdbc:sqlite:db/db.sqlite");
		} 
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		this.model = model;
		manager = new ContactManager(db);
	}
	
	public void addContact()
	{
		
	}
	
	public void deleteContact(int [] rows)
	{
		ArrayList<Contact> contact = model.getContactsList();
		for(int row : rows)
		{
			manager.delete(contact.get(row).getId());
			contact.remove(contact.get(row));
		}
		
		model.notifyObservers(contact , contact.get(0));
	}	
	
	public void viewContact()
	{
	}
}
