package app;

import business.*;

public class Aplicacao {

	public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        catalogo.carregarFilmes();
        System.out.println("Lista de filmes:");
        for (Filme filme : catalogo.getFilmes()) {
            System.out.println(filme.getNome());
        }
    }

}
