import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import exceptions.EspectadorAvaliaException;
import exceptions.EspectadorException;

public class Streaming {
    private String nome;

    private HashMap<Integer, Midia> midias;

    private HashMap<String, Espectador> espectador;
    private HashMap<Integer, Avaliacao> avaliacoes;
    private Espectador AtualEspectador;
    /**
     * Construtor da classe app.PlataformaStreaming.
     *
     * @param nome Nome da plataforma de streaming.
     * @throws IOException Exce��o lan�ada em caso de falha na leitura dos arquivos.
     * @throws InvalidParameterException
     * @throws EspectdorException
     */
    public Streaming(String nome) throws IOException, InvalidParameterException, EspectadorException {
        this.nome = nome;
        this.midias = new HashMap<Integer, Midia>();
        this.espectador = new HashMap<String, Espectador>();
        this.avaliacoes = new HashMap<Integer, Avaliacao>();

        this.AtualEspectador = null;

        this.lerMidias("C:\\POO_Series.csv", "C:\\POO_Filmes.csv");
        this.lerEspectador("C:\\POO_Espectadores.csv");
        this.lerAudiencia("C:\\POO_Audiencia.csv");

    }

    /**
     * Construtor da classe app.PlataformaStreaming para cria��o de uma inst�ncia com um nome e um valor booleano.
     *
     * @param nome O nome da plataforma de streaming.
     * @param dummy Um valor booleano utilizado para inicializa��o.
     */
    public Streaming(String nome, boolean dummy) {
        this.nome = nome;
        this.midias = new HashMap<Integer, Midia>();
        this.espectador = new HashMap<String, Espectador>();
        this.avaliacoes = new HashMap<Integer, Avaliacao>();

        this.AtualEspectador = null;
    }

    /**
     * M�todo para realizar o login de um Espectdor na plataforma.
     *
     * @param nomeUsuario Nome de usu�rio do Espectdor.
     * @param senha       Senha do Espectdor.
     * @return Retorna o objeto Espectdor caso o login seja bem-sucedido, caso
     *         contr�rio, retorna null.
     */
    public Espectador login(String login, String senha) {
    	Espectador cl = espectador.get(login);
        if(cl.getLogin().intern() == login.intern() && cl.getSenha().intern() == senha.intern()) {
            this.AtualEspectador = cl;
            return cl;
        }
        return null;
    }

    /**
     * Adiciona uma s�rie � plataforma.
     *
     * @param serie Objeto da classe midia.Midia representando a s�rie a ser adicionada.
     */
    public void adicionarSerie(Midia serie) {
        this.midias.put(serie.getId(), serie);
    }

    /**
     * Adiciona um Espectdor � plataforma.
     *
     * @param espectdor Objeto da EspectdorEspectdor representando o espectdor a ser
     *                adicionado.
     */
    public void adicionarEspectador(Espectador espectador) {
        this.espectador.put(espectador.getLogin(), espectador);
    }

    /**
     * Adiciona um filme � plataforma.
     *
     * @param filme Objeto da classe midia.Midia representando o filme a ser adicionado.
     */
    public void adicionarFilme(Midia filme) {
        this.midias.put(filme.getId(), filme);
    }

    /**
     * Filtra as m�dias da plataforma por g�nero.
     *
     * @param genero G�nero a ser usado como filtro.
     * @return Uma lista imut�vel de m�dias que correspondem ao g�nero informado. Se o g�nero for nulo ou vazio, retorna uma lista vazia.
     */
    public List<Midia> filtrarPorGenero(String genero) {
        if (genero == null || genero.isEmpty()) {
            return Collections.emptyList(); // Retorna uma lista vazia se o g�nero for nulo ou vazio
        }

        String generoLowerCase = genero.toLowerCase(); // Converter o g�nero para min�sculas

        return this.midias.values().stream()
                .filter(m -> m.getGenero() != null && m.getGenero().equalsIgnoreCase(generoLowerCase))
                .collect(Collectors.toList());
    }

    /**
     * Filtra as m�dias da plataforma por idioma.
     *
     * @param idioma Idioma a ser usado como filtro.
     * @return Uma lista imut�vel de m�dias que correspondem ao idioma informado. Se o idioma for nulo ou vazio, retorna uma lista vazia.
     */
    public List<Midia> filtrarPorIdioma(String idioma) {
        if (idioma == null || idioma.isEmpty()) {
            return Collections.emptyList(); // Retorna uma lista vazia se o idioma for nulo ou vazio
        }

        String idiomaLowerCase = idioma.toLowerCase(); // Converter o idioma para min�sculas

        return this.midias.values().stream()
                .filter(m -> m.getIdioma() != null && m.getIdioma().equalsIgnoreCase(idiomaLowerCase))
                .collect(Collectors.toList());
    }


