package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;

import javax.management.RuntimeErrorException;

public class Circle extends Ellipse implements Shape{

	protected Double Radius ;
	
	public Circle() {
		super.properties.remove("Height");
		super.properties.remove("Width");
		super.properties.put("Radius", null);
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		try {
			Radius = super.getProperties().get("Radius");
			center.x = (int) ((double) super.getProperties().get("Center_X"));
			center.y = (int) ((double) super.getProperties().get("Center_Y"));
			drawOval(Radius, Radius, canvas);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		try {
			Shape copy = new Circle();
			copy.setProperties(this.properties);
			copy.setColor(this.lineColor);
			copy.setFillColor(this.fillColor);
			copy.setPosition(this.center);
			return copy ;
		} catch (Exception e) {
			// TODO: handle exception
			throw new CloneNotSupportedException() ;
		}
	}
}
