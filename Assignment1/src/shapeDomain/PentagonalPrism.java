package shapeDomain;

public class PentagonalPrism extends Prism {
	public PentagonalPrism(double height, double side) {
		super(height, side);
	}

	@Override
	public double calcVolume() {
		return calcBaseArea() * getHeight();
	}

	@Override
	public double calcBaseArea() {
		return (5 * Math.pow(getSide(), 2) * Math.tan(Math.toRadians(54))) / 4;
	}
	
	@Override
	public String toString() {
		return "PentagonalPrism";
	}
}