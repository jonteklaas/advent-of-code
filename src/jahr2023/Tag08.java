package jahr2023;

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
		List<String> wert = new ArrayList<>();

		richtungsanweisungen = scanner.nextLine().replace("L", "0").replace("R", "1");
		while (scanner.hasNext())
		{
			aktuelleZeile = scanner.nextLine().replace(" = ", "").replace("(", "").replace(", ", "").replace(")", "");
			wert.clear();
			schluessel = aktuelleZeile.substring(0, 3);
			wert.add(aktuelleZeile.substring(3, 6));
			wert.add(aktuelleZeile.substring(6, 9));

			System.out.println(koordinaten);
			koordinaten.put(schluessel, wert);
			schluessel = null;
			System.out.println(koordinaten.size());
			System.out.println("HALLO"+koordinaten.get("FCG"));
		}
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		Tag08 t8 = new Tag08();
		System.out.println(t8.berechneSumme1());
	}

	private int berechneSumme1()
	{
		String aktuelleKoordinate = "AAA";
		int anzahlSchritte = 0;
		int stelleRichtungsanweisungen = 0;

		while (!aktuelleKoordinate.equals("ZZZ"))
		{
//			System.out.println(aktuelleKoordinate);
//			System.out.println(koordinaten.get(aktuelleKoordinate));
			aktuelleKoordinate = koordinaten.get(aktuelleKoordinate)
				.get(Integer.parseInt(richtungsanweisungen.charAt(stelleRichtungsanweisungen) + ""));
			stelleRichtungsanweisungen++;
			if (stelleRichtungsanweisungen == richtungsanweisungen.length())
			{
				stelleRichtungsanweisungen = 0;
			}
			anzahlSchritte++;
			if (anzahlSchritte == 10)
			{
				break;
			}
		}
		return anzahlSchritte;
	}
}
