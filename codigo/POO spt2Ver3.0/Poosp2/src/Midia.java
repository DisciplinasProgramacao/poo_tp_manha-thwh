import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public abstract class Midia {
    protected int id; // ID da mídia
    protected String nome; // Nome da mídia
    protected String genero; // Gênero da mídia
    protected String idioma; // Idioma da mídia
    protected Date dataLancamento; // Data de lançamento da mídia

    public Midia(int id, String nome, String genero, String idioma, Date dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.dataLancamento = dataLancamento;
    }

    public abstract void reproduzir();

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void adicionarAvaliacao(int nota) {
        // Lógica para adicionar avaliação à mídia
    }

    protected abstract int getDuracaoOuEpisodios();

    public static List<Midia> lerMidias(String arquivo) throws FileNotFoundException, ParseException {
        List<Midia> midias = new ArrayList<>();
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
            String genero = campos[3];
            String idioma = campos[4];

            // Verifica se é um filme ou série com base no número de campos
            if (campos.length == 6) {
                int duracaoSegundos = Integer.parseInt(campos[5]);
                Filme filme = new Filme(id, nome, genero, idioma, dataLancamento, duracaoSegundos);
                midias.add(filme);
            } else if (campos.length == 7) {
                int quantidadeEpisodios = Integer.parseInt(campos[5]);
                int duracaoEpisodio = Integer.parseInt(campos[6]);
                Serie serie = new Serie(id, nome, genero, idioma, dataLancamento, quantidadeEpisodios, duracaoEpisodio);
                midias.add(serie);
            }
        }

        scanner.close();
        return midias;
    }
}
