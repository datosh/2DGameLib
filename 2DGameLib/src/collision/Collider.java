package collision;

import java.awt.Color;
import java.awt.Graphics;

import objects.GameObject;
import util.Point;

public abstract class Collider {
	protected final Color DRAW_COLOR = Color.GREEN;
	
	protected GameObject attachedTo;
	
	abstract public double getX();
	abstract public double getY();
	abstract public double getWidth();
	abstract public double getHeight();
	abstract public double getRadius();
	abstract public boolean overlaps(Collider c);
	abstract public void setPosition(Point point);
	abstract public void setPosition(double x, double y);
	public abstract void draw(Graphics g);
}
