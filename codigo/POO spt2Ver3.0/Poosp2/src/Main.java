import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando um objeto de login para o usuário
        Espectador usuarioLogado = new Espectador("usuario123", "senha456");

        // Criando um objeto de streaming
        Appstreaming streaming = new Appstreaming(usuarioLogado);

        try {
            // Carregando as séries do arquivo CSV
            Map<String, Serie> lerSeries = (Map<String, Serie>) Serie.lerSeries();
			Map<String, Serie> series = lerSeries;
            streaming.setSeries(series);

            // Carregando os filmes do arquivo CSV
            Map<Integer, Filme> filmes = Filme.lerFilmes("POO_Filmes.csv");
            streaming.setFilmes(filmes);
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado.");
        } catch (ParseException e) {
            System.out.println("Erro: Falha na leitura do arquivo CSV.");
        }

        // Exibindo as séries para assistir no futuro
        System.out.println("Séries para assistir no futuro:");
        for (Serie serie : streaming.getSeriesParaAssistir().values()) {
            System.out.println(serie.getNome());
        }

        // Exibindo os filmes para assistir no futuro
        System.out.println("Filmes para assistir no futuro:");
        for (Serie filme : streaming.getFilmesParaAssistir().values()) {
            System.out.println(filme.getNome());
        }

        // Adicionando uma mídia às mídias já assistidas
        System.out.print("Digite o nome de uma mídia para adicionar às mídias já assistidas: ");
        String nomeMidiaAssistida = scanner.nextLine();
        streaming.adicionarMidiaJaAssistida(nomeMidiaAssistida);

        // Exibindo as mídias já assistidas
        System.out.println("Mídias já assistidas:");
        for (Midia midia : streaming.getMidasJaAssistidas().values()) {
            System.out.println(midia.getNome());
        }

        // Buscando uma mídia pelo nome
        System.out.print("Digite o nome de uma mídia para buscar: ");
        String nomeMidiaBusca = scanner.nextLine();
        if (streaming.buscarNome(nomeMidiaBusca)) {
            System.out.println("A mídia " + nomeMidiaBusca + " foi encontrada.");
        } else {
            System.out.println("A mídia " + nomeMidiaBusca + " não foi encontrada.");
        }

        // Trocando uma mídia de lista (de F para A, ou vice versa)
        System.out.print("Digite o nome de uma mídia para trocar de lista (de F para A, ou vice versa): ");
        String nomeMidiaTroca = scanner.nextLine();
        streaming.trocarDeLista(nomeMidiaTroca);

        // Exibindo as séries para assistir no futuro e as mídias já assistidas após a troca de lista
        System.out.println("Séries para assistir no futuro após a troca de lista:");
        for (Serie serie : streaming.getSeriesParaAssistir().values()) {
            System.out.println(serie.getNome());
        }

        System.out.println("Mídias já assistidas após a troca de lista:");
        for (Midia midia : streaming.getMidasJaAssistidas().values()) {
            System.out.println(midia.getNome());
        }

        scanner.close();
    }
}





