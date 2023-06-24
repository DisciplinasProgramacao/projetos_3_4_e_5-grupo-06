package business.composition;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
	protected LocalDate dataAssistida;
	private String nomeDeUsuario;
	private String senha;
	private List<Serie> listaParaVer;
	private List<Serie> listaJaVistas;
	private int contadorMidiasAssistidas;

	/**
	 * 
	 * Construtor da classe Cliente.
	 * 
	 * @param nomeDeUsuario o nome de usuário do cliente.
	 * @param senha         a senha do cliente.
	 */
	public Cliente(String nomeDeUsuario, String senha) {
		this.nomeDeUsuario = nomeDeUsuario;
		this.senha = senha;
		this.listaParaVer = new ArrayList<>();
		this.listaJaVistas = new ArrayList<>();
		this.contadorMidiasAssistidas = 0;
	}

	/**
	 * 
	 * Método que adiciona uma série na lista "para ver".
	 * 
	 * @param serie a série a ser adicionada.
	 */
	public void adicionarNaLista(Serie serie) {
		listaParaVer.add(serie);
	}

	/**
	 * 
	 * Método que retira uma série da lista "para ver".
	 * 
	 * @param nomeSerie o nome da série a ser retirada.
	 */
	public void retirarDaLista(String nomeSerie) {
		for (Serie serie : listaParaVer) {
			if (serie.getNome().equals(nomeSerie)) {
				listaParaVer.remove(serie);
				break;
			}
		}
	}

	/**
	 * 
	 * Método que filtra séries por gênero.
	 * 
	 * @param genero o gênero a ser filtrado.
	 * @return a lista de séries que correspondem ao gênero filtrado.
	 */
	public List<Serie> filtrarporGenero(String genero) {
		List<Serie> resultado = new ArrayList<>();
		for (Serie serie : listaJaVistas) {
			if (serie.getGenero().equals(genero)) {
				resultado.add(serie);
			}
		}
		return resultado;
	}

	/**
	 * 
	 * Método que filtra séries por idioma.
	 * 
	 * @param idioma o idioma a ser filtrado.
	 * @return a lista de séries que correspondem ao idioma filtrado.
	 */
	public List<Serie> filtrarPorIdioma(String idioma) {
		List<Serie> resultado = new ArrayList<>();
		for (Serie serie : listaJaVistas) {
			if (serie.getIdioma().equals(idioma)) {
				resultado.add(serie);
			}
		}
		return resultado;
	}

	/**
	 * 
	 * Método que filtra séries por quantidade de episódios.
	 * 
	 * @param quantEpisodios a quantidade de episódios a ser filtrada.
	 * @return a lista de séries que correspondem à quantidade de episódios
	 *         filtrada.
	 */
	public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
		List<Serie> resultado = new ArrayList<>();
		for (Serie serie : listaJaVistas) {
			if (serie.getQuantidadeEpisodios() == quantEpisodios) {
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

	/**
	 * 
	 * Registra a audiência de uma série específica.
	 * 
	 * @param serie a série a ter sua audiência registrada
	 *              A função retira a série da lista de séries não vistas e adiciona
	 *              na lista de séries já vistas.
	 *              Em seguida, a função chama o método 'registrarAudiencia' da
	 *              série, responsável por incrementar o número
	 *              de visualizações dessa série.
	 */
	public void registrarAudiencia(Serie serie) {
        retirarDaLista(serie.getNome());
        getListaJaVistas().add(serie);
        serie.registrarAudiencia();

        // Adicionar a data atual à série assistida
        LocalDate dataAtual = LocalDate.now();
		setDataAssistida(dataAtual);
    }

	/**
	 * 
	 * Representa um objeto Usuário com nome de usuário e senha.
	 * /
	 * public class Usuario {
	 * /*
	 * Retorna uma representação em String do objeto Usuário.
	 * 
	 * @return uma String contendo o nome de usuário e a senha do usuário, no
	 *         seguinte formato:
	 *         "Nome de usuário='[nomeDeUsuario]'; Senha='[senha]'".
	 */
	public String toString() {
		return " Nome de usuario='" + getNomeDeUsuario() + "'" +
				"; Senha='" + getSenha() + "'";
	
	}
	public void incrementarContadorMidiasAssistidas() {
        contadorMidiasAssistidas++;
    }

    public int getContadorMidiasAssistidas() {
        return contadorMidiasAssistidas;
    }

		
	public LocalDate getDataAssistida() {
        return dataAssistida;
    }

    public void setDataAssistida(LocalDate dataAssistida) {
        this.dataAssistida = dataAssistida;
    }
}

	
