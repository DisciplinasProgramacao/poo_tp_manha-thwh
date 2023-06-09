import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.EspectadorAvaliaException;
import exceptions.EspectadorException;

/**
 * Classe que representa um Espectador.
 */
public class Espectador {
    private String nome;
    private String login;
    private String senha;
    private List<Midia> assistirFuturo;
    private List<Midia> jaAssistida;
    private List<Avaliacao> listaNotas;
    private List<LocalDate> datasSeriesAssistidas;
    private Profissional profissional;

    /**
     * Construtor da classe Espectador.
     *
     * @param nome  O nome do espectador
     * @param login O login do espectador
     * @param senha A senha do espectador
     * @throws EspectadorException Se ocorrer um erro ao criar o espectador
     */
    public Espectador(String nome, String login, String senha) throws EspectadorException {
        if (nome == null || nome.isEmpty()) {
            throw new EspectadorException("O nome do espectador não pode ser vazio.", nome);
        }
        if (login == null || login.isEmpty()) {
            throw new EspectadorException("O login do espectador não pode ser vazio.", login);
        }
        if (senha == null || senha.isEmpty()) {
            throw new EspectadorException("A senha do espectador não pode ser vazia.", senha);
        }

        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.datasSeriesAssistidas = new ArrayList<>();
        this.jaAssistida = new ArrayList<>();
        this.profissional = Profissional.OUTRO_PROFISSIONAL;
    }

    /**
     * Adiciona uma mídia à lista de assistir no futuro.
     *
     * @param midia A mídia a ser adicionada
     */
    public void adicionarNaLista(Midia midia) {
        if (!assistirFuturo.contains(midia)) {
            this.assistirFuturo.add(midia);
        }
    }

    /**
     * Remove uma mídia da lista de assistir no futuro.
     *
     * @param midia A mídia a ser removida
     */
    public void retirarDaLista(Midia midia) {
        this.assistirFuturo.remove(midia);
    }

    /**
     * Busca as mídias de um determinado gênero na lista de assistir no futuro.
     *
     * @param genero O gênero a ser buscado
     * @return Uma lista de mídias do gênero especificado
     */
    public List<Midia> buscarGenero(Genero genero) {
        return assistirFuturo.stream()
                .filter(s -> s.getGenero() == genero)
                .collect(Collectors.toList());
    }

    /**
     * Busca as mídias de um determinado idioma na lista de assistir no futuro.
     *
     * @param idioma O idioma a ser buscado
     * @return Uma lista de mídias do idioma especificado
     */
    public List<Midia> buscarIdioma(Idioma idioma) {
        return assistirFuturo.stream()
                .filter(s -> s.getIdioma() == idioma)
                .collect(Collectors.toList());
    }

    /**
     * Busca as mídias com uma quantidade específica de episódios na lista de assistir no futuro.
     *
     * @param qtdEpisodios A quantidade de episódios desejada
     * @return Uma lista de mídias com a quantidade de episódios especificada
     * @throws IllegalArgumentException Se a quantidade de episódios for inválida
     */
    public List<Midia> buscarQtdEpisodios(int qtdEpisodios) {
        if (qtdEpisodios <= 1) {
            throw new IllegalArgumentException("A quantidade de episódios deve ser um valor válido e maior que 1.");
        }

        return assistirFuturo.stream()
                .filter(s -> s.getQuantidadeEpisodios() == qtdEpisodios)
                .collect(Collectors.toList());
    }

    /**
     * Registra a visualização de uma mídia.
     *
     * @param midia A mídia visualizada
     */
    public void registrarVisualizacao(Midia midia) {
        if (!this.jaAssistida.contains(midia)) {
            this.jaAssistida.add(midia);
            midia.registrarVisualizacao();
            this.datasSeriesAssistidas.add(LocalDate.now());
        }
    }

    /**
     * Verifica se o espectador pode avaliar uma determinada mídia.
     *
     * @param midia A mídia a ser avaliada
     * @return true se o espectador pode avaliar a mídia, false caso contrário
     * @throws EspectadorAvaliaException Se ocorrer um erro ao verificar se o espectador pode avaliar a mídia
     */
    public boolean podeAvaliarMidia(Midia midia) throws EspectadorAvaliaException {
        for (Avaliacao avaliacao : listaNotas) {
            if (avaliacao.getMidia().equals(midia) || !this.jaAssistida.contains(midia)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Avalia uma mídia.
     *
     * @param nota  A nota da avaliação
     * @param midia A mídia a ser avaliada
     * @throws EspectadorAvaliaException    Se ocorrer um erro ao avaliar a mídia
     * @throws InvalidParameterException    Se a avaliação for inválida
     */
    public void avaliarMidia(double nota, Midia midia) throws EspectadorAvaliaException, InvalidParameterException {
        if (podeAvaliarMidia(midia)) {
            Avaliacao avaliacao = new Avaliacao(this, midia, nota);
            this.listaNotas.add(avaliacao);
            midia.adicionarAvaliacao(avaliacao);
        } else {
            throw new InvalidParameterException("O cliente não pode avaliar esta mídia.");
        }
    }

    /**
     * Obtém a lista de avaliações feitas pelo espectador.
     *
     * @return A lista de avaliações
     */
    public List<Avaliacao> getAvaliacoes() {
        return this.listaNotas;
    }

    /**
     * Obtém as datas em que o espectador assistiu séries.
     *
     * @return A lista de datas de séries assistidas
     */
    public List<LocalDate> getDatasSeriesAssistidas() {
        return this.datasSeriesAssistidas;
    }

    /**
     * Obtém a senha do espectador.
     *
     * @return A senha do espectador
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * Obtém o nome do espectador.
     *
     * @return O nome do espectador
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Define o nome do espectador.
     *
     * @param nome O nome do espectador
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o login do espectador.
     *
     * @return O login do espectador
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Define o login do espectador.
     *
     * @param login O login do espectador
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Define a senha do espectador.
     *
     * @param senha A senha do espectador
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Obtém a lista de mídias para assistir no futuro.
     *
     * @return A lista de mídias para assistir no futuro
     */
    public List<Midia> getAssistirFuturo() {
        return this.assistirFuturo;
    }

    /**
     * Define a lista de mídias para assistir no futuro.
     *
     * @param assistirFuturo A lista de mídias para assistir no futuro
     */
    public void setAssistirFuturo(List<Midia> assistirFuturo) {
        this.assistirFuturo = assistirFuturo;
    }

    /**
     * Obtém a lista de mídias já assistidas pelo espectador.
     *
     * @return A lista de mídias já assistidas
     */
    public List<Midia> getJaAssistida() {
        return this.jaAssistida;
    }

    /**
     * Define a lista de mídias já assistidas pelo espectador.
     *
     * @param jaAssistida A lista de mídias já assistidas
     */
    public void setJaAssistida(List<Midia> jaAssistida) {
        this.jaAssistida = jaAssistida;
    }

    /**
     * Obtém o profissional associado ao espectador.
     *
     * @return O profissional associado ao espectador
     */
    public Profissional getProfissional() {
        return profissional;
    }

    /**
     * Define o tipo de profissional associado ao espectador.
     *
     * @param profissional O tipo de profissional associado ao espectador
     */
    public void setTipoProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    /**
     * Adiciona uma mídia à lista de mídias já assistidas pelo espectador.
     *
     * @param midia A mídia a ser adicionada
     */
    public void adicionarMidiaAssistida(Midia midia) {
        jaAssistida.add(midia);
        midia.registrarVisualizacao();
        if (midia instanceof Serie) {
            datasSeriesAssistidas.add(LocalDate.now());
        }
    }
}
