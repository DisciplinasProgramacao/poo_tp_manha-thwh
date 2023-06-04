import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Espectador {
    private String nome; // Nome do espectador
    private String login; // Login do espectador
    private String senha; // Senha do espectador
    private Map<Integer, Integer> notasMidia; // Mapa para armazenar as notas atribuídas a cada mídia
    private Map<LocalDate, Integer> avaliacoesPorMes;

    public Espectador(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.notasMidia = new HashMap<>();
        this.avaliacoesPorMes = new HashMap<>();
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

    public void atribuirNota(int idMidia, int nota) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate mesAnterior = LocalDate.now().minusMonths(1);

        if (avaliacoesPorMes.getOrDefault(mesAnterior, 0) >= 5) {
            System.out.println("Espectador é um especialista.");
            if (notasMidia.containsKey(idMidia)) {
                System.out.println("Espectador já avaliou esta mídia anteriormente.");
                return;
            }
        } else {
            if (notasMidia.containsKey(idMidia)) {
                System.out.println("Espectador já avaliou esta mídia anteriormente.");
                return;
            }

            if (nota < 1 || nota > 5) {
                System.out.println("A nota deve estar entre 1 e 5.");
                return;
            }
        }

        avaliacoesPorMes.put(dataAtual, avaliacoesPorMes.getOrDefault(dataAtual, 0) + 1);
        notasMidia.put(idMidia, nota);
        System.out.println("Avaliação atribuída com sucesso.");
    }

    public double calcularMediaNotas(int idMidia) {
        int totalNotas = 0;
        int quantidadeNotas = 0;

        for (Map.Entry<Integer, Integer> entry : notasMidia.entrySet()) {
            int id = entry.getKey();
            int nota = entry.getValue();

            if (id == idMidia) {
                totalNotas += nota;
                quantidadeNotas++;
            }
        }

        if (quantidadeNotas == 0) {
            System.out.println("Nenhuma nota atribuída para a mídia com ID " + idMidia);
            return 0.0;
        }

        return (double) totalNotas / quantidadeNotas;
    }

    public int getAvaliacoesNoMes(LocalDate mes) {
        return avaliacoesPorMes.getOrDefault(mes, 0);
    }

    public static void salvarEspectadores(String caminhoArquivo, Map<String, Espectador> espectadores) {
        try {
            FileWriter fileWriter = new FileWriter(caminhoArquivo);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (Map.Entry<String, Espectador> entry : espectadores.entrySet()) {
                String login = entry.getKey();
                Espectador espectador = entry.getValue();
                String linha = espectador.getNome() + ";" + login + ";" + espectador.getSenha();
                writer.write(linha);
                writer.newLine();
            }

            writer.close();
            System.out.println("Espectadores salvos com sucesso no arquivo: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar espectadores no arquivo: " + e.getMessage());
        }
    }

    public static Map<String, Espectador> lerEspectadores(String caminhoArquivo) {
        Map<String, Espectador> espectadores = new HashMap<>(800000);

        try {
            FileReader fileReader = new FileReader(caminhoArquivo);
            BufferedReader reader = new BufferedReader(fileReader);
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                String nome = campos[0];
                String login = campos[1];
                String senha = campos[2];
                Espectador espectador = new Espectador(nome, login, senha);
                espectadores.put(login, espectador);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return espectadores;
    }
}

