package business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import business.composition.Avaliacao;
import business.composition.Cliente;
import business.composition.Midia;
import business.composition.enums.ClienteType;
import business.exceptions.ClienteAlreadyExistsException;
import business.exceptions.ClienteReviewExcessException;
import business.exceptions.ClienteUnauthorizedPermission;
import business.exceptions.MidiaAlreadyExistsException;
import business.exceptions.MidiaAlreadyInListException;
import business.exceptions.MidiaNotFoundException;
import business.exceptions.ReviewScoreException;
import business.subobjects.Filme;
import business.subobjects.Serie;

public class PlataformaStreaming {
	
	public static final String[] GENEROS = new String[] {"Ação", "Anime", "Aventura", "Comédia", "Documentário", "Drama", "Policial", "Romance", "Suspense" };
    public static final String[] IDIOMAS = new String[] {"Português", "Inglês", "Espanhol", "Japonês", "Chinês", "Russo", "Alemão", "Francês"};
	
    public static Scanner scanner = new Scanner(System.in);
	
	@SuppressWarnings("unused")
	private String plataformaName;
	private HashMap<String, Cliente> clientes;
	private HashMap<Integer, Midia> midias;
	private Cliente clienteLogado;

	public PlataformaStreaming(String plataformaName) {
		this.plataformaName = plataformaName;
		this.clientes = new HashMap<>();
		this.midias = new HashMap<>();
		this.clienteLogado = null;
		
		try {
			carregarFilmes();
			carregarEspectadores();
			carregarSeries();
			carregarAudiencia();
			carregarAvaliacoes();
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
	}

	/* Carga de Arquivos */
	
    public void carregarFilmes() throws NumberFormatException, ParseException {
        String caminhoArquivo = "./codigo/dados/filmes.csv";
        Scanner sc;
        try {
            sc = new Scanner(new File(caminhoArquivo));
            while (sc.hasNext()) {
                String a = sc.nextLine();
                String elementos[] = a.split(";");
                
                String generoAleatorio = GENEROS[(int) (Math.random() * (GENEROS.length))];
                String idiomaAleatorio = IDIOMAS[(int) (Math.random() * (IDIOMAS.length))];
                
                Midia novoFilme = new Filme(
                		elementos[1], // nome
                		generoAleatorio, // genero
                		idiomaAleatorio, // idioma
                		new SimpleDateFormat("dd/MM/yyyy").parse(elementos[2]), // data de lançamento
                		(elementos.length < 5) ? new Random().nextBoolean() : Boolean.parseBoolean(elementos[4]), // lancamento?
                		Integer.valueOf(elementos[3])); // duração
                
                this.midias.put(Integer.valueOf(elementos[0]), novoFilme);
            }
            
            System.out.println("Arquivo de filmes carregado com sucesso.");
        } catch (InputMismatchException e) {
            System.out.println("Erro ao ler o arquivo de filmes: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void carregarSeries() throws NumberFormatException, ParseException {
        String caminhoArquivo = "./codigo/dados/series.csv";
        Scanner sc;
        try {
            sc = new Scanner(new File(caminhoArquivo));
            while (sc.hasNext()) {
                String a = sc.nextLine();
                String elementos[] = a.split(";");
                
                String generoAleatorio = GENEROS[(int) (Math.random() * (GENEROS.length))];
                String idiomaAleatorio = IDIOMAS[(int) (Math.random() * (IDIOMAS.length))];
                
                Midia novaSerie = new Serie(
                		elementos[1],
                		generoAleatorio,
                		idiomaAleatorio,
                		new SimpleDateFormat("dd/MM/yyyy").parse(elementos[2]),
                		(elementos.length < 5) ? new Random().nextBoolean() : Boolean.parseBoolean(elementos[4]), // lancamento?
                		(elementos.length < 4) ? new Random().nextInt(100 - 1) + 1 : Integer.parseInt(elementos[3])); // qtdepisodios
                
                this.midias.put(Integer.valueOf(elementos[0]), novaSerie);
            }
            
            System.out.println("Arquivo de series carregado com sucesso.");
        } catch (InputMismatchException e) {
            System.out.println("Erro ao ler o arquivo de series: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void carregarAudiencia() {
        String caminhoArquivo = "./codigo/dados/audiencia.csv";
        Scanner sc;
        
        int notViewed = 0;
        
        try {
            sc = new Scanner(new File(caminhoArquivo));
            while (sc.hasNext()) {
                String a = sc.nextLine();
                String elementos[] = a.split(";");
                
                // client id, F||A, midia id
                
                if (midias.containsKey(Integer.valueOf(elementos[2])) && clientes.containsKey(elementos[0])) {
                	switch (elementos[1]) {
					case "F":
						try {
							clientes.get(elementos[0]).addListaParaAssistir(midias.get(Integer.valueOf(elementos[2])));
						} catch (NumberFormatException | MidiaAlreadyInListException e) {
							System.out.println(e.getMessage());
						}
						break;
					case "A":
						try {
							clientes.get(elementos[0]).assistirMidia(midias.get(Integer.valueOf(elementos[2])));
						} catch (MidiaNotFoundException | NumberFormatException | ClienteUnauthorizedPermission e) {
							notViewed++;
						}
						break;
					}
                }
            }
            
            System.out.println("Arquivo de audiencia carregado com sucesso. " + notViewed + " clientes não assistiram a certas midias pois não possuem nivel de profissional");
        } catch (InputMismatchException e) {
            System.out.println("Erro ao ler o arquivo de audiencia: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void carregarAvaliacoes() {
        String caminhoArquivo = "./codigo/dados/avaliacoes.csv";
        Scanner sc;
        try {
            sc = new Scanner(new File(caminhoArquivo));
            while (sc.hasNext()) {
                String a = sc.nextLine();
                String elementos[] = a.split(";");
                
                // id da midia; id do cliente; nota; comentario;
                
                if (midias.containsKey(Integer.valueOf(elementos[0]))) {
                	Midia m = midias.get(Integer.valueOf(elementos[0]));
                	
                	if (clientes.containsKey(elementos[1])) {
                		
                		Cliente c = clientes.get(elementos[1]);
                		
                		try {
							c.avaliar(m, Integer.valueOf(elementos[2]), c.getTipoCliente() != ClienteType.REGULAR && (elementos.length > 3) ? elementos[3] : null);
						} catch (NumberFormatException | ClienteUnauthorizedPermission | ClienteReviewExcessException | ReviewScoreException e) {
							System.out.println(e.getMessage());
						}
                	}
                }
            }
            
            System.out.println("Arquivo de avaliacoes carregado com sucesso.");
        } catch (InputMismatchException e) {
            System.out.println("Erro ao ler o arquivo de avaliacoes: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void carregarEspectadores() {
        String caminhoArquivo = "./codigo/dados/espectadores.csv";
        Scanner sc;
        try {
            sc = new Scanner(new File(caminhoArquivo));
            
            while (sc.hasNext()) {
                String a = sc.nextLine();
                String elementos[] = a.split(";");
                
                // name; id; password
                
                String id = elementos[1];
                String nome = elementos[0];
                String senha = elementos[2];
                
                Cliente cliente = new Cliente(nome, senha);
                
                this.clientes.put(id, cliente);
            }
            
            System.out.println("Arquivo de espectadores carregado com sucesso.");
        } catch (InputMismatchException e) {
            System.out.println("Erro ao ler o arquivo de clientes: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    /* Salvamento de Arquivos */
    
	public void salvarEspectadores() {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("./codigo/dados/espectadores.csv"))) {
			
			for (Map.Entry<String, Cliente> cliente : clientes.entrySet()) {
				writer
				.append(cliente.getValue().getNome())
				.append(";")
				.append(cliente.getKey())
				.append(";")
				.append(cliente.getValue().getSenha())
				.append("\n");
			}
			
			System.out.println("Os espectadores foram salvos no arquivo CSV com sucesso!");
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao salvar os espectadores no arquivo CSV.");
			e.printStackTrace();
		}
	}

	public void salvarSeries() {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("./codigo/dados/series.csv"))) {
			
			for (Map.Entry<Integer, Midia> series : midias.entrySet()) {
				
				if (series.getValue() instanceof Serie) {

					writer
					.append(String.valueOf(series.getKey()))
					.append(";")
					.append(series.getValue().getNome())
					.append(";")
					.append(new SimpleDateFormat("dd/MM/yyyy").format(series.getValue().getDataDeLancamento()))
					.append(";")
					.append(String.valueOf(((Serie) series.getValue()).getQuantidadeDeEpisodios()))
					.append(";")
					.append(Boolean.toString(series.getValue().isLancamento()))
					.append("\n");
				}
			}
			
			System.out.println("As series foram salvas no arquivo CSV com sucesso!");
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao salvar as series no arquivo CSV.");
			e.printStackTrace();
		}
	}
	
	public void salvarFilmes() {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("./codigo/dados/filmes.csv"))) {
			
			for (Map.Entry<Integer, Midia> filmes : midias.entrySet()) {
				
				if (filmes.getValue() instanceof Filme) {

					writer
					.append(String.valueOf(filmes.getKey()))
					.append(";")
					.append(filmes.getValue().getNome())
					.append(";")
					.append(new SimpleDateFormat("dd/MM/yyyy").format(filmes.getValue().getDataDeLancamento()))
					.append(";")
					.append(String.valueOf(((Filme) filmes.getValue()).getMinutosDeDuracao()))
					.append(";")
					.append(Boolean.toString(filmes.getValue().isLancamento()))
					.append("\n");
				}
			}
			
			System.out.println("Os filmes foram salvos no arquivo CSV com sucesso!");
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao salvar as series no arquivo CSV.");
			e.printStackTrace();
		}
	}

	public void salvarAudiencia() {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("./codigo/dados/audiencia.csv"))) {
			
			for (Map.Entry<String, Cliente> cliente : this.clientes.entrySet()) {
				
				for (Midia midia : cliente.getValue().getListaParaAssistir()) {
					
					writer
					.append(cliente.getKey())
					.append(";")
					.append("F")
					.append(";")
					.append(String.valueOf(getMidiaIdByName(midia.getNome())))
					.append("\n");
					
				}
				
				for (Midia midia : cliente.getValue().getListaAssistidas()) {
					writer.append(cliente.getKey())
					.append(";")
					.append("A")
					.append(";")
					.append(String.valueOf(getMidiaIdByName(midia.getNome())))
					.append("\n");
				}
				
			}

			System.out.println("A audiencia foi salva no arquivo CSV com sucesso!");

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao salvar a audiencia no arquivo CSV.");
			e.printStackTrace();
		}
	}
	
	public void salvarAvaliacoes() {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("./codigo/dados/avaliacoes.csv"))) {
			
			for (Map.Entry<Integer, Midia> midia : this.midias.entrySet()) {
				
				for (Map.Entry<Cliente, Avaliacao> avaliacao : midia.getValue().getAvaliacoes().entrySet()) {
					
					// id midia ; id cliente ; nota ; comentario
					
					writer
					.append(String.valueOf(midia.getKey()))
					.append(";")
					.append(getClienteIdByName(avaliacao.getKey().getNome()))
					.append(";")
					.append(String.valueOf(avaliacao.getValue().getNota()))
					.append(";")
					.append(avaliacao.getValue().getComentario() == null ? "" : avaliacao.getValue().getComentario())
					.append("\n");
					
				}
				
			}

			System.out.println("As avaliacoes foram salvas no arquivo CSV com sucesso!");

		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao salvar a audiencia no arquivo CSV.");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		salvarAudiencia();
		salvarAvaliacoes();
		salvarEspectadores();
		salvarFilmes();
		salvarSeries();
	}
	
	/* Metodos de Scanner */
	
	public void adicionarSerie() {
		System.out.println("Digite o ID da serie: ");
		String id = scanner.nextLine();
		
		System.out.println("Digite o Nome da série: ");
		String nome = scanner.nextLine();

		System.out.println("Generos Disponiveis: " + Arrays.toString(GENEROS));
		System.out.println("Digite o Genero da série: ");
		String genero = scanner.nextLine();

		System.out.println("Idiomas Disponiveis: " + Arrays.toString(IDIOMAS));
		System.out.println("Digite o Idioma da série: ");
		String idioma = scanner.nextLine();

		System.out.println("Digite a Quantidade de episodios da série:");
		String quantidadeEpisodios = scanner.nextLine();
		
		System.out.println("Digite a data de lançamento da serie: (dd-mm-aaaa)");
		Date dataLancamento = null;
		
		try {
			dataLancamento = new SimpleDateFormat("dd-MM-yyyy").parse(scanner.nextLine());

			Midia serie = new Serie(nome, genero, idioma, dataLancamento, false, Integer.valueOf(quantidadeEpisodios));

			adicionarMidia(Integer.valueOf(id), serie);
			System.out.println("Série adicionada com sucesso!");
		} catch (MidiaAlreadyExistsException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			System.out.println("Formato invalido para a data");
		}
	}

	public void adicionarCliente() {
		System.out.println("Digite o nome do cliente:");
		String nome = scanner.nextLine();
		
		System.out.println("Digite o id do cliente: ");
		String id = scanner.nextLine();

		System.out.println("Digite a senha do cliente:");
		String senha = scanner.nextLine();

		Cliente cliente = new Cliente(nome, senha);
		
		try {
			adicionarCliente(id, cliente);
			System.out.println("Cliente adicionado com sucesso!");
		} catch (ClienteAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
	}

	public void adicionarFilme() {
		System.out.println("Digite o ID do filme: ");
		String id = scanner.nextLine();
		
		System.out.println("Digite o nome do filme:");
		String nome = scanner.nextLine();

		System.out.println("Generos Disponiveis: " + Arrays.toString(GENEROS));
		System.out.println("Digite o genero do filme:");
		String genero =  scanner.nextLine();

		System.out.println("Idiomas Disponiveis: " + Arrays.toString(IDIOMAS));
		System.out.println("Digite o idioma do filme:");
		String idioma =  scanner.nextLine();

		System.out.println("Digite a duração em minutos do filme:");
		String duracao = scanner.nextLine();
		
		System.out.println("Digite a data de lançamento do filme: (dd-mm-aaaa)");
		Date dataLancamento = null;
		
		try {
			dataLancamento = new SimpleDateFormat("dd-MM-yyyy").parse(scanner.nextLine());

			Midia filme = new Filme(nome, genero, idioma, dataLancamento, false, Integer.valueOf(duracao));

			adicionarMidia(Integer.valueOf(id), filme);
			System.out.println("Filme adicionado com sucesso!");
		} catch (MidiaAlreadyExistsException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			System.out.println("Formato invalido para a data");
		}
	}

	public void logar() {
		System.out.println();
		System.out.println("Insira o id de usuario:");
		String nomeUsuario = scanner.nextLine();

		System.out.println("Insira a senha:");
		String senha = scanner.nextLine();

		Cliente clienteLogado = login(nomeUsuario, senha);

		if (clienteLogado == null) {
			System.out.println("Erro no login: usuario ou senha incorreto");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			this.clienteLogado = clienteLogado;
			
			System.out.println("Login realizado com sucesso!");
			System.out.println("Cliente logado: " + clienteLogado.getNome());
		}
	}
	
	public void filtrarMidia() {
		
		System.out.println("Você deseja filtrar por genero (1), idioma (2) ou quantidade de episodios (3) ?");
		String opcao = scanner.nextLine();
		
		switch (opcao) {
		case "1":
			System.out.println("Generos para filtragem: " + Arrays.toString(GENEROS));
			
			System.out.println("Digite o genero escolhido para filtragem: ");
			
			String genero = scanner.nextLine();
			
			System.out.println("Lista de Midias para Assistir filtradas por genero: ");
			
			filtrarPorGenero(genero);
			
			break;

		case "2":
			
			System.out.println("Idiomas para filtragem: " + Arrays.toString(IDIOMAS));
			
			System.out.println("Digite o idioma escolhido para filtragem: ");
			
			String idioma = scanner.nextLine();
			
			System.out.println("Lista de Midias para Assistir filtradas por idioma: ");
			
			filtrarPorIdioma(idioma);
			break;
		
		case "3":
			System.out.println("Digite a quantidade de episodios para filtragem: ");
			
			String episodios = scanner.nextLine();
			
			System.out.println("Lista de Midias para Assistir filtradas pela Quantidade de Episodios: ");
			
			filtrarPorQuantidadeDeEpisodios(Integer.valueOf(episodios));
			break;
			
		default:
			System.out.println("Opcao invalida");
			break;
		}
	}

	/* Metodos para adicionar em mémoria */
	
	/**
	 * Adiciona uma midia ao mapa de midias, utilizando par "id" como chave e "m" como valor
	 * Lança exceção caso a midia ja exista no mapa
	 *
	 * @param id
	 * @param m
	 * @throws MidiaAlreadyExistsException
	 *  
 	 */
	public void adicionarMidia(Integer id, Midia m) throws MidiaAlreadyExistsException {
		if (this.midias.containsKey(id)) {
			throw new MidiaAlreadyExistsException(m);
		}
		
		this.midias.put(id, m);
	}
	
	/**
	 * 
	 * Tenta adiciona um novo cliente ao mapa de clientes, utilizando "id" como chave e "c" como valor
	 * Lança exceção caso o cliente ja exista no mapa
	 * 
	 * @param id
	 * @param cliente
	 * @throws ClienteAlreadyExistsException
	 */
	public void adicionarCliente(String id, Cliente cliente) throws ClienteAlreadyExistsException {
		if (clientes.containsKey(id)) {
			throw new ClienteAlreadyExistsException(cliente);
		}
		
		clientes.put(id, cliente);
	}

	/**
	 * 
	 * Tenta fazer login com um "nomeDeUsuario" e "senha"
	 * Retorna um objeto cliente se conseguir ou null se não conseguir
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return Objeto cliente logado
	 */
	public Cliente login(String nomeUsuario, String senha) {
		
		Cliente cliente = this.clientes.get(nomeUsuario);
		
		if (cliente != null) {
			if (cliente.getSenha().equals(senha)) {
				this.clienteLogado = cliente;
				
				return clienteLogado;
			}
		}
		
		return null;
	}
	
	/* Buscas */
	
	/**
	 * @return O cliente logado ou null caso não há nenhum cliente logado
	 */
	public Cliente getClienteLogado() {
		return this.clienteLogado;
	}

	/**
	 * Acha o Id de um cliente utilizando como parametro o seu nome
	 * 
	 * @param userName
	 * @return Id do cliente caso encontrado ou null caso não encontrado
	 */
	public String getClienteIdByName(String userName) {
		for (Map.Entry<String, Cliente> cliente : this.clientes.entrySet()) {
			if (cliente.getValue().getNome().equals(userName)) {
				return cliente.getKey();
			}
		}
		return null;
	}
	
	/**
	 * Acha o Id de uma midia utilizando como parametro o seu nome
	 * 
	 * @param midiaName
	 * @return Id da midia caso encontrada ou null caso não encontrada
	 */
	public Integer getMidiaIdByName(String midiaName) {
		for (Map.Entry<Integer, Midia> midia : this.midias.entrySet()) {
			if (midia.getValue().getNome().equals(midiaName)) {
				return midia.getKey();
			}
		}
		
		return null;
	}

	/* Ações */
	
	/**
	 * Executa os metodos para que o cliente logado assista a uma midia
	 * 
	 * @throws MidiaNotFoundException caso a midia informada nao seja encontrada
	 * @throws ClienteUnauthorizedPermission caso a midia seja lançamento e o cliente não seja profissional
	 */
	public void assistirMidia() throws MidiaNotFoundException, ClienteUnauthorizedPermission {
		System.out.println("Digite o id da midia que você deseja assistir: ");
		String id = scanner.nextLine();
		
		Midia m = this.midias.get(Integer.valueOf(id));
		
		if (m == null) {
			throw new MidiaNotFoundException();
		}
		
		if (m.isLancamento() && getClienteLogado().getTipoCliente() != ClienteType.PROFISSIONAL) {
			throw new ClienteUnauthorizedPermission(getClienteLogado());
		}
		
		try {
			getClienteLogado().assistirMidia(m);
			System.out.println("Midia " + m.getNome() + " assistida!");
		} catch (MidiaNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Adiciona na lista "para assistir" do cliente logado a midia informada 
	 * 
	 * @throws MidiaNotFoundException caso a midia informada não seja encontrada
	 */
	public void adicionarMidiaParaAssistir() throws MidiaNotFoundException {
		System.out.println("Digite o id da midia que gostaria de adicionar na lista para assistir: ");
		
		Midia m = this.midias.get(scanner.nextInt());
		
		if (m == null) { 
			throw new MidiaNotFoundException();
		}
		
		try {
			getClienteLogado().addListaParaAssistir(m);
			System.out.println("Midia " + m.getNome() + " adicionada a lista para assistir!");
		} catch (MidiaAlreadyInListException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Adiciona na lista "assistidos" do cliente logado a midia informada 
	 * 
	 * @throws MidiaNotFoundException caso a midia informada não seja encontrada
	 */
	public void adicionarNaListaDeAssistidos() throws MidiaNotFoundException {
		System.out.println("Digite o id da midia que gostaria de adicionar na lista de assistidas: ");
		
		Midia m = this.midias.get(scanner.nextInt());
		
 		if (m == null) {
 			throw new MidiaNotFoundException();
 		}
		
		try {
			getClienteLogado().addListaAssistida(m);
			System.out.println("Midia " + m.getNome() + " adicionada a lista de assistidos!");
		} catch (MidiaAlreadyInListException e) {
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * Exibe a lista de "midias para assistir" do cliente logado 
	 */
	public void verListaMidiasParaAssistir() {
		
		for (Midia m : getClienteLogado().getListaParaAssistir()) {
			
			System.out.println("-----------\n");
			System.out.println("ID: " + getMidiaIdByName(m.getNome()) + "\n");
			
			m.print();
			
		}
	}

	/**
	 * Exibe a lista de "midias assistidas" do cliente logado 
	 */
	public void verListaMidiasAssistidas() {
		
		for (Midia m : getClienteLogado().getListaAssistidas()) {
			
			System.out.println("-----------\n");
			System.out.println("ID: " + getMidiaIdByName(m.getNome()) + "\n");
			
			m.print();
			
		}
		
	}
	
	/**
	 * Filtra a lista "para assistir" do cliente logado passando por parametro o genero da midia
	 * 
	 * @param genero
	 * @return Lista do tipo "Midia" filtrada por genero
	 */
	public List<Midia> filtrarPorGenero(String genero) {
		List<Midia> filtradas = new ArrayList<>();
		
		getClienteLogado().getListaParaAssistir().forEach(midia -> { if (midia.getGenero().equals(genero)) { filtradas.add(midia); }});
		
		filtradas.forEach(midia -> midia.print());
		
		return filtradas;
	}
	
	/**
	 * Filtra a lista "para assistir" do cliente logado passando por parametro o idioma da midia
	 * 
	 * @param genero
	 * @return Lista do tipo "Midia" filtrada por idioma
	 */
	public List<Midia> filtrarPorIdioma(String idioma) {
		List<Midia> filtradas = new ArrayList<>();
		
		getClienteLogado().getListaParaAssistir().forEach(midia -> { if (midia.getIdioma().equals(idioma)) { filtradas.add(midia); }});
		
		filtradas.forEach(midia -> midia.print());
		
		return filtradas;
	}
	
	/**
	 * Filtra a lista "para assistir" do cliente logado passando por parametro o numero de episodios da serie
	 * 
	 * @param genero
	 * @return Lista do tipo "Serie" filtrada por quantidade de episodios
	 */
	public List<Serie> filtrarPorQuantidadeDeEpisodios(Integer qtdEpisodios) {
		List<Serie> filtradas = new ArrayList<>();
		
		getClienteLogado().getListaParaAssistir().forEach(midia -> { if (midia instanceof Serie) { if (((Serie) midia).getQuantidadeDeEpisodios() == qtdEpisodios) { filtradas.add((Serie) midia); }}});
		
		filtradas.forEach(midia -> midia.print());
		
		return filtradas;
	}

	/**
	 * Torna o cliente logado "Profissional" e permite que ele assista a midias em lançamento
	 */
	public void virarProfissional() {
		System.out.println("Você agora é um cliente profissional");
		
		getClienteLogado().setTipoCliente(ClienteType.PROFISSIONAL);
	}

	/**
	 * Exibe as avaliações do cliente logado
	 */
	public void verAvaliacoesClienteLogado() {
		
		System.out.println("Avaliações do cliente: " + getClienteLogado().getNome());
		
		for (Midia midia : this.midias.values()) {
			
			for (Map.Entry<Cliente, Avaliacao> avaliacao : midia.getAvaliacoes().entrySet()) {
				
				if (avaliacao.getKey() == this.getClienteLogado()) {
					
					System.out.println("-----");
					System.out.println("Midia: " + midia.getNome());
					System.out.println("Nota: " + avaliacao.getValue().getNota());
					
					if (avaliacao.getValue().getComentario() != null) {
						System.out.println("Comentario: " + avaliacao.getValue().getComentario());
					}
					
					System.out.println("");
				}
			}
		}
	}

	/**
	 * Exibe o catalogo de midias
	 */
	public void verCatalogoDeMidias() {

		System.out.println("Catalogo de Midias: ");
		
		for (Map.Entry<Integer, Midia> midia : this.midias.entrySet()) {
			
			System.out.println("-----------\n");
			System.out.println("ID: " + midia.getKey() + "\n");
			
			midia.getValue().print();
		}
	}

	/**
	 * Exibe a audiencia de uma midia informada
	 * 
	 * @throws MidiaNotFoundException caso a midia informada não seja encontrada
	 */
	public void verAudienciaDeUmaMidia() throws MidiaNotFoundException {
		
		System.out.println("Digite o id da midia que você gostaria de ver a audiencia: ");
		
		Midia m;
		
		try {
			m = this.midias.get(Integer.valueOf(scanner.nextLine()));
		} catch (NumberFormatException e) {
			throw new MidiaNotFoundException();
		}
		
		if (m == null) {
			throw new MidiaNotFoundException();
		}
		
		System.out.println("Audiencia de " + m.getNome() + ": " + m.getAudiencia());
	}

	/**
	 * Exibe as informações de uma midia informada
	 * 
	 * @throws MidiaNotFoundException caso a midia informada não seja encontrada
	 */
	public void encontrarMidiaPeloNome() throws MidiaNotFoundException {
		
		System.out.println("Digite o nome da midia que você gostaria de encontrar: ");
		
		Integer id = getMidiaIdByName(scanner.nextLine());
		
		Midia m = this.midias.get(id);
		
		if (m == null) {
			throw new MidiaNotFoundException();
		}
		
		System.out.println("-----------\n");
		System.out.println("ID: " + id + "\n");
		
		m.print();
		
	}

	/**
	 * Executa os metodos para avaliar uma midia informada
	 * 
	 * @throws MidiaNotFoundException se a midia informada não for encontrada
	 */
	public void avaliarMidia() throws MidiaNotFoundException {
		System.out.println("");
		
		System.out.println("Midias assistidas: ");
		
		for (Midia midia : getClienteLogado().getListaAssistidas()) {
			System.out.println(getMidiaIdByName(midia.getNome()) + " - " + midia.getNome());
		}
		
		System.out.println("Digite o id da midia que deseja avaliar");
		
		Midia m = this.midias.get(Integer.valueOf(scanner.nextLine()));;
		
		if (m == null) {
			throw new MidiaNotFoundException();
		}
		
		if (!getClienteLogado().getListaAssistidas().contains(m)) {
			System.out.println("Não é possivel avaliar uma midia que não foi assistida");
			return;
		}
			
			System.out.println("Digite uma nota de 1 a 5 (numero inteiro)");
			
			String nota = scanner.nextLine();
			
			String comentario = null;
			
			if (getClienteLogado().getTipoCliente() != ClienteType.REGULAR) {
				
				System.out.println("Digite um comentario");
				
				comentario = scanner.nextLine();
			}
			
			try {
				m.addAvaliacao(getClienteLogado(), Integer.valueOf(nota), comentario);
				System.out.println("Midia avaliada com sucesso!");
			} catch (ClienteReviewExcessException | NumberFormatException | ReviewScoreException e) {
				System.out.println(e.getMessage());
			}
	}

	/* Relatorios */
	
	/**
	 * Exibe qual cliente assistiu mais midias
	 */
	
	public void qualClienteAssistiuMaisMidias() {
		
		List<Cliente> clientes = this.clientes.values().stream()
				.sorted(Comparator.comparingInt(Cliente::getListaAssistidasSize).reversed())
				.collect(Collectors.toList());
		
		Cliente c = clientes.get(0);
		
		System.out.println("O cliente que assistiu mais midias foi:");
		System.out.println("");
		System.out.println("Nome: " + c.getNome());
		System.out.println("Numero de Midias Assistidas: " + c.getListaAssistidasSize());
	}

	
	/**
	 * Exibe qual cliente tem mais avaliações
	 */
	public void qualClienteTemMaisAvaliacoes() {
		
        Map<Cliente, Integer> contadorClientes = new HashMap<>();

        for (Map.Entry<Integer, Midia> entry : this.midias.entrySet()) {
            Midia midia = entry.getValue();
            Map<Cliente, Avaliacao> mapaAvaliacoes = midia.getAvaliacoes(); // Obtém o mapa de avaliações

            for (Map.Entry<Cliente, Avaliacao> avaliacaoEntry : mapaAvaliacoes.entrySet()) {
                Cliente cliente = avaliacaoEntry.getKey();

                if (contadorClientes.containsKey(cliente)) {
                    int contador = contadorClientes.get(cliente);
                    contadorClientes.put(cliente, contador + 1);
                } else {
                    contadorClientes.put(cliente, 1);
                }
            }
        }

        Cliente clienteMaisAvaliacoes = null;
        int maxAvaliacoes = 0;
        
        for (Map.Entry<Cliente, Integer> contadorEntry : contadorClientes.entrySet()) {
            Cliente cliente = contadorEntry.getKey();
            int avaliacoes = contadorEntry.getValue();

            if (clienteMaisAvaliacoes == null || avaliacoes > maxAvaliacoes) {
                maxAvaliacoes = avaliacoes;
                clienteMaisAvaliacoes = cliente;
            }
        }

        if (clienteMaisAvaliacoes != null) {
            System.out.println("Cliente que mais avaliou: " + clienteMaisAvaliacoes.getNome());
        } else {
            System.out.println("Nenhum cliente encontrado.");
        }
	}

	
	/**
	 * Exibe qual a porcentagem de clientes com pelo menos 15 avaliações
	 */
	public void qualPorcentagemDeClientesComPeloMenos15Avaliacoes() {
        Map<Cliente, Integer> contadorAvaliacoes = new HashMap<>();

        for (Map.Entry<Integer, Midia> entry : this.midias.entrySet()) {
            Midia midia = entry.getValue();
            Map<Cliente, Avaliacao> mapaAvaliacoes = midia.getAvaliacoes();

            for (Map.Entry<Cliente, Avaliacao> avaliacaoEntry : mapaAvaliacoes.entrySet()) {
                Cliente cliente = avaliacaoEntry.getKey();

                if (contadorAvaliacoes.containsKey(cliente)) {
                    int contador = contadorAvaliacoes.get(cliente);
                    contadorAvaliacoes.put(cliente, contador + 1);
                } else {
                    contadorAvaliacoes.put(cliente, 1);
                }
            }
        }

        int totalClientes = contadorAvaliacoes.size();
        int clientesComAvaliacoes = 0;

        for (Map.Entry<Cliente, Integer> contadorEntry : contadorAvaliacoes.entrySet()) {
            int numAvaliacoes = contadorEntry.getValue();

            if (numAvaliacoes >= 15) {
                clientesComAvaliacoes++;
            }
        }

        double porcentagem = (clientesComAvaliacoes / (double) totalClientes) * 100;

        System.out.println("Porcentagem de clientes com pelo menos 15 avaliações: " + porcentagem + "%");
		
	}

	
	/**
	 * Exibe quais são as 10 midias mais vistas
	 */
	public void quaisSaoAs10MidiasMaisVistas() {
        Map<Midia, Integer> contadorMidias = new HashMap<>();

        // Itera sobre cada objeto Midia
        for (Map.Entry<Integer, Midia> entry : this.midias.entrySet()) {
            Midia midia = entry.getValue();
            int visualizacoes = midia.getAudiencia();

            if (contadorMidias.containsKey(midia)) {
                int contador = contadorMidias.get(midia);
                contadorMidias.put(midia, contador + visualizacoes);
            } else {
                contadorMidias.put(midia, visualizacoes);
            }
        }

        List<Map.Entry<Midia, Integer>> listaClassificada = new ArrayList<>(contadorMidias.entrySet());
        listaClassificada.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Midia> dezMidiasMaisVistas = new ArrayList<>();
        int numMidias = Math.min(10, listaClassificada.size());
        for (int i = 0; i < numMidias; i++) {
            dezMidiasMaisVistas.add(listaClassificada.get(i).getKey());
        }

        System.out.println("As 10 mídias mais vistas:");
        System.out.println();
        for (Midia midia : dezMidiasMaisVistas) {
            midia.print();
        }
		
	}
	
	
	/**
	 * Exibe quais são as 10 midias mais vistas por genero
	 */
	public void dezMidiasMaisVistasPorGenero() {
        Map<String, List<Midia>> midiasMaisVistasPorGenero = new HashMap<>();

        for (String genero : GENEROS) {
            midiasMaisVistasPorGenero.put(genero, new ArrayList<>());
        }

        for (Map.Entry<Integer, Midia> entry : this.midias.entrySet()) {
            Midia midia = entry.getValue();
            int visualizacoes = midia.getAudiencia();
            String genero = midia.getGenero();

            List<Midia> midiasMaisVistasDoGenero = midiasMaisVistasPorGenero.get(genero);

            boolean encontrada = false;
            for (Midia m : midiasMaisVistasDoGenero) {
                if (m.equals(midia)) {
                    encontrada = true;
                    if (visualizacoes > m.getAudiencia()) {
                        m = midia;
                    }
                    break;
                }
            }

            if (!encontrada) {
                midiasMaisVistasDoGenero.add(midia);
            }
        }

        for (String genero : GENEROS) {
            List<Midia> midiasDoGenero = midiasMaisVistasPorGenero.get(genero);
            midiasDoGenero.sort(Comparator.comparingInt(Midia::getAudiencia).reversed());
        }

        Map<String, List<Midia>> dezMidiasMaisVistasPorGenero = new HashMap<>();
        for (String genero : GENEROS) {
            List<Midia> midiasDoGenero = midiasMaisVistasPorGenero.get(genero);
            int numMidias = Math.min(10, midiasDoGenero.size());
            List<Midia> dezMidiasMaisVistas = midiasDoGenero.subList(0, numMidias);
            dezMidiasMaisVistasPorGenero.put(genero, dezMidiasMaisVistas);
        }

        System.out.println("As 10 Midias mais assistidas separadas por genero: ");
        System.out.println();
        for (String genero : GENEROS) {
            System.out.println("Gênero: " + genero);
            List<Midia> dezMidiasMaisVistas = dezMidiasMaisVistasPorGenero.get(genero);
            for (Midia midia : dezMidiasMaisVistas) {
                midia.print();
            }
            System.out.println();
        }
	}

	
	/**
	 * Exibe as dez midias com melhor avaliação
	 */
	public void dezMidiasComMelhorAvaliacao() {
        Map<Midia, Integer> melhoresAvaliacoes = new HashMap<>();

        // Itera sobre cada objeto Midia
        for (Map.Entry<Integer, Midia> entry : this.midias.entrySet()) {
            Midia midia = entry.getValue();
            Map<Cliente, Avaliacao> avaliacoes = midia.getAvaliacoes();

            if (avaliacoes.size() >= 100) {
                int somaNotas = 0;

                for (Avaliacao avaliacao : avaliacoes.values()) {
                    somaNotas += avaliacao.getNota();
                }

                melhoresAvaliacoes.put(midia, somaNotas);
            }
        }

        List<Map.Entry<Midia, Integer>> sortedAvaliacoes = new ArrayList<>(melhoresAvaliacoes.entrySet());
        sortedAvaliacoes.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        int numMidias = Math.min(10, sortedAvaliacoes.size());
        List<Map.Entry<Midia, Integer>> dezMelhoresAvaliacoes = sortedAvaliacoes.subList(0, numMidias);

        System.out.println("10 Midias com melhor avaliação em nota: ");
        System.out.println();
        
        if (dezMelhoresAvaliacoes.isEmpty()) {
        	System.out.println("Não há midias com avaliações superiores a 100.");
        	return;
        }
        
        for (Map.Entry<Midia, Integer> entry : dezMelhoresAvaliacoes) {
            Midia midia = entry.getKey();
            int somaNotas = entry.getValue();
            System.out.println("Mídia: " + midia.getNome() + " - Somatorio das Notas: " + somaNotas);
            System.out.println();
        }
		
	}

	
	/**
	 * Exibe as dez midias com melhor avaliação por genero
	 */
	public void dezMidiasComMelhorAvaliacaoPorGenero() {
        Map<String, List<Midia>> melhoresAvaliacoesPorGenero = new HashMap<>();

        for (String genero : GENEROS) {
            melhoresAvaliacoesPorGenero.put(genero, new ArrayList<>());
        }

        for (Map.Entry<Integer, Midia> entry : this.midias.entrySet()) {
            Midia midia = entry.getValue();
            Map<Cliente, Avaliacao> avaliacoes = midia.getAvaliacoes();

            if (avaliacoes.size() >= 100) {
                int somaNotas = 0;

                for (Avaliacao avaliacao : avaliacoes.values()) {
                    somaNotas += avaliacao.getNota();
                }

                String genero = midia.getGenero();

                if (melhoresAvaliacoesPorGenero.containsKey(genero)) {
                    List<Midia> melhoresAvaliacoes = melhoresAvaliacoesPorGenero.get(genero);

                    if (melhoresAvaliacoes.size() < 10 || somaNotas > getMenorSomaNotas(melhoresAvaliacoes)) {
                        melhoresAvaliacoes.add(midia);

                        melhoresAvaliacoes.sort(Comparator.comparingInt(m -> getSomaNotas(m.getAvaliacoes())));

                        if (melhoresAvaliacoes.size() > 10) {
                            melhoresAvaliacoes.remove(10);
                        }
                    }
                }
            }
        }

        for (String genero : melhoresAvaliacoesPorGenero.keySet()) {
            System.out.println("10 Midias com melhor avaliação em nota por genero: " + genero);

            List<Midia> melhoresAvaliacoes = melhoresAvaliacoesPorGenero.get(genero);
            
            if (melhoresAvaliacoes.isEmpty()) {
            	System.out.println("Não há midias com avaliações superiores a 100.");
                System.out.println();
            	continue;
            }
            
            for (Midia midia : melhoresAvaliacoes) {
                midia.print();
            }

            System.out.println();
        }
    }

    
	/**
	 * Metodo para somar todas as notas de um determinado mapa de avaliações de uma midia
	 * 
	 * @param avaliacoes
	 * @return somatorio de notas
	 */
	private static int getSomaNotas(Map<Cliente, Avaliacao> avaliacoes) {
        int soma = 0;
        for (Avaliacao avaliacao : avaliacoes.values()) {
            soma += avaliacao.getNota();
        }
        return soma;
    }

    
	/**
	 * Descobre qual é o menor somatorio de notas dada uma lista de midias
	 * 
	 * @param lista de midias
	 * @return menor somatorio de notas
	 */
	private static int getMenorSomaNotas(List<Midia> midias) {
        int menorSoma = Integer.MAX_VALUE;
        for (Midia midia : midias) {
            int soma = getSomaNotas(midia.getAvaliacoes());
            if (soma < menorSoma) {
                menorSoma = soma;
            }
        }
        return menorSoma;
    }
	
}
