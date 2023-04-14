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
}
