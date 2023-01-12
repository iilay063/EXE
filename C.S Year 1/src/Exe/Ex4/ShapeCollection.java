package Exe.Ex4;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
	private ArrayList<GUI_Shapeable> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) {
		return _shapes.remove(i);
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		_shapes.add(i,s);
	}
	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public ShapeCollectionable copy() {
		ShapeCollection copy = new ShapeCollection();
		ArrayList<GUI_Shapeable> copyArray = new ArrayList();
		for (int i = 0; i < _shapes.size(); i++) {
			copyArray.add(_shapes.get(i).copy());
		}
		copy._shapes = copyArray;
		return copy;
	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		_shapes.sort(comp);
	}

	@Override
	public void removeAll() {
		_shapes.clear();
	}

	@Override
	public void save(String file) {
		try {
			File fileToCreate = new File(file);
			fileToCreate.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			for (int i = 0; i < _shapes.size(); i++) {
				fileWriter.write(_shapes.get(i).toString());
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String file) {
		File fileToRead = new File(file);
		try {
			Scanner scanner = new Scanner(fileToRead);
			while (scanner.hasNextLine()){
				_shapes.add(new GUIShape(scanner.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	@Override
	public Rect2D getBoundingBox() {
		Rect2D ans = null;
		if(!_shapes.isEmpty()) {
			double xMin = _shapes.get(0).getShape().getPoints()[0].x();
			double xMax = _shapes.get(0).getShape().getPoints()[0].x();
			double yMin = _shapes.get(0).getShape().getPoints()[0].y();
			double yMax = _shapes.get(0).getShape().getPoints()[0].y();
			for (int i = 0; i < _shapes.size(); i++) {
				Point2D[] points = _shapes.get(i).getShape().getPoints();
				for (int j = 0; j < points.length; j++) {
					xMin = Math.min(points[j].x(), xMin);
					yMin = Math.min(points[j].y(), yMin);
					xMax = Math.max(points[j].x(), xMax);
					yMax = Math.max(points[j].y(), yMax);
				}
			}
			ans = new Rect2D(new Point2D(xMin, yMin), new Point2D(xMax, yMax));
		}
		return ans;
	}
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
	

}
