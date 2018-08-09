package bsc.example.objective.exception;

/**
 * Custom exception which represents exceptional state due to invalid user input.
 *
 * @author Yahor
 */
public class InvalidUserInputException extends Exception {

    public InvalidUserInputException(String errorText) {
        super(errorText);
    }

    public InvalidUserInputException(String errorText, Throwable e) {
        super(errorText, e);
    }

    public InvalidUserInputException(Throwable e) {
        super(e);
    }
}
