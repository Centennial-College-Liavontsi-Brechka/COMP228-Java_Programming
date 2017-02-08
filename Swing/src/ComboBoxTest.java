// ComboBoxTest.java
// Using a JComboBox to select an image to display.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComboBoxTest extends JFrame {
	private JComboBox<String> images;
	private JLabel label;
	private String names[] =
		{ "Leaf.png", "Leaf2.png",
				"Leaf3.png", "Leaf4.png" };
	private Icon icons[] =
		{ new ImageIcon( getClass().getResource(names[ 0 ])), //located where class is
				new ImageIcon( getClass().getResource(names[ 1 ]) ), //located in project directory
				new ImageIcon(getClass().getResource(names[ 2 ])),
				new ImageIcon( getClass().getResource(names[ 3 ]) ) };

	public ComboBoxTest()
	{
		super( "Testing JComboBox" );

		//Container c = getContentPane();
		setLayout( new FlowLayout() );      

		images = new JComboBox<String>( names );
		images.setMaximumRowCount( 3 );
		//images.setEditable(true);

		images.addItemListener(
				new ItemListener() {
					public void itemStateChanged( ItemEvent e )
					{
						label.setIcon(
								icons[ images.getSelectedIndex() ] );
					}
				}
				);

		add( images );

		label = new JLabel( icons[ 0 ] );
		add( label );

	}

	public static void main( String args[] )
	{ 
		ComboBoxTest app = new ComboBoxTest();

		app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		app.setSize( 350, 100 ); // set frame size
		app.setVisible( true ); // display frame
	}
}
