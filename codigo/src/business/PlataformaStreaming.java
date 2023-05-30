package business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import business.*;

public class PlataformaStreaming {
	private String nome;
	private HashMap<String, Serie> series;
	private HashMap<String, Cliente> clientes;
	private HashMap<String, Filme> filmes;
	private Cliente clienteAtual;
	private int porcentCliAvaAlta;
	
	
	/**
	 * Construtor da classe PlataformaStreaming
	 * 
	 * @param nome Nome da plataforma de streaming
	 */
	public PlataformaStreaming(String nome) {
		this.nome = nome;
		this.series = new HashMap<String, Serie>();
		this.clientes = new HashMap<String, Cliente>();
		this.filmes = new HashMap<String, Filme>();
		this.clienteAtual = null;
	}

	/**
	 * Método de login para autenticar um cliente na plataforma de streaming
	 * 
	 * @param nomeUsuario Nome do usuário do cliente
	 * @param senha       Senha do cliente
	 * @return Retorna um objeto do tipo Cliente, caso o login seja bem sucedido,
	 *         retorna null caso contrário
	 */
	public Cliente login(String nomeUsuario, String senha) {
		Cliente cliente = clientes.get(nomeUsuario);
		if (cliente != null && cliente.getSenha().equals(senha)) {
			this.clienteAtual = cliente;
			return cliente;
		}
		return null;
	}

	/**
	 * Método que retorna todos os clientes da plataforma de streaming
	 * 
	 * @return Retorna um HashMap com todos os clientes da plataforma
	 */
	public HashMap<String, Cliente> getTodosClientes() {
		return this.clientes;
	}

	/**
	 * Método que retorna todas as séries da plataforma de streaming
	 * 
	 * @return Retorna um HashMap com todas as séries da plataforma
	 */
	public HashMap<String, Serie> getTodasSeries() {
		return this.series;
	}

	/**
	 * Método que retorna todos os filmes da plataforma de streaming
	 * 
	 * @return Retorna um HashMap com todos os filmes da plataforma
	 */
	public HashMap<String, Filme> getTodosFilmes() {
		return this.filmes;
	}
	
	/**
	 * Método getter da porcentCliAvaBaixa
	 * 
	 * @return Retorna a quantidade de clientes com baixa porcentagem
	 */
	
	public float getPorcentCliAvaAlta() {
		return porctAvalClientes();
	}
	/**
	 * Método para ver a porcentagem de clientes com avaliacao menor que 15
	 * 
	 * @return Retorna a porcentagem de clientes com avaliacao menor que 15
	 */
	public float porctAvalClientes() {
		float qnt;
		for(this.clientes : clientes.getAvaliacaoM15())
			if(clientes.getAvaliacaoM15 == true) 
				qnt++;
		return (float)((this.clientes.size()-qnt)/this.clientes.size());
		
	}
	
	/**
	 * Método para adicionar uma nova série à plataforma de streaming
	 * 
	 * @param serie Objeto da classe Serie a ser adicionado
	 */
	public void adicionarSerie(Serie serie) {
		series.put(serie.getNome(), serie);
	}

	/**
	 * Método para adicionar um novo cliente à plataforma de streaming
	 * 
	 * @param cliente Objeto da classe Cliente a ser adicionado
	 */
	public void adicionarCliente(Cliente cliente) {
		clientes.put(cliente.getNomeDeUsuario(), cliente);
	}

	/**
	 * Método para adicionar um novo filme à plataforma de streaming
	 * 
	 * @param filme Objeto da classe Filme a ser adicionado
	 */
	public void adicionarFilmes(Filme filme) {
		filmes.put(filme.getNome(), filme);
	}

	/**
	 * Método para obter o objeto do cliente atualmente autenticado na plataforma de
	 * streaming
	 * 
	 * @return Retorna o objeto do tipo Cliente
	 */
	public Cliente getClienteAtual() {
		return clienteAtual;
	}

	/**
	 * Método para atualizar o valor do objeto do cliente atualmente autenticado na
	 * plataforma de streaming
	 * 
	 * @param clienteAtual Objeto do tipo Cliente a ser atualizado
	 * @return Retorna o objeto atualizado do tipo Cliente
	 */
	public Cliente setClienteAtual(Cliente clienteAtual) {
		return this.clienteAtual = clienteAtual;
	}

	/**
	 * 
	 * Filtra as séries por gênero.
	 * 
	 * @param genero O gênero a ser filtrado.
	 * @return Uma lista de séries que possuem o gênero especificado.
	 */
	public List<Serie> filtrarPorGenero(String genero) {
		List<Serie> resultado = new ArrayList<>();
		for (Serie serie : series.values()) {
			if (serie.getGenero().equals(genero)) {
				resultado.add(serie);
			}
		}
		return resultado;
	}

	/**
	 * 
	 * Filtra as séries por idioma.
	 * 
	 * @param idioma O idioma a ser filtrado.
	 * @return Uma lista de séries que possuem o idioma especificado.
	 */
	public List<Serie> filtrarPorIdioma(String idioma) {
		List<Serie> resultado = new ArrayList<>();
		for (Serie serie : series.values()) {
			if (serie.getIdioma().equals(idioma)) {
				resultado.add(serie);
			}
		}
		return resultado;
	}

