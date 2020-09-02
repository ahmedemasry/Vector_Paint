package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;

import javax.management.RuntimeErrorException;

public class RightTriangle extends Triangle implements Shape{

	protected double side_1 , side_2 ;
	
	public RightTriangle(){
		super();
		super.properties.remove("Angle");
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		try {
			side_1 = super.getProperties().get("Side_1");
			side_2 = super.getProperties().get("Side_2");
			center.x = (int) ((double) super.getProperties().get("Center_X"));
			center.y = (int) ((double) super.getProperties().get("Center_Y"));
			super.drawTriangle(side_1, side_2, 90, canvas);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	
	
}
