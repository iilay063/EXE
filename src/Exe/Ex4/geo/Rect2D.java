package Exe.Ex4.geo;

import java.awt.geom.Rectangle2D;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */
public class Rect2D implements GeoShapeable {

    private Point2D _firstPoint;
    private Point2D _secondPoint;

    public Rect2D(Point2D p1, Point2D p2) {
        this._firstPoint = new Point2D(p1);
        this._secondPoint = new Point2D(p2);

    }

    @Override
    public boolean contains(Point2D ot) {
        double xMin = Math.min(this._firstPoint.x(), this._secondPoint.x());
        double xMax = Math.max(this._firstPoint.x(), this._secondPoint.x());
        double yMin = Math.min(this._firstPoint.y(), this._secondPoint.y());
        double yMax = Math.max(this._firstPoint.y(), this._secondPoint.y());
        if (ot.x() <= xMax && ot.x() >= xMin && ot.y() <= yMax && ot.y() >= yMin) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double area() {
        double height = Math.abs(this._firstPoint.y() - this._secondPoint.y());
        double width = Math.abs(this._firstPoint.x() - this._secondPoint.x());
        double area = height * width;
        return area;
    }

    @Override
    public double perimeter() {
        double length = Math.abs(this._firstPoint.y() - this._secondPoint.y());
        double height = Math.abs(this._firstPoint.x() - this._secondPoint.x());
        double perimeter = (length * 2) + (height * 2);
        return perimeter;
    }

    @Override
    public void move(Point2D vec) {
        _firstPoint.move(vec);
        _secondPoint.move(vec);
    }

    @Override
    public GeoShapeable copy() {
        Point2D firstPoint = new Point2D(this._firstPoint.x(), this._firstPoint.y());
        Point2D secondPoint = new Point2D(this._secondPoint.x(), this._secondPoint.y());
        return new Rect2D(firstPoint, secondPoint);
    }

    @Override
    public void scale(Point2D center, double ratio) {
        _firstPoint.scale(center, ratio);
        _secondPoint.scale(center, ratio);
    }

    @Override
    public void rotate(Point2D center, double angleDegrees) {
        _firstPoint.rotate(center, angleDegrees);
        _secondPoint.rotate(center, angleDegrees);
    }

    @Override
    public Point2D[] getPoints() {
        Point2D[] pCollection = new Point2D[2];
        double xMin = Math.min(this._firstPoint.x(), this._secondPoint.x());
        double xMax = Math.max(this._firstPoint.x(), this._secondPoint.x());
        double yMin = Math.min(this._firstPoint.y(), this._secondPoint.y());
        double yMax = Math.max(this._firstPoint.y(), this._secondPoint.y());
        pCollection[0] = new Point2D(xMin, yMin);
        pCollection[1] = new Point2D(xMax, yMax);

        return pCollection;
    }

    @Override
    public String toString() {
        return "Rect2D," + _firstPoint.x() + "," + _firstPoint.y() + ","
                + _firstPoint.x() + "," + _secondPoint.y() + "," + _secondPoint.x() + "," + _firstPoint.y()
                + "," + _secondPoint.x() + "," + _secondPoint.y();
    }
}
