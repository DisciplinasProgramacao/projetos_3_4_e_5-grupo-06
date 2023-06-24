package business.exceptions;

import business.composition.Cliente;

public class ClienteAlreadyExistsException extends Exception {
	
	public ClienteAlreadyExistsException(Cliente cliente) {
		super("O cliente " + cliente.getNome() + " nao pode ser adicionado a plataforma pois ja foi adicionado anteriormente");
	}

}