
public enum Profissional {
    JORNALISTA,  // Valor do tipo Profissional para Jornalista
    DIRETOR,     // Valor do tipo Profissional para Diretor
    ATOR,        // Valor do tipo Profissional para Ator
    OUTRO_PROFISSIONAL;  // Valor do tipo Profissional para outros profissionais

    /**
     * Verifica se o tipo de profissional não é OUTRO_PROFISSIONAL.
     *
     * @param tipo O tipo de profissional a ser verificado
     * @return true se o tipo de profissional não for OUTRO_PROFISSIONAL, false caso contrário
     */
    public static boolean isProfissional(Profissional tipo) {
        return tipo != OUTRO_PROFISSIONAL;
    }
}
