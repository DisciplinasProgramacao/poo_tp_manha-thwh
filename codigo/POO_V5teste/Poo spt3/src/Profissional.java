
public enum Profissional {
    JORNALISTA,
    DIRETOR,
    ATOR,
    OUTRO_PROFISSIONAL;

    public static boolean isProfissional(Profissional tipo) {
        return tipo != OUTRO_PROFISSIONAL;
    }
}