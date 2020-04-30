package top.stuck.link.core.handler.consumer;

import top.stuck.link.core.exception.ConsumerException;
import top.stuck.link.core.model.MessageModel;

/**
 * Created on 2020-04-27
 *
 * @author Octopus
 */
public class SimpleMessageConsumer extends MessageConsumer {

    @Override
    public boolean consume(MessageModel messageModel) throws ConsumerException {
        System.out.println(messageModel);
        return true;
    }
}
