package RubricaOO;

import java.util.ArrayList;

public class RubricaOO {
    public RubricaOO(String name, int max_dim) {
        rubrica = new ArrayList<>();
        numRubriche++;
        this.MAX_DIM = max_dim;
        this.nome = name;
    }
    public RubricaOO() {
        rubrica = new ArrayList<>();
        numRubriche++;
        this.MAX_DIM = 6;
        this.nome = "Rubrica " + numRubriche;
    }

    public RubricaOO(String name) {
        rubrica = new ArrayList<>();
        numRubriche++;
        this.MAX_DIM = 6;
        this.nome = name;
    }

    public RubricaOO(int max_dimm) {
        rubrica = new ArrayList<>();
        numRubriche++;
        this.MAX_DIM = max_dimm;
        this.nome = "Rubrica " + numRubriche;
    }

    private static ArrayList<String> rubrica;
    public final int MAX_DIM;
    public final String nome;
    public static int numRubriche;



    public int numE1() {
        return rubrica.size();
    }

    public static void svuota() {
        rubrica.clear();
    }

    public int aggiungi(String string) {
        if (rubrica.size() == MAX_DIM) return -1;

        if (rubrica.contains(string)) return 0;

        rubrica.add(string);
        return 1;

    }

    public ArrayList<String> cerca(String string) {
        ArrayList<String> listaPrefisso = new ArrayList<String>();
        for (String s : rubrica) {
            if (s.contains(string)) {
                listaPrefisso.add(s);
            }
        }
        return listaPrefisso;
    }

    public void cancella(String string) {
        rubrica.removeAll(cerca(string));
    }

    public String toString(){
        String out = rubrica.toString();
        out = out.replace("[", "");
        out = out.replace("]", "");
        out = out.replace(" ", "");
        return out;
    }
}