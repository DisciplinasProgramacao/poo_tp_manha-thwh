import java.util.ArrayList;
import java.util.Date;

public class Serie extends Midia {
    private int quantidadeEpisodios;
    private int iD_serie;

    public Serie(int id, String nome, String genero, String idioma, Date dataLancamento, int quantidadeEpisodios, int iD_serie) {
        super(id, nome, genero, idioma, dataLancamento);
        this.quantidadeEpisodios = quantidadeEpisodios;
        this.iD_serie = iD_serie;
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

    public static ArrayList<Serie> lerSeries() {
        // Lógica para ler as séries de algum lugar (arquivo, banco de dados, etc.)
        // Aqui você deve implementar a leitura das séries e retornar uma lista de objetos Serie
        // Exemplo:
        ArrayList<Serie> series = new ArrayList<>();
        // Código para ler as séries e adicionar à lista 'series'
        return series;
    }
}
