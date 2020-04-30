package top.stuck.link.core.handler.pipline;

/**
 * 通用管道接口
 * Created on 2019-11-11
 *
 * @author Octopus
 */
public interface Pipeline<T> {

    public static final Integer DEFAULT_QUEUE_SIZE = 1024;

    /**
     * 向管道放元素
     * @param t
     * @return 放入管道元素数量
     */
    Integer push(T... t);

    /**
     * 获取管道中一个元素
     * @return
     */
    T poll();

    /**
     * 管道中元素长度
     * @return
     */
    Integer length();
}
