package jahr2023;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand
{
	private String karten;
	private int bid;
	private List<Integer> kartenwerte;
	private int typ;

	public Hand(String karten, int bid)
	{
		this.karten = karten;
		this.kartenwerte = berechneKartenwerte(karten);
		this.typ = ermittleTyp(karten);
		this.bid = bid;
	}

	private int ermittleTyp(String karten)
	{
		Set<Character> verschiedeneKarten = new HashSet<>();
		for (int j = 0; j < karten.length(); j++)
		{
			verschiedeneKarten.add(karten.charAt(j));
		}
		int anzahlVerschiedeneKarten = verschiedeneKarten.size();
		int anzahlMaximaleKarte = ermittleWieOftKarteMaximalVorkommt(verschiedeneKarten, karten);
		if (anzahlVerschiedeneKarten == 1)
		{
			return 6;
		}
		else
			if (anzahlVerschiedeneKarten == 2)
			{
				if (anzahlMaximaleKarte == 4)
				{
					return 5;
				}
				else
				{
					return 4;
				}
			}
			else
				if (anzahlVerschiedeneKarten == 3)
				{
					if (anzahlMaximaleKarte == 3)
					{
						return 3;
					}
					else
					{
						return 2;
					}
				}
				else
					if (anzahlVerschiedeneKarten == 4)
					{
						return 1;
					}
					else
					{
						return 0;
					}
	}

	private int ermittleWieOftKarteMaximalVorkommt(Set<Character> verschiedeneKarten, String hand)
	{
		int maximaleDopplungen = 1;
		for (Character karte : verschiedeneKarten)
		{
			int dopplungen = 0;
			for (int i = 0; i < hand.length(); i++)
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

	private List<Integer> berechneKartenwerte(String karten)
	{
		List<Integer> handAlsInteger = new ArrayList<>();
		for (int i = 0; i < karten.length(); i++)
		{
			char aktuelleKarte = karten.charAt(i);
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
						break;
					case 'K':
						handAlsInteger.add(13);
						break;
					case 'Q':
						handAlsInteger.add(12);
						break;
					case 'J':
						handAlsInteger.add(11);
						break;
					case 'T':
						handAlsInteger.add(10);
						break;
				}
			}
		}
		return handAlsInteger;
	}

	public int gibWertDerKarte(int stelle)
	{
		if (stelle < kartenwerte.size())
		{
			return kartenwerte.get(stelle);
		}
		return -1;
	}

	public int gibTyp()
	{
		return typ;
	}

	public int gibBid()
	{
		return bid;
	}
}
