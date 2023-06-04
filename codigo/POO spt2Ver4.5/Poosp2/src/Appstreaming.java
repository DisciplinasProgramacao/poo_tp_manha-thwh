import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Appstreaming {
    private Map<String, Midia> F; // Mapa de s�ries para assistir no futuro
    private Map<String, Midia> A; // Mapa de s�ries j� assistidas
    private Espectador usuarioLogado; // Objeto de login do usu�rio

    // Construtor
    public Appstreaming(Espectador login) {
        F = new HashMap<>();
        A = new HashMap<>();
        this.setUsuarioLogado(login);
    }

    // M�todo para adicionar uma s�rie para assistir no futuro
    public void adicionarParaAssistir(Midia midia) {
        F.put(midia.getNome(), midia);
    }

    // M�todo para adicionar uma s�rie � lista de s�ries j� assistidas
    public void adicionarJaAssistida(String nomeMidia) {
        Midia midia = buscarMidia(nomeMidia);
        if (midia != null && F.containsKey(nomeMidia)) {
            F.remove(nomeMidia);
            A.put(nomeMidia, midia);
        } else {
            System.out.println("S�rie n�o encontrada na lista de s�ries para assistir.");
        }
    }

    // M�todo para buscar uma s�rie pelo nome
    private Midia buscarMidia(String nomeMidia) {
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
        Midia midia = buscarMidia(nomeMidia);
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

    public void setSeries(Map<Integer, Serie> series) {
        F.clear();
        A.clear();
        for (Serie serie : series.values()) {
            if (serie.getStatus().equals("F")) {
                F.put(serie.getNome(), serie);
            } else if (serie.getStatus().equals("A")) {
                A.put(serie.getNome(), serie);
            }
        }
    }

    public void setFilmes(Map<Integer, Filme> filmes) {
        
    }

    public Map<String, Midia> getSeriesParaAssistir() {
        return F;
    }

    public Map<String, Serie> getFilmesParaAssistir() {
        return null;
    }

    public void adicionarMidiaJaAssistida(String nomeMidiaAssistida) {
        
    }

    public Map<String, Midia> getMidasJaAssistidas() {
        return A;
    }

    public Espectador getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Espectador usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    // M�todo para ler o arquivo CSV e atualizar as listas de s�ries
    public void lerArquivoCSV(String nomeArquivo) throws FileNotFoundException {
        File arquivo = new File(nomeArquivo);
        Scanner scanner = new Scanner(arquivo);

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] campos = linha.split(";");

            if (campos.length == 3) {
                String login = campos[0];
                String status = campos[1];
                String idMidia = campos[2];

                if (login.equals(usuarioLogado.getLogin())) {
                    if (status.equals("F")) {
                        Serie serie = Serie.lerSeriePorId(idMidia);
                        if (serie != null) {
                            F.put(serie.getNome(), serie);
                        }
                    } else if (status.equals("A")) {
                        Serie serie = Serie.lerSeriePorId(idMidia);
                        if (serie != null) {
                            A.put(serie.getNome(), serie);
                        }
                    }
                }
            }
        }

        scanner.close();
    }
}


