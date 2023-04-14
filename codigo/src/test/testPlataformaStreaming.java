package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import business.PlataformaStreaming;
import business.Cliente;
import business.Serie;

class testPlataformaStreaming {

	private PlataformaStreaming plataforma;

    @BeforeEach
    public void setUp() {
        plataforma = new PlataformaStreaming("Netflix");
    }

	// teste login
    @Test
    public void testLogin() {
        Cliente cliente = new Cliente("user123", "password");
        plataforma.adicionarCliente(cliente);

        Assertions.assertNull(plataforma.login("user123", "wrongpassword"));
        Assertions.assertEquals(cliente, plataforma.login("user123", "password"));
        Assertions.assertEquals(cliente, plataforma.getClienteAtual());
    }

    @Test
    public void testFiltrarPorGenero() {
        Serie serie1 = new Serie("Breaking Bad", "Drama", "English", 62, 0);
        Serie serie2 = new Serie("Friends", "Comedy", "English", 236, 0);
        Serie serie3 = new Serie("La Casa de Papel", "Drama", "Spanish", 31, 0);
        plataforma.adicionarSerie(serie1);
        plataforma.adicionarSerie(serie2);
        plataforma.adicionarSerie(serie3);

        Assertions.assertEquals(2, plataforma.filtrarPorGenero("Drama").size());
        Assertions.assertEquals(1, plataforma.filtrarPorGenero("Comedy").size());
        Assertions.assertEquals(0, plataforma.filtrarPorGenero("Action").size());
    }

}
