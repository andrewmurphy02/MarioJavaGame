//---------------------------------------------------
// Purpose: Model class creates an ArrayList of Sprite objects
//			and has a method to add and remove pipes and add
//			goombas and fireballs. It has a method to check if 
//			sprites are colliding with each other.
// Author: 	Andrew Murphy 10/31/2022
//---------------------------------------------------
import java.util.ArrayList;
class Model
{
	ArrayList<Sprite> sprites;
	Mario mario;
	
	Model()
	{
		// Initializes an array of pipe objects
		sprites = new ArrayList<Sprite>();
		// Creates the mario character
		mario = new Mario(100,100);
		sprites.add(mario);
	}
	
	void unmarshal(Json ob)
	{
		sprites = new ArrayList<Sprite>();
		sprites.add(mario);
		Json tmpListPipes = ob.get("pipes");
		Json tmpListGoombas = ob.get("goombas");
		for(int i = 0; i < tmpListPipes.size(); i++)
			sprites.add(new Pipe(tmpListPipes.get(i)));
		for(int i = 0; i < tmpListGoombas.size(); i++)
			sprites.add(new Goomba(tmpListGoombas.get(i)));
	}
	
	Json marshal()
	{
		Json ob = Json.newObject();
		Json tmpListPipes = Json.newList();
		ob.add("pipes", tmpListPipes);
		Json tmpListGoombas = Json.newList();
		ob.add("goombas", tmpListGoombas);
		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isPipe())
				tmpListPipes.add(((Pipe)sprites.get(i)).marshal());
			if(sprites.get(i).isGoomba())
				tmpListGoombas.add(((Goomba)sprites.get(i)).marshal());
		}
		return ob;
	}
		
	// This method adds a pipe
	public void addPipe(int x, int y)
	{
		boolean foundPipe = false;
		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isPipe())
			{
				if(((Pipe)sprites.get(i)).isPipeClicked(x, y) == true) // Means there is pipe where i clicked
				{
					foundPipe = true;
					sprites.remove(i); // Removes pipe
				}
			}	
		}
		if(!foundPipe)
			sprites.add(new Pipe(x, y)); // Adds a new pipe
	}
	
	public void addGoomba(int x, int y)
	{
		sprites.add(new Goomba(x, y));
	}
	
	public void addFireball()
	{
		sprites.add(new Fireball(mario.x, mario.y));
	}
	
	public void update()
	{
		// Goomba and Pipe
		for(int i = 0; i < sprites.size(); i++)
		{
			sprites.get(i).update(); // Update the sprites
			
			if(sprites.get(i).isGoomba()) // Goomba Collision
			{
				for(int j = 0; j < sprites.size(); j++)
				{
					if(sprites.get(j).isPipe()) 
					{
						if(isCollidingCheck(sprites.get(i), sprites.get(j))) // Goomba Pipe Collsion
						{
			                spriteCollision(sprites.get(i), sprites.get(j));
			            }
					}
				}
			}
			
			// Fireball and Goomba
			if(sprites.get(i).isFireball())
			{
				for(int j = 0; j < sprites.size(); j++)
				{
					if(sprites.get(j).isGoomba()) 
					{
						if(isCollidingCheck(sprites.get(i), sprites.get(j))) // Goomba Fireball Collision
						{
			                // load goomba fire image
			                ((Goomba)sprites.get(j)).getImage();
			                // Set Burn To True
			                ((Goomba)sprites.get(j)).burn = true;
						}
			            if(((Goomba)sprites.get(j)).numFrames > 50)
			        	{
			            	((Goomba)sprites.get(j)).numFrames = 0;
			               	sprites.remove(j); // Remove Goomba
			        	}		    
					}	
				}
			}
			
			// Mario and Pipe
			for(int j = 0; j < sprites.size(); j++)
			{
		        if(sprites.get(j).isPipe()) 
		        {
		            if(isCollidingCheck(mario, sprites.get(j))) 
		            {
		                spriteCollision(mario, sprites.get(j));
		            }
		        }
		    }
		}
	}
	
	// Method to check if a sprite is colliding with another sprite
	boolean isCollidingCheck(Sprite a, Sprite b)
	{	
		if(a.x+a.w < b.x) // Sprite right side collides with left side of another sprite
			return false;
		if(a.x > b.x + b.w) // Sprite left side collides with right side of another sprite
			return false;
		if(a.y+a.h < b.y) // Sprite bottom right collides with top left of another sprite
			return false;
		if(a.y > b.y + b.h) // Sprite bottom left collides with top right of another sprite
			return false;
		return true;
	}
	
	void spriteCollision(Sprite a, Sprite b)
	{
		if(a.y+a.h >= b.y && a.previousY + a.h <= b.y) // Top
		{
			a.y = b.y - a.h;
			if(a == mario)
			{
				mario.vert_velocity = 0;
				mario.numFramesInAir = 0;
				return;
			}
		}

		if(a.y <= b.y + b.h && a.previousY >= b.y+b.h) // Toes
		{
			a.y = b.y+b.h;
			return;
		}
	
		if(a.previousX + a.w <= b.x + b.w && a.x + a.w >= b.x) // Left
		{
			a.x = b.x - a.w;
			
			if (a.isGoomba())
			{
				((Goomba)a).direction *= -1; // Change direction
			}
		}
		
		if(a.previousX >= b.x+b.w && a.x <= b.x + b.w) // Right
		{
			a.x = b.x+b.w;
			if(a.isGoomba())
			{
				((Goomba)a).direction *= -1; // Change direction
			}
		}
	}
}

