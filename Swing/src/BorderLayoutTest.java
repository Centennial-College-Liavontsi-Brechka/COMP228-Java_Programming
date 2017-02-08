import java.awt.*;
import javax.swing.*;

public class BorderLayoutTest extends JFrame {

	public BorderLayoutTest() {
		setLayout(new BorderLayout());
		add("North", new Button("Button 1 (North)"));
		add("West", new Button("Button 2 (West)"));
		add("Center", new Button("Long-Named Button 3 (Center)"));
		add("East", new Button("Button 4 (East)"));
		add(new Button("5 South"), BorderLayout.SOUTH);
	}
	public static void main( String args[] )
	{ 
		BorderLayoutTest frame = new BorderLayoutTest(); // create frame
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize( 500, 100 ); // set frame size
		frame.setVisible( true ); // display frame
	} // end main
}

