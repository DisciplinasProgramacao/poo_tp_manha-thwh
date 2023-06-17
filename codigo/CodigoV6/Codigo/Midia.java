import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Classe abstrata Midia que representa uma mídia genérica.
 */
public abstract class Midia {

    private int id;
    private String nome;
    private Genero genero;
    private int visualizacao;
    private LocalDate dataLancamento;
    private LocalDate dataAssistida;
    private Idioma idioma;
    protected boolean lancamento;
    private HashMap<Avaliacao, Espectador> avaliacoes;
    private List<String> comentarios;

    private static final List<Genero> GENEROS = new ArrayList<>(Arrays.asList(
            Genero.ACAO,
            Genero.ANIME,
            Genero.AVENTURA,
            Genero.COMEDIA,
            Genero.DOCUMENTARIO,
            Genero.DRAMA,
            Genero.POLICIAL,
            Genero.ROMANCE,
            Genero.SUSPENSE
    ));
    private static final List<Idioma> IDIOMAS = new ArrayList<>(Arrays.asList(
            Idioma.INGLES,
            Idioma.PORTUGUES,
            Idioma.ESPANHOL,
            Idioma.JAPONÊS,
            Idioma.COREANO,
            Idioma.RUSSO,
            Idioma.MANDARIM,
            Idioma.ALEMÃO,
            Idioma.ÁRABE
    ));

    /**
     * Construtor padrão da classe Midia.
     */
    public Midia() {
    }

    /**
     * Construtor da classe Midia.
     *
     * @param id             Identificador único da mídia.
     * @param nome           Nome da mídia.
     * @param dataLancamento Data de lançamento da mídia.
     * @param genero         Índice do gênero da mídia na lista de gêneros disponíveis.
     * @param idioma         Índice do idioma da mídia na lista de idiomas disponíveis.
     */
    public Midia(int id, String nome, LocalDate dataLancamento, int genero, int idioma, boolean lancamento) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.genero = GENEROS.get(genero);
        this.idioma = IDIOMAS.get(idioma);
        this.avaliacoes = new HashMap<>();
        this.comentarios = new ArrayList<>();
        this.lancamento = lancamento;
    }

    /**
     * Construtor da classe Midia.
     *
     * @param id             Identificador único da mídia.
     * @param nome           Nome da mídia.
     * @param dataLancamento Data de lançamento da mídia.
     * @throws InvalidParameterException se algum parâmetro for inválido.
     */
    public Midia(int id, String nome, LocalDate dataLancamento) throws InvalidParameterException {
        if (id < 0) {
            throw new InvalidParameterException("!!! O ID DA MIDIA NÃO PODE SER VAZIO !!!");
        }
        if (nome.length() < 3) {
            throw new InvalidParameterException("!!! O NOME DA MIDIA DEVE POSSUIR MAIS DE 3 CARACTERES !!!");
        }
        if (dataLancamento.isAfter(LocalDate.now())) {
            throw new InvalidParameterException("!!! O LANÇAMENTO DA MIDIA NÃO PODE SER UMA DATA FUTURA !!!");
        }

        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.avaliacoes = new HashMap<>();
        this.comentarios = new ArrayList<>();
    }

    /**
     * Adiciona uma avaliação à mídia.
     *
     * @param avaliacao A avaliação a ser adicionada.
     */
    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.put(avaliacao, avaliacao.getEspectador());
    }

    /**
     * Registra a data em que a mídia está sendo assistida.
     *
     * @param dataAssistida A data em que a mídia está sendo assistida.
     */
    public void registrarDataAssistida(LocalDate dataAssistida) {
        this.dataAssistida = dataAssistida;
    }

    /**
     * Retorna a data em que a mídia foi assistida.
     *
     * @return A data em que a mídia foi assistida.
     */
    public LocalDate getDataAssistida() {
        return dataAssistida;
    }

    /**
     * Retorna a lista de avaliações da mídia.
     *
     * @return A lista de avaliações da mídia.
     */
    public HashMap<Avaliacao, Espectador> getAvaliacoes() {
        return this.avaliacoes;
    }

    /**
     * Calcula a média das notas atribuídas à mídia.
     *
     * @return A média das notas.
     */
    public double calcularMediaDeNotas() {
        if (avaliacoes.isEmpty()) {
            return 0;
        }

        double somaNotas = 0;
        int quantidadeDeAvaliacoes = avaliacoes.size();

        for (Entry<Avaliacao, Espectador> entry : avaliacoes.entrySet()) {
            Avaliacao avaliacao = entry.getKey();
            somaNotas += avaliacao.getNota();
        }

        return somaNotas / quantidadeDeAvaliacoes;
    }

    /**
     * Verifica se a mídia é um lançamento.
     *
     * @return true se for um lançamento, false caso contrário.
     */
    public boolean isLancamento() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataLancamento = getDataLancamento();
        return lancamento && dataLancamento.isAfter(dataAtual.minusMonths(1));
    }

    /**
     * Verifica se um espectador tem acesso ao lançamento da mídia.
     *
     * @param espectador O espectador a ser verificado.
     * @return true se o espectador tem acesso ao lançamento, false caso contrário.
     */
    public boolean verificarAcessoLancamento(Espectador espectador) {
        return espectador.getTipoEspectador() == TipoEspectador.PROFISSIONAL && isLancamento();
    }
    /**
     * Adiciona um comentário à mídia.
     *
     * @param espectador O espectador que está adicionando o comentário.
     * @param mensagem   A mensagem do comentário.
     */
    public void adicionarComentario(Espectador espectador, String mensagem) {
        if (espectador.getTipoEspectador() == TipoEspectador.PROFISSIONAL || espectador.getTipoEspectador() == TipoEspectador.ESPECIALISTA) {
            comentarios.add(mensagem);
            System.out.println("<< COMENTÁRIO FOI ADICIONADO COM ÊXITO >>");
        } else {
            System.out.println("!!! APENAS ESPECTADORES PROFISSIONAIS E ESPECIALISTAS PODEM ADICIONAR COMENTÁRIOS !!!");
        }
    }

    /**
     * Registra a visualização da mídia.
     */
    public void registrarVisualizacao() {
        this.visualizacao++;
    }

    // Getters e Setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVisualizacao() {
        return this.visualizacao;
    }

    public void setVisualizacao(int visualizacao) {
        this.visualizacao = visualizacao;
    }

    public LocalDate getDataLancamento() {
        return this.dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Idioma getIdioma() {
        return this.idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    /**
     * Retorna a quantidade de episódios da mídia.
     *
     * @return A quantidade de episódios.
     */
    public int getQuantidadeEpisodios() {
		return 0;
	}

    /**
     * Retorna a duração da mídia.
     *
     * @return A duração da mídia em minutos.
     */
    public double getDuracao() {
		return 0;
	} 
}
