import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Catalogo {
    private Map<Integer, Midia> midias;

    public Catalogo() {
        this.midias = new HashMap<>();
    }

    public void adicionarMidia(Midia midia) {
        midias.put(midia.getId(), midia);
    }

    public void removerMidia(int id) {
        midias.remove(id);
    }

    public Midia buscarMidia(int id) {
        return midias.get(id);
    }

    public void salvarCatalogo(String caminhoArquivo) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            for (Midia midia : midias.values()) {
                String linha = midia.getId() + ";" + midia.getNome() + ";" + dateFormat.format(midia.getDataLancamento()) +
                        ";" + midia.getGenero() + ";" + midia.getIdioma() + ";" + midia.getDuracaoOuEpisodios();
                writer.write(linha);
                writer.newLine();
            }

            writer.close();
            System.out.println("Catálogo salvo com sucesso no arquivo: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar catálogo no arquivo: " + e.getMessage());
        }
    }

    public Map<Integer, Midia> filtrarMidiasPorGenero(String genero) {
        Map<Integer, Midia> midiasFiltradas = new HashMap<>();
        for (Midia midia : midias.values()) {
            if (midia.getGenero().equalsIgnoreCase(genero)) {
                midiasFiltradas.put(midia.getId(), midia);
            }
        }
        return midiasFiltradas;
    }

    public Map<Integer, Midia> filtrarMidiasPorDataLancamento(Date dataMinima, Date dataMaxima) {
        Map<Integer, Midia> midiasFiltradas = new HashMap<>();
        for (Midia midia : midias.values()) {
            Date dataLancamento = midia.getDataLancamento();
            if (dataLancamento.compareTo(dataMinima) >= 0 && dataLancamento.compareTo(dataMaxima) <= 0) {
                midiasFiltradas.put(midia.getId(), midia);
            }
        }
        return midiasFiltradas;
    }

    public Map<Integer, Midia> getMidias() {
        return midias;
    }

    public boolean contemMidia(Midia midia) {
        return midias.containsValue(midia);
    }
}
