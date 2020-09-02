package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;


public class MyShape implements Shape{

	protected Point center ;
	protected Color lineColor , fillColor ;
	protected Map<String, Double> properties = new HashMap<String, Double>();

	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		try {
			center = position ;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		try {
			return center ;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		try {
			this.properties = properties;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		try {
			return this.properties ;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		try {
			lineColor = color ;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		try {
			return lineColor ;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub
		try {
			fillColor = color ;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		try {
			return fillColor ;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Shape p = null;
		p = (MyShape) super.clone();
		return p ;
	}
	
}
