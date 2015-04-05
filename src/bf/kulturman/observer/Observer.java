package bf.kulturman.observer;

import java.util.ArrayList;

import bf.kulturman.model.Contact;

public interface Observer 
{
	public void update(ArrayList<Contact> contactsList , Contact focus);
}
