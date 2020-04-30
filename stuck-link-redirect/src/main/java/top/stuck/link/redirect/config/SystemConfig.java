package top.stuck.link.redirect.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import top.stuck.link.core.handler.builder.MessageBuilder;
import top.stuck.link.core.handler.consumer.MessageConsumer;
import top.stuck.link.core.handler.consumer.SimpleMessageConsumer;
import top.stuck.link.core.handler.pipline.LinkedBlockingQueuePipeline;
import top.stuck.link.core.handler.pipline.MessagePipline;
import top.stuck.link.core.handler.producer.MessageProducer;
import top.stuck.link.core.handler.producer.SimpleMessageProducer;

/**
 * Created on 2019-11-07
 *
 * @author Octopus
 */
@Component
@Configuration
public class SystemConfig {

    /**
     * 消息队列长度
     */
    @Value("${link.queue-size:1024}")
    private Integer queueSize;

    /**
     * 默认构建类-消息构建q
     * @return
     */
    @Bean
    public MessageBuilder builder() {
        return new MessageBuilder();
    }

    /**
     * 默认管道类-ArrayBlockingQueue实现
     * @return
     */
    @Bean
    public MessagePipline pipeline() {
        return new LinkedBlockingQueuePipeline(queueSize);
    }

    /**
     * 初始化消息生产类
     * @param builder
     * @param pipeline
     * @return
     */
    @Bean
    public MessageProducer producer(MessageBuilder builder, MessagePipline pipeline) {
        return new SimpleMessageProducer(builder, pipeline);
    }


    /**
     * 初始化消息消费类
     * @return
     */
//    @Bean
    public MessageConsumer consumer() {
        return new SimpleMessageConsumer();
    }

    public Integer getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Integer queueSize) {
        this.queueSize = queueSize;
    }
}
