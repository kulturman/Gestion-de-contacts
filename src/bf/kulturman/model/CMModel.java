package bf.kulturman.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import bf.kulturman.observer.Observable;
import bf.kulturman.observer.Observer;

public class CMModel implements Observable
{
	private ArrayList<Observer> observers;
	private ArrayList<Contact> contactsList;
	private Connection db;
	/*le contact ayant le focus*/
	private Contact focus;
	
	public CMModel()
	{
		observers = new ArrayList<Observer>();
		try 
		{
			db = DriverManager.getConnection("jdbc:sqlite:db/db.sqlite");
			ContactManager manager = new ContactManager(db);
			contactsList = manager.getAll();
			if(contactsList.size() > 0)
				focus = contactsList.get(0);
		} 
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Contact> getContactsList()
	{
		return contactsList;
	}
	
	@Override
	public void addObserver(Observer obs) 
	{
		if(observers.indexOf(obs) < 0)
			observers.add(obs);
		notifyObservers(contactsList, focus);
	}

	@Override
	public void deleteObserver(Observer obs)
	{
		observers.remove(obs);
	}

	@Override
	public void notifyObservers(ArrayList<Contact> contactsList, Contact focus) 
	{
		for(Observer o : observers)
			o.update(contactsList , focus);
	}


}
