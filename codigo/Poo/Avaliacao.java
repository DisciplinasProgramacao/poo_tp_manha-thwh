import java.security.InvalidParameterException;

/**
 * Classe que representa uma avalia��o feita por um espectador para uma m�dia.
 */
public class Avaliacao {
    private Espectador espectador;
    private Midia midia;
    private double nota;

    /**
     * Construtor da classe Avaliacao.
     *
     * @param espectador O espectador que fez a avalia��o.
     * @param midia      A m�dia avaliada.
     * @param nota       A nota atribu�da � m�dia.
     */
    public Avaliacao(Espectador espectador, Midia midia, double nota) {
        this.espectador = espectador;
        this.midia = midia;
        setNota(nota); // Utiliza o m�todo setter para validar a nota
    }

    /**
     * Obt�m o espectador que fez a avalia��o.
     *
     * @return O espectador.
     */
    public Espectador getEspectador() {
        return espectador;
    }

    /**
     * Define o espectador que fez a avalia��o.
     *
     * @param espectador O espectador.
     */
    public void setEspectador(Espectador espectador) {
        this.espectador = espectador;
    }

    /**
     * Obt�m a m�dia avaliada.
     *
     * @return A m�dia.
     */
    public Midia getMidia() {
        return midia;
    }

    /**
     * Define a m�dia avaliada.
     *
     * @param midia A m�dia.
     */
    public void setMidia(Midia midia) {
        this.midia = midia;
    }

    /**
     * Obt�m a nota atribu�da � m�dia.
     *
     * @return A nota.
     */
    public double getNota() {
        return nota;
    }

    /**
     * Define a nota atribu�da � m�dia. A nota deve estar entre 0 e 5.
     *
     * @param nota A nota.
     * @throws InvalidParameterException Exce��o lan�ada se a nota estiver fora do intervalo v�lido.
     */
    public void setNota(double nota) throws InvalidParameterException {
        if (nota < 0 || nota > 5) {
            throw new InvalidParameterException("<< A NOTA DEVE SER ENTRE 0 A 5 >>");
        }
        this.nota = nota;
    }
}



   

