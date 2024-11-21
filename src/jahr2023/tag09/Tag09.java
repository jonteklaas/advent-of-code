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
		System.out.println("Aufgabe 1: " + new Tag09().loeseAufgabe1());
	}

	private Integer loeseAufgabe1()
	{
		Integer SummeDerLetztenZahlen = 0;
		while (scanner.hasNext())
		{
			zeilenliste = new ArrayList<>();
			SummeDerLetztenZahlen +=
				verarbeiteZeile(
					new Zahlenzeile(Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toList()));
		}
		return SummeDerLetztenZahlen;
	}

	private Integer verarbeiteZeile(Zahlenzeile zeile)
	{
		zeilenliste.add(zeile);
		berechneZahlenbaum(zeile);

		zeilenliste.getLast().haengeZahlAn(0);

		for (int i = zeilenliste.size() - 2; i >= 0; i--)
		{
			zeilenliste.get(i).berechneLetzteZahl(zeilenliste.get(i + 1).getZeile().getLast());
		}

		return zeilenliste.getFirst().getZeile().getLast();
	}

	// TODO: nur die letzte Zahl der Zeile merken
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
