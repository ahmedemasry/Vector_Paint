package eg.edu.alexu.csd.oop.draw;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1365, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 10, 1339, 60);
		frame.getContentPane().add(panel);
		
		Button button_1 = new Button("New button");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog d = new JDialog();
				d.setBounds(100, 100, 100, 200);
				d.setVisible(true);
			}
		});
		panel.add(button_1);
		
		//this for loop must be for class that implements Shape interface
//		for (Class clazz: commandClasses) {
//		}
		Package p = Factory.class.getPackage();
		Class<?>[] classes = null;
//		Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
//		try{
//			System.out.println(p.toString());
//			classes = getClasses(p.toString());
//		}catch(Exception e){}

		
		for (Class<?> class1 : classes) {
			Class[] intfs = class1.getInterfaces();
			if(Shape.class.isAssignableFrom(class1))
			for (Class i : intfs) {
				System.out.println(i.toString());
//				if (i.toString().equals(Shape.toString()) {
//				}
			}
		}
			    

	    ClassLoader classLoader = this.getClass().getClassLoader();
//	    List<Class> classes2 = 
	    
		for(int i = 0 ; i < 5 ; i++ ){
			Button button = new Button("New button"+i);
			panel.add(button);
		}
		
		Canvas canvas = new Canvas();
		canvas.setBounds(10, 76, 1339, 665);
		frame.getContentPane().add(canvas);
		canvas.setBackground(Color.WHITE);

	}
	
	
	
	
	
	
	public static Class[] getClasses(String pckgname)
            throws ClassNotFoundException {
        ArrayList<Class> classes = new ArrayList<Class>();
        // Get a File object for the package
        File directory = null;
        try {
            ClassLoader cld = Thread.currentThread().getContextClassLoader();
            if (cld == null) {
                throw new ClassNotFoundException("Can't get class loader.");
            }
            String path = '/' + pckgname.replace('.', '/');
            URL resource = cld.getResource(path);
            if (resource == null) {
                throw new ClassNotFoundException("No resource for " + path);
            }
            directory = new File(resource.getFile());
        } catch (NullPointerException x) {
            throw new ClassNotFoundException(pckgname + " (" + directory
                    + ") does not appear to be a valid package");
        }
        if (directory.exists()) {
            // Get the list of the files contained in the package
            String[] files = directory.list();
            for (int i = 0; i < files.length; i++) {
                // we are only interested in .class files
                if (files[i].endsWith(".class")) {
                    // removes the .class extension
                    classes.add(Class.forName(pckgname + '.'
                            + files[i].substring(0, files[i].length() - 6)));
                }
            }
        } else {
            throw new ClassNotFoundException(pckgname
                    + " does not appear to be a valid package");
        }
        Class[] classesA = new Class[classes.size()];
        classes.toArray(classesA);
        return classesA;
    }
	
}


