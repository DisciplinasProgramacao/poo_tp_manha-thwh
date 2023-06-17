import java.time.LocalDate;
import java.util.List;

public enum TipoEspectador {
    REGULAR(false),
    ESPECIALISTA(true),
    PROFISSIONAL(true);

    private boolean autenticado;

    TipoEspectador(boolean autenticado) {
        this.autenticado = autenticado;
    }

    /**
     * Verifica se o espectador está autenticado.
     *
     * @return true se o espectador estiver autenticado, false caso contrário
     */
    public boolean isAutenticado() {
        return autenticado;
    }

    /**
     * Torna o espectador autenticado.
     */
    public void autenticar() {
        autenticado = true;
    }

    /**
     * Torna o espectador não autenticado.
     */
    public void desautenticar() {
        autenticado = false;
    }

    /**
     * Verifica se o espectador é um especialista.
     *
     * @return true se o espectador for um especialista, false caso contrário
     */
    public boolean isEspecialista() {
        return this == ESPECIALISTA;
    }

    /**
     * Verifica se o espectador é um profissional.
     *
     * @return true se o espectador for um profissional, false caso contrário
     */
    public boolean isProfissional() {
        return this == PROFISSIONAL;
    }

    /**
     * Verifica se o espectador pode comentar uma mídia.
     *
     * @param quantidadeMidiasAssistidas A quantidade de mídias assistidas pelo espectador
     * @return true se o espectador puder comentar uma mídia, false caso contrário
     */
    public boolean podeComentarMidia(int quantidadeMidiasAssistidas) {
        if (this == REGULAR) {
            return false;
        } else if (this == ESPECIALISTA) {
            return quantidadeMidiasAssistidas >= 5;
        } else if (this == PROFISSIONAL) {
        	return true;
        }
        return false;
    }

    /**
     * Verifica se o espectador pode avaliar uma mídia.
     *
     * @return true se o espectador puder avaliar uma mídia, false caso contrário
     */
    public boolean podeAvaliarMidia() {
        return this == REGULAR;
    }

    /**
     * Verifica se o espectador é um especialista que pode voltar a ser regular no mês seguinte.
     *
     * @param dataReferencia A data de referência para verificação
     * @param datasSeriesAssistidas A lista de datas de séries assistidas pelo espectador
     * @return true se o espectador especialista puder voltar a ser regular no mês seguinte, false caso contrário
     */
    public boolean podeVoltarSerRegularNoMes(LocalDate dataReferencia, List<LocalDate> datasSeriesAssistidas) {
        if (this == ESPECIALISTA && dataReferencia.getMonthValue() != datasSeriesAssistidas.get(datasSeriesAssistidas.size() - 1).getMonthValue()) {
            return true;
        }
        return false;
    }
}
