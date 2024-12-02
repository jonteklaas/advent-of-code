package jahr2024.tag02;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Tag02
{
	private Scanner scanner;
	private List<List<Integer>> zeilen;
	private List<Integer> korrigierteZeile;

	public Tag02() throws FileNotFoundException
	{
		scanner = new Scanner(new FileReader("src/jahr2024/tag02/input.txt"));
		zeilen = new ArrayList<>();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Aufgabe 1: " + new Tag02().loeseAufgabe01());
		System.out.println("Aufgabe 2: " + new Tag02().loeseAufgabe02());
	}

	private int loeseAufgabe01()
	{
		leseInputEin();
		int anzahlKorrekterZeilen = 0;
		for (List<Integer> zeile : zeilen)
		{
			if (istKorrekt(zeile))
			{
				anzahlKorrekterZeilen++;
			}
		}
		return anzahlKorrekterZeilen;
	}

	private int loeseAufgabe02()
	{
		leseInputEin();
		int anzahlKorrekterZeilen = 0;
		List<Integer> aktuelleZeile;
		List<Integer> korrigierteZeile;
		for (List<Integer> zeile : zeilen)
		{
			for (int i = 0; i < zeile.size(); i++)
			{
				korrigierteZeile = new ArrayList<>();
				for (int j = 0; j < zeile.size(); j++)
				{
					if (i != j)
					{
						korrigierteZeile.add(zeile.get(j));
					}
				}
				if (istKorrekt(korrigierteZeile))
				{
					anzahlKorrekterZeilen++;
					break;
				}
			}
		}
		return anzahlKorrekterZeilen;
	}

	private boolean istKorrekt(List<Integer> zeile)
	{
		int differenz;
		if (Objects.equals(zeile.get(0), zeile.get(1)))
		{
			return false;
		}
		if (zeile.get(0) < zeile.get(1))
		{
			for (int i = 1; i < zeile.size(); i++)
			{
				differenz = zeile.get(i) - zeile.get(i - 1);
				if (differenz < 1 || differenz > 3)
				{
					return false;
				}
			}
		}
		if (zeile.get(0) > zeile.get(1))
		{
			for (int i = 1; i < zeile.size(); i++)
			{
				differenz = zeile.get(i - 1) - zeile.get(i);
				if (differenz < 1 || differenz > 3)
				{
					return false;
				}
			}
		}
		return true;
	}

	private void leseInputEin()
	{
		List<String> aktuelleZeile;
		while (scanner.hasNext())
		{
			aktuelleZeile = Arrays.stream(scanner.nextLine().split(" ")).toList();
			zeilen.add(aktuelleZeile.stream().map(Integer::parseInt).toList());
		}
	}
}
