package games.spaceInvaders.client;

import collision.ColliderBox;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile {
	private final int SCALED_X = 5;
	private final int SCALED_Y = 10;
	
	private boolean alive;
	private int positionX;
	private int positionY;
	private int speed;
	private Image image;
	private ColliderBox collider; 
	
	public Projectile() {
		alive = false;
	}
	
	public Projectile(int positionX, int positionY, int speed){
		this.positionX = positionX - SCALED_X / 2;
		this.positionY = positionY - SCALED_Y / 2;
		this.speed = speed;
		alive = true;
		
		try {
			image = ImageIO.read(new File("images/space_invaders/projectile.png")).getScaledInstance(SCALED_X, SCALED_Y, Image.SCALE_SMOOTH); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		collider = new ColliderBox(this.positionX, this.positionY, SCALED_X, SCALED_Y);
	}
	
	public int getX() {
		return positionX;
	}
	
	public int getY() {
		return positionY;
	}
	
	public ColliderBox getCollider() {
		return collider;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void toggleAlive() {
		alive = !alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void move(int height){
		positionY += speed;
		if(alive) {
			collider.move(0, speed);
		}

		if(positionY > height || positionY < 0) {
			alive = false;
		}
	}
}
