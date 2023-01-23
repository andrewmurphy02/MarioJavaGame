//---------------------------------------------------
// Purpose: Mario class has a constructor that sets 
//			marios values. It also has an update method that
//			creates gravity and a method that changes his
//			image as he is moving around.
// Author: 	Andrew Murphy 10/31/2022
//---------------------------------------------------
import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class Mario extends Sprite
{
	int currentImage;
	BufferedImage [] mario_images;
	
	public Mario(int x, int y)
	{
		this.x = x;
		this.y = y;
		currentImage = 0;
		// Creates an array of mario images
		mario_images = new BufferedImage[5];
		// Loops through the array and loads each image
		for(int i = 0; i < mario_images.length; i++)
		{
			mario_images[i] = View.loadImage("mario" + (i+1) + ".png");
		}
		// Sets the height and width of the mario images
		this.h = mario_images[0].getHeight();
		this.w = mario_images[0].getWidth();
	}
	
	void previousPosition()
	{
		previousX = x;
		previousY = y;
	}
	
	void update() 
	{
		vert_velocity += 9.8; // This is gravity
		y += vert_velocity; // Update position
		
		// If his position is above the ground set his velocity to 0 to
		// bring him back to the ground.
		if(y > 444-h)
		{
			vert_velocity = 0;
			y = 444-h;
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
	
	void changeImageState()
	{
		// If marios image is greater than 4 reset his image to sprite 0
		currentImage++;
		if(currentImage > 4)
			currentImage = 0;
	}
	
	void drawYourself(Graphics g, int scroll)
	{
		// Draws marios sprite when he is going in the right direction
		if(rightFacing)
			g.drawImage(mario_images[currentImage], x-scroll, y, w, h, null);
		// Draws marios sprite when he is going in the left direction
		else
			g.drawImage(mario_images[currentImage], x-scroll+w, y, -w, h, null);
	}
	
	@Override 
	public String toString() 
	{
		return "Mario (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
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
		return false;
	}
}

