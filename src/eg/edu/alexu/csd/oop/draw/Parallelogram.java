package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;

import javax.management.RuntimeErrorException;

/*
 *  	 ________
 *	    /       /
 *	   /_______/
 * */
public class Parallelogram extends Trapezoid implements Shape {

	protected Double side_1, side_2;

	public Parallelogram() {
		super();
		super.properties.remove("Side_Parelle_1");
		super.properties.remove("Side_Parelle_2");
		super.properties.remove("Side_3");
		super.properties.put("Side_1", null);
		super.properties.put("Side_2", null);
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		try {
			side_1 = super.getProperties().get("Side_1");
			side_2 = super.getProperties().get("Side_2");
			angle = super.getProperties().get("Angle");
			center.x = (int) ((double) super.getProperties().get("Center_X"));
			center.y = (int) ((double) super.getProperties().get("Center_Y"));
			super.drawTrapezoid(side_1, side_1, side_2, angle, canvas);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	

}
