import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Carregando os espectadores do arquivo CSV
        Map<String, Espectador> espectadores = Espectador.lerEspectadores("POO_Espectadores.csv");

        // Criando um objeto de login para o usu�rio
        Espectador usuarioLogado = null;
        boolean loginValido = false;
        while (!loginValido) {
            System.out.print("Digite o login do usu�rio: ");
            String login = scanner.nextLine();
            System.out.print("Digite a senha do usu�rio: ");
            String senha = scanner.nextLine();

            usuarioLogado = espectadores.get(login);
            if (usuarioLogado != null && usuarioLogado.getSenha().equals(senha)) {
                loginValido = true;
            } else {
                System.out.println("Login ou senha inv�lidos. Tente novamente.");
            }
        }

        // Criando um objeto de streaming
        Appstreaming streaming = new Appstreaming(usuarioLogado);
        try {
            // Carregando as s�ries do arquivo CSV
            Map<Integer, Serie> series = Serie.lerSeries("POO_Series.csv");
            streaming.setSeries(series);

            // Carregando os filmes do arquivo CSV
            Map<Integer, Filme> filmes = Filme.lerFilmes("POO_Filmes.csv");
            streaming.setFilmes(filmes);

            // Lendo o arquivo CSV com as informa��es adicionais
            streaming.lerArquivoCSV("POO_Dados.csv");
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo n�o encontrado.");
        } catch (ParseException e) {
            System.out.println("Erro: Falha na leitura do arquivo CSV.");
        }

        // Exibindo as s�ries para assistir no futuro
        System.out.println("S�ries para assistir no futuro:");
        for (Midia serie : streaming.getSeriesParaAssistir().values()) {
            System.out.println(serie.getNome());
        }

        // Exibindo os filmes para assistir no futuro
        System.out.println("Filmes para assistir no futuro:");
        for (Midia filme : streaming.getFilmesParaAssistir().values()) {
            System.out.println(filme.getNome());
        }

        // Adicionando uma m�dia �s m�dias j� assistidas
        System.out.print("Digite o nome de uma m�dia para adicionar �s m�dias j� assistidas: ");
        String nomeMidiaAssistida = scanner.nextLine();
        streaming.adicionarMidiaJaAssistida(nomeMidiaAssistida);

        // Exibindo as m�dias j� assistidas
        System.out.println("M�dias j� assistidas:");
        for (Midia midia : streaming.getMidasJaAssistidas().values()) {
            System.out.println(midia.getNome());
        }

        // Buscando uma m�dia pelo nome
        System.out.print("Digite o nome de uma m�dia para buscar: ");
        String nomeMidiaBusca = scanner.nextLine();
        if (streaming.buscarNome(nomeMidiaBusca)) {
            System.out.println("A m�dia " + nomeMidiaBusca + " foi encontrada.");
        } else {
            System.out.println("A m�dia " + nomeMidiaBusca + " n�o foi encontrada.");
        }

        // Trocando uma m�dia de lista (de F para A, ou vice versa)
        System.out.print("Digite o nome de uma m�dia para trocar de lista (de F para A, ou vice versa): ");
        String nomeMidiaTroca = scanner.nextLine();
        streaming.trocarDeLista(nomeMidiaTroca);

        // Exibindo as s�ries para assistir no futuro e as m�dias j� assistidas ap�s a troca de lista
        System.out.println("S�ries para assistir no futuro ap�s a troca de lista:");
        for (Midia serie : streaming.getSeriesParaAssistir().values()) {
            System.out.println(serie.getNome());
        }

        System.out.println("M�dias j� assistidas ap�s a troca de lista:");
        for (Midia midia : streaming.getMidasJaAssistidas().values()) {
            System.out.println(midia.getNome());
        }

        scanner.close();
    }
}





