import java.security.InvalidParameterException;
import java.time.LocalDate;

/**
 * A classe Filme representa um filme e herda da classe Midia.
 */
public class Filme extends Midia {

    private double duracao;

    /**
     * Construtor padrão da classe Filme.
     */
    public Filme() {
        super();
    }

    /**
     * Construtor da classe Filme.
     *
     * @param id             Identificador único do filme.
     * @param nome           Nome do filme.
     * @param dataLancamento Data de lançamento do filme.
     * @param duracao        Duração do filme em minutos.
     * @param genero         Gênero do filme.
     * @param idioma         Idioma do filme.
     * @throws InvalidParameterException se a duração do filme for menor ou igual a 1 minuto.
     */
    public Filme(int id, String nome, LocalDate dataLancamento, double duracao, int genero, int idioma) throws InvalidParameterException {
        super(id, nome, dataLancamento, genero, idioma, true);
        if (duracao > 1) {
            this.duracao = duracao;
        } else {
            throw new InvalidParameterException("A DURAÇÃO DO FILME NÃO PODE SER MENOR OU IGUAL A 1 MINUTO!");
        }
    }

    /**
     * Retorna a duração do filme.
     *
     * @return A duração do filme em minutos.
     */
    public double getDuracao() {
        return this.duracao;
    }

    /**
     * Retorna uma String com os dados do filme.
     *
     * @return Uma String com os dados do filme.
     */
    @Override
    public String toString() {
        return this.getId() + ";" + this.getNome() + ";" + this.getDataLancamento() + ";" + this.getDuracao();
    }

}