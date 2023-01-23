//---------------------------------------------------
// Purpose: Goomba class has a constructor that sets 
//			goomba values. It also has an update method that
//			creates gravity and a method to change the
//			goombas image around as it is moving around.
// Author: 	Andrew Murphy 10/31/2022
//---------------------------------------------------
import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class Goomba extends Sprite
{
	//int currentImage;
	static BufferedImage image;
	static BufferedImage image2;
	int speed = 4;
	int direction = 1;
	int numFrames = 0;
	boolean burn = false;
	
	// Constructor sets values to variables
	public Goomba(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.h = 37;
		this.w = 45;
		if(image == null)
			image = View.loadImage("goomba1.png");	
	}
	
	public Goomba(Json ob)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		this.h = 37;
		this.w = 45;
		if(image == null)
			image = View.loadImage("goomba1.png");
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
	
	void previousPosition()
	{
		previousX = x;
		previousY = y;
	}
	
	void update()
	{
		previousPosition();
		vert_velocity += 9.8; // This is gravity
		y+= vert_velocity; // Update position
		x+= speed * direction; // Update position and send goomba to right
		// If goomba position is above the ground set velocity to 0 to
		// bring goomba back to the ground.
		if(burn == true)
			numFrames++;
		if(y > 444-h)
		{
			vert_velocity = 0;
			y = 444-h;
		}
	}
	
	public void drawYourself(Graphics g, int scroll)
	{
		// Draws goomba sprite when he is not on fire
		if(rightFacing)
			g.drawImage(image, x-scroll, y, w, h, null);
		// Draws goomba sprite when he is on fire
		else
			g.drawImage(image2, x-scroll+w, y, -w, h, null);
	}
	
	void getImage()
	{
		if(image2 == null)
		{
			image2 = View.loadImage("goomba2.png");
		}
			numFrames++;
			rightFacing = false;
			speed = 0;
	}

	@Override 
	public String toString() 
	{
		return "Goomba (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
	}

	boolean isGoomba()
	{
		return true;
	}
	
	boolean isPipe()
	{
		return false;
	}
	
	boolean isFireball()
	{
		return false;
	}
}
