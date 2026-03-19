package application.exceptions;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException(String msg) {
        super(msg);
    }
}