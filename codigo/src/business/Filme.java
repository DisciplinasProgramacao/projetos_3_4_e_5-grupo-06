package business;

public class Filme {

    public int idFilme;
    private String nome;
    private String dataLancamento;
    private int duracao;
    private int avaliacao = -1;

    /**
     * 
     * Construtor da classe Filme.
     * 
     * @param idFilme        o identificador do filme
     * @param nome           o nome do filme
     * @param dataLancamento a data de lançamento do filme
     * @param duracao        a duração do filme em minutos
     */
    public Filme(int idFilme, String nome, String dataLancamento, int duracao) {
        this.idFilme = idFilme;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.avaliacao = -1;
    }

    public Filme(int idFilme, String nome, String dataLancamento, int duracao, int avaliacao) {
        this.idFilme = idFilme;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.avaliacao = -1; //para filmes que não foram avaliados
    }

    /**
     * 
     * Método que permite ao cliente avaliar o filme com uma nota entre 1 e 5.
     * 
     * @param avaliacao a nota atribuída pelo cliente (entre 1 e 5)
     */
    public void avaliarFilme(int avaliacao) {
        if (avaliacao >= 1 && avaliacao <= 5) {
            this.avaliacao = avaliacao;
        }
    }

    // getters and setters
    public int getId() {
        return idFilme;
    }

    public void setId(int id) {
        this.idFilme = idFilme;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
}
