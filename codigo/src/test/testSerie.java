package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Serie;

class testSerie {
	
	Serie serie1;
	
	@BeforeEach
	public void setUp() {
		serie1 = new Serie("Game of Thrones", "Fantasia", "Inglês", 62, 0);
	}
	
	@Test
	public void testRegistrarAudiencia(){
		serie1.registrarAudiencia();
		assertEquals(1, serie1.getAudiencia());
	}

}
