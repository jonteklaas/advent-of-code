package jahr2023.tag09;

import java.util.ArrayList;
import java.util.List;

public class Zahlenzeile
{
	private List<Integer> zeile;

	public Zahlenzeile(List<Integer> zeile)
	{
		this.zeile = new ArrayList<>(zeile);
	}

	public Zahlenzeile getNaechsteZeile()
	{
		List<Integer> naechsteZeile = new ArrayList<>(List.of());
		for (int i = 0; i < zeile.size() - 1; i++)
		{
			naechsteZeile.add(zeile.get(i + 1) - zeile.get(i));
		}
		return new Zahlenzeile(naechsteZeile);
	}

	public void berechneLetzteZahl(Integer letzteZahlDerUnterzeile)
	{
		zeile.add(zeile.getLast() + letzteZahlDerUnterzeile);
	}

	public boolean enthaeltAndereZahlAls0()
	{
		for (int i = 0; i < zeile.size(); i++)
		{
			if (!zeile.get(i).equals(0))
			{
				return true;
			}
		}
		return false;
	}

	public void haengeZahlAn(Integer zahl)
	{
		zeile.add(zahl);
	}

	public List<Integer> getZeile()
	{
		return zeile;
	}

}
