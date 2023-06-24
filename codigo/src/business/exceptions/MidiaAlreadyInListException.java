package business.exceptions;

import business.composition.Cliente;
import business.composition.Midia;

public class MidiaAlreadyInListException extends Exception {
	
	public MidiaAlreadyInListException(Cliente c, Midia m) {
		super("A operacao nao pode ser concluida pois o cliente " + c.getNome() + " ja havia a midia " + m.getNome() + " na lista a ser inserida");
	}

}

