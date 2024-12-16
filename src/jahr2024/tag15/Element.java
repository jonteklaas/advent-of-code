package jahr2024.tag15;

public enum Element
{
	ROBOTER('@'),
	WAND('#'),
	WARE('O'),
	LEER('.');

	public final char zeichen;

	Element(char zeichen)
	{
		this.zeichen = zeichen;
	}

	public static Element ermittleElement(char zeichen)
	{
		for (Element element : Element.values())
		{
			if (element.zeichen == zeichen)
				return element;
		}
		return null;
	}

	public boolean istWare()
	{
		return zeichen == 'O';
	}

	public boolean istLeer()
	{
		return zeichen == '.';
	}
}
