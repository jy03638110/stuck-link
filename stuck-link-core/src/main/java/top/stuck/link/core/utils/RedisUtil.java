package top.stuck.link.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import top.stuck.link.core.properties.RedisProperties;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2019-06-13
 *
 * @author Octopus
 */
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static RedisTemplate<String, String> redisTemplate;

    private static RedisProperties redisProperties;

    private static JedisPool pool;

    public RedisUtil(RedisTemplate<String, String> redisTemplate, RedisProperties redisProperties) {
        logger.info("初始化redis工具类:   " + redisTemplate);
        RedisUtil.redisTemplate = redisTemplate;
        RedisUtil.redisProperties = redisProperties;
    }

    public static JedisPool jedisPoolFactory() {
        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + redisProperties.getHost() + ":" + redisProperties.getPort());
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWaitMillis());

        JedisPool jedisPool;
        if (StringUtil.isEmpty(redisProperties.getPassword())) {
            jedisPool = new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(), redisProperties.getTimeout());
        } else {
            jedisPool = new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(), redisProperties.getTimeout(), redisProperties.getPassword());
        }
        return jedisPool;
    }

    public static Jedis getjedis() {
        if (pool == null) {
            pool = jedisPoolFactory();
        }
        return pool.getResource();
    }

    public static Set<String> keys(String prefix) {
        if (StringUtil.isEmpty(prefix)) {
            return new HashSet<String>();
        }
        return redisTemplate.keys(prefix + "*");
    }

    public static boolean removeKeys(String prefix) {
        if (StringUtil.isEmpty(prefix)) {
            return true;
        }
        try {
            Set<String> keys = RedisUtil.keys(prefix + "*");
            redisTemplate.delete(keys);
            return true;
        } catch (Exception e) {
            logger.error("清除Keys失败", e);
            return false;
        }
    }

    public static boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error("设置过期时间失败", e);
            return false;
        }
    }

    public static long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public static boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error("判断key是否存在失败", e);
            return false;
        }
    }

    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    public static void del(Collection<String> list) {
        if (list != null && !list.isEmpty()) {
            redisTemplate.delete(list);
        }
    }

    public static String getString(String key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    public static <T> T get(String key, Class<T> clazz) {
        String value = getString(key);
        if (value != null) {
            return StringUtil.fromJson(value, clazz);
        }
        return null;
    }

    public static boolean set(String key, String value) {
        if (value == null) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            logger.error("存入值失败", e);
            return false;
        }
    }

    public static boolean set(String key, String value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            logger.error("存入值失败", e);
            return false;
        }
    }

    public static boolean set(String key, Object value) {
        if (value == null) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, StringUtil.toJson(value));
            return true;
        } catch (Exception e) {
            logger.error("存入值失败", e);
            return false;
        }
    }

    public static boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, StringUtil.toJson(value), time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            logger.error("存入值失败", e);
            return false;
        }
    }

    public static long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public static long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    public static Long llen(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            logger.error("获取list大小失败", e);
            return -1L;
        }
    }

    public static boolean push(String key, String value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            logger.error("存入list值失败", e);
            return false;
        }
    }

    public static boolean pushAll(String key, String... values) {
        try {
            redisTemplate.opsForList().rightPushAll(key, values);
            return true;
        } catch (Exception e) {
            logger.error("存入list值失败", e);
            return false;
        }
    }

    public static Object pop(String key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().rightPop(key);
    }

    public static Object peek(String key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().index(key, 0);
    }

    public static Object poll(String key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().leftPop(key);
    }

    public static Object poll(String key, long time) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().leftPop(key, time, TimeUnit.SECONDS);
    }

    public static String listGet(String key, Long index) {
        try {
            List<String> range = redisTemplate.opsForList().range(key, index, index);
            if (range != null && !range.isEmpty()) {
                return range.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("获取范围List数据失败", e);
            return null;
        }
    }

    public static List range(String key, Long start, Long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            logger.error("获取范围List数据失败", e);
            return new ArrayList(1);
        }
    }

    public static boolean setRemove(String key, String value) {
        if (value == null) {
            return false;
        }
        try {
            redisTemplate.opsForSet().remove(key, value);
            return true;
        } catch (Exception e) {
            logger.error("清除值失败", e);
            return false;
        }
    }

    public static boolean setAdd(String key, String... value) {
        if (value == null || value.length == 0) {
            return false;
        }
        try {
            redisTemplate.opsForSet().add(key, value);
            return true;
        } catch (Exception e) {
            logger.error("新增值失败", e);
            return false;
        }
    }

}
