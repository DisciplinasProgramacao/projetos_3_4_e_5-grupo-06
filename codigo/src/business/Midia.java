package business;

import java.util.List;

public class Midia {
    

    /**
     * Verifica se um cliente é regular ou especialista com base no número de mídias assistidas.
     * 
     * @param cliente o cliente a ser verificado.
     * @return true se o cliente é especialista, false se o cliente é regular.
     */
    public boolean isEspecialista(Cliente cliente) {
        int quantidadeMinima = 5; //quantidade mínima de mídias para um cliente ser considerado especialista
        
        List<Serie> listaJaVistas = cliente.getListaJaVistas();
        return listaJaVistas.size() >= quantidadeMinima;

        //falta definir tempo minimo de um mes

    }
}
