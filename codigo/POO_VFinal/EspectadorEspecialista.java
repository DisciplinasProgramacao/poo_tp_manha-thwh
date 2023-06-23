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
     * Adiciona um comentário a uma mídia.
     * O cliente precisa ter assistido pelo menos 5 séries no último mês para adicionar um comentário.
     * 
     * @param midia      A mídia à qual o comentário será adicionado.
     * @param comentario O comentário a ser adicionado.
     * @throws InvalidParameterException Se o cliente não tiver assistido pelo menos 5 séries no último mês,
     *                                   ou se o cliente não tiver assistido a mídia em questão.
     */
    @Override
    public void adicionarComentario(Midia midia, String comentario) throws InvalidParameterException {
        LocalDate umMesAtras = LocalDate.now().minus(1, ChronoUnit.MONTHS);

        // Verifica se o cliente assistiu pelo menos 5 séries no último mês
        long seriesAssistidasNoUltimoMes = super.getDatasSeriesAssistidas().stream()
                .filter(data -> data.isAfter(umMesAtras))
                .count();

        if (seriesAssistidasNoUltimoMes < 5) {
            throw new InvalidParameterException("A quantidade mínima de episódios vistos para adicionar um comentário precisa ser 5");
        } else if (super.getJaAssistida().contains(midia)) {
            comentarios.put(midia, comentario);
        } else {
            throw new InvalidParameterException("Não é possível adicionar um comentário a uma série que você não assistiu");
        }
    }

    /**
     * Retorna os comentários feitos pelo espectador especialista.
     *
     * @return Um mapa contendo as mídias como chave e os comentários como valor.
     */
    public Map<Midia, String> getComentarios() {
        return this.comentarios;
    }
}