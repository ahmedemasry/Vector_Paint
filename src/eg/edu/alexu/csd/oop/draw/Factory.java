package eg.edu.alexu.csd.oop.draw;

import javax.management.RuntimeErrorException;

public class Factory {

	public Shape create(String name) {
		try {
			Package p = Factory.class.getPackage();
//			Class<?> newClass = Class.forName(p.getName() + "." + name);
			System.out.println("(Factory) Class Name : " + name);
			Class<?> newClass = Class.forName(name);
			Shape newShape = (Shape) newClass.newInstance();
			return newShape;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
