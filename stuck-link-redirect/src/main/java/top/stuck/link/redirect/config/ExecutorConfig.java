package top.stuck.link.redirect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.stuck.link.core.executor.StuckLinkExecutor;
import top.stuck.link.core.executor.StuckLinkSpringExecutor;
import top.stuck.link.core.handler.consumer.MessageConsumer;
import top.stuck.link.core.handler.pipline.MessagePipline;

import java.util.List;

/**
 * Created on 2020-04-26
 *
 * @author Octopus
 */
@Configuration
public class ExecutorConfig {

    @Value("${link.consumer-pool-size}")
    private Integer poolSize;

    @Bean
    @Autowired
    public StuckLinkExecutor stuckLinkExecutor(List<MessageConsumer> consumerList, MessagePipline pipeline) {
        StuckLinkSpringExecutor stuckLinkSpringExecutor = new StuckLinkSpringExecutor(poolSize);
        stuckLinkSpringExecutor.setConsumerList(consumerList);
        stuckLinkSpringExecutor.setPipeline(pipeline);
        return stuckLinkSpringExecutor;
    }
}
