import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class Midia {
    protected int id; // ID da m�dia
    protected String nome; // Nome da m�dia
    protected String genero; // G�nero da m�dia
    protected String idioma; // Idioma da m�dia
    protected Date dataLancamento; // Data de lan�amento da m�dia
    protected int visualizacao; // Visualiza��o da m�dia

    public Midia(int id, String nome, String idioma, String genero, Date dataLancamento, int visualizacao) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.genero = genero;
        this.dataLancamento = dataLancamento;
        this.visualizacao = visualizacao;
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

    public int getVisualizacao() {
        return visualizacao;
    }

    public void setVisualizacao(int visualizacao) {
        this.visualizacao = visualizacao;
    }

    public void adicionarAvaliacao(int nota) {
        // L�gica para adicionar avalia��o � m�dia
        if (nota < 1 || nota > 5) {
            System.out.println("A nota deve estar entre 1 e 5.");
            return;
        }
        
        System.out.println("Avalia��o adicionada com sucesso.");
    }


    protected abstract int getDuracaoOuEpisodios();

    public static Map<Integer, Midia> lerMidias(String arquivo) throws FileNotFoundException, ParseException {
        Map<Integer, Midia> midias = new HashMap<>(800000);
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

            String idioma = "Desconhecido"; // Idioma padr�o
            if (campos.length > 4) {
                idioma = campos[4];
            }

            String genero = "Sem G�nero"; // G�nero padr�o
            if (campos.length > 3) {
                genero = campos[3];
            }

            // Verifica se � um filme ou s�rie com base no n�mero de campos
            if (campos.length == 6) {
                int duracaoSegundos = Integer.parseInt(campos[5]);
                Filme filme = new Filme(id, nome, idioma, genero, dataLancamento, duracaoSegundos);
                midias.put(id, filme);
            } else if (campos.length == 7) {
                int quantidadeEpisodios = Integer.parseInt(campos[5]);
                int duracaoEpisodio = Integer.parseInt(campos[6]);
                Serie serie = new Serie(id, nome, idioma, genero, dataLancamento, quantidadeEpisodios, duracaoEpisodio);
                midias.put(id, serie);
            }
        }

        scanner.close();
        return midias;
    }
}

