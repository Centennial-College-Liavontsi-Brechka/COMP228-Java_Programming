class EggVote extends Thread
{
	@Override
	public void run()
	{
		for(int i = 0; i < 5; i++)
		{
			try{
				sleep(1000);		//Sleep for 1 s
			}catch(InterruptedException e){}
			
			System.out.println("Egg!");	
		}
		//"Egg" - 5 times
	}
}

public class ChickenVote	//contains main() as well
{
	static EggVote mAnotherOpinion;	//Side thread
	
	public static void main(String[] args)
	{
		mAnotherOpinion = new EggVote();	//Create a thread
		System.out.println("Argument begins...");
		mAnotherOpinion.start(); 			//Run a thread
		
		for(int i = 0; i < 5; i++)
		{
			try{
				Thread.sleep(1000);		//1 s Sleep
			}catch(InterruptedException e){}
			
			System.out.println("Chicken!");
		}
		
		//"Chicken" - 5 times

		if(mAnotherOpinion.isAlive())	//Is your opponent still arguing?
		{
			try{
				mAnotherOpinion.join();	//Wait for your opponent
				//mAnotherOpinion
			}catch(InterruptedException e){}
			
			System.out.println("Egg came first!");
		}
		else	//if you opponent is done arguing
		{
			System.out.println("Chicken came first!");
		}
		System.out.println("The End!");	
	}
}