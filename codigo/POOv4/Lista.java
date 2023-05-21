import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe que representa uma lista de s�ries.
 */
public class Lista {
    private ArrayList<Midia> midias; // Lista de s�ries

    /**
     * Construtor da ClasseLista.
     * Cria uma nova inst�ncia da ClasseLista e inicializa a lista de s�ries.
     */
    public Lista() {
    	midias = new ArrayList<Midia>();
    }

    /**
     * M�todo para adicionar uma s�rie � lista.
     * @param serie Objeto classeSerie a ser adicionado � lista.
     */
    public void adicionarElemento(Midia midia) {
    	midias.add(midia);
    }

    /**
     * M�todo para remover uma s�rie da lista.
     * @param serie Objeto classeSerie a ser removido da lista.
     */
    public void removerElemento(Midia midia) {
    	midias.remove(midia);
    }

    /**
     * M�todo para buscar uma s�rie na lista pelo nome.
     * @param nome Nome da s�rie a ser buscada.
     * @return Objeto classeSerie encontrado na lista, ou null se n�o encontrado.
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
     * M�todo para buscar s�ries pelo g�nero.
     * @param genero G�nero das s�ries a serem buscadas.
     * @return true se h� pelo menos uma s�rie do g�nero na lista, false caso contr�rio.
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