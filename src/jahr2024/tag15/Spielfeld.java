package jahr2024.tag15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

	public void bewegeRoboterInRichtung2(Richtung richtung)
	{
		Koordinate zielkoordinate = koordinateRoboter.ermittleKoordinateInRichtung(richtung);
		if (roboterKannInRichtungGehen2(richtung))
		{
			bewegeWareVonKoordinateInRichtung2(zielkoordinate, richtung);
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

	private boolean roboterKannInRichtungGehen2(Richtung richtung)
	{
		Koordinate zielkoordinate = koordinateRoboter.ermittleKoordinateInRichtung(richtung);
		if (getFeld(zielkoordinate).getElement() != Element.WAND)
		{
			return false;
		}

		return wareKannInRichtungGehen2(koordinateRoboter, richtung);
	}

	private boolean wareKannInRichtungGehen2(Koordinate koordinate, Richtung richtung)
	{
		Koordinate zielkoordinate = koordinate.ermittleKoordinateInRichtung(richtung);
		if (getFeld(zielkoordinate).getElement() == Element.WAND)
		{
			return false;
		}

		if (richtung == Richtung.OBEN || richtung == Richtung.UNTEN)
		{
			if (getFeld(koordinate).getElement() == Element.WARE_LINKS)
			{
				return wareKannInRichtungGehen2(zielkoordinate, richtung) && wareKannInRichtungGehen2(
					zielkoordinate.ermittleKoordinateInRichtung(Richtung.RECHTS), richtung);
			}

			if (getFeld(koordinate).getElement() == Element.WARE_RECHTS)
			{
				return wareKannInRichtungGehen2(zielkoordinate, richtung) && wareKannInRichtungGehen2(
					zielkoordinate.ermittleKoordinateInRichtung(Richtung.LINKS), richtung);
			}
		}

		if (richtung == Richtung.LINKS || richtung == Richtung.RECHTS)
		{
			while (getFeld(zielkoordinate.ermittleKoordinateInRichtung(richtung)).getElement() != Element.WAND)
			{
				if (getFeld(zielkoordinate).getElement() == Element.LEER)
				{
					return true;
				}
				zielkoordinate = zielkoordinate.ermittleKoordinateInRichtung(richtung);
			}
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

	private void bewegeWareVonKoordinateInRichtung2(Koordinate startkoordinate, Richtung richtung)
	{
		Koordinate zielkoordinate = startkoordinate.ermittleKoordinateInRichtung(richtung);

		if (richtung == Richtung.OBEN || richtung == Richtung.UNTEN)
		{
			if (getFeld(startkoordinate).getElement() == Element.WARE_LINKS)
			{
				if (getFeld(zielkoordinate).getElement() != Element.WAND
					&& getFeld(zielkoordinate.ermittleKoordinateInRichtung(Richtung.RECHTS)).getElement()
					!= Element.WAND)
				{
					if (getFeld(zielkoordinate).getElement() == Element.WARE_LINKS
						|| getFeld(zielkoordinate).getElement() == Element.WARE_RECHTS)
					{
						bewegeWareVonKoordinateInRichtung(zielkoordinate, richtung);
					}
					setFeld(zielkoordinate, getFeld(startkoordinate).getElement());
					setFeld(zielkoordinate.ermittleKoordinateInRichtung(Richtung.RECHTS),
						getFeld(startkoordinate.ermittleKoordinateInRichtung(Richtung.RECHTS)).getElement());
					setFeld(startkoordinate.ermittleKoordinateInRichtung(Richtung.RECHTS), Element.LEER);
				}
			}

			if (getFeld(startkoordinate).getElement() == Element.WARE_RECHTS)
			{
				if (getFeld(zielkoordinate).getElement() != Element.WAND
					&& getFeld(zielkoordinate.ermittleKoordinateInRichtung(Richtung.LINKS)).getElement()
					!= Element.WAND)
				{
					if (getFeld(zielkoordinate).getElement() == Element.WARE_LINKS
						|| getFeld(zielkoordinate).getElement() == Element.WARE_RECHTS)
					{
						bewegeWareVonKoordinateInRichtung(zielkoordinate, richtung);
					}
					setFeld(zielkoordinate, getFeld(startkoordinate).getElement());
					setFeld(zielkoordinate.ermittleKoordinateInRichtung(Richtung.LINKS),
						getFeld(startkoordinate.ermittleKoordinateInRichtung(Richtung.LINKS)).getElement());
					setFeld(startkoordinate.ermittleKoordinateInRichtung(Richtung.LINKS), Element.LEER);
				}
			}
		}

		if (richtung == Richtung.LINKS)
		{
			if (getFeld(startkoordinate).getElement() == Element.WARE_RECHTS)
			{
				if (getFeld(zielkoordinate.ermittleKoordinateInRichtung(Richtung.LINKS)).getElement() != Element.WAND)
				{
					if (getFeld(zielkoordinate.ermittleKoordinateInRichtung(Richtung.LINKS)).getElement()
						== Element.WARE_RECHTS)
					{
						bewegeWareVonKoordinateInRichtung(zielkoordinate, richtung);
					}
					setFeld(zielkoordinate.ermittleKoordinateInRichtung(Richtung.LINKS)
							.ermittleKoordinateInRichtung(Richtung.LINKS),
						getFeld(startkoordinate.ermittleKoordinateInRichtung(Richtung.LINKS)
							.ermittleKoordinateInRichtung(Richtung.LINKS)).getElement());
					setFeld(zielkoordinate.ermittleKoordinateInRichtung(Richtung.LINKS),
						getFeld(startkoordinate.ermittleKoordinateInRichtung(Richtung.LINKS)).getElement());
				}
			}
		}

		if (richtung == Richtung.RECHTS)
		{
			if (getFeld(startkoordinate).getElement() == Element.WARE_LINKS)
			{
				if (getFeld(zielkoordinate.ermittleKoordinateInRichtung(Richtung.RECHTS)).getElement() != Element.WAND)
				{
					if (getFeld(zielkoordinate.ermittleKoordinateInRichtung(Richtung.RECHTS)).getElement()
						== Element.WARE_LINKS)
					{
						bewegeWareVonKoordinateInRichtung(zielkoordinate, richtung);
					}
					setFeld(zielkoordinate.ermittleKoordinateInRichtung(Richtung.RECHTS)
							.ermittleKoordinateInRichtung(Richtung.RECHTS),
						getFeld(startkoordinate.ermittleKoordinateInRichtung(Richtung.RECHTS)
							.ermittleKoordinateInRichtung(Richtung.RECHTS)).getElement());
					setFeld(zielkoordinate.ermittleKoordinateInRichtung(Richtung.RECHTS),
						getFeld(startkoordinate.ermittleKoordinateInRichtung(Richtung.RECHTS)).getElement());
				}
			}
		}

	}

	public List<Koordinate> ermittleGpsKoordinatenDerWaren(Element ware)
	{
		List<Koordinate> gpsKoordinatenDerWaren = new ArrayList<>();
		for (List<Feld> zeile : felder)
		{
			gpsKoordinatenDerWaren.addAll(
				zeile.stream().filter(f -> f.getElement() == ware).map(Feld::getKoordinate).toList());
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

	public void verdoppleGroeße()
	{
		List<List<Feld>> neuesSpielfeld = new ArrayList<>();
		List<Feld> neueZeile = new ArrayList<>();
		for (int zeile = 0; zeile < felder.size(); zeile++)
		{
			for (int spalte = 0; spalte < felder.get(zeile).size(); spalte++)
			{
				neueZeile.addAll(ermittleNeuesZeichen(getFeld(new Koordinate(zeile, spalte))));
			}
			neuesSpielfeld.add(neueZeile);
		}
		felder = neuesSpielfeld;
	}

	private List<Feld> ermittleNeuesZeichen(Feld feld)
	{
		return switch (feld.getElement())
			{
				case WAND -> Arrays.asList(new Feld(feld.getKoordinate().multipliziereSpalteMit2(), '#'), new Feld(
					feld.getKoordinate().multipliziereSpalteMit2().ermittleKoordinateInRichtung(Richtung.RECHTS), '#'));
				case WARE -> Arrays.asList(new Feld(feld.getKoordinate().multipliziereSpalteMit2(), '['), new Feld(
					feld.getKoordinate().multipliziereSpalteMit2().ermittleKoordinateInRichtung(Richtung.RECHTS), ']'));
				case LEER -> Arrays.asList(new Feld(feld.getKoordinate().multipliziereSpalteMit2(), '.'), new Feld(
					feld.getKoordinate().multipliziereSpalteMit2().ermittleKoordinateInRichtung(Richtung.RECHTS), '.'));
				case ROBOTER -> Arrays.asList(new Feld(feld.getKoordinate().multipliziereSpalteMit2(), '@'), new Feld(
					feld.getKoordinate().multipliziereSpalteMit2().ermittleKoordinateInRichtung(Richtung.RECHTS), '.'));
				default -> null;
			};
	}

	@Override
	public String toString()
	{
		var output = "";
		for (List<Feld> felderzeile : felder)
		{
			for (Feld feld : felderzeile)
			{
				output += feld.getElement().zeichen;
			}
			output += "\n";
		}
		return output;
	}
}
