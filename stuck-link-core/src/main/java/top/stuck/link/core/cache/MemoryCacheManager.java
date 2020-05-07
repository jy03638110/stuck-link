package top.stuck.link.core.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import top.stuck.link.core.utils.StringUtil;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 2020-05-07
 *
 * @author Octopus
 */
@Component
@ConditionalOnProperty(prefix = "link.cache", name = "type", havingValue = "memory")
public class MemoryCacheManager implements CacheManager {

    private static final Integer DEFAULT_CACHE_SIZE = 1000;

    private static Map<String,Object> memoryCacheMap;

    @PostConstruct
    void init(){
        MemoryCacheManager.memoryCacheMap = new ConcurrentHashMap(DEFAULT_CACHE_SIZE);
    }

    @Override
    public String getString(String key) {
        Object obj = memoryCacheMap.get(key);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        Object obj = memoryCacheMap.get(key);
        if (obj == null) {
            return null;
        }
        if (!obj.getClass().equals(clazz)) {
            return null;
        } else {
            return clazz.cast(obj);
        }
    }

    @Override
    public boolean set(String key, String value) {
        if(StringUtil.isEmpty(key) || StringUtil.isEmpty(value)){
            return false;
        }
        memoryCacheMap.put(key, value);
        return true;
    }

    @Override
    public boolean set(String key, String value, long time) {
        set(key, value);
        return true;
    }

    @Override
    public boolean set(String key, Object value) {
        if(StringUtil.isEmpty(key) || StringUtil.isEmpty(value)){
            return false;
        }
        memoryCacheMap.put(key, value);
        return true;
    }

    @Override
    public boolean set(String key, Object value, long time) {
        set(key, value);
        return true;
    }

    @Override
    public boolean remove(String key) {
        if (StringUtil.isEmpty(key)) {
            return false;
        }
        Object remove = memoryCacheMap.remove(key);
        if (remove == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean expire(String key, long time) {
        return false;
    }

    @Override
    public boolean flush() {
        memoryCacheMap.clear();
        return true;
    }
}
