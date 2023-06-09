import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import exceptions.EspectadorException;

/**
 * Classe abstrata que representa um Espectador Especialista.
 * Estende a classe Espectador e implementa a interface Especialista.
 */
public abstract class EspectadorEspecialista extends Espectador implements Especialista {
    private Map<Midia, String> comentarios;

    /**
     * Construtor da classe EspectadorEspecialista.
     *
     * @param nome       O nome do espectador especialista
     * @param login      O login do espectador especialista
     * @param senha      A senha do espectador especialista
     * @param profissional O tipo de profissional do espectador especialista
     * @throws EspectadorException Se ocorrer um erro ao criar o espectador especialista
     */
    public EspectadorEspecialista(String nome, String login, String senha, Profissional profissional) throws EspectadorException {
        super(nome, login, senha);
        this.comentarios = new HashMap<>();
    }

    /**
     * Adiciona um comentário sobre uma determinada mídia.
     *
     * @param midia      A mídia sobre a qual o comentário será feito
     * @param comentario O comentário a ser adicionado
     * @throws InvalidParameterException Se um parâmetro inválido for fornecido
     */
    public void comentar(Midia midia, String comentario) throws InvalidParameterException {
        LocalDate umMesAtras = LocalDate.now().minus(1, ChronoUnit.MONTHS);

        // Verifica se o espectador assistiu pelo menos 5 séries no último mês
        long seriesAssistidasNoUltimoMes = super.getDatasSeriesAssistidas().stream()
                .filter(data -> data.isAfter(umMesAtras))
                .count();

        if (seriesAssistidasNoUltimoMes < 5) {
            throw new InvalidParameterException("A quantidade mínima de séries assistidas para adicionar um comentário é de 5");
        } else if (super.getJaAssistida().contains(midia)) {
            comentarios.put(midia, comentario);
        } else {
            throw new InvalidParameterException("Não é possível adicionar um comentário a uma mídia que você não assistiu");
        }
    }

    /**
     * Obtém um mapa contendo as mídias e seus respectivos comentários.
     *
     * @return O mapa contendo as mídias e seus comentários
     */
    public Map<Midia, String> getComentarios() {
        return new LinkedHashMap<>(comentarios);
    }
}
