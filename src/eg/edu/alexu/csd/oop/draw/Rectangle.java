package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;

public class Rectangle extends Parallelogram implements Shape {
	
	public Rectangle() {
		super();
		super.properties.remove("Acute_Angle");
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		side_1 = super.getProperties().get("Side_1");
		side_2 = super.getProperties().get("Side_2");
		center.x = (int) ((double) super.getProperties().get("Center_X"));
		center.y = (int) ((double) super.getProperties().get("Center_Y"));
		super.drawTrapezoid(side_1, side_1, side_2, (double) 90, canvas);
	}

		
}
