import java.util.Random;

public class DepositTransaction implements Runnable{
	private Account account;
	public DepositTransaction( Account account )
	{
	
	   this.account=account;
	   
	} // 

	// method run contains the code that a thread will execute
	public void run()
	{
	   account.deposit(100.0);
	   
	} // end method run
}


class Account { 
  private double balance; 
  public Account(double initialDeposit) { 
    balance = initialDeposit; 
  } 
  public synchronized  double getBalance() { 
    return balance; 
  } 
  public synchronized  void deposit(double amount) { 
    balance += amount;
    System.out.println("After deposit balance is: "+balance);
  } 
  public synchronized void withdraw(double amount) { 
	  if ( balance >= amount ) { balance -= amount; }
  } 
}


