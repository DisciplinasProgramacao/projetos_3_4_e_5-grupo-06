package app;

import business.*;

public class Aplicacao {

	public static void main(String[] args) {
        //Carregando lista de filmes
        Catalogo catalogo = new Catalogo();
        catalogo.carregarFilmes();
        System.out.println("Lista de filmes:");
        for (Filme filme : catalogo.getFilmes()) {
            System.out.println(filme.getId() + ", " + filme.getNome() + ", " + filme.getDataLancamento() + ", " + filme.getDuracao());
        }
    }

    //Carregar lista de séries
    //PlataformaStreaming.carregarSeries("caminhoarquivo");
    //System.out.println("Lista de séries:");
        //for (Serie serie : catalogo.getSeries()) {
           // System.out.println(serie.getId() + ", " + serie.getNome() + ", " + serie.getDataLancamento() + ", " + serie.getQuantidadeEpisodios());
        //}

        //Carregando lista de audiência
        //catalogo.carregarAudiencia("caminhoarquivo");
        //System.out.println("Lista de audiência:");
        //for (Audiencia audiencia : catalogo.getAudiencia()) {
            //System.out.println(audiencia.getId() + ", " + audiencia.getData() + ", " + audiencia.getQuantidadeEspectadores());
        // }
}

