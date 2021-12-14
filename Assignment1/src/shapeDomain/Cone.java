package shapeDomain;

public class Cone extends Shape {
	private double radius;

	public Cone(double height, double radius) {
		super(height);
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	@Override
	public double calcVolume() {
		return (1 / 3.0) * calcBaseArea() * getHeight();
	}

	@Override
	public double calcBaseArea() {
		return Math.PI * Math.pow(getRadius(), 2);
	}
	
	@Override
	public String toString() {
		return "Cone";
	}
}