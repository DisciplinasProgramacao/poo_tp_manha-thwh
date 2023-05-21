import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe que representa uma lista de séries.
 */
public class Lista {
    private ArrayList<Midia> midias; // Lista de séries

    /**
     * Construtor da ClasseLista.
     * Cria uma nova instância da ClasseLista e inicializa a lista de séries.
     */
    public Lista() {
    	midias = new ArrayList<Midia>();
    }

    /**
     * Método para adicionar uma série à lista.
     * @param serie Objeto classeSerie a ser adicionado à lista.
     */
    public void adicionarElemento(Midia midia) {
    	midias.add(midia);
    }

    /**
     * Método para remover uma série da lista.
     * @param serie Objeto classeSerie a ser removido da lista.
     */
    public void removerElemento(Midia midia) {
    	midias.remove(midia);
    }

    /**
     * Método para buscar uma série na lista pelo nome.
     * @param nome Nome da série a ser buscada.
     * @return Objeto classeSerie encontrado na lista, ou null se não encontrado.
     */
    public Midia buscarElemento(String nome) {
        for (Midia midia : midias) {
            if (midia.getNome().equals(nome)) {
                return midia;
            }
        }
        return null;
    }

    /**
     * Método para buscar séries pelo gênero.
     * @param genero Gênero das séries a serem buscadas.
     * @return true se há pelo menos uma série do gênero na lista, false caso contrário.
     */
    public boolean buscarGenero(String genero) {
        for (Midia midia : midias) {
            if (midia.getGenero().equals(genero)) {
                return true;
            }
        }
        return false;
    }
}