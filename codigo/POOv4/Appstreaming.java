import java.util.ArrayList;
import java.util.List;

public class Appstreaming {
    private List<Midia> F; // Lista de s�ries para assistir no futuro
    private List<Midia> A; // Lista de s�ries j� assistidas
    private Espectador usuarioLogado; // Objeto de login do usu�rio

    // Construtor
    public Appstreaming(Espectador login) {
        F = new ArrayList<>();
        A = new ArrayList<>();
        this.usuarioLogado = login;
    }

    // M�todo para adicionar uma s�rie para assistir no futuro
    public void adicionarParaAssistir(Midia midia) {
        F.add(midia);
    }

    // M�todo para adicionar uma s�rie � lista de s�ries j� assistidas
    public void adicionarJaAssistida(String nomeMidia) {
    	Midia midia = buscarSerie(nomeMidia);
        if (midia != null && F.contains(midia)) {
            F.remove(midia);
            A.add(midia);
        } else {
            System.out.println("S�rie n�o encontrada na lista de s�ries para assistir.");
        }
    }

    // M�todo para buscar uma s�rie pelo nome
    private Midia buscarSerie(String nomeMidia) {
        for (Midia s : F) {
            if (s.getNome().equals(nomeMidia)) {
                return s;
            }
        }
        for (Midia s : A) {
            if (s.getNome().equals(nomeMidia)) {
                return s;
            }
        }
        return null;
    }

    // M�todo para buscar se uma s�rie de determinado g�nero est� nas listas de s�ries
    public boolean buscarGenero(String genero) {
        for (Midia s : F) {
            if (s.getGenero().equals(genero)) {
                return true;
            }
        }
        for (Midia s : A) {
            if (s.getGenero().equals(genero)) {
                return true;
            }
        }
        return false;
    }

    // M�todo para buscar se uma s�rie de determinado idioma est� nas listas de s�ries
    public boolean buscarIdioma(String idioma) {
        for (Midia s : F) {
            if (s.getIdioma().equals(idioma)) {
                return true;
            }
        }
        for (Midia s : A) {
            if (s.getIdioma().equals(idioma)) {
                return true;
            }
        }
        return false;
    }

    // M�todo para buscar se uma s�rie de determinado nome est� nas listas de s�ries
    public boolean buscarNome(String nome) {
        for (Midia s : F) {
            if (s.getNome().equals(nome)) {
                return true;
            }
        }
        for (Midia s : A) {
            if (s.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public void trocarDeLista(String nomeMidia) {
    	Midia midia = buscarSerie(nomeMidia);
        if (midia != null) {
            if (F.contains(midia)) {
                F.remove(midia);
                A.add(midia);
            } else if (A.contains(midia)) {
                A.remove(midia);
                F.add(midia);
            }
        } else {
            System.out.println("S�rie n�o encontrada nas listas de s�ries.");
        }
    }

	public Midia[] getF() {
		
		return null;
	}

	public Midia[] getA() {
		
		return null;
	}

	public void setSeries(ArrayList<Serie> series) {
		
		
	}

	public void setFilmes(ArrayList<Filme> filmes) {
		
		
	}

	public Serie[] getSeries() {
		
		return null;
	}

	public Filme[] getFilmes() {
		
		return null;
	}

	public Midia[] getMidasJaAssistidas() {
		
		return null;
	}
}
