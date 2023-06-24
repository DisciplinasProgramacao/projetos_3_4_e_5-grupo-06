package business.exceptions;

import business.composition.Cliente;

public class ClienteReviewExcessException extends Exception {
	
	public ClienteReviewExcessException(Cliente c) {
		super("Nao foi possivel lancar a avaliacao do cliente " + c.getNome() + " pois ele ja avaliou essa midia anteriormente");
	}

}
