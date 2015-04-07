package bf.kulturman.model;

import java.util.ArrayList;
import bf.kulturman.observer.Observable;
import bf.kulturman.observer.Observer;

public class CMModel implements Observable
{
	private ArrayList<Observer> observers;
	private ArrayList<Contact> contactsList;
	/*le contact ayant le focus*/
	private Contact focus;
	
	public CMModel()
	{
		observers = new ArrayList<Observer>();
		ContactManager manager = ContactManager.getContactManager();
		contactsList = manager.getAll();
		if(contactsList.size() > 0)
			focus = contactsList.get(0);
	}
	
	public ArrayList<Contact> getContactsList()
	{
		return contactsList;
	}
	
	public Contact getFocus()
	{
		return focus;
	}

	public void setFocus(Contact focus)
	{
		this.focus = focus;
		notifyObservers(contactsList, focus);
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
