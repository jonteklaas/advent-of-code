package jahr2023.tag08;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Tag08
{
	Scanner scanner = new Scanner(new FileReader("src/jahr2023/resources/Tag08.txt"));

	Map<String, List<String>> koordinaten = new HashMap<>();
	String richtungsanweisungen = "";

	public Tag08() throws FileNotFoundException
	{
		String aktuelleZeile;
		String schluessel = "";
		List<String> wert;

		richtungsanweisungen = scanner.nextLine().replace("L", "0").replace("R", "1");
		while (scanner.hasNext())
		{
			aktuelleZeile = scanner.nextLine().replace(" = ", "").replace("(", "").replace(", ", "").replace(")", "");
			wert = new ArrayList<>();
			schluessel = aktuelleZeile.substring(0, 3);
			wert.add(aktuelleZeile.substring(3, 6));
			wert.add(aktuelleZeile.substring(6, 9));
			koordinaten.put(schluessel, wert);
		}
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println(new Tag08().berechneSumme1());
		System.out.println(new Tag08().berechneSumme2());
	}

	private int berechneSumme1()
	{
		String aktuelleKoordinate = "AAA";
		int anzahlSchritte = 0;
		int stelleRichtungsanweisungen = 0;

		while (!aktuelleKoordinate.equals("ZZZ"))
		{
			aktuelleKoordinate = koordinaten.get(aktuelleKoordinate)
				.get(Integer.parseInt(richtungsanweisungen.charAt(stelleRichtungsanweisungen) + ""));
			stelleRichtungsanweisungen++;
			if (stelleRichtungsanweisungen == richtungsanweisungen.length())
			{
				stelleRichtungsanweisungen = 0;
			}
			anzahlSchritte++;
		}
		return anzahlSchritte;
	}

	private int berechneSumme2()
	{
		List<String> aktuelleKoordinatenGerade = koordinaten.keySet().stream().filter(s -> s.endsWith("A")).toList();
		System.out.println(aktuelleKoordinatenGerade);
		List<String> aktuelleKoordinatenUngerade = new ArrayList<>();
		int anzahlGleichzeitigeKnoten = aktuelleKoordinatenGerade.size();

		int anzahlSchritte = 0;
		int stelleRichtungsanweisungen = 1;
		boolean gerade = false;
		boolean fertig = false;

		while (!fertig)
		{
			fertig = true;
			if (gerade)
			{
				for (int i = 0; i < anzahlGleichzeitigeKnoten; i++)
				{
					aktuelleKoordinatenGerade.add(koordinaten.get(aktuelleKoordinatenUngerade.get(i))
						.get(Integer.parseInt(richtungsanweisungen.charAt(stelleRichtungsanweisungen) + "")));
					if (!aktuelleKoordinatenGerade.getLast().endsWith("Z"))
					{
						fertig = false;
					}
				}
				aktuelleKoordinatenUngerade = new ArrayList<>();
			}
			else
			{
				for (int i = 0; i < anzahlGleichzeitigeKnoten; i++)
				{
					aktuelleKoordinatenUngerade.add(koordinaten.get(aktuelleKoordinatenGerade.get(i))
						.get(Integer.parseInt(richtungsanweisungen.charAt(stelleRichtungsanweisungen) + "")));
					if (!aktuelleKoordinatenUngerade.getLast().endsWith("Z"))
					{
						fertig = false;
					}
				}
				aktuelleKoordinatenGerade = new ArrayList<>();
			}
			gerade = !gerade;
			stelleRichtungsanweisungen++;
			if (stelleRichtungsanweisungen == richtungsanweisungen.length())
			{
				stelleRichtungsanweisungen = 0;
			}
			anzahlSchritte++;
			System.out.println(anzahlSchritte);
		}
		return anzahlSchritte;
	}
}
