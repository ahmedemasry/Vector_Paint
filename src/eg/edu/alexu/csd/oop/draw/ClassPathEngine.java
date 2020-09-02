package eg.edu.alexu.csd.oop.draw;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

import javax.management.RuntimeErrorException;

public class ClassPathEngine {

	private List<Class<? extends Shape>> list = new LinkedList<Class<? extends Shape>>();

	public ClassPathEngine() {
		try {
			// list.add(Ellipse.class);
			// list.add(Circle.class);
			// list.add(Dot.class);
			// list.add(EquilateralTriangle.class);
			// list.add(IsoscelesTriangle.class);
			// list.add(Line.class);
			// list.add(MyShape.class);
			// list.add(Parallelogram.class);
			// list.add(Rectangle.class);
			// list.add(Rhombus.class);
			// list.add(RightTriangle.class);
			// list.add(Square.class);
			// list.add(Trapezoid.class);
			// list.add(Triangle.class);
			String classPath = System.getProperty("java.class.path");
			String[] paths = classPath.split(System
					.getProperty("path.separator"));
			for (String path : paths) {
				if (path.endsWith(".jar")) {
					JarInputStream j = new JarInputStream(new FileInputStream(
							new File(path)));
					JarEntry dummy = j.getNextJarEntry();
					while (dummy != null) {
						if (dummy.getName().endsWith(".class")) {
							String className = dummy.getName().substring(0,
									dummy.getName().length() - 6);
							className = className.replace('/', '.');
							Class<?> c = Class.forName(className);
							if (Shape.class.isAssignableFrom(c)) {
								list.add((Class<? extends Shape>) c);
							}
						}
						dummy = j.getNextJarEntry();
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeErrorException(null);
		}
	}

	public List<Class<? extends Shape>> getList() {
		try {
			list.add(Ellipse.class);
			list.add(Circle.class);
			list.add(Dot.class);
			list.add(EquilateralTriangle.class);
			list.add(IsoscelesTriangle.class);
			list.add(Line.class);
			list.add(MyShape.class);
			list.add(Parallelogram.class);
			list.add(Rectangle.class);
			list.add(Rhombus.class);
			list.add(RightTriangle.class);
			list.add(Square.class);
			list.add(Trapezoid.class);
			list.add(Triangle.class);
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeErrorException(null);
		}
	}

	private boolean isJarFile(String file) {
		try {
			JarFile jarFile = new JarFile(file);
			jarFile.close();
		} catch (java.util.zip.ZipException e) {
			// the uploaded file is NOT JAR file
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

	private boolean isClass(String name) {
		try {
			if (name.endsWith(".class")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("");
		}
	}

	@SuppressWarnings("unchecked")
	private void check(String name) {
		try {
			String className = name.substring(0, name.length() - 6);
			className = className.replace('/', '.');
			Class<?> c = Class.forName(className);
			if (Shape.class.isAssignableFrom(c)) {
				list.add((Class<? extends Shape>) c);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("failed in the check");
		}
	}
}
