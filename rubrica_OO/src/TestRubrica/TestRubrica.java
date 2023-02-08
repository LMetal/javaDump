package TestRubrica;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import RubricaOO.RubricaOO;

class TestRubrica {

    @BeforeEach
    void setup() {
        RubricaOO.numRubriche = 0;
    }

    @Test
    void costruttore1(){
        RubricaOO r1 = new RubricaOO("amici", 10);
        assertEquals(1, RubricaOO.numRubriche);
        RubricaOO r2 = new RubricaOO("parenti", 5);
        assertEquals(2, RubricaOO.numRubriche);

        assertEquals("amici", r1.nome);
        assertEquals(10, r1.MAX_DIM);
        assertEquals("parenti", r2.nome);
        assertEquals(5, r2.MAX_DIM);
    }

    @Test
    void costruttore2(){
        RubricaOO r1 = new RubricaOO();
        assertEquals(1, RubricaOO.numRubriche);

        assertEquals("Rubrica " + RubricaOO.numRubriche, r1.nome);
        assertEquals(6, r1.MAX_DIM);
    }

    @Test
    void costruttore3(){
        RubricaOO r1 = new RubricaOO("studenti");
        assertEquals(1, RubricaOO.numRubriche);
        assertEquals("studenti", r1.nome);
        assertEquals(6, r1.MAX_DIM);
    }

    @Test
    void costruttore4(){
        RubricaOO r1 = new RubricaOO(10);
        assertEquals(1, RubricaOO.numRubriche);
        assertEquals("Rubrica " + RubricaOO.numRubriche, r1.nome);
        assertEquals(10, r1.MAX_DIM);
    }

    @Test
    void aggiungiCorrettamente() {
        RubricaOO r1 = new RubricaOO("amici", 10);

        int n0 = 0;
        int ris1 = r1.aggiungi("Gianni=123");
        int n1 = r1.numE1();
        int ris2 = r1.aggiungi("Gianni=321");
        int n2 = r1.numE1();

        assertEquals(1, ris1);
        assertEquals(1, ris2);

        assertEquals(n0 + 1, n1);
        assertEquals(n1 + 1, n2);

    }


    @Test
    void testSvuota() {
        RubricaOO r = new RubricaOO("amici", 10);
        r.aggiungi("Gianni=123");
        r.aggiungi("Gianni=12");

        RubricaOO.svuota();

        assertEquals(0, r.numE1());
    }



    @Test
    void aggiungOltreMAX(){
        RubricaOO r = new RubricaOO("amici", 3);

        r.aggiungi("Leo=123");
        r.aggiungi("Leo=321");
        r.aggiungi("Leo=132");

        int ris = r.aggiungi("Giann1=1234");

        assertEquals(r.MAX_DIM, r.numE1());
        assertEquals(-1, ris);
    }

    @Test
    void aggiungiDupplicato() {
        RubricaOO r = new RubricaOO("amici", 3);
        r.aggiungi("Giann1=1234");
        r.aggiungi("Giorgio=765");

        int ris = r.aggiungi("Giann1=1234");

        assertEquals(0, ris);
    }

    @Test
    void ritornaPrefisso() {
        RubricaOO r = new RubricaOO("amici", 3);

        r.aggiungi("Pippo=1234");
        r.aggiungi("Pippo=5678");
        r.aggiungi("Paolo=1234");

        ArrayList<String> arr = r.cerca("Pip");
        assertEquals(2, arr.size());

        arr = r.cerca("P");
        assertEquals(3, arr.size());
    }


    @Test
    void cancellaPrefisso() {
        RubricaOO r = new RubricaOO("amici", 3);

        r.aggiungi("Pippo=1234");
        r.aggiungi("Pippo=5678");
        r.aggiungi("Paolo=1234");

        r.cancella("Gia");
        assertEquals(3, r.numE1());

        r.cancella("Pip");
        assertEquals(1, r.numE1());
    }

    @Test
    void tornaString(){
        RubricaOO r = new RubricaOO("amici", 3);

        r.aggiungi("Pippo=1234");
        r.aggiungi("Pippo=5678");
        r.aggiungi("Paolo=1234");

        String strReturn = r.toString();
        String expected = "Pippo=1234,Pippo=5678,Paolo=1234";

        assertEquals(expected, strReturn);
    }
}