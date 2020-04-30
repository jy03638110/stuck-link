package top.stuck.link.core.exception;

/**
 * Created on 2020-04-27
 *
 * @author Octopus
 */
public class StuckException extends Throwable {

    public StuckException() {

    }

    public StuckException(String message) {
        super(message);
    }

    public StuckException(Throwable throwable) {
        super(throwable);
    }
}
