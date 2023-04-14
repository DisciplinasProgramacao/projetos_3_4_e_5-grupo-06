package business;

public class Serie {
	
	//Declaração de variáveis
	String nome, genero, idioma;
	int quantidadeEpisodios, audiencia;
	
	//Getters e Setters
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
	
	//Registro de audiência
	public void registrarAudiencia() {
		audiencia++;
	}
	
	
	//Construtores
	
	public Serie(){
		setAudiencia(0);
		setGenero(null);
		setNome(null);
		setQuantidadeEpisodios(0);
	}
	public Serie(String nome, String genero, String idioma, int quantidadeEpisodios, int audiencia){
		setNome(nome);
		setGenero(genero);
		setIdioma(idioma);
		setQuantidadeEpisodios(quantidadeEpisodios);
		setAudiencia(audiencia);
	}
	
}
