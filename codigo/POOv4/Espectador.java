import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Espectador implements regular, especialista {
    private String nome;
    private String login;
    private String senha;
    private List<Avaliacao> avaliacoes;

    public Espectador(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.avaliacoes = new ArrayList<>();
    }

    public Espectador(String string, String string2) {
		
	}

	public void salvarUsuario(String caminhoArquivo) {
        try {
            FileWriter fileWriter = new FileWriter(caminhoArquivo, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            String linha = nome + ";" + login + ";" + senha;
            writer.write(linha);
            writer.newLine();

            writer.close();
            System.out.println("Usu�rio salvo com sucesso no arquivo: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usu�rio no arquivo: " + e.getMessage());
        }
    }

    public static List<Espectador> lerEspectador(String caminhoArquivo) {
        List<Espectador> espectadores = new ArrayList<>();
        try {
            File file = new File(caminhoArquivo);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");
                if (campos.length >= 3) {
                    String nome = campos[0];
                    String login = campos[1];
                    String senha = campos[2];
                    Espectador espectador = new Espectador(nome, login, senha);
                    espectadores.add(espectador);
                } else {
                    System.out.println("Formato inv�lido no arquivo: " + caminhoArquivo);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo n�o encontrado: " + e.getMessage());
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

    @Override
    public void avaliarMidia(Midia midia, int nota) {
        if (!verificarAvaliacaoExistente(midia)) {
            Avaliacao avaliacao = new Avaliacao(midia, nota);
            avaliacoes.add(avaliacao);
            midia.adicionarAvaliacao(avaliacao);
            System.out.println("M�dia avaliada com sucesso!");
        } else {
            System.out.println("Voc� j� avaliou essa m�dia anteriormente.");
        }
    }

    @Override
    public void avaliarMidia(Midia midia, int nota, String comentario) {
        if (!verificarAvaliacaoExistente(midia)) {
            Avaliacao avaliacao = new Avaliacao(midia, nota, comentario);
            avaliacoes.add(avaliacao);
            midia.adicionarAvaliacao(avaliacao);
            System.out.println("M�dia avaliada com sucesso!");
        } else {
            System.out.println("Voc� j� avaliou essa m�dia anteriormente.");
        }
    }

    @Override
    public boolean isClienteEspecialista() {
        int avaliacoesMesAnterior = contarAvaliacoesMesAnterior();
        return avaliacoesMesAnterior >= 5;
    }

    private boolean verificarAvaliacaoExistente(Midia midia) {
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getMidia().equals(midia)) {
                return true;
            }
        }
        return false;
    }

    private int contarAvaliacoesMesAnterior() {
        // L�gica para contar as avalia��es do m�s anterior
        // Implemente de acordo com a sua necessidade
        return 0;
    }

    public void avaliarCatalogo(Catalogo catalogo, int nota) {
        List<Midia> midias = catalogo.getMidias();
        for (Midia midia : midias) {
            avaliarMidia(midia, nota);
        }
    }

    public void avaliarCatalogo(Catalogo catalogo, int nota, String comentario) {
        List<Midia> midias = catalogo.getMidias();
        for (Midia midia : midias) {
            avaliarMidia(midia, nota, comentario);
        }
    }

    public static Catalogo getCatalogo() throws FileNotFoundException, ParseException {
        Catalogo catalogo = new Catalogo();
        File file = new File("caminho_do_arquivo_csv"); // Substitua pelo caminho correto do arquivo CSV
        Scanner scanner = new Scanner(file);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Ler o arquivo CSV e criar as m�dias correspondentes
        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(";");
            int idMidia = Integer.parseInt(campos[0]);
            String nome = campos[1];
            Date dataLancamento = dateFormat.parse(campos[2]);
            String genero = campos[3];
            String idioma = campos[4];
            int duracaoOuEpisodios = Integer.parseInt(campos[5]); // Dura��o para filmes, epis�dios para s�ries

            // Verificar se � uma s�rie ou um filme
            Midia midia;
            if (duracaoOuEpisodios > 0) {
                // � uma s�rie
                midia = new Serie(idMidia, nome, genero, idioma, dataLancamento, duracaoOuEpisodios);
            } else {
                // � um filme
                midia = new Filme(idMidia, nome, genero, idioma, dataLancamento, -duracaoOuEpisodios);
            }

            catalogo.adicionarMidia(midia);
        }

        scanner.close();
        return catalogo;
    }
}