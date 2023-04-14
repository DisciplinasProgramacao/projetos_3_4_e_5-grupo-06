package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;

import business.Cliente;
import business.Serie;

class testCliente {

	private Cliente cliente;
    	private Serie serie1;
    	private Serie serie2;
    	private Serie serie3;
    
    @BeforeEach
    public void setup() {
        cliente = new Cliente("cliente123", "senha123");
        serie1 = new Serie("Game of Thrones", "Fantasia", "Inglês", 8);
        serie2 = new Serie("Breaking Bad", "Drama", "Inglês", 5);
        serie3 = new Serie("La Casa de Papel", "Ação", "Espanhol", 4);
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

}
