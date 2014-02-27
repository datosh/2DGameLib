package collision;

public abstract class Collider {
	abstract public double getX();
	abstract public double getY();
	abstract public boolean overlaps(Collider c);

}
