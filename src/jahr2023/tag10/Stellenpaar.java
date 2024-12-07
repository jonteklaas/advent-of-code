package jahr2023.tag10;

public class Stellenpaar
{
/*
	F-7
	|.|
	L-J
*/
	private Stelle stelle1;
	private Stelle stelle2;

	public Stellenpaar(Stelle stelle1, Stelle stelle2)
	{
		this.stelle1 = stelle1;
		this.stelle2 = stelle2;
	}

	public Stelle getStelle1()
	{
		return stelle1;
	}

	public Stelle getStelle2()
	{
		return stelle2;
	}
}
