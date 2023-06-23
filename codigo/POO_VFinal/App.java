import exceptions.EspectadorAvaliaException;
import exceptions.EspectadorException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App {
    
	public static void main(String[] args) throws IOException, EspectadorException, InvalidParameterException, EspectadorAvaliaException {

	    Streaming streaming = new Streaming("");
	    Scanner scanner = new Scanner(System.in);

	    while (true) {
	        System.out.println("_____________________________");
	        System.out.println("||Menu:");
	        System.out.println("||1. Adicionar espectador||");
	        System.out.println("||2. Adicionar mídia||");
	        System.out.println("||3. Relatórios||");
	        System.out.println("||4. Funções do espectador||");
	        System.out.println("||5. Salvar||");
	        System.out.println("||0. Sair||");
	        System.out.println("|____________________________|");
	        System.out.print("Escolha uma opção: ");
	        int opcao = scanner.nextInt();
	        scanner.nextLine();

	        switch (opcao) {
	            case 1:
	                cadastrarEspectador(streaming, scanner);
	                break;
	            case 2:
	                cadastrarMidia(streaming, scanner);
	                break;
	            case 3:
	                puxarRelatorio(streaming, scanner);
	                break;
	            case 4:
	                System.out.println("Digite o nome de usuário do espectador:");
	                String login = scanner.nextLine();

	                System.out.println("Digite a senha do espectador:");
	                String senha = scanner.nextLine();

	                Espectador espectador = streaming.login(login, senha);
	                if (espectador != null) {
	                	menuEspectador(streaming, scanner);
	                } else {
	                    System.out.println("Usuário ou senha inválidos.");
	                }
	                break;
	            case 0:
	                System.out.println("Saindo...");
	                System.out.println(" /\\_/\\");
			        System.out.println("( o.o )");
			        System.out.println(" > ^ <");
			        System.out.println("/~~~~\\/");
			        System.out.println("|    |");
			        System.out.println("|    |");
			        System.out.println("\\~~~~/");
			        System.out.println(" \\__/");
	                scanner.close();
	                return;
	            default:
	                System.out.println("Opção inválida. Tente novamente.");
	        }
	    }
	}

	/**
	 * Método para adicionar um novo espectador à plataforma.
	 *
	 * @param plataforma A plataforma de streaming
	 * @param scanner    O objeto scanner para entrada do usuário
	 * @throws EspectadorException se ocorrer um erro relacionado a espectadores
	 */

	private static void cadastrarEspectador(Streaming streaming, Scanner scanner) throws EspectadorException {
	    System.out.println("Digite o nome completo do espectador:");
	    String nomeCompleto = scanner.nextLine();

	    System.out.println("Digite o nome de usuário do espectador:");
	    String login = scanner.nextLine();

	    System.out.println("Digite a senha do espectador:");
	    String senha = scanner.nextLine();

	    Espectador espectador = new Espectador(nomeCompleto, login, senha);
	    streaming.adicionarEspectador(espectador);
	    System.out.println("Espectador adicionado com sucesso!");
	}

	/**
	 * Método para adicionar uma nova mídia à plataforma.
	 *
	 * @param plataforma A plataforma de streaming
	 * @param scanner    O objeto scanner para entrada do usuário
	 * @throws IOException se ocorrer um erro de E/S
	 */
    private static void cadastrarMidia(Streaming streaming, Scanner scanner) throws InvalidParameterException {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("____________________________");
        System.out.println("||Escolha o tipo de mídia:||");
        System.out.println("||1. Série||");
        System.out.println("||2. Filme||");
        System.out.println("||0. Sair||");
        System.out.println("|_________________________|");
        System.out.print("Digite a opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Digite o ID da série:");
                int id = scanner.nextInt();
                scanner.nextLine(); 

                System.out.println("Digite o nome da série:");
                String nome = scanner.nextLine();

                System.out.println("Digite a data de lançamento no formato dd/MM/yyyy:");
                LocalDate dataLancamento = LocalDate.parse(scanner.nextLine(), dateFormatter);

                System.out.println("Digite o número de episodios:");
                int qtdEpisodios = scanner.nextInt();
                scanner.nextLine();


                System.out.print("<<Digite o g�nero da m�dia (\n\n�ndice: \n 1-  ACAO \n   2-ANIME \n 3-AVENTURA \n 4-COMEDIA \n5- DOCUMENTARIO \n  6- DRAMA   \n  7-POLICIAL  \n 8-ROMANCE  \n 9-SUSPENSE   \n \n)>>:");
                System.out.println("Digite o número da categoria que encaixa com essa série:");
                int genero = scanner.nextInt();
                scanner.nextLine();

                System.out.print("<<Digite o g�nero da m�dia (\n\n�ndice: \n 1-INGLES \n   2-PORTUGUES \n 3-ESPANHOL  \n 4-JAPONES \n5-COREANO \n  6-RUSSO   \n  7-MANDARIM  \n 8-ALEMAO  \n  9-ARABE   \n \n)>>:");
                System.out.println("Digite o número do idioma que encaixa com essa série:");
                int idioma = scanner.nextInt();
                scanner.nextLine();

                Serie serie = new Serie(id, nome, genero, idioma, qtdEpisodios, dataLancamento);
                streaming.adicionarSerie(serie);
                System.out.println("Série adicionada com sucesso!");
                break;

            case 2:
                System.out.println("Digite o ID do filme:");
                int idFilme = scanner.nextInt();
                scanner.nextLine(); 

                System.out.println("Digite o nome do filme:");
                String nomeFilme = scanner.nextLine();

                System.out.println("Digite a data de lançamento no formato dd/MM/yyyy:");
                LocalDate dataLancamentoFilme = LocalDate.parse(scanner.nextLine(), dateFormatter);

                System.out.println("Digite a duração do filme:");
                int duracao = scanner.nextInt();
                scanner.nextLine(); 

                System.out.print("<<Digite o g�nero da m�dia (\n\n�ndice: \n 1-  ACAO \n   2-ANIME \n 3-AVENTURA \n 4-COMEDIA \n5- DOCUMENTARIO \n  6- DRAMA   \n  7-POLICIAL  \n 8-ROMANCE  \n 9-SUSPENSE   \n \n)>>:");
                System.out.println("Digite o número da categoria que encaixa com esse filme:");
                int generoFilme = scanner.nextInt();
                scanner.nextLine();

                System.out.print("<<Digite o g�nero da m�dia (\n\n�ndice: \n 1-INGLES \n   2-PORTUGUES \n 3-ESPANHOL  \n 4-JAPONES \n5-COREANO \n  6-RUSSO   \n  7-MANDARIM  \n 8-ALEMAO  \n  9-ARABE   \n \n)>>:");
                System.out.println("Digite o número do idioma que encaixa com esse filme:");

                int idiomaFilme = scanner.nextInt();
                scanner.nextLine();

                Filme filme = new Filme(idFilme, nomeFilme, dataLancamentoFilme, duracao, generoFilme, idiomaFilme);
                streaming.adicionarFilme(filme);
                System.out.println("Filme adicionado com sucesso!");
                break;
            
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    /**
     * Método para exibir e salvar os relatórios disponíveis.
     *
     * @param plataforma A plataforma de streaming
     * @param scanner    O objeto scanner para entrada do usuário
     * @throws IOException se ocorrer um erro de E/S
     */

    private static void puxarRelatorio(Streaming streaming, Scanner scanner) {
    	System.out.println("________________________________________________________________________________________________________");
    	System.out.println("||Escolha o relatório:||");
        System.out.println("||1. Qual cliente assistiu mais mídias, e quantas mídias||");
        System.out.println("||2. Qual cliente tem mais avaliações, e quantas avaliações||");
        System.out.println("||3. Qual a porcentagem dos clientes com pelo menos 15 avaliações||");
        System.out.println("||4. Quais são as 10 mídias de melhor avaliação, com pelo menos 100 avaliações, em ordem decrescente||");
        System.out.println("||5. Qual a mídia mais assistida pelos clientes||");
        System.out.println("||6. Quais são as 10 mídias com mais avaliações por genero (Avaliações > 100)||");
        System.out.println("||7. Quais são as 10 mídias com mais visualizações por genero||");
        System.out.println("|______________________________________________________________________________________________________|");

        System.out.print("Digite a opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        RelatoriosGerenciais relatorio = new RelatoriosGerenciais(streaming);

        switch (opcao) {
            case 1:
                relatorio.EspectadorAssistiuMaisMidias();
                break;
            case 2:
                relatorio.EspectadorMaisAvaliacoes();
                break;
            case 3:
                relatorio.porcentagemEspectadorComMais15Avaliacoes();
                break;
            case 4:
                relatorio.midiasComMaisAvaliacoes();
                break;
            case 5:
                relatorio.midiasComMaisVisualizacoes();
                break;
            case 6:
                relatorio.midiasComMaisAvaliacoesPorGenero();
                break;
            case 7:
                relatorio.midiasComMaisVisualizacoesPorGenero();
                break;

            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

     /**
     * Método para exibir o menu do cliente.
     *
     * @param plataforma A plataforma de streaming
     * @param scanner    O objeto scanner para entrada do usuário
     * @throws ClienteException      se ocorrer um erro relacionado a clientes
     * @throws ClienteAvaliaException se ocorrer um erro relacionado a avaliações de clientes
     */
    
    private static void menuEspectador(Streaming streaming, Scanner scanner) throws InvalidParameterException, EspectadorAvaliaException {
    	System.out.println("_________________________________________________");
    	System.out.println("||Menu:||");
        System.out.println("||1. Assistir mídia||");
        System.out.println("||2. Avaliar mídia||");
        System.out.println("||3. Filtrar mídias do genero escolhido||");
        System.out.println("||4. Filtrar midias do idioma escolhido||");
        System.out.println("||5. Lista de todas as mídias assistidas||");
        System.out.println("||6. Adicionar na lista de assistir mais tarde||");
        System.out.println("||7. Retirar da lista de assistir mais tarde||");
        System.out.println("|_____________________________________________|");
        System.out.print("Escolha uma opção: ");

        String nomeMidia;
        int opcao = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcao) {
                case 1:
                    System.out.println("Digite o nome da Midia: ");
                    nomeMidia = scanner.nextLine();
                    streaming.registrarAudiencia(nomeMidia);
                    break;
                case 2:
                    System.out.println("Digite o nome da Midia: ");
                    nomeMidia = scanner.nextLine();
                    System.out.println("Digite a nota: ");
                    Double nota = scanner.nextDouble();
                    streaming.avaliarMidia(nomeMidia, nota);
                    break;
                case 3:
                    String genero = scanner.nextLine();
                    streaming.filtrarPorGenero(genero);
                    break;
                case 4:
                    String idioma = scanner.nextLine();
                    streaming.filtrarPorIdioma(idioma);
                    break;
                case 5:
                    List<Midia> lista = streaming.mostrarMidiasEspectador(streaming.getAtualEspectador());
                    for (Midia midia : lista) {
                        System.out.println(midia.getNome());
                    }
                    break;
                case 6:
                    System.out.println("Digite o nome da Midia: ");
                    nomeMidia = scanner.nextLine();
                    streaming.adicionarMidiaLista(nomeMidia);
                    break;
                case 7:
                    System.out.println("Digite o nome da Midia: ");
                    nomeMidia = scanner.nextLine();
                    streaming.removerMidiaLista(nomeMidia);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    System.out.println(" /\\_/\\");
    		        System.out.println("( o.o )");
    		        System.out.println(" > ^ <");
    		        System.out.println("/~~~~\\/");
    		        System.out.println("|    |");
    		        System.out.println("|    |");
    		        System.out.println("\\~~~~/");
    		        System.out.println(" \\__/");

                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
