package top.stuck.link.core.cache;

/**
 * 通用缓存管理器接口
 * Created on 2020-05-06
 *
 * @author Octopus
 */
public interface CacheManager {

    String getString(String key);

    <T> T get(String key, Class<T> clazz);

    boolean set(String key, String value);

    boolean set(String key, String value, long time);

    boolean set(String key, Object value);

    boolean set(String key, Object value, long time);

    boolean remove(String key);

    boolean expire(String key, long time);

    boolean flush();
}
