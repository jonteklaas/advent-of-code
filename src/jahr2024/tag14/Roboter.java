package jahr2024.tag14;

public class Roboter
{
	private final int BREITE =  101;
	private final int HOEHE = 103;
	private int positionX;
	private int positionY;
	private int geschwindigkeitX;
	private int geschwindigkeitY;

	public Roboter(int positionX, int positionY, int geschwindigkeitX, int geschwindigkeitY)
	{
		this.positionX = positionX;
		this.positionY = positionY;
		this.geschwindigkeitX = geschwindigkeitX;
		this.geschwindigkeitY = geschwindigkeitY;
	}

	public void bewege()
	{
		positionX += geschwindigkeitX;
		positionY += geschwindigkeitY;
		while (positionX <= 0)
		{
			positionX += BREITE;
		}
		if (positionX > BREITE)
		{
			positionX %= BREITE;
		}
		while (positionY <= 0)
		{
			positionY += HOEHE;
		}
		if (positionY > HOEHE)
		{
			positionY %= HOEHE;
		}
	}

	//	1|2
	//	-+-
	//	3|4
	public int ermittleQuadrant()
	{
		if (positionX == (BREITE + 1) / 2 || positionY == (HOEHE + 1) / 2)
		{
			return 0;
		}
		if (positionX <= BREITE / 2)
		{
			if (positionY <= HOEHE / 2)
			{
				return 1;
			}
			return 3;
		}
		if (positionY <= HOEHE / 2)
		{
			return 2;
		}
		return 4;
	}
}
