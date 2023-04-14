package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import business.Cliente;
import business.Serie;

class testCliente {

	private Cliente cliente;
    	private Serie serie1;
    	private Serie serie2;
    	private Serie serie3;
    
    @Before
    public void setup() {
        cliente = new Cliente("cliente123", "senha123");
        serie1 = new Serie("Game of Thrones", "Fantasia", "Inglês", 62, 0);
        serie2 = new Serie("Breaking Bad", "Drama", "Inglês", 73, 0);
        serie3 = new Serie("La Casa de Papel", "Ação", "Espanhol", 24, 0);
    }
    
	@Test
	public void retirarDaListaNaoRemoveSeNaoExistir() {
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);
        cliente.adicionarNaLista(serie3);
        
        cliente.retirarDaLista("Friends");
        
        Assertions.assertEquals(3, cliente.getListaParaVer().size());
    }
	
	@Test
	public void adicionarNaListaAdicionaSerieNaLista() {
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);
        cliente.adicionarNaLista(serie3);
        
        Assertions.assertTrue(cliente.getListaParaVer().contains(serie1));
        Assertions.assertTrue(cliente.getListaParaVer().contains(serie2));
        Assertions.assertTrue(cliente.getListaParaVer().contains(serie3));
	}

    @Test
    public void filtrarPorGenerotest(){
        cliente.registrarAudiencia(serie2);
        List<Serie> resultado = cliente.filtrarporGenero("Drama");
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(serie2));
    }

    @Test
    public void fitrarPorIdioma(){
        cliente.registrarAudiencia(serie1);
        List<Serie> resultado = cliente.filtrarPorIdioma("Inglês");
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(serie1));
    }

    @Test
	public void testFiltrarPorQtdEpisodios() {
		cliente.registrarAudiencia(serie1);
		List<Serie> resultado = cliente.filtrarPorQtdEpisodios(62);
		assertEquals(1, resultado.size());
		assertTrue(resultado.contains(serie1));
	}
}
