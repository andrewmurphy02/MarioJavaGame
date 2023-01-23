//---------------------------------------------------
// Purpose: Controller class has methods that allows the user
//			to click on the window and add/remove pipes,
//			use the arrow keys to move left and right on
//			the map, and ability to save, edit, quit, jump,
//			and add goombas and shoot fireballs.
// Author: 	Andrew Murphy 09/30/2022
//---------------------------------------------------
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
class Controller implements ActionListener, MouseListener, KeyListener
{
	Model model;
	View view;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean spaceBar;
	boolean control;
	boolean editMode = false;
	boolean goombaMode = false;
	boolean pipeMode = false;
	
	Controller(Model m)
	{
		model = m;
	}
	
	void setView(View v)
	{
		view = v;
	}
	
	public void actionPerformed(ActionEvent e)
	{
	}
	// Gets the position on window that the user clicked
	public void mousePressed(MouseEvent e)
	{
		// Calls the addPipe method in the model class
		if(pipeMode && editMode == true)
			model.addPipe(e.getX() + view.scrollPos, e.getY());
		else if(goombaMode && editMode == true)
			model.addGoomba(e.getX() + view.scrollPos, e.getY());
	}
	
	public void mouseReleased(MouseEvent e)
	{
	}
	public void mouseEntered(MouseEvent e)
	{
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public void mouseClicked(MouseEvent e)
	{
		if(e.getY() < 100)
		{
			System.out.println("break here");
		}
	}
	
	// Gets which key the user pressed
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: 
				keyRight = true; 
				break;
			case KeyEvent.VK_LEFT: 
				keyLeft = true; 
				break;
			case KeyEvent.VK_UP: 
				keyUp = true; 
				break;
			case KeyEvent.VK_DOWN: 
				keyDown = true; 
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			case KeyEvent.VK_SPACE: 
				spaceBar = true; 
				break;
			case KeyEvent.VK_CONTROL:
				control = true;
				break;
		}
		
		
	}
	// Gets which key the user releases
	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: spaceBar = false; break;
			case KeyEvent.VK_CONTROL: control = false; break;
		}
		char c = Character.toLowerCase(e.getKeyChar());
		if(c == 'q')
			System.exit(0);
		if(c == 's')
		{
			Json saveObject = model.marshal();
			saveObject.save("map.json");
			System.out.println("File is saved.");
		}
		if(c == 'e')
        {
            editMode = !editMode;
            System.out.println("Edit mode: " + editMode); 
        }
		if(c == 'p')
		{
			pipeMode = !pipeMode;
			System.out.println("Pipe mode: " + pipeMode);
		}
		if(c == 'g')
		{
			goombaMode = !goombaMode;
			editMode = false;
		    System.out.println("Goomba mode: " + goombaMode);
		}
	}
	// Will get which key the user types
	public void keyTyped(KeyEvent e)
	{
	}
	
	void update()
	{
		model.mario.previousPosition();
		if(keyRight) 
		{
			model.mario.x += 4; 
			model.mario.changeImageState();
			model.mario.rightFacing = true;
		}
		if(keyLeft)
		{
			model.mario.x -= 4;
			model.mario.changeImageState();
			model.mario.rightFacing = false; 
		}
		if(spaceBar)
		{
			if(model.mario.numFramesInAir < 5)
				model.mario.vert_velocity -= 20;	
		}
		if(control)
		{
			model.addFireball();
			control = false;
		}
	}
}
