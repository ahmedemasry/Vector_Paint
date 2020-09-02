package eg.edu.alexu.csd.oop.draw;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.management.RuntimeErrorException;

public class Triangle extends MyShape implements Shape {

	protected Double side_1, side_2, angle;

	public Triangle() {
		super();
		super.properties.put("Side_1", null);
		super.properties.put("Side_2", null);
		super.properties.put("Angle", null);
		super.properties.put("Center_X", null);
		super.properties.put("Center_Y", null);
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
			this.drawTriangle(side_1, side_2, angle, canvas);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
		super.draw(canvas);
	}


	protected void drawTriangle(double side_1, double side_2, double angle,
			Graphics canvas) {
		try {
			if (canvas instanceof Graphics2D) {
				Graphics2D accurateCanvas = (Graphics2D) canvas;
				accurateCanvas.setStroke(new BasicStroke(10));
				double x2 = side_1 * Math.cos(Math.PI * angle / 180) + center.x;
				double y2 = side_1 * Math.sin(Math.PI * angle / 180) + center.y;
				GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
				path.moveTo(center.x, center.y);
				path.lineTo(x2, y2);
				path.lineTo(x2 - side_2, y2);
				path.closePath();
				accurateCanvas.setColor(super.getColor());
				accurateCanvas.draw(path);
				accurateCanvas.setColor(super.getFillColor());
				accurateCanvas.fill(path);
			} else {
				double x2 = side_1 * Math.cos(Math.PI * angle / 180) + center.x;
				double y2 = side_1 * Math.sin(Math.PI * angle / 180) + center.y;
				canvas.setColor(super.getColor());
				canvas.drawLine(center.x, center.y, (int) x2, (int) y2);
				canvas.drawLine((int) x2, (int) y2, (int) ((int) x2-side_2), (int) y2);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

}
