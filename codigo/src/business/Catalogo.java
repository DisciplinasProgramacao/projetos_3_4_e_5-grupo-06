package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
        private List<Filme> filmes;

        public Catalogo() {
            this.filmes = new ArrayList<>();
        }
        
        public void carregarFilmes() {
            String caminhoArquivo = "C:\\Users\\dtiDIgital\\Downloads\\projetos_3_4_e_5-grupo-06-master (2)\\projetos_3_4_e_5-grupo-06\\codigo\\POO_Filmes.csv";
            StringBuilder conteudoArquivo = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(";");
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String dataLancamento = dados[2];
                    int duracao = Integer.parseInt(dados[3]);
                    Filme filme = new Filme(id, nome, dataLancamento, duracao);
                    filmes.add(filme);
                    conteudoArquivo.append(linha).append("\n");
                }
                br.close();
                System.out.println("Arquivo carregado com sucesso:");
                System.out.println(conteudoArquivo.toString());
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

