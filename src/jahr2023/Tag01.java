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
	String firstNumber = "";
	String lastNumber = "";
	String text;
	List<String> numbers = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

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
			text = scanner.nextLine();
			for (int i = 0; i < text.length(); i++)
			{
				if (Character.isDigit(text.charAt(i)))
				{
					if (firstNumber.isEmpty())
					{
						firstNumber = String.valueOf(text.charAt(i));
					}
					lastNumber = String.valueOf(text.charAt(i));
				}
			}
			summe += Integer.parseInt(firstNumber + lastNumber);
			firstNumber = "";
			lastNumber = "";
		}
		System.out.println("Summe 1: " + summe);
	}

	void berechneSumme2()
	{
		while (scanner.hasNext())
		{
			text = scanner.nextLine();
			for (int i = 0; i < text.length(); i++)
			{
				if (Character.isDigit(text.charAt(i)))
				{
					fuegeZahlHinzu(Integer.parseInt(text.charAt(i) + ""));
				}
				else
					if (i >= 2)
					{
						if (numbers.contains("" + text.charAt(i - 2) + text.charAt(i - 1) + text.charAt(i)))
						{
							fuegeZahlHinzu(
								numbers.indexOf("" + text.charAt(i - 2) + text.charAt(i - 1) + text.charAt(i)) + 1);
						}
					}
				if (i >= 3)
				{
					if (numbers.contains(
						"" + text.charAt(i - 3) + text.charAt(i - 2) + text.charAt(i - 1) + text.charAt(i)))
					{
						fuegeZahlHinzu(numbers.indexOf(
							"" + text.charAt(i - 3) + text.charAt(i - 2) + text.charAt(i - 1) + text.charAt(i)) + 1);

					}
				}
				if (i >= 4)
				{
					if (numbers.contains(
						"" + text.charAt(i - 4) + text.charAt(i - 3) + text.charAt(i - 2) + text.charAt(i - 1)
							+ text.charAt(i)))
					{
						fuegeZahlHinzu(numbers.indexOf(
							"" + text.charAt(i - 4) + text.charAt(i - 3) + text.charAt(i - 2) + text.charAt(i - 1)
								+ text.charAt(i)) + 1);
					}
				}
			}
			summe += Integer.parseInt(firstNumber + lastNumber);
			firstNumber = "";
			lastNumber = "";
		}
		System.out.println("Summe 2: " + summe);
	}

	void fuegeZahlHinzu(int zahl)
	{
		if (firstNumber.isEmpty())
		{
			firstNumber = String.valueOf(zahl);
		}
		lastNumber = String.valueOf(zahl);
	}
}
