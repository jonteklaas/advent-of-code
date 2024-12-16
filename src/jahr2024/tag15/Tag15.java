package jahr2024.tag15;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tag15
{
	private Scanner scanner;
	private Spielfeld spielfeld;
	private List<Richtung> richtungen;
	private Koordinate roboter;

	public Tag15(String pfad) throws FileNotFoundException
	{
		scanner = new Scanner(new FileReader(pfad));
		richtungen = new ArrayList<>();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Aufgabe 1: " + new Tag15("src/jahr2024/tag15/input.txt").loeseAufgabe01());
		System.out.println("Aufgabe 2: " + new Tag15("src/jahr2024/tag15/input.txt").loeseAufgabe02());
	}

	private int loeseAufgabe01()
	{
		leseInputEin();
		for (Richtung richtung : richtungen)
		{
			spielfeld.bewegeRoboterInRichtung(richtung);
		}
		int ergebnis = 0;
		var werteDerKoordinaten =
			spielfeld.ermittleGpsKoordinatenDerWaren(Element.WARE).stream().map(Koordinate::ermittleWertDerKoordinate)
				.toList();
		for (int wert : werteDerKoordinaten)
		{
			ergebnis += wert;
		}
		return ergebnis;
	}

	private int loeseAufgabe02()
	{
		leseInputEin();
		spielfeld.verdoppleGroeße();
		for (Richtung richtung : richtungen)
		{
			spielfeld.bewegeRoboterInRichtung2(richtung);
		}
		int ergebnis = 0;
		var werteDerKoordinaten =
			spielfeld.ermittleGpsKoordinatenDerWaren(Element.WARE_LINKS).stream()
				.map(Koordinate::ermittleWertDerKoordinate).toList();
		for (int wert : werteDerKoordinaten)
		{
			ergebnis += wert;
		}
		return ergebnis;
	}

	private void leseInputEin()
	{
		spielfeld = new Spielfeld();
		int zeilennummer = 0;
		String zeile;
		boolean feldEingelesen = false;
		while (scanner.hasNext())
		{
			zeile = scanner.nextLine();
			if (zeile.isEmpty())
			{
				feldEingelesen = true;
				zeile = scanner.nextLine();
			}
			if (!feldEingelesen)
			{
				List<Feld> spielfeldzeile = new ArrayList<>();
				for (int spaltennummer = 0; spaltennummer < zeile.length(); spaltennummer++)
				{
					char zeichen = zeile.charAt(spaltennummer);
					if (zeichen == '@')
					{
						spielfeld.setKoordinateVonRoboter(new Koordinate(zeilennummer, spaltennummer));
					}
					spielfeldzeile.add(
						new Feld(new Koordinate(zeilennummer, spaltennummer), zeichen));
				}
				spielfeld.fuegeZeileHinzu(spielfeldzeile);
				zeilennummer++;
			}
			else
			{
				for (int spalte = 0; spalte < zeile.length(); spalte++)
				{
					richtungen.add(Richtung.ermittleRichtung(zeile.charAt(spalte)));
				}
			}
		}
	}
}
