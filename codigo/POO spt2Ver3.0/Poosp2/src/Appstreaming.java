import java.util.HashMap;
import java.util.Map;

public class Appstreaming {
    private Map<String, Midia> F; // Mapa de séries para assistir no futuro
    private Map<String, Midia> A; // Mapa de séries já assistidas
    private Espectador usuarioLogado; // Objeto de login do usuário

    // Construtor
    public Appstreaming(Espectador login) {
        F = new HashMap<>();
        A = new HashMap<>();
        this.usuarioLogado = login;
    }

    // Método para adicionar uma série para assistir no futuro
    public void adicionarParaAssistir(Midia midia) {
        F.put(midia.getNome(), midia);
    }

    // Método para adicionar uma série à lista de séries já assistidas
    public void adicionarJaAssistida(String nomeMidia) {
        Midia midia = buscarSerie(nomeMidia);
        if (midia != null && F.containsKey(nomeMidia)) {
            F.remove(nomeMidia);
            A.put(nomeMidia, midia);
        } else {
            System.out.println("Série não encontrada na lista de séries para assistir.");
        }
    }

    // Método para buscar uma série pelo nome
    private Midia buscarSerie(String nomeMidia) {
        if (F.containsKey(nomeMidia)) {
            return F.get(nomeMidia);
        } else if (A.containsKey(nomeMidia)) {
            return A.get(nomeMidia);
        }
        return null;
    }

    // Método para buscar se uma série de determinado gênero está nas listas de séries
    public boolean buscarGenero(String genero) {
        for (Midia midia : F.values()) {
            if (midia.getGenero().equals(genero)) {
                return true;
            }
        }
        for (Midia midia : A.values()) {
            if (midia.getGenero().equals(genero)) {
                return true;
            }
        }
        return false;
    }

    // Método para buscar se uma série de determinado idioma está nas listas de séries
    public boolean buscarIdioma(String idioma) {
        for (Midia midia : F.values()) {
            if (midia.getIdioma().equals(idioma)) {
                return true;
            }
        }
        for (Midia midia : A.values()) {
            if (midia.getIdioma().equals(idioma)) {
                return true;
            }
        }
        return false;
    }

    // Método para buscar se uma série de determinado nome está nas listas de séries
    public boolean buscarNome(String nome) {
        return F.containsKey(nome) || A.containsKey(nome);
    }

    public void trocarDeLista(String nomeMidia) {
        Midia midia = buscarSerie(nomeMidia);
        if (midia != null) {
            if (F.containsKey(nomeMidia)) {
                F.remove(nomeMidia);
                A.put(nomeMidia, midia);
            } else if (A.containsKey(nomeMidia)) {
                A.remove(nomeMidia);
                F.put(nomeMidia, midia);
            }
        } else {
            System.out.println("Série não encontrada nas listas de séries.");
        }
    }

	public void setSeries(Map<String, Serie> series) {
		// TODO Auto-generated method stub
		
	}

	public void setFilmes(Map<Integer, Filme> filmes) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Serie> getSeriesParaAssistir() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Serie> getFilmesParaAssistir() {
		// TODO Auto-generated method stub
		return null;
	}

	public void adicionarMidiaJaAssistida(String nomeMidiaAssistida) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Serie> getMidasJaAssistidas() {
		// TODO Auto-generated method stub
		return null;
	}
}

