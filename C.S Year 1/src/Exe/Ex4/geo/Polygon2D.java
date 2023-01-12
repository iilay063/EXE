package Exe.Ex4.geo;

import java.util.ArrayList;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{
	private ArrayList<Point2D> _points = new ArrayList<Point2D>();
	public Polygon2D(ArrayList<Point2D> p1) {
		ArrayList<Point2D> clonedArr = new ArrayList<>();
		for(Point2D p2 : p1){
			clonedArr.add(p2);
		}
		this._points = clonedArr;
	}

	public Polygon2D() {
		this._points = new ArrayList<>();
	}
	public void addPoint(Point2D p) {
		_points.add(p);
	}
	@Override
	public boolean contains(Point2D ot) { //using Raycasting algorithm to check whether a point is inside a polygon
		int intersections = 0;
		double x = ot.x();
		double y = ot.y();

		for (int i = 0; i < _points.size(); i++) {
			Point2D p1 = _points.get(i);
			Point2D p2 = _points.get((i + 1) % _points.size());

			double x1 = p1.x();
			double y1 = p1.y();
			double x2 = p2.x();
			double y2 = p2.y();

			// Check if the point is on the edge
			if (ot.distance(p1) < 1e-10 || ot.distance(p2) < 1e-10) {
				return true;
			}

			// Check if the edge is horizontal and the point is above or below it
			if (y1 == y2) {
				if (y > y1) {
					continue;
				}
			}

			// Check if the point is to the left of the edge
			if (x < Math.min(x1, x2)) {
				continue;
			}

			// Check if the point is on the right of the edge
			if (x > Math.max(x1, x2)) {
				continue;
			}

			double slope = (y2 - y1) / (x2 - x1);
			double yIntercept = y1 - slope * x1;
			double yRay = slope * x + yIntercept;

			if (yRay > y) {
				continue;
			}

			intersections++;
		}

		return (intersections % 2 == 1);
	}

	@Override
	public double area() {
		double area = 0;
		for (int i = 0; i < _points.size(); i++) {
			Point2D p1 = _points.get(i);
			Point2D p2 = _points.get((i + 1) % _points.size());

			area += p1.x() * p2.y() - p1.y() * p2.x();
		}

		return Math.abs(area) / 2;
	}

	@Override
	public double perimeter() {
		double sum = 0;
		for (int i = 0; i < _points.size(); i++) {
			sum += _points.get(i).distance(_points.get((i+1)%_points.size()));
		}
		return sum;
	}

	@Override
	public void move(Point2D vec) {
		for (int i = 0; i < _points.size(); i++) {
			_points.get(i).move(vec);
		}
	}

	@Override
	public GeoShapeable copy() {
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		for (Point2D point : _points) {
			points.add(new Point2D(point.x(), point.y()));
		}
		return new Polygon2D(points);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		for (int i = 0; i <_points.size(); i++) {
			_points.get(i).scale(center ,ratio );
		}
		
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (int i = 0; i <_points.size(); i++) {
			_points.get(i).rotate(center ,angleDegrees );
		}
		
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] arr = new Point2D[_points.size()];
		for (int i = 0; i < _points.size(); i++) {
			arr[i] = new Point2D(_points.get(i).x(),_points.get(i).y());
		}
		return arr;
	}
	public String toString() {
		String ans = "Polygon2D,";
		for (int i = 0; i < this._points.size(); i++) {
			if (i == 0) {
				ans += this._points.get(i).toString();
			} else {
				ans += "," + this._points.get(i).toString();
			}
		}
		return ans;
	}
	
}
