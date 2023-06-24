package business.exceptions;

public class MidiaNotFoundException extends Exception {
	
	public MidiaNotFoundException() {
		super("Nao foi possivel executar a operacao pois a midia buscada nao existe");
	}

}

