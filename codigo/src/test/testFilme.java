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
        filme.setId(1);
        assertEquals(1, filme.getId());
    }

    //teste get e set de nome
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

    //teste get e set de dataLancamento
    @Test
    public void testGetDataLancamento() {
        Filme filme = new Filme(1, "Filme 1", "01/01/2023", 120);
        assertEquals("01/01/2023", filme.getDataLancamento());
    }
    

    @Test
    public void testSetDataLancamento() {
        Filme filme = new Filme(1, "Filme 1", "01/01/2023", 120);
        filme.setDataLancamento("02/02/2023");
        assertEquals("02/02/2023", filme.getDataLancamento());
    }

    //teste get e set  Duração
    @Test
    public void testGetDuracao() {
        Filme filme = new Filme(1, "Filme 1", "01/01/2023", 120);
        assertEquals(120, filme.getDuracao());
    }
    
    @Test
    public void testSetDuracao() {
        Filme filme = new Filme(1, "Filme 1", "01/01/2023", 120);
        filme.setDuracao(90);
        assertEquals(90, filme.getDuracao());
    }
}

