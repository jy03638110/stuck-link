package top.stuck.link.redirect.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import top.stuck.link.core.factory.StuckThreadFactory;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 异步任务配置类
 * Created on 2020-04-26
 *
 * @author Octopus
 */
@Configuration
@EnableAsync
@ConditionalOnProperty(prefix = "link.async", name = "enabled", havingValue = "true")
public class AsyncConfig implements AsyncConfigurer {

    @Value("${link.async.pool-size:5}")
    private Integer asyncPoolSize;

    /**
     * Async异步任务线程池
     * @return
     */
    @Bean
    @Override
    public Executor getAsyncExecutor() {
        if (asyncPoolSize <= 0) {
            throw new IllegalArgumentException("config link.async.pool-size must be greater than 0");
        }
        return new ConcurrentTaskExecutor(Executors.newFixedThreadPool(asyncPoolSize, new StuckThreadFactory("async")));
    }

    /**
     * 异步任务异常处理接口实现
     * @return
     */
    @Bean
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SpringAsyncExceptionHandler();
    }

    /**
     * 异步任务异常处理实现类
     */
    class SpringAsyncExceptionHandler implements AsyncUncaughtExceptionHandler{
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            System.out.println("异步错误");
            throwable.printStackTrace();
        }
    }
}
