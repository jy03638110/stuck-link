package top.stuck.link.core.handler.pipline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.stuck.link.core.model.MessageModel;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue为底层的消息管道，push和poll采用不同锁，性能较好
 * Created on 2020-04-27
 *
 * @author Octopus
 */
public class LinkedBlockingQueuePipeline extends MessagePipline {

    private static Logger logger = LoggerFactory.getLogger(LinkedBlockingQueuePipeline.class);

    private LinkedBlockingQueue<MessageModel> messageQueue;

    public LinkedBlockingQueuePipeline() {
        messageQueue = new LinkedBlockingQueue<MessageModel>(DEFAULT_QUEUE_SIZE);
    }

    public LinkedBlockingQueuePipeline(Integer queueSize) {
        messageQueue = new LinkedBlockingQueue<MessageModel>(queueSize);
    }

    @Override
    public Integer push(MessageModel... models) {
        Integer count = 0;
        if (models != null && models.length > 0) {
            for (MessageModel model : models) {
                if (model != null && messageQueue.offer(model)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public MessageModel poll() {
        try {
            return messageQueue.take();
        } catch (InterruptedException e) {
            logger.error("获取消息失败");
        }
        return null;
    }

    @Override
    public Integer length() {
        return messageQueue.size();
    }
}
