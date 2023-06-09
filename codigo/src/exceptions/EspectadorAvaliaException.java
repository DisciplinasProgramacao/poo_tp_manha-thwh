package exceptions;

public class EspectadorAvaliaException extends Exception {
   

	public EspectadorAvaliaException() {
        super("Você precisa ter visto a mídia para poder avalia-lá");
    }
}
