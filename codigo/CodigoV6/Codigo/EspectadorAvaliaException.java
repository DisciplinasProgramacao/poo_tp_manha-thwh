package exceptions;

public class EspectadorAvaliaException extends Exception {
   

	public EspectadorAvaliaException() {
        super("!!! VOCÊ PRECISA TER VISTO A MIDIA PARA PODER AVALIAR !!!");
    }
}
