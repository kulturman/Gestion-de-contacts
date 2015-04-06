package bf.kulturman.view;

import java.awt.*;

import javax.swing.*;

import java.io.*;

import javax.imageio.*;

@SuppressWarnings("serial")
public class MyPanel extends JPanel
{
	private String background;
    int w , h; 
    public MyPanel(int w , int h , String b) 
    {
    	 background = b;
         this.w = w;
         this.h = h;
    }
    
    public MyPanel(int w , int h) 
    {
         this.w = w;
         this.h = h;
         background = "images/cont.png";
    }
	
	public void setBackground(String b)
	{
		background = b;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		try
		{
			Image i = ImageIO.read(new File(background));
			g.drawImage(i , 0 , 0 , w , h , this);
		}
		
		catch(IOException e)
		{
			System.out.println("exception : " + e.getMessage());
		}
	}

}