    /**
     * Filtra as m�dias da plataforma por idioma.
     *
     * @param idioma Idioma a ser usado como filtro.
     * @return Uma lista imut�vel de m�dias que correspondem ao idioma informado. Se o idioma for nulo ou vazio, retorna uma lista vazia.
     */
    public Midia filtrarPorNome(String nome) {
        String nomeLowerCase = nome.toLowerCase(); // Converter o nome para min�sculas
        if (nome != null || !nome.isEmpty()) {
            return this.midias.values().stream()
                .filter(m -> m.getNome() != null && m.getNome().equalsIgnoreCase(nomeLowerCase))
                .findFirst()
                .orElseThrow(null);
        }
        return null;
    }

    /**
    * Registra a audi�ncia de uma m�dia pelo Espectdor atual.
    * 
    * @param nomeMidia O nome da m�dia a ter a audi�ncia registrada.
    */
    public void registrarAudiencia(String nomeMidia) {
        Midia midia = filtrarPorNome(nomeMidia);

        if (midia != null) {
            this.AtualEspectador.registrarAudiencia(midia);
        }
    }

    /**
    * Avalia uma m�dia pelo Espectdor atual.
    * 
    * @param nomeMidia O nome da m�dia a ser avaliada.
    * @param nota A nota atribu�da � m�dia.
    * @throws InvalidParameterException se a nota estiver fora do intervalo v�lido.
    * @throws EspectdorAvaliaException se ocorrer um erro ao avaliar a m�dia.
    */
    public void avaliarMidia(String nomeMidia, double nota) throws InvalidParameterException, EspectadorAvaliaException {
        Midia midia = filtrarPorNome(nomeMidia);

        if (midia != null) {
            this.AtualEspectador.avaliarMidia(nota, midia);
        }
    }

    /**
     * Adiciona uma m�dia � lista de reprodu��o do Espectdor atual.
     * 
     * @param nomeMidia O nome da m�dia a ser adicionada.
     */
    public void adicionarMidiaLista(String nomeMidia) {
        Midia midia = filtrarPorNome(nomeMidia);

        if (midia != null) {
            this.AtualEspectador.adicionarNaLista(midia);
        }
    }

    /**
    * Remove uma m�dia da lista de reprodu��o do espectdor atual.
    * 
    * @param nomeMidia O nome da m�dia a ser removida.
    */
    public void removerMidiaLista(String nomeMidia) {
        Midia midia = filtrarPorNome(nomeMidia);

        if (midia != null) {
            this.AtualEspectador.retirarDaLista(midia);
        }
    }

    /**
     * Retorna a lista de todas as m�dias que o espectdor logado j� visualizou.
     *
     * @return A lista de m�dias visualizadas pelo espectdor.
     */
    
    public List<Midia> mostrarMidiasEspectador(Espectador espectador) {
        return espectador.getJaAssistida();
    }

  

    /**
     * Retorna um HashMap contendo os Espectdor cadastrados na plataforma.
     *
     * @return HashMap contendo os Espectdor.
     */
    public HashMap<String, Espectador> getEspectador() {
        return this.espectador;
    }

