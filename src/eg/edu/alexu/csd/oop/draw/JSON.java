package eg.edu.alexu.csd.oop.draw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.AccessMap.Keys;

public class JSON {
	private PrintWriter writer;
	private AccessMap am;

	private void addJsonElement(String key, String value, boolean hasComma) {
		if (hasComma)
			writer.println("		\"" + key + "\":\"" + value + "\",");
		else
			writer.println("		\"" + key + "\":\"" + value + "\"");
	}

	public void write(String path) {
		try {
			File file = new File (path);
			file.getParentFile().mkdirs();
			writer = new PrintWriter (file);
			Shape[] shapes = MyDrawingEngine.getInstance().getShapes();
			am = new AccessMap();
			List<Map<String, String>> list = am.setAccessMap(shapes);
			// List<Map<String, String>> list =
			// AccessMap.getInstance().setAccessMap(shapes);

			// Fill in the file
			writer.println("{");
			boolean first = true; // Used in writing

			if (shapes == null) {
				// Handle Null Array !
				writer.println("	\"" + Keys.NULL + "\":{}");
				writer.println("}");
				writer.close();
				return;
			} else if ((shapes.length == 0)) {
				// Handle Empty Array
				writer.println("}");
				writer.close();
				return;
			}
			for (Map<String, String> map : list) {
				if (!first)
					writer.println(",");
				first = false;
				writer.println("	\"shape\":{");

				for (Iterator<String> iterator = map.keySet().iterator(); iterator
						.hasNext();) {
					String key = iterator.next();
					addJsonElement(key, map.get(key), iterator.hasNext());
				}
				writer.print("	}");
			}
			writer.println();
			writer.println("}");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Shape> read(String path) {
		List<Shape> shapes = new LinkedList<>();
		String[] elements = getElements(path);
		if (elements[0].equals(Keys.NULL)) {
			return null;
		} else if (elements[0].equals("")) {
			return shapes;
		}
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();

		Map<String, String> map = new HashMap<>();
		for (int i = 1; i < elements.length; i++) {
			if (elements[i].equals("shape")) {
				list.add(map);
				map = new HashMap<>();
				continue;
			}
			map.put(elements[i++], elements[i]);

		}
		AccessMap m = new AccessMap();
		shapes = m.getShapesFromAccessMap(list);
		// shapes = AccessMap.getInstance().getShapesFromAccessMap(list);
		return shapes;
	}

	private String[] getElements(String path) {
		// Getting Elements
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String elements = "";
			String line;
			line = br.readLine();
			while (line != null) {
				elements += line;
				line = br.readLine();
			}
			elements = elements.replace("\"", "");
			elements = elements.replace("{", "");
			elements = elements.replace("}", "");
			elements = elements.replace("	", "");
			elements = elements.replace(":", ",");
			elements += ",shape";
			String[] allElements = elements.split(",");
			br.close();
			return allElements;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("File Path Problem !!");
		}
	}
}
