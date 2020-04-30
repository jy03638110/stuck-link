package top.stuck.link.core.handler.producer;

import top.stuck.link.core.exception.ProducerException;
import top.stuck.link.core.handler.builder.Builder;
import top.stuck.link.core.handler.pipline.Pipeline;
import top.stuck.link.core.model.MessageModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2019-11-07
 *
 * @author Octopus
 */
public abstract class MessageProducer extends Producer<MessageModel> {

    public MessageProducer(Builder<MessageModel> builder, Pipeline<MessageModel> pipeline) {
        super(builder, pipeline);
    }

    @Override
    public final MessageModel build(HttpServletRequest request) {
        return builder.build(request);
    }

    @Override
    public abstract boolean send(MessageModel messageModel) throws ProducerException;

    @Override
    public final boolean buildAndSend(HttpServletRequest request) throws ProducerException {
        MessageModel messageModel = build(request);
        return send(messageModel);
    }
}
