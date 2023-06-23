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
     * @param nomeDeUsuario  Nome de usuário do espectador.
     * @param senha          Senha do espectador.
     * @throws EspectadorException
     */
    public Espectador(String nome, String login, String senha) throws EspectadorException {
		if (nome == null || nome.isEmpty()) {
			throw new EspectadorException("!!! O NOME DO ESPECTADOR NÃO PODE SER VAZIO !!!", nome);
		}
		if (login == null || login.isEmpty()) {
			throw new EspectadorException("!!! O LOGIN DO ESPECTADOR NÃO PODE SER VAZIO !!!", login);
		}
		if (senha == null || senha.isEmpty()) {
			throw new EspectadorException("!!! A SENHA DO ESPECTADOR NÃO PODE SER VAZIA !!!", senha);
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
     * Adiciona uma mídia à lista de assistir no futuro do espectador.
     *
     * @param midia A mídia a ser adicionada à lista.
     */
    public void adicionarNaLista(Midia midia) {
        if (!assistirFuturo.contains(midia)) {
            this.assistirFuturo.add(midia);
        }
    }

    /**
     * Remove uma série da lista de já assistidas do espectador.
     *
     * @param midia A série a ser removida da lista.
     */
    public void retirarDaLista(Midia midia) {
        if (assistirFuturo.contains(midia)) {
            this.assistirFuturo.remove(midia);
        } else {
            System.out.println("Mídia não encontrada!");
        }
    }

    /**
     * Filtra a lista de assistir no futuro do espectador por gênero.
     *
     * @param genero O gênero pelo qual filtrar as mídias.
     * @return Uma lista das mídias filtradas por gênero.
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
     * @param idioma O idioma pelo qual filtrar as mídias.
     * @return Uma lista das mídias filtradas por idioma.
     */
    public List<Midia> filtrarPorIdioma(String idioma) {
        String idiomaLowerCase = idioma.toLowerCase();
        return assistirFuturo.stream()
                .filter(m -> m.getIdioma().toLowerCase().equals(idiomaLowerCase))
                .collect(Collectors.toList());
    }

    /**
     * Filtra a lista de assistir no futuro do espectador pela quantidade de episódios.
     *
     * @param qtdEpisodios A quantidade de episódios pela qual filtrar as mídias.
     * @return Uma lista das mídias filtradas pela quantidade de episódios.
     */
    public List<Midia> filtrarPorQtdEpisodios(int qtdEpisodios) {
        if (qtdEpisodios <= 0) {
            throw new IllegalArgumentException("A quantidade de episódios deve ser um valor válido e maior que zero.");
        }

        return assistirFuturo.stream()
                .filter(m -> m.getQuantidadeEpisodios() == qtdEpisodios)
                .collect(Collectors.toList());
    }

    /**
     * Registra a audiência de uma mídia na lista de mídias já assistidas pelo espectador.
     *
     * @param midia A mídia cuja audiência será registrada.
     */
    public void registrarAudiencia(Midia midia) {
        if (!this.jaAssistida.contains(midia)) {
            this.jaAssistida.add(midia);
            midia.registrarAudiencia();
            this.datasSeriesAssistidas.add(LocalDate.now());
        }
    }

    /**
     * Verifica se o espectador pode avaliar a mídia.
     *
     * @param midia A mídia a ser avaliada.
     * @return true se o espectador pode avaliar a mídia, false caso contrário.
     * @throws EspectadorAvaliaException Exceção lançada quando o espectador não pode avaliar a mídia.
     */
    public boolean podeAvaliarMidia(Midia midia) throws EspectadorAvaliaException {
        for (Avaliacao avaliacao : listaNotas) {
            if (avaliacao.getMidia().equals(midia) || !this.jaAssistida.contains(midia)) {
                return false; // A mídia já foi avaliada pelo espectador
            }
        }
        return true;
    }

    /**
     * Avalia uma mídia atribuindo uma nota a ela.
     *
     * @param nota  A nota a ser atribuída à mídia.
     * @param midia A mídia a ser avaliada.
     * @throws EspectadorAvaliaException    Exceção lançada em caso de erro ao avaliar a mídia pelo espectador.
     * @throws InvalidParameterException    Exceção lançada quando a mídia não foi assistida antes de ser avaliada.
     */
    public void avaliarMidia(double nota, Midia midia) throws EspectadorAvaliaException, InvalidParameterException {
        if (podeAvaliarMidia(midia) && nota > 0 && nota < 6) {
            Avaliacao avaliacao = new Avaliacao(this, midia, nota);
            this.listaNotas.add(avaliacao);
            midia.adicionarAvaliacao(avaliacao);
        } else {
            throw new InvalidParameterException("O espectador não pode avaliar esta mídia.");
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
     * Adiciona uma mídia na lista de desejos do espectador.
     *
     * @param midia A mídia a ser adicionada à lista de desejos.
     */
    public void adicionarNaListaDesejos(Midia midia) {
        this.listaDesejos.add(midia);
    }
}