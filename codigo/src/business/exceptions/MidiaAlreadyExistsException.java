package business.exceptions;

import business.composition.Midia;

public class MidiaAlreadyExistsException extends Exception {
	
	public MidiaAlreadyExistsException(Midia m) {
		super("A midia " + m.getNome() + " nao pode ser adicionada a plataforma pois ja foi adicionada anteriormente");
	}

}
