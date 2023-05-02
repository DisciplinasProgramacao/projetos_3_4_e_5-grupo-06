
package app;

import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import business.*;

public class Aplicacao {

    public static void main(String[] args)throws IOException {
       
    
    	PlataformaStreaming plataforma = new PlataformaStreaming("testar"); 
        
		Scanner scanner = new Scanner(System.in);
        
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	
		while (true) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar série");
            System.out.println("2. Adicionar filme"); 
            System.out.println("3. Adicionar cliente");
            System.out.println("4. Fazer login");
            System.out.println("5. Carregar lista de filmes");
            System.out.println("6. Carregar lista de séries");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
            case 1:
            
            System.out.println("Digite o Nome da série:");
            int nome = scanner.nextInt();
            scanner.nextLine(); 
            
            System.out.println("Digite o Genero da série:");
            int genero = scanner.nextInt();
            scanner.nextLine(); 
            
            System.out.println("Digite o Idioma da série:");
            int idioma = scanner.nextInt();
            scanner.nextLine(); 
            
            System.out.println("Digite a Quantidade de episodios da série:");
            int quantidadeEpisodios = scanner.nextInt();
            scanner.nextLine(); 
            
            System.out.println("Digite a data de lançamento no formato dd/MM/yyyy:");
            LocalDate dataLancamento = LocalDate.parse(scanner.nextLine(), dateFormatter);
            
            Serie serie = new Serie(nome, genero, idioma, quantidadeEpisodios, dataLancamento);
            plataforma.adicionarSerie(serie);
            System.out.println("Série adicionada com sucesso!");
            
            	break;
            	
            case 2:
            	
            	System.out.println("Digite o Id do filme:");
                int idDoFilme = scanner.nextInt();
                scanner.nextLine(); 
                
            	System.out.println("Digite o Nome do filme:");
            	String nomeDoFilme = scanner.nextInt();
                scanner.nextLine();      
                
                System.out.println("Digite a duracao do filme:");
                String duracao = scanner.nextInt();
                scanner.nextLine();       
                
                System.out.println("Digite a data de lançamento no formato dd/MM/yyyy:");
                LocalDate dataLancamentoDoFilme = LocalDate.parse(scanner.nextLine(), dateFormatter);
            	
                Filme filme = new Filme(idDoFilme, nomeDoFilme, duracao, dataLancamentoDoFilme);
                plataforma.adicionarFilme(Filme);
                System.out.println("Filme adicionado com sucesso!");
            	
            	break;
            	
            case 3:
            	
            	System.out.println("Digite o nome de usuário do cliente:");
                String nomeDeUsuario = scanner.nextLine();

                System.out.println("Digite a senha do cliente:");
                String senhaDeUsuario = scanner.nextLine();

                Cliente cliente = new Cliente(nomeDeUsuario, senhaDeUsuario);
                plataforma.adicionarCliente(cliente);
                System.out.println("Cliente adicionado com sucesso!");
            	
            	break;
            	
            case 4:
            	
            	//	System.out.println("Insira o nome de usuario:");
            	//   String nomeUsuario = scanner.nextLine();
                
            	//  System.out.println("Insira a senha:");
            	// String senha = scanner.nextLine();
            	
                //if (nomeUsuario) == null){
                //	System.out.println("Erro no login: usario nao encontrado");
                //  System.out.println();
                // }
                
                //else {
                //    System.out.println("Erro no login: senha incorreta");
                //}
                //System.out.println();
                break;
            case 5:
            	// Carregando lista de filmes
            	Catalogo catalogo = new Catalogo();
            	catalogo.carregarFilmes();
            	System.out.println("Lista de filmes:");
            	for (Filme filme : catalogo.getFilmes()) {
            		System.out.println(filme.getId() + ", " + filme.getNome() + ", " + filme.getDataLancamento() + ", "+ filme.getDuracao());                 
        }  	
            	break;
            	
            case 6:
            	// Carregar lista de séries
            	catalogo.carregarSeries();
            	System.out.println("Lista de séries:");
            	for (Serie serie : catalogo.getSerie()) {
        		System.out.println(serie.getId() + ", " + serie.getNome() + ", " + serie.getDataLancamento());
            	}
            	break;
            case 0:
                System.out.println("Saindo...");
                scanner.close();
                return;
	}
		
    	

        

        // Carregando lista de audiência
        // catalogo.carregarAudiencia("caminhoarquivo");
        // System.out.println("Lista de audiência:");
        // for (Audiencia audiencia : catalogo.getAudiencia()) {
        // System.out.println(audiencia.getId() + ", " + audiencia.getData() + ", " +
        // audiencia.getQuantidadeEspectadores());
        // }
    }

}
