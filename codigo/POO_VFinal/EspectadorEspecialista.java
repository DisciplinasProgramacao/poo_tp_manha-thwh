import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import exceptions.EspectadorException;

public class EspectadorEspecialista extends Espectador implements Especialista {
    private Map<Midia, String> comentarios;

    public EspectadorEspecialista(String nome, String login, String senha) throws EspectadorException {
        super(nome, login, senha);
        this.comentarios = new HashMap<>();
    }

    /**
     * Adiciona um coment�rio a uma m�dia.
     * O cliente precisa ter assistido pelo menos 5 s�ries no �ltimo m�s para adicionar um coment�rio.
     * 
     * @param midia      A m�dia � qual o coment�rio ser� adicionado.
     * @param comentario O coment�rio a ser adicionado.
     * @throws InvalidParameterException Se o cliente n�o tiver assistido pelo menos 5 s�ries no �ltimo m�s,
     *                                   ou se o cliente n�o tiver assistido a m�dia em quest�o.
     */
    @Override
    public void adicionarComentario(Midia midia, String comentario) throws InvalidParameterException {
        LocalDate umMesAtras = LocalDate.now().minus(1, ChronoUnit.MONTHS);

        // Verifica se o cliente assistiu pelo menos 5 s�ries no �ltimo m�s
        long seriesAssistidasNoUltimoMes = super.getDatasSeriesAssistidas().stream()
                .filter(data -> data.isAfter(umMesAtras))
                .count();

        if (seriesAssistidasNoUltimoMes < 5) {
            throw new InvalidParameterException("A quantidade m�nima de epis�dios vistos para adicionar um coment�rio precisa ser 5");
        } else if (super.getJaAssistida().contains(midia)) {
            comentarios.put(midia, comentario);
        } else {
            throw new InvalidParameterException("N�o � poss�vel adicionar um coment�rio a uma s�rie que voc� n�o assistiu");
        }
    }

    /**
     * Retorna os coment�rios feitos pelo espectador especialista.
     *
     * @return Um mapa contendo as m�dias como chave e os coment�rios como valor.
     */
    public Map<Midia, String> getComentarios() {
        return this.comentarios;
    }
}