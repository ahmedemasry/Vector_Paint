package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;

import javax.management.RuntimeErrorException;

public class Line extends MyShape implements Shape {

	double x2, y2;

	public Line() {
		super();
		super.properties.put("Center_X", null);
		super.properties.put("Center_Y", null);
		super.properties.put("To_X", null);
		super.properties.put("To_Y", null);
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		try {
			x2 = (int) ((double) super.getProperties().get("To_X"));
			y2 = (int) ((double) super.getProperties().get("To_Y"));
			center.x = (int) ((double) super.getProperties().get("Center_X"));
			center.y = (int) ((double) super.getProperties().get("Center_Y"));
			canvas.setColor(this.getColor());
			canvas.drawLine(center.x, center.y, (int) x2, (int) y2);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	
}
