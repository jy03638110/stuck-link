package top.stuck.link.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import top.stuck.link.core.properties.RedisProperties;

/**
 * Created on 2019-06-13
 *
 * @author Octopus
 */
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static RedisProperties redisProperties;

    private static JedisPool pool;

    public RedisUtil(RedisProperties redisProperties) {
        logger.info("初始化redis工具类");
        RedisUtil.redisProperties = redisProperties;
    }

    private static JedisPool jedisPoolFactory() {
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

    public static Jedis getJedis() {
        if (pool == null) {
            pool = jedisPoolFactory();
        }
        return pool.getResource();
    }
}
