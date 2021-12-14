package shapeDomain;

public abstract class Shape implements Comparable<Shape> {
	private double height;

	public Shape(double height) {
		super();
		this.height = height;
	}
	
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public abstract double calcVolume();
	
	public abstract double calcBaseArea();
	
	@Override
	public int compareTo(Shape s) {
		if(this.getHeight() > s.getHeight()) {
			return 1;
		} else if(this.getHeight() < s.getHeight()) {
			return -1;
		} else {
			return 0;
		}
	}
}