package jahr2023;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tag03
{
	Scanner scanner = new Scanner(new FileReader("src/jahr2023/resources/Tag03.txt"));

	List<String> eingabe = new ArrayList<>();

	String aktuelleZeile;
	String aktuelleZahl = "";

	int startIndex = 0;
	int endIndex = 0;

	int summe = 0;

	boolean istGueltig;

	public Tag03() throws FileNotFoundException {}

	public static void main(String[] args) throws FileNotFoundException
	{
		new Tag03().berechneSumme1();
		new Tag03().berechneSumme2();
	}

	void berechneSumme1()
	{
		while (scanner.hasNext())
		{
			eingabe.add(scanner.nextLine());
		}
		for (int i = 0; i < eingabe.size(); i++)
		{
			aktuelleZeile = eingabe.get(i);
			for (int j = 0; j < aktuelleZeile.length(); j++)
			{
				if (aktuelleZahl.isEmpty())
				{
					startIndex = j;
				}
				if (Character.isDigit(aktuelleZeile.charAt(j)))
				{
					aktuelleZahl += aktuelleZeile.charAt(j);
				}
				else
					if (!aktuelleZahl.isEmpty())
					{
						if (i > 0)
						{
							istGueltig =
								istZeichenVorhanden(eingabe.get(i - 1), startIndex, startIndex + aktuelleZahl.length());
						}
						if (i < eingabe.size() - 1 && !istGueltig)
						{
							istGueltig =
								istZeichenVorhanden(eingabe.get(i + 1), startIndex, startIndex + aktuelleZahl.length());
						}
						if (!istGueltig)
						{
							istGueltig =
								istZeichenVorhanden(eingabe.get(i), startIndex, startIndex + aktuelleZahl.length());
						}
						if (istGueltig)
						{
							summe += Integer.parseInt(aktuelleZahl);
						}
						aktuelleZahl = "";
						istGueltig = false;
					}
			}
		}
		System.out.println(summe);
	}

	boolean istZeichenVorhanden(String zeile, int startIndex, int endIndex)
	{
		for (int i = startIndex - 1; i <= endIndex; i++)
		{
			if (i >= 0 && i < zeile.length() && zeile.charAt(i) != '.' && !Character.isDigit(zeile.charAt(i)))
			{
				return true;
			}
		}
		return false;
	}

	void berechneSumme2()
	{
		for (int i = 0; i < eingabe.size(); i++)
		{
			aktuelleZeile = eingabe.get(i);
			for (int j = 0; j < aktuelleZeile.length(); j++)
			{
				if (aktuelleZahl.isEmpty())
				{
					startIndex = j;
				}
				if (Character.isDigit(aktuelleZeile.charAt(j)))
				{
					aktuelleZahl += aktuelleZeile.charAt(j);
				}
				else
					if (!aktuelleZahl.isEmpty())
					{
						if (i > 0)
						{
							summe +=
								Integer.parseInt(aktuelleZahl) * istAsteriskVorhanden(i, startIndex, startIndex + aktuelleZahl.length());
						}
						if (i < eingabe.size() - 1 && !istGueltig)
						{
							istGueltig =
								istZeichenVorhanden(eingabe.get(i + 1), startIndex, startIndex + aktuelleZahl.length());
						}
						if (!istGueltig)
						{
							istGueltig =
								istZeichenVorhanden(eingabe.get(i), startIndex, startIndex + aktuelleZahl.length());
						}
						if (istGueltig)
						{
							summe += Integer.parseInt(aktuelleZahl);
						}
						aktuelleZahl = "";
						istGueltig = false;
					}
			}
		}
		System.out.println(summe);
	}

	// nur "unten rechts" checken
	//  XYZ*
	// *****
	//

	Integer istAsteriskVorhanden(int zeilenIndex, int startIndex, int endIndex)
	{
		if (eingabe.get(zeilenIndex).charAt(endIndex + 1) == '*')
		{
			if(getWeitereZahl(zeilenIndex, endIndex + 1) != 0){
				return getWeitereZahl(zeilenIndex, endIndex + 1);
			} else {
				for (int i = startIndex - 1; i <= endIndex; i++)
				{
					//if (i >= 0 && i < zeile.length() && zeile.charAt(i) == '*')
					//{
					//	return true;
					//}
				}
			};
		}
		return 0;
	}

	Integer getWeitereZahl(int zeilenIndex, int asteriskIndex)
	{
		int i = asteriskIndex + 1;
		String weitereZahl = "0";
		if (Character.isDigit(eingabe.get(zeilenIndex).charAt(i)))
		{
			while (Character.isDigit(eingabe.get(zeilenIndex).charAt(i)))
			{
				weitereZahl += eingabe.get(zeilenIndex).charAt(i);
				i++;
			}
		}
		return Integer.parseInt(weitereZahl);
	}
}
