import java.util.ArrayList;

public class Mix extends Dog {
	// no declaration of size or energy; inherited from Dog
	private ArrayList<String> breeds;

	public Mix ()
	{ // call to a Mix constructor
		this(5, 5); 
	}

	public Mix(int size, int energy)
	{ // call to a Mix constructor
		this(size, energy, new ArrayList<String>());
	}

	public Mix(int size, int energy,
			ArrayList<String> breeds)
	{ // call to a Dog constructor
		super(size, energy);
		this.breeds = new ArrayList<String>(breeds);
	}
	

}