package rubrica;

import java.util.ArrayList;

public class Rubrica {
    public Rubrica(String name, int max_dim) {
        rubrica = new ArrayList<Contatto>();
        numRubriche++;
        MAX_DIM = max_dim;
        nome = name;
    }

    public Rubrica(int max_dim) {
        this("Rubrica " + (numRubriche+1), max_dim);
    }

    public Rubrica(String name) {
        this(name, DEFAULT_MAX_DIM);
    }

    public Rubrica() {
        this(DEFAULT_MAX_DIM);
    }

    private ArrayList<Contatto> rubrica;
    public final int MAX_DIM;
    private static final int DEFAULT_MAX_DIM = 6;
    public final String nome;
    public static int numRubriche;

    public int numEl() {
        return rubrica.size();
    }

    public int aggiungi(Contatto c1) {
        if(rubrica.size() == MAX_DIM) return -1;
        if( cercaPerEmail(c1.getEmail() ).size() != 0) return 0;

        rubrica.add(c1);
        return 1;
    }

    public int aggiungi(String email, String nome) {
        return aggiungi(new Contatto(email, nome));
    }

    public int aggiungi(String email) {
        return aggiungi(email, "");
    }


    private ArrayList<Contatto> cerca(String stringaCerca, boolean cercaEmail) {
        ArrayList<Contatto> trovati = new ArrayList<Contatto>();

        for (Contatto contatto : rubrica) {

            //ricerca per mail, cercaEmail == true
            if (cercaEmail) {
                if (contatto.matchEmail(stringaCerca)) {
                    trovati.add(contatto);
                }
            }

            //ricerca per nome, cercaEmail == false
            if (!cercaEmail) {
                if (contatto.matchNome(stringaCerca)) {
                    trovati.add(contatto);
                }
            }

        }
        return trovati;
    }

    public ArrayList<Contatto> cercaPerEmail(String mail) {
        return cerca(mail, true);
    }

    public ArrayList<Contatto> cercaPerNome(String nome) {
        return cerca(nome, false);
    }

    public boolean eliminaPerNome(String nome) {
        return rubrica.removeAll(cercaPerNome(nome));
    }

    public boolean eliminaPerEmail(String mail) {
        return rubrica.removeAll(cercaPerEmail(mail));
    }

    public String toString() {
        String str = "";
        for (Contatto contatto : rubrica) {
            str = str.concat(contatto.toString() + ";");
        }
        return str;
    }
}