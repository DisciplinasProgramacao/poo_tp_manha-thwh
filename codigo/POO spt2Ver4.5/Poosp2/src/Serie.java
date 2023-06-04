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
    private String genero; // G�nero da s�rie
    private String idioma; // Idioma da s�rie

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
        System.out.println("Reproduzindo a s�rie: " + nome);
        // L�gica espec�fica para reprodu��o de uma s�rie
    }

    public void mostraEpisodio(int numeroEpisodio) {
        System.out.println("Mostrando epis�dio " + numeroEpisodio + " da s�rie " + nome);
        // L�gica para mostrar o epis�dio especificado
    }

    public static void catalogoSerie(Map<Integer, Serie> series) {
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

    public static Map<Integer, Serie> lerSeries(String arquivo) throws FileNotFoundException, ParseException {
        Map<Integer, Serie> series = new HashMap<>(800000);
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
            int quantidadeEpisodios = Integer.parseInt(campos[3]);
            
            String genero = "Sem G�nero"; // G�nero padr�o
            if (campos.length > 4) {
                genero = campos[4];
            }

            String idioma = "Desconhecido"; // Idioma padr�o
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

