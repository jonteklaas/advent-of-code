package jahr2024.tag13;

import jahr2024.tag05.Tag05;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Tag13
{
    private Scanner scanner;
    private List<Spielautomat> spielautomaten;

    public Tag13() throws FileNotFoundException
    {
        scanner = new Scanner(new FileReader("src/jahr2024/tag13/input.txt"));
        spielautomaten = new ArrayList<>();
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println("Aufgabe 1: " + new Tag13().loeseAufgabe01());
    }

    private int loeseAufgabe01()
    {
        leseInputEin();
        int ergebnis = 0;
        for(Spielautomat spielautomat : spielautomaten)
        {
           ergebnis += spielautomat.berechneGuenstigstenPreis();
        }
        return ergebnis;
    }


    private void leseInputEin()
    {
        String zeile;
        while (scanner.hasNext())
        {
            HashMap<Character, Integer> knopfA = new HashMap<>();
            HashMap<Character, Integer> knopfB = new HashMap<>();
            HashMap<Character, Integer> preis = new HashMap<>();
            zeile = scanner.nextLine();
            knopfA.put('X', Integer.parseInt(zeile.substring(zeile.indexOf('+'), zeile.indexOf(','))));
            knopfA.put('Y', Integer.parseInt(zeile.substring(zeile.lastIndexOf('+') + 1)));
            zeile = scanner.nextLine();
            knopfB.put('X', Integer.parseInt(zeile.substring(zeile.indexOf('+'), zeile.indexOf(','))));
            knopfB.put('Y', Integer.parseInt(zeile.substring(zeile.lastIndexOf('+') + 1)));
            zeile = scanner.nextLine();
            preis.put('X', Integer.parseInt(zeile.substring(zeile.indexOf('=') + 1, zeile.indexOf(','))));
            preis.put('Y', Integer.parseInt(zeile.substring(zeile.lastIndexOf('=') + 1)));
            if(scanner.hasNext())
            {
                scanner.nextLine();
            }
            spielautomaten.add(new Spielautomat(knopfA, knopfB, preis));
        }
    }
}
