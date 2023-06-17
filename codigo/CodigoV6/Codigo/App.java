import java.io.IOException;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.EspectadorAvaliaException;
import exceptions.EspectadorException;

public class App {
    public static void main(String[] args) {
        Autenticador autenticador = new Autenticador();
        TipoEspectador tipoEspectador = TipoEspectador.REGULAR;
        Streaming streaming = new Streaming();
        // Teste da classe LeitorArquivo
        LeitorArquivo leitor = new LeitorArquivo();

        try {
            List<String[]> series = leitor.lerArquivoSeries();
            List<String[]> filmes = leitor.lerArquivoFilmes();
            List<String[]> espectadores = leitor.lerArquivoEspectadores();
            List<String[]> audiencia = leitor.lerArquivoAudiencia();

            // Faça algo com as listas de arrays de strings lidas

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }

        // Teste da classe EspectadorException
        String nome = "John Doe";
        String caracter = "3";

        try {
            throw new EspectadorException(nome, caracter);
        } catch (EspectadorException e) {
            System.out.println(e.getMessage());
        }

        // Teste da classe EspectadorAvaliaException
        try {
            throw new EspectadorAvaliaException();
        } catch (EspectadorAvaliaException e) {
            System.out.println(e.getMessage());
        }

        // Teste da classe Idioma
        System.out.println("Idiomas disponíveis:");
        for (Idioma idioma : Idioma.values()) {
            System.out.println(idioma);
        }

        // Teste da classe Genero
        System.out.println("Gêneros disponíveis:");
        for (Genero genero : Genero.values()) {
            System.out.println(genero);
        }

        // Criando um espectador
        Espectador espectador1;
        try {
            espectador1 = new Espectador("usuario1", "senha123", "João");
            Espectador espectador2 = new Espectador("usuario2", "senha456", "Maria");

            // Criando uma mídia
            LocalDate dataLancamento = LocalDate.of(2022, 8, 15);
            Midia midia = new Filme();

            // Criando uma avaliação
            try {
                Avaliacao avaliacao = new Avaliacao(espectador1, midia, 4.5);
                midia.adicionarAvaliacao(avaliacao);
            } catch (InvalidParameterException e) {
                System.out.println("Erro ao criar avaliação: " + e.getMessage());
            }

            // Autenticando um espectador
            Autenticador espectador = new Autenticador();
            autenticador.criarEspectador("usuario3", "senha789", "Pedro");
            boolean autenticado = autenticador.autenticarEspectador("usuario3", "senha789");
            System.out.println("Espectador autenticado: " + autenticado);

            // Acessando informações da mídia
            System.out.println("Nome da mídia: " + midia.getNome());
            System.out.println("Média de notas da mídia: " + midia.calcularMediaDeNotas());

            // Adicionando e removendo mídia da lista de assistir no futuro
            espectador1.adicionarNaLista(midia);
            espectador1.retirarDaLista(midia);

            // Buscando mídias por gênero e idioma
            Genero genero = Genero.ACAO;
            List<Midia> midiasPorGenero = espectador1.buscarGenero(genero);
            System.out.println("Mídias do gênero " + genero + ":");
            for (Midia m : midiasPorGenero) {
                System.out.println(m.getNome());
            }

            Idioma idioma = Idioma.PORTUGUES;
            List<Midia> midiasPorIdioma = espectador1.buscarIdioma(idioma);
            System.out.println("Mídias do idioma " + idioma + ":");
            for (Midia m : midiasPorIdioma) {
                System.out.println(m.getNome());
            }

            // Registrando visualização e verificando quantidade de visualizações
            espectador1.registrarVisualizacao(midia);
            int visualizacoes = midia.getVisualizacao();
            System.out.println("Visualizações da mídia: " + visualizacoes);

            // Avaliando uma mídia
            try {
                espectador1.avaliarMidia(4.0, midia);
            } catch (EspectadorAvaliaException | InvalidParameterException e) {
                System.out.println("Erro ao avaliar mídia: " + e.getMessage());
            }

            // Obtendo a lista de avaliações do espectador
            List<Avaliacao> avaliacoes = espectador1.getAvaliacoes();
            System.out.println("Avaliações do espectador:");
            for (Avaliacao avaliacao : avaliacoes) {
                System.out.println(avaliacao);
            }

            // Obtendo as datas em que o espectador assistiu séries
            List<LocalDate> datasSeriesAssistidas = espectador1.getDatasSeriesAssistidas();
            System.out.println("Datas de séries assistidas pelo espectador:");
            for (LocalDate data : datasSeriesAssistidas) {
                System.out.println(data);
            }

            // Alterando nome e senha do espectador
            espectador1.setNome("Joana");
            espectador1.setSenha("novaSenha");

            // Obtendo informações do espectador
            System.out.println("Nome do espectador: " + espectador1.getNome());
            System.out.println("Senha do espectador: " + espectador1.getSenha());

        } catch (EspectadorException e) {
            System.out.println("Erro ao criar espectador: " + e.getMessage());
        }

        // Criando uma série
        try {
            LocalDate dataLancamento = LocalDate.of(2020, 5, 10);
            Serie serie1 = new Serie(1, "Série 1", dataLancamento, 10);

            // Obtendo informações da série
            System.out.println("ID da série: " + serie1.getID_Serie());
            System.out.println("Nome da série: " + serie1.getNome());
            System.out.println("Data de lançamento da série: " + serie1.getDataLancamento());
            System.out.println("Quantidade de episódios da série: " + serie1.getQuantidadeEpisodios());
        } catch (InvalidParameterException e) {
            System.out.println("Erro ao criar série: " + e.getMessage());
        }


        try {
            // Criando um filme
            LocalDate dataLancamento = LocalDate.of(2022, 8, 15);
            Filme filme = new Filme();

            // Obtendo informações do filme
            System.out.println("ID do filme: " + filme.getID_Filme());
            System.out.println("Nome do filme: " + filme.getNome());
            System.out.println("Data de lançamento do filme: " + filme.getDataLancamento());
            System.out.println("Duração do filme: " + filme.getDuracao());

            // Exibindo dados do filme
            System.out.println("Dados do filme: " + filme.toString());

        } catch (InvalidParameterException e) {
            System.out.println("Erro ao criar filme: " + e.getMessage());
        }



        try {
            // Criando uma série
            LocalDate dataLancamento = LocalDate.of(2020, 5, 10);
            Serie serie1 = new Serie(1, "Série 1", dataLancamento, 10);

            // Obtendo informações da série
            System.out.println("ID da série: " + serie1.getID_Serie());
            System.out.println("Nome da série: " + serie1.getNome());
            System.out.println("Data de lançamento da série: " + serie1.getDataLancamento());
            System.out.println("Quantidade de episódios da série: " + serie1.getQuantidadeEpisodios());

            // Exibindo dados da série
            System.out.println("Dados da série: " + serie1.toString());

        } catch (InvalidParameterException e) {
            System.out.println("Erro ao criar série: " + e.getMessage());
        }
    
    
    try {
        // Criando uma mídia
        LocalDate dataLancamento = LocalDate.of(2022, 10, 20);
        Midia midia1 = new Filme(1, "Filme 1", dataLancamento, 120, 3, 2);

        // Obtendo informações da mídia
        System.out.println("ID da mídia: " + midia1.getId());
        System.out.println("Nome da mídia: " + midia1.getNome());
        System.out.println("Data de lançamento da mídia: " + midia1.getDataLancamento());
        System.out.println("Mídia é lançamento: " + midia1.isLancamento());

        // Adicionando uma avaliação à mídia
        Espectador espectador = new Espectador(caracter, nome, null);
        Avaliacao avaliacao = new Avaliacao(espectador, midia1, 0);
        midia1.adicionarAvaliacao(avaliacao);

        // Calculando a média das notas atribuídas à mídia
        double mediaNotas = midia1.calcularMediaDeNotas();
        System.out.println("Média das notas: " + mediaNotas);

        // Registrando a visualização da mídia
        midia1.registrarVisualizacao();
        System.out.println("Visualizações da mídia: " + midia1.getVisualizacao());

        // Registrando a data em que a mídia está sendo assistida
        LocalDate dataAssistida = LocalDate.now();
        midia1.registrarDataAssistida(dataAssistida);
        System.out.println("Data em que a mídia foi assistida: " + midia1.getDataAssistida());

    } catch (InvalidParameterException e) {
        System.out.println("Erro ao criar mídia: " + e.getMessage());
    }  


    // Criar espectadores
    autenticador.criarEspectador("usuario1", "senha123", "Usuário 1");
    autenticador.criarEspectador("usuario2", "senha456", "Usuário 2");

    // Autenticar espectadores
    boolean autenticado1 = autenticador.autenticarEspectador("usuario1", "senha123");
    boolean autenticado2 = autenticador.autenticarEspectador("usuario2", "senha456");
    boolean autenticado3 = autenticador.autenticarEspectador("usuario1", "senha456");

    // Verificar autenticação
    System.out.println("Usuário 1 autenticado: " + autenticado1);
    System.out.println("Usuário 2 autenticado: " + autenticado2);
    System.out.println("Usuário 1 autenticado com senha incorreta: " + autenticado3);



    // Exemplo de uso da classe TipoEspectador
    System.out.println("O espectador está autenticado? " + tipoEspectador.isAutenticado()); // false

    tipoEspectador.autenticar();
    System.out.println("O espectador está autenticado? " + tipoEspectador.isAutenticado()); // true

    System.out.println("O espectador é um especialista? " + tipoEspectador.isEspecialista()); // false
    System.out.println("O espectador é um profissional? " + tipoEspectador.isProfissional()); // false

    int quantidadeMidiasAssistidas = 3;
    System.out.println("O espectador pode comentar uma mídia? " + tipoEspectador.podeComentarMidia(quantidadeMidiasAssistidas)); // false
    System.out.println("O espectador pode avaliar uma mídia? " + tipoEspectador.podeAvaliarMidia()); // true

    LocalDate dataReferencia = LocalDate.now();
    List<LocalDate> datasSeriesAssistidas = new ArrayList<>();
    datasSeriesAssistidas.add(LocalDate.of(2023, 1, 10));
    datasSeriesAssistidas.add(LocalDate.of(2023, 2, 15));
    datasSeriesAssistidas.add(LocalDate.of(2023, 3, 20));
    System.out.println("O espectador especialista pode voltar a ser regular no próximo mês? " + tipoEspectador.podeVoltarSerRegularNoMes(dataReferencia, datasSeriesAssistidas)); // false



    

   }
    
}