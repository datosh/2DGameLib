package objects;

import java.awt.Color;
import java.awt.Graphics;

import collision.Collider;
import util.Point;
import util.Vector2D;

public class Circle extends GameObject {
	private final double FORCEX = 0, FORCEY = 0;
	
	private Vector2D force;
	private Point center;
	private double radius;
	private Vector2D velocity;
	private double mass;
	private long lastMoved;
	
	/*
	 * CONSTRUCTOR
	 */
	public Circle(boolean moveable) {
		this.lastMoved = 0; 
		this.force = new Vector2D(FORCEX, FORCEY);
		this.center = new Point(0, 0);
		this.radius = 0;
		this.velocity = new Vector2D(0, 0);
		this.moveable = moveable;
		this.mass = 0.1f;
	}
	
	public Circle(Point center, double radius) {
		this.lastMoved = 0;
		this.force = new Vector2D(FORCEX, FORCEY);
		this.center = new Point(center);
		this.radius = radius;
		this.velocity = new Vector2D(0, 0);
		this.moveable = false;
		this.mass = 0.1f;
	}
	
	public Circle(Point center, double radius, Vector2D velocity) {
		this.lastMoved = 0;
		this.force = new Vector2D(FORCEX, FORCEY);
		this.center = new Point(center);
		this.radius = radius;
		this.velocity = new Vector2D(velocity);
		this.moveable = true;
		this.mass = 0.1f;
	}
	
	public Circle(Point center, double radius, Vector2D velocity, double mass) {
		this.lastMoved = 0;
		this.force = new Vector2D(FORCEX, FORCEY);
		this.center = new Point(center);
		this.radius = radius;
		this.velocity = new Vector2D(velocity);
		this.moveable = true;
		this.mass = mass;
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
	
	@Override
	public Vector2D getVelocity() {
		return velocity;
	}
	
	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}
	
	@Override
	public Collider getCollider() {
		if(this.collider == null) { //TODO:REPLACE WITH NO COLLIDER ATTACHED EXCEPTION? 
			return null;
		} else {
			return this.collider;
		}
		
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
	
	public void move() {
		//Get time since last movement, if zero set time to now and return. 
		if(lastMoved == 0) {
			lastMoved = System.nanoTime();
			return;
		}
		double dt = System.nanoTime() - lastMoved;
		dt /= 1_000_000_000;
		lastMoved = System.nanoTime();
		
		//Update the position of the object. 
		center.setX(center.getX() + (velocity.getX() * dt));
		center.setY(center.getY() + (velocity.getY() * dt));
		
		//Update the velocity
		velocity = velocity.add(new Vector2D((force.getX() / mass) * dt, (force.getY() / mass) * dt));
		this.force = new Vector2D(FORCEX, FORCEY);
		
		//Update of the colliderbox
		if(this.collider != null) {
			this.collider.setPosition(center);
		}
	}
	
	public void addForce(Vector2D force) {
		this.force = this.force.add(force);
	}

	@Override
	public void addCollider(Collider collider) {
		this.collider = collider;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(drawColor);
		g.drawOval((int)(center.getX() - radius), (int)(center.getY() - radius), (int)(radius * 2), (int)(radius * 2));
		
	}

	@Override
	public void draw(Graphics g, Color color) {
		this.drawColor = color;
		g.setColor(drawColor);
		g.drawOval((int)(center.getX() - radius), (int)(center.getY() - radius), (int)(radius * 2), (int)(radius * 2));
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(drawColor);
		g.fillOval((int)(center.getX() - radius), (int)(center.getY() - radius), (int)(radius * 2), (int)(radius * 2));
		
	}

	@Override
	public void fill(Graphics g, Color color) {
		this.drawColor = color;
		g.setColor(drawColor);
		g.fillOval((int)(center.getX() - radius), (int)(center.getY() - radius), (int)(radius * 2), (int)(radius * 2));
	}

	@Override
	public void setColor(Color color) {
		this.drawColor = color;		
	}

	

}
