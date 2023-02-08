package masterMind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Giudice {
    public final static int LUNGHEZZA = 4;
    public final static int TENTATIVI = 10;
    public final static ArrayList<Character> CHARS = new ArrayList<Character>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));

    public static boolean valida(String stringa) {
        return (stringa.length() == LUNGHEZZA && caratteriGiusti(stringa)) && caratteriNonRipetuti(stringa);
    }

    private static boolean caratteriGiusti(String guess) {
        for(int i=0; i<LUNGHEZZA; i++) {
            if(!CHARS.contains(guess.charAt(i))) return false;
        }
        return true;
    }

    private static boolean caratteriNonRipetuti(String guess) {
        for(int i=0; i<LUNGHEZZA-1; i++) {
            if(guess.lastIndexOf(guess.charAt(i)) != i) return false;
        }
        return true;
    }

    public static int numBulls(String target, String guess) {
        int bulls=0;
        for(int i=0; i<LUNGHEZZA; i++) {
            if(guess.charAt(i) == target.charAt(i)) bulls++;
        }
        return bulls;
    }

    public static int numMaggots(String target, String guess) {
        int maggots=0;
        for(int i=0; i<LUNGHEZZA; i++) {
            for(int j=0; j<LUNGHEZZA; j++) {
                if(j==i) continue;
                if(guess.charAt(i) == target.charAt(j)) {
                    maggots++;
                    break;
                }
            }
        }

        return maggots;
    }

    public static String genTarget() {
        Random random = new Random();
        char n;
        String target = "";

        for(int i=0; i<LUNGHEZZA; i++) {
            do {
                n = (char)(random.nextInt(10) + '0');	//genera caratteri da '0' a '9'
            }while(target.indexOf(n) != -1);	//se non e' presente in target

            target = target.concat(String.valueOf(n));
        }
        System.out.println(target);
        return target;
    }
}
