package top.stuck.link.core.handler.producer;

import top.stuck.link.core.handler.builder.Builder;
import top.stuck.link.core.handler.pipline.Pipeline;
import top.stuck.link.core.model.MessageModel;

/**
 * Created on 2020-04-27
 *
 * @author Octopus
 */
public class SimpleMessageProducer extends MessageProducer {

    public SimpleMessageProducer(Builder<MessageModel> builder, Pipeline<MessageModel> pipeline) {
        super(builder, pipeline);
    }

    @Override
    public boolean send(MessageModel messageModel) {
        try {
            Integer push = pipeline.push(messageModel);
            if (push == null || push == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
