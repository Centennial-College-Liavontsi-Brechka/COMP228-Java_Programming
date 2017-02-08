import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class AccountTest
{
	public static void main( String[] args )
	{
		Account a = new Account(150);
		// create and name each runnable
		DepositTransaction trans1 = new DepositTransaction(a);
		DepositTransaction trans2 = new DepositTransaction(a);
		
		
		// create ExecutorService to manage threads
		ExecutorService threadExecutor = Executors.newCachedThreadPool();
		
		// start threads and place in runnable state
		threadExecutor.execute( trans1 ); // deposit	
		threadExecutor.execute( trans2 ); // deposit
		 
		// shut down worker threads when their tasks complete
		threadExecutor.shutdown(); 
		
		System.out.println( "Tasks started, main ends.\n" );
	} // end main
} // end class TaskExecutor
