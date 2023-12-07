package jahr2023;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tag04
{
	Scanner scanner = new Scanner(new FileReader("src/jahr2023/resources/Tag04.txt"));

	String aktuelleZeile;

	String[] gewinnZahlenString;
	List<Integer> gewinnZahlen = new ArrayList<>();

	String[] eigeneZahlenString;
	List<Integer> eigeneZahlen = new ArrayList<>();

	public Tag04() throws FileNotFoundException
	{}

	public static void main(String[] args) throws FileNotFoundException
	{
		new Tag04().berechneSumme1();
	}

	void berechneSumme1()
	{
		int summe = 0;
		while (scanner.hasNext())
		{
			gewinnZahlen.clear();
			eigeneZahlen.clear();
			aktuelleZeile = scanner.nextLine();
			erstelleZahlenListen();
			summe += berechnePunktzahl(berechneAnzahlTreffer(gewinnZahlen, eigeneZahlen));
		}
		System.out.println(summe);
	}

	int berechnePunktzahl(int anzahlTreffer)
	{
		int punktzahl = 0;
		if (anzahlTreffer > 0)
		{
			punktzahl = 1;
			for (int i = 1; i < anzahlTreffer; i++)
			{
				punktzahl *= 2;
			}
		}
		return punktzahl;
	}

	int berechneAnzahlTreffer(List<Integer> gewinnZahlen, List<Integer> eigeneZahlen)
	{
		int anzahlTreffer = 0;
		for (int i = 0; i < gewinnZahlen.size(); i++)
		{
			if (eigeneZahlen.contains(gewinnZahlen.get(i)))
			{
				anzahlTreffer++;
			}
		}
		return anzahlTreffer;
	}

	void erstelleZahlenListen()
	{
		String zeile = aktuelleZeile.replaceAll(":  ", ": ").split(": ")[1];
		zeile = zeile.replaceAll("  ", " ").replaceAll(" ", ":");
		gewinnZahlenString = zeile.split(":\\|:")[0].split(":");
		eigeneZahlenString = zeile.split(":\\|:")[1].split(":");
		for (int i = 0; i < gewinnZahlenString.length; i++)
		{
			gewinnZahlen.add(Integer.parseInt(gewinnZahlenString[i]));
		}
		for (int i = 0; i < eigeneZahlenString.length; i++)
		{
			eigeneZahlen.add(Integer.parseInt(eigeneZahlenString[i]));
		}
	}
}
