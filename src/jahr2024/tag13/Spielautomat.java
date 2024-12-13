package jahr2024.tag13;

import java.util.HashMap;

public class Spielautomat {
    private HashMap<Character, Integer> knopfA;
    private HashMap<Character, Integer> knopfB;
    private HashMap<Character, Integer> preis;

    public Spielautomat(HashMap<Character, Integer> knopfA, HashMap<Character, Integer> knopfB, HashMap<Character, Integer> preis) {
        this.knopfA = knopfA;
        this.knopfB = knopfB;
        this.preis = preis;
    }

    public int berechneGuenstigstenPreis()
    {
        int a = 0;
        int b = 0;
        for (; knopfA.get('X') * a < preis.get('X'); a++)
        {
            if ((preis.get('X') - (knopfA.get('X') * a)) % knopfB.get('X') == 0)
            {
                b = preis.get('X') - (knopfA.get('X') * a) / knopfB.get('X');
                if (preis.get('Y') - (knopfA.get('Y') * a) - (knopfB.get('Y') * b) == 0)
                {
                   return a * 3 + b;
                }
            }
        }
        return 0;
    }
}
