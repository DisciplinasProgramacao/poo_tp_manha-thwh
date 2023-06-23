
/**
 * Enumerador para os idiomas dispon�veis.
 */
enum Idioma {
    INGLES,     // Valor do tipo Idioma para Ingl�s
    PORTUGUES,  // Valor do tipo Idioma para Portugu�s
    ESPANHOL,   // Valor do tipo Idioma para Espanhol
    JAPONES,    // Valor do tipo Idioma para Japon�s
    COREANO,    // Valor do tipo Idioma para Coreano
    RUSSO,      // Valor do tipo Idioma para Russo
    MANDARIM,   // Valor do tipo Idioma para Mandarim
    ALEMO,     // Valor do tipo Idioma para Alem�o
    ARABE       // Valor do tipo Idioma para �rabe
;

	/**
     * Verifica se o Idioma é igual ao Idioma fornecido, ignorando diferenças de caixa.
     *
     * @param genero O Idioma a ser comparado.
     * @return true se o Idioma for igual ao Idioma fornecido, ignorando diferenças de caixa, false caso contrário.
     */
    public boolean equalsIgnoreCase(String idioma) {
        return this.name().equalsIgnoreCase(idioma);
    }

    /**
     * Retorna o valor do Idioma em letras minúsculas.
     *
     * @return O valor do Idioma em letras minúsculas.
     */
    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}