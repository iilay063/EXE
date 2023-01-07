package Exe.Ex4.geo;


/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{
	private Point2D _firstPoint;
	private Point2D _secondPoint;

	public Segment2D(Point2D p1, Point2D p2) {
		this._firstPoint = new Point2D(p1);
		this._secondPoint = new Point2D(p2);
	}

	@Override
	/** a point is on a straight line when the line equation exist with it */

	public boolean contains(Point2D ot) {
		Point2D firstPoint = new Point2D(_firstPoint.x(), _firstPoint.y());
		Point2D secondPoint = new Point2D(_secondPoint.x(), _secondPoint.y());
		double m = (firstPoint.y() - secondPoint.y()) / (firstPoint.x() - secondPoint.x());
		double c = (m * firstPoint.x()) - firstPoint.y();
		return ot.y() == m * ot.x() + c;
	}

	@Override
	/** area of a straight line is just the length of the line itself so calculating the distance between _firstPoint and _secondPoint would be sufficient*/
	public double area() {
		Point2D firstPoint = new Point2D(_firstPoint.x(), _firstPoint.y());
		Point2D secondPoint = new Point2D(_secondPoint.x(), _secondPoint.y());
		double x1 = firstPoint.x();
		double y1 = firstPoint.y();
		double x2 = secondPoint.x();
		double y2 = secondPoint.y();
		double length = Math.sqrt(Math.pow(2 , (x2 - x1))+Math.pow(2 ,(y2 - y1) ));;
		return length;
	}

	@Override
	public double perimeter() {
		Point2D firstPoint = new Point2D(_firstPoint.x(), _firstPoint.y());
		Point2D secondPoint = new Point2D(_secondPoint.x(), _secondPoint.y());
		double x1 = firstPoint.x();
		double y1 = firstPoint.y();
		double x2 = secondPoint.x();
		double y2 = secondPoint.y();
		double length = Math.sqrt(Math.pow(2 , (x2 - x1))+Math.pow(2 ,(y2 - y1) ));;
		return length;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GeoShapeable copy() {
		Point2D firstPoint = new Point2D(_firstPoint.x(), _firstPoint.y());
		Point2D secondPoint = new Point2D(_secondPoint.x(), _secondPoint.y());
		return new Segment2D(firstPoint , secondPoint);
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
		Point2D [] ans = new Point2D[2];
		ans[0] = new Point2D(this._firstPoint);
		ans[1] = new Point2D(this._secondPoint);
		return ans;
	}
	
}