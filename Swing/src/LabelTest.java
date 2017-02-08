// Demonstrating the JLabel class.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LabelTest extends JFrame {
	private JLabel label1; // JLabel with just text
	private JLabel label2; // JLabel constructed with text and icon
	private JLabel label3; // JLabel with added text and icon
	private JPanel panel;
	private JPanel panel2;
	// LabelText constructor adds JLabels to JFrame
	public LabelTest()
	{
		super( "Testing JLabel" );
		setLayout( new FlowLayout() ); // set frame layout
		// JLabel constructor with a string argument
		label1 = new JLabel( "Label with text" );
		label1.setToolTipText( "This is label1" );
		add( label1 ); // add label1 to JFrame
		//JLabel constructor with string, Icon and alignment arguments
		Icon leaf = new ImageIcon( getClass().getResource( "leaf.png" ) );
		label2 = new JLabel( "Label with text and icon", leaf,
				SwingConstants.LEFT );
		label2.setToolTipText( "This is label2" );
		add( label2 ); // add label2 to JFrame
		label3 = new JLabel(); // JLabel constructor no arguments
		label3.setText( "Label with icon and text at bottom" );
		label3.setIcon( leaf); // add icon to JLabel
		label3.setHorizontalTextPosition( SwingConstants.CENTER );
		label3.setVerticalTextPosition( SwingConstants.BOTTOM );
		label3.setToolTipText( "This is label3" );
		add( label3 ); // add label3 to JFrame

		panel = new JPanel();
		panel.setLayout( new BoxLayout(panel, BoxLayout.Y_AXIS) );
		panel2 = new JPanel();
		panel2.setLayout( new BoxLayout(panel2, BoxLayout.Y_AXIS) );
		panel.add(new JLabel ("Label 01"));
		panel.add(new JLabel ("Label 02"));
		panel.add(new JLabel ("Label 03"));
		panel2.add(new JLabel ("Label 04"));
		panel2.add(new JLabel ("Label 05"));
		panel2.add(new JLabel ("Label 06"));
		add(panel);
		add(panel2);
	} // end LabelText constructor
	public static void main( String args[] )
	{
		LabelTest labelFrame = new LabelTest(); // create LabelFrame
		labelFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		labelFrame.setSize( 275, 200 ); // set frame size
		labelFrame.setVisible( true ); // display frame
	}
}