package business.subobjects;

import java.text.SimpleDateFormat;
import java.util.Date;

import business.composition.Midia;

public class Filme extends Midia {
	
	int minutosDeDuracao;

	public Filme(String nome, String genero, String idioma, Date dataLancamento, boolean lancamento, int minutosDeDuracao) {
		super(nome, genero, idioma, dataLancamento, lancamento);
		this.minutosDeDuracao = minutosDeDuracao;
	}
	
	public int getMinutosDeDuracao() {
		return this.minutosDeDuracao;
	}

	@Override
	public void print() {
		StringBuilder sb = new StringBuilder();
		
		sb
		.append("-----------\n")
		.append("Nome: " + this.getNome() + "\n")
		.append("Genero: " + this.getGenero() + "\n")
		.append("Idioma: " + this.getIdioma() + "\n")
		.append("Data de Lançamento: " + new SimpleDateFormat("dd/MM/yyyy").format(this.getDataDeLancamento()) + "\n")
		.append("Nota media: " + this.getMediaAvaliacoes() + "\n")
		.append("Midia de Lançamento? " + (this.isLancamento() ? "Sim" : "Nao") + "\n")
		.append("Duração em Minutos: " + this.getMinutosDeDuracao() + "\n")
		.append("Audiencia: " + this.getAudiencia() + "\n")
		.append("\n");
		
		System.out.println(sb.toString());
	}
}
