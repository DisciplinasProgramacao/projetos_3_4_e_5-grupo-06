package business;

public class Filme extends Midia{

 

    /**
     * 
     * Construtor da classe Filme.
     * 
     * @param idFilme        o identificador do filme
     * @param nome           o nome do filme
     * @param dataLancamento a data de lançamento do filme
     * @param duracao        a duração do filme em minutos
     */
    public Filme(int id, String nome, String dataLancamento, int duracao) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
    }

    /**
     * 
     * Método que permite ao cliente avaliar o filme com uma nota entre 1 e 5.
     * 
     * @param avaliacao a nota atribuída pelo cliente (entre 1 e 5)
     */
    @Override
    public void avaliar(int avaliacao) {
        if (avaliacao >= 1 && avaliacao <= 5) {
            this.avaliacao = avaliacao;
            System.out.println("Filme avaliado com sucesso!");
        }else {
        	System.out.println("Avaliacao invalida. Tente avaliar com numeros de 1 a 5!");
        }
    }
    
 // Metodo que pega os dados de uma entidade e transforma para o formato String
 	 @Override
 	    public String toString() {
 	        return 
 	                " nome='" + getNome() + "'" +
 	                "; genero='" + getGenero() + "'" +
 	                "; idioma='" + getIdioma() + "'" +
 	                "; quantidade de episodios='" + getQuantidadeEpisodios() + "'" +
 	                "; audiencia='" + getAudiencia();
 	               
 	    }

   
}
