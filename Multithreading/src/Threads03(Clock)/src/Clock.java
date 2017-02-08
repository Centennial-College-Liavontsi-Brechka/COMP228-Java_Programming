import java.awt.*; 
import javax.swing.*; 
import java.text.DateFormat;

import java.util.*; 

public class Clock extends JPanel implements Runnable 
{ 
	private TimeZone timeZone;
    private TextField display; 
    // Date will give us the current date and time 
    private Date date; 
	private DateFormat timeFormat;
         
    public  Clock(TimeZone timeZone,Locale locale)
    { 
    	this.timeZone=timeZone;
		// Get a date time formatter for display in locale.
		timeFormat=DateFormat.getDateTimeInstance(
			DateFormat.FULL,
			DateFormat.FULL,
			locale);
		timeFormat.setTimeZone(timeZone);
      	// a grid layout to place one textfield 
        setLayout(new GridLayout(1,1)); 
        display = new TextField();
        display.setFont(new Font("Arial",Font.BOLD,14)); 
        add(display); 
                   
    } 
  
    // The working method for the thread. 
    public void run() 
    { 
     	// loop indefinitely 
        while (true) 
        { 
        	
			display.setText(timeZone.getID()+": "
					+timeFormat.format(new Date()));
           	System.out.println(Thread.currentThread().getName());
           	try 
           	{  
        		Thread.sleep(1000); 
            } 
            catch (InterruptedException e) 
            { 
            	System.out.println(e); 
            } 
                
        } //while 
    } //run
	
	  
} //Clock
