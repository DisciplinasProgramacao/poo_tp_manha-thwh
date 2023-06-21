import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exceptions.EspectadorException;
import java.time.format.DateTimeFormatter;

public class Streaming {
	private String nome;
	private HashMap<Integer, Midia> midia;
	private HashMap<String, Espectador> espectador;
	private Espectador atualEspectador;
	private LeitorArquivo leitorArquivo;
	private Autenticador autenticador;

	/**
	 * Construtor da classe Streaming.
	 * 
	 * @param nome Nome da streaming.
	 * @throws IOException               Exceção lançada em caso de falha na leitura
	 *                                   dos arquivos.
	 * @throws InvalidParameterException
	 * @throws EspectadorException
	 */
	public Streaming(String nome) throws IOException, InvalidParameterException, EspectadorException {
		this.nome = nome;
		this.midia = new HashMap<Integer, Midia>();
		this.espectador = new HashMap<String, Espectador>();

		this.atualEspectador = null;

		this.leitorArquivo = new LeitorArquivo();
		this.lerMidia("POO_Series.csv", "POO_Filmes.csv");
		this.lerEspectador("POO_Espectadores.csv");
		this.lerAudiencia("POO_Audiencia.csv");
	}

	/**
	 * Construtor da classe Streaming para criação de uma instância com um nome e um
	 * valor booleano.
	 * 
	 * @param nome  O nome da streaming.
	 * @param dummy Um valor booleano utilizado para inicialização.
	 */
	public Streaming(String nome, boolean dummy) {
		this.nome = nome;
		this.midia = new HashMap<Integer, Midia>();
		this.espectador = new HashMap<String, Espectador>();

		this.atualEspectador = null;
	}

	/**
	 * Método para realizar o login de um espectador na plataforma.
	 * 
	 * @param login Nome de usuário do especatdor.
	 * @param senha Senha do espectador.
	 * @return Retorna o objeto Espectador caso o login seja bem-sucedido, caso
	 *         contrário, retorna null.
	 */
	public Espectador login(String login, String senha) {
		Espectador cl = espectador.get(login);
		if (cl.getLogin() == login && cl.getSenha() == senha) {
			this.atualEspectador = cl;
			return cl;
		}
		return null;
	}

	/**
	 * Adiciona uma série à plataforma.
	 * 
	 * @param serie Objeto da classe Midia representando a série a ser adicionada.
	 */
	public void adicionarSerie(Midia serie) {
		this.midia.put(serie.getId(), serie);
	}

	/**
	 * Adiciona um cliente à plataforma.
	 * 
	 * @param espectador Objeto da classe Espectador representando o espectador a
	 *                   ser adicionado.
	 */
	public void adicionarEspectador(Espectador espectador) {
		this.espectador.put(espectador.getLogin(), espectador);
	}

	/**
	 * Adiciona um filme à plataforma.
	 * 
	 * @param filme Objeto da classe Midia representando o filme a ser adicionado.
	 */
	public void adicionarFilme(Midia filme) {
		this.midia.put(filme.getId(), filme);
	}

	/**
	 * Busca mídias na plataforma com base no gênero.
	 *
	 * @param genero Gênero a ser usado como filtro.
	 * @return Retorna uma lista de mídias que correspondem ao gênero informado.
	 */
	public List<Midia> buscarGenero(Genero genero) {
		if (genero == null) {
			return Collections.emptyList(); // Retorna uma lista vazia se o gênero for nulo
		}

		return this.midia.values().stream().filter(m -> m.getGenero() == genero).collect(Collectors.toList());
	}

	/**
	 * Busca mídias na plataforma com base no idioma.
	 *
	 * @param idioma Idioma a ser usado como filtro.
	 * @return Retorna uma lista de mídias que correspondem ao idioma informado.
	 */
	public List<Midia> buscarIdioma(Idioma idioma) {
		if (idioma == null) {
			return Collections.emptyList(); // Retorna uma lista vazia se o idioma for nulo
		}

		return this.midia.values().stream().filter(m -> m.getIdioma() == idioma).collect(Collectors.toList());
	}

	/**
	 * Retorna um HashMap contendo os espectadores cadastrados na Streaming.
	 * 
	 * @return HashMap contendo os espectdores.
	 */
	public HashMap<String, Espectador> getEspectador() {
		return this.espectador;
	}

	/**
	 * Lê as informações dos espectadores a partir de um arquivo CSV e os adiciona à
	 * plataforma.
	 *
	 * @param arquivo Nome do arquivo CSV contendo as informações dos espectadores.
	 * @throws IOException         Exceção lançada em caso de falha na leitura do
	 *                             arquivo.
	 * @throws EspectadorException Exceção lançada em caso de erro nos dados do
	 *                             espectador.
	 */
	private void lerEspectador(String arquivo) throws IOException, EspectadorException {
		List<String[]> linhas = leitorArquivo.lerArquivo(arquivo);

		for (String[] campos : linhas) {
			String nome = campos[0];
			String login = campos[1];
			String senha = campos[2];

			Espectador espectador = new Espectador(nome, login, senha);
			this.adicionarEspectador(espectador);
		}
	}

