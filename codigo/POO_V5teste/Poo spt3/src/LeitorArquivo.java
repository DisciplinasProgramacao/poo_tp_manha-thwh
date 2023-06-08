import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorArquivo {
    public List<String[]> lerArquivo(String Arquivo) throws IOException {
        List<String[]> linhas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(Arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                linhas.add(campos);
            }
        }

        return linhas;
    }

	
}