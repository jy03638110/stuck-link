package top.stuck.link.core.handler.pipline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.stuck.link.core.model.MessageModel;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue为底层的消息管道，push和poll采用相同锁，性能较差
 * Created on 2019-11-11
 *
 * @author Octopus
 */
public class ArrayBlockingQueuePipeline extends MessagePipline {

    private static Logger logger = LoggerFactory.getLogger(ArrayBlockingQueuePipeline.class);

    private ArrayBlockingQueue<MessageModel> messageQueue;

    public ArrayBlockingQueuePipeline() {
        messageQueue = new ArrayBlockingQueue<MessageModel>(DEFAULT_QUEUE_SIZE);
    }

    public ArrayBlockingQueuePipeline(Integer queueSize) {
        messageQueue = new ArrayBlockingQueue<MessageModel>(queueSize);
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
