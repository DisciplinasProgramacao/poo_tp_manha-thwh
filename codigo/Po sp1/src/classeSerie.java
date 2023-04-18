import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class classeSerie {
    private int iD_Serie;
    private String nome;
    private String genero;
    private String idioma;
    private int visualizacao;
    private Date dataLancamento;
    
    public classeSerie(int iD_Serie, String nome, String genero, String idioma, Date dataLancamento) {
        this.iD_Serie = iD_Serie;
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.visualizacao = 0;
        this.dataLancamento = dataLancamento;
    }
    
    public void registrarAudiencia(int qtdAssistiram) {
        this.visualizacao += qtdAssistiram;
    }
    
    public static ArrayList<classeSerie> lerSeries() throws FileNotFoundException, ParseException {
        ArrayList<classeSerie> series = new ArrayList<>();
        File file = new File("C:\\Users\\T-Gamer\\Downloads\\POO_Series_2023(1)\\POO_Series.csv");
        Scanner scanner = new Scanner(file);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(";");
            int idSerie = Integer.parseInt(campos[0]);
            String nome = campos[1];
            Date dataLancamento = dateFormat.parse(campos[2]);      
            String genero = campos[3];
            String idioma = campos[4];
            classeSerie serie = new classeSerie(idSerie, nome, genero, idioma, dataLancamento);
            series.add(serie);
        }
        
        scanner.close();
        return series;
    }

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
