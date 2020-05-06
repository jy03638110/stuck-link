package top.stuck.link.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created on 2020-04-30
 *
 * @author Octopus
 */
public class WebUtil {

    private static Logger logger = LoggerFactory.getLogger(WebUtil.class);

    /**
     * 获取请求参数MAP集合
     *
     * @param request http请求
     *
     * @return 参数集合
     */
    public static Map<String, Object> getReqMap(HttpServletRequest request) {
        if (request == null || request.getParameterMap() == null) {
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        Map<String, String[]> reqParams = request.getParameterMap();
        Set<String> keySet = reqParams.keySet();
        for (String key : keySet) {
            String[] val = reqParams.get(key);
            if (val.length == 1) {
                params.put(key, val[0]);
            } else {
                params.put(key, val);
            }
        }
        return params;
    }

    /**
     * 从Map中获取指定名称的业务参数并转换为String类型
     *
     * @param paramName 参数名称
     * @param params Map对象
     *
     * @return 参数值
     */
    public static String getString(String paramName, Map<String, Object> params) {
        String result = "";
        if (params == null || params.size() == 0) {
            return result;
        }
        Object value = params.get(paramName);
        if (value == null) {
            return result;
        }
        return value.toString();
    }
}
