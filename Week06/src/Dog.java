public class Dog extends Object{
	private int size;
	private int energy;

	// creates an "average" dog
	public Dog()
	{  this(5, 5); }

	public Dog(int size, int energy)
	{  this.setSize(size);  this.setEnergy(energy);  }

	public int getSize()
	{ return this.size; }

	public int getEnergy()
	{ return this.energy; }

	public void setSize(int size)
	{ this.size = size; }

	public final void setEnergy(int energy)
	{ this.energy = energy; }

	@Override
	public String toString()
	{
		String s = "size " + this.getSize() +
				" energy " + this.getEnergy();
		//return s;
		return super.toString();
	}
}
