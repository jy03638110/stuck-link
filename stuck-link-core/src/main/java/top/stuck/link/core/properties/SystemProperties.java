package top.stuck.link.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created on 2020-05-07
 *
 * @author Octopus
 */
@Component
@ConfigurationProperties(prefix = SystemProperties.PREFIX)
public class SystemProperties {

    public static final String PREFIX = "link";

    private String noCodeRedirect = "";

    private Integer codeLength = 6;

    private Integer queueSize = 500;

    private Integer consumerPoolSize = 5;

    private String accessToken = "";

    public String getNoCodeRedirect() {
        return noCodeRedirect;
    }

    public void setNoCodeRedirect(String noCodeRedirect) {
        this.noCodeRedirect = noCodeRedirect;
    }

    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }

    public Integer getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Integer queueSize) {
        this.queueSize = queueSize;
    }

    public Integer getConsumerPoolSize() {
        return consumerPoolSize;
    }

    public void setConsumerPoolSize(Integer consumerPoolSize) {
        this.consumerPoolSize = consumerPoolSize;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
         this.accessToken = accessToken;
    }
}
