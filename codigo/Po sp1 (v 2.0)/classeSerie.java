import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Classe que representa uma série de TV.
 */
public class ClasseSerie {
    private int iD_Serie; // ID da série
    private String nome; // Nome da série
    private String genero; // Gênero da série
    private String idioma; // Idioma da série
    private int visualizacao; // Quantidade de visualizações da série
    private Date dataLancamento; // Data de lançamento da série

    /**
     * Construtor da classe Serie.
     * @param iD_Serie ID da série
     * @param nome Nome da série
     * @param genero Gênero da série
     * @param idioma Idioma da série
     * @param dataLancamento Data de lançamento da série
     */
    public ClasseSerie(int iD_Serie, String nome, String genero, String idioma, Date dataLancamento) {
        this.iD_Serie = iD_Serie;
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.visualizacao = 0;
        this.dataLancamento = dataLancamento;
    }

    /**
     * Método para registrar a quantidade de audiência de uma série.
     * @param qtdAssistiram Quantidade de pessoas que assistiram à série
     */
    public void registrarAudiencia(int qtdAssistiram) {
        this.visualizacao += qtdAssistiram;
    }

    /**
     * Método estático para ler as séries de um arquivo CSV e retornar uma lista de séries.
     * @return ArrayList de séries lidas do arquivo CSV
     * @throws FileNotFoundException se o arquivo CSV não for encontrado
     * @throws ParseException se ocorrer um erro de formatação de data ao ler o arquivo CSV
     */
    public static ArrayList<ClasseSerie> lerSeries() throws FileNotFoundException, ParseException {
        ArrayList<ClasseSerie> series = new ArrayList<>();
        File file = new File("C:\\Users\\wande\\Downloads\\POO_Series_2023(1)\\POO_Series.csv");
        Scanner scanner = new Scanner(file);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(";");
            int idSerie = Integer.parseInt(campos[0]);
            String nome = campos[1];
            Date dataLancamento = dateFormat.parse(campos[2]);
            String genero = campos[3];
            String idioma = campos[4];
            ClasseSerie serie = new ClasseSerie(idSerie, nome, genero, idioma, dataLancamento);
            series.add(serie);
        }

        scanner.close();
        return series;
    }

    /**
     * Método estático para ler a audiência de séries de um arquivo CSV.
     * @throws FileNotFoundException se o arquivo CSV não for encontrado
     */
    public static void lerAudiencia() throws FileNotFoundException {
        File file = new File("C:\\teste");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(";");
            String login = campos[0];
            String fOuA = campos[1];
            int idSerie = Integer.parseInt(campos[2]);

            ArrayList<ClasseSerie> series = lerSeries();
            for (classeSerie serie : series) {
                if (serie.iD_Serie == idSerie) {
                    if (fOuA.equals("F")) {
                        serie.registrarAudiencia(1);
                        System.out.println("Usuário " + login + " assistiu à série " + serie.nome + " - Series para assistir futuramente");
                    } else if (fOuA.equals("A")) {
                        serie.registrarAudiencia(1);
                        System.out.println("Usuário " + login + " assistiu à série " + serie.nome + " - Series já assistidas");
                    } else {
                        System.out.println("Valor inválido para o atributo F/A");
                    }
                    break;
                }
            }
        }

        scanner.close(); {
        	public String getNome() {
        	    return nome;
        	}

        	public int getID_Serie() {
        	    return iD_Serie;
        	}

        	public Date getDataLancamento() {
        	    return dataLancamento;
        	}

        	public String getGenero() {
        	    return genero;
        	}

        	public String getIdioma() {
        	    return idioma;
        	}
        
    }
}

