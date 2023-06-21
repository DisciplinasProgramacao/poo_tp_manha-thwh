
import java.security.InvalidParameterException;
import java.time.LocalDate;

/**
 * A classe Serie representa uma série de TV e herda da classe Midia.
 * Implementa a interface Serializable para permitir a gravação de objetos da classe em arquivos.
 */
public class Serie extends Midia {

    private int quantidadeEpisodios;

    /**
     * Construtor padrão.
     */
    public Serie() {
        super();
    }

    /**
     * Construtor da classe Serie.
     *
     * @param id                 Identificador único da série.
     * @param nome               Nome da série.
     * @param dataLancamento     Data de lançamento da série.
     * @param quantidadeEpisodios Quantidade de episódios da série.
     * @throws InvalidParameterException
     */
    public Serie(int id, String nome, LocalDate dataLancamento, int quantidadeEpisodios) throws InvalidParameterException {
        super(id, nome, dataLancamento);
        if (quantidadeEpisodios > 0) {
            this.quantidadeEpisodios = quantidadeEpisodios;
        }
        else {
            throw new InvalidParameterException("!!! A QUANTIDADE DE EPISÓDIOS NÃO PODE SER 0 !!!");
        }
    }

    /**
     * Retorna a quantidade de episódios da série.
     *
     * @return A quantidade de episódios.
     */
    @Override
    public int getQuantidadeEpisodios() {
        return this.quantidadeEpisodios;
    }

    /**
     * Obtém o ID da série.
     *
     * @return O ID da série.
     */
    public int getID_Serie() {
        return this.getId();
    }

    /**
     * Retorna uma String com os dados da série.
     *
     * @return Uma String com os dados da série.
     */
    @Override
    public String toString() {
        return this.getId() + ";" + this.getNome() + ";" + this.getDataLancamento();
    }

}

