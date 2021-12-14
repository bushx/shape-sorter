package shapeDomain;

public class Cylinder extends Shape {
	private double radius;
	
	public Cylinder(double height, double radius) {
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
		return calcBaseArea() * getHeight();
	}

	@Override
	public double calcBaseArea() {
		return Math.PI * Math.pow(getRadius(), 2);
	}
	
	@Override
	public String toString() {
		return "Cylinder";
	}
}