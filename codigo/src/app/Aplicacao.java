package app;

import java.io.IOException;
import java.util.Scanner;

import business.*;

public class Aplicacao {

    public static void main(String[] args)throws IOException {
       
    
    	PlataformaStreaming plataforma = new PlataformaStreaming("Goflix");         
    	Catalogo catalogo = new Catalogo();
    	
        Scanner scanner = new Scanner(System.in);
        
    	
	while (true) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar série");
            System.out.println("2. Adicionar filme"); 
            System.out.println("3. Registrar cliente");
            System.out.println("4. Fazer login");
            System.out.println("5. Carregar lista de filmes");
            System.out.println("6. Carregar lista de séries");
            System.out.println("7. Carregar audiência");
            System.out.println("8. Carregar espectadores");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
            case 1:
            
            System.out.println("Digite o Nome da série:");
            String nome = scanner.nextLine();
           
            System.out.println("Digite o Genero da série:");
            String genero =  scanner.nextLine();
            
            System.out.println("Digite o Idioma da série:");
            String idioma =  scanner.nextLine();
                       
            System.out.println("Digite a Quantidade de episodios da série:");
            int quantidadeEpisodios = scanner.nextInt();
            scanner.nextLine(); 
            
            System.out.println("Digite a audiencia da série:");
            int audiencia = scanner.nextInt();
            scanner.nextLine(); 
            
            Serie serie = new Serie(nome, genero, idioma, quantidadeEpisodios, audiencia);
            plataforma.adicionarSerie(serie);
            System.out.println("Série adicionada com sucesso!");
            
            	break;
            	
            case 2:
            	
            	System.out.println("Digite o Id do filme:");
                int idFilme = scanner.nextInt();
                scanner.nextLine(); 
                
            	System.out.println("Digite o Nome do filme:");
            	String nomeDoFilme =  scanner.nextLine();
                     
                System.out.println("Digite a data de lançamento no formato dd/MM/yyyy:");
                String dataLancamento =  scanner.nextLine();
                
                System.out.println("Digite a duracao do filme:");
                int duracao =  scanner.nextInt();
                scanner.nextLine();      
                            
            	
                Filme filme = new Filme(idFilme, nomeDoFilme, dataLancamento, duracao);
                plataforma.adicionarFilmes(filme);
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
            	
            	System.out.println("Insira o nome de usuario:");
                String nomeUsuario = scanner.nextLine();
                
                System.out.println("Insira a senha:");
                String senha = scanner.nextLine();
                
                plataforma.login(nomeUsuario, senha);
            	
                if (nomeUsuario == null){
                 System.out.println("Erro no login: usario nao encontrado");
                  System.out.println();
                 }
                
                else {
                    System.out.println("Erro no login: senha incorreta");
                }
                System.out.println();
                break;
            case 5:
            	// Carregando lista de filmes
 
            	catalogo.carregarFilmes();
            	System.out.println("Lista de filmes:");
            	for (Filme filme1 : catalogo.getFilmes()) {
            		System.out.println(filme1.getId() + ", " + filme1.getNome() + ", " + filme1.getDataLancamento() + ", "+ filme1.getDuracao());                 
        }  	
            	break;
            	
            case 6:
            	// Carregar lista de séries 
            	
            	
            	catalogo.carregarSeries();
            	System.out.println("Lista de séries:");
            	for (Serie serie1 : catalogo.getSerie()) {
        		System.out.println(serie1.getId() + ", " + serie1.getNome() + ", " + serie1.getDataLancamento());
            	}
            	break;
            	
            case 7:
            	//Carregando lista de audiência
            
            	System.out.println("Lista de audiência:");
                plataforma.carregarAudiencia("audiencia.csv");
                
                break;
            case 8:
            	//Carregando lista de espectadores
            	
            	System.out.println("Lista de espectadores");
                plataforma.carregarEspectadores("espectador.csv");
                
                break;
            case 0:
                System.out.println("Saindo...");
                scanner.close();
                return;
	}
		
            }
    }

}