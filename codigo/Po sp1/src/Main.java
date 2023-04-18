import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) throws ParseException {
        
        // Leitura do arquivo de espectadores
        List<classeEspectador> espectadores = classeEspectador.lerEspectador();

        // Exibi��o dos dados dos espectadores
		for (classeEspectador e : espectadores) {
		    System.out.println("Nome: " + e.getNome());
		    System.out.println("Login: " + e.getLogin());
		    System.out.println("Senha: " + e.getSenha());
		    System.out.println("-------------------------------------------------------");
		} 
        
        try {
            // Leitura do arquivo de s�ries
            ArrayList<classeSerie> series = classeSerie.lerSeries();
            // Exibi��o dos dados das s�ries
            for (classeSerie serie : series) {
                System.out.println("ID: " + serie.getID_Serie());
                System.out.println("Nome: " + serie.getNome());
                System.out.println("Data de lan�amento: " + serie.getDataLancamento());
                System.out.println("-------------------------------------------------------");
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("Arquivo n�o encontrado.");
        } 
        
        // Cria��o de algumas s�ries
        classeSerie serie1 = new classeSerie(0, null, null, null, null);
        classeSerie serie2 = new classeSerie(0, null, null, null, null);
        classeSerie serie3 = new classeSerie(0, null, null, null, null);
        
        // Cria��o de uma lista de s�ries
        ClasseLista listaSerie = new ClasseLista();
        
        // Adi��o de s�ries � lista de s�ries
        listaSerie.adicionarElemento(serie1);
        listaSerie.adicionarElemento(serie2);
        listaSerie.adicionarElemento(serie3);
        
        // Busca de s�ries por g�nero
        boolean temSerieDrama = listaSerie.buscarGenero("Drama");
        boolean temSerieComedia = listaSerie.buscarGenero("Com�dia");
        
        System.out.println("Tem s�rie de drama? " + temSerieDrama);
        System.out.println("Tem s�rie de com�dia? " + temSerieComedia);
        
        // Cria��o de um objeto de login
        classeEspectador login = new classeEspectador(null, null, null);
        
        // Cria��o de um objeto de streaming
        ClasseStreaming streaming = new ClasseStreaming(login);
        
        // Adi��o de s�ries para assistir no futuro
        streaming.adicionarParaAssistir(serie1);
        streaming.adicionarParaAssistir(serie2);
        
        // Adi��o de s�rie j� assistida
        streaming.adicionarJaAssistida("Friends");
        
        // Busca de s�rie por nome
        boolean temSerieStrangerThings = streaming.buscarNome("Stranger Things");
        
        System.out.println("Tem s�rie Stranger Things na lista de s�ries para assistir? " + temSerieStrangerThings);
        
        // Troca de s�rie de lista (de F para A, ou vice-versa)
        streaming.trocarDeLista("Stranger Things");
    }
}
