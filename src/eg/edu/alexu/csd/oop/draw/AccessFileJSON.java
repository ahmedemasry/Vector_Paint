package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//{
//	  "firstName": "John",
//	  "lastName": "Smith",
//	  "isAlive": true,
//	  "age": 25,
//	  "address": {
//	    "streetAddress": "21 2nd Street",
//	    "city": "New York",
//	    "state": "NY",
//	    "postalCode": "10021-3100"
//	  },
//	  "phoneNumbers": [
//	    {
//	      "type": "home",
//	      "number": "212 555-1234"
//	    },
//	    {
//	      "type": "office",
//	      "number": "646 555-4567"
//	    }
//	  ],
//	  "children": [],
//	  "spouse": null
//	}

public class AccessFileJSON {

	private PrintWriter writer;

	private void addJsonElement(String key, String value, boolean hasComma) {
		if (hasComma)
			writer.println("		\"" + key + "\":\"" + value + "\",");
		else
			writer.println("		\"" + key + "\":\"" + value + "\"");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void writeJSON(String path) {
		try {
			writer = new PrintWriter(path, "UTF-8");
			Shape[] shapes = MyDrawingEngine.getInstance().getShapes();
			boolean first = true;
			writer.println("{");
			if (shapes == null) {
				writer.println("}");
				writer.close();
				return;
			} else {
				for (Shape shape : shapes) {
					if (shape == null) {
						if (!first)
							writer.println(",");
						first = false;
						
						writer.println("	\"shape\":{");
						addJsonElement("type", "null", false);
						writer.print("	}");
					} else {
						try {
							// Get Shape Type
							Class<? extends Shape> s = shape.getClass();
							String className = s.getName();

							if (!first)
								writer.println(",");
							first = false;
							writer.println("	\"shape\":{");
							addJsonElement("type", className, true);
							// Adding Position
							Point p = shape.getPosition();
							if (p == null) {
								addJsonElement("positionX", "null", true);
								addJsonElement("positionY", "null", true);
							} else {
								addJsonElement("positionX",
										Double.toString(p.getX()), true);
								addJsonElement("positionY",
										Double.toString(p.getY()), true);
							}
							// Adding Colors
							Color color = shape.getColor();
							String str = Integer
									.toHexString(((Color) validate(color))
											.getRGB());
							addJsonElement("color", str, true);

							color = shape.getFillColor();
							if (!(color == null))
								str = Integer.toHexString((color).getRGB());
							else
								str = "null";
							addJsonElement("fillColor", str, true);
							// Adding properties map
							Map properties = shape.getProperties();
							if (properties == null) {
								addJsonElement("properties", "null", true);
							} else {
								for (Iterator<String> iterator = properties
										.keySet().iterator(); iterator
										.hasNext();) {
									String key = iterator.next();
									String value = "";
									if (properties.get(key) == null) {
										value = "null";
									} else {
										value = properties.get(key).toString();
									}
									addJsonElement(key, value,
											iterator.hasNext());
								}
							}
							writer.print("	}");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			writer.println();
			writer.println("}");
			writer.close();
		} catch (FileNotFoundException e) {
		} catch (UnsupportedEncodingException e) {
		}

	}

	@SuppressWarnings({ "resource", "rawtypes", "unchecked" })
	protected ArrayList<Shape> readJSON(String path) {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		try {
			// Getting Elements
			BufferedReader br = new BufferedReader(new FileReader(path));
			String elements = "";
			String line;
			line = br.readLine();
			while (line != null) {
				elements += line;
				line = br.readLine();
			}//
			elements = elements.replace("\"", "");
			elements = elements.replace("{", "");
			elements = elements.replace("}", "");
			elements = elements.replace("	", "");
			elements = elements.replace(":", ",");
			elements += ",shape";

			String[] allElements = elements.split(",");
			if (allElements[0].equals("")) {
				System.out.println("NULL");
				return null;
			}
			int c = 0;
			for (String string : allElements) {
				System.out.println((c++) + " : " + string);
			}
			for (int i = 1; i < allElements.length; i++) {
				// Declarations
				i--;
				String shapeType = "";
				Point position = new Point();
				Map<String, Double> map = new HashMap();

				i += 2;
				shapeType = allElements[i];
				System.out.println("Type : " + allElements[i]);
				if(shapeType.equals("null")){
					shapes.add(null);
					i+= 1;
					continue;
				}

				i += 2;
				if (allElements[i].equals("null")) {
					position = null;
				} else {
					position.setLocation(Double.parseDouble(allElements[i]),
							Double.parseDouble(allElements[i + 2]));
					System.out.println("position : " + allElements[i] + " , "
							+ allElements[i + 2]);
				}
				i += 4;

				System.out.println("color : " + allElements[i] + "  " + i);
				String ss = (String) validate(allElements[i]);
				Color color;
				if ((ss == null)) {
					color = null;
				} else {
					color = getColorFromString((String) validate(allElements[i]));
				}

				i += 2;
				ss = (String) validate(allElements[i]);
				System.out.println("fillColor : " + allElements[i]);
				Color fillColor;
				if ((ss == null)) {
					fillColor = null;
				} else {
					fillColor = getColorFromString((String) validate(allElements[i]));
				}

				i += 1;
				if (allElements[i].equals("properties")) {
					map = null;
					i += 3;
				} else {
					while (!allElements[i].equals("shape")) {
						if (!allElements[i + 1].equals("null")) {
							map.put(allElements[i++],
									Double.parseDouble(allElements[i++]));
						} else {
							map.put(allElements[i++], null);
							i++;
						}
						System.out.println(allElements[i - 2] + " : "
								+ allElements[i - 1]);
					}
				}
				System.out.println("---------------------------------");

				// Add
				// Shape----------------------------------------------------------------
				Factory factory = new Factory();
				Shape shape = factory.create(shapeType);

				shape.setPosition(position);
				shape.setProperties(map);

				shape.setColor(color);
				shape.setFillColor(fillColor);

				shapes.add(shape);
			}
			return shapes;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception!!!!!!!!!!!!!");
		}
		return null;
	}

	private Color getColorFromString(String str) {
		int k = 2;
		if (str == null) {
			return null;
		}
		if (str.equals("null"))
			return null;
		Color color = new Color(Integer.valueOf(str.substring(k, k + 2), 16),
				Integer.valueOf(str.substring(k + 2, k + 4), 16),
				Integer.valueOf(str.substring(k + 4, k + 6), 16));
		return color;
	}

	private Object validate(Object s) {
		if (s == null) {
			return "null";
		} else if (s.equals("null")) {
			return null;
		} else {
			return s;
		}
	}
}
