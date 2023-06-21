import java.security.InvalidParameterException;
import java.time.LocalDate;

/**
 * A classe Filme representa um filme e herda da classe Midia.
 */
public class Filme extends Midia {

    private double duracao;

    /**
     * Construtor padr�o da classe Filme.
     */
    public Filme() {
        super();
    }


    /**
     * Construtor da classe Filme.
     *
     * @param id             Identificador �nico do filme.
     * @param nome           Nome do filme.
     * @param dataLancamento Data de lan�amento do filme.
     * @param duracao        Dura��o do filme em minutos.
     * @param genero         G�nero do filme.
     * @param idioma         Idioma do filme.
     * @throws InvalidParameterException
     */
    public Filme(int id, String nome, LocalDate dataLancamento, double duracao, int genero, int idioma) throws InvalidParameterException {
        super(id, nome, dataLancamento, genero, idioma, true);
        if (duracao > 1) {
            this.duracao = duracao;
        }
        else {
            throw new InvalidParameterException("!!! A DURA��O DO FILME N�O PODE SER MENOR QUE 1 MINUTO !!!");
        }
    }

    /**
     * Retorna a dura��o do filme.
     *
     * @return A dura��o do filme em minutos.
     */
    public double getDuracao() {
        return this.duracao;
    }

    /**
     * Obt�m o ID do filme.
     *
     * @return O ID do filme.
     */
    public int getID_Filme() {
        return this.getId();
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
