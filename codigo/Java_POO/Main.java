import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando um objeto de login para o usuário
        classeEspectador usuarioLogado = new classeEspectador("usuario123", "senha456");

        // Criando um objeto de streaming
        ClasseStreaming streaming = new ClasseStreaming(usuarioLogado);

        // Adicionando algumas séries para assistir no futuro
        classeSerie serie1 = new classeSerie("Série 1", "Gênero 1", "Idioma 1");
        classeSerie serie2 = new classeSerie("Série 2", "Gênero 2", "Idioma 2");
        classeSerie serie3 = new classeSerie("Série 3", "Gênero 1", "Idioma 2");
        streaming.adicionarParaAssistir(serie1);
        streaming.adicionarParaAssistir(serie2);
        streaming.adicionarParaAssistir(serie3);

        // Exibindo as séries para assistir no futuro
        System.out.println("Séries para assistir no futuro:");
        for (classeSerie serie : streaming.getF()) {
            System.out.println(serie.getNome());
        }

        // Adicionando uma série às séries já assistidas
        System.out.print("Digite o nome de uma série para adicionar às séries já assistidas: ");
        String nomeSerieAssistida = scanner.nextLine();
        streaming.adicionarJaAssistida(nomeSerieAssistida);

        // Exibindo as séries já assistidas
        System.out.println("Séries já assistidas:");
        for (classeSerie serie : streaming.getA()) {
            System.out.println(serie.getNome());
        }

        // Buscando uma série pelo nome
        System.out.print("Digite o nome de uma série para buscar: ");
        String nomeSerieBusca = scanner.nextLine();
        if (streaming.buscarNome(nomeSerieBusca)) {
            System.out.println("A série " + nomeSerieBusca + " foi encontrada.");
        } else {
            System.out.println("A série " + nomeSerieBusca + " não foi encontrada.");
        }

        // Trocando uma série de lista (de F para A, ou vice versa)
        System.out.print("Digite o nome de uma série para trocar de lista (de F para A, ou vice versa): ");
        String nomeSerieTroca = scanner.nextLine();
        streaming.trocarDeLista(nomeSerieTroca);

        // Exibindo as séries para assistir no futuro e as séries já assistidas após a troca de lista
        System.out.println("Séries para assistir no futuro após a troca de lista:");
        for (classeSerie serie : streaming.getF()) {
            System.out.println(serie.getNome());
        }

        System.out.println("Séries já assistidas após a troca de lista:");
        for (classeSerie serie : streaming.getA()) {
            System.out.println(serie.getNome());
        }

        scanner.close();
    }
}

