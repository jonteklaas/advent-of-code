package jahr2024.tag03;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tag03
{
	private Scanner scanner;
	private List<String> zeilen;

	public Tag03() throws FileNotFoundException
	{
		scanner = new Scanner(new FileReader("src/jahr2024/tag03/input.txt"));
		zeilen = new ArrayList<>();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Aufgabe 1: " + new Tag03().loeseAufgabe01());
		System.out.println("Aufgabe 2: " + new Tag03().loeseAufgabe02());
	}

	private int loeseAufgabe01()
	{
		leseInputEin();
		Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
		Matcher matcher;
		Integer summe = 0;
		List<String> aktuelleMultiplikation;
		for (String zeile : zeilen)
		{
			matcher = pattern.matcher(zeile);
			while (matcher.find())
			{
				aktuelleMultiplikation = new ArrayList<>(
					Arrays.stream(matcher.group().substring(4, matcher.group().length() - 1).split(",")).toList());
				summe +=
					Integer.parseInt(aktuelleMultiplikation.get(0)) * Integer.parseInt(aktuelleMultiplikation.get(1));
			}
		}
		return summe;
	}

	private int loeseAufgabe02()
	{
		leseInputEin();
		Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
		Matcher matcher;
		Integer summe = 0;
		List<String> aktuelleMultiplikation;
		boolean istAktiviert = true;
		for (String zeile : zeilen)
		{
			matcher = pattern.matcher(zeile);
			while (matcher.find())
			{
				aktuelleMultiplikation = new ArrayList<>(
					Arrays.stream(matcher.group().substring(4, matcher.group().length() - 1).split(",")).toList());
				summe +=
					Integer.parseInt(aktuelleMultiplikation.get(0)) * Integer.parseInt(aktuelleMultiplikation.get(1));
			}
		}
		return summe;
	}

	private void leseInputEin()
	{
		while (scanner.hasNext())
		{
			zeilen.add(scanner.nextLine());
		}
	}
}