	/**
	 * Lê as informações da audiência a partir de um arquivo CSV e atualiza os dados
	 * dos espectadores.
	 *
	 * @param arquivo Nome do arquivo CSV contendo as informações da audiência.
	 * @throws IOException Exceção lançada em caso de falha na leitura do arquivo.
	 */
	private void lerAudiencia(String arquivo) throws IOException {
		List<String[]> linhas = leitorArquivo.lerArquivo(arquivo);

		for (String[] campos : linhas) {
			String login = campos[0];
			String tipo = campos[1];
			String id = campos[2];

			Espectador espectador = this.getEspectador().get(login);
			if (espectador != null) {
				Midia midia = this.midia.get(Integer.parseInt(id));

				if ("F".equalsIgnoreCase(tipo)) {
					if (midia instanceof Serie) {
						espectador.adicionarNaLista((Serie) midia);
					}
				} else if ("A".equalsIgnoreCase(tipo)) {
					if (midia instanceof Serie) {
						espectador.registrarVisualizacao((Serie) midia);
					}
				}
			}
		}
	}

	/**
	 * Lê as informações das mídias (séries e filmes) a partir de arquivos CSV e as
	 * adiciona à plataforma.
	 *
	 * @param arquivos Nomes dos arquivos CSV contendo as informações das mídias.
	 * @throws IOException               Exceção lançada em caso de falha na leitura
	 *                                   dos arquivos.
	 * @throws InvalidParameterException Exceção lançada em caso de parâmetros
	 *                                   inválidos.
	 * @throws EspectadorException       Exceção lançada em caso de erro no
	 *                                   espectador.
	 */
	private void lerMidia(String arquivoSeries, String arquivoFilmes) throws IOException, InvalidParameterException {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// Lendo séries
		List<String[]> linhasSeries = leitorArquivo.lerArquivo(arquivoSeries);

		for (String[] campos : linhasSeries) {
			String idStr = campos[0].replaceAll("[^\\d]", ""); // Remove todos os não dígitos
			int id = Integer.parseInt(idStr);
			String nome = campos[1];
			int duracao = Integer.parseInt(campos[2]);
			String dataLancamentoStr = campos[3];
			LocalDate dataLancamento = LocalDate.parse(dataLancamentoStr, dateFormatter);

			Serie serie = new Serie(id, nome, dataLancamento, duracao);
			this.midia.put(serie.getId(), serie);
		}

		// Lendo filmes
		List<String[]> linhasFilmes = leitorArquivo.lerArquivo(arquivoFilmes);

		for (String[] campos : linhasFilmes) {
			String idStr = campos[0].replaceAll("[^\\d]", ""); // Remove todos os não dígitos
			int id = Integer.parseInt(idStr);
			String nome = campos[1];
			int duracao = Integer.parseInt(campos[2]);
			String dataLancamentoStr = campos[3];
			LocalDate dataLancamento = LocalDate.parse(dataLancamentoStr, dateFormatter);

			Filme filme = new Filme(id, nome, dataLancamento, duracao, duracao, duracao);
			this.midia.put(filme.getId(), filme);
		}
	}

	public Streaming() {
		this.autenticador = new Autenticador();
	}

	/**
	 * Realiza o processo de autenticação do espectador.
	 * 
	 * @param login Nome de usuário do espectador.
	 * @param senha Senha do espectador.
	 * @return Retorna true se a autenticação for bem-sucedida, caso contrário,
	 *         retorna false.
	 */
	public boolean autenticar(String login, String senha) {
		return autenticador.autenticarEspectador(login, senha);
	}

	/**
	 * Cria um novo espectador na plataforma.
	 * 
	 * @param login Nome de usuário do novo espectador.
	 * @param senha Senha do novo espectador.
	 * @param nome  Nome do novo espectador.
	 * @return Retorna true se a criação do espectador for bem-sucedida, caso
	 *         contrário, retorna false.
	 */
	public boolean criarEspectador(String login, String senha, String nome) {
		return autenticador.criarEspectador(login, senha, nome);
	}

	/**
	 * Salva as informações das mídias (filmes e séries) em arquivos CSV.
	 *
	 * @throws IOException Exceção lançada em caso de falha ao salvar os arquivos.
	 */
	public void salvar() throws IOException {
		// Agrupa as mídias pelo tipo (classe) em um mapa
		Map<Class<? extends Midia>, List<Midia>> mapaDeMidias = ((Collection<Midia>) midia).stream()
				.collect(Collectors.groupingBy(Midia::getClass));

		// Itera sobre o mapa de mídias e salva cada tipo em um arquivo CSV separado
		mapaDeMidias.forEach((classeMidia, midiasDaClasse) -> {
			String nomeDoArquivo = "salvar/POO_" + classeMidia.getSimpleName() + ".csv";
			Path arquivo = Paths.get(nomeDoArquivo);

			try {
				if (!Files.exists(arquivo)) {
					Files.createFile(arquivo);
				}

				List<String> linhas = midiasDaClasse.stream().map(Object::toString).collect(Collectors.toList());

				Files.write(arquivo, linhas);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

	public Stream<Entry<String, Espectador>> getMidias() {
		return getMidias();
	}
}