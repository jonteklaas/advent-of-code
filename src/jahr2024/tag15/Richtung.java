package jahr2024.tag15;

public enum Richtung
{
	OBEN('^'),
	RECHTS('>'),
	UNTEN('v'),
	LINKS('<');

	public final char zeichen;

	Richtung(char zeichen)
	{
		this.zeichen = zeichen;
	}

	public static Richtung ermittleRichtung(char zeichen)
	{
		for (Richtung richtung : Richtung.values())
		{
			if (richtung.zeichen == zeichen)
				return richtung;
		}
		return null;
	}
}
