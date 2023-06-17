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
    private TipoEspectador tipoEspectador;

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
        this.datasSeriesAssistidas = new ArrayList<>();
        this.jaAssistida = new ArrayList<>();
        this.tipoEspectador = TipoEspectador.REGULAR;
    }

    /**
     * Adiciona uma m�dia � lista de assistir no futuro.
     *
     * @param midia A m�dia a ser adicionada
     */
    public void adicionarNaLista(Midia midia) {
        if (!assistirFuturo.contains(midia)) {
            this.assistirFuturo.add(midia);
        }
    }

    /**
     * Remove uma m�dia da lista de assistir no futuro.
     *
     * @param midia A m�dia a ser removida
     */
    public void retirarDaLista(Midia midia) {
        this.assistirFuturo.remove(midia);
    }

    /**
     * Busca as m�dias de um determinado g�nero na lista de assistir no futuro.
     *
     * @param genero O g�nero a ser buscado
     * @return Uma lista de m�dias do g�nero especificado
     */
    public List<Midia> buscarGenero(Genero genero) {
        return assistirFuturo.stream()
                .filter(s -> s.getGenero() == genero)
                .collect(Collectors.toList());
    }

    /**
     * Busca as m�dias de um determinado idioma na lista de assistir no futuro.
     *
     * @param idioma O idioma a ser buscado
     * @return Uma lista de m�dias do idioma especificado
     */
    public List<Midia> buscarIdioma(Idioma idioma) {
        return assistirFuturo.stream()
                .filter(s -> s.getIdioma() == idioma)
                .collect(Collectors.toList());
    }

    /**
     * Registra a visualiza��o de uma m�dia.
     *
     * @param midia A m�dia visualizada
     */
    public void registrarVisualizacao(Midia midia) {
        if (!this.jaAssistida.contains(midia)) {
            this.jaAssistida.add(midia);
            midia.registrarVisualizacao();
            this.datasSeriesAssistidas.add(LocalDate.now());
        }
    }

    /**
     * Verifica se o espectador pode avaliar uma determinada m�dia.
     *
     * @param midia A m�dia a ser avaliada
     * @return true se o espectador pode avaliar a m�dia, false caso contr�rio
     * @throws EspectadorAvaliaException Se ocorrer um erro ao verificar se o espectador pode avaliar a m�dia
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
     * Avalia uma m�dia.
     *
     * @param nota  A nota da avalia��o
     * @param midia A m�dia a ser avaliada
     * @throws EspectadorAvaliaException    Se ocorrer um erro ao avaliar a m�dia
     * @throws InvalidParameterException    Se a avalia��o for inv�lida
     */
    public void avaliarMidia(double nota, Midia midia) throws EspectadorAvaliaException, InvalidParameterException {
        if (podeAvaliarMidia(midia)) {
            Avaliacao avaliacao = new Avaliacao(this, midia, nota);
            this.listaNotas.add(avaliacao);
            midia.adicionarAvaliacao(avaliacao);
        } else {
            throw new InvalidParameterException("!!! O CLIENTE N�O PODE AVALIAR ESTA MIDIA !!!");
        }
    }

    /**
     * Obt�m a lista de avalia��es feitas pelo espectador.
     *
     * @return A lista de avalia��es
     */
    public List<Avaliacao> getAvaliacoes() {
        return this.listaNotas;
    }

    /**
     * Obt�m as datas em que o espectador assistiu s�ries.
     *
     * @return A lista de datas de s�ries assistidas
     */
    public List<LocalDate> getDatasSeriesAssistidas() {
        return this.datasSeriesAssistidas;
    }

    /**
     * Obt�m a senha do espectador.
     *
     * @return A senha do espectador
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * Obt�m o nome do espectador.
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
     * Obt�m o login do espectador.
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
     * Obt�m a lista de m�dias para assistir no futuro.
     *
     * @return A lista de m�dias para assistir no futuro
     */
    public List<Midia> getAssistirFuturo() {
        return this.assistirFuturo;
    }

    /**
     * Define a lista de m�dias para assistir no futuro.
     *
     * @param assistirFuturo A lista de m�dias para assistir no futuro
     */
    public void setAssistirFuturo(List<Midia> assistirFuturo) {
        this.assistirFuturo = assistirFuturo;
    }

    /**
     * Obt�m a lista de m�dias j� assistidas pelo espectador.
     *
     * @return A lista de m�dias j� assistidas
     */
    public List<Midia> getJaAssistida() {
        return this.jaAssistida;
    }

    /**
     * Define a lista de m�dias j� assistidas pelo espectador.
     *
     * @param jaAssistida A lista de m�dias j� assistidas
     */
    public void setJaAssistida(List<Midia> jaAssistida) {
        this.jaAssistida = jaAssistida;
    }

    /**
     * Adiciona uma m�dia � lista de m�dias j� assistidas pelo espectador.
     *
     * @param midia A m�dia a ser adicionada
     */
    public void adicionarMidiaAssistida(Midia midia) {
        jaAssistida.add(midia);
        midia.registrarVisualizacao();
        if (midia instanceof Serie) {
            datasSeriesAssistidas.add(LocalDate.now());
        }
    }


    /**
     * Obt�m o tipo de espectador.
     *
     * @return O tipo de espectador
     */
    public TipoEspectador getTipoEspectador() {
        return this.tipoEspectador;
    }

    /**
     * Define o tipo de espectador.
     *
     * @param tipoEspectador O tipo de espectador
     */
    public void setTipoEspectador(TipoEspectador tipoEspectador) {
        this.tipoEspectador = tipoEspectador;
    }
}
