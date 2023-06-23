import java.security.InvalidParameterException;
import java.util.Map;

/**
 * A interface Especialista define os m�todos para adicionar coment�rios a uma Midia
 * e para obter os coment�rios associados �s M�dias.
 */
public interface Especialista {
    
    /**
     * Adiciona um coment�rio � Midia especificada.
     *
     * @param midia a Midia � qual o coment�rio ser� adicionado
     * @param comentario o coment�rio a ser adicionado
     * @throws InvalidParameterException se a Midia ou o coment�rio forem inv�lidos
     */
    public void adicionarComentario(Midia midia, String comentario) throws InvalidParameterException;
    
    /**
     * Obt�m um mapa contendo todas as M�dias associadas aos seus coment�rios correspondentes.
     *
     * @return um mapa de M�dias e seus coment�rios
     */
    public Map<Midia, String> getComentarios();
}
