package bsc.example.objective.exception;


import java.io.IOException;

/**
 * Custom exception represents which represents exceptional state sue to invalid user exception
 *
 * @author Yahor
 */
public class InvalidInputException extends Exception {

    public InvalidInputException(String errorText) {
        super(errorText);
    }

    public InvalidInputException(String errorText, Throwable e) {
        super(errorText, e);
    }

    public InvalidInputException(Throwable e) {
        super(e);
    }
}
