package rubrica;

import java.util.ArrayList;

public class Contatto {
    public Contatto ( String mail , String nome ){
        email = mail;
        name = nome;
        numbers = new ArrayList<String>();
    }

    public Contatto(String email) {
        this(email, "");
    }

    private final String email;
    private String name;
    private ArrayList<String> numbers;

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        name = nome;

    }

    public String getEmail() {
        return email;
    }

    public boolean inserisciNumTel(String numero) {
        if(numbers.contains(numero)) return false;

        numbers.add(numero);
        return true;
    }

    public boolean eliminaNumTel(String numero) {
        if(!numbers.contains(numero)) return false;

        numbers.remove(numero);
        return true;
    }

    public String numTel() {
        String str = numbers.toString();
        str = str.replace("[", "");
        str = str.replace("]", "");
        str = str.replace(" ", "");

        return str;
    }

    public boolean matchNome(String nome) {
        return name.contains(nome);
    }

    public boolean matchEmail(String mail) {
        return email.contains(mail);
    }

    public boolean equals(Contatto c2) {
        return email.equals(c2.getEmail());
    }

    public String toString() {
        return email + "," + name + "," + numTel();
    }


}
