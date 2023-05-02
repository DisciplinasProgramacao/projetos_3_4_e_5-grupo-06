package business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PlataformaStreaming {
    private String nome;
    private HashMap<String, Serie> series;
    private HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;

    //construtor
    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.series = new HashMap<String, Serie>();
        this.clientes = new HashMap<String, Cliente>();
        this.clienteAtual = null;
    }

    // método login que retorna um objeto tipo Cliente caso o login seja bem sucedido, retorna null caso contrário
    public Cliente login(String nomeUsuario, String senha) {
        Cliente cliente = clientes.get(nomeUsuario);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            this.clienteAtual = cliente;
            return cliente;
        }
        return null;
    }
	
    public  HashMap<String, Cliente> getTodosClientes() {
    	return this.clientes;
    }
    
    public  HashMap<String, Serie> getTodasSeries() {
    	return this.series;
    }

    //método para adicionarserie
    public void adicionarSerie(Serie serie) {
        series.put(serie.getNome(), serie);
    }

    //método para adicionarcliente
    public void adicionarCliente(Cliente cliente) {
        clientes.put(cliente.getNomeDeUsuario(), cliente);
    }

    //metodo que retorna objeto cliente
    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    //atualiza valor do ClienteAtual
    public Cliente setClienteAtual(){
        return this.clienteAtual = clienteAtual;
    }

    //método para filtrar series por genero
    public List<Serie> filtrarPorGenero(String genero) {
        List<Serie> resultado = new ArrayList<>();
        for (Serie serie : series.values()) {
            if (serie.getGenero().equals(genero)) {
                resultado.add(serie);
            }
        }
        return resultado;
    }

    //metodo para filtrar series por idioma
    public List<Serie> filtrarPorIdioma(String idioma) {
        List<Serie> resultado = new ArrayList<>();
        for (Serie serie : series.values()) {
            if (serie.getIdioma().equals(idioma)) {
                resultado.add(serie);
            }
        }
        return resultado;
    }

    //metodo para filtrar series por quantidade de episodios
    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Serie> resultado = new ArrayList<>();
        for (Serie serie : series.values()) {
            if (serie.getQuantidadeEpisodios() == quantEpisodios) {
                resultado.add(serie);
            }
        }
        return resultado;
    }

    //chama metodo da classe serie para registrar audiencia
    public void registrarAudiencia(Serie serie) {
        serie.registrarAudiencia();
    }
    
    public void salvarEspectadores(String nomeArquivo, HashMap<String, Cliente> clientes, PlataformaStreaming a) {
    	
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
			 for (Cliente c : clientes.values()) {
 	    writer.write(a.login(c.getNomeDeUsuario(), c.getSenha()) + ";" + c.getNomeDeUsuario() + ";" + c.getSenha() + "\n");
			 }
        System.out.println("Os espectadores foram salvos no arquivo CSV com sucesso!");
    } catch (IOException e) {
        System.out.println("Ocorreu um erro ao salvar os espectadores no arquivo CSV.");
        e.printStackTrace();
    }
	}
    
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
    
 public void salvarAudiencia(String nomeArquivo, PlataformaStreaming a, HashMap<String, Cliente> clientes, HashMap<String, Serie> series) {
    	
  		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
  			 for (Cliente c : clientes.values()) {
  				 for(Serie s : series.values()) {
   	    writer.write(a.login(c.getNomeDeUsuario(), c.getSenha()) + ";"  +c.getListaParaVer()+ "/" + c.getListaJaVistas() + ";" + s.getId() + "\n");
  			 }
  			 }
          System.out.println("A audiencia foi salva no arquivo CSV com sucesso!");
          
      } catch (IOException e) {
          System.out.println("Ocorreu um erro ao salvar a audiencia no arquivo CSV.");
          e.printStackTrace();
      }
  	}
 
 public void carregarEspectadores(String uri) {
		try{
			Scanner sc = new Scanner(new File(uri));
			sc.useDelimiter(";|\n");
			while (sc.hasNext()) {
				System.out.print(" | "+sc.next()); 
			}
			System.out.println();
			sc.close(); 
		} catch(IOException e) {
			System.out.println("Ocorreu um erro ao ler o arquivo de espectadores no arquivo CSV.");
	          e.printStackTrace();
		}
	}
	
	public void carregarSeries(String uri) {
		try{
			Scanner sc = new Scanner(new File(uri));
			sc.useDelimiter(";|\n");
			while (sc.hasNext()) {
				System.out.print(" | " + sc.next()); 
			}
			System.out.println();
			sc.close(); 
		} catch(IOException e) {
			System.out.println("Ocorreu um erro ao ler o arquivo de series no arquivo CSV.");
	          e.printStackTrace();
		}
	}
	
	public void carregarAudiencia(String uri) {
	    try {
	    	Scanner sc = new Scanner(new File(uri));
	        sc.useDelimiter(";|\n");
	        while (sc.hasNextLine()) {
	            System.out.print(" | " + sc.next());
	        }
	        System.out.println();
	        sc.close();
	    } catch (IOException e) {
	        System.out.println("Ocorreu um erro ao ler o arquivo de audiencia no arquivo CSV.");
	        e.printStackTrace();
	    }
	}

}
