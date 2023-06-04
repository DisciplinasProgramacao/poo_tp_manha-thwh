import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Filme extends Midia {
    private int duracaoSegundos;
    private int iD_filme;
    private String genero; // G�nero do filme
    private String idioma; // Idioma do filme

    public Filme(int id, String nome, String genero, String idioma, Date dataLancamento, int duracaoSegundos) {
        super(id, nome, idioma, genero, dataLancamento, duracaoSegundos);
        this.duracaoSegundos = duracaoSegundos;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public int getID_Filme() {
        return iD_filme;
    }

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo o filme: " + nome);
        // L�gica espec�fica para reprodu��o de um filme
    }

    public void mostrarDuracao() {
        System.out.println("Dura��o do filme " + nome + ": " + duracaoSegundos + " segundos");
    }

    public static void catalogoFilme(Map<Integer, Filme> filmes) {
        System.out.println("Cat�logo de filmes:");
        for (Filme filme : filmes.values()) {
            System.out.println("ID: " + filme.getID_Filme());
            System.out.println("Nome: " + filme.getNome());
            System.out.println("G�nero: " + filme.getGenero());
            System.out.println("Idioma: " + filme.getIdioma());
            System.out.println("Data de Lan�amento: " + filme.getDataLancamento());
            System.out.println("Dura��o: " + filme.getDuracaoSegundos() + " segundos");
            System.out.println("--------------------------");
        }
    }

    public static Map<Integer, Filme> lerFilmes(String arquivo) throws FileNotFoundException, ParseException {
        Map<Integer, Filme> filmes = new HashMap<>(800000);
        File file = new File(arquivo);
        Scanner scanner = new Scanner(file);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Ignorar a primeira linha (coment�rios)
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(";");
            int id = Integer.parseInt(campos[0]);
            String nome = campos[1];
            Date dataLancamento = dateFormat.parse(campos[2]);
            int duracaoSegundos = Integer.parseInt(campos[3]);
            
            String genero = "Sem G�nero"; // G�nero padr�o
            if (campos.length > 4) {
                genero = campos[4];
            }

            String idioma = "Desconhecido"; // Idioma padr�o
            if (campos.length > 5) {
                idioma = campos[5];
            }

            Filme filme = new Filme(id, nome, genero, idioma, dataLancamento, duracaoSegundos);
            filmes.put(id, filme);
        }

        scanner.close();
        return filmes;
    }

    @Override
    protected int getDuracaoOuEpisodios() {
        return duracaoSegundos;
    }
}

