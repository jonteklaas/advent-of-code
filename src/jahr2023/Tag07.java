package jahr2023;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Tag07
{
	Scanner scanner = new Scanner(new FileReader("src/jahr2023/resources/Tag07.txt"));

	List<String> eingabe = new ArrayList<>();
	List<String> haende = new ArrayList<>();

	List<List<Integer>> kartenwerte = new ArrayList<>();

	List<List<Integer>> indizesTypen = new ArrayList<>();
	static final int FUENF_GLEICHE = 6;
	static final int VIER_GLEICHE = 5;
	static final int FULL_HOUSE = 4;
	static final int DREI_GLEICHE = 3;
	static final int ZWEI_PAARE = 2;
	static final int EIN_PAAR = 1;
	static final int KEINE_GLEICHEN = 0;

	public Tag07() throws FileNotFoundException
	{
		while (scanner.hasNext())
		{
			eingabe.add(scanner.nextLine());
		}
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println(new Tag07().loeseAufgabe1());
	}

	int loeseAufgabe1()
	{
		int summe = 0;
		indizesTypen.add(new ArrayList<>());
		indizesTypen.add(new ArrayList<>());
		indizesTypen.add(new ArrayList<>());
		indizesTypen.add(new ArrayList<>());
		indizesTypen.add(new ArrayList<>());
		indizesTypen.add(new ArrayList<>());
		indizesTypen.add(new ArrayList<>());
		String[] zeile;
		List<Integer> bids = new ArrayList<>();
		for (int i = 0; i < eingabe.size(); i++)
		{
			zeile = eingabe.get(i).split(" ");
			haende.add(zeile[0]);
			bids.add(Integer.parseInt(zeile[1]));
		}
		for (int i = 0; i < haende.size(); i++)
		{
			kartenwerte.add(Collections.singletonList(i));
			kartenwerte.set(i, wandleHandInIntegerUm(haende.get(i)));
		}
		List<Integer> typenwerte = ermittleIndizesTypenwerte(haende);

		int anzahlHaende = haende.size();
		List<String> haendeZumRemoven = haende;
		for(int i = 0; i < anzahlHaende; i++){

			int summand = (haende.size() - i) * bids.get(ermittleIndexDerHoechstenVerbliebendenHand(typenwerte, haendeZumRemoven));
			summe += summand;
		}
		return summe;
	}

	private List<Integer> wandleHandInIntegerUm(String hand)
	{
		List<Integer> handAlsInteger = new ArrayList<>();
		for (int i = 0; i < hand.length(); i++)
		{
			char aktuelleKarte = hand.charAt(i);
			if (Character.isDigit(aktuelleKarte))
			{
				handAlsInteger.add(Integer.parseInt(Character.toString(aktuelleKarte)));
			}
			else
			{

				switch (aktuelleKarte)
				{
					case 'A':
						handAlsInteger.add(14);
					case 'K':
						handAlsInteger.add(13);
					case 'Q':
						handAlsInteger.add(12);
					case 'J':
						handAlsInteger.add(11);
					case 'T':
						handAlsInteger.add(10);
				}
			}

		}
		return handAlsInteger;
	}

	int ermittleIndexDerHoechstenVerbliebendenHand(List<Integer> typenWerte, List<String> haendeZumRemoven)
	{
		for (int i = FUENF_GLEICHE; i >= 0; i--)
		{
			if (!indizesTypen.get(i).isEmpty())
			{
				return vergleicheHaende(indizesTypen.get(i), 0, haendeZumRemoven);
			}
		}
		return 1;
	}

	int vergleicheHaende(List<Integer> indizesTyp, int stelleZumVergleichen, List<String> haendeZumRemoven)
	{
		int hoechsteHand = 0;
		List<Integer> hoechsteHaende = new ArrayList<>();
		for (int i = 0; i < indizesTyp.size(); i++)
		{
			hoechsteHand =
				Math.max(kartenwerte.get(indizesTyp.get(i)).get(stelleZumVergleichen), hoechsteHand);
		}
		for (int i = 0; i < indizesTyp.size(); i++)
		{
			if (kartenwerte.get(indizesTyp.get(i)).get(stelleZumVergleichen) >= hoechsteHand)
			{
				hoechsteHaende.add(indizesTyp.get(i));
			}
		}
		if (hoechsteHaende.size() == 1)
		{
			kartenwerte.remove(kartenwerte.get(hoechsteHaende.get(0)));
			return hoechsteHaende.get(0);
		}
		return vergleicheHaende(hoechsteHaende, stelleZumVergleichen + 1, haendeZumRemoven);
	}

	List<Integer> ermittleIndizesTypenwerte(List<String> haende)
	{
		List<Integer> typenwerte = new ArrayList<>();
		for (int i = 0; i < haende.size(); i++)
		{
			String hand = haende.get(i);
			Set<Character> verschiedeneKarten = new HashSet<>();
			for (int j = 0; j < hand.length(); j++)
			{
				verschiedeneKarten.add(hand.charAt(j));
			}
			int anzahlVerschiedeneKarten = verschiedeneKarten.size();
			int anzahlMaximaleKarte = ermittleWieOftKartenInHandMaximalVorkommen(verschiedeneKarten, hand);
			if (anzahlVerschiedeneKarten == 1)
			{
				typenwerte.add(FUENF_GLEICHE);
				indizesTypen.get(FUENF_GLEICHE).add(i);
			}
			if (anzahlVerschiedeneKarten == 2)
			{
				if (anzahlMaximaleKarte == 4)
				{
					typenwerte.add(VIER_GLEICHE);
					indizesTypen.get(VIER_GLEICHE).add(i);
				}
				typenwerte.add(FULL_HOUSE);
				indizesTypen.get(FULL_HOUSE).add(i);
			}
			if (anzahlVerschiedeneKarten == 3)
			{
				if (anzahlMaximaleKarte == 3)
				{
					typenwerte.add(DREI_GLEICHE);
					indizesTypen.get(DREI_GLEICHE).add(i);
				}
				typenwerte.add(ZWEI_PAARE);
				indizesTypen.get(ZWEI_PAARE).add(i);
			}
			if (anzahlVerschiedeneKarten == 4)
			{
				typenwerte.add(EIN_PAAR);
				indizesTypen.get(EIN_PAAR).add(i);
			}
			typenwerte.add(KEINE_GLEICHEN);
			indizesTypen.get(KEINE_GLEICHEN).add(i);
		}
		return typenwerte;
	}

	int ermittleWieOftKartenInHandMaximalVorkommen(Set<Character> verschiedeneKarten, String hand)
	{
		int maximaleDopplungen = 1;
		for (int i = 0; i < hand.length(); i++)
		{
			int dopplungen = 0;
			for (Character karte : verschiedeneKarten)
			{
				if (karte.equals(hand.charAt(i)))
				{
					dopplungen++;
				}
			}
			maximaleDopplungen = Math.max(dopplungen, maximaleDopplungen);
		}
		return maximaleDopplungen;
	}
}
