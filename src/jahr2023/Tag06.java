package jahr2023;

import java.util.ArrayList;
import java.util.List;

public class Tag06
{
	public static void main(String[] args)
	{
		System.out.println(
			new Tag06().loeseAufgabe1(new int[] { 35, 69, 68, 87 }, new int[] { 213, 1168, 1086, 1248 }));
		System.out.println(new Tag06().loeseAufgabe2(35696887, 213116810861248L));
	}

	int loeseAufgabe1(int[] zeiten, int[] streckenRekorde)
	{
		int produktDerAnzahlMoeglicherRekorde = 1;
		for (int i = 0; i < zeiten.length; i++)
		{
			produktDerAnzahlMoeglicherRekorde *=
				ermittleAnzahlMoeglicheRekorde(ermittleMoeglicheStrecken(zeiten[i]), (long) streckenRekorde[i]);
		}
		return produktDerAnzahlMoeglicherRekorde;
	}

	int loeseAufgabe2(int zeit, Long strecke){
		return ermittleAnzahlMoeglicheRekorde(ermittleMoeglicheStrecken(zeit), strecke);
	}

	List<Long> ermittleMoeglicheStrecken(int zeit)
	{
		List<Long> strecken = new ArrayList<>();
		for (long i = 0L; i <= zeit; i++)
		{
			strecken.add(((zeit - i) * i));
		}
		return strecken;
	}

	int ermittleAnzahlMoeglicheRekorde(List<Long> moeglicheStrecken, Long aktuellerRekord)
	{
		int anzahlMoeglicheRekorde = 0;
		for (long moeglicheStrecke : moeglicheStrecken)
		{
			if (moeglicheStrecke > aktuellerRekord)
			{
				anzahlMoeglicheRekorde++;
			}
		}
		return anzahlMoeglicheRekorde;
	}
}
