package exceptions;

import java.io.Serial;

/**
 * Exception which shall be thrown in order to stop execution of current test
 */
public class StopTestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Stop test exception.
     *
     * @param message the message
     */
    public StopTestException(String message) {
        super(message);
    }
}
