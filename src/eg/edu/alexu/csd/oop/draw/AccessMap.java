package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccessMap {
//	static public AccessMap instance;
//
//	static public AccessMap getInstance() {
//		if (instance == null) {
//			instance = new AccessMap();
//		}
//		return instance;
//	}
//
//	private AccessMap() {}

	//A Class To Store String Keys to be easier to use 
	public class Keys {
		final public static String NULL = "null";
		final public static String NOT_NULL = "notNull";
		final public static String TYPE = "type";
		final public static String POSITION_X = "positionX";
		final public static String POSITION_Y = "positionY";
		final public static String COLOR = "color";
		final public static String FILL_COLOR = "fillColor";
		final public static String PROPERTIES = "properties";
	}

	//Set A Map<String,String> for each shape
	public  List<Map<String, String>> setAccessMap(Shape[] shapes) {
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();
		for (Shape shape : shapes) {
			Map<String, String> map = new HashMap<String, String>();
			
			if (shape == null) {
				map.put(Keys.TYPE, Keys.NULL);
			} else {
				Class<? extends Shape> s = shape.getClass();
				String className = s.getName();
				map.put(Keys.TYPE, className);
				
				if (shape.getPosition() == null) {
					map.put(Keys.POSITION_X, Keys.NULL);
					map.put(Keys.POSITION_Y, Keys.NULL);
				} else {
					map.put(Keys.POSITION_X,
							Double.toString(shape.getPosition().getX()));
					map.put(Keys.POSITION_Y,
							Double.toString(shape.getPosition().getY()));
				}

				if (shape.getColor() == null) {
					map.put(Keys.COLOR, Keys.NULL);
				} else {
					map.put(Keys.COLOR, StringFromColor(shape.getColor()));
				}

				if (shape.getFillColor() == null) {
					map.put(Keys.FILL_COLOR, Keys.NULL);
				} else {
					map.put(Keys.FILL_COLOR,
							StringFromColor(shape.getFillColor()));
				}

				if (shape.getProperties() == null) {
					map.put(Keys.PROPERTIES, Keys.NULL);
				} else {
					map.put(Keys.PROPERTIES, Keys.NOT_NULL);
					Map<String, Double> propertiesMap = shape.getProperties();
					for (Iterator<String> iterator = propertiesMap.keySet()
							.iterator(); iterator.hasNext();) {
						String key = iterator.next();
						if(propertiesMap.get(key) == null){
							map.put(key, Keys.NULL);
						}else{							
							map.put(key, Double.toString(propertiesMap.get(key)));
						}
					}
				}
			}
			list.add(map);
		}
		
		return list;
	}

	//Get Shapes from AccessMap by extracting Strings 
	//and converting them into the required data 
	public  List<Shape> getShapesFromAccessMap(List<Map<String, String>> list){
		List<Shape> shapes = new LinkedList<>();
		System.out.println(list.size());
		for (Map<String, String> map : list) {
			if(map.get(Keys.TYPE).equals(Keys.NULL)){
				shapes.add(null);
			}else{
				Factory factory = new Factory();
				Shape shape = factory.create(map.get(Keys.TYPE));
				
				if(map.get(Keys.POSITION_X) == null){
					shape.setPosition(null);
				}else if(map.get(Keys.POSITION_X).equals(Keys.NULL)){
					shape.setPosition(null);
				}else{
					Double x = Double.parseDouble(map.get(Keys.POSITION_X));
					Double y = Double.parseDouble(map.get(Keys.POSITION_Y));
					Point p = new Point();
					p.setLocation(x, y);
					shape.setPosition(p);
				}
				
				if(map.get(Keys.COLOR) == null){
					shape.setColor(null);
				}else if(map.get(Keys.COLOR).equals(Keys.NULL)){
					shape.setColor(null);
				}else{
					shape.setColor(ColorFromString(map.get(Keys.COLOR)));
				}
				
				if(map.get(Keys.FILL_COLOR) == null){
					shape.setFillColor(null);
				}else if(map.get(Keys.FILL_COLOR).equals(Keys.NULL)){
					shape.setFillColor(null);
				}else{
					shape.setFillColor(ColorFromString(map.get(Keys.FILL_COLOR)));
				}
				
				if((map.get(Keys.PROPERTIES) == null)){
					shape.setProperties(null);
				}else if(map.get(Keys.PROPERTIES).equals(Keys.NULL)){
					shape.setProperties(null);
				}else{
					Set<String> keys = map.keySet();
					Map<String, Double> properites = new HashMap<>();
					
					for (String key : keys) {
						if(isAProperty(key)){
							if(map.get(key).equals(Keys.NULL)){
								properites.put(key, null);
							}else{
								Double value = Double.parseDouble(map.get(key));
								properites.put(key, value);
							}
						}
					}
					shape.setProperties(properites);
				}
				
				shapes.add(shape);
			}
		}
		
//		Shape[] shapesArray = (Shape[]) shapes.toArray();
		return shapes;
	}
	
	//To check wither this key is a property key
	private  boolean isAProperty(String str){
		if(		(str.equals(Keys.COLOR)) || (str.equals(Keys.FILL_COLOR))
				||(str.equals(Keys.NULL)) || (str.equals(Keys.NOT_NULL))
				||(str.equals(Keys.POSITION_X)) || (str.equals(Keys.POSITION_Y))
				||(str.equals(Keys.PROPERTIES)) || (str.equals(Keys.TYPE))){
			return false;
		}
		return true;
	}
	 //Converting HexString into a Color 
	private  Color ColorFromString(String str) {
		if(str == null)return null;
		int k = 2;
		Color color = new Color(Integer.valueOf(str.substring(k, k + 2), 16),
				Integer.valueOf(str.substring(k + 2, k + 4), 16),
				Integer.valueOf(str.substring(k + 4, k + 6), 16));
		return color;
	}
	
	//Converting Color into HexString
	private  String StringFromColor(Color color) {
		String str = Integer.toHexString(((color)).getRGB());
		return str;
	}

}
