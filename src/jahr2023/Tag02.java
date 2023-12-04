package jahr2023;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Tag02
{
	Scanner scanner = new Scanner(new FileReader("src/jahr2023/resources/Tag02.txt"));

	String aktuelleZeile;
	int stelleFarbe;
	int anzahlWuerfel;

	int id = 1;
	int anzahlRoteWuerfel = 0;
	int anzahlGrueneWuerfel = 0;
	int anzahlBlaueWuerfel = 0;

	int summe = 0;

	public Tag02() throws FileNotFoundException {}

	public static void main(String[] args) throws FileNotFoundException
	{
		new Tag02().berechneSumme1();
	}

	void berechneSumme1()
	{
		while (scanner.hasNext())
		{
			aktuelleZeile = scanner.nextLine().split(":")[1].replace(" ", "").replace(",", "").replace(";", "");
			aktuelleZeile = aktuelleZeile.replace("red", "r").replace("green", "g").replace("blue", "b");
			while (!aktuelleZeile.isEmpty())
			{
				if (Character.isDigit(aktuelleZeile.charAt(1)))
				{
					stelleFarbe = 2;
					anzahlWuerfel = Integer.parseInt(aktuelleZeile.charAt(0) + "" + aktuelleZeile.charAt(1));
				}
				else
				{
					stelleFarbe = 1;
					anzahlWuerfel = Integer.parseInt(aktuelleZeile.charAt(0) + "");
				}
				switch (aktuelleZeile.charAt(stelleFarbe))
				{
					case 'r':
						anzahlRoteWuerfel = Math.max(anzahlWuerfel, anzahlRoteWuerfel);
					case 'g':
						anzahlGrueneWuerfel = Math.max(anzahlWuerfel, anzahlGrueneWuerfel);
					case 'b':
						anzahlBlaueWuerfel = Math.max(anzahlWuerfel, anzahlBlaueWuerfel);
				}
				aktuelleZeile = aktuelleZeile.substring(stelleFarbe + 1);
			}
			if (anzahlRoteWuerfel <= 12 && anzahlGrueneWuerfel <= 13 && anzahlBlaueWuerfel <= 14)
			{
				summe += id;
			}
			id++;
			anzahlRoteWuerfel = 0;
			anzahlGrueneWuerfel = 0;
			anzahlBlaueWuerfel = 0;
		}
		System.out.println(summe);
	}
}
