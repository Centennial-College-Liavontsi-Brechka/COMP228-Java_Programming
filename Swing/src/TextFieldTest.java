import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
public class TextFieldTest extends JFrame
{
	private JTextField textField1; // text field with set size
	private JTextField textField2; // text field constructed with text
	private JTextField textField3; // text field with text and size
	private JPasswordField passwordField; // password field with text
	// TextFieldFrame constructor adds JTextFields to JFrame
	public TextFieldTest()
	{
		super( "Testing JTextField and JPasswordField" );
		setLayout( new FlowLayout() ); // set frame layout
		// construct textfield with 10 columns
		textField1 = new JTextField( 10 );
		add( textField1 ); // add textField1 to JFrame
		// construct textfield with default text
		textField2 = new JTextField( "Enter text here" );
		add( textField2 ); // add textField2 to JFrame
		// construct textfield with default text and 21 columns
		textField3 = new JTextField( "Uneditable text field", 21 );
		textField3.setEditable( false ); // disable editing
		add( textField3 ); // add textField3 to JFrame
		// construct passwordfield with default text
		passwordField = new JPasswordField( "Hidden text" );
		add( passwordField ); // add passwordField to JFrame
		// register event handlers
		TextFieldHandler handler = new TextFieldHandler();
		textField1.addActionListener( handler );
		textField2.addActionListener( handler );
		textField3.addActionListener( handler );
		passwordField.addActionListener( handler );
	} // end TextFieldFrame constructor
	// private inner class for event handling
	private class TextFieldHandler implements ActionListener
	{
		// process textfield events
		public void actionPerformed( ActionEvent event )
		{
			String string = ""; // declare string to display
			// user pressed Enter in JTextField textField1
			if ( event.getSource() == textField1 )
				string = "textField1: " + event.getActionCommand() ;
			// user pressed Enter in JTextField textField2
			else if ( event.getSource() == textField2 )
				string = "textField2: "+event.getActionCommand() ;
			// user pressed Enter in JTextField textField3
			else if ( event.getSource() == textField3 )
				string = "textField3: "+event.getActionCommand() ;
			// user pressed Enter in JTextField passwordField
			else if ( event.getSource() == passwordField )
				string = "passwordField: "+ new String(
						passwordField.getPassword() ) ;
			// display JTextField content
			JOptionPane.showMessageDialog( null, string );
		} // end method actionPerformed
	} // end private inner class TextFieldHandler
	public static void main( String args[] )
	{
		TextFieldTest textFieldFrame = new TextFieldTest();
		textFieldFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		textFieldFrame.setSize( 400, 100 ); // set frame size
		//textFieldFrame.pack();
		textFieldFrame.setVisible( true ); // display frame
	} // end main
} // end class TextFieldTest