	/**
	 * 
	 * Filtra as séries por quantidade de episódios.
	 * 
	 * @param quantEpisodios A quantidade de episódios a ser filtrada.
	 * @return Uma lista de séries que possuem a quantidade de episódios
	 *         especificada.
	 */
	public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
		List<Serie> resultado = new ArrayList<>();
		for (Serie serie : series.values()) {
			if (serie.getQuantidadeEpisodios() == quantEpisodios) {
				resultado.add(serie);
			}
		}
		return resultado;
	}

	/**
	 * 
	 * Chama o método da classe série para registrar a audiência.
	 * 
	 * @param serie A série que terá a audiência registrada.
	 */
	public void registrarAudiencia(Serie serie) {
		serie.registrarAudiencia();
	}

	/**
	 * 
	 * Escreve em um arquivo CSV os dados dos espectadores da plataforma de
	 * streaming.
	 * 
	 * @param nomeArquivo O nome do arquivo CSV a ser escrito.
	 * 
	 * @param clientes    Um HashMap que contém todos os clientes da plataforma.
	 * 
	 * @param a           A instância da plataforma.
	 */
	public void salvarEspectadores(String nomeArquivo, HashMap<String, Cliente> clientes, PlataformaStreaming a) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
			for (Cliente c : clientes.values()) {
				writer.write(a.login(c.getNomeDeUsuario(), c.getSenha()).toString() + "\n");
			}
			System.out.println("Os espectadores foram salvos no arquivo CSV com sucesso!");
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao salvar os espectadores no arquivo CSV.");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Escreve em um arquivo CSV os dados das séries da plataforma de streaming.
	 * 
	 * @param nomeArquivo O nome do arquivo CSV a ser escrito.
	 * 
	 * @param series      Um HashMap que contém todas as séries da plataforma.
	 */
	public void salvarSeries(String nomeArquivo, HashMap<String, Serie> series) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
			for (Serie s : series.values()) {
				writer.write(s.getId() + ";" + s.getNome() + ";" + s.getData() + "\n");
			}
			System.out.println("As series foram salvas no arquivo CSV com sucesso!");
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao salvar as series no arquivo CSV.");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Método que escreve em um arquivo CSV os dados da audiência da plataforma de
	 * streaming.
	 * 
	 * @param nomeArquivo Nome do arquivo CSV onde serão salvos os dados.
	 * @param a           Objeto da classe PlataformaStreaming.
	 * @param clientes    HashMap com objetos da classe Cliente.
	 * @param series      HashMap com objetos da classe Serie.
	 */
	public void salvarAudiencia(String nomeArquivo, PlataformaStreaming a, HashMap<String, Cliente> clientes,
			HashMap<String, Serie> series) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
			for (Cliente c : clientes.values()) {

				writer.write(a.login(c.getNomeDeUsuario(), c.getSenha()).toString() + ";" + c.getListaParaVer() + " / "
						+ c.getListaJaVistas() + "\n");

			}
			System.out.println("A audiencia foi salva no arquivo CSV com sucesso!");

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao salvar a audiencia no arquivo CSV.");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Método que lê e carrega dados de um arquivo CSV com informações de
	 * espectadores.
	 * 
	 * @param uri URI do arquivo CSV.
	 */
	public void carregarEspectadores(String uri) {
		try {
			Scanner sc = new Scanner(new File(uri));
			sc.useDelimiter(";|\n");
			while (sc.hasNext()) {
				System.out.print(" | " + sc.next());
			}
			System.out.println();
			sc.close();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao ler o arquivo de espectadores no arquivo CSV.");
			e.printStackTrace();
		}
	}

	public void registrarVisualizacao(Cliente cliente) {
		cliente.incrementarContadorMidiasAssistidas();
	}

	public Cliente obterClienteComMaisMidiasAssistidas() {
	    Cliente clienteComMaisMidiasAssistidas = null;
	    int maxMidiasAssistidas = 0;

	    List<Cliente> clientesList = new ArrayList<>(clientes.values());

	    for (Cliente cliente : clientesList) {
	        int contadorMidiasAssistidas = cliente.getContadorMidiasAssistidas();
	        if (contadorMidiasAssistidas > maxMidiasAssistidas) {
	            maxMidiasAssistidas = contadorMidiasAssistidas;
	            clienteComMaisMidiasAssistidas = cliente;
	        }
	    }

	    return clienteComMaisMidiasAssistidas;
	}



	/**
	 * 
	 * Método que lê e carrega dados de um arquivo CSV com informações de audiência.
	 * 
	 * @param uri URI do arquivo CSV.
	 */
	public void carregarAudiencia(String uri) {
		try {
			Scanner sc = new Scanner(new File(uri));
			sc.useDelimiter(";|\n");
			while (sc.hasNext()) {
				System.out.print(" | " + sc.next());
			}
			System.out.println();

			sc.close();

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao ler o arquivo de audiencia no arquivo CSV.");
			e.printStackTrace();
		}
	}

//public List<Filme> buscarFilmePorNome(String nome) {
	// List<Filme> resultado = new ArrayList<>();
	// for (Filme filme : filmes.values()) {
	// if (filme.getNome() == nome) {
	// resultado.add(filme);
	// }
	// }
	// return resultado;
	
	/**
	 * Método para ver qual cliente possui mais avaliações
	 * 
	 *@return Retorna o cliente
	 */
	
	//public Cliente maisAva() {
		
	//}
}
