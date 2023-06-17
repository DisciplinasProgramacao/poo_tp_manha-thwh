import java.util.HashMap;

public class Autenticador {
    private HashMap<String, Espectador> espectadores;

    public Autenticador() {
        this.espectadores = new HashMap<String, Espectador>();
    }

    /**
     * Autentica um espectador na plataforma.
     * 
     * @param login Nome de usu�rio do espectador.
     * @param senha Senha do espectador.
     * @return Retorna true se a autentica��o for bem-sucedida, caso contr�rio, retorna false.
     */
    public boolean autenticarEspectador(String login, String senha) {
        Espectador espectador = espectadores.get(login);
        return espectador != null && espectador.getSenha().equals(senha);
    }

    /**
     * Cria um novo espectador na plataforma.
     * 
     * @param login Nome de usu�rio do novo espectador.
     * @param senha Senha do novo espectador.
     * @param nome  Nome do novo espectador.
     * @return Retorna true se a cria��o do espectador for bem-sucedida, caso contr�rio, retorna false.
     */
    public boolean criarEspectador(String login, String senha, String nome) {
        if (espectadores.containsKey(login)) {
            return false; // J� existe um espectador com esse login
        }

        Espectador novoEspectador = new Espectador(login, senha, nome);
        espectadores.put(login, novoEspectador);
        return true;
    }
}