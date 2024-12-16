package jahr2024.tag14;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tag14
{
	private Scanner scanner;
	private List<Roboter> roboter;

	public Tag14(String pfad) throws FileNotFoundException
	{
		scanner = new Scanner(new FileReader(pfad));
		roboter = new ArrayList<>();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Aufgabe 1: " + new Tag14("src/main/jahr2024/tag14/input.txt").loeseAufgabe01());
	}

	private int loeseAufgabe01()
	{
		leseInputEin();
		for (int i = 0; i < 100; i++)
		{
			for (Roboter aktuellerRoboter : roboter)
			{
				aktuellerRoboter.bewege();
			}
		}
		List<Integer> anzahlRoboterInQuadrant = new ArrayList<>();
		anzahlRoboterInQuadrant.add(1);
		anzahlRoboterInQuadrant.add(0);
		anzahlRoboterInQuadrant.add(0);
		anzahlRoboterInQuadrant.add(0);
		anzahlRoboterInQuadrant.add(0);
		for (Roboter aktuellerRoboter : roboter)
		{
			int quadrant = aktuellerRoboter.ermittleQuadrant();
			if (quadrant != 0)
			{
				anzahlRoboterInQuadrant.set(quadrant, anzahlRoboterInQuadrant.get(quadrant) + 1);
			}
		}
		System.out.println(roboter.size());
		return anzahlRoboterInQuadrant.stream().reduce(1, (a, b) -> a * b);
	}

	private void leseInputEin()
	{
		String zeile;
		String position;
		String geschwindigkeit;
		while (scanner.hasNext())
		{
			zeile = scanner.nextLine();
			position = zeile.substring(zeile.indexOf("=") + 1, zeile.indexOf(" "));
			geschwindigkeit = zeile.substring(zeile.lastIndexOf("=") + 1);
			List<Integer> positionen = Arrays.stream(position.split(",")).map(Integer::parseInt).toList();
			List<Integer> geschwindigkeiten = Arrays.stream(geschwindigkeit.split(",")).map(Integer::parseInt).toList();
			roboter.add(
				new Roboter(positionen.get(0) + 1, positionen.get(1) + 1, geschwindigkeiten.get(0), geschwindigkeiten.get(1)));
		}
	}
}
