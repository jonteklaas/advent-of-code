package jahr2024.tag11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tag11
{
	private List<Long> steine;

	public Tag11()
	{
		steine = Arrays.asList(70949L, 6183L, 4L, 3825336L, 613971L, 0L, 15L, 182L);
	}

	public static void main(String[] args)
	{
		System.out.println("Aufgabe 1: " + new Tag11().loeseAufgabe(25));
		System.out.println("Aufgabe 2: " + new Tag11().loeseAufgabe(75));
	}

	private Integer loeseAufgabe(int anzahlBlinzeln)
	{
		for (int i = 0; i < anzahlBlinzeln; i++)
		{
			blinzle();
		}
		return steine.size();
	}

	private void blinzle()
	{
		for (int i = 0; i < steine.size(); i++)
		{
			List<Long> naechsteZahl = berechneNaechsteZahl(steine.get(i));
			steine.set(i, naechsteZahl.get(0));
			if (naechsteZahl.size() == 2)
			{
				List<Long> steineDavor = new ArrayList<>();
				for (int j = 0; j <= i; j++)
				{
					steineDavor.add(steine.get(j));
				}
				List<Long> steineDahinter = new ArrayList<>();
				for (int j = i + 1; j < steine.size(); j++)
				{
					steineDahinter.add(steine.get(j));
				}
				steine = steineDavor;
				steine.add(naechsteZahl.get(1));
				steine.addAll(steineDahinter);
				i++;
			}
		}
	}

	private List<Long> berechneNaechsteZahl(Long aktuelleZahl)
	{
		if (aktuelleZahl == 0)
		{
			return List.of(1L);
		}
		String aktuelleZahlAlsString = aktuelleZahl.toString();
		if (aktuelleZahlAlsString.length() % 2 == 0)
		{
			return List.of(
				Long.parseLong(aktuelleZahlAlsString.substring(0, aktuelleZahlAlsString.length() / 2)),
				Long.parseLong(aktuelleZahlAlsString.substring(aktuelleZahlAlsString.length() / 2)));
		}
		return List.of(aktuelleZahl * 2024L);
	}

}
