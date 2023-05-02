package business;

public class Serie {
	
	//Declaração de variáveis
	String nome, genero, idioma, data, dataLancamento;
	int quantidadeEpisodios, audiencia, id;
	
	
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
	
	//Getters e Setters

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public double getId() {
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
	
	//Registro de audiência
	public void registrarAudiencia() {
		audiencia++;
	}	
}
