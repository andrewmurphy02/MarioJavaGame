//---------------------------------------------------
// Purpose: Game class that pulls up a window and runs
// 			the game.
// Author: 	Andrew Murphy 09/30/2022
//---------------------------------------------------
import javax.swing.JFrame;
import java.awt.Toolkit;
public class Game extends JFrame
{
	Model model;
	Controller controller;
	View view;
	
	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		this.setTitle("A5 - Final Mario Game");
		view.addMouseListener(controller);
		this.addKeyListener(controller);
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		// Loads the saved map
		Json j = Json.load("map.json");
		model.unmarshal(j);
		System.out.println("File is loaded.");
	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
	
	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent 
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 40 milliseconds. 1000 milliseconds / 40 milliseconds = 25fps
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
