import java.util.ArrayList;

public class ClasseLista {
    private ArrayList<classeSerie> series;

    // Construtor
    public ClasseLista() {
        series = new ArrayList<classeSerie>();
    }

    // Método para adicionar uma série à lista
    public void adicionarElemento(classeSerie serie) {
        series.add(serie);
    }

    // Método para remover uma série da lista
    public void removerElemento(classeSerie serie) {
        series.remove(serie);
    }

    // Método para buscar uma série na lista pelo nome
    public classeSerie buscarElemento(String nome) {
        for (classeSerie serie : series) {
            if (serie.getNome().equals(nome)) {
                return serie;
            }
        }
        return null;
    }

	public boolean buscarGenero(String string) {
		
		return false;
	}
}