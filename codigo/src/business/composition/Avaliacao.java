package business.composition;

public class Avaliacao {
	
	int nota;
	String comentario;
	
	public Avaliacao(int nota, String comentario) {
		this.nota = nota;
		this.comentario = comentario;
	}
	
	public String getComentario() {
		return this.comentario;
	}
	
	public int getNota() {
		return this.nota;
	}
	
}
