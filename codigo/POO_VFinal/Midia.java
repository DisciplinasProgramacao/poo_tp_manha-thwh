import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * Classe abstrata Midia que representa uma m�dia gen�rica.
 */
public abstract class Midia {

	private int id;
    private String nome;
    private int audiencia = 0;
    private LocalDate dataLancamento;
    private LocalDate dataAssistida;
    private Genero genero;
    private Idioma idioma;
    private List<Avaliacao> avaliacoes;
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
            Idioma.JAPONES,
            Idioma.COREANO,
            Idioma.RUSSO,
            Idioma.MANDARIM,
            Idioma.ALEMO,
            Idioma.ARABE
    ));

    /**
     * Construtor padr�o da classe Midia.
     */
    public Midia() {
    }

    /**
     * Construtor da classe Midia.
     *
     * @param id             Identificador �nico da m�dia.
     * @param nome           Nome da m�dia.
     * @param dataLancamento Data de lan�amento da m�dia.
     * @param genero         �ndice do g�nero da m�dia na lista de g�neros dispon�veis.
     * @param idioma         �ndice do idioma da m�dia na lista de idiomas dispon�veis.
     */
    public Midia(int id, String nome, LocalDate dataLancamento, int genero, int idioma, boolean lancamento) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.genero = GENEROS.get(genero);
        this.idioma = IDIOMAS.get(idioma);
        this.avaliacoes = new ArrayList<>();
 
    }

    /**
     * Construtor da classe Midia.
     *
     * @param id             Identificador �nico da m�dia.
     * @param nome           Nome da m�dia.
     * @param dataLancamento Data de lan�amento da m�dia.
     * @throws InvalidParameterException se algum par�metro for inv�lido.
     */
    public Midia(int id, String nome, LocalDate dataLancamento) throws InvalidParameterException {
        if (id < 0) {
            throw new InvalidParameterException("!!! O ID DA MIDIA N�O PODE SER VAZIO !!!");
        }
        if (nome.length() < 3) {
            throw new InvalidParameterException("!!! O NOME DA MIDIA DEVE POSSUIR MAIS DE 3 CARACTERES !!!");
        }
        if (dataLancamento.isAfter(LocalDate.now())) {
            throw new InvalidParameterException("!!! O LAN�AMENTO DA MIDIA N�O PODE SER UMA DATA FUTURA !!!");
        }

        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.avaliacoes = new ArrayList<>();
        
    }

    /**
     * Adiciona uma avalia��o � lista de avalia��es da m�dia.
     *
     * @param avaliacao A avalia��o a ser adicionada.
     */
    public void adicionarAvaliacao(Avaliacao avaliacao) {
            this.avaliacoes.add(avaliacao);

    }

    /**
     * Registra a data em que a m�dia est� sendo assistida.
     * @param dataAssistida A data em que a m�dia est� sendo assistida.
     */
    public void registrarDataAssistida(LocalDate dataAssistida) {
        this.dataAssistida = dataAssistida;
    }

    /**
     * Retorna a data em que a m�dia foi assistida.
     * @return A data em que a m�dia foi assistida.
     */
    public LocalDate getDataAssistida() {
        return dataAssistida;
    }

    /**
     * Retorna a lista de avalia��es da m�dia.
     *
     * @return A lista de avalia��es da m�dia.
     */
    public List<Avaliacao> getAvaliacoes() {
        return this.avaliacoes;
    }


    /**
     * @return Quantidade de avalia��es
     */
    public int getNumAvaliacoes() {
        return this.avaliacoes.size();
    }

    /**
     * Calcula a m�dia das notas atribu�das por todos os app.app.clientes para esta m�dia.
     *
     * @return A m�dia das notas atribu�das pelos app.app.clientes para esta m�dia.
     */
    public double calcularMediaDeNotas() {
        if (avaliacoes.isEmpty()) {
            return 0;
        }

        double somaNotas = 0;
        int quantidadeDeAvaliacoes = avaliacoes.size();
        
        for (Avaliacao avaliacao : avaliacoes) {
            somaNotas += avaliacao.getNota();
        }
        
        return somaNotas / quantidadeDeAvaliacoes;
    }
    
     public boolean isLancamento() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataLancamento = getDataLancamento();
        return dataLancamento.isAfter(dataAtual.minusMonths(1));
    } 
     
    /**
     * Registra a audi�ncia da m�dia.
     */
    public void registrarAudiencia(){
        this.audiencia++;
    }

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

    public int getAudiencia() {
        return this.audiencia;
    }

    public void setAudiencia(int audiencia) {
        this.audiencia = audiencia;
    }

    public LocalDate getDataLancamento() {
        return this.dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Genero getGenero() {
        return Optional.ofNullable(this.genero)
                .orElseThrow();
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
     * Retorna a quantidade de epis�dios da m�dia.
     * @return -1, j� que a classe midia.Midia � abstrata e n�o possui epis�dios.
    */
    public int getQuantidadeEpisodios() {
        return -1;
    }
}