    /**
     * Retorna um HashMap contendo os app.app.Espectdor cadastrados na plataforma.
     *
     * @return HashMap contendo os Espectdor.
     * @throws EspectdorException
     */
    private void lerEspectador(String arquivo) throws IOException, EspectadorException {
        try (Scanner scanner = new Scanner(new File(arquivo))) {
            String linha;
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                String[] campos = linha.split(";");
                String nomeCompleto = campos[0];
                String login = campos[1];
                String senha = campos[2];

                Espectador espectador = new Espectador(nomeCompleto, login, senha);
                this.adicionarEspectador(espectador);
            }
        }
    }


    /**
     * L� informa��es da audi�ncia de um arquivo e registra a audi�ncia na
     * plataforma.
     *
     * @param arquivo Nome do arquivo contendo as informa��es da audi�ncia.
     * @throws IOException Exce��o lan�ada em caso de falha na leitura do arquivo.
     */
    private void lerAudiencia(String arquivo) throws IOException {
        try (Scanner scanner = new Scanner(new File(arquivo))) {
            String linha;
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                String[] campos = linha.split(";");
                String login = campos[0];
                String tipo = campos[1];
                String id = campos[2];

                Espectador espectador = this.getEspectador().get(login);
                if (espectador != null) {
                    Midia midia = this.midias.get(Integer.parseInt(id));

                    if ("F".equalsIgnoreCase(tipo)) {
                        if (midia instanceof Serie) {
                        	espectador.adicionarNaLista((Serie) midia);
                        }
                    } else if ("A".equalsIgnoreCase(tipo)) {
                        if (midia instanceof Serie) {
                        	espectador.registrarAudiencia((Serie) midia);
                        }
                    }
                }

            }
        }
    }

    /**
     * L� informa��es das m�dias de arquivos e adiciona as m�dias � plataforma.
     *
     * @param arquivoSeries Nome do arquivo contendo as informa��es das s�ries.
     * @param arquivoFilmes Nome do arquivo contendo as informa��es dos filmes.
     * @throws IOException Exce��o lan�ada em caso de falha na leitura dos arquivos.
     * @throws InvalidParameterException
     */
    private void lerMidias(String arquivoSeries, String arquivoFilmes) throws IOException, InvalidParameterException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Lendo s�ries
        try (Scanner scanner = new Scanner(new File(arquivoSeries))) {
            String linha;
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                String[] campos = linha.split(";");
                String idStr = campos[0].replaceAll("[^\\d]", ""); // Remove caracteres n�o num�ricos
                int id = Integer.parseInt(idStr);
                String nome = campos[1];
                LocalDate dataDeLancamento = LocalDate.parse(campos[2], dateFormatter);

                // Os campos adicionais podem ser adicionados conforme necess�rio
                Serie serie = new Serie(id, nome, dataDeLancamento, id);
                this.adicionarSerie(serie);
            }
        }

        // Lendo filmes
        try (Scanner scanner = new Scanner(new File(arquivoFilmes))) {
            String linha;
            linha = scanner.nextLine();
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                String[] campos = linha.split(";");
                String idFilmeStr = campos[0].replaceAll("[^\\d]", ""); // Remove caracteres n�o num�ricos
                int idFilme = Integer.parseInt(idFilmeStr);
                String nome = campos[1];
                LocalDate dataDeLancamento = LocalDate.parse(campos[2], dateFormatter);
                int duracao = Integer.parseInt(campos[3]);

                // Os campos adicionais podem ser adicionados conforme necess�rio
                Filme filme = new Filme(idFilme, nome, dataDeLancamento, duracao, duracao, idFilme);
                this.adicionarFilme(filme);
            }
        }
    }

    /**
     * Adiciona um Espectdor � platctdoraforma se o nome de usu�rio n�o existir.
     * @param nomeCompleto  Nome completo do Espectdor.
     * @param nomeDeUsuario Nome de usu�rio do Espectdor.
     * @param senha         Senha do Espectdor.
     * @return Retorna true se o Espectdor foi adicionado com sucesso, caso contr�rio,
     *         retorna false.
     * @throws EspectdorException
     */
    public boolean adicionarEspectador(String nomeCompleto, String login, String senha) throws EspectadorException {
        // Verificar se o nome de usu�rio j� existe
        if (espectador.containsKey(login)) {
            return false; 
        }


        Espectador novoEspectador = new Espectador(nomeCompleto, login, senha);
        espectador.put(login, novoEspectador);
        return true;
    }


    public HashMap<Integer, Midia> getMidias() {
        return midias;
    }

    /**
     * Salva as informa��es das m�dias em arquivos CSV.
     * 
     * @throws IOException Exce��o lan�ada em caso de erro ao salvar os arquivos.
     */
    public void salvar() throws IOException {
        Map<String, List<Midia>> mapaDeMidias = new HashMap<>();

        midias.forEach((id, midia) -> {
            String nomeDaClasse = midia.getClass().getSimpleName();

            if (mapaDeMidias.containsKey(nomeDaClasse)) {
                mapaDeMidias.get(nomeDaClasse).add(midia);
            } else {
                List<Midia> listaDeMidias = new ArrayList<>();
                listaDeMidias.add(midia);
                mapaDeMidias.put(nomeDaClasse, listaDeMidias);
            }
        });

        mapaDeMidias.keySet().forEach(classe -> {
            Path nomeDoArquivo = Paths.get("salvar/POO_" + classe + ".csv");
            List<Midia> midiasDaClasse = mapaDeMidias.get(classe);

            try {
                if (!Files.exists(nomeDoArquivo)) {
                    Files.createFile(nomeDoArquivo);
                }

                Files.write(nomeDoArquivo, midiasDaClasse.stream()
                                            .map(Object::toString)
                                            .collect(Collectors.toList()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Espectador getAtualEspectador() {
        return AtualEspectador;
    }

}}