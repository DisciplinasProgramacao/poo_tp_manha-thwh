import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Filme extends Midia {
    private int duracaoSegundos;
    private int iD_filme;

    public Filme(int id, String nome, String genero, String idioma, Date dataLancamento, int duracaoSegundos) {
        super(id, nome, genero, idioma, dataLancamento);
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
        // Lógica específica para reprodução de um filme
    }

    public void mostrarDuracao() {
        System.out.println("Duração do filme " + nome + ": " + duracaoSegundos + " segundos");
    }

    public static void catalogoFilme(ArrayList<Filme> filmes) {
        System.out.println("Catálogo de filmes:");
        for (Filme filme : filmes) {
            System.out.println("ID: " + filme.getID_Filme());
            System.out.println("Nome: " + filme.getNome());
            System.out.println("Gênero: " + filme.getGenero());
            System.out.println("Idioma: " + filme.getIdioma());
            System.out.println("Data de Lançamento: " + filme.getDataLancamento());
            System.out.println("Duração: " + filme.getDuracaoSegundos() + " segundos");
            System.out.println("--------------------------");
        }
    }
    public static ArrayList<Filme> lerFilmes(String arquivo) throws FileNotFoundException, ParseException {
        ArrayList<Filme> midias = new ArrayList<>();
        File file = new File("POO_Filmes.csv");
        Scanner scanner = new Scanner(file);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Ignorar a primeira linha (comentários)
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(";");
            int id = Integer.parseInt(campos[0]);
            String nome = campos[1];
            Date dataLancamento = dateFormat.parse(campos[2]);
            String genero = campos[3];
            String idioma = campos[4];
            int duracaoSegundos = Integer.parseInt(campos[5]);
            Filme filme = new Filme(id, nome, genero, idioma, dataLancamento, duracaoSegundos);
            midias.add(filme);
        }

        scanner.close();
        return midias;
    }
	@Override
	protected int getDuracaoOuEpisodios() {
		// TODO Auto-generated method stub
		return 0;
	}
}