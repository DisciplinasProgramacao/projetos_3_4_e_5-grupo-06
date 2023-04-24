package test;

import org.junit.Test;
import static org.junit.Assert.*;
import business.*;

public class testFilme {

    //teste para o getIdFilme
    @Test
    public void testGetIdFilme() {
        Filme filme = new Filme(1, "Filme 1", "01/01/2023", 120);
        assertEquals(1, filme.getId());
    }
    
    //teste para setIdFilme
    @Test
    public void testSetIdFilme() {
        Filme filme = new Filme(1, "Filme 1", "01/01/2023", 120);
        filme.setId(2);
        assertEquals(2, filme.getId());
    }

    @Test
    public void testGetNome() {
        Filme filme = new Filme(1, "Filme 1", "01/01/2023", 120);
        assertEquals("Filme 1", filme.getNome());
    }
    
    @Test
    public void testSetNome() {
        Filme filme = new Filme(1, "Filme 1", "01/01/2023", 120);
        filme.setNome("Filme 2");
        assertEquals("Filme 2", filme.getNome());
    }
}
