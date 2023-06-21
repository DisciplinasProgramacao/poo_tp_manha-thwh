
import java.security.InvalidParameterException;
import java.time.LocalDate;

/**
 * A classe Serie representa uma s�rie de TV e herda da classe Midia.
 * Implementa a interface Serializable para permitir a grava��o de objetos da classe em arquivos.
 */
public class Serie extends Midia {

    private int quantidadeEpisodios;

    /**
     * Construtor padr�o.
     */
    public Serie() {
        super();
    }

    /**
     * Construtor da classe Serie.
     *
     * @param id                 Identificador �nico da s�rie.
     * @param nome               Nome da s�rie.
     * @param dataLancamento     Data de lan�amento da s�rie.
     * @param quantidadeEpisodios Quantidade de epis�dios da s�rie.
     * @throws InvalidParameterException
     */
    public Serie(int id, String nome, LocalDate dataLancamento, int quantidadeEpisodios) throws InvalidParameterException {
        super(id, nome, dataLancamento);
        if (quantidadeEpisodios > 0) {
            this.quantidadeEpisodios = quantidadeEpisodios;
        }
        else {
            throw new InvalidParameterException("!!! A QUANTIDADE DE EPIS�DIOS N�O PODE SER 0 !!!");
        }
    }

    /**
     * Retorna a quantidade de epis�dios da s�rie.
     *
     * @return A quantidade de epis�dios.
     */
    @Override
    public int getQuantidadeEpisodios() {
        return this.quantidadeEpisodios;
    }

    /**
     * Obt�m o ID da s�rie.
     *
     * @return O ID da s�rie.
     */
    public int getID_Serie() {
        return this.getId();
    }

    /**
     * Retorna uma String com os dados da s�rie.
     *
     * @return Uma String com os dados da s�rie.
     */
    @Override
    public String toString() {
        return this.getId() + ";" + this.getNome() + ";" + this.getDataLancamento();
    }

}

