package jahr2023;

import java.util.ArrayList;
import java.util.List;

public class Tag06
{
	public static void main(String[] args)
	{
		System.out.println(
			new Tag06().loeseAufgabe1(new int[] { 35, 69, 68, 87 }, new int[] { 213, 1168, 1086, 1248 }));
	}

	int loeseAufgabe1(int[] zeiten, int[] streckenRekorde)
	{
		int produktDerAnzahlMoeglicherRekorde = 1;
		for (int i = 0; i < zeiten.length; i++)
		{
			produktDerAnzahlMoeglicherRekorde *=
				ermittleAnzahlMoeglicheRekorde(ermittleMoeglicheStrecken(zeiten[i]), streckenRekorde[i]);
		}
		return produktDerAnzahlMoeglicherRekorde;
	}

	List<Integer> ermittleMoeglicheStrecken(int zeit)
	{
		List<Integer> strecken = new ArrayList<>();
		for (int i = 0; i <= zeit; i++)
		{
			strecken.add((zeit - i) * i);
		}
		return strecken;
	}

	int ermittleAnzahlMoeglicheRekorde(List<Integer> moeglicheStrecken, int aktuellerRekord)
	{
		int anzahlMoeglicheRekorde = 0;
		for (int moeglicheStrecke : moeglicheStrecken)
		{
			if (moeglicheStrecke > aktuellerRekord)
			{
				anzahlMoeglicheRekorde++;
			}
		}
		return anzahlMoeglicheRekorde;
	}
}
