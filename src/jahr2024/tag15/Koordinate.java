package jahr2024.tag15;

public record Koordinate(int zeile, int spalte)
{
	public Integer ermittleWertDerKoordinate()
	{
		return zeile * 100 + spalte;
	}

	public Koordinate ermittleKoordinateInRichtung(Richtung richtung)
	{
		return switch (richtung)
			{
				case OBEN -> new Koordinate(zeile - 1, spalte);
				case RECHTS -> new Koordinate(zeile, spalte + 1);
				case UNTEN -> new Koordinate(zeile + 1, spalte);
				case LINKS -> new Koordinate(zeile, spalte - 1);
			};
	}

	public Koordinate getKoordinateVonOben()
	{
		return new Koordinate(zeile - 1, spalte);
	}

	public Koordinate getKoordinateVonRechts()
	{
		return new Koordinate(zeile, spalte + 1);
	}

	public Koordinate getKoordinateVonUnten()
	{
		return new Koordinate(zeile + 1, spalte);
	}

	public Koordinate getKoordinateVonLinks()
	{
		return new Koordinate(zeile, spalte - 1);
	}
}
