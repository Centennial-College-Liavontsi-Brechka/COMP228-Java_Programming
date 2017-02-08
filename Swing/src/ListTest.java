// ListTest.java
// Selecting colors from a JList.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ListTest extends JFrame {
	private JList colorList;
	private Container c;

	private String colorNames[] =
		{ "Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green",
				"Light Gray", "Magenta", "Orange", "Pink", "Red",
				"White", "Yellow" };

	private Color colors[] =
		{ Color.black, Color.blue, Color.cyan, Color.darkGray,
				Color.gray, Color.green, Color.lightGray,
				Color.magenta, Color.orange, Color.pink, Color.red,
				Color.white, Color.yellow };

	public ListTest()
	{
		super( "List Test" );

		c = getContentPane();
		setLayout( new FlowLayout() );

		// create a list with the items in the colorNames array
		colorList = new JList( colorNames );
		colorList.setVisibleRowCount( 5 );

		// do not allow multiple selections
		colorList.setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION );

		// add a JScrollPane containing the JList
		// to the content pane
		add( new JScrollPane( colorList ) );

		// set up event handler
		colorList.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged( ListSelectionEvent e )  
					{
						c.setBackground(
								colors[ colorList.getSelectedIndex() ] );
					}
				}
				);
	}

	public static void main( String args[] )
	{ 
		ListTest app = new ListTest();
		app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		app.setSize( 300, 150 ); // set frame size
		app.setVisible( true ); // display frame
	}
}
