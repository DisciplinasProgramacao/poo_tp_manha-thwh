import java.util.ArrayList;
import java.util.List;

public class Appstreaming {
    private List<Midia> F; // Lista de séries para assistir no futuro
    private List<Midia> A; // Lista de séries já assistidas
    private Espectador usuarioLogado; // Objeto de login do usuário

    // Construtor
    public Appstreaming(Espectador login) {
        F = new ArrayList<>();
        A = new ArrayList<>();
        this.usuarioLogado = login;
    }

    // Método para adicionar uma série para assistir no futuro
    public void adicionarParaAssistir(Midia midia) {
        F.add(midia);
    }

    // Método para adicionar uma série à lista de séries já assistidas
    public void adicionarJaAssistida(String nomeMidia) {
    	Midia midia = buscarSerie(nomeMidia);
        if (midia != null && F.contains(midia)) {
            F.remove(midia);
            A.add(midia);
        } else {
            System.out.println("Série não encontrada na lista de séries para assistir.");
        }
    }

    // Método para buscar uma série pelo nome
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

    // Método para buscar se uma série de determinado gênero está nas listas de séries
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

    // Método para buscar se uma série de determinado idioma está nas listas de séries
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

    // Método para buscar se uma série de determinado nome está nas listas de séries
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
            System.out.println("Série não encontrada nas listas de séries.");
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
