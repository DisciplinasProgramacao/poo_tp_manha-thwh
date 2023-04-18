import java.util.ArrayList;

public class ClasseLista {
    private ArrayList<classeSerie> series;

    // Construtor
    public ClasseLista() {
        series = new ArrayList<classeSerie>();
    }

    // M�todo para adicionar uma s�rie � lista
    public void adicionarElemento(classeSerie serie) {
        series.add(serie);
    }

    // M�todo para remover uma s�rie da lista
    public void removerElemento(classeSerie serie) {
        series.remove(serie);
    }

    // M�todo para buscar uma s�rie na lista pelo nome
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