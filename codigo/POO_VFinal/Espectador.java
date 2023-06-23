import exceptions.EspectadorAvaliaException;
import exceptions.EspectadorException;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe Espectador que representa um espectador na plataforma de streaming.
 */
public class Espectador {
    private String nome;
    private String login;
    private String senha;
    private List<Midia> assistirFuturo;
    private List<Midia> jaAssistida;
    private List<Midia> listaDesejos;
    private List<Avaliacao> listaNotas;
    private List<LocalDate> datasSeriesAssistidas;

    /**
     * Construtor da classe Espectador.
     *
     * @param nome           Nome completo do espectador.
     * @param nomeDeUsuario  Nome de usu�rio do espectador.
     * @param senha          Senha do espectador.
     * @throws EspectadorException
     */
    public Espectador(String nome, String login, String senha) throws EspectadorException {
		if (nome == null || nome.isEmpty()) {
			throw new EspectadorException("!!! O NOME DO ESPECTADOR N�O PODE SER VAZIO !!!", nome);
		}
		if (login == null || login.isEmpty()) {
			throw new EspectadorException("!!! O LOGIN DO ESPECTADOR N�O PODE SER VAZIO !!!", login);
		}
		if (senha == null || senha.isEmpty()) {
			throw new EspectadorException("!!! A SENHA DO ESPECTADOR N�O PODE SER VAZIA !!!", senha);
		}

        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.assistirFuturo = new ArrayList<>();
        this.jaAssistida = new ArrayList<>();
        this.listaDesejos = new ArrayList<>();
        this.listaNotas = new ArrayList<>();
        this.datasSeriesAssistidas = new ArrayList<>();
    }

    /**
     * Adiciona uma m�dia � lista de assistir no futuro do espectador.
     *
     * @param midia A m�dia a ser adicionada � lista.
     */
    public void adicionarNaLista(Midia midia) {
        if (!assistirFuturo.contains(midia)) {
            this.assistirFuturo.add(midia);
        }
    }

    /**
     * Remove uma s�rie da lista de j� assistidas do espectador.
     *
     * @param midia A s�rie a ser removida da lista.
     */
    public void retirarDaLista(Midia midia) {
        if (assistirFuturo.contains(midia)) {
            this.assistirFuturo.remove(midia);
        } else {
            System.out.println("M�dia n�o encontrada!");
        }
    }

    /**
     * Filtra a lista de assistir no futuro do espectador por g�nero.
     *
     * @param genero O g�nero pelo qual filtrar as m�dias.
     * @return Uma lista das m�dias filtradas por g�nero.
     */
    public List<Midia> filtrarPorGenero(String genero) {
        String generoLowerCase = genero.toLowerCase();
        return assistirFuturo.stream()
                .filter(m -> m.getGenero().toLowerCase().equals(generoLowerCase))
                .collect(Collectors.toList());
    }

    /**
     * Filtra a lista de assistir no futuro do espectador por idioma.
     *
     * @param idioma O idioma pelo qual filtrar as m�dias.
     * @return Uma lista das m�dias filtradas por idioma.
     */
    public List<Midia> filtrarPorIdioma(String idioma) {
        String idiomaLowerCase = idioma.toLowerCase();
        return assistirFuturo.stream()
                .filter(m -> m.getIdioma().toLowerCase().equals(idiomaLowerCase))
                .collect(Collectors.toList());
    }

    /**
     * Filtra a lista de assistir no futuro do espectador pela quantidade de epis�dios.
     *
     * @param qtdEpisodios A quantidade de epis�dios pela qual filtrar as m�dias.
     * @return Uma lista das m�dias filtradas pela quantidade de epis�dios.
     */
    public List<Midia> filtrarPorQtdEpisodios(int qtdEpisodios) {
        if (qtdEpisodios <= 0) {
            throw new IllegalArgumentException("A quantidade de epis�dios deve ser um valor v�lido e maior que zero.");
        }

        return assistirFuturo.stream()
                .filter(m -> m.getQuantidadeEpisodios() == qtdEpisodios)
                .collect(Collectors.toList());
    }

    /**
     * Registra a audi�ncia de uma m�dia na lista de m�dias j� assistidas pelo espectador.
     *
     * @param midia A m�dia cuja audi�ncia ser� registrada.
     */
    public void registrarAudiencia(Midia midia) {
        if (!this.jaAssistida.contains(midia)) {
            this.jaAssistida.add(midia);
            midia.registrarAudiencia();
            this.datasSeriesAssistidas.add(LocalDate.now());
        }
    }

    /**
     * Verifica se o espectador pode avaliar a m�dia.
     *
     * @param midia A m�dia a ser avaliada.
     * @return true se o espectador pode avaliar a m�dia, false caso contr�rio.
     * @throws EspectadorAvaliaException Exce��o lan�ada quando o espectador n�o pode avaliar a m�dia.
     */
    public boolean podeAvaliarMidia(Midia midia) throws EspectadorAvaliaException {
        for (Avaliacao avaliacao : listaNotas) {
            if (avaliacao.getMidia().equals(midia) || !this.jaAssistida.contains(midia)) {
                return false; // A m�dia j� foi avaliada pelo espectador
            }
        }
        return true;
    }

    /**
     * Avalia uma m�dia atribuindo uma nota a ela.
     *
     * @param nota  A nota a ser atribu�da � m�dia.
     * @param midia A m�dia a ser avaliada.
     * @throws EspectadorAvaliaException    Exce��o lan�ada em caso de erro ao avaliar a m�dia pelo espectador.
     * @throws InvalidParameterException    Exce��o lan�ada quando a m�dia n�o foi assistida antes de ser avaliada.
     */
    public void avaliarMidia(double nota, Midia midia) throws EspectadorAvaliaException, InvalidParameterException {
        if (podeAvaliarMidia(midia) && nota > 0 && nota < 6) {
            Avaliacao avaliacao = new Avaliacao(this, midia, nota);
            this.listaNotas.add(avaliacao);
            midia.adicionarAvaliacao(avaliacao);
        } else {
            throw new InvalidParameterException("O espectador n�o pode avaliar esta m�dia.");
        }
    }

    public List<Avaliacao> getAvaliacoes() {
        return this.listaNotas;
    }

    public List<LocalDate> getDatasSeriesAssistidas() {
        return this.datasSeriesAssistidas;
    }

    // Getters e setters para os atributos da classe Espectador

    public String getSenha() {
        return this.senha;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Midia> getAssistirFuturo() {
        return this.assistirFuturo;
    }

    public void setAssistirFuturo(List<Midia> assistirFuturo) {
        this.assistirFuturo = assistirFuturo;
    }

    public List<Midia> getJaAssistida() {
        return this.jaAssistida;
    }

    public void setJaAssistida(List<Midia> jaAssistida) {
        this.jaAssistida = jaAssistida;
    }

    public List<Avaliacao> getListaNotas() {
        return this.listaNotas;
    }

    /**
     * Adiciona uma m�dia na lista de desejos do espectador.
     *
     * @param midia A m�dia a ser adicionada � lista de desejos.
     */
    public void adicionarNaListaDesejos(Midia midia) {
        this.listaDesejos.add(midia);
    }
}