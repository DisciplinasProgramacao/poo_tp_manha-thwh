
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
     * @param id Identificador �nico da s�rie.
     * @param nome Nome da s�rie.
     * @param dataLancamento Data de lan�amento da s�rie.
     * @param quantidadeEpisodios Quantidade de epis�dios da s�rie.
     * @throws InvalidParameterException
    */
    public Serie(int id, String nome, LocalDate dataLancamento, int quantidadeEpisodios) throws InvalidParameterException {

        super(id, nome, dataLancamento);
        if(quantidadeEpisodios < 1) {
            throw new InvalidParameterException("A quantidade de epis�dios n�o pode ser 0!");
        }
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    /**
     * Construtor alternativo da classe Serie.
     * @param id Identificador �nico da s�rie.
     * @param nome Nome da s�rie.
     * @param string Parametro n�o utilizado.
     * @param string2 Parametro n�o utilizado.
     * @param i Parametro n�o utilizado.
     * @param j Parametro n�o utilizado.
     * @param dataLancamento Data de lan�amento da s�rie.
     * @throws InvalidParameterException
     */
    public Serie(int id, String nome, int genero, int idioma, int quantidadeEps,
            LocalDate dataLancamento) throws InvalidParameterException {
                super(id, nome, dataLancamento, genero, idioma, false);
                this.quantidadeEpisodios = quantidadeEps;
    }

    /**
    * Construtor alternativo da classe Serie.
    * @param id Identificador �nico da s�rie.
    * @param nome Nome da s�rie.
    * @param dataLancamento Data de lan�amento da s�rie.
    * @throws InvalidParameterException
    */
    public Serie(int id, String nome, LocalDate dataDeLancamento) throws InvalidParameterException {  //rever
        super(id, nome, dataDeLancamento);
        this.quantidadeEpisodios = 1;
    }

    /**
     * Retorna a quantidade de epis�dios da s�rie.
     * @return A quantidade de epis�dios.
     */
    @Override
    public int getQuantidadeEpisodios() {
        return this.quantidadeEpisodios;
    }

    public int getID_Serie() {
        return getID_Serie();
    }
    
    
    /**
     * Define a quantidade de epis�dios da s�rie.
     * @param quantidadeEpisodios A quantidade de epis�dios.
     * @throws InvalidParameterException se a quantidade de epis�dios for menor que 2.
     */
    public void setQuantidadeEpisodios(int quantidadeEpisodios) throws InvalidParameterException {
        if(quantidadeEpisodios < 1){
            throw new InvalidParameterException("A quantidade de epis�dios n�o pode ser 0!");
        }
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    /**
     * Retorna uma String com os dados da s�rie.
     * @return Uma String com os dados da s�rie.
     */
    @Override
    public String toString() {
        return this.getId() + ";" + this.getNome() + ";" + this.getDataLancamento();
    }
}
