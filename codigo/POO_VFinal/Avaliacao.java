import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa uma avaliação feita por um espectador para uma mídia.
 */
public class Avaliacao {
    private Espectador espectador;
    private Midia midia;
    private double nota;
    private Map<Espectador, String> comentarios;

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
        this.nota = nota;
        this.comentarios = new HashMap<>();
    }

    /**
     * Construtor da classe Avaliacao.
     *
     * @param espectador O espectador que fez a avaliação.
     * @param midia      A mídia avaliada.
     * @param nota       A nota atribuída à mídia.
     * @param comentario O comentário adicionado à avaliação.
     */
    public Avaliacao(Espectador espectador, Midia midia, double nota, String comentario) {
        this(espectador, midia, nota);
        this.addComentario(espectador, comentario);
    }

    private void addComentario(Espectador espectador, String comentario) {
        this.comentarios.put(espectador, comentario);
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
        return this.midia;
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
        return this.nota;
    }

    /**
     * Define a nota atribuída à mídia. A nota deve estar entre 0 e 5.
     *
     * @param nota A nota.
     * @throws InvalidParameterException Exceção lançada se a nota estiver fora do intervalo válido.
     */
    public void setNota(double nota) throws InvalidParameterException {
        if (nota < 0 || nota > 5) {
            throw new InvalidParameterException("A nota deve estar entre 0 e 5.");
        }
        this.nota = nota;
    }
}

   
