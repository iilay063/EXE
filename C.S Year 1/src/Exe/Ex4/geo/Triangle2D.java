package Exe.Ex4.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */
public class Triangle2D implements GeoShapeable {
	private Point2D _firstPoint;
	private Point2D _thirdPoint;

	private Point2D _secondPoint;

	public Triangle2D(Segment2D firstLine, Segment2D secondLine) {
		this._firstPoint = firstLine.getPoints()[0];
		this._secondPoint = firstLine.getPoints()[1];
		this._thirdPoint = secondLine.getPoints()[1];
	}

	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub

	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] pCollection = new Point2D[3];
		pCollection[0] = new Point2D(this._firstPoint);
		pCollection[1] = new Point2D(this._secondPoint);
		pCollection[2] = new Point2D(this._thirdPoint);

		return pCollection;
	}

}
