// Creating radio buttons using ButtonGroup and JRadioButton.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RadioButtonTest extends JFrame {
	private JTextField t;
	private Font plainFont, boldFont,
	italicFont, boldItalicFont;
	private JRadioButton plain, bold, italic, boldItalic;
	private ButtonGroup radioGroup;

	public RadioButtonTest()
	{
		super( "RadioButton Test" );

		Container c = getContentPane();
		setLayout( new FlowLayout() );

		t = new JTextField( "Watch the font style change", 25 );
		add( t ); 

		// Create radio buttons
		plain = new JRadioButton( "Plain", true );
		add( plain );
		bold = new JRadioButton( "Bold", false);
		add( bold );
		italic = new JRadioButton( "Italic", false );
		add( italic );
		boldItalic = new JRadioButton( "Bold/Italic", false );
		add( boldItalic );

		// register events
		RadioButtonHandler handler = new RadioButtonHandler();
		plain.addItemListener( handler );
		bold.addItemListener( handler );
		italic.addItemListener( handler );
		boldItalic.addItemListener( handler );

		// create logical relationship between JRadioButtons
		radioGroup = new ButtonGroup();
		radioGroup.add( plain );
		radioGroup.add( bold );
		radioGroup.add( italic );
		radioGroup.add( boldItalic );

		plainFont = new Font( "TimesRoman", Font.PLAIN, 14 );
		boldFont = new Font( "TimesRoman", Font.BOLD, 14 );
		italicFont = new Font( "TimesRoman", Font.ITALIC, 14 );
		boldItalicFont =
				new Font( "TimesRoman", Font.BOLD + Font.ITALIC, 14 );
		t.setFont( plainFont );

	}


	private class RadioButtonHandler implements ItemListener {
		public void itemStateChanged( ItemEvent e )
		{
			if ( e.getSource() == plain ) 
				t.setFont( plainFont );
			else if ( e.getSource() == bold ) 
				t.setFont( boldFont );
			else if ( e.getSource() == italic ) 
				t.setFont( italicFont );
			else if ( e.getSource() == boldItalic ) 
				t.setFont( boldItalicFont );

			t.repaint();
		}
	}

	public static void main( String args[] )
	{
		RadioButtonTest app = new RadioButtonTest();

		app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		app.setSize( 400, 400 ); // set frame size
		app.setVisible( true ); // display frame
	}

}

