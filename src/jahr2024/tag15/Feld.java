package jahr2024.tag15;

public class Feld
{
	private Koordinate koordinate;
	private Element element;

	public Feld(Koordinate koordinate, char zeichen)
	{
		this.koordinate = koordinate;
		this.element = Element.ermittleElement(zeichen);
	}

	public Koordinate getKoordinate()
	{
		return koordinate;
	}

	public Element getElement()
	{
		return element;
	}

	public void setElement(Element element)
	{
		this.element = element;
	}
}
