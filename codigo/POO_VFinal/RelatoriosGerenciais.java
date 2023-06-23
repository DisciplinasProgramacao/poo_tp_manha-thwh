import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RelatoriosGerenciais {
    Streaming streaming;

    public RelatoriosGerenciais(Streaming streaming) {
        this.streaming = streaming;
    }

    /**
     * Qual cliente assistiu mais mídias, e quantas mídias.
     */
    public void EspectadorAssistiuMaisMidias() {
        Espectador espectador = this.streaming.getEspectador().entrySet().stream()
                .max(Comparator.comparingInt(p -> p.getValue().getJaAssistida().size()))
                .orElseThrow(NoSuchElementException::new)
                .getValue();

        System.out.println("Cliente: " + espectador.getNome() + " - Assistiu: " + espectador.getJaAssistida().size());
    }

    /**
     * Qual cliente tem mais avaliações, e quantas avaliações.
     */
    public void EspectadorMaisAvaliacoes() {
        Espectador espectador = this.streaming.getEspectador().entrySet().stream()
                .max(Comparator.comparingInt(p -> p.getValue().getAvaliacoes().size()))
                .orElseThrow(NoSuchElementException::new)
                .getValue();

        System.out.println("Espectador: " + espectador.getNome() + " - Avaliações: " + espectador.getAvaliacoes().size());
    }

    /**
     * Qual a porcentagem dos clientes com pelo menos 15 avaliações.
     */
    public void porcentagemEspectadorComMais15Avaliacoes() {
        long total = this.streaming.getEspectador().entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(o -> o.getAvaliacoes().size() >= 15)
                .count();

        long porcentagem = total * this.streaming.getEspectador().size();

        System.out.println("Porcentagem: " + porcentagem + "%");
    }

    /**
     * Quais são as 10 mídias de melhor avaliação, com pelo menos 100 avaliações, em ordem decrescente.
     */
    public void midiasComMaisAvaliacoes() {
        List<Midia> lista = this.streaming.getMidias().values().stream()
                .filter(o -> o.getAvaliacoes().size() > 100)
                .sorted(Comparator.comparingInt(Midia::getNumAvaliacoes).reversed())
                .limit(10)
                .collect(Collectors.toList());

        if (lista.size() > 0) {
            System.out.println("Lista de mais avaliados");
            for (Midia obj : lista) this.printMidiaAvaliacoes(obj);
        } else {
            System.out.println("Não possui nenhuma mídia com pelo menos 100 avaliações!");
        }
    }

    /**
     * Quais são as 10 mídias com mais visualizações, em ordem decrescente.
     */
    public void midiasComMaisVisualizacoes() {
        List<Midia> lista = this.streaming.getMidias().values().stream()
                .sorted(Comparator.comparingInt(Midia::getAudiencia).reversed())
                .limit(10)
                .collect(Collectors.toList());

        System.out.println("Lista de mídias com mais visualizações:");
        for (Midia midia : lista) {
            System.out.println("Nome: " + midia.getNome() + " | Visualizações: " + midia.getAudiencia());
        }
    }

    /**
     * Quais são as 10 mídias de melhor avaliação, com pelo menos 100 avaliações, em ordem decrescente por gênero.
     */
    public void midiasComMaisAvaliacoesPorGenero() {
        Map<Genero, List<Midia>> lista = this.streaming.getMidias().values().stream()
                .filter(o -> o.getAvaliacoes().size() > 100)
                .sorted(Comparator.comparingInt(Midia::getNumAvaliacoes).reversed())
                .limit(10)
                .collect(Collectors.groupingBy(Midia::getGenero));

        System.out.println("Lista de mais avaliados por gênero: ");

        if (lista.size() > 0) {
            for (Entry<Genero, List<Midia>> entry : lista.entrySet()) {
                Genero chave = entry.getKey();
                List<Midia> listaInterna = entry.getValue();
                System.out.println("Gênero: " + chave);

                for (Midia midia : listaInterna) this.printMidiaAvaliacoes(midia);
            }
        } else {
            System.out.println("Não possui nenhuma mídia com pelo menos 100 avaliações!");
        }
    }

    /**
     * Quais são as 10 mídias com mais visualizações, em ordem decrescente por gênero.
     */
    public void midiasComMaisVisualizacoesPorGenero() {
        Map<Genero, List<Midia>> lista = this.streaming.getMidias().values().stream()
                .sorted(Comparator.comparingInt(Midia::getAudiencia).reversed())
                .limit(10)
                .collect(Collectors.groupingBy(Midia::getGenero));

        System.out.println("Lista de mais visualizações por gênero: ");

        for (Entry<Genero, List<Midia>> entry : lista.entrySet()) {
            Genero chave = entry.getKey();
            List<Midia> listaInterna = entry.getValue();
            System.out.println("Gênero: " + chave);

            for (Midia midia : listaInterna) this.printMidiaVisualizacoes(midia);
        }
    }

    private void printMidiaVisualizacoes(Midia midia) {
        System.out.println("    Nome: " + midia.getNome() + " | Visualizações:" + midia.getAudiencia());
    }

    private void printMidiaAvaliacoes(Midia midia) {
        System.out.println("    Nome: " + midia.getNome() + " | Avaliações: " + midia.getNumAvaliacoes());
    }
}