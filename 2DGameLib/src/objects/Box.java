package objects;

import java.awt.Color;
import java.awt.Graphics;

import util.Vector2D;
import collision.Collider;

public class Box extends GameObject {
	double x, y;
	double width, height;
	
	public Box(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	
	@Override
	public Vector2D getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collider getCollider() {
		if(this.collider == null) {
			return null;
		} else {
			return collider;
		}
	}

	@Override
	public void addCollider(Collider collider) {
		this.collider = collider;
	}

	@Override
	public boolean isMoveable() {
		return this.moveable;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(drawColor);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void draw(Graphics g, Color color) {
		this.drawColor = color;
		g.setColor(drawColor);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(drawColor);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void fill(Graphics g, Color color) {
		this.drawColor = color;
		g.setColor(drawColor);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void setColor(Color color) {
		this.drawColor = color;		
	}
	
	

}
