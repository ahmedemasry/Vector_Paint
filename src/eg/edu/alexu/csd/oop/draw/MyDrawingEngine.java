package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MyDrawingEngine implements DrawingEngine {

	private Stack<List<Shape>> undo = new Stack<List<Shape>>();
	private Stack<List<Shape>> redo = new Stack<List<Shape>>();

	private static MyDrawingEngine instance;

	private MyDrawingEngine() {
	}

	public static MyDrawingEngine getInstance() {
		if (instance == null)
			instance = new MyDrawingEngine();
		return instance;
	}

	@Override
	public void refresh(Graphics canvas) {
		// TODO Auto-generated method stub
		try {
			Iterator<Shape> iterator = undo.peek().iterator();
			while (iterator.hasNext()) {
				Shape object = iterator.next();
				object.draw(canvas);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		try {
			if (this.getShapes().length == 0) {
				List<Shape> show = new ArrayList<Shape>();
				undo.push(show);
			}
			Shape[] shapes = getShapes().clone();
			List<Shape> show = new ArrayList<Shape>(Arrays.asList(shapes));
			show.add(shape);
			undo.push(show);
			redo.clear();
			while (undo.size() > 20) {
				undo.remove(0);
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
		try {
			Shape[] shapes = getShapes().clone();
			List<Shape> show = new ArrayList<Shape>(Arrays.asList(shapes));
			show.remove(shape);
			undo.push(show);
			while (undo.size() > 20) {
				undo.remove(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		try {
			Shape[] shapes = getShapes().clone();
			List<Shape> show = new ArrayList<Shape>(Arrays.asList(shapes));
			show.remove(oldShape);
			show.add(newShape);
			undo.push(show);
			while (undo.size() > 20) {
				undo.remove(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		try {
			if (undo.isEmpty()) {
				return new Shape[0];
			}
			return undo.peek().toArray(new Shape[0]);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Getting Shapes", e);
		}
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub

		ClassPathEngine cpe = new ClassPathEngine();
		return cpe.getList();

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		try {
			redo.push(undo.pop());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		try {
			undo.push(redo.pop());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public void save(String path) {
		// String[] temp = path.split(".");
		if(path.startsWith("/")){
			path = path.substring(1);
		}
		String extension = getExtension(path);
		if (extension == null)
			throw new RuntimeException("Null Extension");
		if (extension.toLowerCase().equals("xml")) {
			// AccessFileXML afXML = new AccessFileXML();
			// afXML.writeXML(path);
		} else if (extension.toLowerCase().equals("json")) {
			JSON json = new JSON();
			json.write(path);
		} else {
			throw new RuntimeException();
		}
	}

	public void load(String path) {
		ArrayList<Shape> shapes = null;
		String extension = getExtension(path);
		if(path.startsWith("/")){
			path = path.substring(1);
		}
		try {
			if (extension.toLowerCase().equals("xml")) {
				// AccessFileXML afXML = new AccessFileXML();
				// shapes = afXML.readXML(path);
			} else if (extension.toLowerCase().equals("json")) {
				JSON json = new JSON();
//				shapes = json.read(path);
				List<Shape> getter = json.read(path);
				shapes = new ArrayList<>();
				for (Shape shape : getter) {
					shapes.add(shape);
				}
			} else {
				throw new RuntimeException("Loading Extension");
			}
			// undo.removeAllElements();
			if (!(shapes == null)) {
				undo.clear();
				redo.clear();
				undo.push( new ArrayList<Shape>());
				undo.push(shapes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String getExtension(String path) {
		if (path == null) {
			return null;
		}
		System.out.println(path);
		String name = path + ".";
		String extension = "";
		String a[] = path.split(".");
		if (path.toLowerCase().endsWith(".xml")) {
			return "xml";
		} else if (path.toLowerCase().endsWith(".json")) {
			return "json";
		}
		System.out.println(a.length);
		System.out.println(a[1]);
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == '.') {
				i++;
				while (name.charAt(i) != '.') {
					extension += name.charAt(i++);
				}
			}
		}
		System.out.println(extension);
		return extension;
	}

	private void createFile(String path) {
		try {
			File file = new File(path);
			file.getParentFile().mkdirs();
			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}

		} catch (Exception e) {

		}
	}

}
