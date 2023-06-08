package exceptions;

import java.security.InvalidParameterException;

public class EspectadorException extends InvalidParameterException {
    public EspectadorException(String nome, String caracter) {
        super("O "+ nome +" do Cliente deve possuir mais de "+ caracter +" caracteres!");
}
}