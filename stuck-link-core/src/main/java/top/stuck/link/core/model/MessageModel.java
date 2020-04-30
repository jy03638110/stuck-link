package top.stuck.link.core.model;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Date;

/**
 * Created on 2019-11-07
 *
 * @author Octopus
 */
public class MessageModel {

    /**
     * 消息类型
     * 1:短链访问
     * 2:二维码访问
     * 3:匿名访问
     */
    private Integer messageType;

    /**
     * 请求的URL
     */
    private String requestUrl;

    /**
     * 查询参数
     */
    private String queryString;

    /**
     * 来源页面
     */
    private String referer;

    /**
     * 来访者的IP地址
     */
    private String remoteAddr;
    /**
     * 来访者的端口
     */
    private String remoteHost;

    /**
     * WEB服务器的IP地址
     */
    private String localAddr;

    /**
     * WEB服务器的主机名
     */
    private String localName;

    /**
     * 浏览器信息
     */
    private String userAgent;

    /**
     * remoteUser信息
     */
    private String remoteUser;

    /**
     * cookie
     */
    private Cookie[] cookies;

    /**
     * 日期
     */
    private Date visitTime;

    /**
     * 操作系统家族
     */
    private String osFamily;
    /**
     * 操作系统详细名称
     */
    private String osName;
    /**
     * 浏览器名称和版本
     */
    private String uaName;
    /**
     * 类型
     */
    private String uaType;
    /**
     * 浏览器名称
     */
    private String uaFamily;
    /**
     * 浏览器版本
     */
    private String browserVersion;
    /**
     * 设备类型
     */
    private String deviceType;

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public String getLocalAddr() {
        return localAddr;
    }

    public void setLocalAddr(String localAddr) {
        this.localAddr = localAddr;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public Cookie[] getCookies() {
        return cookies;
    }

    public void setCookies(Cookie[] cookies) {
        this.cookies = cookies;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getOsFamily() {
        return osFamily;
    }

    public void setOsFamily(String osFamily) {
        this.osFamily = osFamily;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getUaName() {
        return uaName;
    }

    public void setUaName(String uaName) {
        this.uaName = uaName;
    }

    public String getUaType() {
        return uaType;
    }

    public void setUaType(String uaType) {
        this.uaType = uaType;
    }

    public String getUaFamily() {
        return uaFamily;
    }

    public void setUaFamily(String uaFamily) {
        this.uaFamily = uaFamily;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "requestUrl='" + requestUrl + '\'' +
                ", queryString='" + queryString + '\'' +
                ", referer='" + referer + '\'' +
                ", remoteAddr='" + remoteAddr + '\'' +
                ", remoteHost='" + remoteHost + '\'' +
                ", localAddr='" + localAddr + '\'' +
                ", localName='" + localName + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", remoteUser='" + remoteUser + '\'' +
                ", cookies=" + Arrays.toString(cookies) +
                ", visitTime=" + visitTime +
                ", osFamily='" + osFamily + '\'' +
                ", osName='" + osName + '\'' +
                ", uaName='" + uaName + '\'' +
                ", uaType='" + uaType + '\'' +
                ", uaFamily='" + uaFamily + '\'' +
                ", browserVersion='" + browserVersion + '\'' +
                ", deviceType='" + deviceType + '\'' +
                '}';
    }
}
