package objects;

import java.awt.Color;
import java.awt.Graphics;

import collision.Collider;
import util.Vector2D;

public abstract class GameObject {
	protected boolean moveable;
	protected Collider collider;
	
	protected Color drawColor;
	
	
	public abstract Vector2D getVelocity();
	public abstract Collider getCollider();
	
	public abstract boolean isMoveable();
	
	public abstract void addCollider(Collider collider);
	
	public abstract void draw(Graphics g);
	public abstract void draw(Graphics g, Color color);
	public abstract void fill(Graphics g);
	public abstract void fill(Graphics g, Color color);
	public abstract void setColor(Color color);
}
