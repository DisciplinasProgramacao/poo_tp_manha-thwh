import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RelatoriosGerenciais {
    Streaming streaming;

    public RelatoriosGerenciais(Streaming streaming) {
        this.streaming = streaming;
    }

    /**
     * Qual cliente assistiu mais m�dias, e quantas m�dias.
     */
    public void EspectadorAssistiuMaisMidias() {
        Espectador espectador = this.streaming.getEspectador().entrySet().stream()
                .max(Comparator.comparingInt(p -> p.getValue().getJaAssistida().size()))
                .orElseThrow(NoSuchElementException::new)
                .getValue();

        System.out.println("Cliente: " + espectador.getNome() + " - Assistiu: " + espectador.getJaAssistida().size());
    }

    /**
     * Qual cliente tem mais avalia��es, e quantas avalia��es.
     */
    public void EspectadorMaisAvaliacoes() {
        Espectador espectador = this.streaming.getEspectador().entrySet().stream()
                .max(Comparator.comparingInt(p -> p.getValue().getAvaliacoes().size()))
                .orElseThrow(NoSuchElementException::new)
                .getValue();

        System.out.println("Espectador: " + espectador.getNome() + " - Avalia��es: " + espectador.getAvaliacoes().size());
    }

    /**
     * Qual a porcentagem dos clientes com pelo menos 15 avalia��es.
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
     * Quais s�o as 10 m�dias de melhor avalia��o, com pelo menos 100 avalia��es, em ordem decrescente.
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
            System.out.println("N�o possui nenhuma m�dia com pelo menos 100 avalia��es!");
        }
    }

    /**
     * Quais s�o as 10 m�dias com mais visualiza��es, em ordem decrescente.
     */
    public void midiasComMaisVisualizacoes() {
        List<Midia> lista = this.streaming.getMidias().values().stream()
                .sorted(Comparator.comparingInt(Midia::getAudiencia).reversed())
                .limit(10)
                .collect(Collectors.toList());

        System.out.println("Lista de m�dias com mais visualiza��es:");
        for (Midia midia : lista) {
            System.out.println("Nome: " + midia.getNome() + " | Visualiza��es: " + midia.getAudiencia());
        }
    }

    /**
     * Quais s�o as 10 m�dias de melhor avalia��o, com pelo menos 100 avalia��es, em ordem decrescente por g�nero.
     */
    public void midiasComMaisAvaliacoesPorGenero() {
        Map<Genero, List<Midia>> lista = this.streaming.getMidias().values().stream()
                .filter(o -> o.getAvaliacoes().size() > 100)
                .sorted(Comparator.comparingInt(Midia::getNumAvaliacoes).reversed())
                .limit(10)
                .collect(Collectors.groupingBy(Midia::getGenero));

        System.out.println("Lista de mais avaliados por g�nero: ");

        if (lista.size() > 0) {
            for (Entry<Genero, List<Midia>> entry : lista.entrySet()) {
                Genero chave = entry.getKey();
                List<Midia> listaInterna = entry.getValue();
                System.out.println("G�nero: " + chave);

                for (Midia midia : listaInterna) this.printMidiaAvaliacoes(midia);
            }
        } else {
            System.out.println("N�o possui nenhuma m�dia com pelo menos 100 avalia��es!");
        }
    }

    /**
     * Quais s�o as 10 m�dias com mais visualiza��es, em ordem decrescente por g�nero.
     */
    public void midiasComMaisVisualizacoesPorGenero() {
        Map<Genero, List<Midia>> lista = this.streaming.getMidias().values().stream()
                .sorted(Comparator.comparingInt(Midia::getAudiencia).reversed())
                .limit(10)
                .collect(Collectors.groupingBy(Midia::getGenero));

        System.out.println("Lista de mais visualiza��es por g�nero: ");

        for (Entry<Genero, List<Midia>> entry : lista.entrySet()) {
            Genero chave = entry.getKey();
            List<Midia> listaInterna = entry.getValue();
            System.out.println("G�nero: " + chave);

            for (Midia midia : listaInterna) this.printMidiaVisualizacoes(midia);
        }
    }

    private void printMidiaVisualizacoes(Midia midia) {
        System.out.println("    Nome: " + midia.getNome() + " | Visualiza��es:" + midia.getAudiencia());
    }

    private void printMidiaAvaliacoes(Midia midia) {
        System.out.println("    Nome: " + midia.getNome() + " | Avalia��es: " + midia.getNumAvaliacoes());
    }
}