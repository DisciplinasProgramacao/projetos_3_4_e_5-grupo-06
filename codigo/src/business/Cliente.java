package business;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private List<Serie> listaParaVer;
    private List<Serie> listaJaVistas;
    
    public Cliente(String nomeDeUsuario, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new ArrayList<>();
    }
    public void adicionarNaLista(Serie serie) {
        listaParaVer.add(serie);
    }
    
    public void retirarDaLista(String nomeSerie) {
        for (Serie serie : listaParaVer) {
            if (serie.getNome().equals(nomeSerie)) {
                listaParaVer.remove(serie);
                break;
            }
        }
    }
    
    // get / set

    public String getNomeDeUsuario() {
		  return nomeDeUsuario;
  	}
	  public void setNomeDeUsuario(String nomeDeUsuario) {
		  this.nomeDeUsuario = nomeDeUsuario;
	  }
  	public String getSenha() {
		  return senha;
	  }
	  public void setSenha(String senha) {
		  this.senha = senha;
	  }
  	public List<Serie> getListaParaVer() {
	  	return listaParaVer;
	  }
	  public void setListaParaVer(List<Serie> listaParaVer) {
	  	this.listaParaVer = listaParaVer;
	  }
	  public List<Serie> getListaJaVistas() {
		  return listaJaVistas;
	  }
	  public void setListaJaVistas(List<Serie> listaJaVistas) {
		  this.listaJaVistas = listaJaVistas;
	  }
