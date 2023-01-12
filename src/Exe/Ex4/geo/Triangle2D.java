package Exe.Ex4.geo;

import java.awt.*;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{
	private Point2D _firstPoint;
	private Point2D _secondPoint;
	private Point2D _thirdPoint;
	public Triangle2D (Point2D p1 , Point2D p2 , Point2D p3){
		this._firstPoint = p1;
		this._secondPoint = p2;
		this._thirdPoint = p3;
	}
	@Override
	public boolean contains(Point2D ot) { //The function calculates the barycentric coordinates alpha, beta, and gamma of the point with respect to the triangle, and returns true if all of these coordinates are non-negative (i.e. the point is inside the triangle) and false otherwise.
		double denominator = ((_secondPoint.y() - _thirdPoint.y()) * (_firstPoint.x() - _thirdPoint.x()) + (_thirdPoint.x() - _secondPoint.x()) * (_firstPoint.y() - _thirdPoint.y()));

		double alpha = ((_secondPoint.y() - _thirdPoint.y()) * (ot.x() - _thirdPoint.x()) + (_thirdPoint.x() - _secondPoint.x()) * (ot.y() - _thirdPoint.y())) / denominator;
		double beta = ((_thirdPoint.y() - _firstPoint.y()) * (ot.x() - _thirdPoint.x()) + (_firstPoint.x() - _thirdPoint.x()) * (ot.y() - _thirdPoint.y())) / denominator;
		double gamma = 1.0 - alpha - beta;

		return (alpha >= 0) && (beta >= 0) && (gamma >= 0);
	}

	@Override
	public double area() {
		double xMin = Math.min(Math.min(_firstPoint.x() , _secondPoint.x()) , Math.min(_firstPoint.x() , _thirdPoint.x()));
		double xMax = Math.max(Math.max(_firstPoint.x() , _secondPoint.x()) , Math.max(_firstPoint.x() , _thirdPoint.x()));
		double yMin = Math.min(Math.min(_firstPoint.y() , _secondPoint.y()) , Math.min(_firstPoint.y() , _thirdPoint.y()));
		double yMax = Math.max(Math.max(_firstPoint.y() , _secondPoint.y()) , Math.max(_firstPoint.y() , _thirdPoint.y()));
		double height = yMax - yMin;
		double width =  xMax - xMin;
		double area = (height * width) / 2;
		return area;
	}

	@Override
	public double perimeter() {
		double distanceP1P2 = Math.sqrt(Math.pow(_firstPoint.x() - _secondPoint.x(),2 ) + Math.pow(_firstPoint.y() - _secondPoint.y() , 2));
		double distanceP2P3 = Math.sqrt(Math.pow(_secondPoint.x() - _thirdPoint.x(),2 ) + Math.pow(_secondPoint.y() - _thirdPoint.y() , 2));
		double distanceP3P1 = Math.sqrt(Math.pow(_thirdPoint.x() - _firstPoint.x(),2 ) + Math.pow(_thirdPoint.y() - _firstPoint.y() , 2));
		double perimeter = distanceP3P1 + distanceP2P3 + distanceP1P2;
		return perimeter;
	}

	@Override
	public void move(Point2D vec) {
		_firstPoint.move(vec);
		_secondPoint.move(vec);
		_thirdPoint.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		Point2D firstPoint = new Point2D(_firstPoint.x(), _firstPoint.y());
		Point2D secondPoint = new Point2D(_secondPoint.x(), _secondPoint.y());
		Point2D thirdPoint = new Point2D(_thirdPoint.x(), _thirdPoint.y());
		return new Triangle2D(firstPoint , secondPoint , thirdPoint );
	}

	@Override
	public void scale(Point2D center, double ratio) {
		_firstPoint.scale(center,ratio);
		_secondPoint.scale(center,ratio);
		_thirdPoint.scale(center,ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		_firstPoint.rotate(center,angleDegrees);
		_secondPoint.rotate(center,angleDegrees);
		_thirdPoint.rotate(center,angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		Point2D [] pointCollection = new Point2D[3];
		pointCollection[0] = new Point2D(_firstPoint);
		pointCollection[1] = new Point2D(_secondPoint);
		pointCollection[2] = new Point2D(_thirdPoint);
		return pointCollection;
	}
	public String toString() {
		return "Triangle2D," + _firstPoint.x() + "," + _firstPoint.y() + ","
				+ _secondPoint.x() + "," + _secondPoint.y() + "," + _thirdPoint.x() + "," + _thirdPoint.y();
	}

}
