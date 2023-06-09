
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
     * @param id Identificador único da série.
     * @param nome Nome da série.
     * @param dataLancamento Data de lançamento da série.
     * @param quantidadeEpisodios Quantidade de episódios da série.
     * @throws InvalidParameterException
    */
    public Serie(int id, String nome, LocalDate dataLancamento, int quantidadeEpisodios) throws InvalidParameterException {

        super(id, nome, dataLancamento);
        if(quantidadeEpisodios < 1) {
            throw new InvalidParameterException("A quantidade de episódios não pode ser 0!");
        }
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    /**
     * Construtor alternativo da classe Serie.
     * @param id Identificador único da série.
     * @param nome Nome da série.
     * @param string Parametro não utilizado.
     * @param string2 Parametro não utilizado.
     * @param i Parametro não utilizado.
     * @param j Parametro não utilizado.
     * @param dataLancamento Data de lançamento da série.
     * @throws InvalidParameterException
     */
    public Serie(int id, String nome, int genero, int idioma, int quantidadeEps,
            LocalDate dataLancamento) throws InvalidParameterException {
                super(id, nome, dataLancamento, genero, idioma, false);
                this.quantidadeEpisodios = quantidadeEps;
    }

    /**
    * Construtor alternativo da classe Serie.
    * @param id Identificador único da série.
    * @param nome Nome da série.
    * @param dataLancamento Data de lançamento da série.
    * @throws InvalidParameterException
    */
    public Serie(int id, String nome, LocalDate dataDeLancamento) throws InvalidParameterException {  //rever
        super(id, nome, dataDeLancamento);
        this.quantidadeEpisodios = 1;
    }

    /**
     * Retorna a quantidade de episódios da série.
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
        return getID_Serie();
    }
    
    
    /**
     * Define a quantidade de episódios da série.
     * @param quantidadeEpisodios A quantidade de episódios.
     * @throws InvalidParameterException se a quantidade de episódios for menor que 2.
     */
    public void setQuantidadeEpisodios(int quantidadeEpisodios) throws InvalidParameterException {
        if(quantidadeEpisodios < 1){
            throw new InvalidParameterException("A quantidade de episódios não pode ser 0!");
        }
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    /**
     * Retorna uma String com os dados da série.
     * @return Uma String com os dados da série.
     */
    @Override
    public String toString() {
        return this.getId() + ";" + this.getNome() + ";" + this.getDataLancamento();
    }
}
