import javax.swing.JFrame;
public class MyFrame extends JFrame {
	public static void main( String args[] )
	{
		MyFrame mf = new MyFrame(); // create ButtonFrame
		mf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mf.setSize( 400, 400 ); // set frame size
		mf.setVisible( true ); // display frame
	} 
}