package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
}