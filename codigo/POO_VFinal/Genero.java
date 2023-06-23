/**
 * Enumerador para os gêneros disponíveis.
 */
enum Genero {
    ACAO,         // Valor do tipo Genero para Ação
    ANIME,        // Valor do tipo Genero para Anime
    AVENTURA,     // Valor do tipo Genero para Aventura
    COMEDIA,      // Valor do tipo Genero para Comédia
    DOCUMENTARIO, // Valor do tipo Genero para Documentário
    DRAMA,        // Valor do tipo Genero para Drama
    POLICIAL,     // Valor do tipo Genero para Policial
    ROMANCE,      // Valor do tipo Genero para Romance
    SUSPENSE;     // Valor do tipo Genero para Suspense

    /**
     * Verifica se o gênero é igual ao gênero fornecido, ignorando diferenças de caixa.
     *
     * @param genero O gênero a ser comparado.
     * @return true se o gênero for igual ao gênero fornecido, ignorando diferenças de caixa, false caso contrário.
     */
    public boolean equalsIgnoreCase(String genero) {
        return this.name().equalsIgnoreCase(genero);
    }

    /**
     * Retorna o valor do gênero em letras minúsculas.
     *
     * @return O valor do gênero em letras minúsculas.
     */
    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}
