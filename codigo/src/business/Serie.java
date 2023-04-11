package business;

public class Serie {
	
	//Declaração de variáveis
	String[] GENEROS;
	String nome, genero, idioma;
	int quantidadeEpisodios, audiencia;
	
	//Getters e Setters
	public String[] getGENEROS() {
		return GENEROS;
	}
	public void setGENEROS(String[] gENEROS) {
		GENEROS = gENEROS;
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
	public void registrarAudiencia() {
		audiencia++;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	public void getIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	//Construtores
	
	public Serie(){
		setAudiencia(0);
		setGenero(null);
		setGENEROS(null);
		setNome(null);
		setQuantidadeEpisodios(0);
	}
	public Serie(String[] GENEROS, String nome, String genero, int quantidadeEpisodios, int audiencia){
		setAudiencia(audiencia);
		setGenero(genero);
		setGENEROS(GENEROS);
		setNome(nome);
		setQuantidadeEpisodios(quantidadeEpisodios);
	}
	
}
