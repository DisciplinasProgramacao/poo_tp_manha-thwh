import java.security.InvalidParameterException;
import java.util.Map;


public interface Especialista {
    
	
	public void Comentar(Midia midia, String comentario) throws InvalidParameterException;

    public Map<Midia, String> getComentarios();

	

}