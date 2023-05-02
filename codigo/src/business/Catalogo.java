package business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Catalogo {
    private List<Filme> filmes;
    private List<Serie> series;

    public Catalogo() {
        this.filmes = new ArrayList<>();
    }
    
    //Carregamento de filmes a partir de um arquivo
    public void carregarFilmes() {
        String caminhoArquivo = "C:\\Users\\dtiDIgital\\Downloads\\projetos_3_4_e_5-grupo-06-master (2)\\projetos_3_4_e_5-grupo-06\\codigo\\filmes.csv";
        Scanner sc;
        try {
            sc = new Scanner(new File(caminhoArquivo));
            while(sc.hasNext()) {
                String a = sc.nextLine();
                String elementos[] = a.split(";");
                int id = Integer.parseInt(elementos[0]);
                String nome = elementos[1];
                String dataLancamento = elementos[2];
                int duracao = Integer.parseInt(elementos[3]);
                Filme filme = new Filme(id, nome, dataLancamento, duracao);
                this.filmes.add(filme); // adicionar filme a lista
            }      
            sc.close();
            System.out.println("Arquivo carregado com sucesso.");
        } catch (InputMismatchException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarSeries() {
        String caminhoArquivo = "C:\\Users\\dtiDIgital\\Downloads\\projetos_3_4_e_5-grupo-06-master (2)\\projetos_3_4_e_5-grupo-06\\codigo\\series.csv";
        Scanner sc;
        try {
            sc = new Scanner(new File(caminhoArquivo));
            while(sc.hasNext()) {
                String a = sc.nextLine();
                String elementos[] = a.split(";");
                int idSerie = Integer.parseInt(elementos[0]);
                String nome = elementos[1];
                String dataLancamento = elementos[2];
                Serie serie = new Serie(idSerie, nome, dataLancamento);
                this.series.add(serie); // adicionar serie a lista
            }      
            sc.close();
            System.out.println("Arquivo carregado com sucesso.");
        } catch (InputMismatchException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // getters and setters

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public List<Serie> getSerie() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

}
