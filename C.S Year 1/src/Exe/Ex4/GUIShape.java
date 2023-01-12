package Exe.Ex4;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;


public class GUIShape implements GUI_Shapeable{
	private GeoShapeable _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;
	
	public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}

	public GUIShape(String s){
		String[] arr = s.split(",");
		init(arr);
	}
	
	@Override
	public GeoShapeable getShape() {
		return _g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;
		
	}

	@Override
	public GUI_Shapeable copy() {
		GUI_Shapeable cp = new GUIShape(this);
		return cp;
	}
	@Override
	public String toString() {
		return "GUIShape," + _color.getRGB() +"," + _fill+"," + _tag +","+ _g.toString()  ;
	}
	private void init(String[] ww) {
		_color = new Color(Integer.parseInt(ww[1]));
		_fill = Boolean.parseBoolean(ww[2]);
		_tag = Integer.parseInt(ww[3]);
		if(ww[4].equals("Circle2D")){
			_g = new Circle2D(new Point2D(Double.parseDouble(ww[5]),Double.parseDouble(ww[6])),
								Double.parseDouble(ww[7]));
		}if(ww[4].equals("Polygon2D")){
			ArrayList<Point2D> point2DS = new ArrayList<>();
			for (int i = 5; i < ww.length; i+=2) {
				point2DS.add(new Point2D(Double.parseDouble(ww[i]),Double.parseDouble(ww[i+1])));
			}
			_g = new Polygon2D(point2DS);
		}if(ww[4].equals("Triangle2D")){
			_g = new Triangle2D(new Point2D(Double.parseDouble(ww[5]),Double.parseDouble(ww[6])),
					new Point2D(Double.parseDouble(ww[7]) ,Double.parseDouble(ww[8]) ) , new Point2D(Double.parseDouble(ww[9]) ,Double.parseDouble(ww[10]) ));
		}if(ww[4].equals("Rect2D")){
			_g = new Rect2D(new Point2D(Double.parseDouble(ww[5]),Double.parseDouble(ww[6])),
					new Point2D(Double.parseDouble(ww[11]) ,Double.parseDouble(ww[12]) ) );
		}if(ww[4].equals("Segment2D")){
			_g = new Segment2D(new Point2D(Double.parseDouble(ww[5]),Double.parseDouble(ww[6])),
					new Point2D(Double.parseDouble(ww[7]) ,Double.parseDouble(ww[8]) ) );
		}


	}
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	@Override
	public void setShape(GeoShapeable g) {
		_g = g;
	}
}
