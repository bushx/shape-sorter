package shapeDomain;

public class OctagonalPrism extends Prism {
	public OctagonalPrism(double height, double side) {
		super(height, side);
	}
	
	@Override
	public double calcVolume() {
		return calcBaseArea() * getHeight();
	}

	@Override
	public double calcBaseArea() {
		return 2 * (1 + Math.sqrt(2)) * Math.pow(getSide(), 2);
	}
	
	@Override
	public String toString() {
		return "OctagonalPrism";
	}
}