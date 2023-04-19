import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
public class ClasseStreaming {
    private List<classeSerie> F; // Lista de s�ries para assistir no futuro
    private List<classeSerie> A; // Lista de s�ries j� assistidas
    private classeEspectador usuarioLogado; // Objeto de login do usu�rio

    // Construtor
    public ClasseStreaming(classeEspectador login) {
        F = new ArrayList<>();
        A = new ArrayList<>();
        this.usuarioLogado = login;
    }

    // M�todo para adicionar uma s�rie para assistir no futuro
    public void adicionarParaAssistir(classeSerie serie) {
        F.add(serie);
    }

    // M�todo para adicionar uma s�rie � lista de s�ries j� assistidas
    public void adicionarJaAssistida(String nomeSerie) {
        classeSerie serie = buscarSerie(nomeSerie);
        if (serie != null && F.contains(serie)) {
            F.remove(serie);
            A.add(serie);
        } else {
            System.out.println("S�rie n�o encontrada na lista de s�ries para assistir.");
        }
    }

    // M�todo para buscar uma s�rie pelo nome
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

    // M�todo para buscar se uma s�rie de determinado g�nero est� nas listas de s�ries
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

    // M�todo para buscar se uma s�rie de determinado idioma est� nas listas de s�ries
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

    // M�todo para buscar se uma s�rie de determinado nome est� nas listas de s�ries
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
            System.out.println("S�rie n�o encontrada nas listas de s�ries.");
        }
    }
}