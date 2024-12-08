package jahr2024.tag06;

import jahr2024.tag05.Tag05;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Tag06
{
	private Scanner scanner;

	private List<String> karte;
	private int positionZeile;
	private int positionSpalte;
	boolean istImFeld;

	public Tag06() throws FileNotFoundException
	{
		scanner = new Scanner(new FileReader("src/jahr2024/tag06/input.txt"));
		karte = new ArrayList<>();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Aufgabe 1: " + new Tag06().loeseAufgabe01());
	}

	private int loeseAufgabe01()
	{
		leseInputEin();
		istImFeld = true;
		macheSchritt();
		return zaehleAnzahlXInKarte();
	}

	private int zaehleAnzahlXInKarte()
	{
		int anzahlX = 0;
		for (String zeile : karte)
		{
			for (int i = 0; i < zeile.length(); i++)
			{
				if (zeile.charAt(i) == 'X')
				{
					anzahlX++;
				}
			}
		}
		return anzahlX;
	}

	private void macheSchritt()
	{
		char richtung = ermittleZeichenAnStelle(positionZeile, positionSpalte);
		System.out.println(richtung);
		if (istImFeld)
		{
			if (richtung == '^')
			{
				if (ermittleZeichenAnStelle(positionZeile - 1, positionSpalte) == '#')
				{
					String zeile =
						karte.get(positionZeile).substring(0, positionSpalte - 1) + ">" + karte.get(
							positionZeile).substring(positionSpalte + 1);
					karte.set(positionZeile, zeile);
					macheSchritt();
				}
				else
				{
					karte.set(positionZeile, karte.get(positionZeile).replace('^', 'X'));
					if (positionZeile != 0)
					{
						String zeile =
							karte.get(positionZeile - 1).substring(0, positionSpalte - 1) + "^" + karte.get(
								positionZeile - 1).substring(positionSpalte + 1);
						karte.set(positionZeile - 1, zeile);
						positionZeile--;
						macheSchritt();
					}
					else
					{
						istImFeld = false;
					}
				}
			}

			if (richtung == 'V')
			{
				if (ermittleZeichenAnStelle(positionZeile + 1, positionSpalte) == '#')
				{
					String zeile =
						karte.get(positionZeile).substring(0, positionSpalte - 1) + "<" + karte.get(
							positionZeile).substring(positionSpalte + 1);
					karte.set(positionZeile, zeile);
					macheSchritt();
				}
				else
				{
					karte.set(positionZeile, karte.get(positionZeile).replace('V', 'X'));
					if (positionZeile != karte.size() - 1)
					{
						String zeile =
							karte.get(positionZeile + 1).substring(0, positionSpalte - 1) + "V" + karte.get(
								positionZeile + 1).substring(positionSpalte + 1);
						karte.set(positionZeile + 1, zeile);
						positionZeile++;
						macheSchritt();
					}
					else
					{
						istImFeld = false;
					}
				}
			}

			if (richtung == '>')
			{
				if (ermittleZeichenAnStelle(positionZeile, positionSpalte + 1) == '#')
				{
					String zeile =
						karte.get(positionZeile).substring(0, positionSpalte - 1) + "V" + karte.get(
							positionZeile).substring(positionSpalte + 1);
					karte.set(positionZeile, zeile);
					macheSchritt();
				}
				else
				{
					karte.set(positionZeile, karte.get(positionZeile).replace('>', 'X'));
					if (positionZeile != karte.get(positionZeile).length() - 1)
					{
						String zeile =
							karte.get(positionZeile).substring(0, positionSpalte) + ">" + karte.get(
								positionZeile).substring(positionSpalte + 2);
						karte.set(positionZeile, zeile);
						positionSpalte++;
						macheSchritt();
					}
					else
					{
						istImFeld = false;
					}
				}
			}

			if (richtung == '<')
			{
				if (ermittleZeichenAnStelle(positionZeile, positionSpalte - 1) == '#')
				{
					String zeile =
						karte.get(positionZeile).substring(0, positionSpalte - 1) + "^" + karte.get(
							positionZeile).substring(positionSpalte + 1);
					karte.set(positionZeile, zeile);
					macheSchritt();
				}
				else
				{
					karte.set(positionZeile, karte.get(positionZeile).replace('<', 'X'));
					if (positionZeile != 0)
					{
						String zeile =
							karte.get(positionZeile).substring(0, positionSpalte - 2) + "<" + karte.get(
								positionZeile).substring(positionSpalte);
						karte.set(positionZeile, zeile);
						positionSpalte++;
						macheSchritt();
					}
					else
					{
						istImFeld = false;
					}
				}
			}
		}
	}

	private Character ermittleZeichenAnStelle(int zeile, int spalte)
	{
		return karte.get(zeile).charAt(spalte);
	}

	private void leseInputEin()
	{
		String zeile;
		int zeilennummer = 0;
		while (scanner.hasNext())
		{
			zeile = scanner.nextLine();
			karte.add(zeile);
			if (zeile.contains("^"))
			{
				positionZeile = zeilennummer;
				positionSpalte = zeile.indexOf("^");
			}
			zeilennummer++;
		}
	}
}
