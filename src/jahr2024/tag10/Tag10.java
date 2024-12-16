package jahr2024.tag10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Tag10
{
	private Scanner scanner;
	private List<List<Integer>> berg;
	private List<List<Integer>> richtungen;

	public Tag10() throws FileNotFoundException
	{
		scanner = new Scanner(new FileReader("src/jahr2024/tag10/input.txt"));
		berg = new ArrayList<>();
		richtungen = Arrays.asList(
			Arrays.asList(-1, 0),
			Arrays.asList(0, -1),
			Arrays.asList(0, 1),
			Arrays.asList(1, 0)
		);
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Aufgabe 1: " + new Tag10().loeseAufgabe01());
	}

	private int loeseAufgabe01()
	{
		leseInputEin();
		int ergebnis = 0;
		for (int zeile = 0; zeile < berg.size(); zeile++)
		{
			for (int spalte = 0; spalte < berg.get(zeile).size(); spalte++)
			{
				if (berg.get(zeile).get(spalte) == 0)
				{
					ergebnis += ermittleAnzahlDerZiele(zeile, spalte);
				}
			}
		}
		return ergebnis;
	}

	private int ermittleAnzahlDerZiele(int zeile, int spalte)
	{
		List<List<Integer>> ziele = ermittleUmliegendeKoordinatenMitZiffer(zeile, spalte, 1);
		for (int i = 0; i < ziele.size() - 1; i++)
		{
			for (int j = i + 1; j < ziele.size(); j++)
				if (ziele.get(i).equals(ziele.get(j)))
				{
					System.out.println(ziele);
					System.out.println(ziele.get(i) + "=" + ziele.get(j));
					ziele.remove(j);
					j--;
					System.out.println(ziele);
					System.out.println(i + " " + j);
				}
		}
		System.out.println("----" + ziele);
		return ziele.size();
	}

	private List<List<Integer>> ermittleUmliegendeKoordinatenMitZiffer(int zeile, int spalte, int gesuchteZiffer)
	{
		if (gesuchteZiffer == 10)
		{
			return List.of(Arrays.asList(zeile, spalte));
		}
		List<List<Integer>> umliegendeFelderMitZiffer = new ArrayList<>();
		for (List<Integer> richtung : richtungen)
		{
			if (koordinateIstImFeld(zeile + richtung.get(0), spalte + richtung.get(1)))
			{
				if (berg.get(zeile + richtung.get(0)).get(spalte + richtung.get(1)) == gesuchteZiffer)
				{
					umliegendeFelderMitZiffer.addAll(
						ermittleUmliegendeKoordinatenMitZiffer(zeile + richtung.get(0), spalte + richtung.get(1),
							++gesuchteZiffer));
				}
			}
		}
		return umliegendeFelderMitZiffer;
	}

	private boolean koordinateIstImFeld(int zeile, int spalte)
	{
		return zeile >= 0 && zeile < berg.size() && spalte >= 0 && spalte < berg.get(0).size();
	}

	private void leseInputEin()
	{
		List<String> aktuelleZeile;
		while (scanner.hasNext())
		{
			aktuelleZeile = Arrays.stream(scanner.nextLine().split("")).toList();
			berg.add(aktuelleZeile.stream().map(Integer::parseInt).toList());
		}
	}
}
