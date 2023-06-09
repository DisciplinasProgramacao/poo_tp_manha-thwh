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
import java.util.stream.Collectors;
import exceptions.EspectadorException;
import java.time.format.DateTimeFormatter;

public class Streaming {
    private String nome;
    private HashMap<Integer, Midia> midia;
    private HashMap<String, Espectador> espectador;
    private Espectador atualEspectador;
    private LeitorArquivo leitorArquivo;

    /**
     * Construtor da classe PlataformaStreaming.
     * 
     * @param nome Nome da plataforma de streaming.
     * @throws IOException Exceção lançada em caso de falha na leitura dos arquivos.
     * @throws InvalidParameterException
     * @throws ClienteException
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
     * Construtor da classe PlataformaStreaming para criação de uma instância com um nome e um valor booleano.
     * 
     * @param nome O nome da plataforma de streaming.
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
     * @param nomeUsuario Nome de usuário do especatdor.
     * @param senha       Senha do espectador.
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
     * @param espectador Objeto da classe Espectador representando o espectador a ser
     *                adicionado.
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

    public List<Midia> buscarGenero(Genero genero) {
        if (genero == null) {
            return Collections.emptyList(); // Retorna uma lista vazia se o gênero for nulo
        }

        return this.midia.values().stream()
                .filter(m -> m.getGenero() == genero)
                .collect(Collectors.toList());
    }

    public List<Midia> buscarIdioma(Idioma idioma) {
        if (idioma == null) {
            return Collections.emptyList(); // Retorna uma lista vazia se o idioma for nulo
        }

        return this.midia.values().stream()
                .filter(m -> m.getIdioma() == idioma)
                .collect(Collectors.toList());
    }


    /**
     * Filtra as mídias da plataforma pela quantidade de episódios.
     * 
     * @param quantEpisodios Quantidade de episódios a ser usada como filtro.
     * @return Retorna uma lista de mídias que correspondem à quantidade de
     *         episódios informada.
     */
    public List<Midia> BuscarQtdEpisodios(int quantEpisodios) {
        return this.midia.values().stream()
                .filter(s -> s.getQuantidadeEpisodios() == quantEpisodios)
                .collect(Collectors.toList());
    }

    /**
     * Retorna um HashMap contendo os espectadores cadastrados na plataforma.
     * 
     * @return HashMap contendo os espectdores.
     */
    public HashMap<String, Espectador> getEspectador() {
        return this.espectador;
    }

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

            Filme filme = new Filme(id, nome, dataLancamento, duracao);
            this.midia.put(filme.getId(), filme);
        }
    }


	public boolean adicionarEspectador(String nome, String login, String senha) throws EspectadorException {
        // Verificar se o nome de usuário já existe
        if (espectador.containsKey(login)) {
            return false; // Nome de usuário já existe, não é possível adicionar o espectdor
        }

        // Criar uma nova instância do espectadore adicioná-la ao HashMap
        Espectador novoEspectador = new Espectador(nome, login, senha);
        espectador.put(login, novoEspectador);
        return true; // Espector adicionado com sucesso
    }


    
    public void salvar() throws IOException {
        Map<Class<? extends Midia>, List<Midia>> mapaDeMidias = ((Collection<Midia>) midia).stream()
                .collect(Collectors.groupingBy(Midia::getClass));
    
        mapaDeMidias.forEach((classeMidia, midiasDaClasse) -> {
            String nomeDoArquivo = "salvar/POO_" + classeMidia.getSimpleName() + ".csv";
            Path arquivo = Paths.get(nomeDoArquivo);
    
            try {
                if (!Files.exists(arquivo)) {
                    Files.createFile(arquivo);
                }
    
                List<String> linhas = midiasDaClasse.stream()
                        .map(Object::toString)
                        .collect(Collectors.toList());
    
                Files.write(arquivo, linhas);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    
}