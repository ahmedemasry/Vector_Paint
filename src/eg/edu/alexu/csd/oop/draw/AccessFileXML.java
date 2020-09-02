package eg.edu.alexu.csd.oop.draw;


public class AccessFileXML {
//
//	private static AccessFileXML instance;
//
//	private AccessFileXML() {
//	}
//
//	public static AccessFileXML getInstance() {
//		if (instance == null)
//			instance = new AccessFileXML();
//		return instance;
//	}
//
//	//
//	protected void writeXML(String path) {
//		Shape[] shapes = MyDrawingEngine.getInstance().getShapes();
//		Document doc = null;
//		try {
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
//					.newInstance();
//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			// root element
//			doc = dBuilder.newDocument();
//			Element rootElement = doc.createElement("shapes");
//			doc.appendChild(rootElement);
//
//			// For each shape , Add a new tag
//			int count = 1;
//			System.out.println(shapes.length);
//			for (Shape shape : shapes) {
//				// Get Shape Type
//				String className = shape.getClass().getName();
//				System.out.println("Class Name : " + className);
//
//				Element tagShape = doc.createElement("shape");
//				tagShape.setAttribute("type", "" + className);
//
//				// Adding position
//				Point position = shape.getPosition();
//				Element tagPosition = doc.createElement("position");
//				String xPosition = Integer.toString(position.x);
//				String yPosition = Integer.toString(position.y);
//				tagPosition.setAttribute("x", xPosition);
//				tagPosition.setAttribute("y", yPosition);
//				System.out.println("Position to save : " + xPosition + " , "
//						+ yPosition);
//				tagShape.appendChild(tagPosition);
//
//				// Adding Colors
//				Element tagColor = doc.createElement("Colors");
//
//				Color color = shape.getColor();
//				String str = "";
//				if (color == null)
//					str = "null";
//				else
//					str = Integer.toHexString(color.getRGB());
//				tagColor.setAttribute("borderColor", str);
//
//				color = shape.getFillColor();
//				if (color == null)
//					str = "null";
//				else
//					str = Integer.toHexString(color.getRGB());
//				tagColor.setAttribute("fillColor", str);
//				tagShape.appendChild(tagColor);
//
//				// For each key , add an attribute
//				Map<String, Double> properties = shape.getProperties();
//				Element tagKey = doc.createElement("properties");
//
//				for (Iterator<String> iterator = properties.keySet().iterator(); iterator
//						.hasNext();) {
//					String key = iterator.next();
//					String value = "";
//					if (properties.get(key) == null) {
//						value = "null";
//					} else {
//						value = properties.get(key).toString();
//					}
//					tagKey.setAttribute(key, value);
//					tagShape.appendChild(tagKey);
//
//					System.out.println("(saving) " + key + " : " + value);
//				}
//				System.out.println("Heeeeeeeeeeeeereeeeeeeeee!!!!!___________");
//				rootElement.appendChild(tagShape);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("EXCEPTION !!!!!!!!!!!!!!!!!!!!!!!!");
//		}
//		try {
//			// Saving XML File
//			TransformerFactory transformerFactory = TransformerFactory
//					.newInstance();
//			Transformer transformer = transformerFactory.newTransformer();
//			DOMSource source = new DOMSource(doc);
//			StreamResult result = new StreamResult(new File(path));
//			transformer.transform(source, result);
//			System.out.println("Saved Successfully");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//	}
//
//	protected LinkedList<Shape> readXML(String path) {
//		try {
//			LinkedList<Shape> shapesList = new LinkedList<Shape>();
//
//			// Reading
//			// file------------------------------------------------------------------
//			File inputFile = new File(path);
//			DocumentBuilderFactory docFactory = DocumentBuilderFactory
//					.newInstance();
//			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//			Document doc = docBuilder.parse(inputFile);
//
//			doc.getDocumentElement().normalize();
//			String sRoot = doc.getDocumentElement().getNodeName();
//			System.out.println("Root element :"
//					+ doc.getDocumentElement().getNodeName());
//			// -----------------------------------------------------------------------------
//
//			// For each shape , Collect data
//			// -----------------------------------------------
//			NodeList nList = doc.getElementsByTagName("shape");
//
//			for (int temp = 0; temp < nList.getLength(); temp++) {
//
//				Node nNode = nList.item(temp);
//				System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element eElement = (Element) nNode;
//
//					// Get Shape
//					// Type-------------------------------------------------------
//
//					String shapeTypeClass = eElement.getAttribute("type");
//					Factory factory = new Factory();
//					Shape shape = factory.create(shapeTypeClass);
//
//					// Getting
//					// Position--------------------------------------------------------
//					NodeList nL = eElement.getElementsByTagName("position");
//					Node nPosition = nL.item(0);
//					System.out.println("\nCurrent Element :"
//							+ nPosition.getNodeName());
//					Element position = (Element) nPosition;
//					Point positionP = new Point(Integer.parseInt(position
//							.getAttribute("x")), Integer.parseInt(position
//							.getAttribute("y")));
//					System.out.println("Here ---------------------------- ");
//					System.out.println("x : " + positionP.getX() + " |  y : "
//							+ positionP.getY());
//					// Getting
//					// Colors----------------------------------------------------------
//					nL = eElement.getElementsByTagName("Colors");
//					Node nColors = nL.item(0);
//					Element colors = (Element) nColors;
//					String borderColor = colors.getAttribute("borderColor");
//					String sfillColor = colors.getAttribute("fillColor");
//					Color color = null;
//					Color fillColor = null;
//					if (!borderColor.equals("null"))
//						color = getColorFromString(borderColor);
//					if (!sfillColor.equals("null"))
//						fillColor = getColorFromString(sfillColor);
//
//					// System.out.println("color : " + color.toString());
//					// System.out.println("fillColor : " +
//					// fillColor.toString());
//
//					// Getting
//					// Properties--------------------------------------------------------
//					nL = eElement.getElementsByTagName("properties");
//					Node nProperties = nL.item(0);
//
//					NamedNodeMap propertiesMap = ((Element) nProperties)
//							.getAttributes();
//
//					Map<String, Double> properties = new HashMap();
//					for (int j = 0; j < propertiesMap.getLength(); j++) {
//						String key = propertiesMap.item(j).getNodeName();
//						if (!propertiesMap.item(j).getNodeValue()
//								.equals("null")) {
//							Double value = Double.valueOf(propertiesMap.item(j)
//									.getNodeValue());
//							properties.put(key, value);
//						} else {
//							properties.put(key, null);
//						}
//						System.out.println("Attribute: "
//								+ propertiesMap.item(j).getNodeValue());
//					}
//
//					// -------------------------------------------------------------
//					// return shape;
//					shape.setPosition(positionP);
//					shape.setProperties((Map<String, Double>) properties);
//					shape.setColor(color);
//					shape.setFillColor(fillColor);
//					shapesList.add(shape);
//				}
//			}
//			for (Shape shape : shapesList) {
//				System.out.println(shape.getProperties().toString());
//			}
//			return shapesList;
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Exception");
//			return null;
//
//		}
//	}
//
//	private Color getColorFromString(String str) {
//		int k = 2;
//		String colorStr = str;
//		Color color = new Color(Integer.valueOf(colorStr.substring(k, k + 2),
//				16), Integer.valueOf(colorStr.substring(k + 2, k + 4), 16),
//				Integer.valueOf(colorStr.substring(k + 4, k + 6), 16));
//		return color;
//	}
}