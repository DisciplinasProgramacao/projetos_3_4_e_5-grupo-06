package business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Catalogo {
    private List<Filme> filmes;

    public Catalogo() {
        this.filmes = new ArrayList<>();
    }

    public void carregarFilmes() {
        String caminhoArquivo = "C:\\Users\\dtiDIgital\\Downloads\\projetos_3_4_e_5-grupo-06-master (2)\\projetos_3_4_e_5-grupo-06\\codigo\\filmes.csv";
        Scanner sc;
        try {
            sc = new Scanner(new File(caminhoArquivo));
            while(sc.hasNext()) {
                String a = sc.nextLine();
                String elementos[] = a.split(";");
                int idFilme = Integer.parseInt(elementos[0]);
                String nome = elementos[1];
                String dataLancamento = elementos[2];
                int duracao = Integer.parseInt(elementos[3]);
                Filme filme = new Filme(idFilme, nome, dataLancamento, duracao);
                this.filmes.add(filme); // add the film object to the list
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
}
