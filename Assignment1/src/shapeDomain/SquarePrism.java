package shapeDomain;

public class SquarePrism extends Prism {
	public SquarePrism(double height, double side) {
		super(height, side);
	}

	@Override
	public double calcVolume() {
		return calcBaseArea() * getHeight();
	}

	@Override
	public double calcBaseArea() {
		return  Math.pow(getSide(), 2);
	}
	
	@Override
	public String toString() {
		return "SquarePrism";
	}
}