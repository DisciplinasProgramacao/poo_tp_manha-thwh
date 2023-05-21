import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Serie extends Midia {
    private int quantidadeEpisodios;
    private int iD_serie;

    public Serie(int id, String nome, String genero, String idioma, Date dataLancamento, int quantidadeEpisodios) {
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
        System.out.println("Reproduzindo a série: " + nome);
        // Lógica específica para reprodução de uma série
    }

    public void mostraEpisodio(int numeroEpisodio) {
        System.out.println("Mostrando episódio " + numeroEpisodio + " da série " + nome);
        // Lógica para mostrar o episódio especificado
    }

    public static void catalogoSerie(ArrayList<Serie> series) {
        System.out.println("Catálogo de séries:");
        for (Serie serie : series) {
            System.out.println("ID: " + serie.getID_Serie());
            System.out.println("Nome: " + serie.getNome());
            System.out.println("Gênero: " + serie.getGenero());
            System.out.println("Idioma: " + serie.getIdioma());
            System.out.println("Data de Lançamento: " + serie.getDataLancamento());
            System.out.println("Quantidade de Episódios: " + serie.getQuantidadeEpisodios());
            System.out.println("--------------------------");
        }
    }

    public static List<Midia> lerSeries(String arquivo) throws FileNotFoundException, ParseException {
        List<Midia> midias = new ArrayList<>();
        File file = new File("POO_Series.csv");
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
            int quantidadeEpisodios = Integer.parseInt(campos[5]);
            Serie serie = new Serie(id, nome, genero, idioma, dataLancamento, quantidadeEpisodios);
            midias.add(serie);
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