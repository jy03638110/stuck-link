package top.stuck.link.core;

/**
 * 通用缓存管理器接口
 * Created on 2020-05-06
 *
 * @author Octopus
 */
public interface CacheManager<T> {

    public T get(String key);

    public boolean set(String key, T value);
}
