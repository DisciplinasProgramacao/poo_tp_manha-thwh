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
     * Verifica se o espectador est� autenticado.
     *
     * @return true se o espectador estiver autenticado, false caso contr�rio
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
     * Torna o espectador n�o autenticado.
     */
    public void desautenticar() {
        autenticado = false;
    }

    /**
     * Verifica se o espectador � um especialista.
     *
     * @return true se o espectador for um especialista, false caso contr�rio
     */
    public boolean isEspecialista() {
        return this == ESPECIALISTA;
    }

    /**
     * Verifica se o espectador � um profissional.
     *
     * @return true se o espectador for um profissional, false caso contr�rio
     */
    public boolean isProfissional() {
        return this == PROFISSIONAL;
    }

    /**
     * Verifica se o espectador pode comentar uma m�dia.
     *
     * @param quantidadeMidiasAssistidas A quantidade de m�dias assistidas pelo espectador
     * @return true se o espectador puder comentar uma m�dia, false caso contr�rio
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
     * Verifica se o espectador pode avaliar uma m�dia.
     *
     * @return true se o espectador puder avaliar uma m�dia, false caso contr�rio
     */
    public boolean podeAvaliarMidia() {
        return this == REGULAR;
    }

    /**
     * Verifica se o espectador � um especialista que pode voltar a ser regular no m�s seguinte.
     *
     * @param dataReferencia A data de refer�ncia para verifica��o
     * @param datasSeriesAssistidas A lista de datas de s�ries assistidas pelo espectador
     * @return true se o espectador especialista puder voltar a ser regular no m�s seguinte, false caso contr�rio
     */
    public boolean podeVoltarSerRegularNoMes(LocalDate dataReferencia, List<LocalDate> datasSeriesAssistidas) {
        if (this == ESPECIALISTA && dataReferencia.getMonthValue() != datasSeriesAssistidas.get(datasSeriesAssistidas.size() - 1).getMonthValue()) {
            return true;
        }
        return false;
    }
}
