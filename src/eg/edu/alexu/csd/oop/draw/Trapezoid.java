package eg.edu.alexu.csd.oop.draw;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.management.RuntimeErrorException;

public class Trapezoid extends MyShape implements Shape {

	protected Double sideParelle_1, sideParelle_2, side_3, angle;

	public Trapezoid() {
		super();
		super.properties.put("Side_Parelle_1", null);
		super.properties.put("Side_Parelle_2", null);
		super.properties.put("Side_3", null);
		super.properties.put("Angle", null);
		super.properties.put("Center_X", null);
		super.properties.put("Center_Y", null);
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		try {
			sideParelle_1 = super.getProperties().get("Side_Parelle_1");
			sideParelle_2 = super.getProperties().get("Side_Parelle_2");
			side_3 = super.getProperties().get("Side_3");
			angle = super.getProperties().get("Angle");
			center.x = (int) ((double) super.getProperties().get("Center_X"));
			center.y = (int) ((double) super.getProperties().get("Center_Y"));
			this.drawTrapezoid(sideParelle_1, sideParelle_2, side_3, angle,
					canvas);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	protected void drawTrapezoid(Double sideParelle_1,
			Double sideParelle_2, Double side_3, Double angle, Graphics canvas) {
		try {
			if (canvas instanceof Graphics2D) {
				Graphics2D accurateCanvas = (Graphics2D) canvas;
				accurateCanvas.setStroke(new BasicStroke(10));
				double x2 = center.x + sideParelle_1;
				double y2 = center.y;
				double x3 = - side_3 * Math.cos(Math.PI * angle / 180) + x2;
				double y3 = side_3 * Math.sin(Math.PI * angle / 180) + y2;
				double x4 = x3 - sideParelle_2;
				double y4 = y3;
				GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
				path.moveTo(center.x, center.y);
				path.lineTo(x2, y2);
				path.lineTo(x3, y3);
				path.lineTo(x4, y4);
				path.closePath();
				accurateCanvas.setColor(super.getColor());
				accurateCanvas.draw(path);
				accurateCanvas.setColor(super.getFillColor());
				accurateCanvas.fill(path);
			} else {
				double x2 = center.x + sideParelle_1;
				double y2 = center.y;
				double x3 = -side_3 * Math.cos(Math.PI * angle / 180) + x2;
				double y3 = side_3 * Math.sin(Math.PI * angle / 180) + y2;
				double x4 = x3 + sideParelle_2;
				double y4 = y3;
				canvas.setColor(super.getColor());
				canvas.drawLine(center.x, center.y, (int) x2, (int) y2);
				canvas.drawLine((int) x2, (int) y2, (int) x3, (int) y3);
				canvas.drawLine((int) x3, (int) y3, (int) x4, (int) y4);
				canvas.drawLine((int) x4, (int) y4, center.x, center.y);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

}