package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Double> m = new HashMap<String, Double>();

		DrawingEngine de = MyDrawingEngine.getInstance();
		Point c = null;
		
		
		Shape s = new Circle();
		s.setProperties(null);
		s.setColor(Color.BLUE);
		s.setFillColor(Color.RED);
		de.addShape(s);

		Shape s1 = null;
		de.addShape(s1);

		Shape s2 = new Parallelogram();
		m = s2.getProperties();
		m.put("Side_1", (double) 200);
		m.put("Side_2", (double) 100);
		m.put("Angle", (double) 135);
		m.put("Center_X", (double) 500);
		m.put("Center_Y", (double) 100);
		c = new Point(50, 50);
		s2.setPosition(c);
		s2.setProperties(m);
		s2.setColor(Color.GREEN);
		s2.setFillColor(Color.PINK);
		de.addShape(s2);

		Shape s8 = new RightTriangle();
		m = s8.getProperties();
		m.put("Side_1", (double) 200);
		m.put("Side_2", (double) 100);
		m.put("Center_X", (double) 300);
		m.put("Center_Y", (double) 300);
		c = new Point(50, 50);
		s8.setPosition(c);
		s8.setProperties(m);
		s8.setColor(Color.GREEN);
		s8.setFillColor(Color.PINK);
		de.addShape(s8);

		Shape s9 = new RightTriangle();
		m = s9.getProperties();
		m.put("Side_1", (double) 100);
		m.put("Side_2", (double) 50);
		m.put("Center_X", (double) 300);
		m.put("Center_Y", (double) 300);
		c = new Point(50, 50);
		s9.setPosition(c);
		s9.setProperties(m);
		s9.setColor(Color.GREEN);
		s9.setFillColor(Color.PINK);
		de.addShape(s9);
		
		System.out.println(de.getShapes()[2].getPosition());
		de.save("/debug/ahmed_masry.json");
		de.load("/debug/ahmed_masry.json");
	}

}
