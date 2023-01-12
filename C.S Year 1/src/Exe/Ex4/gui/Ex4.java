package Exe.Ex4.gui;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.*;
import Exe.Ex4.geo.Point2D;

/**
 * 
 * This class is a simple "interlayer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI{
	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private  Point2D _p1;
	private  Point2D _p2;

	private ArrayList<Point2D> pointsOfPolygon = new ArrayList<Point2D>();
	
	private  static Ex4 _winEx4 = null;
	
	private Ex4() {
			init(null);
	}
	public void init(ShapeCollectionable s) {
		if(s==null) {this._shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
	}
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}
	
	public void drawShapes() {
		StdDraw_Ex4.clear();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable sh = _shapes.get(i);

			drawShape(sh);
		}
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}
	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if (g.isSelected()) {
			StdDraw_Ex4.setPenColor(Color.gray);
		}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		if (gs instanceof Circle2D) {
			Circle2D c = (Circle2D) gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if (isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			} else {
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}else if (gs instanceof Segment2D) {
			Segment2D s = (Segment2D) gs;
			Point2D firstPoint = s.getPoints()[0];
			Point2D secondPoint = s.getPoints()[1];
			StdDraw_Ex4.line(firstPoint.x(), firstPoint.y(), secondPoint.x(), secondPoint.y());
		}else if (gs instanceof Rect2D) {
			Rect2D r = (Rect2D) gs;
			Point2D firstPoint = r.getPoints()[0];
			Point2D secondPoint = r.getPoints()[1];
			if (isFill) {
				StdDraw_Ex4.filledRectangle((firstPoint.x() + secondPoint.x()) / 2, (firstPoint.y() + secondPoint.y()) / 2, Math.abs((firstPoint.x() - secondPoint.x())) / 2, Math.abs((firstPoint.y() - secondPoint.y())) / 2);

			} else {
				StdDraw_Ex4.rectangle((firstPoint.x() + secondPoint.x()) / 2, (firstPoint.y() + secondPoint.y()) / 2, Math.abs((firstPoint.x() - secondPoint.x())) / 2, Math.abs((firstPoint.y() - secondPoint.y())) / 2);
			}
		} else if (gs instanceof Triangle2D) {
			Triangle2D t = (Triangle2D) gs;
			double [] xOfPc = new double [3];
			double [] yOfPc = new double[3];
			for (int i = 0 ; i < 3 ; i++){
				xOfPc [i] = t.getPoints()[i].x();
				yOfPc [i] = t.getPoints()[i].y();
			}
			if(isFill){
				StdDraw_Ex4.filledPolygon(xOfPc , yOfPc);
			}else{
				StdDraw_Ex4.polygon(xOfPc , yOfPc);
			}
		} else if (gs instanceof Polygon2D) {
			Polygon2D p = (Polygon2D) gs;
			double [] xOfPc = new double[p.getPoints().length];
			double [] yOfPc = new double[p.getPoints().length];
			for (int i = 0; i < p.getPoints().length; i++) {
				xOfPc[i] = p.getPoints()[i].x();
				yOfPc[i] = p.getPoints()[i].y();
			}
			if(isFill){
				StdDraw_Ex4.filledPolygon(xOfPc , yOfPc);
			}else{
				StdDraw_Ex4.polygon(xOfPc , yOfPc);
			}
		}
	}
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {_shapes.removeAll();}
		if(p.equals("ByArea")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Area));}
		if(p.equals("ByAntiArea")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Area));}
		if(p.equals("ByPerimeter")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Perimeter));}
		if(p.equals("ByAntiPerimeter")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter));}
		if(p.equals("ByToString")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_toString));}
		if(p.equals("ByAntiToString")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_toString));}
		if(p.equals("ByTag")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Tag));}
		if(p.equals("ByAntiTag")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Tag));}
		if(p.equals("Save")) {save();}
		if(p.equals("Load")) {load();}
		if(p.equals("All")) {selectAll(true);}
		if(p.equals("Anti")) {selectAnti();}
		if(p.equals("None")) {selectAll(false);}
		if(p.equals("Info")) {printSelected();}

		drawShapes();
		
	}

	private void printSelected() {
		for (int i = 0; i < _shapes.size(); i++) {
			if(_shapes.get(i).isSelected()){
				System.out.println(_shapes.get(i).toString());
			}
		}
	}

	private void selectAnti() {
		for (int i = 0; i < _shapes.size(); i++) {
			_shapes.get(i).setSelected(!_shapes.get(i).isSelected());
		}
	}

	private void selectAll(Boolean select) {
		for (int i = 0; i < _shapes.size(); i++) {
			_shapes.get(i).setSelected(select);
		}
	}

	private void load() {
	}

	private void save() {

	}


	public void mouseClicked(Point2D p) {
		System.out.println("Mode: " + _mode + "  " + p);
		if (_mode.equals("Move")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			} else {
				_p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
				move();
				_p1 = null;
			}
		}

		if (_mode.equals("Point")) {
			select(p);
		}
		if (_mode.equals("Segment")) {
			if (_gs == null) {
				_p1 = new Point2D(p);

			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if (_mode.equals("Rect")) {
			if (_gs == null) {
				_p1 = new Point2D(p);

			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
			if (_mode.equals("Circle")) {
				if (_gs == null) {
					_p1 = new Point2D(p);
				} else {
					_gs.setColor(_color);
					_gs.setFilled(_fill);
					_shapes.add(_gs);
					_gs = null;
					_p1 = null;
				}
			}
		if (_mode.equals("Triangle")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else if (_p2 == null) {
				_p2 = new Point2D(p);

			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_p2 = null;

			}
		}if(_mode.equals("Polygon")) {
			if (_gs == null) {
				pointsOfPolygon = new ArrayList<Point2D>();
				pointsOfPolygon.add(p);
				_p1 = new Point2D(p);
			}else {
				pointsOfPolygon.add(p);
			}
		}
		/*if(_mode.equals("Polygon")){
			if(_gs == null){
				_gs = new GUIShape(new Polygon2D(),false,Color.pink,0);
			}else{
				((Polygon2D)_gs.getShape()).addPoint(p);
			}
		}*/


		drawShapes();
}

	
	private void select(Point2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	private void move() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.move(_p1);
			}
		}
	}
	
	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!");
		if(_mode.equals("Polygon")&& _p1 != null){
			Polygon2D poly = new Polygon2D(pointsOfPolygon);
			_gs = new GUIShape(poly , _fill , _color , 0);
			_shapes.add(_gs);
			_gs = null;
			_p1 = null;
			drawShapes();
		}else{
			_gs = null;
			_p1 = null;
			pointsOfPolygon.clear();
			drawShapes();
		}
	
	}
	public void mouseMoved(MouseEvent e) {
		if (_p1 != null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
			//	System.out.println("M: "+x1+","+y1);
			Point2D p = new Point2D(x1, y1);
			if (_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle2D(_p1, r);
			} else if (_mode.equals("Segment")) {
				gs = new Segment2D(_p1, p);
			} else if (_mode.equals("Rect")) {
				gs = new Rect2D(_p1, p);

			} else if (_mode.equals("Triangle")) {
				if (_p2 == null) {
					gs = new Segment2D(_p1, p);
				} else {
					gs = new Triangle2D(_p1, _p2, p);
				}
			} else if (_mode.equals("Polygon")) {
				Polygon2D pol = new Polygon2D(pointsOfPolygon);
				pol.addPoint(p);
				gs = pol;

//				if(_gs != null){
//					((Polygon2D)_gs.getShape()).addPoint(p);
//					gs = _gs.getShape();
			}

			_gs = new GUIShape(gs, false, Color.pink, 0);
			drawShapes();
		}
	}
	@Override
	public ShapeCollectionable getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}
