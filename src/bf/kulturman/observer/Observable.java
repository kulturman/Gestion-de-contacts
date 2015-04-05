package bf.kulturman.observer;

import java.util.ArrayList;

import bf.kulturman.model.Contact;

public interface Observable 
{
	public void addObserver(Observer obs);
	public void deleteObserver(Observer obs);
	public void notifyObservers(ArrayList<Contact> contactsList , Contact focus);
}
