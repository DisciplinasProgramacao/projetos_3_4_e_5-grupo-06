package business;

import java.util.List;

public abstract class Midia {
    
	// Declaracao de variaveis
	String nome, genero, idioma, data, dataLancamento;
	private int quantidadeEpisodios, audiencia;
	protected int id;
	protected int duracao;
	protected int avaliacao;
	
	
	
	//Getters e Setters

		public String getDataLancamento() {
			return dataLancamento;
		}

		public void setDataLancamento(String dataLancamento) {
			this.dataLancamento = dataLancamento;
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getGenero() {
			return genero;
		}
		public void setGenero(String genero) {
			this.genero = genero;
		}
		public int getQuantidadeEpisodios() {
			return quantidadeEpisodios;
		}
		public void setQuantidadeEpisodios(int quantidadeEpisodios) {
			this.quantidadeEpisodios = quantidadeEpisodios;
		}
		public int getAudiencia() {
			return audiencia;
		}
		public void setAudiencia(int audiencia) {
			this.audiencia = audiencia;
		}
		public void setIdioma(String idioma) {
			this.idioma = idioma;
		}
		
		public String getIdioma() {
			return idioma;
		}
		
		public int getDuracao() {
	        return duracao;
	    }

	    public void setDuracao(int duracao) {
	        this.duracao = duracao;
	    }
	    
	  //Registro de audiência
		public void registrarAudiencia() {
			audiencia++;
		}	

    /**
     * Verifica se um cliente é regular ou especialista com base no número de mídias assistidas.
     * 
     * @param cliente o cliente a ser verificado.
     * @return true se o cliente é especialista, false se o cliente é regular.
     */
    public boolean isEspecialista(Cliente cliente) {
        int quantidadeMinima = 5; //quantidade mínima de mídias para um cliente ser considerado especialista
        
        List<Serie> listaJaVistas = cliente.getListaJaVistas();
        return listaJaVistas.size() >= quantidadeMinima;

        //falta definir tempo minimo de um mes

    }
    
    /**
     * 
     * Método que permite ao cliente avaliar a midia com uma nota entre 1 e 5.
     * 
     * @param avaliacao a nota atribuída pelo cliente (entre 1 e 5)
     * @return 
     */
    public void avaliar(int avaliacao) {
    	
    }
}
