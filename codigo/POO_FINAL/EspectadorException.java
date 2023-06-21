package exceptions;

import java.security.InvalidParameterException;

public class EspectadorException extends InvalidParameterException {
    public EspectadorException(String nome, String caracter) {
        super("!!! O "+ nome +" do Espectador deve possuir mais de "+ caracter +" caracteres !!!");
}
}