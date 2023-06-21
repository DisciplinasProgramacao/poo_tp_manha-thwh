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

    public Espectador obterClienteComMaisMidiasAssistidas() {
        return listaEspectadores.stream()
                .max(Comparator.comparingInt(Espectador::getQuantidadeMidiasAssistidas))
                .orElse(null);
    }

    public Espectador obterClienteComMaisAvaliacoes() {
        return listaEspectadores.stream()
                .max(Comparator.comparingInt(espectador -> espectador.getAvaliacoes().size()))
                .orElse(null);
    }

    public double calcularPorcentagemClientesComAvaliacoes(int quantidadeMinima) {
        long clientesComAvaliacoes = listaEspectadores.stream()
                .filter(espectador -> espectador.getAvaliacoes().size() >= quantidadeMinima)
                .count();

        double totalClientes = listaEspectadores.size();
        return (clientesComAvaliacoes / totalClientes) * 100;
    }

    public List<Midia> obterTop10MidiasMelhorMediaAvaliacoes(int visualizacoesMinimas) {
        return listaMidias.stream()
                .filter(midia -> midia.getNumeroVisualizacoes() >= visualizacoesMinimas)
                .sorted(Comparator.comparingDouble(Midia::getMediaAvaliacoes).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Midia> obterTop10MidiasMaisVisualizacoes() {
        return listaMidias.stream()
                .sorted(Comparator.comparingInt(Midia::getNumeroVisualizacoes).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Midia> obterTop10MidiasMelhorMediaAvaliacoesPorGenero(int visualizacoesMinimas, Genero genero) {
        return listaMidias.stream()
                .filter(midia -> midia.getNumeroVisualizacoes() >= visualizacoesMinimas && midia.getGenero() == genero)
                .sorted(Comparator.comparingDouble(Midia::getMediaAvaliacoes).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Midia> obterTop10MidiasMaisVisualizacoesPorGenero(Genero genero) {
        return listaMidias.stream()
                .filter(midia -> midia.getGenero() == genero)
                .sorted(Comparator.comparingInt(Midia::getNumeroVisualizacoes).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}
