import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatoriosGerenciais {
    private List<Espectador> listaEspectadores;
    private List<Midia> listaMidias;

    public RelatoriosGerenciais(List<Espectador> listaEspectadores, List<Midia> listaMidias) {
        this.listaEspectadores = listaEspectadores;
        this.listaMidias = listaMidias;
    }

    // Obt�m o cliente com mais m�dias assistidas
    public Espectador obterClienteComMaisMidiasAssistidas() {
        return listaEspectadores.stream()
            .max(Comparator.comparingInt(Espectador::getQuantidadeMidiasAssistidas))
            .orElse(null);
    }

    // Obt�m o cliente com mais avalia��es
    public Espectador obterClienteComMaisAvaliacoes() {
        return listaEspectadores.stream()
            .max(Comparator.comparingInt(espectador -> espectador.getAvaliacoes().size()))
            .orElse(null);
    }

    // Calcula a porcentagem de clientes com um m�nimo de avalia��es
    public double calcularPorcentagemClientesComAvaliacoes(int quantidadeMinima) {
        long clientesComAvaliacoes = listaEspectadores.stream()
            .filter(espectador -> espectador.getAvaliacoes().size() >= quantidadeMinima)
            .count();
        double totalClientes = listaEspectadores.size();
        return (clientesComAvaliacoes / totalClientes) * 100;
    }

    // Obt�m as 10 melhores m�dias com base na m�dia das avalia��es e um m�nimo de visualiza��es
    public List<Midia> obterTop10MidiasMelhorMediaAvaliacoes(int visualizacoesMinimas) {
        return listaMidias.stream()
            .filter(midia -> midia.getNumeroVisualizacoes() >= visualizacoesMinimas)
            .sorted(Comparator.comparingDouble(Midia::getMediaAvaliacoes).reversed())
            .limit(10)
            .collect(Collectors.toList());
    }

    // Obt�m as 10 m�dias mais visualizadas
    public List<Midia> obterTop10MidiasMaisVisualizacoes() {
        return listaMidias.stream()
            .sorted(Comparator.comparingInt(Midia::getNumeroVisualizacoes).reversed())
            .limit(10)
            .collect(Collectors.toList());
    }

    // Obt�m as 10 melhores m�dias por g�nero com base na m�dia das avalia��es e um m�nimo de visualiza��es
    public List<Midia> obterTop10MidiasMelhorMediaAvaliacoesPorGenero(int visualizacoesMinimas, Genero genero) {
        return listaMidias.stream()
            .filter(midia -> midia.getNumeroVisualizacoes() >= visualizacoesMinimas && midia.getGenero() == genero)
            .sorted(Comparator.comparingDouble(Midia::getMediaAvaliacoes).reversed())
            .limit(10)
            .collect(Collectors.toList());
    }

    // Obt�m as 10 m�dias mais visualizadas por g�nero
    public List<Midia> obterTop10MidiasMaisVisualizacoesPorGenero(Genero genero) {
        return listaMidias.stream()
            .filter(midia -> midia.getGenero() == genero)
            .sorted(Comparator.comparingInt(Midia::getNumeroVisualizacoes).reversed())
            .limit(10)
            .collect(Collectors.toList());
    }
}
