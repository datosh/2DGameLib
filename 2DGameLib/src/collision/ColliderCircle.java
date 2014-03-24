package collision;

import java.awt.Color;
import java.awt.Graphics;

import objects.GameObject;
import util.Point;

public class ColliderCircle extends Collider{
	private Point center;
	private double radius;
	
	/*
	 * CONSTRUCTOR
	 */
	
	public ColliderCircle(GameObject attachedTo) {
		this.center = new Point(0, 0);
		this.radius = 0;
		this.attachedTo = attachedTo;
	}
	
	public ColliderCircle(Point center, double radius, GameObject attachedTo) {
		this.center = new Point(center);
		this.radius = radius;
		this.attachedTo = attachedTo;
	}
	
	/*
	 * GETTER
	 */
	
	public double getX() {
		return this.center.getX();
	}
	
	public double getY() {
		return this.center.getY();
	}
	
	public Point getCenter() {
		return this.center;
	}
	
	public double getRadius() {
		return this.radius;
	}
	
	/*
	 * SETTER
	 */
	
	
	
	/*
	 * OTHER METHODS
	 */
	
	public boolean overlaps(Collider c) {
		if(c instanceof ColliderBox) {
			
		}
		
		if(c instanceof ColliderCircle) {
			
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param c The box in which the circle has to be
	 * @return Returns true if the Circle is fully inside the Box
	 */
	public boolean inside(ColliderBox c) {
		if(this.center.getX() - this.radius > c.getX()
				&& this.center.getX() + this.radius < c.getX() + c.getWidth()
				&& this.center.getY() - this.radius > c.getY() 
				&& this.center.getY() + this.radius < c.getY() + c.getHeight()) {
			return true;
		}
		return false;
	}
	
	public boolean overlaps(ColliderCircle c) {
		//A = this
		//B = the argument passed
		//Dist = sqrt((Ax - Bx)^2 + (Ay - By)^2)
		//dist <= A.radius + B.radius
		double deltaXSquared = this.center.getX() - c.getX();
		deltaXSquared *= deltaXSquared;
		
		double deltaYSquared = this.center.getY() - c.getY();
		deltaYSquared *= deltaYSquared;
		
		double sumRadiiSquared = this.radius + c.getRadius();
		sumRadiiSquared *= sumRadiiSquared;
		
		//Check if dist^2 <= (a.radius + b.radius)^2
		//better performance to square, than get square root
		if(deltaXSquared + deltaYSquared <= sumRadiiSquared) {
			return true;
		}
		
		return false;
	}
	
	//Source: http://www.gamasutra.com/view/feature/3015/pool_hall_lessons_fast_accurate_.php
	public boolean collideWithStatic(ColliderCircle c) {
		// Early Escape Test: if length of vector of moving
		// circle is less than distance bezween the centers
		// of the circles minus their radii, there's no way
		// they can hit
		double dist = c.getCenter().distance(this.center);
		double sumRadii = c.getRadius() + this.radius;
		dist -= sumRadii;
		if(attachedTo.getVelocity().getLength() < dist) {
			return false;
		}
		
		return true;
	}
	
	public boolean collideWithDynamic(ColliderCircle c) {
		
		
		return true;
	}

	@Override
	public void setPosition(Point point) {
		center = point;
	}

	@Override
	public void setPosition(double x, double y) {
		center.setX(x);
		center.setY(y);		
	}

	@Override
	public double getWidth() {
		// TODO MAKE EXCEPTION
		return 0;
	}

	@Override
	public double getHeight() {
		// TODO MAKE EXCEPTION
		return 0;
	}
	

	@Override
	public void draw(Graphics g) {
		g.setColor(DRAW_COLOR);
		g.drawOval((int)(center.getX() - radius), (int)(center.getY() - radius), (int)(radius * 2), (int)(radius * 2));
	}
}
	

