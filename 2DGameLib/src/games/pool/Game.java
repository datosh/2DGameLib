package games.pool;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import collision.ColliderBox;
import collision.ColliderCircle;
import objects.Box;
import objects.Circle;
import util.Point;
import util.Vector2D;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;	//UniqueID. Needed for compatibility with older version (Not interesting for us) 

	private final boolean DEBUG = false;
	
	public static final int WIDTH = 160;				//Width of the game's window (in pixel)
	public static final int HEIGHT = WIDTH / 4 * 3;		//Calculate the height of the game's window using the width (current format 4:3)
	public static final int SCALE = 3;					//Scales the game for a better view on big monitors. 
	public static final String NAME = "POOL";			//Specifies the name of the window, generally the game's name. 
	
	//Don't really need to look into this
	private JFrame frame;								//Frame in which the game is running. 
	public boolean running = false;						//Used to stop the game, i.e. when the player pauses the game or when the game is over. 
	public int tickCount = 0;							//Used to show the performance of the game in the console. 

	public InputHandler input;

	/* GAME SPECIFIC ATTRIBUTES _______________________________________________________________ */
	private Circle circle;
	private Box box;
	
	public Game() {
		//Set the minimum, maximum and preferred size of the window to size we specified => User cannot change the size
		//of the window. Therefore we don't need to worry about dynamic windows sizes. 
		this.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));		
		this.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame(NAME);									//Creates the Frame. Need as the argument the name of the window/game. 

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//Specifies what happens when the window close button is pressed. 
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public void init() {
		input = new InputHandler(this);
		
		circle = new Circle(new Point(80, 50), 15.0, new Vector2D(0.0, 0.0), 5.0);
		circle.addCollider(new ColliderCircle(circle.getCenter(), circle.getRadius(), circle));

		box = new Box(20, 20, 200, 300);
		box.addCollider(new ColliderBox(20, 20, 200, 300));
		
		
	}

	public void start() {
		running = true;
		new Thread(this).start();
	}
	
	public void goOn() {
		running = true;
	}

	public void stop() {
		running = false;
	}

	public void run() {
		long lastTime = System.nanoTime();
		// NanoSeconds of one Frame in 60 FPS
		double nsPerTick = 1_000_000_000D / 60D;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		init();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			boolean shouldRender = true;

			while (delta >= 1) {
				ticks++;
				tick();
				delta--;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void tick() {
		tickCount++;

		/* INPUT HANDLING _________________________________________________________________ */
		if(input.right.isPressed()) {
			circle.addForce(new Vector2D(50, 0));
		}
		if(input.left.isPressed()) {
			circle.addForce(new Vector2D(-50, 0));
		}
		if(input.up.isPressed()) {
			circle.addForce(new Vector2D(0, -50));
		}
		if(input.down.isPressed()) {
			circle.addForce(new Vector2D(0, 50));
		}
		
		
		/* LOGICAL UPDATES _________________________________________________________________ */
		circle.move();
		//System.out.println("Position of Cirlce - X: " + circle.getX() + ", Y: " + circle.getY());
		//System.out.println("Velocity of Cirlce - X: " + circle.getVelocityX() + ", Y: " + circle.getVelocityY());
		
		if(((ColliderCircle)circle.getCollider()).inside((ColliderBox)box.getCollider())) {
			System.out.println("In the Field");
			
		} else {
			//circle.setVelocity(new Vector2D(circle.getVelocityX(), -circle.getVelocityY()));
		}
		
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		//Set Background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		/* DRAW HERE ________________________________________________________________ */
		
		circle.fill(g, Color.BLUE);
		box.draw(g, Color.BLUE);
		
		circle.getCollider().draw(g);
		box.getCollider().draw(g);
		
		/* TIL HERE _________________________________________________________________ */
		
		g.dispose();
		bs.show();
	}
	
	/* LOGICAL UPDATE METHODS ____________________________________ */

	
	

	/* DRAW METHODS _____________________________________________ */
	
	
	
	public static void main(String[] args) {
		new Game().start();
	}
}
