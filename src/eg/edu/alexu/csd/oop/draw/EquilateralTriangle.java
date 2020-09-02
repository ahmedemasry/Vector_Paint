package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;

import javax.management.RuntimeErrorException;

public class EquilateralTriangle extends IsoscelesTriangle implements Shape {

	protected Double side;

	public EquilateralTriangle() {
		super();
		super.properties.remove("Angle");
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		try {
			side = super.getProperties().get("Side");
			center.x = (int) ((double) super.getProperties().get("Center_X"));
			center.y = (int) ((double) super.getProperties().get("Center_Y"));
			super.drawTriangle(side, side, 60, canvas);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}


}
