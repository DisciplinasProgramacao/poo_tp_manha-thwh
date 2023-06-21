import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.EspectadorAvaliaException;

public class App {
	private static final List<Midia> listaMidias = new ArrayList<>();
	private static final List<Espectador> listaEspectadores = new ArrayList<>();
	private static Autenticador autenticador;
	private static boolean usuarioAutenticado;

	public static void main(String[] args) throws InvalidParameterException, EspectadorAvaliaException {
		autenticador = new Autenticador();
		usuarioAutenticado = false;
		RelatoriosGerenciais relatoriosGerenciais = new RelatoriosGerenciais(listaEspectadores, listaMidias);
		Espectador espectador = null;
		exibirOpcoesMenu(listaMidias, espectador, relatoriosGerenciais);
		;
	}

	private static void exibirOpcoesMenu(List<Midia> listaMidias, Espectador espectador, RelatoriosGerenciais relatoriosGerenciais) throws InvalidParameterException, EspectadorAvaliaException {
	        Scanner scanner = new Scanner(System.in);
	        int opcao;

		do {
			System.out.println("_________________________________________________________________________________________________");	
			System.out.println("||---- Cadastro -------------------------------------------------------------------------------||");
				System.out.println("||1. Logar<-                                                                                   ||");
				System.out.println("||2. Cadastrar<-                                                                               ||");
        System.out.println("||---------------------------------------------------------------------------------------------||");
        System.out.println("||---- Interface ------------------------------------------------------------------------------||");
				System.out.println("||3. Adicionar filme<-                                                                         ||");
				System.out.println("||4. Adicionar s�rie<-                                                                         ||");
				System.out.println("||5. Assistir m�dia<-                                                                          ||");
				System.out.println("||6. Avaliar m�dia<-                                                                           ||");
				System.out.println("||7. Qual cliente assistiu mais m�dias<-                                                       ||");
				System.out.println("||8. Qual cliente tem mais avalia��es<-                                                        ||");
				System.out.println("||9. Porcentagem dos clientes com pelo menos 15 avalia��es<-                                   ||");
				System.out.println("||10. Top 10 m�dias com melhor m�dia de avalia��es e vistas pelo menos 100 vezes<-             ||");
				System.out.println("||11. Top 10 m�dias com mais visualiza��es<-                                                   ||");
				System.out.println("||12. Top 10 m�dias com melhor m�dia de avalia��es e vistas pelo menos 100 vezes (por g�nero)<-||");
				System.out.println("||13. Top 10 m�dias com mais visualiza��es (por g�nero)<-                                      ||");
        System.out.println("||---------------------------------------------------------------------------------------------||");
        System.out.println("||---- Finalizar ------------------------------------------------------------------------------||");
				System.out.println("||0. Sair<-                                                                                    ||");
				System.out.println("||---------------------------------------------------------------------------------------------||");
				System.out.println("|_______________________________________________________________________________________________|");
				System.out.println("<<Digite a op��o desejada:>>");
  
      
			
				opcao = scanner.nextInt();
				scanner.nextLine(); // Limpa o buffer do scanner

			switch (opcao) {
			case 1:
				System.out.print("<<Digite o login:>> ");
				String login = scanner.nextLine();
				System.out.print("<<Digite a senha:>> ");
				String senha = scanner.nextLine();
				usuarioAutenticado = autenticador.autenticarEspectador(login, senha);
				if (usuarioAutenticado) {
					System.out.println("<Usu�rio autenticado com sucesso!>");
				} else {
					System.out.println("!!! Falha na autentica��o. Verifique o login e senha. !!!");
				}
				break;
			case 2:
				System.out.print("<<Digite o login do novo espectador:>> ");
					String novoLogin = scanner.nextLine();
					System.out.print("<<Digite a senha do novo espectador:>> ");
					String novaSenha = scanner.nextLine();
					System.out.print("<<Digite o nome do novo espectador:>> ");
					String novoNome = scanner.nextLine();

					boolean criacaoSucesso = autenticador.criarEspectador(novoLogin, novaSenha, novoNome);
					if (criacaoSucesso) {
						System.out.println("<Espectador criado com sucesso!>");
						autenticador.autenticarEspectador(novoLogin, novaSenha);
						usuarioAutenticado = true;
						System.out.println("<Usu�rio autenticado>");
					} else {
						System.out.println("!!! N�o foi poss�vel criar o espectador. Login j� existente. !!!");
					}
				break;

			case 3:
				if (usuarioAutenticado) {
					System.out.println("<<Digite o ID da m�dia:>>");
					int id = scanner.nextInt();
					scanner.nextLine(); // Limpar o buffer
					System.out.println("<<Digite o nome da m�dia:>>");
					String nome = scanner.nextLine();
					System.out.println("<<Digite a data de lan�amento da m�dia (DD/MM/AAAA):>>");
					String dataLancamentoString = scanner.nextLine();
					System.out.print("<<Digite o g�nero da m�dia (\n\n�ndice: \n 1-  ACAO \n   2-ANIME \n 3-AVENTURA \n 4-COMEDIA \n5- DOCUMENTARIO \n  6- DRAMA   \n  7-POLICIAL  \n 8-ROMANCE  \n 9-SUSPENSE   \n \n)>>:");
					int genero = scanner.nextInt();
					System.out.print("<<Digite o g�nero da m�dia (\n\n�ndice: \n 1-INGLES \n   2-PORTUGUES \n 3-ESPANHOL  \n 4-JAPONES \n5-COREANO \n  6-RUSSO   \n  7-MANDARIM  \n 8-ALEMAO  \n  9-ARABE   \n \n)>>:");
					int idioma = scanner.nextInt();
					System.out.println("<<A m�dia � um lan�amento? (true/false):>>");
					boolean lancamento = scanner.nextBoolean();

					Midia novaMidia = new Filme();
					listaMidias.add(novaMidia);
					;
				} else {
					System.out.println("!!! Usu�rio n�o autenticado. Opera��o cancelada.!!!");
				}
				break;
			case 4:
				if (usuarioAutenticado) {
					System.out.println("<<Digite o ID da m�dia:>>");
					int idM = scanner.nextInt();
					scanner.nextLine(); // Limpar o buffer
					System.out.println("<<Digite o nome da m�dia:>>");
					String nomeM = scanner.nextLine();
					System.out.println("<<Digite a data de lan�amento da m�dia (DD/MM/AAAA):>>");
					String dataLancamentoStringM = scanner.nextLine();
					System.out.print("<<Digite o g�nero da m�dia (\n\n�ndice: \n 1-  ACAO \n   2-ANIME \n 3-AVENTURA \n 4-COMEDIA \n5- DOCUMENTARIO \n  6- DRAMA   \n  7-POLICIAL  \n 8-ROMANCE  \n 9-SUSPENSE   \n \n)>>:");
					int generoM = scanner.nextInt();
					System.out.print("<<Digite o g�nero da m�dia (\n\n�ndice: \n 1-INGLES \n   2-PORTUGUES \n 3-ESPANHOL  \n 4-JAPONES \n5-COREANO \n  6-RUSSO   \n  7-MANDARIM  \n 8-ALEMAO  \n 9-ARABE  \n  \n)>>:");
					int idiomaM = scanner.nextInt();
					System.out.println("<<Digite a quantidade de epis�dios da s�rie:>>");
					int quantidadeEpisodios = scanner.nextInt();
					System.out.println("<<Digite a dura��o m�dia dos epis�dios (em minutos):>>");
					int duracaoEpisodio = scanner.nextInt();

					Midia novaSerie = new Serie();
					listaMidias.add(novaSerie);
				} else {
					System.out.println("!!! Usu�rio n�o autenticado. Opera��o cancelada.!!!");
				}
				break;
			case 5:
				if (usuarioAutenticado) {
					System.out.println("<<Digite o ID da m�dia que deseja assistir>>:");
					int idMidiaAssistir = scanner.nextInt();
					Midia midiaAssistir = buscarMidiaPorId(listaMidias, idMidiaAssistir);
					if (midiaAssistir != null) {
						espectador.assistirMidia(midiaAssistir);
						System.out.println("<M�dia assistida com sucesso!>");
					} else {
						System.out.println("!!! M�dia n�o encontrada. Tente novamente.!!!");
					}
				} else {
					System.out.println("!!! Usu�rio n�o autenticado. Opera��o cancelada.!!!");
				}
				break;
			case 6:
				if (usuarioAutenticado) {
					System.out.println("<<Digite o ID da m�dia que deseja avaliar:>>");
					int idMidiaAvaliar = scanner.nextInt();
					scanner.nextLine(); // Limpar o buffer
					Midia midiaAvaliar = buscarMidiaPorId(listaMidias, idMidiaAvaliar);
					if (midiaAvaliar != null) {
						System.out.println("<<Digite a sua avalia��o (de 1 a 5)>>:");
						int avaliacao = scanner.nextInt();
						espectador.avaliarMidia(avaliacao, midiaAvaliar);
						System.out.println("<<Avalia��o registrada com sucesso>>!");
					} else {
						System.out.println("!!! M�dia n�o encontrada. Tente novamente.!!!");
					}
				} else {
					System.out.println("!!! Usu�rio n�o autenticado. Opera��o cancelada.!!!");
				}
				break;
			case 7:
				if (usuarioAutenticado) {
					System.out.println("<<Qual cliente assistiu mais m�dias>>:");
					Espectador clienteMaisMidias = relatoriosGerenciais.obterClienteComMaisMidiasAssistidas();
					if (clienteMaisMidias != null) {
						int quantidadeMidias = espectador.getQuantidadeMidiasAssistidas();
						System.out.println("Cliente: " + clienteMaisMidias.getNome());
						System.out.println("Quantidade de m�dias assistidas: " + quantidadeMidias);
					} else {
						System.out.println("!!! Nenhum cliente encontrado.!!!");
					}
				} else {
					System.out.println("!!! Usu�rio n�o autenticado. Opera��o cancelada.!!!");
				}
				break;
			case 8:
				if (usuarioAutenticado) {
					System.out.println("<<Qual cliente tem mais avalia��es>>:");
					Espectador clienteMaisAvaliacoes = relatoriosGerenciais.obterClienteComMaisMidiasAssistidas();
					if (clienteMaisAvaliacoes != null) {
						List<Avaliacao> quantidadeAvaliacoes = clienteMaisAvaliacoes.getAvaliacoes();
						System.out.println("Cliente: " + clienteMaisAvaliacoes.getNome());
						System.out.println("Quantidade de avalia��es: " + quantidadeAvaliacoes);
					} else {
						System.out.println("!!! Nenhum cliente encontrado.!!!");
					}
				} else {
					System.out.println("!!! Usu�rio n�o autenticado. Opera��o cancelada.!!!");
				}
				break;
			case 9:
				if (usuarioAutenticado) {
					System.out.println("<<Qual a porcentagem dos clientes com pelo menos 15 avalia��es>>:");
					double porcentagem = relatoriosGerenciais.calcularPorcentagemClientesComAvaliacoes(15);
					System.out.println("Porcentagem: " + porcentagem + "%");
				} else {
					System.out.println("!!! Usu�rio n�o autenticado. Opera��o cancelada.!!!");
				}
				break;
			case 10:
				if (usuarioAutenticado) {
					System.out.println(
							"<<As 10 m�dias com a melhor m�dia de avalia��es e que foram vistas pelo menos 100 vezes>>:");
					List<Midia> top10MidiasMelhorMedia = relatoriosGerenciais
							.obterTop10MidiasMelhorMediaAvaliacoes(100);
					exibirListaMidias(top10MidiasMelhorMedia);
				} else {
					System.out.println("!!! Usu�rio n�o autenticado. Opera��o cancelada. !!!");
				}
				break;
			case 11:
				if (usuarioAutenticado) {
					System.out.println("<<As 10 m�dias com mais visualiza��es>>:");
					List<Midia> top10MidiasMaisVisualizacoes = relatoriosGerenciais.obterTop10MidiasMaisVisualizacoes();
					exibirListaMidias(top10MidiasMaisVisualizacoes);
				} else {
					System.out.println("!!! Usu�rio n�o autenticado. Opera��o cancelada.!!!");
				}
				break;
			case 12:
				if (usuarioAutenticado) {
					System.out.println(
							"<<As 10 m�dias com a melhor m�dia de avalia��es e que foram vistas pelo menos 100 vezes (por g�nero)>>:");
					for (Genero genero : Genero.values()) {
						System.out.println("G�nero: " + genero);
						List<Midia> top10MidiasMelhorMediaPorGenero = relatoriosGerenciais
								.obterTop10MidiasMelhorMediaAvaliacoesPorGenero(100, genero);
						exibirListaMidias(top10MidiasMelhorMediaPorGenero);
					}
				} else {
					System.out.println("!!! Usu�rio n�o autenticado. Opera��o cancelada.!!!");
				}
				break;
			case 13:
				if (usuarioAutenticado) {
					System.out.println("<<As 10 m�dias com mais visualiza��es (por g�nero):>>");
					for (Genero genero : Genero.values()) {
						System.out.println("G�nero: " + genero);
						List<Midia> top10MidiasMaisVisualizacoesPorGenero = relatoriosGerenciais
								.obterTop10MidiasMaisVisualizacoesPorGenero(genero);
						exibirListaMidias(top10MidiasMaisVisualizacoesPorGenero);
					}
				} else {
					System.out.println("!!! Usu�rio n�o autenticado. Opera��o cancelada.!!!");
				}
				break;

			case 0:
				System.out.println("<Saindo do aplicativo...>");
				System.out.println(" /\\_/\\");
		        System.out.println("( o.o )");
		        System.out.println(" > ^ <");
		        System.out.println("/~~~~\\/");
		        System.out.println("|    |");
		        System.out.println("|    |");
		        System.out.println("\\~~~~/");
		        System.out.println(" \\__/");
				
				break;
			default:
				System.out.println("!!! Op��o inv�lida. Tente novamente.!!!");
          System.out.println();
          System.out.println();
          System.out.println();
          System.out.println();
				break;
			}
			
			System.out.println();
		} while (opcao != 0);
		scanner.close();
	}

	private static Midia buscarMidiaPorId(List<Midia> listaMidias, int idMidiaAssistir) {
		for (Midia midia : listaMidias) {
			if (midia.getId() == idMidiaAssistir) {
				return midia;
			}
		}
		return null;
	}

	private static void exibirListaMidias(List<Midia> listaMidias) {
		for (Midia midia : listaMidias) {
			System.out.println(midia);
		}
	}

}