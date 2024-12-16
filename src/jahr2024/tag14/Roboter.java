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
		System.out.println(
			"alte Position: " + positionX + " " + positionY + "; Geschwindigkeit: " + geschwindigkeitX + " "
				+ geschwindigkeitY);
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
		System.out.println(
			"neue Position: " + positionX + " " + positionY + "; Geschwindigkeit: " + geschwindigkeitX + " "
				+ geschwindigkeitY);
	}

	//	1|2
	//	-+-
	//	3|4
	public int ermittleQuadrant()
	{
		if (positionX == (BREITE + 1) / 2 || positionY == (HOEHE + 1) / 2)
		{
			System.out.println("Quadrant 0 für " + positionX + " " + positionY);
			return 0;
		}
		if (positionX <= BREITE / 2)
		{
			if (positionY <= HOEHE / 2)
			{
			System.out.println("Quadrant 1 für " + positionX + " " + positionY);
				return 1;
			}
			System.out.println("Quadrant 3 für " + positionX + " " + positionY);
			return 3;
		}
		if (positionY <= HOEHE / 2)
		{
			System.out.println("Quadrant 2 für " + positionX + " " + positionY);
			return 2;
		}
			System.out.println("Quadrant 4 für " + positionX + " " + positionY);
		return 4;
	}
}
