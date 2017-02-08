public class PureBreed extends Dog {
	private String breed;
	public PureBreed ()
	{ // call to a Mix constructor
		this(5, 5); 
	}

	public PureBreed(int size, int energy)
	{ // call to a Mix constructor
		this(size, energy, "Unknown");
	}

	public PureBreed(int size, int energy,
			String breed)
	{ // call to a Dog constructor
		super(size, energy);
		this.breed = breed;
	}

	@Override public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append(super.toString());
		b.append(" " + breed);
		return b.toString();
	}

}
