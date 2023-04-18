import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) throws ParseException {
        
        // Leitura do arquivo de espectadores
        List<classeEspectador> espectadores = classeEspectador.lerEspectador();

        // Exibição dos dados dos espectadores
		for (classeEspectador e : espectadores) {
		    System.out.println("Nome: " + e.getNome());
		    System.out.println("Login: " + e.getLogin());
		    System.out.println("Senha: " + e.getSenha());
		    System.out.println("-------------------------------------------------------");
		} 
        
        try {
            // Leitura do arquivo de séries
            ArrayList<classeSerie> series = classeSerie.lerSeries();
            // Exibição dos dados das séries
            for (classeSerie serie : series) {
                System.out.println("ID: " + serie.getID_Serie());
                System.out.println("Nome: " + serie.getNome());
                System.out.println("Data de lançamento: " + serie.getDataLancamento());
                System.out.println("-------------------------------------------------------");
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        } 
        
        // Criação de algumas séries
        classeSerie serie1 = new classeSerie(0, null, null, null, null);
        classeSerie serie2 = new classeSerie(0, null, null, null, null);
        classeSerie serie3 = new classeSerie(0, null, null, null, null);
        
        // Criação de uma lista de séries
        ClasseLista listaSerie = new ClasseLista();
        
        // Adição de séries à lista de séries
        listaSerie.adicionarElemento(serie1);
        listaSerie.adicionarElemento(serie2);
        listaSerie.adicionarElemento(serie3);
        
        // Busca de séries por gênero
        boolean temSerieDrama = listaSerie.buscarGenero("Drama");
        boolean temSerieComedia = listaSerie.buscarGenero("Comédia");
        
        System.out.println("Tem série de drama? " + temSerieDrama);
        System.out.println("Tem série de comédia? " + temSerieComedia);
        
        // Criação de um objeto de login
        classeEspectador login = new classeEspectador(null, null, null);
        
        // Criação de um objeto de streaming
        ClasseStreaming streaming = new ClasseStreaming(login);
        
        // Adição de séries para assistir no futuro
        streaming.adicionarParaAssistir(serie1);
        streaming.adicionarParaAssistir(serie2);
        
        // Adição de série já assistida
        streaming.adicionarJaAssistida("Friends");
        
        // Busca de série por nome
        boolean temSerieStrangerThings = streaming.buscarNome("Stranger Things");
        
        System.out.println("Tem série Stranger Things na lista de séries para assistir? " + temSerieStrangerThings);
        
        // Troca de série de lista (de F para A, ou vice-versa)
        streaming.trocarDeLista("Stranger Things");
    }
}
