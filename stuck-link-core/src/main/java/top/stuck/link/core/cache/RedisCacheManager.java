package top.stuck.link.core.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.stuck.link.core.utils.StringUtil;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2020-05-07
 *
 * @author Octopus
 */
@Component
@ConditionalOnProperty(prefix = "link.cache", name = "type", havingValue = "redis")
public class RedisCacheManager implements CacheManager {

    private static Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    void init() {
        logger.info("初始化redis缓存管理器:" + redisTemplate);
    }

    @Override
    public String getString(String key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        String value = getString(key);
        if (value != null) {
            return StringUtil.fromJson(value, clazz);
        }
        return null;
    }

    @Override
    public boolean set(String key, String value) {
        if (value == null) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean set(String key, String value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean set(String key, Object value) {
        if (value == null) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, StringUtil.toJson(value));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, StringUtil.toJson(value), time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean flush() {
        try {
            Set<String> keys = redisTemplate.keys("*");
            redisTemplate.delete(keys);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
