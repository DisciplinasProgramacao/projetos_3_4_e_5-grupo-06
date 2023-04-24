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
            String caminhoArquivo = "C:\\Users\\dtiDIgital\\Downloads\\projetos_3_4_e_5-grupo-06-master (2)\\projetos_3_4_e_5-grupo-06\\codigo\\POO_Filmes.csv";
            try {
                Scanner sc = new Scanner(new File(caminhoArquivo));
                sc.useDelimiter(";");
                while (sc.hasNext()) {
                    try {
                        int idFilme = sc.nextInt();
                        String nome = sc.next();
                        String dataLancamento = sc.next();
                        int duracao = sc.nextInt();
                        Filme filme = new Filme(idFilme, nome, dataLancamento, duracao);
                        filmes.add(filme);
                    } catch (InputMismatchException e) {
                        System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                        System.out.println("Token inválido: " + sc.next());
                    }
                }
                sc.close();
                System.out.println("Arquivo carregado com sucesso.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        //getters and setters

        public List<Filme> getFilmes() {
            return filmes;
        }

        public void setFilmes(List<Filme> filmes) {
            this.filmes = filmes;
        }
    }   

