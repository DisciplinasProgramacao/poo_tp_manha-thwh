import java.security.InvalidParameterException;
import java.util.Map;

/**
 * A interface Especialista define os métodos para adicionar comentários a uma Midia
 * e para obter os comentários associados às Mídias.
 */
public interface Especialista {
    
    /**
     * Adiciona um comentário à Midia especificada.
     *
     * @param midia a Midia à qual o comentário será adicionado
     * @param comentario o comentário a ser adicionado
     * @throws InvalidParameterException se a Midia ou o comentário forem inválidos
     */
    public void adicionarComentario(Midia midia, String comentario) throws InvalidParameterException;
    
    /**
     * Obtém um mapa contendo todas as Mídias associadas aos seus comentários correspondentes.
     *
     * @return um mapa de Mídias e seus comentários
     */
    public Map<Midia, String> getComentarios();
}
