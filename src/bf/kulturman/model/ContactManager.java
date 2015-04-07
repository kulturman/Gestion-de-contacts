package bf.kulturman.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ContactManager
{
	private static ContactManager manager;
	private Connection db;
	private PreparedStatement st;
	private ResultSet res;

	private ContactManager()
	{
		this.db = MyConnection.getConnection("jdbc:sqlite:db/db.sqlite", "" , "");
	}
	
	public void create(Contact c)
	{
		try 
		{
			st = db.prepareStatement("INSERT INTO contact VALUES(NULL , ? , ? , ? , ? , ? , ?)");
			st.setString(1, c.getName());
			st.setString(2, c.getFirstname());
			st.setString(3, c.getMail());
			st.setString(4, c.getImage());
			st.setString(5, c.getBirth());
			st.setString(6, c.getNumber());
			st.executeUpdate();
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "finally", "finally" })
	public Contact get(int id)
	{
		Contact c = null;
		try
		{
			st = db.prepareStatement("SELECT * FROM contact WHERE id = ?");
			st.setInt(1, id);
			res = st.executeQuery();
			res.next();
			c = new Contact(res.getString("name"), res.getString("firstname"), 
							res.getString("mail"), res.getString("mage"), res.getString("birth") 
							, res.getString("number"));
			c.setId(res.getInt("id"));
			return c;
		} 
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			return c;
		}
	}
	
	public ArrayList<Contact> getAll()
	{
		ArrayList<Contact> contacts = null;
		Contact c;
		try
		{
			st = db.prepareStatement("SELECT * FROM contact ORDER BY name , firstname");
			res = st.executeQuery();
			contacts = new ArrayList<Contact>();
			while(res.next())
			{
				c = new Contact(res.getString("name"), res.getString("firstname"), 
						res.getString("mail"), res.getString("image"), res.getString("birth") 
						, res.getString("number"));
				c.setId(res.getInt("id"));
				contacts.add(c);
			}

		}
		
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			return contacts;
		}
	}
	
	public void getSortedByBirthDate(ArrayList<Contact> contacts)
	{
		try 
		{
			st = db.prepareStatement("SELECT * FROM contact ORDER BY birth");
			res = st.executeQuery();
			Contact current;
			int i = 0;
			while(res.next())
			{
				current = contacts.get(i);
				current.setBirth(res.getString("birth"));
				current.setName(res.getString("name"));
				current.setFirstname(res.getString("firstname"));
				current.setMail(res.getString("mail"));
				current.setId(res.getInt("id"));
				current.setImage(res.getString("image"));
				current.setNumber(res.getString("number"));
				i++;
			}
		} 
		
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null , e.getMessage());
		}
	}
	
	public void getSortedById(ArrayList<Contact> contacts)
	{
		try 
		{
			st = db.prepareStatement("SELECT * FROM contact");
			res = st.executeQuery();
			Contact current;
			int i = 0;
			while(res.next())
			{
				current = contacts.get(i);
				current.setBirth(res.getString("birth"));
				current.setName(res.getString("name"));
				current.setFirstname(res.getString("firstname"));
				current.setMail(res.getString("mail"));
				current.setId(res.getInt("id"));
				current.setImage(res.getString("image"));
				current.setNumber(res.getString("number"));
				i++;
			}
		} 
		
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null , e.getMessage());
		}
	}
	
	public void getSortedByName(ArrayList<Contact> contacts)
	{
		try 
		{
			st = db.prepareStatement("SELECT * FROM contact ORDER BY name , firstname");
			res = st.executeQuery();
			Contact current;
			int i = 0;
			while(res.next())
			{
				current = contacts.get(i);
				current.setBirth(res.getString("birth"));
				current.setName(res.getString("name"));
				current.setFirstname(res.getString("firstname"));
				current.setMail(res.getString("mail"));
				current.setId(res.getInt("id"));
				current.setImage(res.getString("image"));
				current.setNumber(res.getString("number"));
				i++;
			}
		} 
		
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null , e.getMessage());
		}
	}
	
	public void delete(int id)
	{
		try 
		{
			st = db.prepareStatement("DELETE FROM contact WHERE id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} 
		
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null , e.getMessage());
		}
		
	}
	
	public static ContactManager getContactManager()
	{
		if(manager == null)
			manager = new ContactManager();
		return manager;
	}
}