package jahr2024.tag15;

import java.util.ArrayList;
import java.util.List;

public class Spielfeld
{
	private List<List<Feld>> felder;
	private Koordinate koordinateRoboter;

	public Spielfeld()
	{
		this.felder = new ArrayList<>();
	}

	public void bewegeRoboterInRichtung(Richtung richtung)
	{
		Koordinate zielkoordinate = koordinateRoboter.ermittleKoordinateInRichtung(richtung);
		if (roboterKannInRichtungGehen(richtung))
		{
			bewegeWareVonKoordinateInRichtung(zielkoordinate, richtung);
			setFeld(koordinateRoboter, Element.LEER);
			setFeld(zielkoordinate, Element.ROBOTER);
			setKoordinateVonRoboter(zielkoordinate);
		}
	}

	private boolean roboterKannInRichtungGehen(Richtung richtung)
	{
		Koordinate zielkoordinate = koordinateRoboter.ermittleKoordinateInRichtung(richtung);
		while (getFeld(zielkoordinate).getElement() != Element.WAND)
		{
			if (getFeld(zielkoordinate).getElement() == Element.LEER)
			{
				return true;
			}
			zielkoordinate = zielkoordinate.ermittleKoordinateInRichtung(richtung);
		}
		return false;
	}

	private void bewegeWareVonKoordinateInRichtung(Koordinate startkoordinate, Richtung richtung)
	{
		Koordinate zielkoordinate = startkoordinate.ermittleKoordinateInRichtung(richtung);
		if (getFeld(startkoordinate).getElement() == Element.WARE)
		{
			if (getFeld(zielkoordinate).getElement() != Element.WAND)
			{
				if (getFeld(zielkoordinate).getElement() == Element.WARE)
				{
					bewegeWareVonKoordinateInRichtung(zielkoordinate, richtung);
				}
				setFeld(zielkoordinate, getFeld(startkoordinate).getElement());
			}
		}
	}

	public List<Koordinate> ermittleGpsKoordinatenDerWaren()
	{
		List<Koordinate> gpsKoordinatenDerWaren = new ArrayList<>();
		for (List<Feld> zeile : felder)
		{
			gpsKoordinatenDerWaren.addAll(
				zeile.stream().filter(f -> f.getElement() == Element.WARE).map(Feld::getKoordinate).toList());
		}
		return gpsKoordinatenDerWaren;
	}

	public Feld getFeld(Koordinate koordinate)
	{
		return felder.get(koordinate.zeile()).get(koordinate.spalte());
	}

	public void setFeld(Koordinate koordinate, Element element)
	{
		getFeld(koordinate).setElement(element);
	}

	public void setKoordinateVonRoboter(Koordinate koordinate)
	{
		koordinateRoboter = koordinate;
	}

	public void fuegeZeileHinzu(List<Feld> zeile)
	{
		felder.add(zeile);
	}
}
