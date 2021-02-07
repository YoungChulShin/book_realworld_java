package study.java.exceptions;

public class UnknownFileTypeException extends RuntimeException {

    public UnknownFileTypeException(String message) {
        super(message);
    }
}
