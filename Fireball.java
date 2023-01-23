//---------------------------------------------------
// Purpose: Fireball class has a constructor that sets 
//			fireball values. It also has an update method that
//			creates gravity.
// Author: 	Andrew Murphy 10/31/2022
//---------------------------------------------------
import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class Fireball extends Sprite{

	static BufferedImage image;
	int speed = 10;
	int direction = 1;
	
	// Constructor sets values to variables
	public Fireball(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.w = 47;
		this.h = 47;
		if(image == null)
			image = View.loadImage("fireball.png");
	}
	
	public Fireball(Json ob)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = 47;
		h = 47;
		if(image == null)
			image = View.loadImage("fireball.png");
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
	
	void drawYourself(Graphics g, int scroll) 
	{
		g.drawImage(image, x-scroll, y, w, h, null);
	}

	void previousPosition()
	{
		previousX = x;
		previousY = y;
	}
	
	void update() 
	{
		x+= speed * direction; // Update position and send fireball to right
		vert_velocity += 9.8; // This is gravity
		y += vert_velocity; // Update position
		// If fireball position is above the ground set velocity to 0 to
		// bring it back to the ground.
		if(y > 444-h)
		{
			y = 444-h;
			vert_velocity = -60;
			
		}
		
		if(vert_velocity == 0)
		{
			numFramesInAir = 0;
		}
		else
		{
			numFramesInAir++;
		}
	}
	
	@Override 
	public String toString() 
	{
		return "Fireball (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
	} 
	
	boolean isPipe() 
	{
		return false;
	}

	boolean isGoomba() 
	{
		return false;
	}
	
	boolean isFireball()
	{
		return true;
	}	
}
