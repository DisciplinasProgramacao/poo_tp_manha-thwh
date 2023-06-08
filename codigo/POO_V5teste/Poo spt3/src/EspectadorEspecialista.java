import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import exceptions.EspectadorException;

public abstract class EspectadorEspecialista extends Espectador implements Especialista {
    private Map<Midia, String> comentarios;

    public EspectadorEspecialista(String nome, String login, String senha, Profissional Profissional) throws EspectadorException {
        super(nome, login, senha, Profissional);
        this.comentarios = new HashMap<>();
    }

    public void comentar(Midia midia, String comentario) throws InvalidParameterException {
        LocalDate umMesAtras = LocalDate.now().minus(1, ChronoUnit.MONTHS);

        // Verifica se o cliente assistiu pelo menos 5 séries no último mês
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

    public Map<Midia, String> getComentarios() {
        return new LinkedHashMap<>(comentarios);
    }
}

