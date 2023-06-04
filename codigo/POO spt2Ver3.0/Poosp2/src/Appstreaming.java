import java.util.HashMap;
import java.util.Map;

public class Appstreaming {
    private Map<String, Midia> F; // Mapa de s�ries para assistir no futuro
    private Map<String, Midia> A; // Mapa de s�ries j� assistidas
    private Espectador usuarioLogado; // Objeto de login do usu�rio

    // Construtor
    public Appstreaming(Espectador login) {
        F = new HashMap<>();
        A = new HashMap<>();
        this.usuarioLogado = login;
    }

    // M�todo para adicionar uma s�rie para assistir no futuro
    public void adicionarParaAssistir(Midia midia) {
        F.put(midia.getNome(), midia);
    }

    // M�todo para adicionar uma s�rie � lista de s�ries j� assistidas
    public void adicionarJaAssistida(String nomeMidia) {
        Midia midia = buscarSerie(nomeMidia);
        if (midia != null && F.containsKey(nomeMidia)) {
            F.remove(nomeMidia);
            A.put(nomeMidia, midia);
        } else {
            System.out.println("S�rie n�o encontrada na lista de s�ries para assistir.");
        }
    }

    // M�todo para buscar uma s�rie pelo nome
    private Midia buscarSerie(String nomeMidia) {
        if (F.containsKey(nomeMidia)) {
            return F.get(nomeMidia);
        } else if (A.containsKey(nomeMidia)) {
            return A.get(nomeMidia);
        }
        return null;
    }

    // M�todo para buscar se uma s�rie de determinado g�nero est� nas listas de s�ries
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

    // M�todo para buscar se uma s�rie de determinado idioma est� nas listas de s�ries
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

    // M�todo para buscar se uma s�rie de determinado nome est� nas listas de s�ries
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
            System.out.println("S�rie n�o encontrada nas listas de s�ries.");
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

