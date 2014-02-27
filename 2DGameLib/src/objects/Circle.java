package objects;

import util.Point;
import util.Vector2D;

public class Circle extends GameObject {
	private Point center;
	private double radius;
	private Vector2D velocity;
	private boolean moveable;
	
	/*
	 * CONSTRUCTOR
	 */
	
	public Circle(boolean moveable) {
		center = new Point(0, 0);
		radius = 0;
		velocity = new Vector2D(0, 0);
		this.moveable = moveable;
	}
	
	public Circle(Point center, double radius) {
		center = new Point(center);
		this.radius = radius;
		velocity = new Vector2D(0, 0);
		this.moveable = false;
	}
	
	public Circle(Point center, double radius, Vector2D velocity) {
		center = new Point(center);
		this.radius = radius;
		velocity = new Vector2D(velocity);
		this.moveable = true;
	}
	
	/*
	 * GETTER
	 */
	
	public double getX() {
		return center.getX();
	}
	
	public double getY() {
		return center.getY();
	}
	
	public Point getCenter() {
		return center;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getVelocityX() {
		return velocity.getX();
	}
	
	public double getVelocityY() {
		return velocity.getY();
	}
	
	public Vector2D getVelocity() {
		return velocity;
	}
	
	public boolean isMoveable() {
		return moveable;
	}
	
	/*
	 * SETTER
	 */

	
	/*
	 * OTHER METHODS
	 */
	

}
