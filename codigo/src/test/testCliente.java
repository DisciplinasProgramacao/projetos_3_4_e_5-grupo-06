package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.composition.Cliente;
import business.composition.Midia;
import business.composition.enums.ClienteType;
import business.exceptions.ClienteReviewExcessException;
import business.exceptions.ClienteUnauthorizedPermission;
import business.exceptions.MidiaAlreadyInListException;
import business.exceptions.MidiaNotFoundException;
import business.exceptions.ReviewScoreException;
import business.subobjects.Serie;

public class ClienteTest {

    Midia s, lan;
    Cliente c;
    
    @BeforeEach
    public void setUp() {
        c = new Cliente("Tester", "123");
        try {
        	lan = new Serie("SerieDahora", "Inglês", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), true, 5);
			s = new Serie("SerieDahora", "Inglês", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 5);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void adicionarNaListaDeAssistidasTest() {
    	try {
			c.addListaAssistida(s);
		} catch (MidiaAlreadyInListException e) {
			e.printStackTrace();
		}
    	
    	assertEquals(1, c.getListaAssistidasSize());
    }
    
    @Test
    public void tentaAdicionarNaListaDeAssistidasJaAdicionadaAntesTest() {
    	try {
			c.addListaAssistida(s);
		} catch (MidiaAlreadyInListException e) {
			e.printStackTrace();
		}
    	
		assertThrows(MidiaAlreadyInListException.class,
				() -> { c.addListaAssistida(s); });
    }
    
    @Test
    public void adicionaNaListaParaAssistirTest() {
    	try {
			c.addListaParaAssistir(s);
		} catch (MidiaAlreadyInListException e) {
			e.printStackTrace();
		}
    	
    	assertEquals(1, c.getListaParaAssistir().size());
    }
    
    @Test
    public void tentaAdicionarNaListaParaAssistirJaAdicionadaAntesTest() {
    	try {
			c.addListaParaAssistir(s);
		} catch (MidiaAlreadyInListException e) {
			e.printStackTrace();
		}
    	
		assertThrows(MidiaAlreadyInListException.class,
				() -> { c.addListaParaAssistir(s); });
    }
    
    @Test
    public void assistirMidiaTest() {
    	try {
			c.assistirMidia(s);
		} catch (MidiaNotFoundException | ClienteUnauthorizedPermission e) {
			e.printStackTrace();
		}
    	
    	assertEquals(1, c.getListaAssistidasSize());
    }
    
    @Test
    public void assistirMidiaLancamentoSemPermissaoTest() {
		assertThrows(ClienteUnauthorizedPermission.class, () -> { c.assistirMidia(lan); });
    }
    
    @Test
    public void assistirMidiaLancamentoComPermissaoTest() {
    	c.setTipoCliente(ClienteType.PROFISSIONAL);
    	
    	try {
			c.assistirMidia(lan);
		} catch (MidiaNotFoundException | ClienteUnauthorizedPermission e) {
			e.printStackTrace();
		}
    	
		assertEquals(1, lan.getAudiencia());
    }
      
    @Test
    public void tentaAssistirMidiaJaAssistidaTest() {
    	try {
			c.assistirMidia(s);
			c.assistirMidia(s);
		} catch (MidiaNotFoundException | ClienteUnauthorizedPermission e) {
			e.printStackTrace();
		}
    	
    	assertEquals(1, c.getListaAssistidasSize());
    }
    
    @Test
    public void avaliarSerieTest() {
    	try {
			c.avaliar(s, 5, null);
		} catch (ClienteUnauthorizedPermission | ClienteReviewExcessException | ReviewScoreException e) {
			e.printStackTrace();
		}
    	
    	assertEquals(1, s.getAvaliacoes().size());
    }
    
    // avaliar com nota maior q 5 ou menor q 1
    
    @Test
    public void avaliarSerieDuasVezesTest() {
    	try {
			c.avaliar(s, 5, null);
		} catch (ClienteUnauthorizedPermission | ClienteReviewExcessException | ReviewScoreException e) {
			e.printStackTrace();
		}
    	
		assertThrows(ClienteReviewExcessException.class,
				() -> { c.avaliar(s, 5, null); });
    }
    
    @Test
    public void avaliarSerieComComentarioSemPermissaoTest() {
		assertThrows(ClienteUnauthorizedPermission.class,
				() -> { c.avaliar(s, 5, "Teste"); });
    }
    
    @Test
    public void avaliarSerieComComentarioComPermissaoTest() {
    	Midia s1 = null, s2 = null, s3 = null, s4 = null, s5 = null, s6 = null;
    	
		try {
			s1 = new Serie("SerieDahora", "Inglês", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 5);
			s2 = new Serie("SerieDahora", "Inglês", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 5);
	    	s3 = new Serie("SerieDahora", "Inglês", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 5);
	    	s4 = new Serie("SerieDahora", "Inglês", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 5);
	    	s5 = new Serie("SerieDahora", "Inglês", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 5);
	    	s6 = new Serie("SerieDahora", "Inglês", "Português", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2021"), false, 5);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	try {
			c.assistirMidia(s1);
			c.assistirMidia(s2);
			c.assistirMidia(s3);
			c.assistirMidia(s4);
			c.assistirMidia(s5);
			c.assistirMidia(s6);
		} catch (MidiaNotFoundException | ClienteUnauthorizedPermission e) {
			e.printStackTrace();
		}
    	
    	try {
			c.avaliar(s1, 5, "Teste");
		} catch (ClienteUnauthorizedPermission | ClienteReviewExcessException | ReviewScoreException e) {
			e.printStackTrace();
		}
    	
    	assertEquals(1, s1.getAvaliacoes().size());
    }
}
