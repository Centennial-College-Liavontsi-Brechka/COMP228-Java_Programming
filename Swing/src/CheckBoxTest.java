// CheckBoxTest.java
// Creating Checkbox buttons.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckBoxTest extends JFrame {
	private JTextField t;
	private JCheckBox bold, italic;

	public CheckBoxTest()
	{
		super( "JCheckBox Test" );
		Container c = getContentPane();
		setLayout(new FlowLayout());

		t = new JTextField( "Watch the font style change", 20 );
		t.setFont( new Font( "TimesRoman", Font.PLAIN, 14 ) );
		add( t );

		// create checkbox objects
		bold = new JCheckBox( "Bold" );
		add( bold );     

		italic = new JCheckBox( "Italic" );
		add( italic );

		CheckBoxHandler handler = new CheckBoxHandler();
		bold.addItemListener( handler );
		italic.addItemListener( handler );

	}

	private class CheckBoxHandler implements ItemListener {
		private int valBold = Font.PLAIN;
		private int valItalic = Font.PLAIN;

		public void itemStateChanged( ItemEvent e )
		{
			if ( e.getSource() == bold )
				if ( e.getStateChange() == ItemEvent.SELECTED )
					valBold = Font.BOLD;
				else
					valBold = Font.PLAIN;

			if ( e.getSource() == italic )
				if ( e.getStateChange() == ItemEvent.SELECTED )
					valItalic = Font.ITALIC;
				else
					valItalic = Font.PLAIN;

			t.setFont(
					new Font( "TimesRoman", valBold + valItalic, 14 ) );
			t.repaint();
		}
	}

	public static void main( String args[] )
	{ 
		CheckBoxTest app = new CheckBoxTest();
		app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		app.setSize( 275, 100 ); // set frame size
		app.setVisible( true ); // display frame

	}


}
