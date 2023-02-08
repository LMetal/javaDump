package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rubrica.Contatto;
import rubrica.ContattoException;
import rubrica.Rubrica;
import rubrica.RubricaException;

public class TestRubrica {
    @BeforeEach
    void setup() {
        Rubrica.numRubriche = 0;
    }

    @Test
    void costruttore1(){
        Rubrica r1 = new Rubrica("amici", 10);
        assertEquals(1, Rubrica.numRubriche);
        Rubrica r2 = new Rubrica("parenti", 5);
        assertEquals(2, Rubrica.numRubriche);

        assertEquals("amici", r1.nome);
        assertEquals(10, r1.MAX_DIM);
        assertEquals("parenti", r2.nome);
        assertEquals(5, r2.MAX_DIM);
    }

    @Test
    void costruttore2(){
        Rubrica r1 = new Rubrica();
        assertEquals(1, Rubrica.numRubriche);

        assertEquals("Rubrica " + Rubrica.numRubriche, r1.nome);
        assertEquals(6, r1.MAX_DIM);
    }

    @Test
    void costruttore3(){
        Rubrica r1 = new Rubrica("studenti");
        assertEquals(1, Rubrica.numRubriche);
        assertEquals("studenti", r1.nome);
        assertEquals(6, r1.MAX_DIM);
    }

    @Test
    void costruttore4(){
        Rubrica r1 = new Rubrica(10);
        assertEquals(1, Rubrica.numRubriche);
        assertEquals("Rubrica " + Rubrica.numRubriche, r1.nome);
        assertEquals(10, r1.MAX_DIM);
    }

    @Test
    void numElem() throws RubricaException, ContattoException {
        Rubrica r1 = new Rubrica();
        int e1 = r1.numEl();
        r1.aggiungi("pippo@123.it", "Leo");
        int e2 = r1.numEl();
        r1.aggiungi("pippo123@123.it", "Leo");
        int e3 = r1.numEl();

        assertEquals(0, e1);
        assertEquals(1, e2);
        assertEquals(2, e3);
    }

    @Test
    void aggiungiEmailNome() {
        Rubrica r1 = new Rubrica(3);
        RubricaException ex = assertThrows(RubricaException.class, () ->{
            r1.aggiungi("leo@123.it", "Leo");			//aggiunge
            r1.aggiungi("pippo@123.it", "Pippo");		//aggiunge
            r1.aggiungi("leo@123.it", "LEO");			//gia' presente
        });
        assertEquals("Elemento uguale", ex.getMessage());

        ex = assertThrows(RubricaException.class, () ->{
            r1.aggiungi("pippo123@456.it", "PIPPO");	//aggiunge
            r1.aggiungi("lollo@123.it", "Lollo");		//MAX_DIM raggiunto
        });
        assertEquals("Limite raggiunto", ex.getMessage());
    }

    @Test
    void aggiungiEmail() {
        Rubrica r1 = new Rubrica(3);
        RubricaException ex = assertThrows(RubricaException.class, () ->{
            r1.aggiungi("leo@123.it");			//aggiunge
            r1.aggiungi("pippo@123.it");		//aggiunge
            r1.aggiungi("leo@123.it");			//gia' presente
        });
        assertEquals("Elemento uguale", ex.getMessage());

        ex = assertThrows(RubricaException.class, () ->{
            r1.aggiungi("pippo123@456.it");		//aggiunge
            r1.aggiungi("lollo@123.it");		//MAX_DIM raggiunto
        });
        assertEquals("Limite raggiunto", ex.getMessage());
    }

    @Test
    void aggiungiContatto() throws RubricaException, ContattoException {
        Rubrica r1 = new Rubrica("pippo", 3);
        RubricaException ex = assertThrows(RubricaException.class, () ->{
            r1.aggiungi(new Contatto("leo@123.it"));				//aggiunge
            r1.aggiungi(new Contatto("pippo@123.it", "nome"));		//aggiunge
            r1.aggiungi(new Contatto("leo@123.it"));				//gia' presente
        });
        assertEquals("Elemento uguale", ex.getMessage());

        ex = assertThrows(RubricaException.class, () ->{
            r1.aggiungi(new Contatto("pippo123@456.it"));	//aggiunge
            r1.aggiungi(new Contatto("lollo@123.it"));		//MAX_DIM raggiunto
        });
        assertEquals("Limite raggiunto", ex.getMessage());

        Rubrica r2 = new Rubrica(3);
        r2.aggiungi(new Contatto("leo@123.it"));

    }

