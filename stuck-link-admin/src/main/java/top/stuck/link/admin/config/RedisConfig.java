package top.stuck.link.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import top.stuck.link.core.properties.RedisProperties;
import top.stuck.link.core.utils.RedisUtil;
import top.stuck.link.core.serizlizer.FastJson2JsonRedisSerializer;

/**
 * 初始化缓存工具
 * Created on 2019-06-12
 *
 * @author Octopus
 */
@Configuration
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

    @Bean
    public RedisUtil redisUtil(RedisTemplate<String, String> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil(redisTemplate, redisProperties);
        return redisUtil;
    }

}
