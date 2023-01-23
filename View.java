//---------------------------------------------------
// Purpose: View class reads in the pipe and ground images
//			and has a method that sets a background color as well
//			as draws pipes and ground to the screen.
// Author: 	Andrew Murphy 10/31/2022
//---------------------------------------------------
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
class View extends JPanel
{
	BufferedImage ground_image;
	Model model;
	int scrollPos = 0;
	View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		ground_image = loadImage("ground.png");
		
	}
	
	// This function loads an image. The static makes it accessible
	// in other locations outside of the class
	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
		    im = ImageIO.read(new File(filename));
		}
		catch(Exception e) 
		{
		    e.printStackTrace(System.err);
		    System.exit(1);
		}
		System.out.println("Successfully loaded " + filename + " image.");
		return im;
	}
	
	Json marshal()
	{
		Json ob = Json.newObject();
		ob.add("scroll", scrollPos);
		return ob;
	}
	
	// The paintComponent method adds background color and draws pipes
	public void paintComponent(Graphics g)
	{
		scrollPos = model.mario.x - 100;
		g.setColor(new Color(128, 255, 255));
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
	    for(int i = 0; i < model.sprites.size(); i++)
	    	model.sprites.get(i).drawYourself(g, scrollPos);
	    // Draws the ground image
	    g.drawImage(ground_image, 0 - scrollPos, 445, 2000, 30, null);
	}
}
