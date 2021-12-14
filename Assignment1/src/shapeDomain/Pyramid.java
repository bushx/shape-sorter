package shapeDomain;

public class Pyramid extends Shape {
	private double side;
	
	public Pyramid(double height, double side) {
		super(height);
		this.side = side;
	}
	
	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
	}

	@Override
	public double calcVolume() {
		return (1 / 3.0) * calcBaseArea() * getHeight();
	}

	@Override
	public double calcBaseArea() {
		return Math.pow(getSide(), 2);
	}
	
	@Override
	public String toString() {
		return "Pyramid";
	}
}