package top.stuck.link.core.utils;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

/**
 * Created on 2019-11-09
 *
 * @author Octopus
 */
public class UserAgentUtil {

    private static UASparser uaSparser;

    private static Logger logger = LoggerFactory.getLogger(UserAgentUtil.class);

    static {
        try {
            uaSparser = new UASparser(OnlineUpdater.getVendoredInputStream());
        } catch (IOException e) {
            logger.error("初始化失败", e);
        }
    }

    public static UserAgentInfo parse(String userAgent){
        UserAgentInfo userAgentInfo = null;
        try {
            userAgentInfo = uaSparser.parse(userAgent);
        } catch (IOException e) {
            logger.error("解析失败", e);
        }
        return userAgentInfo;
    }

    public static void main(String[] args){
        String str = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0";
        try {
            UASparser uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
            UserAgentInfo userAgentInfo = uasParser.parse(str);
            System.out.println("操作系统家族：" + userAgentInfo.getOsFamily());
            System.out.println("操作系统详细名称：" + userAgentInfo.getOsName());
            System.out.println("浏览器名称和版本:" + userAgentInfo.getUaName());
            System.out.println("类型：" + userAgentInfo.getType());
            System.out.println("浏览器名称：" + userAgentInfo.getUaFamily());
            System.out.println("浏览器版本：" + userAgentInfo.getBrowserVersionInfo());
            System.out.println("设备类型：" + userAgentInfo.getDeviceType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
