//---------------------------------------------------
// Purpose: Sprite class initializes variables that
// 			are used in many other classes. It also
//			creates the drawYourself, update, isPipe,
//			isGoomba, isFireball methods.			
// Author: 	Andrew Murphy 10/31/2022
//---------------------------------------------------
import java.awt.Graphics;
public abstract class Sprite 
{
	int x, y, w, h, previousX, previousY;
	boolean rightFacing = true;
	double vert_velocity = 1.2;
	int numFramesInAir = 0;
	
	abstract void drawYourself(Graphics g, int scroll);
	abstract void update();
	
	abstract boolean isPipe();
	abstract boolean isGoomba();
	abstract boolean isFireball();
	
	@Override 
	public String toString() 
	{
		return "Sprite (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
	} 
}
