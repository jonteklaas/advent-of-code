package jahr2023;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tag01
{
	Scanner scanner = new Scanner(new FileReader("src/jahr2023/resources/Tag01.txt"));
	int summe = 0;
	String ersteZiffer = "";
	String letzteZiffer = "";
	String aktuelleZeile;
	List<String> ziffernAlsString = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

	public Tag01() throws FileNotFoundException
	{
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		new Tag01().berechneSumme1();
		new Tag01().berechneSumme2();
	}

	void berechneSumme1()
	{
		while (scanner.hasNext())
		{
			aktuelleZeile = scanner.nextLine();
			for (int i = 0; i < aktuelleZeile.length(); i++)
			{
				if (Character.isDigit(aktuelleZeile.charAt(i)))
				{
					if (ersteZiffer.isEmpty())
					{
						ersteZiffer = String.valueOf(aktuelleZeile.charAt(i));
					}
					letzteZiffer = String.valueOf(aktuelleZeile.charAt(i));
				}
			}
			summe += Integer.parseInt(ersteZiffer + letzteZiffer);
			ersteZiffer = "";
			letzteZiffer = "";
		}
		System.out.println("Summe 1: " + summe);
	}

	void berechneSumme2()
	{
		while (scanner.hasNext())
		{
			aktuelleZeile = scanner.nextLine();
			for (int i = 0; i < aktuelleZeile.length(); i++)
			{
				if (Character.isDigit(aktuelleZeile.charAt(i)))
				{
					fuegeZahlHinzu(Integer.parseInt(aktuelleZeile.charAt(i) + ""));
				}
				else
					if (i >= 2)
					{
						if (ziffernAlsString.contains("" + aktuelleZeile.charAt(i - 2) + aktuelleZeile.charAt(i - 1) + aktuelleZeile.charAt(i)))
						{
							fuegeZahlHinzu(
								ziffernAlsString.indexOf("" + aktuelleZeile.charAt(i - 2) + aktuelleZeile.charAt(i - 1) + aktuelleZeile.charAt(i)) + 1);
						}
					}
				if (i >= 3)
				{
					if (ziffernAlsString.contains(
						"" + aktuelleZeile.charAt(i - 3) + aktuelleZeile.charAt(i - 2) + aktuelleZeile.charAt(i - 1) + aktuelleZeile.charAt(i)))
					{
						fuegeZahlHinzu(ziffernAlsString.indexOf(
							"" + aktuelleZeile.charAt(i - 3) + aktuelleZeile.charAt(i - 2) + aktuelleZeile.charAt(i - 1) + aktuelleZeile.charAt(i)) + 1);

					}
				}
				if (i >= 4)
				{
					if (ziffernAlsString.contains(
						"" + aktuelleZeile.charAt(i - 4) + aktuelleZeile.charAt(i - 3) + aktuelleZeile.charAt(i - 2) + aktuelleZeile.charAt(i - 1)
							+ aktuelleZeile.charAt(i)))
					{
						fuegeZahlHinzu(ziffernAlsString.indexOf(
							"" + aktuelleZeile.charAt(i - 4) + aktuelleZeile.charAt(i - 3) + aktuelleZeile.charAt(i - 2) + aktuelleZeile.charAt(i - 1)
								+ aktuelleZeile.charAt(i)) + 1);
					}
				}
			}
			summe += Integer.parseInt(ersteZiffer + letzteZiffer);
			ersteZiffer = "";
			letzteZiffer = "";
		}
		System.out.println("Summe 2: " + summe);
	}

	void fuegeZahlHinzu(int zahl)
	{
		if (ersteZiffer.isEmpty())
		{
			ersteZiffer = String.valueOf(zahl);
		}
		letzteZiffer = String.valueOf(zahl);
	}
}
