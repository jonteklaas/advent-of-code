package jahr2024.tag04;

import jahr2024.tag03.Tag03;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tag04
{
	private Scanner scanner;
	private List<List<Character>> feld;

	public Tag04() throws FileNotFoundException
	{
		scanner = new Scanner(new FileReader("src/jahr2024/tag04/input.txt"));
		feld = new ArrayList<>();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Aufgabe 1: " + new Tag04().loeseAufgabe01());
	}

	private int loeseAufgabe01()
	{
		leseInputEin();
		int anzahlXmas = 0;
		for (int i = 0; i < feld.size(); i++)
		{
			for (int j = 0; j < feld.get(i).size(); j++)
			{
				anzahlXmas += anzahlXmasDieHierStarten(i, j);
			}
		}
		return anzahlXmas;
	}

	private int anzahlXmasDieHierStarten(int i, int j)
	{
		int anzahlXmasDieHierStarten = 0;
		if (!feld.get(i).get(j).equals('X'))
		{
			return anzahlXmasDieHierStarten;
		}
		return 0;
	}

	private void leseInputEin()
	{
		while (scanner.hasNext())
		{
			feld.add(scanner.nextLine().chars().mapToObj(c -> (char) c).toList());
		}
	}
}