    @Test
    void cercaPerNome() throws RubricaException, ContattoException {
        Rubrica r1 = new Rubrica(10);
        r1.aggiungi("leo@123.it", "Leo");
        r1.aggiungi("pippo@123.it", "Pippo");
        r1.aggiungi("nome@123.it", "Nome");
        r1.aggiungi("leo@456.it", "Leo");
        r1.aggiungi("lollo@123.it", "Lollo");

        ArrayList<Contatto> trovati1 = r1.cercaPerNome("Leo");
        ArrayList<Contatto> trovati2 = r1.cercaPerNome("Andrea");


        assertEquals(2, trovati1.size());
        assertEquals(0, trovati2.size());

    }

    @Test
    void cercaPerEmail() throws RubricaException, ContattoException {
        Rubrica r1 = new Rubrica(7);
        r1.aggiungi("leo@123.it", "Leo");
        r1.aggiungi("pippo@123.it", "Pippo");
        r1.aggiungi("nome@123.it", "Nome");
        r1.aggiungi("leo@456.it", "Leo");
        r1.aggiungi("lollo@123.it", "Lollo");

        ArrayList<Contatto> trovati1 = r1.cercaPerEmail("pippo@123.it");
        ArrayList<Contatto> trovati2 = r1.cercaPerEmail("email.it");


        assertEquals(1, trovati1.size());
        assertEquals(0, trovati2.size());

    }

    @Test
    void eliminaPerNome() throws RubricaException, ContattoException {
        Rubrica r1 = new Rubrica();
        r1.aggiungi("leo@123.it", "Leo");
        r1.aggiungi("pippo@123.it", "Pippo");
        r1.aggiungi("nome@123.it", "Nome");

        Rubrica r2 = new Rubrica();
        r2.aggiungi("leo@456.it", "Leo");

        boolean esito1 = r1.eliminaPerNome("Pippo");
        boolean esito2 = r1.eliminaPerNome("lorenzo");

        boolean esito3 = r2.eliminaPerNome("Pippo");
        boolean esito4 = r2.eliminaPerNome("Leo");

        assertTrue(esito1);
        assertFalse(esito2);
        assertEquals(2, r1.numEl());

        assertFalse(esito3);
        assertTrue(esito4);
        assertEquals(0, r2.numEl());
    }

    @Test
    void eliminaPerEmail() throws RubricaException, ContattoException {
        Rubrica r1 = new Rubrica();
        r1.aggiungi("leo@123.it", "Leo");
        r1.aggiungi("pippo@123.it", "Pippo");
        r1.aggiungi("nome@123.it", "Nome");

        Rubrica r2 = new Rubrica();
        r2.aggiungi("leo@456.it", "Leo");

        boolean esito1 = r1.eliminaPerEmail("leo@123.it");
        boolean esito2 = r1.eliminaPerEmail("altro@not.net");

        boolean esito3 = r2.eliminaPerEmail("ancora");
        boolean esito4 = r2.eliminaPerEmail("leo@456.it");

        assertTrue(esito1);
        assertFalse(esito2);
        assertEquals(2, r1.numEl());

        assertFalse(esito3);
        assertTrue(esito4);
        assertEquals(0, r2.numEl());
    }

    @Test
    void rubricaToString() throws RubricaException, ContattoException {
        Rubrica r1 = new Rubrica();
        r1.aggiungi("leo@123.it", "Leo");
        r1.aggiungi("pippo@123.it", "Pippo");
        r1.aggiungi("nome@123.it", "Nome");

        Rubrica r2 = new Rubrica();
        r2.aggiungi("leo@456.it", "Leo");

        String rubr1 = r1.toString();
        String rubr2 = r2.toString();

        assertEquals("leo@123.it, Leo, ; pippo@123.it, Pippo, ; nome@123.it, Nome, ; ", rubr1);
        assertEquals("leo@456.it, Leo, ; ", rubr2);
    }
}