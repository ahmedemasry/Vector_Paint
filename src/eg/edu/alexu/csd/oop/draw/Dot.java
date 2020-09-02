package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;

import javax.management.RuntimeErrorException;

public class Dot extends MyShape implements Shape{

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		try {
			center.x = (int) ((double) super.getProperties().get("Center_X"));
			center.y = (int) ((double) super.getProperties().get("Center_Y"));
			canvas.setColor(this.getColor());
			canvas.drawLine(center.x, center.y,center.x+3,center.y+3);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	
	
}
