package business.exceptions;

import business.composition.Cliente;

public class ClienteUnauthorizedPermission extends Exception {
	
	public ClienteUnauthorizedPermission(Cliente c) {
		super("O cliente " + c.getNome() + " não pode concluir a operacao pois nao possui nivel de permissao para isso");
	}

}

