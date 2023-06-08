import java.security.InvalidParameterException;
import java.time.LocalDate;


/**
 * A classe Filme representa um filme e herda da classe Midia.
 */
public class Filme extends Midia{

    private double duracao;

    /**
     * Construtor padrão da classe Filme.
     */
    public Filme(){
        super();
    }

    /**
     * Construtor da classe Filme.
     * @param id Identificador único do filme.
     * @param nome Nome do filme.
     * @param dataLancamento Data de lançamento do filme.
     * @param duracao Duração do filme em minutos.
     * @throws InvalidParameterException
     */
    public Filme(int id,String nome, LocalDate dataLancamento, double duracao) throws InvalidParameterException{

        super(id,nome, dataLancamento);
        if(duracao < 1) {
            throw new InvalidParameterException("A duração do Filme não pode ser menor que 1 minutos!");
        }
        this.duracao = duracao;
    }

        /**
     * Construtor da classe Filme.
     * @param id Identificador único do filme.
     * @param nome Nome do filme.
     * @param dataLancamento Data de lançamento do filme.
     * @param duracao Duração do filme em minutos.
     * @param genero Genero do filme.
     * @throws InvalidParameterException
     */
    public Filme(int id,String nome, LocalDate dataLancamento, double duracao, int genero, int idioma) throws InvalidParameterException {

        super(id,nome, dataLancamento, genero, idioma);
        if(duracao < 1) {
            throw new InvalidParameterException("A duração do Filme não pode ser menor que 1 minutos!");
        }
        this.duracao = duracao;
    }

    
    /**
     * Retorna a duração do filme.
     * @return A duração do filme em minutos.
     */
    public double getDuracao() {
        return this.duracao;
    }

    public int getID_Filme() {
        return getID_Filme();
    }

    
    /**
     * Retorna uma String com os dados do filme.
     * @return Uma String com os dados do filme.
     */
    @Override
    public String toString() {
        return this.getId() + ";" + this.getNome() + ";" + this.getDataLancamento() + ";" + this.getDuracao();
    }
}
