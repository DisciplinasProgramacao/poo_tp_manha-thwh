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

    // Obtém o cliente com mais mídias assistidas
    public Espectador obterClienteComMaisMidiasAssistidas() {
        return listaEspectadores.stream()
            .max(Comparator.comparingInt(Espectador::getQuantidadeMidiasAssistidas))
            .orElse(null);
    }

    // Obtém o cliente com mais avaliações
    public Espectador obterClienteComMaisAvaliacoes() {
        return listaEspectadores.stream()
            .max(Comparator.comparingInt(espectador -> espectador.getAvaliacoes().size()))
            .orElse(null);
    }

    // Calcula a porcentagem de clientes com um mínimo de avaliações
    public double calcularPorcentagemClientesComAvaliacoes(int quantidadeMinima) {
        long clientesComAvaliacoes = listaEspectadores.stream()
            .filter(espectador -> espectador.getAvaliacoes().size() >= quantidadeMinima)
            .count();
        double totalClientes = listaEspectadores.size();
        return (clientesComAvaliacoes / totalClientes) * 100;
    }

    // Obtém as 10 melhores mídias com base na média das avaliações e um mínimo de visualizações
    public List<Midia> obterTop10MidiasMelhorMediaAvaliacoes(int visualizacoesMinimas) {
        return listaMidias.stream()
            .filter(midia -> midia.getNumeroVisualizacoes() >= visualizacoesMinimas)
            .sorted(Comparator.comparingDouble(Midia::getMediaAvaliacoes).reversed())
            .limit(10)
            .collect(Collectors.toList());
    }

    // Obtém as 10 mídias mais visualizadas
    public List<Midia> obterTop10MidiasMaisVisualizacoes() {
        return listaMidias.stream()
            .sorted(Comparator.comparingInt(Midia::getNumeroVisualizacoes).reversed())
            .limit(10)
            .collect(Collectors.toList());
    }

    // Obtém as 10 melhores mídias por gênero com base na média das avaliações e um mínimo de visualizações
    public List<Midia> obterTop10MidiasMelhorMediaAvaliacoesPorGenero(int visualizacoesMinimas, Genero genero) {
        return listaMidias.stream()
            .filter(midia -> midia.getNumeroVisualizacoes() >= visualizacoesMinimas && midia.getGenero() == genero)
            .sorted(Comparator.comparingDouble(Midia::getMediaAvaliacoes).reversed())
            .limit(10)
            .collect(Collectors.toList());
    }

    // Obtém as 10 mídias mais visualizadas por gênero
    public List<Midia> obterTop10MidiasMaisVisualizacoesPorGenero(Genero genero) {
        return listaMidias.stream()
            .filter(midia -> midia.getGenero() == genero)
            .sorted(Comparator.comparingInt(Midia::getNumeroVisualizacoes).reversed())
            .limit(10)
            .collect(Collectors.toList());
    }
}
