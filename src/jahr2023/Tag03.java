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
	}

	void berechneSumme1()
	{
		for (int i = 0; scanner.hasNext(); i++)
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
							istGueltig = istZeichenVorhanden(eingabe.get(i), startIndex, startIndex + aktuelleZahl.length());
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
}
