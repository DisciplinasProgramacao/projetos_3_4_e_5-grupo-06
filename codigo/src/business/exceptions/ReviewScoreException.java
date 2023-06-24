package business.exceptions;

import business.composition.Avaliacao;

public class ReviewScoreException extends Exception {
	
	public ReviewScoreException() {
		super("Nao foi possivel avaliar pois so é permitido notas de 1 a 5");
	}

}
