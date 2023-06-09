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

        // Verifica se o cliente assistiu pelo menos 5 s�ries no �ltimo m�s
        long seriesAssistidasNoUltimoMes = super.getDatasSeriesAssistidas().stream()
                .filter(data -> data.isAfter(umMesAtras))
                .count();

        if (seriesAssistidasNoUltimoMes < 5) {
            throw new InvalidParameterException("A quantidade m�nima de s�ries assistidas para adicionar um coment�rio � de 5");
        } else if (super.getJaAssistida().contains(midia)) {
            comentarios.put(midia, comentario);
        } else {
            throw new InvalidParameterException("N�o � poss�vel adicionar um coment�rio a uma m�dia que voc� n�o assistiu");
        }
    }

    public Map<Midia, String> getComentarios() {
        return new LinkedHashMap<>(comentarios);
    }
}

