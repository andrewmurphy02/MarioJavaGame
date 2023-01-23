//---------------------------------------------------
// Purpose: Pipe class has a constructor that sets the
//			pipe values. It also has a method to determine
//			if a pipe is clicked and a Json method.
// Author: 	Andrew Murphy 09/30/2022
//---------------------------------------------------
import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class Pipe extends Sprite
{
	static BufferedImage image;
	
	// Constructor sets values to variables
	public Pipe(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.w = 55;
		this.h = 400;
		if(image == null)
			image = View.loadImage("pipe.png");
	}
	
	public Pipe(Json ob)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = 55;
		h = 400;
		if(image == null)
			image = View.loadImage("pipe.png");
	}

	Json marshal()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		return ob;
	}
	
	// Method to determine if the user is clicking on an existing pipe
	boolean isPipeClicked(int mouseX, int mouseY)
	{
		if((mouseX > x) && (mouseX < (x + w)) && (mouseY > y) && (mouseY < (y + h)))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	void update()
	{
		
	}
	
	public void drawYourself(Graphics g, int scroll)
	{
		g.drawImage(image, x-scroll, y, w, h, null);
	}
	
	@Override 
	public String toString() 
	{
		return "Pipe (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
	}

	boolean isPipe()
	{
		return true;
	}
	
	boolean isGoomba()
	{
		return false;
	}
	
	boolean isFireball()
	{
		return false;
	}
}
