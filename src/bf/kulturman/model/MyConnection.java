package bf.kulturman.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection
{
	private static Connection connection;
	private MyConnection(String db , String usr , String pass)
	{
		try 
		{
			connection = DriverManager.getConnection(db, usr, pass);
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(String db , String usr , String pass)
	{
		if(connection == null)
			new MyConnection(db , usr , pass);
		return connection;
	}
}
