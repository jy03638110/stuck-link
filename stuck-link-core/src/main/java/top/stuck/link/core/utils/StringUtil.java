package top.stuck.link.core.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 工具类
 * @author Octopus
 * @date 2019-01-29
 */
public class StringUtil {

    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

    private static final String NUMBER_PATTERN = "^\\d+$|-\\d+$|\\d+\\.\\d+$|-\\d+\\.\\d+$";

    private static final String URL_PATTERN = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*|)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+(\\\\?{0,1}(([A-Za-z0-9-~]+\\\\={0,1})([A-Za-z0-9-~]*)\\\\&{0,1})*)$";

    private static ObjectMapper jt = new ObjectMapper();

    static {
        jt.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 判断一个对象是否为空
     * @param obj 源对象
     * @return boolean
     */
    public static Boolean isEmpty(Object obj){
        if(obj == null){
            return true;
        }
        if(obj instanceof String){
            return "".equals(obj.toString());
        }
        return false;
    }

    /**
     * 检查String是否为空
     * @param str-目标串
     * @return true- if target string is null or an empty string
     * 		   false- else
     */
    public static boolean isEmpty(String str) {
        return (str == null) || (str.length() == 0);
    }

    /**
     * 检查String是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if ((str == null) || (str.length() == 0)) {
            return false;
        }

        Pattern pattern = Pattern.compile(NUMBER_PATTERN);

        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 生成32位UUID编号
     * @return
     */
    public static String UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 将对象转为json
     * @param obj 要转换的对象
     * @return json字符串
     */
    public static String toJson(Object obj){
        try{
            return jt.writeValueAsString(obj);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将json字符串转换为对象
     * @param jsonStr json字符串
     * @param type 转化的对象类型
     * @return 转换后的对象
     */
    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        try {
            return jt.readValue(jsonStr, clazz);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将json字符串转换为List
     * @param jsonStr
     * @return
     */
    public static <T> List<T> fromJsonAsList(String jsonStr, Class<T> clazz){
        List<T> result = null;
        try {
            JavaType type = jt.getTypeFactory().constructParametricType(List.class, clazz);
            result = jt.readValue(jsonStr, type);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 将json字符串转换为Map
     * @param jsonStr json字符串
     * @return 转换后的对象
     */
    public static <K, V> Map<K, V> fromJsonAsMap(String jsonStr, Class<K> keyClazz, Class<V> valClazz){
        Map<K, V> result = null;
        try {
            JavaType typeRef = jt.getTypeFactory().constructParametricType(Map.class, keyClazz, valClazz);
            result = jt.readValue(jsonStr, typeRef);
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return result;
    }

    public static boolean isUrl(String url) {
        if (isEmpty(url)) {
            return false;
        }
        Pattern pattern = Pattern.compile(URL_PATTERN);
        if (pattern.matcher(url).matches()) {
            return true;
        } else {
            return false;
        }
    }
}

