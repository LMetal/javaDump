package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import masterMind.Giudice;

public class TestGiudice {
    @Test
    void validaLunghezza(){
        assertTrue(Giudice.valida("1234"));
        assertFalse(Giudice.valida("123"));
        assertFalse(Giudice.valida("12345"));
        assertTrue(Giudice.valida("4312"));
    }

    @Test
    void validaCaratteri() {
        assertTrue(Giudice.valida("1234"));
        assertFalse(Giudice.valida("1f34"));
        assertFalse(Giudice.valida("1233"));
        assertFalse(Giudice.valida("AAAA"));
        assertFalse(Giudice.valida("#321"));
        assertTrue(Giudice.valida ("3617"));
        assertFalse(Giudice.valida ("567!"));
        assertFalse(Giudice.valida ("ab%t"));
        assertTrue(Giudice.valida ("3124"));
    }

    @Test
    void validaNonRipetuti() {
        assertTrue(Giudice.valida("1234"));
        assertFalse(Giudice.valida("1231"));
        assertFalse(Giudice.valida("1224"));
        assertFalse(Giudice.valida("4444"));
        assertFalse(Giudice.valida("1444"));
    }

    @Test
    void testValidaNumBulls () {
        assertEquals (0 , Giudice.numBulls ("1234" , "5678" ));
        assertEquals (1 , Giudice.numBulls ("1234" , "1876" ));
        assertEquals (1 , Giudice.numBulls ("1234" , "9874" ));
        assertEquals (1 , Giudice.numBulls ("1234" , "9243" ));
        assertEquals (1 , Giudice.numBulls ("9876" , "5573" ));
        assertEquals (2 , Giudice.numBulls ("1234" , "1256" ));
        assertEquals (2 , Giudice.numBulls ("1234" , "1537" ));
        assertEquals (2 , Giudice.numBulls ("1234" , "1764" ));
        assertEquals (2 , Giudice.numBulls ("1234" , "8237" ));
        assertEquals (2 , Giudice.numBulls ("1234" , "9834" ));
        assertEquals (3 , Giudice.numBulls ("9264" , "9261" ));
        assertEquals (3 , Giudice.numBulls ("9264" , "9284" ));
        assertEquals (3 , Giudice.numBulls ("9264" , "9764" ));
        assertEquals (3 , Giudice.numBulls ("9264" , "5264" ));
        assertEquals (4 , Giudice.numBulls ("9264" , "9264" ));
        assertEquals (4 , Giudice.numBulls ("8725" , "8725" ));
    }

    @Test
    void testValidaNumMaggots() {
        assertEquals(0, Giudice.numMaggots("1234", "1234"));
        assertEquals(0, Giudice.numMaggots("1234", "5678"));
        assertEquals(1, Giudice.numMaggots("1234", "1245"));
        assertEquals(1, Giudice.numMaggots("1234", "4567"));
        assertEquals(1, Giudice.numMaggots("1234", "1489"));
        assertEquals(1, Giudice.numMaggots("1234", "4238"));
        assertEquals(2, Giudice.numMaggots("1234", "1243"));
        assertEquals(2, Giudice.numMaggots("1234", "1326"));
        assertEquals(2, Giudice.numMaggots("1234", "4516"));
        assertEquals(2, Giudice.numMaggots("1234", "3456"));
        assertEquals(3, Giudice.numMaggots("1234", "2345"));
        assertEquals(3, Giudice.numMaggots("1234", "2543"));
        assertEquals(3, Giudice.numMaggots("1234", "2473"));
        assertEquals(3, Giudice.numMaggots("1234", "7321"));
        assertEquals(4, Giudice.numMaggots("1234", "2341"));
        assertEquals(4, Giudice.numMaggots("1234", "3412"));
    }

    @Test
    void testGenTarget() {
        for(int i=0; i<100; i++) {
            assertTrue(Giudice.valida(Giudice.genTarget()));
        }
    }
}