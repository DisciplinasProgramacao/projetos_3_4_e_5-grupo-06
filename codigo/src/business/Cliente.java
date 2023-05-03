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
	
	    //método para filtrar series por genero
    public List<Serie> filtrarporGenero(String genero) {
    	List<Serie> resultado = new ArrayList<>();
    	for(Serie serie : listaJaVistas) {
    		if(serie.getGenero().equals(genero)) {
    			resultado.add(serie);
    		}
    	}
    	return resultado;
    	}
    
    //método para filtrar series por idioma
    public List<Serie> filtrarPorIdioma(String idioma) {
    	List<Serie> resultado = new ArrayList<>();
    	for(Serie serie : listaJaVistas) {
    		if(serie.getIdioma().equals(idioma)) {
    			resultado.add(serie);
    		}
    	}
    	return resultado;
    }
    
    //método para filtrar series por quantidade de episódios
    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
    	List<Serie> resultado = new ArrayList<>();
    	for(Serie serie: listaJaVistas) {
    		if(serie.getQuantidadeEpisodios()==quantEpisodios) {
    			resultado.add(serie);
    		}
    	}
    	return resultado;
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
	
	//Registra a audiencia de uma série específica
	 public void registrarAudiencia(Serie serie) {
		  retirarDaLista(serie.getNome());
		  getListaJaVistas().add(serie);
		  serie.registrarAudiencia();
	  }
	 
	 @Override
	    public String toString() {
	        return 
	                " Nome de usuario='" + getNomeDeUsuario() + "'" +
	                "; Senha='" + getSenha() + "'"
	                ;
	    }
}
