package business.composition;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import business.exceptions.ClienteReviewExcessException;
import business.exceptions.ReviewScoreException;

public abstract class Midia {

	String id, nome, genero, idioma;
	Date dataLancamento;
	int audiencia;
	boolean lancamento;

	Map<Cliente, Avaliacao> avaliacoes;

	public Midia(String nome, String genero, String idioma, Date dataLancamento, boolean lancamento) {
		this.nome = nome;
		this.genero = genero;
		this.idioma = idioma;
		this.dataLancamento = dataLancamento;
		this.lancamento = lancamento;
		this.avaliacoes = new HashMap<>();

		this.audiencia = 0;
	}

	public int getAudiencia() {
		return this.audiencia;
	}

	public String getNome() {
		return this.nome;
	}

	public String getGenero() {
		return this.genero;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public Date getDataDeLancamento() {
		return this.dataLancamento;
	}

	public void aumentaAudiencia() {
		this.audiencia++;
	}

	public boolean isLancamento() {
		return this.lancamento;
	}

	public Map<Cliente, Avaliacao> getAvaliacoes() {
		return this.avaliacoes;
	}

	/**
	 * Adiciona uma nova avaliação ao mapa de avaliações.
	 *
	 * @param c como chave do mapa para a avaliação
	 * @param nota como parametro para criação da avaliação
	 * @param comentario como parametro para criação da avaliação
	 * @throws ClienteReviewExcessException caso o cliente ja tenha adicionado uma avaliação a midia anteriormente
	 * @throws ReviewScoreException caso a nota seja inferior a 1 ou maior que 5
	 */
	public void addAvaliacao(Cliente c, int nota, String comentario) throws ClienteReviewExcessException, ReviewScoreException {
		Avaliacao a = new Avaliacao(nota, comentario);

		if (avaliacoes.containsKey(c)) {
			throw new ClienteReviewExcessException(c);
		}

		if (nota < 0 || nota > 5) {
			throw new ReviewScoreException();
		}

		avaliacoes.put(c, a);
	}

	/**
	 * Executa uma média aritmética simples para descobrir a média das avalições de uma midia utilizando
	 * o valor atribuido as notas.
	 *
	 * @return a media de avaliações da midia
	 */
	public double getMediaAvaliacoes() {
		return this.getAvaliacoes().size() > 0 ? this.getAvaliacoes().values().stream().mapToInt(Avaliacao::getNota).sum() / this.getAvaliacoes().values().size() : 0;
	}

	public abstract void print();

}

