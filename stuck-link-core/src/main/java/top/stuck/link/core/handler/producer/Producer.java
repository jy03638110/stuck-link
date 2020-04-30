package top.stuck.link.core.handler.producer;

import top.stuck.link.core.exception.ProducerException;
import top.stuck.link.core.handler.builder.Builder;
import top.stuck.link.core.handler.pipline.Pipeline;

import javax.servlet.http.HttpServletRequest;

/**
 * 生产者的通用父类
 * Created on 2019-11-07
 * @author Octopus
 */
public abstract class Producer<T> {

    protected Builder<T> builder;

    protected Pipeline<T> pipeline;

    Producer(Builder<T> builder, Pipeline<T> pipeline) {
        this.builder = builder;
        this.pipeline = pipeline;
    }

    /**
     * 构建元素
     * @param request
     * @return
     */
    public T build(HttpServletRequest request){
        return builder.build(request);
    }

    /**
     * 发送元素
     * @param t
     * @return
     */
    public abstract boolean send(T t) throws ProducerException;

    /**
     * 构建并发送元素
     * @param request
     * @return
     */
    public abstract boolean buildAndSend(HttpServletRequest request) throws ProducerException;

    /**
     * 获取管道中元素数量
     * @return
     */
    public final Integer length() throws ProducerException {
        try {
            return pipeline.length();
        } catch (Exception e) {
            throw new ProducerException("获取管道元素总数失败");
        }
    }
    
}
