package util;

public class Point {
	double x;
	double y;
	
	/*
	 * CONSTRUCTOR
	 */
	
	public Point() {
		this.x = 0; 
		this.y = 0;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point point) {
		this.x = point.getX();
		this.y = point.getY();
	}
	
	/*
	 * GETTER
	 */
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	/*
	 * SETTER
	 */
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setPosition(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/*
	 * OTHER METHODS
	 */
	
	//Dist = sqrt((x2 - x1)^2 + (y2 - y1)^2)
	public double distance(Point p) {
		return Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y));
	}
}
