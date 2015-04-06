package bf.kulturman.controler;

import java.sql.Connection;
import java.util.ArrayList;

import bf.kulturman.model.CMModel;
import bf.kulturman.model.Contact;
import bf.kulturman.model.ContactManager;
import bf.kulturman.model.MyConnection;

public class CMControler 
{
	CMModel model;
	ContactManager manager;
	Connection db;
	public CMControler(CMModel model)
	{
		this.model = model;
		manager = ContactManager.getContactManager();
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
	
	/* comme on ne peut pas voir les détails de plusieurs contacts en meme temps, on considère une seule(ligne)
	 * et on s'en fout du reste
	 */
	public void viewContact(int row)
	{
		if(row != - 1)
			model.setFocus(model.getContactsList().get(row));
	}
	
	public void orderByBirthDate()
	{
		ArrayList<Contact> contact = model.getContactsList();
		manager.getSortedByBirthDate(model.getContactsList());
		if(contact.size() > 0)
			model.notifyObservers(contact , contact.get(0));
		else
			model.notifyObservers(contact , null);
	}
	
	public void orderByAddDate()
	{
		ArrayList<Contact> contacts = model.getContactsList();
		manager.getSortedById(contacts);
		if(contacts.size() > 0)
			model.notifyObservers(contacts , contacts.get(0));
		else
			model.notifyObservers(contacts , null);
	}
	
	public void orderByName()
	{
		ArrayList<Contact> contacts = model.getContactsList();
		manager.getSortedByName(contacts);;
		if(contacts.size() > 0)
			model.notifyObservers(contacts , contacts.get(0));
		else
			model.notifyObservers(contacts , null);
	}
	
}
