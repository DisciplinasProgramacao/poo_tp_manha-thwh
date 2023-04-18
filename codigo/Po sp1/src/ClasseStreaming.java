import java.util.ArrayList;
import java.util.List;

public class ClasseStreaming {
    private List<classeSerie> F; // Lista de séries para assistir no futuro
    private List<classeSerie> A; // Lista de séries já assistidas
    private classeEspectador usuarioLogado; // Objeto de login do usuário

    // Construtor
    public ClasseStreaming(classeEspectador login) {
        F = new ArrayList<>();
        A = new ArrayList<>();
        this.usuarioLogado = login;
    }

    // Método para adicionar uma série para assistir no futuro
    public void adicionarParaAssistir(classeSerie serie) {
        F.add(serie);
    }

    // Método para adicionar uma série à lista de séries já assistidas
    public void adicionarJaAssistida(String nomeSerie) {
        classeSerie serie = buscarSerie(nomeSerie);
        if (serie != null && F.contains(serie)) {
            F.remove(serie);
            A.add(serie);
        } else {
            System.out.println("Série não encontrada na lista de séries para assistir.");
        }
    }

    // Método para buscar uma série pelo nome
    private classeSerie buscarSerie(String nomeSerie) {
        for (classeSerie s : F) {
            if (s.getNome().equals(nomeSerie)) {
                return s;
            }
        }
        for (classeSerie s : A) {
            if (s.getNome().equals(nomeSerie)) {
                return s;
            }
        }
        return null;
    }

    // Método para buscar se uma série de determinado gênero está nas listas de séries
    public boolean buscarGenero(String genero) {
        for (classeSerie s : F) {
            if (s.getGenero().equals(genero)) {
                return true;
            }
        }
        for (classeSerie s : A) {
            if (s.getGenero().equals(genero)) {
                return true;
            }
        }
        return false;
    }

    // Método para buscar se uma série de determinado idioma está nas listas de séries
    public boolean buscarIdioma(String idioma) {
        for (classeSerie s : F) {
            if (s.getIdioma().equals(idioma)) {
                return true;
            }
        }
        for (classeSerie s : A) {
            if (s.getIdioma().equals(idioma)) {
                return true;
            }
        }
        return false;
    }

    // Método para buscar se uma série de determinado nome está nas listas de séries
    public boolean buscarNome(String nome) {
        for (classeSerie s : F) {
            if (s.getNome().equals(nome)) {
                return true;
            }
        }
        for (classeSerie s : A) {
            if (s.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }
    public void trocarDeLista(String nomeSerie) {
        classeSerie serie = buscarSerie(nomeSerie);
        if (serie != null) {
            if (F.contains(serie)) {
                F.remove(serie);
                A.add(serie);
            } else if (A.contains(serie)) {
                A.remove(serie);
                F.add(serie);
            }
        } else {
            System.out.println("Série não encontrada nas listas de séries.");
        }
    }
}