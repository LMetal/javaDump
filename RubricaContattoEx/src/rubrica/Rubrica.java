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
	        this(name, 6);
	    }
		
	    public Rubrica() {
	        this(6);
	    }
	
	    
	
	    private ArrayList<Contatto> rubrica;
	    public final int MAX_DIM;
	    public final String nome;
	    public static int numRubriche;
	    
	    public int numEl() {
			return rubrica.size();
		}
		
	    public int aggiungi(Contatto c1) throws RubricaException {
			if(rubrica.size() == MAX_DIM) throw new RubricaException("Limite raggiunto");
	    	if(cercaPerEmail(c1.getEmail() ).size() != 0) throw new RubricaException("Elemento uguale");
	    	
	    	rubrica.add(c1);
	    	return 1;
		}
	    
	    public int aggiungi(String email, String nome) throws RubricaException, ContattoException {
	    	return aggiungi(new Contatto(email, nome));
		}
	    
		public int aggiungi(String email) throws RubricaException, ContattoException {
			return aggiungi(email, "");
		}
		
		
		private ArrayList<Contatto> cerca(String mail, String nome) {
			ArrayList<Contatto> trovati = new ArrayList<Contatto>();
			
			for(int i=0; i<rubrica.size(); i++) {
				if(mail != "") {
					if(rubrica.get(i).matchEmail(mail)) {
						trovati.add(rubrica.get(i));
					}
				}
				
				if(nome != ""){
					if(rubrica.get(i).matchNome(nome)) {
						trovati.add(rubrica.get(i));
					}
				}
				
			}
			return trovati;
		}
		
		public ArrayList<Contatto> cercaPerEmail(String mail) {
			return cerca(mail, "");
		}
		
		public ArrayList<Contatto> cercaPerNome(String nome) {
			return cerca("", nome);
		}
		
		public boolean eliminaPerNome(String nome) {
			return rubrica.removeAll(cercaPerNome(nome));
		}
		
		public boolean eliminaPerEmail(String mail) {
			return rubrica.removeAll(cercaPerEmail(mail));
		}
		
	   public String toString() {
		   String str = "";
		   for(int i=0; i<rubrica.size(); i++) {
			   str = str.concat(rubrica.get(i).toString()) + ";\s";
		   }
		   return str;
	   }   
}
