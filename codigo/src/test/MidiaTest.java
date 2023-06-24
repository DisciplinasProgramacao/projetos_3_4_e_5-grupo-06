package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import business.composition.Cliente;
import business.composition.Midia;
import business.composition.enums.ClienteType;
import business.exceptions.ClienteReviewExcessException;
import business.exceptions.ReviewScoreException;
import business.subobjects.Filme;
import business.subobjects.Serie;

public class MidiaTest {
	
    Midia s, f, lan;
    Cliente c;
    
    @BeforeEach
    public void setUp() {
        c = new Cliente("Tester", "123");
        c.setTipoCliente(ClienteType.PROFISSIONAL);
        try {
        	lan = new Serie("SerieDahora", "Ação", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), true, 5);
			s = new Serie("SerieDahora", "Drama", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 5);
			f = new Filme("FilmeDahora", "Suspense", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 120);
        } catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void adicionarAvaliacaoTest() {
    	try {
			s.addAvaliacao(c, 5, "Comentario");
		} catch (ClienteReviewExcessException | ReviewScoreException e) {
			e.printStackTrace();
		}
    	
    	assertEquals(1, s.getAvaliacoes().size());
    }
    
    @Test
    public void registrarAudienciaTest() {
    	s.aumentaAudiencia();
    	
    	assertEquals(1, s.getAudiencia());
    }
    
    @Test 
    public void verificaMediaDeAvaliacoesTest() {
		try {
			s.addAvaliacao(new Cliente("A", "B"), 5, "Comentario");
			s.addAvaliacao(new Cliente("A", "B"), 2, "Comentario");
			s.addAvaliacao(new Cliente("A", "B"), 3, "Comentario");
			s.addAvaliacao(new Cliente("A", "B"), 4, "Comentario");
			s.addAvaliacao(new Cliente("A", "B"), 1, "Comentario");
		} catch (ClienteReviewExcessException | ReviewScoreException e) {
			e.printStackTrace();
		}
		
		assertEquals(3.0, s.getMediaAvaliacoes());
    }
}

