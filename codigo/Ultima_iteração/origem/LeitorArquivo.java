import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorArquivo {
    /**
     * Lê um arquivo e retorna as linhas como uma lista de arrays de strings.
     *
     * @param arquivo O caminho do arquivo a ser lido.
     * @return Uma lista contendo arrays de strings, onde cada array representa uma linha do arquivo.
     * @throws IOException se ocorrer um erro durante a leitura do arquivo.
     */
    public List<String[]> lerArquivo(String arquivo) throws IOException {
        List<String[]> linhas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Divide a linha em campos utilizando ";" como separador
                String[] campos = linha.split(";");
                linhas.add(campos);
            }
        }

        return linhas;
    }
}
