package top.stuck.link.core.handler.consumer;

import top.stuck.link.core.exception.ConsumerException;

/**
 * Created on 2019-11-07
 *
 * @author Octopus
 */
public interface Consumer<T> {

    boolean consume(T t) throws ConsumerException;
}
