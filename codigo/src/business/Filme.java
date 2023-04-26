package business;

public class Filme {


    public int idFilme;
    private String nome;
    private String dataLancamento;
    private int duracao;
    
    // construtor
    public Filme(int idFilme, String nome, String dataLancamento, int duracao) {
        this.idFilme = idFilme;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
    }

    //getters and setters
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
}
