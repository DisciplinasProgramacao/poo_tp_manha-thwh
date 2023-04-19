import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe que representa um espectador de um serviço de streaming de séries.
 */
public class classeEspectador {
    private String nome; // Nome do espectador
    private String login; // Login do espectador
    private String senha; // Senha do espectador

    /**
     * Construtor da classe classeEspectador.
     * @param nome Nome do espectador.
     * @param login Login do espectador.
     * @param senha Senha do espectador.
     */
    public classeEspectador(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    /**
     * Método para salvar o usuário.
     * Implemente o método de salvar usuário aqui.
     */
    public void salvarUsuario() {
        // Implementação do método de salvar usuário
    }

    /**
     * Método para ler os espectadores de um arquivo CSV.
     * @param caminhoArquivo Caminho do arquivo CSV contendo os dados dos espectadores.
     * @return Lista de objetos classeEspectador lidos do arquivo.
     */
    public static List<classeEspectador> lerEspectador(String caminhoArquivo) {
        List<classeEspectador> espectadores = new ArrayList<>();
        try {
            File file = new File(caminhoArquivo);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");
                String nome = campos[0];
                String login = campos[1];
                String senha = campos[2];
                classeEspectador espectador = new classeEspectador(nome, login, senha);
                espectadores.add(espectador);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
        return espectadores;
    }

    /**
     * Método para obter o nome do espectador.
     * @return Nome do espectador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método para obter a senha do espectador.
     * @return Senha do espectador.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Método para obter o login do espectador.
     * @return Login do espectador.
     */
    public String getLogin() {
        return login;
    }
}


