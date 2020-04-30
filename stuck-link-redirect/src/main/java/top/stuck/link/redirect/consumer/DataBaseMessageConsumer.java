package top.stuck.link.redirect.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.stuck.link.redirect.service.MessageService;
import top.stuck.link.core.exception.ConsumerException;
import top.stuck.link.core.handler.consumer.MessageConsumer;
import top.stuck.link.core.model.MessageModel;

/**
 * Created on 2020-04-27
 *
 * @author Octopus
 */
@Component
public class DataBaseMessageConsumer extends MessageConsumer {

    @Autowired
    private MessageService messageService;

    @Override
    public boolean consume(MessageModel messageModel) throws ConsumerException {
        try {
            Integer count = messageService.addMessage(messageModel);
            if (count > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new ConsumerException(e);
        }
    }
}