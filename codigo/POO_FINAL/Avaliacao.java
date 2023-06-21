import java.security.InvalidParameterException;

/**
 * Classe que representa uma avaliação feita por um espectador para uma mídia.
 */
public class Avaliacao {
    private Espectador espectador;
    private Midia midia;
    private double nota;

    /**
     * Construtor da classe Avaliacao.
     *
     * @param espectador O espectador que fez a avaliação.
     * @param midia      A mídia avaliada.
     * @param nota       A nota atribuída à mídia.
     */
    public Avaliacao(Espectador espectador, Midia midia, double nota) {
        this.espectador = espectador;
        this.midia = midia;
        setNota(nota); // Utiliza o método setter para validar a nota
    }

    /**
     * Obtém o espectador que fez a avaliação.
     *
     * @return O espectador.
     */
    public Espectador getEspectador() {
        return espectador;
    }

    /**
     * Define o espectador que fez a avaliação.
     *
     * @param espectador O espectador.
     */
    public void setEspectador(Espectador espectador) {
        this.espectador = espectador;
    }

    /**
     * Obtém a mídia avaliada.
     *
     * @return A mídia.
     */
    public Midia getMidia() {
        return midia;
    }

    /**
     * Define a mídia avaliada.
     *
     * @param midia A mídia.
     */
    public void setMidia(Midia midia) {
        this.midia = midia;
    }

    /**
     * Obtém a nota atribuída à mídia.
     *
     * @return A nota.
     */
    public double getNota() {
        return nota;
    }

    /**
     * Define a nota atribuída à mídia. A nota deve estar entre 0 e 5.
     *
     * @param nota A nota.
     * @throws InvalidParameterException Exceção lançada se a nota estiver fora do intervalo válido.
     */
    public void setNota(double nota) throws InvalidParameterException {
        if (nota < 0 || nota > 5) {
            throw new InvalidParameterException("<< A NOTA DEVE SER ENTRE 0 A 5 >>");
        }
        this.nota = nota;
    }
}



   

