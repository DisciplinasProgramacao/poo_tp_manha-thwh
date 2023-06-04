import java.util.HashMap;
import java.util.Map;

public class Lista {
    private Map<String, Midia> midias; // Mapa de mídia

    public Lista() {
        midias = new HashMap<>();
    }

    public void adicionarElemento(Midia midia) {
        midias.put(midia.getNome(), midia);
    }

    public void removerElemento(Midia midia) {
        midias.remove(midia.getNome());
    }

    public Midia buscarElemento(String nome) {
        return midias.get(nome);
    }

    public boolean buscarGenero(String genero) {
        for (Midia midia : midias.values()) {
            if (midia.getGenero().equals(genero)) {
                return true;
            }
        }
        return false;
    }

    public boolean buscarIdioma(String idioma) {
        for (Midia midia : midias.values()) {
            if (midia.getIdioma().equals(idioma)) {
                return true;
            }
        }
        return false;
    }
}
