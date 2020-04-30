package top.stuck.link.core.exception;

/**
 * Created on 2020-04-29
 *
 * @author Octopus
 */
public class ProducerException extends StuckException {

    public ProducerException() {

    }

    public ProducerException(String message) {
        super(message);
    }

    public ProducerException(Throwable throwable) {
        super(throwable);
    }
}
