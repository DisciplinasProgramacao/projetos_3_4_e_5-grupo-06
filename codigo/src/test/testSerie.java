package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Serie;

class testSerie {
	
	Serie s;
	
	@BeforeEach
	public void setUp() {
		s = new Serie();
	}
	
	@Test
	public void testRegistrarAudiencia(){
		s.registrarAudiencia();
		assertEquals(s.getAudiencia(), 1);
	}

}
