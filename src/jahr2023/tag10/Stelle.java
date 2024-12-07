package jahr2023.tag10;

public class Stelle
{
	private char zeichen;
	private int zeilennumer;
	private int spaltennumer;
	private int vorherigeZeilennummer;
	private int vorherigeSpaltennummer;

	public Stelle(char zeichen, int zeilennumer, int spaltennumer, int vorherigeZeilennummer,
		int vorherigeSpaltennummer)
	{
		this.zeichen = zeichen;
		this.zeilennumer = zeilennumer;
		this.spaltennumer = spaltennumer;
		this.vorherigeZeilennummer = vorherigeZeilennummer;
		this.vorherigeSpaltennummer = vorherigeSpaltennummer;
	}

	public char getZeichen()
	{
		return zeichen;
	}

	public int getZeilennumer()
	{
		return zeilennumer;
	}

	public int getSpaltennumer()
	{
		return spaltennumer;
	}

	public int getVorherigeZeilennummer()
	{
		return vorherigeZeilennummer;
	}

	public int getVorherigeSpaltennummer()
	{
		return vorherigeSpaltennummer;
	}
}
