import javax.swing.*;
import java.awt.*;

public class FlowLayoutTest extends JFrame {

	public FlowLayoutTest() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		add(new Button("Button 1"));
		add(new Button("2"));
		add(new Button("Button 3"));
		add(new Button("Long-Named Button 4"));
		add(new Button("Button 5"));
	}
	
	public static void main( String args[] )
	{ 
		FlowLayoutTest frame = new FlowLayoutTest(); //create frame
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize( 500, 100 ); // set frame size
		frame.setVisible( true ); // display frame
	} // end main
}
