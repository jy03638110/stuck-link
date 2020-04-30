package top.stuck.link.core.handler.pipline;

import top.stuck.link.core.model.MessageModel;

/**
 * Created on 2020-04-27
 *
 * @author Octopus
 */
public abstract class MessagePipline implements Pipeline<MessageModel> {

    /**
     * 向管道放消息
     * @param messageModels 消息
     * @return 放入成功的消息
     */
    @Override
    public abstract Integer push(MessageModel... messageModels);

    /**
     * 从管道中读取消息
     * @return 消息
     */
    @Override
    public abstract MessageModel poll();

    /**
     * 管道中消息数
     * @return 消息数
     */
    @Override
    public abstract Integer length();
}
