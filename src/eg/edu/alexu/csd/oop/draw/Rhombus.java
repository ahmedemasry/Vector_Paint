package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;

import javax.management.RuntimeErrorException;

/*
 *	    /\
 *	   /  \
 *     \  /
 *      \/
 * */

public class Rhombus extends Parallelogram implements Shape{

	protected Double side ;
	
	public Rhombus() {
		super();
		super.properties.remove("Side_1");
		super.properties.remove("Side_2");
		super.properties.put("Side",null);
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		try {
			side = super.getProperties().get("Side");
			angle = super.getProperties().get("Angle");
			center.x = (int) ((double) super.getProperties().get("Center_X"));
			center.y = (int) ((double) super.getProperties().get("Center_Y"));
			super.drawTrapezoid(side, side, side, angle, canvas);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}



	
	
}
