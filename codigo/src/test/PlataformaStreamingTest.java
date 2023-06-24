package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.PlataformaStreaming;
import business.composition.Cliente;
import business.composition.Midia;
import business.exceptions.ClienteAlreadyExistsException;
import business.exceptions.MidiaAlreadyExistsException;
import business.exceptions.MidiaAlreadyInListException;
import business.subobjects.Filme;
import business.subobjects.Serie;

class PlataformaStreamingTest {
	
	public static PlataformaStreaming ps = new PlataformaStreaming("PlatTest");
	
	Cliente c;

	static Cliente c2;
	Midia s, f, s2, f2, lan;
	
	@BeforeAll
	public static void beforeAl() {
		c2 = new Cliente("Logador", "Logador123");
		
		try {
			ps.adicionarCliente("IDLogador", c2);
		} catch (ClienteAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		ps.login("IDLogador", "Logador123");
	}
	
	@BeforeEach
	public void setUp() {
		c = new Cliente("Tester", "123");

		try {
			lan = new Serie("SerieDahora", "Inglês", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), true, 5);
			s = new Serie("SerieDahora", "Inglês", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 5);
			s2 = new Serie("SerieDahora2", "Inglês", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 5);
			f = new Filme("FilmeDahora", "Suspense", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 120);
			f2 = new Filme("FilmeDahora2", "Suspense", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 120);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void adicionarFilmeTest() {
		try {
			ps.adicionarMidia(128738723, f);
		} catch (MidiaAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		assertEquals(128738723, ps.getMidiaIdByName(f.getNome()));
	}
	
	@Test
	public void adicionarFilmeJaExistenteTest() {
		try {
			ps.adicionarMidia(4894737, f2);
		} catch (MidiaAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		assertThrows(MidiaAlreadyExistsException.class, () -> { ps.adicionarMidia(4894737, f2); });
	}
	
	@Test
	public void adicionarSerieTest() {
		try {
			ps.adicionarMidia(456546, s);
		} catch (MidiaAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		assertEquals(456546, ps.getMidiaIdByName(s.getNome()));
	}
	
	@Test
	public void adicionarSerieJaExistenteTest() {
		try {
			ps.adicionarMidia(234234324, s2);
		} catch (MidiaAlreadyExistsException e) {
			e.printStackTrace();
		}
	
		assertThrows(MidiaAlreadyExistsException.class, () -> { ps.adicionarMidia(234234324, s2); });
	}
	
	@Test
	public void adicionarCliente() {
		try {
			ps.adicionarCliente("A2348923", c);
		} catch (ClienteAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		assertEquals("A2348923", ps.getClienteIdByName(c.getNome()));
	}
	
	@Test
	public void adicionarClienteJaExistente() {
		try {
			ps.adicionarCliente("A83874783", c);
		} catch (ClienteAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		assertThrows(ClienteAlreadyExistsException.class, () -> { ps.adicionarCliente("A83874783", c); });
	}
	
	@Test
	public void loginCorretoTest() {
		ps.login("IDLogador", "Logador123");
		
		assertEquals(ps.getClienteLogado(), c2);
	}
	
	@Test
	public void loginIncorretoTest() {
		ps.login("IDLogador", "LogadorAAA");
		
		assertEquals(c2, ps.getClienteLogado());
	}
	
	@Test
	public void filtragemGeneroTest() {
		try {
			ps.getClienteLogado().addListaParaAssistir(new Serie("Teeste", "Suspense", "Ingles", new SimpleDateFormat("dd/MM/yyyy").parse("10/20/2020"), false, 10));
		} catch (MidiaAlreadyInListException | ParseException e) {
			e.printStackTrace();
		}
		
		List<Midia> filtrada = ps.filtrarPorGenero("Suspense");
		
		assertEquals(1, filtrada.size());
	}
	
	@Test
	public void filtragemIdiomaTest() {
		try {
			ps.getClienteLogado().addListaParaAssistir(new Serie("Teeste", "Acao", "Espanhol", new SimpleDateFormat("dd/MM/yyyy").parse("10/20/2020"), false, 30));
		} catch (MidiaAlreadyInListException | ParseException e) {
			e.printStackTrace();
		}
		
		List<Midia> filtrada = ps.filtrarPorIdioma("Espanhol");
		
		assertEquals(1, filtrada.size());
	}
	
	@Test
	public void filtragemEpisodiosTest() {
		try {
			ps.getClienteLogado().addListaParaAssistir(new Serie("Teeste", "Terror", "Frances", new SimpleDateFormat("dd/MM/yyyy").parse("10/20/2020"), false, 20));
		} catch (MidiaAlreadyInListException | ParseException e) {
			e.printStackTrace();
		}
		
		List<Serie> filtrada = ps.filtrarPorQuantidadeDeEpisodios(20);
		
		assertEquals(1, filtrada.size());
	}

}

