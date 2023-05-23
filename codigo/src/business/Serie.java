package business;

public class Serie extends Midia{
	
	//Construtor
	
	public Serie(String nome, String genero, String idioma, int quantidadeEpisodios, int audiencia){
		setNome(nome);
		setGenero(genero);
		setIdioma(idioma);
		setQuantidadeEpisodios(quantidadeEpisodios);
		setAudiencia(audiencia);
	}

	public Serie(int id, String nome, String dataLancamento){
		setId(id);
		setNome(nome);
		setDataLancamento(dataLancamento);
	}
	
	
	/**
     * 
     * Método que permite ao cliente avaliar a serie com uma nota entre 1 e 5.
     * 
     * @param avaliacao a nota atribuída pelo cliente (entre 1 e 5)
     */
	@Override
    public void avaliar(int avaliacao) {
        if (avaliacao >= 1 && avaliacao <= 5) {
            this.avaliacao = avaliacao;
            System.out.println("Serie avaliada com sucesso!");
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
