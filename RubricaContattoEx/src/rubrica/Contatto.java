package rubrica;

import java.util.ArrayList;

public class Contatto {
	public Contatto ( String email , String nome ) throws ContattoException {
		String regex = "^(.+)@(.+)(\\.)(.+)$";
		if(!email.matches(regex)) throw new ContattoException("Email non valida");
		this.email = email;
		setName(nome);
		this.numbers = new ArrayList<String>();
	}
	
	public Contatto(String email) throws ContattoException {
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

	public void inserisciNumTel(String numero) throws ContattoException  {
		if(numbers.contains(numero)) throw new ContattoException("Numero duplicato");
		
		numbers.add(numero);
	}

	public void eliminaNumTel(String numero) throws ContattoException {
		if(!numbers.contains(numero)) throw new ContattoException("Numero non presente");
		
		numbers.remove(numero);
	}

	public String numTel() {
        String out = numbers.toString();
        out = out.replace("[", "");
        out = out.replace("]", "");
        out = out.replace(" ", "");
    
		return out;
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
		return email + ",\s" + name + ",\s" + numTel();
	}	
}
