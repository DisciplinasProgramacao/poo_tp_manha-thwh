import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

import exceptions.ClienteException;


public class App {
    
    public static void main(String[] args) throws IOException, ClienteException, InvalidParameterException {

        PlataformaStreaming plataforma = new PlataformaStreaming("teste"); 
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar cliente");
            System.out.println("2. Adicionar mídia");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    cadastrarCliente(plataforma, scanner);
                    break;
                case 2:
                    cadastrarMidia(plataforma, scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarCliente(PlataformaStreaming plataforma, Scanner scanner) throws ClienteException {
        System.out.println("Digite o nome completo do cliente:");
        String nomeCompleto = scanner.nextLine();

        System.out.println("Digite o nome de usuário do cliente:");
        String nomeUsuario = scanner.nextLine();

        System.out.println("Digite a senha do cliente:");
        String senha = scanner.nextLine();

        Cliente cliente = new Cliente(nomeCompleto, nomeUsuario, senha);
        plataforma.adicionarCliente(cliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

    private static void cadastrarMidia(PlataformaStreaming plataforma, Scanner scanner) throws InvalidParameterException {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Escolha o tipo de mídia:");
        System.out.println("1. Série");
        System.out.println("2. Filme");

        System.out.print("Digite a opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        switch (opcao) {
            case 1:
                System.out.println("Digite o ID da série:");
                int id = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                System.out.println("Digite o nome da série:");
                String nome = scanner.nextLine();

                System.out.println("Digite a data de lançamento no formato dd/MM/yyyy:");
                LocalDate dataLancamento = LocalDate.parse(scanner.nextLine(), dateFormatter);

                System.out.println("Digite o número de episodios:");
                int qtdEpisodios = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                Serie serie = new Serie(id, nome, dataLancamento, qtdEpisodios);
                plataforma.adicionarSerie(serie);
                System.out.println("Série adicionada com sucesso!");
                break;

            case 2:
                System.out.println("Digite o ID do filme:");
                int idFilme = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                System.out.println("Digite o nome do filme:");
                String nomeFilme = scanner.nextLine();

                System.out.println("Digite a data de lançamento no formato dd/MM/yyyy:");
                LocalDate dataLancamentoFilme = LocalDate.parse(scanner.nextLine(), dateFormatter);

                System.out.println("Digite a duração do filme:");
                int duracao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                Filme filme = new Filme(idFilme, nomeFilme, dataLancamentoFilme, duracao);
                plataforma.adicionarFilme(filme);
                System.out.println("Filme adicionado com sucesso!");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
