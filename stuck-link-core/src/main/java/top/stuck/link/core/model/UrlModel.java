package top.stuck.link.core.model;

/**
 * Created on 2019-11-09
 *
 * @author Octopus
 */
public class UrlModel {

    private String code;

    private String shortUrl;

    private Integer mulitClients;

    private String defaultUrl;

    private String pcUrl;

    private String appleUrl;

    private String createUserCode;

    private String createTime;

    private String invalidTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Integer getMulitClients() {
        return mulitClients;
    }

    public void setMulitClients(Integer mulitClients) {
        this.mulitClients = mulitClients;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public String getPcUrl() {
        return pcUrl;
    }

    public void setPcUrl(String pcUrl) {
        this.pcUrl = pcUrl;
    }

    public String getAppleUrl() {
        return appleUrl;
    }

    public void setAppleUrl(String appleUrl) {
        this.appleUrl = appleUrl;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }
}
