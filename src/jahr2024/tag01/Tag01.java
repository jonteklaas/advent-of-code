package jahr2024.tag01;

import javax.print.DocFlavor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.IntStream;

public class Tag01
{
	private Scanner scanner;
	List<String> beideSpaltenDerAktuellenZeile;
	private List<Integer> linkeSpalte;
	private List<Integer> rechteSpalte;
	private List<Integer> differenzen;

	public Tag01() throws FileNotFoundException
	{
		scanner = new Scanner(new FileReader("src/jahr2024/tag01/input.txt"));
		beideSpaltenDerAktuellenZeile = new ArrayList<>();
		linkeSpalte = new ArrayList<>();
		rechteSpalte = new ArrayList<>();
		differenzen = new ArrayList<>();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Aufgabe 1: " + new Tag01().loeseAufgabe01());
		System.out.println("Aufgabe 2: " + new Tag01().loeseAufgabe02());
	}

	public int loeseAufgabe01()
	{
		leseInputEin();
		sortiereSpaltenlisten();
		for (int i = 0; i < linkeSpalte.size(); i++)
		{
			int differenz = rechteSpalte.get(i) - linkeSpalte.get(i);
			if (differenz < 0)
			{
				differenz *= -1;
			}
			differenzen.add(differenz);
		}
		Integer summeDifferenzen = 0;
		for (Integer differenz : differenzen)
		{
			summeDifferenzen += differenz;
		}
		return summeDifferenzen;
	}

	public int loeseAufgabe02()
	{
		leseInputEin();
		sortiereSpaltenlisten();
		int ergebnis = 0;
		int aktuellerSummand;
		int i = 1;
		while (i < linkeSpalte.size())
		{
			aktuellerSummand = ermittleWieOftDieZahlInDerRechtenSpalteVorkommt(linkeSpalte.get(i - 1)) * linkeSpalte.get(i - 1);
			ergebnis += aktuellerSummand;
			while (i < linkeSpalte.size() && Objects.equals(linkeSpalte.get(i), linkeSpalte.get(i - 1)))
			{
				ergebnis += aktuellerSummand;
				i++;
			}
			i++;
		}
		return ergebnis;
	}

	private int ermittleWieOftDieZahlInDerRechtenSpalteVorkommt(Integer zahl)
	{
		return (int) rechteSpalte.stream().filter(z -> Objects.equals(z, zahl)).count();
	}

	private void sortiereSpaltenlisten()
	{
		linkeSpalte = linkeSpalte.stream().sorted().toList();
		rechteSpalte = rechteSpalte.stream().sorted().toList();
	}

	private void leseInputEin()
	{
		while (scanner.hasNext())
		{
			beideSpaltenDerAktuellenZeile = Arrays.stream(scanner.nextLine().split("   ")).toList();
			linkeSpalte.add(Integer.valueOf(beideSpaltenDerAktuellenZeile.get(0)));
			rechteSpalte.add(Integer.valueOf(beideSpaltenDerAktuellenZeile.get(1)));
		}
	}
}
