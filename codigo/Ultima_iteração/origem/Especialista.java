import java.security.InvalidParameterException;
import java.util.Map;

/**
 * Interface que define um Especialista.
 */
public interface Especialista {

    /**
     * Comenta sobre uma determinada mídia.
     *
     * @param midia      A mídia sobre a qual o comentário será feito
     * @param comentario O comentário a ser adicionado
     * @throws InvalidParameterException Se um parâmetro inválido for fornecido
     */
    public void Comentar(Midia midia, String comentario) throws InvalidParameterException;

    /**
     * Obtém um mapa de mídias e seus respectivos comentários.
     *
     * @return O mapa contendo as mídias e seus comentários
     */
    public Map<Midia, String> getComentarios();
}
