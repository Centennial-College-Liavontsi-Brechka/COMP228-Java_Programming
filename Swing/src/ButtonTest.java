// Creating JButtons.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ButtonTest extends JFrame 
{
	private JButton plainJButton; // button with just text
	private JButton fancyJButton; // button with icons

	// ButtonTest adds JButtons to JFrame
	public ButtonTest()
	{
		super( "Testing Buttons" );
		setLayout( new FlowLayout() ); // set frame layout

		plainJButton = new JButton( "Plain Button" ); // button with text
		add( plainJButton ); // add plainJButton to JFrame

		Icon bug1 = new ImageIcon( getClass().getResource( "leaf.png" ) );
		Icon bug2 = new ImageIcon( getClass().getResource( "leaf2.png" ) );
		fancyJButton = new JButton( "Fancy Button", bug1 ); // set image
		fancyJButton.setRolloverIcon( bug2 ); // set rollover image
		add( fancyJButton ); // add fancyJButton to JFrame

		// create new ButtonHandler for button event handling 
		ButtonHandler handler = new ButtonHandler();
		fancyJButton.addActionListener( handler );

		//register the plain button using an anonymous inner class
		plainJButton.addActionListener( new ActionListener()
		{ //anonymous class
			// handle button event
			public void actionPerformed( ActionEvent event )
			{
				JOptionPane.showMessageDialog( ButtonTest.this, "You pressed: "+ event.getActionCommand() ) ;
			} // end method actionPerformed
		}
				);
	} // end ButtonFrame constructor

	// inner class for button event handling
	private class ButtonHandler implements ActionListener 
	{
		// handle button event
		public void actionPerformed( ActionEvent event )
		{
			JOptionPane.showMessageDialog( ButtonTest.this, "You pressed: "+ event.getActionCommand() ) ;
		} // end method actionPerformed
	} // end private inner class ButtonHandler

	public static void main( String args[] )
	{ 
		ButtonTest buttonFrame = new ButtonTest(); // create ButtonFrame
		buttonFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		buttonFrame.setSize( 275, 160 ); // set frame size
		buttonFrame.setVisible( true ); // display frame
	} // end main
} // end class ButtonTest
