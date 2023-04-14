package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlataformaStreaming {
    private String nome;
    private HashMap<String, Serie> series;
    private HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;

    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.series = new HashMap<String, Serie>();
        this.clientes = new HashMap<String, Cliente>();
        this.clienteAtual = null;
    }

    public Cliente login(String nomeUsuario, String senha) {
        Cliente cliente = clientes.get(nomeUsuario);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            this.clienteAtual = cliente;
            return cliente;
        }
        return null;
    }

    public void adicionarSerie(Serie serie) {
        series.put(serie.getNome(), serie);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.put(cliente.getNomeDeUsuario(), cliente);
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public Cliente setClienteAtual(){
        return this.clienteAtual = clienteAtual;
    }

}