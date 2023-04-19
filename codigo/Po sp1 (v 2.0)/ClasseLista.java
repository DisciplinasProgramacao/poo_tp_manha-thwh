import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe que representa uma lista de séries.
 */
public class ClasseLista {
    private ArrayList<classeSerie> series; // Lista de séries

    /**
     * Construtor da ClasseLista.
     * Cria uma nova instância da ClasseLista e inicializa a lista de séries.
     */
    public ClasseLista() {
        series = new ArrayList<classeSerie>();
    }

    /**
     * Método para adicionar uma série à lista.
     * @param serie Objeto classeSerie a ser adicionado à lista.
     */
    public void adicionarElemento(classeSerie serie) {
        series.add(serie);
    }

    /**
     * Método para remover uma série da lista.
     * @param serie Objeto classeSerie a ser removido da lista.
     */
    public void removerElemento(classeSerie serie) {
        series.remove(serie);
    }

    /**
     * Método para buscar uma série na lista pelo nome.
     * @param nome Nome da série a ser buscada.
     * @return Objeto classeSerie encontrado na lista, ou null se não encontrado.
     */
    public classeSerie buscarElemento(String nome) {
        for (classeSerie serie : series) {
            if (serie.getNome().equals(nome)) {
                return serie;
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
        // Implementação do método para buscar séries pelo gênero
        // Retorne true se houver pelo menos uma série do gênero na lista, false caso contrário
        return false;
    }
}