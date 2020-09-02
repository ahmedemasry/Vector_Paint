package eg.edu.alexu.csd.oop.draw;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.management.RuntimeErrorException;

public class Ellipse extends MyShape implements Shape {

	protected Double height, width;

	public Ellipse() {
		super();
		super.properties.put("Height", null);
		super.properties.put("Width", null);
		super.properties.put("Center_X", null);
		super.properties.put("Center_Y", null);
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		try {
			width = super.getProperties().get("Height");
			height = super.getProperties().get("Width");
			center.x = (int) ((double) super.getProperties().get("Center_X"));
			center.y = (int) ((double) super.getProperties().get("Center_Y"));
			this.drawOval(width, height, canvas);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}



	protected void drawOval(Double a, Double b, Graphics canvas) {
		if (canvas instanceof Graphics2D) {
			Graphics2D accurateCanvas = (Graphics2D) canvas;
			accurateCanvas.setStroke(new BasicStroke(10));
			accurateCanvas.setColor(super.getColor());
			accurateCanvas.drawOval(center.x, center.y, (int) ((double) a),
					(int) ((double) b));
			accurateCanvas.setColor(super.getFillColor());
			accurateCanvas.fillOval(center.x, center.y, (int) ((double) a),
					(int) ((double) b));
		} else {
			int thickness = 4;
			canvas.setColor(super.getColor());
			for (int i = 0; i <= thickness; i++)
				canvas.drawOval(center.x - i, center.y - i, (int) ((double) a)
						+ 2 * i, (int) ((double) b) + 2 * i);
			canvas.setColor(super.getFillColor());
			canvas.fillOval(center.x, center.y, (int) ((double) a),
					(int) ((double) b));
		}

	}
}
