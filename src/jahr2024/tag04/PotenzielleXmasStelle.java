package jahr2024.tag04;

import java.util.List;

public class PotenzielleXmasStelle
{
	List<List<Character>> feld;
	private int zeile;
	private int spalte;
	private char obenlinks;
	private char oben;
	private char obenrechts;
	private char links;
	private char rechts;
	private char untenlinks;
	private char unten;
	private char untenrechts;

	private boolean istObersteZeile;
	private boolean istUntersteZeile;
	private boolean istLinkesteSpalte;
	private boolean istRechtesteSpalte;

	public PotenzielleXmasStelle(int zeile, int spalte, List<List<Character>> feld)
	{
		this.feld = feld;
		this.zeile = zeile;
		this.spalte = spalte;
		ermittleUmliegende('M', zeile, spalte);
	}

	private int ermittleUmliegende(char gesuchtesZeichen, int zeile, int spalte)
	{
		if (gesuchtesZeichen == '.')
		{
			return 1;
		}
		if (zeile == 0)
		{
			return ermittleUmliegendeAusserOben(gesuchtesZeichen, zeile, spalte);
		}
		if (zeile == feld.size() - 1)
		{
			return ermittleUmliegendeAusserUnten(gesuchtesZeichen, zeile, spalte);
		}

//	Problem: Es wird nicht beachtet, aus welcher Richtung das vorherige Zeichen kommt; XMA würde als Treffer zählen
//																						 S

		if (feld.get(zeile - 1).get(spalte - 1).equals(gesuchtesZeichen))
		{
			return ermittleUmliegende(ermittleNaechstesGesuchtesZeichen(gesuchtesZeichen), zeile - 1, spalte - 1);
		}
		if (feld.get(zeile - 1).get(spalte).equals(gesuchtesZeichen))
		{
			return ermittleUmliegende(ermittleNaechstesGesuchtesZeichen(gesuchtesZeichen), zeile - 1, spalte);
		}
		if (feld.get(zeile - 1).get(spalte + 1).equals(gesuchtesZeichen))
		{
			return ermittleUmliegende(ermittleNaechstesGesuchtesZeichen(gesuchtesZeichen), zeile - 1, spalte + 1);
		}

		if (feld.get(zeile).get(spalte - 1).equals(gesuchtesZeichen))
		{
			return ermittleUmliegende(ermittleNaechstesGesuchtesZeichen(gesuchtesZeichen), zeile, spalte - 1);
		}
		if (feld.get(zeile).get(spalte + 1).equals(gesuchtesZeichen))
		{
			return ermittleUmliegende(ermittleNaechstesGesuchtesZeichen(gesuchtesZeichen), zeile, spalte + 1);
		}

		if (feld.get(zeile + 1).get(spalte - 1).equals(gesuchtesZeichen))
		{
			return ermittleUmliegende(ermittleNaechstesGesuchtesZeichen(gesuchtesZeichen), zeile + 1, spalte - 1);
		}
		if (feld.get(zeile + 1).get(spalte).equals(gesuchtesZeichen))
		{
			return ermittleUmliegende(ermittleNaechstesGesuchtesZeichen(gesuchtesZeichen), zeile + 1, spalte);
		}
		if (feld.get(zeile + 1).get(spalte + 1).equals(gesuchtesZeichen))
		{
			return ermittleUmliegende(ermittleNaechstesGesuchtesZeichen(gesuchtesZeichen), zeile + 1, spalte + 1);
		}
		return 0;
	}

	private int ermittleUmliegendeAusserOben(char gesuchtesZeichen, int zeile, int spalte)
	{
		if (spalte == 0)
		{
//			berechneUmliegendeAusserObenUndLinks(gesuchtesZeichen, zeile, spalte);
		}
		if (spalte == feld.get(0).size() - 1)
		{
//			berechneUmliegendeAusserObenUndRechts(gesuchtesZeichen, zeile, spalte);
		}
//		ermittleUmliegendeAusserOben(gesuchtesZeichen); //kann hier implementiert werden
		return 0;
	}

	private int ermittleUmliegendeAusserUnten(char gesuchtesZeichen, int zeile, int spalte)
	{
		if (spalte == 0)
		{
//			berechneUmliegendeAusserUntenUndLinks(gesuchtesZeichen, zeile, spalte);
		}
		if (spalte == feld.get(0).size() - 1)
		{
//			berechneUmliegendeAusserUntenUndRechts(gesuchtesZeichen, zeile, spalte);
		}
//		ermittleUmliegendeAusserUnten(gesuchtesZeichen); //kann hier implementiert werden
		return 0;
	}

	private char ermittleNaechstesGesuchtesZeichen(char aktuellesZeichen)
	{
		if (aktuellesZeichen == 'S')
		{
			return '.';
		}
		return "XMAS".charAt("XMAS".indexOf(aktuellesZeichen) + 1);
	}
}
