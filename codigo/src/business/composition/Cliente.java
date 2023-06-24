package business.composition;

import java.util.ArrayList;
import java.util.List;

import business.composition.enums.ClienteType;
import business.exceptions.ClienteReviewExcessException;
import business.exceptions.ClienteUnauthorizedPermission;
import business.exceptions.MidiaAlreadyInListException;
import business.exceptions.MidiaNotFoundException;
import business.exceptions.ReviewScoreException;

public class Cliente {
	
	private String nome;
	private String senha;
	private List<Midia> listaParaAssistir;
	private List<Midia> listaAssistidas;
	private ClienteType tipoCliente;

	public Cliente(String nome, String senha) {
		this.setNome(nome);
		this.setSenha(senha);
		this.listaParaAssistir = new ArrayList<>();
		this.listaAssistidas = new ArrayList<>();
		
		this.setTipoCliente(ClienteType.REGULAR);
	}
	
	/**
	 * Adiciona na lista de midias "para assistir" uma midia informada por parametro
	 * 
	 * @param midia
	 * @throws MidiaAlreadyInListException caso a midia ja tenha sido informada anteriormente
	 */
	public void addListaParaAssistir(Midia midia) throws MidiaAlreadyInListException {
		if (listaParaAssistir.contains(midia)) {
			throw new MidiaAlreadyInListException(this, midia);
		}
		
		listaParaAssistir.add(midia);
	}
	
	/**
	 * Executa metodos para assistir uma midia, sendo eles, verificar se o cliente tem
	 * permissão para assistir aquela midia caso seja um lançamento e ele não seja profissional,
	 * verifica o tamanho da lista de assistidas e torna-o um especialista caso a lista seja maior que 4
	 * em tamanho e se a lista não conter a midia a ser assistida, adiciona e chama o
	 * metodo de registrar a audiencia. 
	 * 
	 * 
	 * @param midia
	 * @throws MidiaNotFoundException
	 * @throws ClienteUnauthorizedPermission
	 */
	public void assistirMidia(Midia midia) throws MidiaNotFoundException, ClienteUnauthorizedPermission {
		if (midia == null) {
			throw new MidiaNotFoundException();
		}
		
		if (midia.isLancamento() && this.tipoCliente == ClienteType.REGULAR || this.tipoCliente == ClienteType.ESPECIALISTA) {
			throw new ClienteUnauthorizedPermission(this);
		}
		
		if (listaAssistidas.size() > 4) {
			this.tipoCliente = ClienteType.ESPECIALISTA;
		}
		
		if (!listaAssistidas.contains(midia)) {
			listaAssistidas.add(midia);
		}
		
		midia.aumentaAudiencia();
	}
	
	/**
	 * Executa funções para avaliar uma midia e faz verificações de para guarda. 
	 * 
	 * @param midia que será avaliada
	 * @param nota da avaliação
	 * @param comentario da avaliação
	 * @throws ClienteUnauthorizedPermission caso o cliente tente avaliar com comentario sendo do tipo "REGULAR"
	 * @throws ClienteReviewExcessException caso o cliente ja tenho avaliado esse midia anteriormente
	 * @throws ReviewScoreException caso a nota seja ou menor que 1 ou maior que 5
	 */
	public void avaliar(Midia midia, int nota, String comentario) throws ClienteUnauthorizedPermission, ClienteReviewExcessException, ReviewScoreException {
		
		if (comentario != null && getTipoCliente() == ClienteType.REGULAR) {
			throw new ClienteUnauthorizedPermission(this);
		}
		
		if (midia.getAvaliacoes().containsKey(this)) {
			throw new ClienteReviewExcessException(this);
		}
		
		if (nota < 1 || nota > 5) {
			throw new ReviewScoreException();
		}
		
		try {
			midia.addAvaliacao(this, nota, comentario);
		} catch (ClienteReviewExcessException | ReviewScoreException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Adiciona uma midia a lista "midias assistidas"
	 * 
	 * @param m
	 * @throws MidiaAlreadyInListException caso a midia ja tenha sido adicionada anteriormente
	 */
	public void addListaAssistida(Midia m) throws MidiaAlreadyInListException {
		if (this.listaAssistidas.contains(m)) {
			throw new MidiaAlreadyInListException(this, m);
		}
		
		listaAssistidas.add(m);
	}
	
	public int getListaAssistidasSize() {
		return listaAssistidas.size();
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Midia> getListaParaAssistir() {
		return listaParaAssistir;
	}

	public List<Midia> getListaAssistidas() {
		return listaAssistidas;
	}

	public ClienteType getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(ClienteType tipoCliente) {
		this.tipoCliente = tipoCliente;
	}


}
