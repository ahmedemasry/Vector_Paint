package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;

public class Square extends Rectangle implements Shape {

	protected Double side;

	public Square() {
		super();
		super.properties.remove("Side_1");
		super.properties.remove("Side_2");
		super.properties.put("Side", null);
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		side = super.getProperties().get("Side");
		center.x = (int) ((double) super.getProperties().get("Center_X"));
		center.y = (int) ((double) super.getProperties().get("Center_Y"));
		super.drawTrapezoid(side, side, side, (double) 90, canvas);
	}

	
}
