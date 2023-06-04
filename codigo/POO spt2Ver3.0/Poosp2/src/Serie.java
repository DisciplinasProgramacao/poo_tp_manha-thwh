import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Serie extends Midia {
    private int quantidadeEpisodios;
    private int iD_serie;

    public Serie(int id, String nome, String genero, String idioma, Date dataLancamento, int quantidadeEpisodios, int duracaoEpisodio) {
        super(id, nome, genero, idioma, dataLancamento);
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    public int getID_Serie() {
        return iD_serie;
    }

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo a s�rie: " + nome);
        // L�gica espec�fica para reprodu��o de uma s�rie
    }

    public void mostraEpisodio(int numeroEpisodio) {
        System.out.println("Mostrando epis�dio " + numeroEpisodio + " da s�rie " + nome);
        // L�gica para mostrar o epis�dio especificado
    }

    public static void catalogoSerie(HashMap<Integer, Serie> series) {
        System.out.println("Cat�logo de s�ries:");
        for (Serie serie : series.values()) {
            System.out.println("ID: " + serie.getID_Serie());
            System.out.println("Nome: " + serie.getNome());
            System.out.println("G�nero: " + serie.getGenero());
            System.out.println("Idioma: " + serie.getIdioma());
            System.out.println("Data de Lan�amento: " + serie.getDataLancamento());
            System.out.println("Quantidade de Epis�dios: " + serie.getQuantidadeEpisodios());
            System.out.println("--------------------------");
        }
    }

    public static HashMap<Integer, Serie> lerSeries(String arquivo) throws FileNotFoundException, ParseException {
        HashMap<Integer, Serie> series = new HashMap<>();
        File file = new File("POO_Series.csv");
        Scanner scanner = new Scanner(file);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Ignorar a primeira linha (coment�rios)
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(";");
            int id = Integer.parseInt(campos[0]);
            String nome = campos[1];
            Date dataLancamento = dateFormat.parse(campos[2]);
            String genero = campos[3];
            String idioma = campos[4];
            int quantidadeEpisodios = Integer.parseInt(campos[5]);
            Serie serie = new Serie(id, nome, genero, idioma, dataLancamento, quantidadeEpisodios, quantidadeEpisodios);
            series.put(id, serie);
        }

        scanner.close();
        return series;
    }

    @Override
    protected int getDuracaoOuEpisodios() {
        return quantidadeEpisodios;
    }

	public static Map<String, Serie> lerSeries() {
		
		return null;
	}

	
}
