package business;

public class Serie {
	String[] GENEROS;
	String nome, genero;
	int quantidadeEpisodios, audiencia;
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
	Serie(String[] GENEROS, String nome, String genero, int quantidadeEpisodios, int audiencia){
		setAudiencia(audiencia);
		setGenero(genero);
		setGENEROS(GENEROS);
		setNome(nome);
		setQuantidadeEpisodios(quantidadeEpisodios);
	}
}
