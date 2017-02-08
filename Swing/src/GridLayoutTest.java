import java.awt.*;
import javax.swing.*;

public class GridLayoutTest extends JFrame {

	public GridLayoutTest() {
		setLayout(new GridLayout(3, 2, 5 , 5));

		add(new Button("Button 1"));
		add(new Button("2"));
		add(new Button("Button 3"));
		add(new Button("Long-Named Button 4"));
		add(new Button("Button 5"));
		add(new Button(""));
	}

	public static void main( String args[] )
	{ 
		GridLayoutTest frame = new GridLayoutTest(); // create ButtonFrame
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize( 500, 100 ); // set frame size
		frame.setVisible( true ); // display frame
	} // end main
}
