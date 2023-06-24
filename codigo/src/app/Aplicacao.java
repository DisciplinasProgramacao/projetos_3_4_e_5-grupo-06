package app;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import business.*;
import business.exceptions.ClienteUnauthorizedPermission;
import business.exceptions.MidiaNotFoundException;

public class Aplicacao {
	
	/**
	 * ATENÇÃO
	 * 
	 * Pode ser que, ao carregar o arquivo, dependendo de qual IDE você esteja utilizando
	 * O caminho do arquivo não será lido corretamente
	 * O programa foi desenvolvido no Eclipse, no entanto, ao tentar rodar no
	 * Visual Studio Code, foi necessario adicionar contrabarras nas strings
	 * de caminho de arquivo ao invés de barras comuns
	 * 
	 */

	public static void main(String[] args)throws IOException {

		PlataformaStreaming plataforma = new PlataformaStreaming("Goflix");        
		
//		plataforma.login("Nin154859", "NYor07361");
		
		while (plataforma.getClienteLogado() == null) {
			plataforma.logar();
		}
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Scanner scanner = new Scanner(System.in);
		
		boolean close = false;

		while (true) {
			System.out.println("Menu:");
			System.out.println();
			System.out.println("----- Gerenciar");
			System.out.println();
			System.out.println("1. Registrar série");
			System.out.println("2. Registrar filme"); 
			System.out.println("3. Registrar cliente");
			System.out.println();
			System.out.println("--- Cliente");
			System.out.println();
			System.out.println("4. Assistir midia");
			System.out.println("5. Avaliar midia");
			System.out.println("6. Adicionar midia na lista para assistir");
			System.out.println("7. Adicionar midia na lista de assistidos");
			System.out.println("8. Ver lista de midias para assistir");
			System.out.println("9. Ver lista de midias assistidas");
			System.out.println("10. Filtrar minha lista de midias para assistir");
			System.out.println();
			System.out.println("--- Plataforma");
			System.out.println();
			System.out.println("11. Fazer login com outro cliente");
			System.out.println("12. Fazer cliente logado virar profissional");
			System.out.println("13. Ver avaliações do cliente logado");
			System.out.println("14. Ver lista de todas as midias disponiveis do catalogo");
			System.out.println("15. Ver audiencia de uma midia");
			System.out.println("16. Encontrar midia pelo nome");
			System.out.println();
			System.out.println("----- Filtros");
			System.out.println();
			System.out.println("17. Qual cliente assistiu mais midias e quantas midias");
			System.out.println("18. Qual cliente tem mais avaliações e quantas avaliações");
			System.out.println("19. Qual a porcentagem de clientes com pelo menos 15 avaliações ");
			System.out.println("20. Quais são as 10 midias mais vistas");
			System.out.println("21. Quais são as 10 midias mais vistas por genero");
			System.out.println("22. Quais são as 10 midias com melhor avaliação (minimo 100 avaliações)");
			System.out.println("23. Quais são as 10 midias com melhor avaliação (minimo 100 avaliações) por genero");
			System.out.println();
			System.out.println("0. Sair");
			System.out.println();

			System.out.print("Escolha uma opção: ");
			
			String opcao = null;
			int op = -1;
			
			try {
			opcao = scanner.nextLine();
			op = Integer.valueOf(opcao);
			} catch (InputMismatchException | NumberFormatException e) {
				System.out.println("Opcao invalida");
			}
			
			switch (op) {
			case 1:
				
				plataforma.adicionarSerie();

				break;

			case 2:
				
				plataforma.adicionarFilme();

				break;

			case 3:

				plataforma.adicionarCliente();

				break;

			case 4:

				try {
					plataforma.assistirMidia();
				} catch (MidiaNotFoundException | ClienteUnauthorizedPermission e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 5:
				
				try {
					plataforma.avaliarMidia();
				} catch (MidiaNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;

			case 6:
				
				try {
					plataforma.adicionarMidiaParaAssistir();
				} catch (MidiaNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;

			case 7:
				
				try {
					plataforma.adicionarNaListaDeAssistidos();
				} catch (MidiaNotFoundException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 8:
				
				plataforma.verListaMidiasParaAssistir();

				break;
			case 9:
				
				plataforma.verListaMidiasAssistidas();
				
				break;
				
			case 10:
				
				plataforma.filtrarMidia();
				
				break;
				
			case 11:
				
				plataforma.logar();
				
				break;
				
			case 12 :
				
				plataforma.virarProfissional();
				
				break;
				
			case 13:
				
				plataforma.verAvaliacoesClienteLogado();
				
				break;
				
			case 14:
				
				plataforma.verCatalogoDeMidias();
				
				break;
				
			case 15:
				
				try {
					plataforma.verAudienciaDeUmaMidia();
				} catch (MidiaNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;
				
			case 16:
				
				try {
					plataforma.encontrarMidiaPeloNome();
				} catch (MidiaNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;
				
			case 17:
				
				plataforma.qualClienteAssistiuMaisMidias();
				
				break;
				
			case 18:
				
				plataforma.qualClienteTemMaisAvaliacoes();
				
				break;
				
			case 19:
				
				plataforma.qualPorcentagemDeClientesComPeloMenos15Avaliacoes();
				
				break;
				
			case 20:
				
				plataforma.quaisSaoAs10MidiasMaisVistas();
				
				break;
				
			case 21:
				
				plataforma.dezMidiasMaisVistasPorGenero();
				
				break;
				
			case 22:
				
				plataforma.dezMidiasComMelhorAvaliacao();
				
				break;
				
			case 23:
				
				plataforma.dezMidiasComMelhorAvaliacaoPorGenero();
				
				break;
				
			case 0:
				System.out.println("Saindo...");
				System.out.println();
				plataforma.salvar();
				close = true;
				break;
			
			default:
				System.out.println("Opcao invalida");
				break;
			}
			
			if (close)
				break;
			
			System.out.println("");
			System.out.println("Por favor pressione \"ENTER\" para prosseguir");
			System.out.println("");
			
			scanner.nextLine();
			
		}
		
		scanner.close();
	}

}
