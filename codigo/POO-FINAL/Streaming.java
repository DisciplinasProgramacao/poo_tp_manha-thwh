import java.io.IOException;
import java.io.Serializable;
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
	 * @throws IOException               Exce��o lan�ada em caso de falha na leitura
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
	 * Construtor da classe Streaming para cria��o de uma inst�ncia com um nome e um
	 * valor booleano.
	 * 
	 * @param nome  O nome da streaming.
	 * @param dummy Um valor booleano utilizado para inicializa��o.
	 */
	public Streaming(String nome, boolean dummy) {
		this.nome = nome;
		this.midia = new HashMap<Integer, Midia>();
		this.espectador = new HashMap<String, Espectador>();

		this.atualEspectador = null;
	}

	/**
	 * M�todo para realizar o login de um espectador na plataforma.
	 * 
	 * @param login Nome de usu�rio do especatdor.
	 * @param senha Senha do espectador.
	 * @return Retorna o objeto Espectador caso o login seja bem-sucedido, caso
	 *         contr�rio, retorna null.
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
	 * Adiciona uma s�rie � plataforma.
	 * 
	 * @param serie Objeto da classe Midia representando a s�rie a ser adicionada.
	 */
	public void adicionarSerie(Midia serie) {
		this.midia.put(serie.getId(), serie);
	}

	/**
	 * Adiciona um cliente � plataforma.
	 * 
	 * @param espectador Objeto da classe Espectador representando o espectador a
	 *                   ser adicionado.
	 */
	public void adicionarEspectador(Espectador espectador) {
		this.espectador.put(espectador.getLogin(), espectador);
	}

	/**
	 * Adiciona um filme � plataforma.
	 * 
	 * @param filme Objeto da classe Midia representando o filme a ser adicionado.
	 */
	public void adicionarFilme(Midia filme) {
		this.midia.put(filme.getId(), filme);
	}

	/**
	 * Busca m�dias na plataforma com base no g�nero.
	 *
	 * @param genero G�nero a ser usado como filtro.
	 * @return Retorna uma lista de m�dias que correspondem ao g�nero informado.
	 */
	public List<Midia> buscarGenero(Genero genero) {
		if (genero == null) {
			return Collections.emptyList(); // Retorna uma lista vazia se o g�nero for nulo
		}

		return this.midia.values().stream().filter(m -> m.getGenero() == genero).collect(Collectors.toList());
	}

	/**
	 * Busca m�dias na plataforma com base no idioma.
	 *
	 * @param idioma Idioma a ser usado como filtro.
	 * @return Retorna uma lista de m�dias que correspondem ao idioma informado.
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
	 * L� as informa��es dos espectadores a partir de um arquivo CSV e os adiciona �
	 * plataforma.
	 *
	 * @param arquivo Nome do arquivo CSV contendo as informa��es dos espectadores.
	 * @throws IOException         Exce��o lan�ada em caso de falha na leitura do
	 *                             arquivo.
	 * @throws EspectadorException Exce��o lan�ada em caso de erro nos dados do
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
	 * L� as informa��es da audi�ncia a partir de um arquivo CSV e atualiza os dados
	 * dos espectadores.
	 *
	 * @param arquivo Nome do arquivo CSV contendo as informa��es da audi�ncia.
	 * @throws IOException Exce��o lan�ada em caso de falha na leitura do arquivo.
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
	 * L� as informa��es das m�dias (s�ries e filmes) a partir de arquivos CSV e as
	 * adiciona � plataforma.
	 *
	 * @param arquivos Nomes dos arquivos CSV contendo as informa��es das m�dias.
	 * @throws IOException               Exce��o lan�ada em caso de falha na leitura
	 *                                   dos arquivos.
	 * @throws InvalidParameterException Exce��o lan�ada em caso de par�metros
	 *                                   inv�lidos.
	 * @throws EspectadorException       Exce��o lan�ada em caso de erro no
	 *                                   espectador.
	 */
	private void lerMidia(String arquivoSeries, String arquivoFilmes) throws IOException, InvalidParameterException {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// Lendo s�ries
		List<String[]> linhasSeries = leitorArquivo.lerArquivo(arquivoSeries);

		for (String[] campos : linhasSeries) {
			String idStr = campos[0].replaceAll("[^\\d]", ""); // Remove todos os n�o d�gitos
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
			String idStr = campos[0].replaceAll("[^\\d]", ""); // Remove todos os n�o d�gitos
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
	 * Realiza o processo de autentica��o do espectador.
	 * 
	 * @param login Nome de usu�rio do espectador.
	 * @param senha Senha do espectador.
	 * @return Retorna true se a autentica��o for bem-sucedida, caso contr�rio,
	 *         retorna false.
	 */
	public boolean autenticar(String login, String senha) {
		return autenticador.autenticarEspectador(login, senha);
	}

	/**
	 * Cria um novo espectador na plataforma.
	 * 
	 * @param login Nome de usu�rio do novo espectador.
	 * @param senha Senha do novo espectador.
	 * @param nome  Nome do novo espectador.
	 * @return Retorna true se a cria��o do espectador for bem-sucedida, caso
	 *         contr�rio, retorna false.
	 */
	public boolean criarEspectador(String login, String senha, String nome) {
		return autenticador.criarEspectador(login, senha, nome);
	}

	/**
	 * Salva as informa��es das m�dias (filmes e s�ries) em arquivos CSV.
	 *
	 * @throws IOException Exce��o lan�ada em caso de falha ao salvar os arquivos.
	 */
	public void salvar() throws IOException {
		// Agrupa as m�dias pelo tipo (classe) em um mapa
		Map<Class<? extends Midia>, List<Midia>> mapaDeMidias = ((Collection<Midia>) midia).stream()
				.collect(Collectors.groupingBy(Midia::getClass));

		// Itera sobre o mapa de m�dias e salva cada tipo em um arquivo CSV separado
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