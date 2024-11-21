package jahr2023.tag09;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tag09
{
	private Scanner scanner;
	private List<Zahlenzeile> zeilenliste;

	public Tag09() throws FileNotFoundException
	{
		scanner = new Scanner(new FileReader("src/jahr2023/resources/Tag09.txt"));
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Aufgabe 1: " + new Tag09().loeseAufgabe(1));
		System.out.println("Aufgabe 2: " + new Tag09().loeseAufgabe(2));
	}

	private Integer loeseAufgabe(int aufgabe)
	{
		boolean nullWirdHintenAngehaengt = aufgabe == 1;
		Integer SummeDerLetztenZahlen = 0;
		while (scanner.hasNext())
		{
			zeilenliste = new ArrayList<>();
			SummeDerLetztenZahlen +=
				verarbeiteZeile(
					new Zahlenzeile(Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toList()),
					nullWirdHintenAngehaengt);
		}
		return SummeDerLetztenZahlen;
	}

	private Integer verarbeiteZeile(Zahlenzeile zeile, boolean nullWirdHintenAngehaengt)
	{
		zeilenliste.add(zeile);
		berechneZahlenbaum(zeile);

		if (nullWirdHintenAngehaengt)
		{
			zeilenliste.getLast().haengeZahlAn(0);
			for (int i = zeilenliste.size() - 2; i >= 0; i--)
			{
				zeilenliste.get(i).berechneLetzteZahl(zeilenliste.get(i + 1).getZeile().getLast());
			}
			return zeilenliste.getFirst().getZeile().getLast();
		}

		zeilenliste.getLast().stelleZahlVoran(0);
		for (int i = zeilenliste.size() - 2; i >= 0; i--)
		{
			zeilenliste.get(i).berechneErsteZahl(zeilenliste.get(i + 1).getZeile().getFirst());
		}
		return zeilenliste.getFirst().getZeile().getFirst();
	}

	private void berechneZahlenbaum(Zahlenzeile zeile)
	{
		Zahlenzeile naechsteZeile = zeile.getNaechsteZeile();
		zeilenliste.add(naechsteZeile);
		if (zeile.enthaeltAndereZahlAls0())
		{
			berechneZahlenbaum(naechsteZeile);
		}
	}
}
