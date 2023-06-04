import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Serie extends Midia {
    private int quantidadeEpisodios;
    private int iD_serie;
    private String genero; // Gênero da série
    private String idioma; // Idioma da série

    public Serie(int id, String nome, String genero, String idioma, Date dataLancamento, int quantidadeEpisodios, int duracaoEpisodio) {
        super(id, nome, idioma, genero, dataLancamento, duracaoEpisodio);
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
        System.out.println("Reproduzindo a série: " + nome);
        // Lógica específica para reprodução de uma série
    }

    public void mostraEpisodio(int numeroEpisodio) {
        System.out.println("Mostrando episódio " + numeroEpisodio + " da série " + nome);
        // Lógica para mostrar o episódio especificado
    }

    public static void catalogoSerie(Map<Integer, Serie> series) {
        System.out.println("Catálogo de séries:");
        for (Serie serie : series.values()) {
            System.out.println("ID: " + serie.getID_Serie());
            System.out.println("Nome: " + serie.getNome());
            System.out.println("Gênero: " + serie.getGenero());
            System.out.println("Idioma: " + serie.getIdioma());
            System.out.println("Data de Lançamento: " + serie.getDataLancamento());
            System.out.println("Quantidade de Episódios: " + serie.getQuantidadeEpisodios());
            System.out.println("--------------------------");
        }
    }

    public static Map<Integer, Serie> lerSeries(String arquivo) throws FileNotFoundException, ParseException {
        Map<Integer, Serie> series = new HashMap<>(800000);
        File file = new File(arquivo);
        Scanner scanner = new Scanner(file);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Ignorar a primeira linha (comentários)
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(";");
            int id = Integer.parseInt(campos[0]);
            String nome = campos[1];
            Date dataLancamento = dateFormat.parse(campos[2]);
            int quantidadeEpisodios = Integer.parseInt(campos[3]);
            
            String genero = "Sem Gênero"; // Gênero padrão
            if (campos.length > 4) {
                genero = campos[4];
            }

            String idioma = "Desconhecido"; // Idioma padrão
            if (campos.length > 5) {
                idioma = campos[5];
            }

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

	public Object getStatus() {
		
		return null;
	}

	public static Serie lerSeriePorId(String idMidia) {
	
		return null;
	}
}

