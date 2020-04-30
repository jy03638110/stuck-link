package top.stuck.link.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Redis配置文件
 * Created on 2019-09-17
 * @author Octopus
 */
@Component
@ConfigurationProperties(prefix = RedisProperties.PREFIX)
public class RedisProperties {

    public static final String PREFIX = "spring.redis";

    private String host = "localhost";

    private String password = "";

    private Integer port = 6379;

    private Integer database = 0;
    /**
     * 客户端超时时间单位是毫秒 默认是2000
     */
    private Integer timeout = 2000;
    /**
     * 最大空闲数
     */
    private Integer maxIdle = 30;
    /**
     * 控制一个pool可分配多少个jedis实例
     */
    private Integer maxTotal = 10;
    /**
     * 最大建立连接等待时间。如果超过此时间将接到异常
     */
    private Integer maxWaitMillis = -1;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(Integer maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

}
