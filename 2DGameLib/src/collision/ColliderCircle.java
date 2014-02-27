package collision;

import objects.GameObject;
import util.Point;

public class ColliderCircle extends Collider{
	private Point center;
	private double radius;
	private GameObject attachedTo;
	
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
}
