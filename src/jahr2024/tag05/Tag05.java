package jahr2024.tag05;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Tag05
{

	private Scanner scanner;
	private HashMap<Integer, List<Integer>> druckRegeln;
	private List<List<Integer>> druckUpdates;

	public Tag05() throws FileNotFoundException
	{
		scanner = new Scanner(new FileReader("src/jahr2024/tag05/input.txt"));
		druckRegeln = new HashMap<>();
		druckUpdates = new ArrayList<>();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Aufgabe 1: " + new Tag05().loeseAufgabe01());
	}

	private int loeseAufgabe01()
	{
		leseInputEin();
		int ergebnis = 0;
		for (List<Integer> druckUpdate : druckUpdates)
		{
			boolean istKorrekt = true;
			for (int i = druckUpdate.size() - 1; i >= 1; i--)
			{
				if (!alleLinksVomElementSindKorrekt(i, druckUpdate))
				{
					istKorrekt = false;
				}
			}
			if (istKorrekt)
			{
				ergebnis += druckUpdate.get(druckUpdate.size() / 2 + 1);
			}
		}
		return ergebnis;
	}

	private boolean alleLinksVomElementSindKorrekt(Integer stelle, List<Integer> druckUpdate)
	{
		int wert = druckUpdate.get(stelle);
		for (int i = 0; i < stelle; i++)
		{
			if (druckRegeln.containsKey(wert))
			{
				if (druckRegeln.get(wert).contains(druckUpdate.get(i)))
				{
					return false;
				}
			}
		}
		return true;
	}

	private void leseInputEin()
	{
		String zeile;
		boolean regelnEingelesen = false;
		while (scanner.hasNext())
		{
			zeile = scanner.nextLine();
			if (!regelnEingelesen)
			{
				if (!zeile.isEmpty())
				{
					String[] regeln = zeile.split("\\|");
					if (druckRegeln.containsKey(Integer.parseInt(regeln[0])))
					{
						List<Integer> neueListe = new ArrayList<>();
						for (Integer regel : druckRegeln.get(Integer.parseInt(regeln[0])))
						{
							neueListe.add(regel);
						}
						neueListe.add(Integer.parseInt(regeln[1]));
						druckRegeln.put(Integer.parseInt(regeln[0]), neueListe);
					}
					druckRegeln.put(Integer.parseInt(regeln[0]),
						Collections.singletonList(Integer.parseInt(regeln[1])));
				}
				else
				{
					regelnEingelesen = true;
				}
			}
			else
			{
				druckUpdates.add(Arrays.stream(zeile.split(",")).map(Integer::parseInt).toList());
			}
		}
	}
}
