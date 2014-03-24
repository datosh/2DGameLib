package collision;

import java.awt.Color;
import java.awt.Graphics;

import objects.GameObject;
import util.Point;


public class ColliderBox extends Collider {
	private double x, y;
	private double width, height;
	
	public ColliderBox() {
		x = 0; 
		y = 0;
		width = 0;
		height = 0;
	}
	
	public ColliderBox(ColliderBox cb) {
		this.x = cb.getX();
		this.y = cb.getY();
		this.width = cb.getWidth();
		this.height = cb.getHeight();
	}
	
	public ColliderBox(ColliderBox cb, GameObject attachedTo) {
		this.x = cb.getX();
		this.y = cb.getY();
		this.width = cb.getWidth();
		this.height = cb.getHeight();
		this.attachedTo = attachedTo;
	}
	
	public ColliderBox(Point point, int width, int height) {
		this.x = point.getX();
		this.y = point.getY();
		this.width = width;
		this.height = height;
	}
	
	public ColliderBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public ColliderBox(int x, int y, int width, int height, GameObject attachedTo) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.attachedTo = attachedTo;
	}
	
	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getRadius() {
		return 0; //TODO: EXCEPTION
	}
	
	public void move(int deltaX, int deltaY) {
		this.x += deltaX;
		this.y += deltaY;
	}
	

	public void setPosition(Point point) {
		this.x = point.getX();
		this.y = point.getY();
	}
	
	@Override
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean overlaps(Collider c) {
		if(c instanceof ColliderBox) {
			if(x - c.getX() > 0 && x - c.getX() < width && y - c.getY() > 0 && y - c.getY() < height) {
				return true;
			}
		}
		return false;
	}
	
	public boolean overlaps(ColliderBox c) {
		return !(this.x + this.width < c.x || c.x + c.width < x || this.y + this.height < c.y || c.y + c.height < y);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(DRAW_COLOR);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}




}
