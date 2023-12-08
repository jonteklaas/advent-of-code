package jahr2023;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Tag07
{
	Scanner scanner = new Scanner(new FileReader("src/jahr2023/resources/Tag07.txt"));

	List<String> eingabe = new ArrayList<>();
	List<List<Hand>> haende = new ArrayList<>();

	public Tag07() throws FileNotFoundException
	{
		while (scanner.hasNext())
		{
			eingabe.add(scanner.nextLine());
		}
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println(new Tag07().loeseAufgabe1());
	}

	int loeseAufgabe1()
	{
		int anzahlHaende = 0;
		for (int i = 0; i < 7; i++)
		{
			haende.add(new ArrayList<>());
		}

		for (int i = 0; i < eingabe.size(); i++)
		{
			String[] zeile;
			zeile = eingabe.get(i).split(" ");
			Hand aktuelleHand = new Hand(zeile[0], Integer.parseInt(zeile[1]));
			haende.get(aktuelleHand.gibTyp()).add(aktuelleHand);
			anzahlHaende++;
		}

		int summe = 0;
		for(int i = 0; i < anzahlHaende; i++){
			Hand hoechsteHand = ermittleHoechsteVerbliebendeHand(haende);
			System.out.println(haende.get(0).size());
			summe += hoechsteHand.gibBid() * (anzahlHaende - i);
		}
		return summe;
	}

	Hand ermittleHoechsteVerbliebendeHand(List<List<Hand>> verbleibendeHaende)
	{
		for (int i = 6; i >= 0; i--)
		{
			if (!verbleibendeHaende.get(i).isEmpty())
			{
				Hand aktuelleHand = vergleicheHaende(verbleibendeHaende.get(i), 0);
				haende.get(i).remove(aktuelleHand);
				return aktuelleHand;
			}
		}
		return null;
	}

	Hand vergleicheHaende(List<Hand> haende, int stelle)
	{
		int hoechsteHand = 0;
		List<Hand> hoechsteHaende = new ArrayList<>();

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
