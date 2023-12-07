package jahr2023;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Tag05
{
	Scanner scanner = new Scanner(new FileReader("src/jahr2023/resources/Tag05.txt"));

	List<String> eingabe = new ArrayList<>();
	List<String> aktuelleMap = new ArrayList<>();
	List<Long> werte = new ArrayList<>();

	String[] samenliste;

	public Tag05() throws FileNotFoundException
	{
		while (scanner.hasNext())
		{
			eingabe.add(scanner.nextLine());
		}
		samenliste = eingabe.get(0).split(": ")[1].split(" ");
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Ergebnis 1: " + new Tag05().loeseAufgabe1());
		System.out.println("Ergebnis 2: " + new Tag05().loeseAufgabe2());
	}

	Long berechneSumme(List<Long> werte)
	{
		int index = 1;

		for (int i = 0; i < 7; i++)
		{
			aktuelleMap.clear();
			index += 2;
			while (index < eingabe.size() && !eingabe.get(index).isEmpty())
			{
				aktuelleMap.add(eingabe.get(index));
				index++;
			}
			for (int j = 0; j < werte.size(); j++)
			{
				werte.set(j, loeseMapAuf(wandleMapInLongUm(aktuelleMap), werte.get(j)));
			}
		}
		return Collections.min(werte);
	}

	Long loeseAufgabe1()
	{
		for (int i = 0; i < samenliste.length; i++)
		{
			werte.add(Long.valueOf(samenliste[i]));
		}

		return berechneSumme(werte);
	}

	Long loeseAufgabe2()
	{
		for (int i = 0; i + 1 < samenliste.length; i++)
		{
			for (int j = 0; j < Long.parseLong(samenliste[i + 1]); j++){
				werte.add(Long.valueOf(samenliste[i] + j));
			}
		}

		return berechneSumme(werte);
	}

	List<List<Long>> wandleMapInLongUm(List<String> stringMap)
	{
		String[] zeile;
		List<List<Long>> longMap = new ArrayList<>();
		for (int i = 0; i < stringMap.size(); i++)
		{
			longMap.add(new ArrayList<>());
			longMap.get(i).add(-1L);
			longMap.get(i).add(-1L);
			longMap.get(i).add(-1L);
			zeile = stringMap.get(i).split(" ");
			longMap.get(i).set(0, Long.valueOf(zeile[0]));
			longMap.get(i).set(1, Long.valueOf(zeile[1]));
			longMap.get(i).set(2, Long.valueOf(zeile[2]));
		}
		return longMap;
	}

	Long loeseMapAuf(List<List<Long>> longMap, Long quelle)
	{
		for (int i = 0; i < longMap.size(); i++) //ziel, quelle, range
		{
			Long differenz = quelle - longMap.get(i).get(1);
			if (differenz >= 0 && differenz < longMap.get(i).get(2))
			{
				return longMap.get(i).get(0) + differenz;
			}
		}
		return quelle;
	}
}
