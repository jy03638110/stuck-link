package top.stuck.link.redirect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import top.stuck.link.core.serizlizer.FastJson2JsonRedisSerializer;
import top.stuck.link.core.properties.RedisProperties;
import top.stuck.link.core.utils.RedisUtil;

/**
 * Created on 2019-06-12
 *
 * @author Octopus
 */
@Configuration
@ConditionalOnProperty(prefix = "link.cache", name = "type", havingValue = "redis")
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer(String.class);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory, RedisSerializer fastJson2JsonRedisSerializer) {
        RedisTemplate<String, String> template = new RedisTemplate();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(fastJson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * redis工具类
     * @param redisTemplate
     * @return
     */
//    @Bean
    public RedisUtil redisUtil(RedisTemplate<String, String> redisTemplate) {
        return new RedisUtil(redisProperties);
    }

}
