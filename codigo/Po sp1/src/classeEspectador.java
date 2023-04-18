import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class classeEspectador {
    private String nome;
    private String login;
    private String senha;

    public classeEspectador(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public void salvarUsuario() {
        // implemente o m�todo de salvar usu�rio aqui
    }

    public static List<classeEspectador> lerEspectador() {
        List<classeEspectador> espectadores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\wande\\Downloads\\POO_sprint1\\Po sp1\\src))) {
            String linha = br.readLine();
            while (linha != null) {
                String[] campos = linha.split(";");
                String nome = campos[0];
                String login = campos[1];
                String senha = campos[2];
                classeEspectador espectador = new classeEspectador(nome, login, senha);
                espectadores.add(espectador);
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return espectadores;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getLogin() {
        return login;
    }
}

