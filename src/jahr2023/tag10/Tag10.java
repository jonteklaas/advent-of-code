package jahr2023.tag10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tag10
{
	private Scanner scanner;
	private int zeilennummer;
	private int spaltennummer;
	private List<List<Character>> feld;

	public Tag10() throws FileNotFoundException
	{
		scanner = new Scanner(new FileReader("src/jahr2023/resources/Tag10.txt"));
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Aufgabe 1: " + new Tag10().loeseAufgabe1());
	}

	private int loeseAufgabe1()
	{
		feld = new ArrayList<>();
		int i = 0;
		while (scanner.hasNext())
		{
			feld.add(leseZeile(scanner.nextLine(), i++));
		}
		return berechneWeg(findeStartrichtungspaar(zeilennummer, spaltennummer), 1);
	}

	private int berechneWeg(Stellenpaar aktuelleStellen, int abstandZumStart)
	{
		Stelle stelle1 = berechneNaechsteStelle(aktuelleStellen.getStelle1());
		Stelle stelle2 = berechneNaechsteStelle(aktuelleStellen.getStelle1());
		System.out.println(stelle1.getZeichen() + " " + stelle2.getZeichen());
		if (stelle1.getZeilennumer() == stelle2.getZeilennumer()
			&& stelle1.getSpaltennumer() == stelle2.getSpaltennumer())
		{
			return abstandZumStart + 1;
		}
		return berechneWeg(new Stellenpaar(stelle1, stelle2), abstandZumStart + 1);
	}

	private Stelle berechneNaechsteStelle(Stelle stelle)
	{
		char zeichen = stelle.getZeichen();
		if (zeichen == '|')
		{
			if (stelle.getZeilennumer() + 1 == stelle.getVorherigeZeilennummer())
			{
				return new Stelle(feld.get(stelle.getZeilennumer() + 1).get(stelle.getSpaltennumer()),
					stelle.getZeilennumer() + 1, stelle.getSpaltennumer(),
					stelle.getZeilennumer(), stelle.getSpaltennumer());
			}
			return new Stelle(feld.get(stelle.getZeilennumer() - 1)
				.get(stelle.getSpaltennumer()), stelle.getZeilennumer() - 1, stelle.getSpaltennumer(),
				stelle.getZeilennumer(), stelle.getSpaltennumer());
		}
		if (zeichen == '-')
		{
			if (stelle.getSpaltennumer() + 1 == stelle.getVorherigeSpaltennummer())
			{
				return new Stelle(feld.get(stelle.getZeilennumer()).get(stelle.getSpaltennumer() + 1),
					stelle.getZeilennumer(), stelle.getSpaltennumer() + 1,
					stelle.getZeilennumer(), stelle.getSpaltennumer());
			}
			return new Stelle(feld.get(stelle.getZeilennumer()).get(stelle.getSpaltennumer() - 1),
				stelle.getZeilennumer(), stelle.getSpaltennumer() - 1,
				stelle.getZeilennumer(), stelle.getSpaltennumer());
		}
		if (zeichen == 'F')
		{
			if (stelle.getZeilennumer() == stelle.getVorherigeZeilennummer())
			{
				return new Stelle(feld.get(stelle.getZeilennumer() + 1).get(stelle.getSpaltennumer()),
					stelle.getZeilennumer() + 1, stelle.getSpaltennumer(),
					stelle.getZeilennumer(), stelle.getSpaltennumer());
			}
			return new Stelle(feld.get(stelle.getZeilennumer()).get(stelle.getSpaltennumer() + 1),
				stelle.getZeilennumer(), stelle.getSpaltennumer() + 1,
				stelle.getZeilennumer(), stelle.getSpaltennumer());
		}
		if (zeichen == '7')
		{
			if (stelle.getZeilennumer() == stelle.getVorherigeZeilennummer())
			{
				return new Stelle(feld.get(stelle.getZeilennumer() + 1).get(stelle.getSpaltennumer()),
					stelle.getZeilennumer() + 1, stelle.getSpaltennumer(),
					stelle.getZeilennumer(), stelle.getSpaltennumer());
			}
			return new Stelle(feld.get(stelle.getZeilennumer()).get(stelle.getSpaltennumer() - 1),
				stelle.getZeilennumer(), stelle.getSpaltennumer() - 1,
				stelle.getZeilennumer(), stelle.getSpaltennumer());
		}
		if (zeichen == 'L')
		{
			if (stelle.getZeilennumer() == stelle.getVorherigeZeilennummer())
			{
				return new Stelle(feld.get(stelle.getZeilennumer() - 1).get(stelle.getSpaltennumer()),
					stelle.getZeilennumer() - 1, stelle.getSpaltennumer(),
					stelle.getZeilennumer(), stelle.getSpaltennumer());
			}
			return new Stelle(feld.get(stelle.getZeilennumer()).get(stelle.getSpaltennumer() + 1),
				stelle.getZeilennumer(), stelle.getSpaltennumer() + 1,
				stelle.getZeilennumer(), stelle.getSpaltennumer());
		}
		if (zeichen == 'J')
		{
			if (stelle.getZeilennumer() == stelle.getVorherigeZeilennummer())
			{
				return new Stelle(feld.get(stelle.getZeilennumer() - 1).get(stelle.getSpaltennumer()),
					stelle.getZeilennumer() - 1, stelle.getSpaltennumer(),
					stelle.getZeilennumer(), stelle.getSpaltennumer());
			}
		}
		return new Stelle(feld.get(stelle.getZeilennumer()).get(stelle.getSpaltennumer() - 1), stelle.getZeilennumer(), stelle.getSpaltennumer() - 1,
			stelle.getZeilennumer(), stelle.getSpaltennumer());
	}

	private Stellenpaar findeStartrichtungspaar(int zeilennummer, int spaltennummer)
	{
		Stelle stelle1 = new Stelle('X', zeilennummer, spaltennummer, zeilennummer, spaltennummer);
		Stelle stelle2 = new Stelle('X', zeilennummer, spaltennummer, zeilennummer, spaltennummer);
		boolean stelle1belegt = false;
		if (feld.get(zeilennummer - 1).get(spaltennummer) == '|' || feld.get(zeilennummer - 1).get(spaltennummer) == 'F'
			|| feld.get(zeilennummer - 1).get(spaltennummer) == '7')
		{
			stelle1 =
				new Stelle(feld.get(zeilennummer - 1).get(spaltennummer), zeilennummer - 1, spaltennummer, zeilennummer,
					spaltennummer);
			stelle1belegt = true;
		}
		if (feld.get(zeilennummer).get(spaltennummer + 1) == '-' || feld.get(zeilennummer).get(spaltennummer + 1) == 'J'
			|| feld.get(zeilennummer).get(spaltennummer + 1) == '7')
		{
			stelle2 =
				new Stelle(feld.get(zeilennummer).get(spaltennummer + 1), zeilennummer, spaltennummer + 1, zeilennummer,
					spaltennummer);
			if (!stelle1belegt)
			{
				stelle1 = new Stelle(feld.get(zeilennummer).get(spaltennummer + 1), zeilennummer, spaltennummer + 1,
					zeilennummer, spaltennummer);
				stelle1belegt = true;
			}
		}
		if (feld.get(zeilennummer + 1).get(spaltennummer) == '|' || feld.get(zeilennummer + 1).get(spaltennummer) == 'J'
			|| feld.get(zeilennummer + 1).get(spaltennummer) == 'L')
		{
			stelle2 =
				new Stelle(feld.get(zeilennummer + 1).get(spaltennummer), zeilennummer + 1, spaltennummer, zeilennummer,
					spaltennummer);
			if (!stelle1belegt)
			{
				stelle1 = new Stelle(feld.get(zeilennummer + 1).get(spaltennummer), zeilennummer + 1, spaltennummer,
					zeilennummer, spaltennummer);
			}
		}
		if (feld.get(zeilennummer).get(spaltennummer - 1) == '-' || feld.get(zeilennummer).get(spaltennummer - 1) == 'F'
			|| feld.get(zeilennummer).get(spaltennummer - 1) == 'L')
		{
			stelle2 =
				new Stelle(feld.get(zeilennummer).get(spaltennummer - 1), zeilennummer, spaltennummer - 1, zeilennummer,
					spaltennummer);
		}
		return new Stellenpaar(stelle1, stelle2);
	}

	private List<Character> leseZeile(String zeile, int zeilennummer)
	{
		List<Character> zeichenZeile = new ArrayList<>();
		char zeichen;
		for (int i = 0; i < zeile.length(); i++)
		{
			zeichen = zeile.charAt(i);
			if (zeichen == 'S')
			{
				this.zeilennummer = zeilennummer;
				this.spaltennummer = i;
			}
			zeichenZeile.add(zeichen);
		}
		return zeichenZeile;
	}
}
