import javax.swing.*;
import java.util.*;
import java.awt.*;

//Using an ExecutorService to execute Runnables.
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


public class TestMultipleThreads {

	public static void main(String args[])
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize(1000,75);
		//
		frame.setName("Multiple threads");
		frame.setVisible(true);
		//
		TimeZone timeZoneParis = TimeZone.getTimeZone("Europe/Paris");
		TimeZone timeZoneToronto = TimeZone.getDefault();
		//
		try 
		{
			//			 
			Clock clock1 = new Clock(timeZoneToronto,Locale.CANADA);
			Clock clock2 = new Clock(timeZoneParis,Locale.ENGLISH);
			//
			//	 create ExecutorService to manage threads
		      ExecutorService threadExecutor = Executors.newCachedThreadPool();

		     // start threads and place in runnable state
		      threadExecutor.execute( clock1 ); // start clock1
		      threadExecutor.execute( clock2 ); // start clock2
			//Container c = frame.getContentPane();
			frame.setLayout(new GridLayout(1,2,5,5));
			frame.add(clock1);
			frame.add(clock2);
			frame.validate();
			// shut down worker threads when their tasks complete
		    threadExecutor.shutdown(); 

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}
