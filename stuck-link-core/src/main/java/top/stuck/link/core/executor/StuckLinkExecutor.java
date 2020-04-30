package top.stuck.link.core.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.stuck.link.core.contants.SystemContant;
import top.stuck.link.core.exception.ConsumerException;
import top.stuck.link.core.factory.StuckThreadFactory;
import top.stuck.link.core.handler.consumer.MessageConsumer;
import top.stuck.link.core.handler.pipline.MessagePipline;
import top.stuck.link.core.model.MessageModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2020-04-26
 *
 * @author Octopus
 */
public class StuckLinkExecutor {

    private static Logger logger = LoggerFactory.getLogger(StuckLinkExecutor.class);

    private ExecutorService threadPool;

    private Thread executorThread;

    private volatile boolean toStop = false;

    private MessagePipline pipeline;

    private List<MessageConsumer> consumerList;

    public StuckLinkExecutor(){
        threadPool = Executors.newFixedThreadPool(SystemContant.DEFAULT_POOL_SIZE, new StuckThreadFactory("consumer"));
    }

    public StuckLinkExecutor(int poolSize) {
        if (poolSize <= 0) {
            throw new IllegalArgumentException("threadPool size must be greater than 0");
        }
        threadPool = Executors.newFixedThreadPool(SystemContant.DEFAULT_POOL_SIZE, new StuckThreadFactory("consumer"));
    }

    public void start() {
        if (consumerList == null || consumerList.isEmpty()) {
            logger.warn("启动StuckLinkExecutor失败，没有消费任务");
            return;
        }
        if (pipeline == null) {
            logger.warn("启动StuckLinkExecutor失败，没有消费数据来源");
            return;
        }
        executorThread = new Thread(new Runnable() {
            public void run() {
                while (!toStop) {
                    final MessageModel item = pipeline.poll();
                    if (item != null) {
                        threadPool.submit(new Runnable() {
                            public void run() {
                                for (MessageConsumer consumer : consumerList) {
                                    try{
                                        boolean flag = consumer.consume(item);
                                        if (!flag) {
                                            logger.info("消费线程" + Thread.currentThread().getName() + "消费消息：" + item + "失败");
                                        }
                                    }catch (ConsumerException e) {
                                        logger.error("消费线程" + Thread.currentThread().getName() + "消费消息：" + item + "出错", e);
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
        executorThread.setDaemon(true);
        executorThread.setName("stuck-link, executor StuckLinkExecutor");
        executorThread.start();
    }

    /**
     * 停止执行器运行
     */
    public void toStop() {
        toStop = true;
        if (executorThread == null) {
            return;
        }
        // interrupt and wait
        executorThread.interrupt();
        try {
            executorThread.join();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public MessagePipline getPipeline() {
        return pipeline;
    }

    public void setPipeline(MessagePipline pipeline) {
        this.pipeline = pipeline;
    }

    public List<MessageConsumer> getConsumerList() {
        return consumerList;
    }

    public void setConsumerList(List<MessageConsumer> consumerList) {
        this.consumerList = consumerList;
    }
}
