package test;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Assert;

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

    @Test
    public void testAvaliarFilme() {
        Filme filme = new Filme(1, "Filme Teste", "01/01/2022", 120, -1);

        // testando avaliação dentro do intervalo permitido
        filme.avaliarFilme(3);
        Assert.assertEquals(3, filme.getAvaliacao());
    }

    @Test
    public void testAvaliarFilmeabaixo() {
        Filme filme = new Filme(1, "Filme Teste", "01/01/2022", 120, -1);
        // testando avaliação abaixo do intervalo permitido
        filme.avaliarFilme(0);
        Assert.assertEquals(-1, filme.getAvaliacao());
    }
    @Test
    public void testAvaliarFilmeacima() {
        Filme filme = new Filme(1, "Filme Teste", "01/01/2022", 120, -1);
        // testando avaliação acima do intervalo permitido
        filme.avaliarFilme(6);
        Assert.assertEquals(-1, filme.getAvaliacao());
    }

    @Test
    public void testGETAvaliarFilme() {
        Filme filme = new Filme(1, "Filme Teste", "01/01/2022", 120, 1);
        // testando avaliação acima do intervalo permitido
        filme.avaliarFilme(6);
        filme.setAvaliacao(2);
        Assert.assertEquals(2, filme.getAvaliacao());
    }
}

