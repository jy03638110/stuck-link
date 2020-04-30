package top.stuck.link.core.exception;

/**
 * Created on 2020-04-27
 *
 * @author Octopus
 */
public class ConsumerException extends StuckException {

    public ConsumerException() {

    }

    public ConsumerException(String message) {
        super(message);
    }

    public ConsumerException(Throwable throwable) {
        super(throwable);
    }
}
