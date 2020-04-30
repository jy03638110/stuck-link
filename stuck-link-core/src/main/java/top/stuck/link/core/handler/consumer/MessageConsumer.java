package top.stuck.link.core.handler.consumer;

import top.stuck.link.core.exception.ConsumerException;
import top.stuck.link.core.model.MessageModel;

/**
 * Created on 2019-11-07
 *
 * @author Octopus
 */
public abstract class MessageConsumer implements Consumer<MessageModel> {

    @Override
    public abstract boolean consume(MessageModel messageModel) throws ConsumerException;
}
