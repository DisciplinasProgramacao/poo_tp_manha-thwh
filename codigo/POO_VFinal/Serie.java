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
     *
     * @param id                 Identificador único da série.
     * @param nome               Nome da série.
     * @param genero             Gênero da série.
     * @param idioma             Idioma da série.
     * @param qtdEpisodios       Quantidade de episódios da série.
     * @param dataLancamento     Data de lançamento da série.
     */
    public Serie(int id, String nome, int genero, int idioma, int qtdEpisodios, LocalDate dataLancamento) {
        super();
    }

    /**
     * Construtor da classe Serie.
     *
     * @param id                 Identificador único da série.
     * @param nome               Nome da série.
     * @param dataLancamento     Data de lançamento da série.
     * @param quantidadeEpisodios Quantidade de episódios da série.
     * @throws InvalidParameterException se a quantidade de episódios for menor ou igual a 0.
     */
    public Serie(int id, String nome, LocalDate dataLancamento, int quantidadeEpisodios) throws InvalidParameterException {
        super(id, nome, dataLancamento);
        if (quantidadeEpisodios > 0) {
            this.quantidadeEpisodios = quantidadeEpisodios;
        } else {
            throw new InvalidParameterException("A QUANTIDADE DE EPISÓDIOS NÃO PODE SER MENOR OU IGUAL A 0!");
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
     * Define a quantidade de episódios da série.
     *
     * @param quantidadeEpisodios A quantidade de episódios.
     * @throws InvalidParameterException se a quantidade de episódios for menor que 2.
     */
    public void setQuantidadeEpisodios(int quantidadeEpisodios) throws InvalidParameterException {
        if (quantidadeEpisodios < 2) {
            throw new InvalidParameterException("A QUANTIDADE DE EPISÓDIOS NÃO PODE SER MENOR QUE 2!");
        }
        this.quantidadeEpisodios = quantidadeEpisodios;
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
