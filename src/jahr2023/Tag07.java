package jahr2023;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Tag07
{
	Scanner scanner = new Scanner(new FileReader("src/jahr2023/resources/Tag07.txt"));

	List<String> eingabe = new ArrayList<>();
	List<List<Hand1>> haende1 = new ArrayList<>();
	List<List<Hand2>> haende2 = new ArrayList<>();
	int anzahlHaende = 0;

	public Tag07() throws FileNotFoundException
	{
		while (scanner.hasNext())
		{
			eingabe.add(scanner.nextLine());
		}
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println(new Tag07().loeseAufgaben());
	}

	String loeseAufgaben()
	{
		anzahlHaende = eingabe.size();
		for (int i = 0; i < 7; i++)
		{
			haende1.add(new ArrayList<>());
			haende2.add(new ArrayList<>());
		}

		return "Ergebnis 1: " + loeseAufgabe1() + "\nErgebnis 2: " + loeseAufgabe2();
	}

	int loeseAufgabe1()
	{
		for (int i = 0; i < anzahlHaende; i++)
		{
			String[] zeile;
			zeile = eingabe.get(i).split(" ");
			Hand1 aktuelleHand = new Hand1(zeile[0], Integer.parseInt(zeile[1]));
			haende1.get(aktuelleHand.gibTyp()).add(aktuelleHand);
		}

		int summe = 0;
		for (int i = 0; i < anzahlHaende; i++)
		{
			Hand1 hoechsteHand = ermittleHoechsteVerbliebendeHand(haende1);
			summe += hoechsteHand.gibBid() * (anzahlHaende - i);
		}
		return summe;
	}

	int loeseAufgabe2()
	{
		for (int i = 0; i < anzahlHaende; i++)
		{
			String[] zeile;
			zeile = eingabe.get(i).split(" ");
			Hand2 aktuelleHand = new Hand2(zeile[0], Integer.parseInt(zeile[1]));
			haende2.get(aktuelleHand.gibTyp()).add(aktuelleHand);
		}
		return 0;
	}

	Hand1 ermittleHoechsteVerbliebendeHand(List<List<Hand1>> verbleibendeHaende)
	{
		for (int i = 6; i >= 0; i--)
		{
			if (!verbleibendeHaende.get(i).isEmpty())
			{
				Hand1 aktuelleHand = vergleicheHaende(verbleibendeHaende.get(i), 0);
				haende1.get(i).remove(aktuelleHand);
				return aktuelleHand;
			}
		}
		return null;
	}

	Hand1 vergleicheHaende(List<Hand1> haende, int stelle)
	{
		int hoechsteHand = 0;
		List<Hand1> hoechsteHaende = new ArrayList<>();

		for (int i = 0; i < haende.size(); i++)
		{
			hoechsteHand =
				Math.max(haende.get(i).gibWertDerKarte(stelle), hoechsteHand);
		}

		for (int i = 0; i < haende.size(); i++)
		{
			if (haende.get(i).gibWertDerKarte(stelle) >= hoechsteHand)
			{
				hoechsteHaende.add(haende.get(i));
			}
		}
		if (hoechsteHaende.size() == 1)
		{
			return hoechsteHaende.get(0);
		}
		return vergleicheHaende(hoechsteHaende, stelle + 1);
	}

}
