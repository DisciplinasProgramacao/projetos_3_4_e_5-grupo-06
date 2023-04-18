package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import business.PlataformaStreaming;
import business.Cliente;
import business.Serie;

class testPlataformaStreaming {

	private PlataformaStreaming plataforma;
    //executado antes de cada teste criando um objeto da classe PlataformaStreaming
    @BeforeEach
    public void setUp() {
        plataforma = new PlataformaStreaming("Netflix");
    }

	// teste login
    @Test
    public void Logintest() {
        Cliente cliente = new Cliente("user123", "password");
        plataforma.adicionarCliente(cliente);

        Assertions.assertNull(plataforma.login("user123", "wrongpassword"));
        Assertions.assertEquals(cliente, plataforma.login("user123", "password"));
        Assertions.assertEquals(cliente, plataforma.getClienteAtual());
    }

    //teste do metodo FiltrarPorGenero
    @Test
    public void FiltrarPorGenerotest() {
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

    //teste do metodo FiltrarPorIdioma
    @Test
    public void FiltrarPorIdiomatest() {
        Serie serie1 = new Serie("Breaking Bad", "Drama", "English", 62, 0);
        Serie serie2 = new Serie("Friends", "Comedy", "English", 236, 0);
        Serie serie3 = new Serie("La Casa de Papel", "Drama", "Spanish", 31, 0);
        plataforma.adicionarSerie(serie1);
        plataforma.adicionarSerie(serie2);
        plataforma.adicionarSerie(serie3);

        Assertions.assertEquals(2, plataforma.filtrarPorIdioma("English").size());
        Assertions.assertEquals(1, plataforma.filtrarPorIdioma("Spanish").size());
        Assertions.assertEquals(0, plataforma.filtrarPorIdioma("Portuguese").size());
    }

    //teste do metodo FiltrarPorQtdEpisodios
    @Test
    public void FiltrarPorQtdEpisodiostest() {
        Serie serie1 = new Serie("Breaking Bad", "Drama", "English", 62, 0);
        Serie serie2 = new Serie("Friends", "Comedy", "English", 236, 0);
        Serie serie3 = new Serie("La Casa de Papel", "Drama", "Spanish", 31, 0);
        plataforma.adicionarSerie(serie1);
        plataforma.adicionarSerie(serie2);
        plataforma.adicionarSerie(serie3);

        Assertions.assertEquals(1, plataforma.filtrarPorQtdEpisodios(62).size());
        Assertions.assertEquals(1, plataforma.filtrarPorQtdEpisodios(236).size());
        Assertions.assertEquals(1, plataforma.filtrarPorQtdEpisodios(31).size());
        Assertions.assertEquals(0, plataforma.filtrarPorQtdEpisodios(100).size());
    }

    //teste do metodo para registrar audiencia
    @Test
    public void RegistrarAudienciatest() {
        Serie serie1 = new Serie("Série 1", "Drama", "Inglês", 10, 0);
        Serie serie2 = new Serie("Série 2", "Comédia", "Português", 5, 0);
        plataforma.registrarAudiencia(serie1);
        plataforma.registrarAudiencia(serie2);
        plataforma.registrarAudiencia(serie2);
        assertEquals(1, serie1.getAudiencia());
        assertEquals(2, serie2.getAudiencia());
    }
	
    @Test
	public void testSalvarEspectador() {
		Cliente cliente = new Cliente("user123", "password");
		Cliente c2 = new Cliente("user999", "senha");
        plataforma.adicionarCliente(cliente);
        plataforma.adicionarCliente(c2);
        
        plataforma.salvarEspectadores("espectador.csv", plataforma.getTodosClientes(), plataforma);
        assertEquals(cliente, plataforma.login(cliente.getNomeDeUsuario(), cliente.getSenha()));
        assertEquals(c2, plataforma.login(c2.getNomeDeUsuario(), c2.getSenha()));
        
        
	}
    
    @Test
   	public void testSalvarSeries() {
    	Serie serie1 = new Serie("Série 1", "Drama", "Inglês", 10, 0);
    	Serie serie2 = new Serie("Série 2", "Comedia", "Portugues", 10, 0);
        serie1.setData("17/04/2023");
        serie1.setId(1);
        serie2.setData("17/04/2023");
        serie2.setId(2);
        plataforma.adicionarSerie(serie1);
        plataforma.adicionarSerie(serie2);
        plataforma.salvarSeries("series.csv", plataforma.getTodasSeries());
           
        
        assertEquals(1, serie1.getId());
        assertEquals(2, serie2.getId());
           
           
   	}
    
    @Test
   	public void testSalvarAudiencia() {
    	Cliente c = new Cliente("Victor", "123");
    	Cliente c2 = new Cliente("Vitor", "1234");
    	Serie serie1 = new Serie("Série 1", "Drama", "Inglês", 10, 0);
    	Serie serie2 = new Serie("Série 2", "Comedia", "Portugues", 10, 0);
        serie1.setData("17/04/2023");
        serie1.setId(1);
        serie2.setData("17/04/2023");
        serie2.setId(2);
        plataforma.adicionarSerie(serie1);
        plataforma.adicionarSerie(serie2);
        
        c.registrarAudiencia(serie1);
        c2.registrarAudiencia(serie1);
        plataforma.registrarAudiencia(serie1);
        plataforma.registrarAudiencia(serie1);
        plataforma.salvarAudiencia("audiencia.csv", plataforma, plataforma.getTodosClientes(), plataforma.getTodasSeries());
 
           
           
   	}
    
    

}

